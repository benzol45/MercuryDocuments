package com.example.mercury.formDTO;

import com.example.mercury.entitiy.Enterprise;

public class EnterpriseDocumentNumer {
    private Enterprise enterprise;
    private int documentCounter;

    public EnterpriseDocumentNumer(Enterprise enterprise, int documentCounter) {
        this.enterprise = enterprise;
        this.documentCounter = documentCounter;
    }

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public int getDocumentCounter() {
        return documentCounter;
    }
}
