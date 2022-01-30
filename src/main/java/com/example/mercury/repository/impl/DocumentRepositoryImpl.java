package com.example.mercury.repository.impl;

import com.example.mercury.entitiy.Document;
import com.example.mercury.entitiy.Enterprise;
import com.example.mercury.repository.DocumentRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Repository
public class DocumentRepositoryImpl implements DocumentRepository {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Document getByUUID(UUID uuid) {
        return entityManager.find(Document.class,uuid);
    }

    @Override
    @Transactional
    public void add(Document document) {
        entityManager.persist(document);
    }

    @Override
    @Transactional
    public void update(Document document) {
        entityManager.merge(document);
    }

    @Override
    public List<Document> getNotProcessedByEnterprise(Enterprise enterprise) {
        List<Document> documentList = entityManager.createQuery("select d from Document d where d.processed=false and d.enterprise=:Enterprise")
                .setParameter("Enterprise",enterprise).getResultList();
        documentList.sort(Comparator.comparing(o -> ((Document)o).getDate()).reversed());

        return documentList;
    }

    @Override
    public List<Document> getAllByEnterprise(Enterprise enterprise) {
        List<Document> documentList = entityManager.createQuery("select d from Document d where d.enterprise=:Enterprise")
                .setParameter("Enterprise",enterprise).getResultList();
        documentList.sort(Comparator.comparing(o -> ((Document)o).getDate()).reversed());
        return documentList;
    }

    @Override
    public int getAllCount() {
        return ((Long)entityManager.createQuery("SELECT count(document) from Document as document").getSingleResult()).intValue();
    }

    @Override
    public int getNotProcesseCount() {
        return ((Long)entityManager.createQuery("SELECT count(document) from Document as document where document.processed=false").getSingleResult()).intValue();
    }
}
