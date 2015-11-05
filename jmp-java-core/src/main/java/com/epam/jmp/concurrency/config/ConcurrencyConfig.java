package com.epam.jmp.concurrency.config;

import static com.epam.jmp.util.Config.DB_PASSWORD;
import static com.epam.jmp.util.Config.DB_URL;
import static com.epam.jmp.util.Config.DB_USER;
import static com.epam.jmp.util.Config.INPUT_FOLDER;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import com.epam.jmp.concurrency.files.FilesReader;
import com.epam.jmp.concurrency.files.FolderReader;
import com.epam.jmp.util.Config;

@Configuration
@ComponentScan("com.epam.jmp.concurrency")
public class ConcurrencyConfig {

	@Bean
	public JdbcTemplate jdbcTemplate() {
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		dataSource.setDriverClass(org.hsqldb.jdbcDriver.class);
		dataSource.setUsername(Config.INST.prop(DB_USER));
		dataSource.setUrl(Config.INST.prop(DB_URL));
		dataSource.setPassword(Config.INST.prop(DB_PASSWORD));
		return new JdbcTemplate(dataSource);
	}

	@Bean
	public FilesReader filesReader() {
		String dir = Config.INST.prop(INPUT_FOLDER);
		return new FolderReader(dir); 
	}

}
