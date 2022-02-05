package com.example.mercury.service;

import com.example.mercury.entitiy.Enterprise;
import com.example.mercury.entitiy.User;
import com.example.mercury.formDTO.EnterpriseDocumentNumer;

import java.util.List;

public interface EnterpriseService {
    void addEnterprise(Enterprise enterprise, String uuid, String username);

    List<Enterprise> getAll();
    List<Enterprise> getAllByUser(User user);
    Enterprise getByUUID(String uuid);

    List<EnterpriseDocumentNumer> getListNumerDocumentOfEnterpriseByUser(User user);
}
