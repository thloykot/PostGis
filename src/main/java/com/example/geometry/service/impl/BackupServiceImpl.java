package com.example.geometry.service.impl;

import com.example.geometry.dao.BackupDao;
import com.example.geometry.dto.LineDto;
import com.example.geometry.model.Line;
import com.example.geometry.model.customObject.CsvToLine;
import com.example.geometry.service.BackupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BackupServiceImpl implements BackupService {

    private final BackupDao backupDao;
    private final CsvToLine csvToLine;

    @Override
    public String makeCSVFile() throws IOException {
        File backupFile = new File("src/main/resources/backup/db_backup.csv");

        BufferedWriter writer = new BufferedWriter(new FileWriter(backupFile));
        writer.write(backupDao.getLines());
        writer.close();
        return backupFile.getPath();
    }

    @Override
    public int restore(String data) throws IOException {

        csvToLine.read(data);
        List<LineDto> lineList = csvToLine.read(data);
        return 1; //backupDao.restore(data);
    }
}
