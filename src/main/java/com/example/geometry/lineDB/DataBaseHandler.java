package com.example.geometry.lineDB;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Component;

@Component
public class DataBaseHandler {
    private static final String USER = "postgres";
    private static final String PASS = "34132";
    private static final String CONNECTION_STR = "jdbc:postgresql://localhost:5432/geometry";
    public static final DSLContext DSL_CONTEXT = DSL.using(CONNECTION_STR,USER,PASS);
    private DataBaseHandler(){}
}
