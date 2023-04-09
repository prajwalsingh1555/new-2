package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Beans.myConn;

/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/CRUDStudentServlet")
public class CRUDStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CRUDStudentServlet() {
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
		HttpSession newsession=request.getSession(true);
		String uname = (String) newsession.getAttribute("username");
		if(uname=="") {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}

		switch(action) {
		case "addStudent":
			
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String contact = request.getParameter("contact");
			String roll = request.getParameter("rollNumber");
			String feesDues = request.getParameter("fees");
			if(name!="" && email!=""&& contact!=""&& roll!=""&& feesDues!="") {
			try {
				
				con = mycon.getConn();
				String qr = "insert into students(name,email,contact,roll, fees_dues) values(?,?,?,?,?);";				
				PreparedStatement pt = con.prepareStatement(qr);				
				pt.setString(1, name);
				pt.setString(2, email);
				pt.setString(3, contact);
				pt.setString(4, roll);
				pt.setString(5, feesDues);				
				pt.executeUpdate();				
				request.getRequestDispatcher("accountantDashborad.jsp").include(request, response);	
				out.println("Student Added Successfully");
				pt.close();
				con.close();				
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}else {
				request.getRequestDispatcher("addStudent.jsp").include(request, response);
				out.println("<script>alert('Enter Valid Entry Not Null');</script>");
			}
			break;
			
		case "updateStudent":
			String name1	 = request.getParameter("name3");
			String email1 = request.getParameter("email3");
			String contact1 = request.getParameter("contact3");
			String roll1 = request.getParameter("roll3");
			String fees1 = request.getParameter("fees3");
			if(name1!="" && email1!=""&& contact1!=""&& roll1!=""&& fees1!="") {
			try {			
				con = mycon.getConn();
				String qr = "update students set email=?, contact=?, roll=?, fees_dues=? where name=?;";				
				PreparedStatement pt = con.prepareStatement(qr);			
				pt.setString(1, email1);
				pt.setString(2, contact1);
				pt.setString(3, roll1);
				pt.setString(4, fees1);
				pt.setString(5, name1);			
				pt.executeUpdate();				
				pt.close();
				con.close();
				request.getRequestDispatcher("accountantDashborad.jsp").include(request, response);
				out.println("Update Data Successfully");
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
			else {
				request.getRequestDispatcher("accountantDashborad.jsp").include(request, response);
				out.println("<script>alert('Enter Valid Entry Not Null');</script>");
			}
			break;

		case "listStudent":
			try{
				con = mycon.getConn();
				Statement st = con.createStatement();				
				ResultSet rs = st.executeQuery("select * from students;");	
				request.getRequestDispatcher("accountantDashborad.jsp").include(request, response);
				out.println("<table>");	
				out.println("<tr><td> ID </td><td> NAME </td><td> EMAIL </td><td> CONTACT </td><td> ROLL </td><td> FEES DUES </td></tr>");
				while(rs.next()) {	
					String name3 = rs.getString("name");
					String email3 = rs.getString("email");
					String contact3 = rs.getString("contact");
					String roll3 = rs.getString("roll");
					String fees3 = rs.getString("fees_dues");
					 out.println("<tr><td>" + rs.getInt("id") + "</td>");
					 out.println("<td>" + rs.getString("name")+ "</td>");
					 out.println("<td>" + rs.getString("email")+ "</td>");
					 out.println("<td>" + rs.getString("contact")+ "</td>");
					 out.println("<td>" + rs.getString("roll")+ "</td>");
					 out.println("<td>" + rs.getString("fees_dues")+ "</td>");	
					 out.println("<td><form action=deleteAccountantServlet?name="+name3+" method=post><button>Delete</button></form></td>");
					 out.println("<td><form action=CRUDStudentServlet?action=updatedata&name="+name3+"&email="+email3+"&contact="+contact3+"&roll="+roll3+"&fees="+fees3+" method=post ><button>Update</button></form></td></tr>");
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
			String roll2 = request.getParameter("roll");
			String fees2 = request.getParameter("fees");
			request.getRequestDispatcher("accountantDashborad.jsp").include(request, response);
			out.println("<form action=CRUDStudentServlet?action=updateStudent&name3="+name2+" method=post ><input type=text value="+email2+" name=email3 />");
			out.println("<input type=text value="+contact2+" name=contact3 />");
			out.println("<input type=text value="+roll2+" name=roll3 />");
			out.println("<input type=text value="+fees2+" name=fees3 />");
			out.println("<button>UPDATE DATA</button></form>");
			break;
		}

	}

}
