package com.me.artsafuanov.coursework3.service;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public interface FileSockService {
    boolean saveToFile(String json);

    String readFromFile();

    boolean cleanSocksFile();

    File getSocksFile();
}
