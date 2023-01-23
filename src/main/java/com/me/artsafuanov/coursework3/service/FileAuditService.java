package com.me.artsafuanov.coursework3.service;
import java.io.File;

public interface FileAuditService {
    boolean saveToAuditFile(String json);

    String readFromAuditFile();

    boolean cleanAuditFile();

    File getAuditFile();
}
