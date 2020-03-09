package com.websecuritylab.tools.logging.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.websecuritylab.tools.logging.exception.DatabaseException;
import com.websecuritylab.tools.logging.model.Token;



public class TokenDAO{
	private static final String SQL_GET_TOKENS = "SELECT * FROM token ORDER BY name";
	private static final String SQL_INSERT_TOKEN = "INSERT INTO token"  + Token.SQL_INSERT_FIELDS + Token.SQL_INSERT_VALUES;
	private static final String SQL_UPDATE_TOKEN = "UPDATE token SET "  + Token.SQL_UPDATE_FIELDS + " WHERE tokenId=?";
	private static final String SQL_DELETE_TOKEN = "DELETE FROM token WHERE tokenId=?";
	
//	public List<Token> getTokenNames() throws DatabaseException{
//		List<Token> names = new ArrayList<>();
//		try(
//				Connection conn = TokenDatabase.getConnection();
//				PreparedStatement getPS = conn.prepareStatement(SQL_GET_TOKENS);){
//		
//			ResultSet rs = getPS.executeQuery();
//			while (rs.next()) {							// Note: Cannot open another DAO when the single Connection is still open.  It will simply hang waiting for a pool
//				Token attr = new Token(rs);
//				names.add( attr );		// The name is the first item in the ResultSet row
//			}
//		} catch (SQLException e) {
//			throw new DatabaseException("getTokens was not successful.",e);
//		}
//		System.out.println("Returning names: " + names);
//		return names;
//	}
	
//	public Token getTokenById(Integer id) throws NotFoundException, DatabaseException{
//		Token token = null;
//		try(
//				Connection conn = TokenDatabase.getConnection();
//				PreparedStatement getPS = conn.prepareStatement(SQL_GET_TOKEN_BY_ID);){
//			getPS.setInt( 	1, id);
//		
//			ResultSet rs = getPS.executeQuery();
//			if (!rs.next()) {
//				throw new NotFoundException("Token not found for id: " + id);
//			}
//			else {
//				token = new Token(rs);		
//			}
//		} catch (SQLException e) {
//			throw new DatabaseException("getTagById was not successful.",e);
//		}
//		return token;
//	}
//	
	public void insertToken(Token s) throws DatabaseException{
		try(
				Connection conn = TokenDatabase.getConnection();
				PreparedStatement insertPS = conn.prepareStatement(SQL_INSERT_TOKEN);){
		
			insertPS.setString( 1, s.getType().toString());
			insertPS.setString( 2, s.getActual());
			insertPS.setString( 3, s.getToken());
		
			int numRows = insertPS.executeUpdate();
			//int newId = getNewId(conn);
			//return newId;

		} catch (SQLException e) {
			throw new DatabaseException("Insert Token was not successful.",e);
		}
	}
//	
//	public void updateToken(Token b) throws DatabaseException{
//		try(
//				Connection conn = TokenDatabase.getConnection();
//				PreparedStatement insertPS = conn.prepareStatement(SQL_UPDATE_TOKEN);){
//		
//			insertPS.setString( 1, b.getName());
//			insertPS.setString( 2, b.getTokenName());
//			insertPS.setString( 3, b.getCode());
//			
//			insertPS.setInt( 	4, b.getId());
//			int success = insertPS.executeUpdate();
//
//		} catch (SQLException e) {
//			throw new DatabaseException("Update Token was not successful.",e);
//		}
//	}
//	
//	public void deleteToken(Integer id) throws NotDeletedException, DatabaseException {
//		try(
//				Connection conn = TokenDatabase.getConnection();
//				PreparedStatement delPS = conn.prepareStatement(SQL_DELETE_TOKEN);){
//			
//			delPS.setInt( 	1, id);
//		
//			if ( delPS.executeUpdate() == 0 ) {
//				throw new NotDeletedException("Token not deleted for id: " + id);
//			}
//		} catch (SQLException e) {
//			throw new DatabaseException("Delete Tag was not successful.",e);
//		}
//	}
//	

}
