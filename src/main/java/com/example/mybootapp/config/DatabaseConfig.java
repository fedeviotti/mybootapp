package com.example.mybootapp.config;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {

    public static Connection getConnection() throws URISyntaxException, SQLException {
        //String dbUrl = System.getenv("JDBC_DATABASE_URL");
        String dbUrl = "jdbc:postgresql://ec2-174-129-25-182.compute-1.amazonaws.com:5432/d7876ejjse1h2m?user=bgsjugnfclvvsc&password=8c637b8ec3223647b7e5bf49535e7e4874653b4963b8a5637cfdbb2925f7f746&sslmode=require";
        return DriverManager.getConnection(dbUrl);
    }

}