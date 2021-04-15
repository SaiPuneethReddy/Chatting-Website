package com.main.src;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
 * Servlet implementation class MediumServlet
 */
@WebServlet(urlPatterns="/MediumServlet",asyncSupported=true)
public class MediumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HashMap<String,AsyncContext>   contexts=new  HashMap<String,AsyncContext>();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MediumServlet() {
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
		String handler_asyn=(String)request.getParameter("handler").trim();
		AsyncContext AsyncCont=request.startAsync(request, response);
		AsyncCont.setTimeout(10 * 60 * 1000);
		System.out.println(" async"+handler_asyn+": "+AsyncCont);
		contexts.put(handler_asyn,AsyncCont);
		
	
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		Map<String,AsyncContext> AsyncContList = new  HashMap<String,AsyncContext>(); 
		AsyncContList.putAll(this.contexts);
		System.out.print(" before clear  "+AsyncContList);
//		Map<String,Integer> test = new  HashMap<String,Integer>(); 
//		
//		test.put("cd", 123);
		//this.contexts.clear();
		String path="C:\\Users\\dell\\Documents\\chat_workspace\\";
		WriteClass   wc=new WriteClass();
			PrintWriter out=response.getWriter();
			String message=request.getParameter("message");
			String handler=(String)request.getParameter("handler").trim();
			String sendname=(String)request.getParameter("sendname").trim();
			
			String time[]=request.getParameter("t").split(" ");
			String date="("+time[2]+"-"+time[1]+"-"+time[3]+") "+time[4];
			String htmlMessage = "<p>"+ "["+date+"]"+handler + ": " + message + "</p>";
			String alert_htmlMessage="<p>"+"you recieved message from "+handler + " at " + "["+date+"]</p>";
			ServletContext cont=request.getServletContext();
		
			//out.print(" htmlMessage:"+htmlMessage);
			if(cont.getAttribute("message")==null)
			{
			//out.print(" before cont.getAttribute(\"message\"):"+cont.getAttribute("message"));
			//out.print("cont.getAttribute(\"message\"):"+cont.getAttribute("message"));
				cont.setAttribute("message", htmlMessage);
				//out.print(" after cont.getAttribute(\"message\"):"+cont.getAttribute("message"));
			}
			else
			{   
				//out.print("cont.getAttribute(\"message\"):"+cont.getAttribute("message"));
				String prev_messages=(String)cont.getAttribute("message");
				cont.setAttribute("message",prev_messages+"<br>"+htmlMessage);
			}
			//handler="cd";
			System.out.print(" before if "+sendname+" "+AsyncContList.containsKey(sendname));
			
//			System.out.print(" before if test"+sendname+" "+test.contains(sendname));
//			System.out.print(" before if handler test"+handler+" "+test.contains(handler));
			System.out.print(" before after  "+AsyncContList);
			if(AsyncContList.containsKey(sendname))
			{System.out.print(" handler "+handler+" "+sendname+": "+AsyncContList.get(sendname));
				AsyncContext AsyncCont=AsyncContList.get(sendname);
				try(PrintWriter res_Writer=AsyncCont.getResponse().getWriter())
				{  
//					HttpServletRequest Asynrequest=(HttpServletRequest)AsyncCont.getRequest();
//				String handler_asyn=Asynrequest.getParameter("handler");
//				Asynrequest.setAttribute("handler", handler_asyn);
					
					wc.writeFile(path, sendname, handler, htmlMessage);
					wc.writeFile(path, handler, sendname, htmlMessage);
					res_Writer.println(alert_htmlMessage);
					res_Writer.flush();
					AsyncCont.complete();
					if(AsyncContList.containsKey(handler))
					{System.out.print(" handler "+handler+" "+sendname+": "+AsyncContList.get(handler));
						AsyncContext AsyncCont_handler=AsyncContList.get(handler);
						try(PrintWriter res_Writer_handler=AsyncCont_handler.getResponse().getWriter())
						{  
//							HttpServletRequest Asynrequest=(HttpServletRequest)AsyncCont.getRequest();
//						String handler_asyn=Asynrequest.getParameter("handler");
//						Asynrequest.setAttribute("handler", handler_asyn);
							res_Writer_handler.println(htmlMessage);
							res_Writer_handler.flush();
							
							AsyncCont_handler.complete();
							
						}
						catch(Exception e)
						{
							
						}
					}
					
				}
				catch(Exception e)
				{
					
				}
			}

			
	}

}
