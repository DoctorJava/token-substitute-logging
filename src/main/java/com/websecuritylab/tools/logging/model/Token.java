package com.websecuritylab.tools.logging.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import com.websecuritylab.tools.logging.TokenManager.TOKEN_TYPE;

public class Token {
	public static final String SQL_CREATE_TABLE = "CREATE TABLE token ( type TEXT, actual TEXT, token TEXT )";

	public static final String SQL_INSERT_FIELDS = " ( type, actual, token ) ";
	public static final String SQL_INSERT_VALUES = " VALUES (?,?,?) ";
	public static final String SQL_UPDATE_FIELDS = " type=?, actual=?, token=? ";
	
	//private int _id;
	private TOKEN_TYPE _type;
	private String _actual;	
	private String _token;	

	//public Token() {} // This is required for jersey-media-json-jackson binding

	public Token(TOKEN_TYPE type, String actual, String token) {
		super();
		_type = type;
		_actual = actual;
		_token = token;
	}

	public Token(ResultSet rs) throws SQLException {
		_actual = rs.getString("actual");
		_token = rs.getString("token");
	}


	//
	// Generated Getters/Setters
	//
	
	public TOKEN_TYPE getType() {
		return _type;
	}
	public void setType(TOKEN_TYPE type) {
		_type = type;
	}	
	public String getActual() {
		return _actual;
	}
	public void setActual(String actual) {
		_actual = actual;
	}
	public String getToken() {
		return _token;
	}
	public void setToken(String token) {
		_token = token;
	}
}
