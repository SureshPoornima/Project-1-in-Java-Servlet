package com.face.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.face.bo.UserAccount;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class DBUtils {
	public static UserAccount findUser(java.sql.Connection conn, //
	           String userName, String password) throws SQLException {
	       String sql = "Select a.User_Name, a.Password, a.Gender from User_Account a " //
	               + " where a.User_Name = ? and a.password= ?";
	       PreparedStatement pstm = (PreparedStatement) conn.prepareStatement(sql);
	       pstm.setString(1, userName);
	       pstm.setString(2, password);
	       ResultSet rs = pstm.executeQuery();
	       if (rs.next()) {
	           String gender = rs.getString("Gender");
	           UserAccount user = new UserAccount();
	           user.setUserName(userName);
	           user.setPassword(password);
	           user.setGender(gender);
	           return user;
	       }
	       return null;
	   }
	public static UserAccount addProduct(java.sql.Connection conn, //
	           String productId, String productName) throws SQLException {
	       String sql = "INSERT INTO product VALUES ('"+productId+"','"+productName+"')";
	       PreparedStatement pstm = (PreparedStatement) conn.prepareStatement(sql);
	       pstm.setString(1, productId);
	       pstm.setString(2, productName);
	       ResultSet rs = pstm.executeQuery();
	       
	       return null;
	   }
}
