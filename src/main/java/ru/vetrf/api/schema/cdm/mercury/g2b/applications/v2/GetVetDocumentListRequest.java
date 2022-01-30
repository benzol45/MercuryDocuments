
package ru.vetrf.api.schema.cdm.mercury.g2b.applications.v2;

import javax.xml.bind.annotation.*;

import ru.vetrf.api.schema.cdm.base.DateInterval;
import ru.vetrf.api.schema.cdm.base.ListOptions;
import ru.vetrf.api.schema.cdm.dictionary.v2.BusinessMember;
import ru.vetrf.api.schema.cdm.mercury.vet_document.v2.ReferencedDocument;
import ru.vetrf.api.schema.cdm.mercury.vet_document.v2.VetDocumentStatus;
import ru.vetrf.api.schema.cdm.mercury.vet_document.v2.VetDocumentType;


/**
 * ������ �� ��������� ������ ������� ���������������.
 * 
 * <p>Java class for GetVetDocumentListRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetVetDocumentListRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://api.vetrf.ru/schema/cdm/mercury/g2b/applications/v2}MercuryApplicationRequest">
 *       &lt;sequence>
 *         &lt;element ref="{http://api.vetrf.ru/schema/cdm/base}listOptions" minOccurs="0"/>
 *         &lt;element ref="{http://api.vetrf.ru/schema/cdm/mercury/vet-document/v2}vetDocumentType" minOccurs="0"/>
 *         &lt;element ref="{http://api.vetrf.ru/schema/cdm/mercury/vet-document/v2}vetDocumentStatus" minOccurs="0"/>
 *         &lt;element ref="{http://api.vetrf.ru/schema/cdm/mercury/vet-document/v2}issueDateInterval" minOccurs="0"/>
 *         &lt;element ref="{http://api.vetrf.ru/schema/cdm/mercury/vet-document/v2}consignor" minOccurs="0"/>
 *         &lt;element ref="{http://api.vetrf.ru/schema/cdm/mercury/vet-document/v2}referencedDocument" minOccurs="0"/>
 *         &lt;element ref="{http://api.vetrf.ru/schema/cdm/dictionary/v2}enterpriseGuid"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetVetDocumentListRequest", propOrder = {
    "listOptions",
    "vetDocumentType",
    "vetDocumentStatus",
    "issueDateInterval",
    "consignor",
    "referencedDocument",
    "enterpriseGuid"
})
@XmlRootElement(name = "getVetDocumentListRequest")
public class GetVetDocumentListRequest
    extends MercuryApplicationRequest
{

    @XmlElement(namespace = "http://api.vetrf.ru/schema/cdm/base")
    protected ListOptions listOptions;
    @XmlElement(namespace = "http://api.vetrf.ru/schema/cdm/mercury/vet-document/v2")
    @XmlSchemaType(name = "string")
    protected VetDocumentType vetDocumentType;
    @XmlElement(namespace = "http://api.vetrf.ru/schema/cdm/mercury/vet-document/v2")
    @XmlSchemaType(name = "string")
    protected VetDocumentStatus vetDocumentStatus;
    @XmlElement(namespace = "http://api.vetrf.ru/schema/cdm/mercury/vet-document/v2")
    protected DateInterval issueDateInterval;
    @XmlElement(namespace = "http://api.vetrf.ru/schema/cdm/mercury/vet-document/v2")
    protected BusinessMember consignor;
    @XmlElement(namespace = "http://api.vetrf.ru/schema/cdm/mercury/vet-document/v2")
    protected ReferencedDocument referencedDocument;
    @XmlElement(namespace = "http://api.vetrf.ru/schema/cdm/dictionary/v2", required = true)
    protected String enterpriseGuid;

    /**
     * 
     *                         ��������� �������������� ������.
     *                      
     * 
     * @return
     *     possible object is
     *     {@link ListOptions }
     *     
     */
    public ListOptions getListOptions() {
        return listOptions;
    }

    /**
     * Sets the value of the listOptions property.
     * 
     * @param value
     *     allowed object is
     *     {@link ListOptions }
     *     
     */
    public void setListOptions(ListOptions value) {
        this.listOptions = value;
    }

    /**
     * ��� ��������������.
     * 
     * @return
     *     possible object is
     *     {@link VetDocumentType }
     *     
     */
    public VetDocumentType getVetDocumentType() {
        return vetDocumentType;
    }

    /**
     * Sets the value of the vetDocumentType property.
     * 
     * @param value
     *     allowed object is
     *     {@link VetDocumentType }
     *     
     */
    public void setVetDocumentType(VetDocumentType value) {
        this.vetDocumentType = value;
    }

    /**
     * ������ ��������������.
     * 
     * @return
     *     possible object is
     *     {@link VetDocumentStatus }
     *     
     */
    public VetDocumentStatus getVetDocumentStatus() {
        return vetDocumentStatus;
    }

    /**
     * Sets the value of the vetDocumentStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link VetDocumentStatus }
     *     
     */
    public void setVetDocumentStatus(VetDocumentStatus value) {
        this.vetDocumentStatus = value;
    }

    /**
     * ������ ��� ���������� ���������.
     *                         ������������� ���� issueDate ��������� � �������������� ������.
     *                         ������������ ����� ��������� -- 31 ����.
     *                      
     * 
     * @return
     *     possible object is
     *     {@link DateInterval }
     *     
     */
    public DateInterval getIssueDateInterval() {
        return issueDateInterval;
    }

    /**
     * Sets the value of the issueDateInterval property.
     * 
     * @param value
     *     allowed object is
     *     {@link DateInterval }
     *     
     */
    public void setIssueDateInterval(DateInterval value) {
        this.issueDateInterval = value;
    }

    /**
     * ����������� ��� ������������� ���.
     *                         ���������� �������������� �� ����������� �������������� �� (consignor/businessEntity/guid)
     *                         � �������� (consignor/enterprise/guid).
     *                         ���� ������������ ����� �� ���������������� ���������� (���������� ������ vetDocumentType = PRODUCTIVE),
     *                         ���� consignor ������������.
     *                      
     * 
     * @return
     *     possible object is
     *     {@link BusinessMember }
     *     
     */
    public BusinessMember getConsignor() {
        return consignor;
    }

    /**
     * Sets the value of the consignor property.
     * 
     * @param value
     *     allowed object is
     *     {@link BusinessMember }
     *     
     */
    public void setConsignor(BusinessMember value) {
        this.consignor = value;
    }

    /**
     * ��������� ��������.
     *                         �������������� ����� �� ��������� ���������� � ���������� (relationshipType) ������ 1 � 6,
     *                         �������������� ���� ����������: 1-5 (������������ ��������).
     *                         ��� ��������� ������������� � ���������� �������� ���� ��� (type), ����� (issueNumber) � ���� (issueDate).
     *                         ��� ������ �� ���������� ��������� ����������� ���������� ������� vetDocumentType.
     *                      
     * 
     * @return
     *     possible object is
     *     {@link ReferencedDocument }
     *     
     */
    public ReferencedDocument getReferencedDocument() {
        return referencedDocument;
    }

    /**
     * Sets the value of the referencedDocument property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReferencedDocument }
     *     
     */
    public void setReferencedDocument(ReferencedDocument value) {
        this.referencedDocument = value;
    }

    /**
     * ������������� �����������, �� �������� ������������ ����� �������.
     *                         � �������������� ������ ������� (� ����� ������, ���� �� ����������� ������ ��������� ������):
     *                         1) ������������ ���, ��� ������� ��-����������� ������������� ��������� (issuer), �����������-����������� -- ���������� �������������� (enterpriseGuid).
     *                         2) ������������ ���, ��� ������� ��-���������� ������������� ��������� (issuer), �����������-���������� -- ���������� �������������� (enterpriseGuid).
     *                         3) ���������������� ���, ��� ������� ��-������������� ������������� ��������� (issuer), �����������-������������� -- ���������� �������������� (enterpriseGuid).
     *                      
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnterpriseGuid() {
        return enterpriseGuid;
    }

    /**
     * Sets the value of the enterpriseGuid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnterpriseGuid(String value) {
        this.enterpriseGuid = value;
    }

}
