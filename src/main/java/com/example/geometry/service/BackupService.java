package com.example.geometry.service;

import java.util.Optional;

public interface BackupService {

    Optional<String> makeCSVFile();

    int restore();
}
