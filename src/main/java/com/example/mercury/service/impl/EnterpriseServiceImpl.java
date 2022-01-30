package com.example.mercury.service.impl;

import com.example.mercury.entitiy.Enterprise;
import com.example.mercury.entitiy.User;
import com.example.mercury.formDTO.EnterpriseDocumentNumer;
import com.example.mercury.repository.EnterpriseRepository;
import com.example.mercury.service.EnterpriseService;
import com.example.mercury.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EnterpriseServiceImpl implements EnterpriseService {
    private final EnterpriseRepository enterpriseRepository;
    private final UserService userService;


    @Autowired
    public EnterpriseServiceImpl(EnterpriseRepository enterpriseRepository, UserService userService) {
        this.enterpriseRepository = enterpriseRepository;
        this.userService = userService;
    }

    @Override
    public void addEnterprise(Enterprise enterprise, String uuid, String username) {
        enterprise.setUser(userService.getUserByUsername(username).get());
        enterprise.setUuid(UUID.fromString(uuid));
        if (enterprise.getVetInspection()==null) {
            enterprise.setVetInspection(false);
        }
        if (enterpriseRepository.getByUUID(UUID.fromString(uuid)) == null) {
            enterpriseRepository.add(enterprise);
        } else {
            enterpriseRepository.update(enterprise);
        }
    }

    @Override
    public List<Enterprise> getAllByUser(User user) {
        return enterpriseRepository.getAllByUser(user);
    }

    @Override
    public  Enterprise getByUUID(String uuid) {
        return enterpriseRepository.getByUUID(UUID.fromString(uuid));
    }

    @Override
    public List<EnterpriseDocumentNumer> getListNumerDocumentOfEnterpriseByUser(User user) {
        return enterpriseRepository.getListNumerDocumentOfEnterpriseByUser(user);
    }
}
