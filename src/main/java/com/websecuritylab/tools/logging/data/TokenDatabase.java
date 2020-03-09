package com.websecuritylab.tools.logging.data;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sqlite.SQLiteConfig;
import org.sqlite.SQLiteDataSource;

import com.websecuritylab.tools.logging.exception.DatabaseExistsException;
import com.websecuritylab.tools.logging.model.Token;

//import com.mchange.v2.c3p0.ComboPooledDataSource;

public final class TokenDatabase {
	private static final Logger logger = LoggerFactory.getLogger( TokenDatabase.class );  

				// TODO: Get this from properties file
	private static final String _dbId = "aaa";
	private static final String _dbDir = "/opt/apps/data/";
	public static final String DB_PATH = _dbDir + "tokenMap_"+_dbId+".sqlite";
	
	private TokenDatabase() {}		// Ensure this singleton database Helper is not instantiated.  
	
	// Don't use DriveManager, use DataSource. See: https://stackoverflow.com/questions/41230234/using-datasource-to-connect-to-sqlite-with-xerial-sqlite-jdbc-driver
	private static SQLiteDataSource dataSource;
	static {
		SQLiteConfig  config = new SQLiteConfig();
		//config.enforceForeignKeys(true);
		dataSource = new SQLiteDataSource();
		dataSource.setUrl("jdbc:sqlite:" + DB_PATH);
		dataSource.setConfig(config);
	}

    public static Connection getConnection() {
        try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
    }
    
	public static void createDatabase() throws DatabaseExistsException {
		try (	
				Connection conn = getConnection();
				Statement statement = conn.createStatement();		) {   
			statement.execute(Token.SQL_CREATE_TABLE);
		} catch (SQLException e1) {
			throw new DatabaseExistsException();
		}
	}
}
