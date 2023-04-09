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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Beans.jdbc;
import Beans.myConn;

/**
 * Servlet implementation class addDataServlet
 */
@WebServlet("/CRUDAccountant")
public class CRUDAccountant extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CRUDAccountant() {
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
		response.setContentType("text/html");	
		String action = request.getParameter("action");

		switch(action) {
		case "addAccountant":

			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String contact = request.getParameter("contact");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			if(name!="" && email!=""&& contact!=""&& username!=""&& password!="") {
			
			try {
				
				con = mycon.getConn();
				String qr = "insert into Accountants(name,email,contact,username,password) values(?,?,?,?,?);";				
				PreparedStatement pt = con.prepareStatement(qr);				
				pt.setString(1, name);
				pt.setString(2, email);
				pt.setString(3, contact);
				pt.setString(4, username);
				pt.setString(5, password);				
				pt.executeUpdate();				
				request.getRequestDispatcher("addAccountant.jsp").include(request, response);	
				out.println("Accountant Added Successfully");
				pt.close();
				con.close();				
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
			else {
				request.getRequestDispatcher("addAccountant.jsp").include(request, response);
				out.println("<script>alert('Enter Valid Entry Not Null');</script>");
			}
			break;

		case "updateAccountant":
			String name1	 = request.getParameter("name3");
			String email1 = request.getParameter("email3");
			String contact1 = request.getParameter("contact3");
			String username1 = request.getParameter("username3");
			String password1 = request.getParameter("password3");
			
			try {			
				con = mycon.getConn();
				String qr = "update Accountants set email=?, contact=?, username=?, password=? where name=?;";				
				PreparedStatement pt = con.prepareStatement(qr);			
				pt.setString(1, email1);
				pt.setString(2, contact1);
				pt.setString(3, username1);
				pt.setString(4, password1);
				pt.setString(5, name1);			
				pt.executeUpdate();				
				request.getRequestDispatcher("listAccountant.jsp").include(request, response);
				out.println("Update Data Successfully");
				pt.close();
				con.close();
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			break;	
			
		case "listAccountant":
			try{
				
				con = mycon.getConn();
				Statement st = con.createStatement();				
				ResultSet rs = st.executeQuery("select * from accountants;");
				request.getRequestDispatcher("listAccountant.jsp").include(request, response);
				out.println("<h1>" + "List Of Accountants"+ "</h1>");
				out.println("<table>");	
				out.println("<tr><td> ID </td><td> NAME </td><td> EMAIL </td><td> CONTACT </td><td> USERNAME </td><td> PASSWORD </td><td> DELETE </td></tr>");
				while(rs.next()) {
					String id1 = rs.getString("id");
					String name3 = rs.getString("name");
					String email3 = rs.getString("email");
					String contact3 = rs.getString("contact");
					String username3 = rs.getString("username");
					String password3 = rs.getString("password");
					 out.println("<tr><td>" + rs.getInt("id") + "</td>");
					 out.println("<td>" + rs.getString("name")+ "</td>");
					 out.println("<td>" + rs.getString("email")+ "</td>");
					 out.println("<td>" + rs.getString("contact")+ "</td>");
					 out.println("<td>" + rs.getString("username")+ "</td>");
					 out.println("<td>" + rs.getString("password")+ "</td>");	
					 out.println("<td><form action=deleteAccountantServlet?id="+id1+" method=post><button>Delete</button></form></td>");
					 out.println("<td><form action=CRUDAccountant?action=updatedata&name="+name3+"&email="+email3+"&contact="+contact3+"&username="+username3+"&password="+password3+" method=post ><button>Update</button></form></td></tr>");
				}
				out.println("</table>");
				st.close();
				con.close();
				}
				catch(Exception e) {
					e.printStackTrace();
			 	}
			break;
			
		case "updatedata":
			String name2 = request.getParameter("name");
			String email2 = request.getParameter("email");
			String contact2 = request.getParameter("contact");
			String username2 = request.getParameter("username");
			String password2 = request.getParameter("password");
			request.getRequestDispatcher("listAccountant.jsp").include(request, response);
			out.println("<form action=CRUDAccountant?action=updateAccountant&name3="+name2+" method=post ><input type=text value="+email2+" name=email3 />");
			out.println("<input type=text value="+contact2+" name=contact3 />");
			out.println("<input type=text value="+username2+" name=username3 />");
			out.println("<input type=password value="+password2+" name=password3 />");
			out.println("<button>UPDATE DATA</button></form>");
			break;
		}
		
	}

}
