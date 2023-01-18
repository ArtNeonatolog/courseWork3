package com.me.artsafuanov.coursework3.controller;

import com.me.artsafuanov.coursework3.service.FileSockService;
import io.swagger.v3.core.util.Json;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequestMapping("/files/socks")
@Tag(name = "Товар (носки) - работа с файлами", description = "Можно загружать, скачивать данные о товаре на складе")
public class FileSocksController {
    private final FileSockService fileSockService;

    public FileSocksController(FileSockService fileSockService) {
        this.fileSockService = fileSockService;
    }

    @GetMapping("/export")
    @Operation(summary = "Скачивание файла c информацией о товаре (носки)",
            description = "Данный метод предназначен для скачивания файла c информацией о товаре на складе"
    )
    public ResponseEntity<InputStreamResource> downloadRecipeFile() throws FileNotFoundException {
        File file = fileSockService.getSocksFile();
        if (file.exists()) {
            InputStreamResource inputStreamResource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM).contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"SocksFileLog.json\"")
                    .body(inputStreamResource);
        } return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Загрузка файла c информацией о товаре (носки)",
            description = "Данный метод предназначен для загрузки файла c информацией о товаре на складе"
    )
    public ResponseEntity<Void> uploadSocksFile(@RequestParam MultipartFile file) {
        fileSockService.cleanSocksFile();
        File dataFile = fileSockService.getSocksFile();
        try (FileOutputStream fos = new FileOutputStream(dataFile)) {
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        } return ResponseEntity.status(500).build();
    }
}


