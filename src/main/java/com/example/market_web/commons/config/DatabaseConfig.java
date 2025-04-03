package com.example.market_web.commons.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.logging.Level;

@Configuration
public class DatabaseConfig {

    private static final java.util.logging.Logger log = java.util.logging.Logger.getLogger(String.valueOf(DatabaseConfig.class));

    public static final String PREFIX_JDBC_URL = "jdbc:postgresql://";

    @Value("${app.market-web.database.host}")
    private String host;
    @Value("${app.market-web.database.port}")
    private String port;
    @Value("${app.market-web.database.name}")
    private String name;
    @Value("${app.market-web.database.schema}")
    private String schema;
    @Value("${app.market-web.database.username}")
    private String username;
    @Value("${app.market-web.database.password}")
    private String password;
    @Value("${app.market-web.database.driver}")
    private String driver;

    @Bean
    public DataSource marketWebDataSource() {
        String jdbcUrl = PREFIX_JDBC_URL + host + ":" + port + "/" + name +"?currentSchema=" + schema;
        log.log(Level.INFO, "Database connection for: {0}", new Object[]{jdbcUrl});
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(jdbcUrl);
        config.setUsername(username);
        config.setPassword(password);
        return new HikariDataSource(config);
    }
}
