package com.example.mercury.service;

import com.example.mercury.entitiy.Document;
import com.example.mercury.entitiy.Enterprise;
import com.example.mercury.entitiy.User;
import com.example.mercury.formDTO.NotificationProcessingMessage;

import java.util.List;

public interface MercuryService {
    void getAndSaveAllDocumentFromMercuryToBase();
    void getAndSaveDocumentFromMercuryToBaseByUSer(User user);

    void processAllDocumentByEnterprise(Enterprise enterprise, NotificationProcessingMessage message, String notificationId);
    void processAllDocumentByUser(User user, String notificationId);
    void processAllDocumentByAutoprocessedEnterprise();

    boolean isEnterpriseVerifed(Enterprise enterprise, String uuid);
    String getEnterpriseName(Enterprise enterprise, String uuid);
}
