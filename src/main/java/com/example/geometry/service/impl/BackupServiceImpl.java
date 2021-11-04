package com.example.geometry.service.impl;

import com.example.geometry.aws.S3Backup;
import com.example.geometry.dao.BackupDao;
import com.example.geometry.lineDB.converter.CsvToLineConverter;
import com.example.geometry.service.BackupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BackupServiceImpl implements BackupService {

    private final BackupDao backupDao;
    private final CsvToLineConverter csvToLineConverter;

    private static final String PATH = "src/main/resources/backup/db_backup.csv";

    @Override
    public Optional<String> makeCSVFile() {
        try {
            File backupFile = new File(PATH);

            BufferedWriter writer = new BufferedWriter(new FileWriter(backupFile));
            String data = backupDao.getLines();
            writer.write(data);
            writer.close();
            S3Backup.makeBackup(backupFile.getPath());
            return Optional.of(data);
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int restore() {
        return S3Backup.restore().map(s ->
                backupDao.restore(csvToLineConverter.convert(s))
        ).orElse(0);
    }
}
