package com.example.mercury.formDTO;

public class NotificationProcessingMessage {
    private int processedEnterprise;
    private int totalEnterprise;
    private int processedDocuments;
    private int totalDocuments;

    private String content;

    public NotificationProcessingMessage() {
    }

    public NotificationProcessingMessage(String content) {
        this.content = content;
    }

    public NotificationProcessingMessage(int processedEnterprise, int totalEnterprise, int processedDocuments, int totalDocuments) {
        this.processedEnterprise = processedEnterprise;
        this.totalEnterprise = totalEnterprise;
        this.processedDocuments = processedDocuments;
        this.totalDocuments = totalDocuments;
    }

    public int getProcessedEnterprise() {
        return processedEnterprise;
    }

    public void setProcessedEnterprise(int processedEnterprise) {
        this.processedEnterprise = processedEnterprise;
    }

    public int getTotalEnterprise() {
        return totalEnterprise;
    }

    public void setTotalEnterprise(int totalEnterprise) {
        this.totalEnterprise = totalEnterprise;
    }

    public int getProcessedDocuments() {
        return processedDocuments;
    }

    public void setProcessedDocuments(int processedDocuments) {
        this.processedDocuments = processedDocuments;
    }

    public int getTotalDocuments() {
        return totalDocuments;
    }

    public void setTotalDocuments(int totalDocuments) {
        this.totalDocuments = totalDocuments;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
