package com.example.mercury.formDTO;

public class Statistic {
    private int userCount;
    private int enterpriseCount;
    private int documentCount;
    private int documentNotProcessedCount;

    public Statistic(int userCount, int enterpriseCount, int documentCount, int documentNotProcessedCount) {
        this.userCount = userCount;
        this.enterpriseCount = enterpriseCount;
        this.documentCount = documentCount;
        this.documentNotProcessedCount = documentNotProcessedCount;
    }

    public int getUserCount() {
        return userCount;
    }

    public int getEnterpriseCount() {
        return enterpriseCount;
    }

    public int getDocumentCount() {
        return documentCount;
    }

    public int getDocumentNotProcessedCount() {
        return documentNotProcessedCount;
    }
}
