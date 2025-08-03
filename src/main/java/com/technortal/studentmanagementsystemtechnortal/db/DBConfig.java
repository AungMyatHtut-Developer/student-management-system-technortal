package com.technortal.studentmanagementsystemtechnortal.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;
import org.springframework.jdbc.core.JdbcTemplate;

@Getter
public class DBConfig {

    private static JdbcTemplate jdbcTemplate;

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/technortal_db");
        config.setUsername("root");
        config.setPassword("root");

        HikariDataSource dataSource = new HikariDataSource(config);
        jdbcTemplate = new JdbcTemplate(dataSource);
        System.out.println("Connected to database");
    }

    public static JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

}
