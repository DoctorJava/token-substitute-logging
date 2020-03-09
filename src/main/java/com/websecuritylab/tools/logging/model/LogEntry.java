package com.websecuritylab.tools.logging.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.slf4j.MDC;

import com.websecuritylab.tools.logging.TokenManager;
import com.websecuritylab.tools.logging.TokenManager.TOKEN_TYPE;

public class LogEntry {

	private String _user_t;				// _t for token valuE
	private String _hostip_t;
	private String _action;
	private String _result;
	
																					// _a for actual value	
	public LogEntry(String user_a, String host_a, String action, String result) {

		_user_t = TokenManager.getToken(TOKEN_TYPE.USER, user_a);
		_hostip_t = TokenManager.getToken(TOKEN_TYPE.HOSTIP, host_a);
		_action = action;
		_result = result;
	}

	@Override
	public String toString() {
//		String singleQuotedYAML = "{ user_t: '" + _user_t + "',"
//								+ " hostip_t: '" + _hostip_t + "',"
//								+ " action: '" + _action + "',"
//								+ " result: '" + _result + "'}";
		String unQuotedYAML = "{ user_t: " + _user_t + ","
							+ " hostip_t: " + _hostip_t + ","
							+ " action: " + _action + ","
							+ " result: " + _result + "}";		
		return unQuotedYAML;
	}

	//public LogEntry() {} // This is required for jersey-media-json-jackson binding



	//
	// Generated Getters/Setters
	//

}
