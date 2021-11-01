package com.example.geometry.service.impl;

import com.example.geometry.dao.BackupDao;
import com.example.geometry.service.BackupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class BackupServiceImpl implements BackupService {

    private final BackupDao backupDao;

    @Override
    public String makeCSVFile() throws IOException {
        File backupFile = new File("src/main/resources/backup/db_backup.csv");
        if (!backupFile.exists()) {
            backupFile.createNewFile();
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(backupFile));
        writer.write(backupDao.getLines());
        writer.close();
        return backupFile.getPath();
    }
}
