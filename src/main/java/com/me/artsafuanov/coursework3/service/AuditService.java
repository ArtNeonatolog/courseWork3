package com.me.artsafuanov.coursework3.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.me.artsafuanov.coursework3.model.Socks;
import com.me.artsafuanov.coursework3.model.TypeOfOperation;
import org.springframework.stereotype.Service;

@Service
public interface AuditService {
    void recordAddOperation(Socks socks, Integer quantity);

    void recordOutputOperation(Socks socks, Integer quantity);

    void recordRemoveDefectedOperation(Socks socks, Integer quantity);

}
