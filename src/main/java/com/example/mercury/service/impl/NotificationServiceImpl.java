package com.example.mercury.service.impl;

import com.example.mercury.formDTO.NotificationProcessingMessage;
import com.example.mercury.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {
    private SimpMessagingTemplate template;

    @Autowired
    public NotificationServiceImpl(SimpMessagingTemplate template) {
        this.template = template;
    }

    @Override
    public NotificationProcessingMessage getMessage(int totalEnterprise, int totalDocuments) {
        return new NotificationProcessingMessage(0,totalEnterprise,0,totalDocuments);
    }

    @Override
    public void setTotalDocuments(NotificationProcessingMessage message, int totalDocuments) {
        if (message==null) return;
        message.setTotalDocuments(totalDocuments);
    }

    @Override
    public void setProcessDocumentToZero(NotificationProcessingMessage message) {
        if (message==null) return;
        message.setProcessedDocuments(0);
    }

    @Override
    public void increaseProcessedEnterprise(NotificationProcessingMessage message) {
        if (message==null) return;
        message.setProcessedEnterprise(message.getProcessedEnterprise()+1);
    }

    @Override
    public void increaseProcessedDocuments(NotificationProcessingMessage message) {
        if (message==null) return;
        message.setProcessedDocuments(message.getProcessedDocuments()+1);
    }

    @Override
    public void send(NotificationProcessingMessage message, String notificationId) {
        if (notificationId==null) return;
        if (message==null) return;
        if (message.getTotalEnterprise()!=0 && message.getTotalDocuments()!=0) {
            generateContent(message);
        }
        template.convertAndSend("/topic/notification/"+notificationId, message);
    }

    private void generateContent(NotificationProcessingMessage message) {
        message.setContent(new StringBuilder()
                .append("Обработано предприятий ")
                .append(message.getProcessedEnterprise())
                .append(" / ")
                .append(message.getTotalEnterprise())
                .append("<br>")
                .append("Обработано документов предприятия ")
                .append(message.getProcessedDocuments())
                .append(" / ")
                .append(message.getTotalDocuments())
                .toString());
    }
}
