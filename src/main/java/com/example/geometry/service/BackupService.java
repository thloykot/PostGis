package com.example.geometry.service;

import java.io.IOException;

public interface BackupService {

    String makeCSVFile() throws IOException;

    int restore(String data) throws IOException;
}
