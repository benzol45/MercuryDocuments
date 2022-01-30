package com.example.mercury.repository;

import com.example.mercury.entitiy.Document;
import com.example.mercury.entitiy.Enterprise;

import java.util.List;
import java.util.UUID;

public interface DocumentRepository {
    void add(Document document);
    void update(Document document);
    Document getByUUID(UUID uuid);
    List<Document> getNotProcessedByEnterprise(Enterprise enterprise);
    List<Document> getAllByEnterprise(Enterprise enterprise);
    int getAllCount();
    int getNotProcesseCount();
}
