package com.epam.jmp.concurrency.db;

import static com.epam.jmp.util.Config.DB_PASSWORD;
import static com.epam.jmp.util.Config.DB_URL;
import static com.epam.jmp.util.Config.DB_USER;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import com.epam.jmp.util.Config;

public enum Jdbc {
	INST;

	private JdbcTemplate jdbcTemplate;

	Jdbc() {
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		dataSource.setDriverClass(org.hsqldb.jdbcDriver.class);
		dataSource.setUsername(Config.INST.prop(DB_USER));
		dataSource.setUrl(Config.INST.prop(DB_URL));
		dataSource.setPassword(Config.INST.prop(DB_PASSWORD));
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public JdbcTemplate getTemplate() {
		return jdbcTemplate;
	}
}
