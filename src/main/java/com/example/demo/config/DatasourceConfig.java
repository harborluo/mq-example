package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Created by harbor on 5/9/2019.
 */
@Configuration
public class DatasourceConfig {

    @Bean(name = "primaryDB")
    @Primary
    @ConfigurationProperties(prefix="spring.datasource.primary")
    public DataSource primaryDataSource() {
        DataSource ds =  DataSourceBuilder.create().build();

        return ds;
    }

    @Bean(name = "primaryJdbcTemplate")
    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(primaryDataSource());
    }

}
