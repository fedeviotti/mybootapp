package com.example.mybootapp.rest;

import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.atomic.AtomicLong;

import com.example.mybootapp.config.DatabaseConfig;
import com.example.mybootapp.model.Greeting;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/api/public/greeting")
    public Greeting publicGreeting(@RequestParam(value="name", defaultValue="World") String name) throws SQLException, URISyntaxException {
        
        Statement stmt = DatabaseConfig.getConnection().createStatement();
        stmt.executeUpdate("DROP TABLE IF EXISTS ticks");
        stmt.executeUpdate("CREATE TABLE ticks (tick timestamp)");
        stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
        ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");
        while (rs.next()) {
            System.out.println("Read from DB: " + rs.getTimestamp("tick"));
        }

        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
    
    @RequestMapping("/api/private/greeting")
    public Greeting privateGreeting(@RequestParam(value="name", defaultValue="World") String name) throws SQLException, URISyntaxException {
        
        Statement stmt = DatabaseConfig.getConnection().createStatement();
        stmt.executeUpdate("DROP TABLE IF EXISTS ticks");
        stmt.executeUpdate("CREATE TABLE ticks (tick timestamp)");
        stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
        ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");
        while (rs.next()) {
            System.out.println("Read from DB: " + rs.getTimestamp("tick"));
        }

        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
    
    @RequestMapping("/api/public")
    public String pubblico() {
        return "pubblico";
    }
    
    @RequestMapping("/api/private")
    public String privato() {
        return "privato";
    }
    
    @RequestMapping("/api/private-scoped")
    public String privatoScoped() {
        return "privato-scoped";
    }
}