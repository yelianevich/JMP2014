package com.epam.jmp.concurrency.config;

import static com.epam.jmp.util.Constants.DB_PASSWORD;
import static com.epam.jmp.util.Constants.DB_URL;
import static com.epam.jmp.util.Constants.DB_USER;
import static com.epam.jmp.util.Constants.INPUT_FOLDER;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import com.epam.jmp.concurrency.job.FilesReader;
import com.epam.jmp.concurrency.job.FolderReader;

@Configuration
@ComponentScan("com.epam.jmp.concurrency")
@PropertySource("classpath:jmp.properties")
public class ConcurrencyConfig {

    @Autowired
    private Environment env;

    @Bean
    public JdbcTemplate jdbcTemplate() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(org.hsqldb.jdbcDriver.class);
        dataSource.setUsername(env.getProperty(DB_USER));
        dataSource.setUrl(env.getProperty(DB_URL));
        dataSource.setPassword(env.getProperty(DB_PASSWORD));
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public FilesReader filesReader() {
        String dir = env.getProperty(INPUT_FOLDER);
        return new FolderReader(dir);
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
