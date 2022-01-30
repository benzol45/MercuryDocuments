package com.example.mercury.factory;

import ru.vetrf.api.schema.cdm.registry.enterprise.service.v2.EnterpriseServiceBindingQSService;
import ru.vetrf.api.schema.cdm.registry.enterprise.service.v2.EnterpriseServicePortType;

public class MercuryEnterpriseServiceFactory {
    private static final EnterpriseServicePortType enterpriseServicePortType;

    private MercuryEnterpriseServiceFactory() {}

    public static EnterpriseServicePortType getInstance() {
        return enterpriseServicePortType;
    }

    static {
        EnterpriseServiceBindingQSService enterpriseServiceBindingQSService = new EnterpriseServiceBindingQSService();
        enterpriseServicePortType = enterpriseServiceBindingQSService.getEnterpriseServiceBindingQSPort();
    }

}
