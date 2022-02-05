package com.example.mercury.repository;

import com.example.mercury.entitiy.Enterprise;
import com.example.mercury.entitiy.User;
import com.example.mercury.formDTO.EnterpriseDocumentNumer;

import java.util.List;
import java.util.UUID;

public interface EnterpriseRepository {
    void add(Enterprise enterprise);
    void update(Enterprise enterprise);

    Enterprise getByUUID(UUID uuid);
    List<Enterprise> getAll();
    List<Enterprise> getAllByUser(User user);

    List<EnterpriseDocumentNumer> getListNumerDocumentOfEnterpriseByUser(User user);
    int getCount();
}
