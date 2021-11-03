package com.example.geometry.dao;

import java.io.IOException;

public interface BackupDao {

    String getLines();

    int restore(String data) throws IOException;
}
