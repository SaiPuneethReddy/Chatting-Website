package com.main.src;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.AsyncContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginPage
 */
@WebServlet(urlPatterns="/LoginPage",asyncSupported=true)
public class LoginPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<AsyncContext> contextsLoginPage = new LinkedList<>(); 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//response.getWriter().append("Served at: ").append(request.getContextPath());
		AsyncContext AsyncCont=request.startAsync(request, response);
		AsyncCont.setTimeout(10 * 60 * 1000);
		contextsLoginPage.add(AsyncCont);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		List<AsyncContext> AsyncContList = new LinkedList<>(this.contextsLoginPage); 
		this.contextsLoginPage.clear();
		PrintWriter out=response.getWriter();
		//ArrayList<HttpSession> array_session=new ArrayList<HttpSession>();
		//out.print("in LoginPage");
	
				 DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
			       Date dateobj = new Date();
			  
			    // Medium.array_session.add(session);
				String handler=request.getParameter("handler").trim();
				handler=handler.toLowerCase();
				 //session.setAttribute("handler",handler); 
				request.setAttribute("handler", handler);
				
				
				
				//request.setAttribute("request", request);
				if(Medium.userlist.contains(handler))
				{
					out.println("<p style=\"color:red;\">"+handler+" user already exist</p>");
					RequestDispatcher rd=request.getRequestDispatcher("Index.html");
					rd.include(request, response);
				}
				else
				{
						Medium.userlist.add(handler);
						
						request.setAttribute("userlist", Medium.userlist);
						String path="C:\\Users\\dell\\Documents\\chat_workspace\\";
						String dir_name=request.getParameter("handler").trim();
						
						path=path+dir_name;
						
						
						 File file = new File(path);
						 boolean file_exist = file.exists();
						 if(!file_exist)
						 {
								 boolean bool = file.mkdir();
							      if(bool){
							         System.out.println("Directory created successfully");
							      }else{
							         System.out.println("Sorry couldn’t create specified directory");
							      }
						 }	
						 else
						 {
							 System.out.println("directory was already exist");
						 }
							
						String NewJoin= handler.trim();
						ServletContext cont=request.getServletContext();
		//				if(cont.getAttribute("message")==null)
		//				{
		//				
		//					cont.setAttribute("message", NewJoin);
		//				}
		//				else
		//				{   
		//					
		//					String prev_messages=(String)cont.getAttribute("message");
		//					cont.setAttribute("message",prev_messages+"<br>"+NewJoin);
		//				}
						RequestDispatcher rd=request.getRequestDispatcher("chat.jsp");
						rd.forward(request, response);
						
						for(AsyncContext AsyncCont:AsyncContList )
						{
							try(PrintWriter res_Writer=AsyncCont.getResponse().getWriter())
							{   
							
								res_Writer.println(NewJoin);
								res_Writer.flush();
								
								AsyncCont.complete();
								
							}
							catch(Exception e)
							{
								
							}
						}
				}		
	}
	
		


}
