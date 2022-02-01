package com.example.mercury.service;

import com.example.mercury.entitiy.Document;
import com.example.mercury.entitiy.Enterprise;
import com.example.mercury.entitiy.User;
import com.example.mercury.formDTO.NotificationProcessingMessage;

import java.util.List;

public interface MercuryService {
    List<Document> getDocumentFromMercuryByEntreprise(Enterprise enterprise);
    void addNewDocumentFromMercuryToBaseByUSer(User user);
    void processDocument(Document document);
    void processAllDocumentByEnterprise(Enterprise enterprise, NotificationProcessingMessage message, String notificationId);

    void processAllDocumentByUser(User user, String notificationId);

    boolean isVerifed(Enterprise enterprise, String uuid);
    String getName(Enterprise enterprise,String uuid);
}
