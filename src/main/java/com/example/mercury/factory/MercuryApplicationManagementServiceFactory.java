package com.example.mercury.factory;

import ru.vetrf.api.schema.cdm.application.service.ApplicationManagementServiceBindingQSService;
import ru.vetrf.api.schema.cdm.application.service.ApplicationManagementServicePortType;

public class MercuryApplicationManagementServiceFactory {

        private static final ApplicationManagementServicePortType applicationManagementServicePortType;

        private MercuryApplicationManagementServiceFactory() {}

        public static ApplicationManagementServicePortType getInstance() {
            return applicationManagementServicePortType;
        }

        static {
            ApplicationManagementServiceBindingQSService applicationManagementServiceBindingQSService = new ApplicationManagementServiceBindingQSService();
            applicationManagementServicePortType = applicationManagementServiceBindingQSService.getApplicationManagementServiceBindingQSPort();
        }


}
