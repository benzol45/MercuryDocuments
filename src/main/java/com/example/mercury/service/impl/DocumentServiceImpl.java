package com.example.mercury.service.impl;

import com.example.mercury.entitiy.Document;
import com.example.mercury.entitiy.Enterprise;
import com.example.mercury.repository.DocumentRepository;
import com.example.mercury.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DocumentServiceImpl implements DocumentService {
    DocumentRepository documentRepository;

    @Autowired
    public DocumentServiceImpl(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Override
    public void add(Document document) {
        documentRepository.add(document);
    }

    @Override
    public void update(Document document) {
        documentRepository.update(document);
    }

    @Override
    public Document getByUUID(UUID uuid) {
        return documentRepository.getByUUID(uuid);
    }

    @Override
    public List<Document> getNotProcessedByEnterprise(Enterprise enterprise) {
        return documentRepository.getNotProcessedByEnterprise(enterprise);
    }

    @Override
    public List<Document> getAllByEnterprise(Enterprise enterprise) {
        return documentRepository.getAllByEnterprise(enterprise);
    }
}
