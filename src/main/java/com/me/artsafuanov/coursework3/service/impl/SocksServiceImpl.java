package com.me.artsafuanov.coursework3.service.impl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.me.artsafuanov.coursework3.dto.SocksRequest;
import com.me.artsafuanov.coursework3.exception.InsufficientSocksQuantityException;
import com.me.artsafuanov.coursework3.exception.InvalidSocksException;
import com.me.artsafuanov.coursework3.model.Color;
import com.me.artsafuanov.coursework3.model.Size;
import com.me.artsafuanov.coursework3.model.Socks;
import com.me.artsafuanov.coursework3.service.SocksService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SocksServiceImpl implements SocksService {
    private final Map<Socks, Integer> mapOfSocks = new HashMap<>();
    private final FileSockServiceImpl fileSockService;
    private final AuditServiceImpl auditService;
    public SocksServiceImpl(FileSockServiceImpl fileSockService, AuditServiceImpl auditService) {
        this.fileSockService = fileSockService;
        this.auditService = auditService;
    }

    @PostConstruct
    private void init() {
        try {
            fileSockService.readFromFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void addSocks(SocksRequest socksRequest) {
        validateRequest(socksRequest);
        Socks socks = toSock(socksRequest);
        if (mapOfSocks.containsKey(socks)) {
            mapOfSocks.put(socks, mapOfSocks.get(socks) + socksRequest.getQuantity());
        }else {
            mapOfSocks.put(socks, socksRequest.getQuantity());
            saveToFile();
            auditService.recordAddOperation(socks, socksRequest.getQuantity());
        }
    }
    @Override
    public void outputSocks(SocksRequest socksRequest) {
        decreaseSocks(socksRequest, true);
    }

    @Override
    public void removeDefectSocks(SocksRequest socksRequest) {

        decreaseSocks(socksRequest, false);
    }
    private void decreaseSocks (SocksRequest socksRequest, boolean isOutput) {
        validateRequest(socksRequest);
        Socks socks = toSock(socksRequest);
        int socksQuantity = mapOfSocks.getOrDefault(socks, 0);
        if (socksQuantity >= socksRequest.getQuantity()) {
            mapOfSocks.put(socks, socksQuantity - socksRequest.getQuantity());
        }else {
            throw new InsufficientSocksQuantityException("На складе не осталось носков!");
        } if (isOutput) {
            auditService.recordOutputOperation(socks, socksRequest.getQuantity());
        } else {
            auditService.recordRemoveDefectedOperation(socks, socksRequest.getQuantity());
        }
    }
    @Override
    public int getSocksQuantity(Color color, Size size, Integer cottonMin, Integer cottonMax) {
        Integer total = 0;
            for (Map.Entry<Socks, Integer> entry : mapOfSocks.entrySet()) {
                if (color != null && !entry.getKey().getColor().equals(color)) {
                    continue;
                }
                if (size != null && !entry.getKey().getSize().equals(size)) {
                    continue;
                }
                if (cottonMin != null && entry.getKey().getCottonPart() < cottonMin) {
                    continue;
                }
                if (cottonMax != null && entry.getKey().getCottonPart() > cottonMax) {
                    continue;
                }
                total += entry.getValue();
        }
        return total;
    }

    private void validateRequest (SocksRequest socksRequest) {
        if (socksRequest.getColor() == null || socksRequest.getSize() == null) {
            throw new InvalidSocksException("Все поля должны быть заполнены!");
        }
        if (socksRequest.getQuantity() <= 0) {
            throw new InvalidSocksException("Количество носков должно быть больше 0!");
        }
        if (socksRequest.getCottonPart() < 0 || socksRequest.getCottonPart() > 100) {
            throw new InvalidSocksException("Процентное содержание хлопка в носках должно быть от 0 до 100%!");
        }
    }
        private Socks toSock (SocksRequest socksRequest) {
        return new Socks(socksRequest.getColor(), socksRequest.getSize(), socksRequest.getCottonPart());
        }
    private void saveToFile() {
        try {
            List<SocksRequest> socksList = new ArrayList<>();
            for(Map.Entry<Socks, Integer> entry:this.mapOfSocks.entrySet()){
                socksList.add(mapToDto(entry.getKey(), entry.getValue()));
            }
            String json = new ObjectMapper().writeValueAsString(socksList);
            fileSockService.saveToFile(json);

        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
    }
    private SocksRequest mapToDto (Socks socks, Integer quantity){
        SocksRequest socksRequest = new SocksRequest(socks.getColor(), socks.getSize(), socks.getCottonPart(), quantity);
        return socksRequest;
    }
    @Override
    public void addSocksFromInputStream(InputStream inputStream) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] array = StringUtils.split(line, ',');
                SocksRequest socksRequest = new SocksRequest(Color.valueOf(array[0]), Size.valueOf(array[1]), Integer.valueOf(array[2]), Integer.valueOf(array[3]));
                addSocks(socksRequest);
            }
        }
    }
}


