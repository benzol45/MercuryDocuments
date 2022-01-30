package com.example.mercury.service;

import com.example.mercury.formDTO.NotificationProcessingMessage;

public interface NotificationService {
    NotificationProcessingMessage getMessage(int totalEnterprise, int totalDocuments);
    void setTotalDocuments(NotificationProcessingMessage message, int totalDocuments);
    void setProcessDocumentToZero(NotificationProcessingMessage message);
    void increaseProcessedEnterprise(NotificationProcessingMessage message);
    void increaseProcessedDocuments(NotificationProcessingMessage message);
    void send(NotificationProcessingMessage message);
}
