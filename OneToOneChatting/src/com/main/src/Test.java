package com.main.src;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Test
 */
@WebServlet("/Test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Test() {
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
		// TODO Auto-generated method stub
		//doGet(request, response);
		try {
				String path="C:\\Users\\dell\\Documents\\chat_workspace\\";
				String dir_name=request.getParameter("handler").trim();
				String messageText=request.getParameter("message").trim();
				String sendname=request.getParameter("sendname").trim();
				String currentname=request.getParameter("currentname").trim();
				String flag=request.getParameter("flag").trim();
				
				PrintWriter out=response.getWriter();
//				if(flag!=null)
//				{
						path=path+dir_name+"\\"+sendname+".txt";
						
						String switch_path="C:\\Users\\dell\\Documents\\chat_workspace\\";
						  switch_path=switch_path+dir_name+"\\"+currentname+".txt";
						 File file = new File(path);
						 boolean file_exist = file.exists();
						 if(!file_exist)
						 {
								 boolean bool = file.createNewFile();
							      if(bool){
							         System.out.println("file created successfully");
							      }else{
							         System.out.println("Sorry couldn’t create specified file");
							      }
							     

							   
							    
							      File switch_file = new File(switch_path);
									 boolean switch_file_exist = switch_file.exists();
									 if(!switch_file_exist)
									 {
											 boolean switch_file_exist_bool = switch_file.createNewFile();
										      if(switch_file_exist_bool){
										         System.out.println("file created successfully");
										      }else{
										         System.out.println("Sorry couldn’t create specified file");
										      }
										     
										      out.println("");
										      
										      
									 }	
									 else
									 {
										 System.out.println("file was already exist");
										 String data="";
									      Scanner myReader = new Scanner(switch_file);
									      while (myReader.hasNextLine()) {
									        data = data+myReader.nextLine();
									        System.out.println(data);
									      }
									      myReader.close();
									      out.println(data);
									      System.out.println("Successfully read to the file."); 
									 }
							      
						 }	
						 else
						 {
							 System.out.println("file was already exist");

						      
						      File switch_file = new File(switch_path);
								 boolean switch_file_exist = switch_file.exists();
								 if(!switch_file_exist)
								 {
										 boolean switch_file_exist_bool = switch_file.createNewFile();
									      if(switch_file_exist_bool){
									         System.out.println("file created successfully");
									      }else{
									         System.out.println("Sorry couldn’t create specified file");
									      }
									  
									      
									      out.println("");  
									      
								 }	
								 else
								 {
									 System.out.println("file was already exist");
									    String data1="";
									      Scanner myReader1 = new Scanner(switch_file);
									      while (myReader1.hasNextLine()) {
									        data1 = data1+myReader1.nextLine();
									        System.out.println(data1);
									      }
									      myReader1.close();
									      out.println(data1);
									      System.out.println("Successfully read to the file."); 
									 
								 }
						 }
//				}			 
		}
		catch(Exception e)
		{
			System.out.println("Exception :"+e);
		}
		
	}

}
