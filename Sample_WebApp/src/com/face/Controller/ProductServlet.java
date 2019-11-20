package com.face.Controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.face.bo.UserAccount;
import com.face.dao.DBUtils;
import com.face.dao.MyUtils;
import com.mysql.jdbc.PreparedStatement;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet(urlPatterns = { "/product" })
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 System.out.println("Welcome to Product Servlet");
         // Forward to /WEB-INF/views/loginView.jsp
         // (Users can not access directly into JSP pages placed in WEB-INF)
         RequestDispatcher dispatcher //
                 = this.getServletContext().getRequestDispatcher("/WEB-INF/Views/productList.jsp");
         dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productId = request.getParameter("productId");
        String productName = request.getParameter("productName");
        UserAccount user = null;
        InputStream inputStream = null;
        boolean hasError = false;
        String errorString = null;
        if (productId == null || productName == null || productId .length() == 0 || productName.length() == 0) {
            hasError = true;
            errorString = "Enter valid Details!";
        } else {
        	 Connection conn = MyUtils.getStoredConnection(request);
             try {
                 // Find the user in the DB.
                 user = DBUtils.addProduct(conn, productId, productName);
                 if (user == null) {
                     hasError = true;
                     errorString = "not valid";
                 }
             } catch (SQLException e) {
                 e.printStackTrace();
                 hasError = true;
                 errorString = e.getMessage();
             }
         }

}
}