package com.websecuritylab.tools.logging.demo;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.core.ConsoleAppender;

import com.websecuritylab.tools.logging.data.TokenDatabase;
import com.websecuritylab.tools.logging.exception.DatabaseExistsException;
import com.websecuritylab.tools.logging.model.ActivityLog;
import com.websecuritylab.tools.logging.model.LogEntry;

@WebServlet("/ActionDemo")
public class ActionServlet extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger( ActionServlet.class );  
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActionServlet() {
        super();
		try {
			//loadProperties();
			TokenDatabase.createDatabase();
			System.out.println("Creating database: "+TokenDatabase.DB_PATH);
		} catch (DatabaseExistsException e) {
			System.out.println("Using existing database: "+TokenDatabase.DB_PATH);
		}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    MDC.put("user_t", req.getParameter("user"));
	    MDC.put("hostip_t", req.getParameter("hostip"));

        String u = req.getParameter("user"); 
        String h = req.getParameter("hostip"); 
        String a = req.getParameter("action"); 
        String r = req.getParameter("result"); 
        		 
		logger.info(new LogEntry(u,h,a,r).toString());
		
		ActivityLog.write(u,h,a,r);
		
		RequestDispatcher rd = req.getRequestDispatcher("/index.jsp");
		rd.forward(req, res);

	}

}
