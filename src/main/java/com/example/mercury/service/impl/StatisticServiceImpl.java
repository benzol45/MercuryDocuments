package com.example.mercury.service.impl;

import com.example.mercury.formDTO.Statistic;
import com.example.mercury.repository.DocumentRepository;
import com.example.mercury.repository.EnterpriseRepository;
import com.example.mercury.repository.UserRepository;
import com.example.mercury.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticServiceImpl implements StatisticService {
    private final UserRepository userRepository;
    private final EnterpriseRepository enterpriseRepository;
    private final DocumentRepository documentRepository;

    @Autowired
    public StatisticServiceImpl(UserRepository userRepository, EnterpriseRepository enterpriseRepository, DocumentRepository documentRepository) {
        this.userRepository = userRepository;
        this.enterpriseRepository = enterpriseRepository;
        this.documentRepository = documentRepository;
    }


    @Override
    public Statistic getGeneralStatistic() {
        return new Statistic(userRepository.getCount(),
                enterpriseRepository.getCount(),
                documentRepository.getAllCount(),
                documentRepository.getNotProcesseCount());
    }
}
