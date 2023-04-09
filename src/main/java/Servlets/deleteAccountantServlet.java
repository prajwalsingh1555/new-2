package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.myConn;

/**
 * Servlet implementation class deleteAccountantServlet
 */
@WebServlet("/deleteAccountantServlet")
public class deleteAccountantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteAccountantServlet() {
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
		myConn mycon = new myConn();
		Connection con;
		String id = request.getParameter("id");	
		if(id!=null) {	
			try {			
				con = mycon.getConn();
				String qr = "delete from Accountants where id=?;";				
				PreparedStatement pt = con.prepareStatement(qr);				
				pt.setString(1, id);				
				pt.executeUpdate();				
				request.getRequestDispatcher("listAccountant.jsp").include(request, response);
				out.println("One Entry Deleted");
				pt.close();
				con.close();
				}
				catch(Exception e) {
					e.printStackTrace();
				}
		}else if(id==null) {
			String name = request.getParameter("name");
			
			try {			
				con = mycon.getConn();
				String qr = "delete from students where name=?;";				
				PreparedStatement pt = con.prepareStatement(qr);				
				pt.setString(1, name);				
				pt.executeUpdate();				
				request.getRequestDispatcher("accountantDashborad.jsp").include(request, response);
				out.println("One Entry Deleted");
				pt.close();
				con.close();	
				}
				catch(Exception e) {
					e.printStackTrace();
				}
		}
		
		
		
		
		
	}

}
