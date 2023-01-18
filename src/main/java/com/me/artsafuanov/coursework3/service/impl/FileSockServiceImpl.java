package com.me.artsafuanov.coursework3.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.me.artsafuanov.coursework3.dto.SocksRequest;
import com.me.artsafuanov.coursework3.service.FileSockService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileSockServiceImpl implements FileSockService {
    @Value("${path.to.socks.file}")
    private String socksFilePath;

    @Value("${name.of.socks.file}")
    private String socksFileName;

    @Override
    public boolean saveToFile(String json) {
        try {
            cleanSocksFile();
            Files.writeString(Path.of(socksFilePath, socksFileName), json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }
    @Override
    public String readFromFile() {
        try {
            if (Files.exists(Path.of(socksFilePath, socksFileName))) {
                return Files.readString(Path.of(socksFilePath, socksFileName));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } return "Файла нет";
    }

    @Override
    public boolean cleanSocksFile() {
        try {
            Path path = Path.of(socksFilePath, socksFileName);
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public File getSocksFile () {
        return new File(socksFilePath + "/" + socksFileName );
    }
}
