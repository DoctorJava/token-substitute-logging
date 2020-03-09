package com.websecuritylab.tools.logging.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.websecuritylab.tools.logging.TokenManager;
import com.websecuritylab.tools.logging.TokenManager.TOKEN_TYPE;

public class ActivityLog {
	private static final Logger logger = LoggerFactory.getLogger( ActivityLog.class ); 
	public static enum MDC_TYPE{ USER, HOSTIP, ACTION, RESULT }


	public ActivityLog() {}
	
	public static void write(String user, String host, String action, String result) {
	    MDC.put("user_t", TokenManager.getToken(TOKEN_TYPE.USER, user));
	    MDC.put("hostip_t", TokenManager.getToken(TOKEN_TYPE.HOSTIP, host));
	    MDC.put("action", action);
	    MDC.put("result", result);

	    logger.info("");
	}

}
