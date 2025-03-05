package com.sana.database;

import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@Log
public class DatabaseApplication implements CommandLineRunner {
    private final DataSource dataSource;

    public DatabaseApplication(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public static void main(String[] args) {
        SpringApplication.run(DatabaseApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("DataSource: "+dataSource.toString());
        final JdbcTemplate restTemplate = new JdbcTemplate(dataSource);
//        restTemplate.execute("Select 1");

//        List<Map<String, Object>> mylist =  restTemplate.queryForList("Select * from users");
//        for (Object o : mylist) {
//            System.out.println(o);
//        }

        List<Map<String, Object>> mylist =  restTemplate.queryForList("Select * from widgets");
        for (Object o : mylist) {
            System.out.println(o);
        }
    }

}
