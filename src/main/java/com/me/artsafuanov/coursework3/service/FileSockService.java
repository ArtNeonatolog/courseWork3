package com.me.artsafuanov.coursework3.service;

import java.io.File;

public interface FileSockService {
    boolean saveToFile(String json);

    String readFromFile();

    boolean cleanSocksFile();

    File getSocksFile();
}
