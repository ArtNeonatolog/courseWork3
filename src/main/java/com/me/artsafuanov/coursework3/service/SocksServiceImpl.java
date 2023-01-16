package com.me.artsafuanov.coursework3.service;
import com.me.artsafuanov.coursework3.dto.SocksRequest;
import com.me.artsafuanov.coursework3.exception.InsufficientSocksQuantityException;
import com.me.artsafuanov.coursework3.exception.InvalidSocksException;
import com.me.artsafuanov.coursework3.model.Color;
import com.me.artsafuanov.coursework3.model.Size;
import com.me.artsafuanov.coursework3.model.Socks;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class SocksServiceImpl implements SocksService {

    private final Map<Socks, Integer> mapOfSocks = new HashMap<>();

    @Override
    public void addSocks(SocksRequest socksRequest) {
        validateRequest(socksRequest);
        Socks socks = toSock(socksRequest);
        if (mapOfSocks.containsKey(socks)) {
            mapOfSocks.put(socks, mapOfSocks.get(socks) + socksRequest.getQuantity());
        }else {
            mapOfSocks.put(socks, socksRequest.getQuantity());
        }
    }
    @Override
    public void outputSocks(SocksRequest socksRequest) {
        decreaseSocks(socksRequest);
    }

    @Override
    public void removeDefectSocks(SocksRequest socksRequest) {
        decreaseSocks(socksRequest);
    }
    private void decreaseSocks (SocksRequest socksRequest) {
        validateRequest(socksRequest);
        Socks socks = toSock(socksRequest);
        int socksQuantity = mapOfSocks.getOrDefault(socks, 0);
        if (socksQuantity >= socksRequest.getQuantity()) {
            mapOfSocks.put(socks, socksQuantity - socksRequest.getQuantity());
        }else {
            throw new InsufficientSocksQuantityException("На складе не осталось носков!");
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
    }


