package com.example.mercury.service.impl;

import com.example.mercury.entitiy.Document;
import com.example.mercury.entitiy.Enterprise;
import com.example.mercury.entitiy.User;
import com.example.mercury.factory.MercuryApplicationManagementServiceFactory;
import com.example.mercury.factory.MercuryEnterpriseServiceFactory;
import com.example.mercury.formDTO.NotificationProcessingMessage;
import com.example.mercury.service.DocumentService;
import com.example.mercury.service.EnterpriseService;
import com.example.mercury.service.MercuryService;
import com.example.mercury.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.vetrf.api.schema.cdm.application.Application;
import ru.vetrf.api.schema.cdm.application.ApplicationResultWrapper;
import ru.vetrf.api.schema.cdm.application.ApplicationStatus;
import ru.vetrf.api.schema.cdm.application.service.*;
import ru.vetrf.api.schema.cdm.application.ws_definitions.ReceiveApplicationResultRequest;
import ru.vetrf.api.schema.cdm.application.ws_definitions.ReceiveApplicationResultResponse;
import ru.vetrf.api.schema.cdm.dictionary.v2.DocumentNature;
import ru.vetrf.api.schema.cdm.mercury.g2b.applications.v2.*;
import ru.vetrf.api.schema.cdm.mercury.g2b.applications.v2.ObjectFactory;
import ru.vetrf.api.schema.cdm.mercury.vet_document.v2.*;
import ru.vetrf.api.schema.cdm.registry.enterprise.service.v2.*;
import ru.vetrf.api.schema.cdm.registry.enterprise.service.v2.IncorrectRequestFault;
import ru.vetrf.api.schema.cdm.registry.enterprise.service.v2.InternalServiceFault;
import ru.vetrf.api.schema.cdm.registry.ws_definitions.v2.GetEnterpriseByGuidRequest;
import ru.vetrf.api.schema.cdm.registry.ws_definitions.v2.GetEnterpriseByGuidResponse;

