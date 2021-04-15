package com.main.src;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class WriteClass {
	
	public void writeFile(String path,String dir_name,String filename,String messageText)
	{
			try
			{
				path=path+dir_name+"\\"+filename+".txt";
			
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
											     
											      FileWriter myWriter = new FileWriter(file,true);
											      BufferedWriter br = new BufferedWriter(myWriter);
											      br.write(messageText);
											      br.close();
											      myWriter.close();
											      System.out.println("Successfully wrote to the file." +messageText); 
												  
												  
												  }
									 else
										 {
											 System.out.println("file was already exist");
			
										     FileWriter myWriter = new FileWriter(file,true);
										     BufferedWriter br = new BufferedWriter(myWriter);
										      myWriter.write(messageText);
										      br.close();
										      myWriter.close();
											  
										 }
			}		
			
			catch(Exception e)
			{
				System.out.println("Exception :"+e);
			}
	}	

}
