package com.me.artsafuanov.coursework3.controller;
import com.me.artsafuanov.coursework3.service.FileAuditService;
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
@RequestMapping("/audit")
@Tag(name = "Аудит товара (носки)", description = "Можно загружать, скачивать данные об аудите товара на складе")
public class FileAuditOfSocksController {
    private final FileAuditService fileAuditService;

    public FileAuditOfSocksController(FileAuditService fileAuditService) {
        this.fileAuditService = fileAuditService;
    }

    @GetMapping("/export")
    @Operation(summary = "Скачивание файла c информацией об аудите товара (носков)",
            description = "Данный метод предназначен для скачивания файла c информацией об аудите товара на складе"
    )
    public ResponseEntity<InputStreamResource> downloadAuditOfSocksFile() throws FileNotFoundException {
        File file = fileAuditService.getAuditFile();
        if (file.exists()) {
            InputStreamResource inputStreamResource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM).contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"AuditFileLog.json\"")
                    .body(inputStreamResource);
        } return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Загрузка файла c информацией об аудите товара (носков)",
            description = "Данный метод предназначен для загрузки файла c информацией об аудите товара на складе"
    )
    public ResponseEntity<Void> uploadAuditOfSocksFile(@RequestParam MultipartFile file) {
        fileAuditService.cleanAuditFile();
        File dataFile = fileAuditService.getAuditFile();
        try (FileOutputStream fos = new FileOutputStream(dataFile)) {
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        } return ResponseEntity.status(500).build();
    }
}
