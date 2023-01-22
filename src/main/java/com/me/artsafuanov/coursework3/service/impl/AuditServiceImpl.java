package com.me.artsafuanov.coursework3.service.impl;
import com.me.artsafuanov.coursework3.model.Operation;
import com.me.artsafuanov.coursework3.model.Socks;
import com.me.artsafuanov.coursework3.model.TypeOfOperation;
import com.me.artsafuanov.coursework3.service.AuditService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuditServiceImpl implements AuditService {
    private final List<Operation> operations = new ArrayList<>();

    @Override
    public void recordAddOperation(Socks socks, Integer quantity) {
        recordOperation(TypeOfOperation.ADD, socks, quantity);
    }

    @Override
    public void recordOutputOperation(Socks socks, Integer quantity) {
        recordOperation(TypeOfOperation.OUTPUT, socks, quantity);
    }

    @Override
    public void recordRemoveDefectedOperation(Socks socks, Integer quantity) {
        recordOperation(TypeOfOperation.REMOVE_DEFECTED, socks, quantity);
    }

    private void recordOperation(TypeOfOperation type, Socks socks, Integer quantity) {
        this.operations.add(new Operation(type, LocalDateTime.now(), quantity, socks));
    }
}
