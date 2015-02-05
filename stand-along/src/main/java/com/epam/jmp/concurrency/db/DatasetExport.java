package com.epam.jmp.concurrency.db;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;

import com.epam.jmp.util.Config;

public class DatasetExport {

	public static void main(String[] args) throws Exception {
		Class.forName(Config.INST.prop(Config.DB_DRIVER));
		String dbUrl = Config.INST.prop(Config.DB_URL);
		String dbUser = Config.INST.prop(Config.DB_USER);
		String dbPassword = Config.INST.prop(Config.DB_PASSWORD);
		Connection jdbcConnection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
		IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);

		QueryDataSet partialDataSet = new QueryDataSet(connection);
		partialDataSet.addTable("NEWS", "SELECT TOP 1 * FROM NEWS");
		FlatXmlDataSet.write(partialDataSet, new FileOutputStream("news.xml"));
		// FlatDtdDataSet.write(connection.createDataSet(), new FileOutputStream("test.dtd"));
	}
}