import javax.xml.bind.JAXBElement;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.Holder;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class MercuryServiceImpl implements MercuryService {
    Environment environment;
    DocumentService documentService;
    EnterpriseService enterpriseService;
    NotificationService notificationService;

    @Autowired
    public MercuryServiceImpl(Environment environment, DocumentService documentService, EnterpriseService enterpriseService, NotificationService notificationService) {
        this.environment = environment;
        this.documentService = documentService;
        this.enterpriseService = enterpriseService;
        this.notificationService = notificationService;
    }

    private List<Document> getDocumentFromMercuryByEntreprise(Enterprise enterprise) {
        //В классе GetVetDocumentListRequest добавил @XmlRootElement(name = "getVetDocumentListRequest") для сериализации
        GetVetDocumentListRequest getVetDocumentListRequest = new ru.vetrf.api.schema.cdm.mercury.g2b.applications.v2.ObjectFactory().createGetVetDocumentListRequest();
        getVetDocumentListRequest.setLocalTransactionId(UUID.randomUUID().toString());
        getVetDocumentListRequest.setInitiator(new ru.vetrf.api.schema.cdm.mercury.vet_document.v2.ObjectFactory().createUser());
        getVetDocumentListRequest.getInitiator().setLogin(enterprise.getMercLogin());
        getVetDocumentListRequest.setListOptions(new ru.vetrf.api.schema.cdm.base.ObjectFactory().createListOptions());
        getVetDocumentListRequest.setVetDocumentType(VetDocumentType.INCOMING);
        getVetDocumentListRequest.setVetDocumentStatus(VetDocumentStatus.CONFIRMED);
        getVetDocumentListRequest.setEnterpriseGuid(enterprise.getUuid().toString());
        Holder<Application> applicationHolder = getHolderOfMercuryApplicationRequest(enterprise, getVetDocumentListRequest);

        ApplicationResultWrapper resultWrapper = executeRequestAndGetResult(enterprise, applicationHolder);

        JAXBElement<GetVetDocumentListResponse> any = (JAXBElement<GetVetDocumentListResponse>) resultWrapper.getAny();
        List<VetDocument> vetDocumentList = any.getValue().getVetDocumentList().getVetDocument();

        return convertListVetdocumentToListDocument(enterprise, vetDocumentList);
    }

    private List<Document> convertListVetdocumentToListDocument(Enterprise enterprise, List<VetDocument> vetDocumentList) {
        Function convertVetDocumentToDocument = o -> {
            Document document = new Document();
            VetDocument vetDocument = (VetDocument)o;
            document.setUuid(UUID.fromString(vetDocument.getUuid()));
            document.setDate(vetDocument.getIssueDate().toGregorianCalendar().getTime());
            document.setEnterprise(enterprise);
            Batch batch = vetDocument.getCertifiedConsignment().getBatch();
            document.setContent(batch.getProductItem().getName() + " / " + batch.getVolume().toString());
            document.setProcessed(false);
            return document;
        };
        return (List<Document>)vetDocumentList.stream()
                .map(convertVetDocumentToDocument)
                .collect(Collectors.toList());
    }

    @Override
    @Scheduled(fixedDelayString = "${mercury.sheduler-get-new-documents-delay-ms}")
    public void getAndSaveAllDocumentFromMercuryToBase() {
        Logger logger = LoggerFactory.getLogger(MercuryServiceImpl.class);
        logger.trace("Start get documents from Mercury");
        List<Enterprise> enterpriseList = enterpriseService.getAll();
        for (Enterprise enterprise: enterpriseList) {
            logger.trace("Enterprise: " + enterprise.getMercuryName());
            List<Document> documentListByEnterprise = getDocumentFromMercuryByEntreprise(enterprise);
            logger.trace("Recived: " + documentListByEnterprise.size() + " docs");

            int addedCounter = 0;
            for (Document document:documentListByEnterprise) {
                if (documentService.getByUUID(document.getUuid())==null) {
                    documentService.add(document);
                    addedCounter++;
                }
            }
            logger.trace("Added new: " + addedCounter + " docs");
        }
        logger.trace("End get documents from Mercury");
    }

    @Override
    public void getAndSaveDocumentFromMercuryToBaseByUSer(User user) {
        List<Document> documentList = new ArrayList<>();

        List<Enterprise> enterpriseList = enterpriseService.getAllByUser(user);
        for (Enterprise enterprise: enterpriseList) {
            List<Document> documentListByEnterprise = getDocumentFromMercuryByEntreprise(enterprise);
            documentList.addAll(documentListByEnterprise);
        }

        for (Document document: documentList) {
            if (documentService.getByUUID(document.getUuid())==null) {
                documentService.add(document);
            }
        }
    }


    private void processDocument(Document document) {
        if (Boolean.parseBoolean(environment.getProperty("mercury.fake-documents-processing"))){
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {}
            return;
        }

        VetDocument vetDocument = getVetDocumentFromMercuryByUUID(document.getEnterprise(),document.getUuid());
        //TODO На полученных доках проверять а не погашены ли они уже ? если да то только писать в базу новый статус
        boolean result = processVetDocument(document.getEnterprise(),vetDocument);
        if (result) {
            document.setProcessed(true);
            documentService.update(document);
        };
    }

    private VetDocument getVetDocumentFromMercuryByUUID(Enterprise enterprise, UUID uuid) {
        //В классе GetVetDocumentByUuidRequest добавил @XmlRootElement(name = "getVetDocumentByUuidRequest") для сериализации
        GetVetDocumentByUuidRequest getVetDocumentByUuidRequest = new ru.vetrf.api.schema.cdm.mercury.g2b.applications.v2.ObjectFactory().createGetVetDocumentByUuidRequest();
        getVetDocumentByUuidRequest.setLocalTransactionId(UUID.randomUUID().toString());
        getVetDocumentByUuidRequest.setInitiator(new ru.vetrf.api.schema.cdm.mercury.vet_document.v2.ObjectFactory().createUser());
        getVetDocumentByUuidRequest.getInitiator().setLogin(enterprise.getMercLogin());
        getVetDocumentByUuidRequest.setUuid(uuid.toString());
        getVetDocumentByUuidRequest.setEnterpriseGuid(enterprise.getUuid().toString());
        Holder<Application> applicationHolder = getHolderOfMercuryApplicationRequest(enterprise, getVetDocumentByUuidRequest);

        ApplicationResultWrapper resultWrapper = executeRequestAndGetResult(enterprise,applicationHolder);

        JAXBElement<GetVetDocumentByUuidResponse> any = (JAXBElement<GetVetDocumentByUuidResponse>) resultWrapper.getAny();
        return any.getValue().getVetDocument();
    }

    private boolean processVetDocument(Enterprise enterprise, VetDocument vetDocument) {
        Batch batch = vetDocument.getCertifiedConsignment().getBatch();
        Consignment consignment = convertBatchToConsignment(batch);

        //В классе ProcessIncomingConsignmentRequest добавил @XmlRootElement(name = "processIncomingConsignmentRequest") для сериализации
        ProcessIncomingConsignmentRequest processIncomingConsignmentRequest = new ObjectFactory().createProcessIncomingConsignmentRequest();
        processIncomingConsignmentRequest.setLocalTransactionId(UUID.randomUUID().toString());
        processIncomingConsignmentRequest.setInitiator(new ru.vetrf.api.schema.cdm.mercury.vet_document.v2.ObjectFactory().createUser());
        processIncomingConsignmentRequest.getInitiator().setLogin(enterprise.getMercLogin());
        processIncomingConsignmentRequest.setDelivery(new ru.vetrf.api.schema.cdm.mercury.vet_document.v2.ObjectFactory().createDelivery());
        processIncomingConsignmentRequest.getDelivery().setDeliveryDate(getDateInGregorianCalendar(new Date()));
        processIncomingConsignmentRequest.getDelivery().setConsignor(vetDocument.getCertifiedConsignment().getConsignor());
        processIncomingConsignmentRequest.getDelivery().setConsignee(vetDocument.getCertifiedConsignment().getConsignee());
        processIncomingConsignmentRequest.getDelivery().getConsignment().add(consignment);
        processIncomingConsignmentRequest.getDelivery().setTransportInfo(vetDocument.getCertifiedConsignment().getTransportInfo());
        processIncomingConsignmentRequest.getDelivery().setTransportStorageType(vetDocument.getCertifiedConsignment().getTransportStorageType());
        processIncomingConsignmentRequest.getDelivery().setAccompanyingForms(new ru.vetrf.api.schema.cdm.mercury.vet_document.v2.ObjectFactory().createConsignmentDocumentList());
        processIncomingConsignmentRequest.getDelivery().getAccompanyingForms().setWaybill(convertReferencedDocumentsToWaybill(vetDocument.getReferencedDocument()));
        processIncomingConsignmentRequest.getDelivery().getAccompanyingForms().getVetCertificate().add(vetDocument);
        processIncomingConsignmentRequest.setDeliveryFacts(new ru.vetrf.api.schema.cdm.mercury.vet_document.v2.ObjectFactory().createDeliveryFactList());
        processIncomingConsignmentRequest.getDeliveryFacts().setVetCertificatePresence(DocumentNature.ELECTRONIC);
        processIncomingConsignmentRequest.getDeliveryFacts().setDocInspection(new ru.vetrf.api.schema.cdm.mercury.vet_document.v2.ObjectFactory().createDeliveryInspection());
        processIncomingConsignmentRequest.getDeliveryFacts().getDocInspection().setResult(DeliveryInspectionResult.CORRESPONDS);
        processIncomingConsignmentRequest.getDeliveryFacts().setVetInspection(new ru.vetrf.api.schema.cdm.mercury.vet_document.v2.ObjectFactory().createDeliveryInspection());
        processIncomingConsignmentRequest.getDeliveryFacts().getVetInspection().setResult(enterprise.getVetInspection()?DeliveryInspectionResult.CORRESPONDS:DeliveryInspectionResult.UNSUPERVISED);
        processIncomingConsignmentRequest.getDeliveryFacts().setDecision(DeliveryDecision.ACCEPT_ALL);
        Holder<Application> applicationHolder = getHolderOfMercuryApplicationRequest(enterprise, processIncomingConsignmentRequest);

        ApplicationResultWrapper resultWrapper = executeRequestAndGetResult(enterprise,applicationHolder);

        JAXBElement<ProcessIncomingConsignmentResponse> any = (JAXBElement<ProcessIncomingConsignmentResponse>) resultWrapper.getAny();
        if (any.getValue().getVetDocument().get(0).getVetDStatus()==VetDocumentStatus.UTILIZED) {
            return true;
        } else {
            throw new RuntimeException("Гашение не было выполнено. Статус после попытки гашения не UTILIZED");
        }
    }

    private Consignment convertBatchToConsignment(Batch batch) {
        Consignment consignment = new ru.vetrf.api.schema.cdm.mercury.vet_document.v2.ObjectFactory().createConsignment();
        consignment.setProductType(batch.getProductType());
        consignment.setProduct(batch.getProduct());
        consignment.setSubProduct(batch.getSubProduct());
        consignment.setProductItem(batch.getProductItem());
        consignment.setVolume(batch.getVolume());
        consignment.setUnit(batch.getUnit());
        consignment.setDateOfProduction(batch.getDateOfProduction());
        consignment.setExpiryDate(batch.getExpiryDate());
        for (String s:batch.getBatchID()){
            consignment.getBatchID().add(s);
        }
        consignment.setPerishable(batch.isPerishable());
        consignment.setOrigin(batch.getOrigin());
        consignment.setLowGradeCargo(batch.isLowGradeCargo());
        consignment.setPackageList(batch.getPackageList());

        return consignment;
    }

    private Waybill convertReferencedDocumentsToWaybill(List<ReferencedDocument> referencedDocuments) {
        for (ReferencedDocument rd : referencedDocuments) {
            if (rd.getType().intValue() == 1) {
                Waybill waybill = new ru.vetrf.api.schema.cdm.mercury.vet_document.v2.ObjectFactory().createWaybill();
                waybill.setType(rd.getType());
                waybill.setIssueSeries(rd.getIssueSeries());
                waybill.setIssueNumber(rd.getIssueNumber());
                waybill.setIssueDate(rd.getIssueDate());
                return waybill;
            }
        }
        return null;
    }

    @Override
    public void processAllDocumentByEnterprise(Enterprise enterprise, NotificationProcessingMessage message, String notificationId) {
        List<Document> documentList = documentService.getNotProcessedByEnterprise(enterprise);
        notificationService.setTotalDocuments(message,documentList.size());
        notificationService.setProcessDocumentToZero(message);
        notificationService.send(message, notificationId);
        for (Document document: documentList) {
            processDocument(document);
            notificationService.increaseProcessedDocuments(message);
            notificationService.send(message, notificationId);
        }
    }

    @Override
    public void processAllDocumentByUser(User user, String notificationId) {
        List<Enterprise> enterpriseList = enterpriseService.getAllByUser(user);
        NotificationProcessingMessage message = notificationService.getMessage(enterpriseList.size(),0);
        for (Enterprise enterprise: enterpriseList) {
            processAllDocumentByEnterprise(enterprise, message, notificationId);
            notificationService.increaseProcessedEnterprise(message);
        }
    }

    @Override
    public boolean isEnterpriseVerifed(Enterprise enterprise, String uuid) {
        try {
            return !getEnterpriseNameFromMercuryByUUID(enterprise, uuid).equals("");
        } catch (EntityNotFoundFault | IncorrectRequestFault | InternalServiceFault e) {
            return false;
        }
    }

    @Override
    public String getEnterpriseName(Enterprise enterprise, String uuid) {
        try {
            return getEnterpriseNameFromMercuryByUUID(enterprise, uuid);
        } catch (EntityNotFoundFault | IncorrectRequestFault | InternalServiceFault e) {
            return "";
        }
    }

    private String getEnterpriseNameFromMercuryByUUID(Enterprise enterprise, String uuid) throws EntityNotFoundFault, IncorrectRequestFault, InternalServiceFault {
        Authenticator.setDefault(new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(enterprise.getVetisApiLogin(),enterprise.getVetisApiPassword().toCharArray());}
        });

        GetEnterpriseByGuidRequest getEnterpriseByGuidRequest = new ru.vetrf.api.schema.cdm.registry.ws_definitions.v2.ObjectFactory().createGetEnterpriseByGuidRequest();
        getEnterpriseByGuidRequest.setGuid(uuid);


        EnterpriseServicePortType enterpriseServicePortType = MercuryEnterpriseServiceFactory.getInstance();
        GetEnterpriseByGuidResponse enterpriseByGuidResponse = enterpriseServicePortType.getEnterpriseByGuid(getEnterpriseByGuidRequest);
        return enterpriseByGuidResponse.getEnterprise().getName() + " " + enterpriseByGuidResponse.getEnterprise().getAddress().getAddressView();
    }

    private Holder<Application> getHolderOfMercuryApplicationRequest(Enterprise enterprise, MercuryApplicationRequest mercuryApplicationRequest) {
        Application application = new ru.vetrf.api.schema.cdm.application.ObjectFactory().createApplication();
        application.setServiceId("mercury-g2b.service:2.1");
        application.setIssuerId(enterprise.getVetisApiIssuerID());
        application.setIssueDate(getDateInGregorianCalendar(new Date()));
        application.setData(new ru.vetrf.api.schema.cdm.application.ObjectFactory().createApplicationDataWrapper());
        application.getData().setAny(mercuryApplicationRequest);
        Holder<Application> applicationHolder = new Holder<>(application);

        return applicationHolder;
    }

    private XMLGregorianCalendar getDateInGregorianCalendar(Date date) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        try {
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
        } catch (DatatypeConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    private ApplicationResultWrapper executeRequestAndGetResult(Enterprise enterprise, Holder<Application> request) {
        Authenticator.setDefault(new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(enterprise.getVetisApiLogin(), enterprise.getVetisApiPassword().toCharArray());
            }
        });

        ApplicationManagementServicePortType applicationManagementServicePortType = MercuryApplicationManagementServiceFactory.getInstance();
        try {
            applicationManagementServicePortType.submitApplicationRequest(enterprise.getVetisApiKey(),request);
        } catch (AccessDeniedFault
                | ru.vetrf.api.schema.cdm.application.service.IncorrectRequestFault
                | ru.vetrf.api.schema.cdm.application.service.InternalServiceFault
                | UnknownServiceIdFault
                | UnsupportedApplicationDataTypeFault e) {
            throw new RuntimeException(e);
        }
        if (request.value.getStatus()!= ApplicationStatus.ACCEPTED) {
            throw new RuntimeException("Веб-сервер ГИС Меркурий отклонил запрос");
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        ReceiveApplicationResultResponse receiveApplicationResultResponse = null;
        ReceiveApplicationResultRequest receiveApplicationResultRequest = new ru.vetrf.api.schema.cdm.application.ws_definitions.ObjectFactory().createReceiveApplicationResultRequest();
        receiveApplicationResultRequest.setApplicationId(request.value.getApplicationId());
        receiveApplicationResultRequest.setApiKey(enterprise.getVetisApiKey());
        receiveApplicationResultRequest.setIssuerId(enterprise.getVetisApiIssuerID());
        int checkCounter = 0;
        do {
            checkCounter++;
            if (checkCounter>300) {
                throw  new RuntimeException("After 600s request is IN_PROCESS");
            }
            try {
                receiveApplicationResultResponse = applicationManagementServicePortType.receiveApplicationResult(receiveApplicationResultRequest);
            } catch (AccessDeniedFault
                    | ApplicationNotFoundFault
                    | ru.vetrf.api.schema.cdm.application.service.IncorrectRequestFault
                    | ru.vetrf.api.schema.cdm.application.service.InternalServiceFault e) {
                throw new RuntimeException(e);
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } while (receiveApplicationResultResponse.getApplication().getStatus() == ApplicationStatus.IN_PROCESS);

        if (receiveApplicationResultResponse.getApplication().getStatus() == ApplicationStatus.REJECTED) {
            throw new RuntimeException("На запрос от ГИС Меркурий получент статус REJECTED");
        }

        return receiveApplicationResultResponse.getApplication().getResult();
    }
}
