package com.websecuritylab.tools.logging;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;

import com.websecuritylab.tools.logging.data.TokenDAO;
import com.websecuritylab.tools.logging.exception.DatabaseException;
import com.websecuritylab.tools.logging.model.Token;

public class TokenManager {
	private static final int TOKEN_SIZE = 5;		// If lower case alpha: 5 -> 11 Million unique values, 6 -> 300, 7 -> 8 Billion
	
	public static enum TOKEN_TYPE{ USER, HOSTIP }
	private static Map<String, String> tokenMap = new HashMap<>();  // Maps Actual(key) to Token(value)
	private static final String T = "|";							// This is another "token" for separating TYPE|VAUE

	private TokenManager() {}						// Ensure this singleton database Helper is not instantiated.  

	public static String getToken(TOKEN_TYPE type, String actual) {
	
		String key = type+T+actual;
		if ( !tokenMap.containsKey(key) ) {
			String newToken = RandomStringUtils.randomAlphabetic(TOKEN_SIZE).toLowerCase();
			TokenDAO tdao = new TokenDAO();
			try {
				tdao.insertToken(new Token(type, actual, newToken));
			} catch (DatabaseException e) {
				e.printStackTrace();
			}
			
			tokenMap.put(key, newToken );
			
		}
		
		System.out.println("Got new token: " + tokenMap.get(key));
		
		return tokenMap.get(key);
	}

}
