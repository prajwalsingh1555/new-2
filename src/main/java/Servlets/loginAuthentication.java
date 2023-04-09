package Servlets;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import Beans.jdbc;
import Beans.myConn;


/**
 * Servlet implementation class loginAuthentication
 */
@WebServlet("/loginAuthentication")
public class loginAuthentication extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginAuthentication() {
        super();
        // TODO Auto-generated constructor stub
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		//JDBC connection object
		myConn mycon = new myConn();
		Connection con;
		
		String action = request.getParameter("action");
		switch(action){
		
		case "login":
			String login_info = request.getParameter("login");
			if(login_info.equals("admin")) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			try {	
				con = mycon.getConn();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("select * from signupData;");
				
				while(rs.next()) {
					if(username.equals(rs.getString("username")) && password.equals(rs.getString("password")))
					{
						request.getSession().invalidate();
						HttpSession session = request.getSession();
						session.setAttribute("user", "Pankaj");
						session.setMaxInactiveInterval(30*60);
						Cookie userName = new Cookie("user", username);
						userName.setMaxAge(30*60);
						response.addCookie(userName);
						request.getRequestDispatcher("HomePage.jsp").forward(request, response);
					}
				}
				
				request.getRequestDispatcher("index.jsp").forward(request, response);
				  
				st.close();
				con.close();
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
			else
			{
				String username2 = request.getParameter("username");
				String password2 = request.getParameter("password");
				try {	
					con = mycon.getConn();Statement st = con.createStatement();
					ResultSet rs = st.executeQuery("select * from Accountants;");
					
					while(rs.next()) {
						if(username2.equals(rs.getString("username")) && password2.equals(rs.getString("password")))
						{
							request.getSession().invalidate();
							HttpSession newSession1 = request.getSession(true);
							newSession1.setMaxInactiveInterval(300);
							newSession1.setAttribute("username2", username2);
							request.getRequestDispatcher("accountantDashborad.jsp").forward(request, response);
						}
					}		
					request.getRequestDispatcher("index.jsp").forward(request, response);			
					st.close();
					con.close();
					}
					catch(Exception e) {
						e.printStackTrace();
					}
			}
			break;
			
		case "logout":
			request.getSession().invalidate();
			request.getRequestDispatcher("index.jsp").forward(request, response);
			break;
			
		case "signup":
			String name = request.getParameter("name");
			String username1 = request.getParameter("username");
			String contact_number = request.getParameter("contact_number");
			String password1 = request.getParameter("password");
		
			try {
				con = mycon.getConn();
				String qr = "insert into signupData(name,username,contact_no,password) values(?,?,?,?);";			
				PreparedStatement pt = con.prepareStatement(qr);	
				pt.setString(1, name);
				pt.setString(2, username1);
				pt.setString(3, contact_number);
				pt.setString(4, password1);		
				pt.executeUpdate();		
				request.getRequestDispatcher("index.jsp").forward(request, response);
				pt.close();
				con.close();
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			break;
			
			
		case "accountantlogout":
			out.println("entered");
			request.getSession().invalidate();
			request.getRequestDispatcher("index.jsp").forward(request, response);
			break;
			
		}

	}

}
