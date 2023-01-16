package com.me.artsafuanov.coursework3.service;
import com.me.artsafuanov.coursework3.dto.SocksRequest;
import com.me.artsafuanov.coursework3.model.Color;
import com.me.artsafuanov.coursework3.model.Size;
import org.springframework.stereotype.Service;

@Service
public interface SocksService {

    void addSocks (SocksRequest socksRequest);

    void outputSocks(SocksRequest socksRequest);

    void removeDefectSocks(SocksRequest socksRequest);

    int getSocksQuantity (Color color, Size size, Integer cottonMin, Integer cottonMax);


}
