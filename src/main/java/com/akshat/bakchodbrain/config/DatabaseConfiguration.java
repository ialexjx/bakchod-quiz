package com.akshat.bakchodbrain.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;

@Slf4j
@Configuration
public class DatabaseConfiguration {

    @Value("${SPRING_DATASOURCE_URL:${DATABASE_URL:}}")
    private String databaseUrl;

    @Value("${spring.datasource.driver-class-name:com.mysql.cj.jdbc.Driver}")
    private String driverClassName;

    @Value("${spring.datasource.username:root}")
    private String defaultUsername;

    @Value("${spring.datasource.password:}")
    private String defaultPassword;

    @Value("${spring.datasource.hikari.maximum-pool-size:10}")
    private int maxPoolSize;

    @Bean
    @Primary
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();

        // 1. Cloud Render PostgreSQL auto-detect
        if (databaseUrl != null && (databaseUrl.startsWith("postgres://") || databaseUrl.startsWith("postgresql://"))) {
            log.info("BakchodBrain: Render PostgreSQL DATABASE_URL detected. Converting to JDBC...");
            try {
                URI uri = new URI(databaseUrl);
                String userInfo = uri.getUserInfo();
                String username = defaultUsername;
                String password = defaultPassword;

                if (userInfo != null && userInfo.contains(":")) {
                    String[] parts = userInfo.split(":", 2);
                    username = parts[0];
                    password = parts[1];
                }

                int port = uri.getPort() == -1 ? 5432 : uri.getPort();
                String jdbcUrl = "jdbc:postgresql://" + uri.getHost() + ":" + port + uri.getPath();

                config.setJdbcUrl(jdbcUrl);
                config.setUsername(username);
                config.setPassword(password);
                config.setDriverClassName("org.postgresql.Driver");
                log.info("BakchodBrain: Successfully configured Render PostgreSQL: {}", jdbcUrl);
            } catch (Exception e) {
                log.error("Failed to parse Render DATABASE_URL: {}. Falling back to H2.", e.getMessage());
                applyH2Fallback(config);
            }
        } 
        // 2. MySQL connection check (falls back to H2 if local MySQL service is inactive)
        else {
            String targetUrl = (databaseUrl != null && !databaseUrl.isBlank()) 
                    ? databaseUrl 
                    : "jdbc:mysql://localhost:3306/bakchodbrain_db?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

            boolean isMysqlAvailable = checkMysqlConnectivity(targetUrl, defaultUsername, defaultPassword);

            if (isMysqlAvailable) {
                log.info("BakchodBrain: Local MySQL connection verified successfully at {}", targetUrl);
                config.setJdbcUrl(targetUrl);
                config.setDriverClassName("com.mysql.cj.jdbc.Driver");
                config.setUsername(defaultUsername);
                config.setPassword(defaultPassword);
            } else {
                log.warn("BakchodBrain: Local MySQL not reachable at {}. Gracefully falling back to in-memory H2 so app boots seamlessly!", targetUrl);
                applyH2Fallback(config);
            }
        }

        config.setMaximumPoolSize(maxPoolSize);
        config.setMinimumIdle(2);
        config.setIdleTimeout(30000);
        config.setMaxLifetime(1800000);
        config.setConnectionTimeout(20000);

        return new HikariDataSource(config);
    }

    private boolean checkMysqlConnectivity(String url, String user, String pass) {
        try (Connection conn = DriverManager.getConnection(url, user, pass)) {
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void applyH2Fallback(HikariConfig config) {
        config.setJdbcUrl("jdbc:h2:mem:bakchodbrain_db;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE;MODE=MySQL");
        config.setDriverClassName("org.h2.Driver");
        config.setUsername("sa");
        config.setPassword("");
    }
}
