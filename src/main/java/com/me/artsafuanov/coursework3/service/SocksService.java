package com.me.artsafuanov.coursework3.service;
import com.me.artsafuanov.coursework3.dto.SocksRequest;
import com.me.artsafuanov.coursework3.model.Color;
import com.me.artsafuanov.coursework3.model.Size;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.InputStream;

@Service
public interface SocksService {
    void addSocks (SocksRequest socksRequest);

    void outputSocks(SocksRequest socksRequest);

    void removeDefectSocks(SocksRequest socksRequest);

    int getSocksQuantity (Color color, Size size, Integer cottonMin, Integer cottonMax);

    void addSocksFromInputStream(InputStream inputStream) throws IOException;
}
