package com.me.artsafuanov.coursework3.service.impl;
import com.me.artsafuanov.coursework3.service.FileAuditService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileAuditServiceImpl implements FileAuditService {
        @Value("${path.to.auditOfSocks.file}")
        private String auditFilePath;

        @Value("${name.of.auditOfSocks.file}")
        private String auditFileName;

        @Override
        public boolean saveToAuditFile(String json) {
            try {
                cleanAuditFile();
                Files.writeString(Path.of(auditFilePath, auditFileName), json);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        @Override
        public String readFromAuditFile() {
            try {
                if (Files.exists(Path.of(auditFilePath, auditFileName))) {
                    return Files.readString(Path.of(auditFilePath, auditFileName));
                }
            } catch (IOException e) {
                e.printStackTrace();
            } return "Файла нет";
        }
        @Override
        public boolean cleanAuditFile() {
            try {
                Path path = Path.of(auditFilePath, auditFileName);
                Files.deleteIfExists(path);
                Files.createFile(path);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        @Override
        public File getAuditFile () {
            return new File(auditFilePath + "/" + auditFileName);
        }
}
