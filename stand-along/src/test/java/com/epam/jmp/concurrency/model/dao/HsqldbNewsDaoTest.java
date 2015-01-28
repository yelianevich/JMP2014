package com.epam.jmp.concurrency.model.dao;

import static com.epam.jmp.util.Config.DB_DRIVER;
import static com.epam.jmp.util.Config.DB_PASSWORD;
import static com.epam.jmp.util.Config.DB_URL;
import static com.epam.jmp.util.Config.DB_USER;
import static org.dbunit.PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL;
import static org.dbunit.PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS;
import static org.dbunit.PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD;
import static org.dbunit.PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME;

import java.io.FileInputStream;

import org.dbunit.DBTestCase;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Test;

import com.epam.jmp.util.Config;

public class HsqldbNewsDaoTest extends DBTestCase {

	public HsqldbNewsDaoTest(String name) {
		super(name);
		System.setProperty(DBUNIT_DRIVER_CLASS, Config.INST.prop(DB_DRIVER));
		System.setProperty(DBUNIT_CONNECTION_URL, Config.INST.prop(DB_URL));
		System.setProperty(DBUNIT_USERNAME, Config.INST.prop(DB_USER));
		System.setProperty(DBUNIT_PASSWORD, Config.INST.prop(DB_PASSWORD));
	}

	@Test
	public void test() {
		
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(new FileInputStream("dbunit/dataset/news.xml"));
	}
}
