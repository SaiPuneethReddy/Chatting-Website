<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page language="java" import="java.util.*,java.lang.*" %>
<!DOCTYPE html>
<html>
<head>
 <title>online chatting</title>
  <link rel="stylesheet" type="text/css" href="css/style.css" />
 
</head>
<body>
<%
String var=(String)request.getAttribute("handler");
//String var_2=(String)application.getAttribute("message");
String var_2=null;
List<String> userlist=(ArrayList<String>)request.getAttribute("userlist");
 

 %>
 <%
 final String name=var;
  %>
 <div id="main_chat">
     Welcome
<div id="user name" >
  <%if(name!=null) {%>
   <%=name%>
   <%} %>
 </div>
 
 <div id="alert" >
  
 </div>
 <div id = 'workplace'>
       
			 <div id = 'name_tabs'>
			    <table style="width:100%" id='name_list'>
			        <thead>
			            <tr>
			                <th>
			                    Connected Users
			                </th>
			            </tr>
			        </thead>
			        <tbody id='table_body'>
			           <%  Iterator<String> iter = userlist.iterator(); 
									while(iter.hasNext()) { %>
									
									<% String temp=iter.next();
									    if(!temp.equals(var))
									    {%>
									  <tr>
			                <td><button onclick="buttonClick(this)" id="<%=temp%>" value='<%=temp%>'><%=temp%></button></td>
			            </tr>  
									    
									    
									  <%  }
									    
									} %>
			            
			        
			        </tbody>
			     </table>
			 </div>

 
				 <div id='screen'>
				
				   <div id='output'>
				   <%if(var_2!=null) {%>
				   <%=var_2%>
				   <%} %>
				   </div>
				   <div id='feedback'></div>
				 </div>

		<div>
		 
		  <input id='message' type='text'  name="message" placeholder="message"/> <br>
		  
		  
			  <input id='send' type="button" value='send' onclick="doPost()"/>	
		
		</div>
 </div>
    
 <script>
 var flag=null;
 var sendname = null;
 var input = document.getElementById("message");
input.addEventListener("keyup", function(event) {
  if (event.keyCode === 13) {
   event.preventDefault();
   document.getElementById("send").click();
   
  }
});
  var messagesWaiting = false;
 function loadAsyn() {
 if(!messagesWaiting)
 var xhttp=new XMLHttpRequest();
 
messagesWaiting=true;
 xhttp.onreadystatechange=function(){
 
 if(this.readyState==4 && this.status==200)
 {messagesWaiting=false;
    var contentElement = document.getElementById("output");

 contentElement.innerHTML= contentElement.innerHTML+xhttp.responseText;
 

 
 }
 };
  var nameText = escape(document.getElementById("user name").innerHTML);
 xhttp.open("GET", "MediumServlet?t="+new Date()+"&handler="+nameText, true);
 //xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
  xhttp.send();
 }
 function doPost()
 {
 
 var xhttp=new XMLHttpRequest();
   var nameText = escape(document.getElementById("user name").innerHTML);
      //var  sendname=document.getElementById("sendname").value;
 var messageText = escape(document.getElementById("message").value);
 xhttp.open("POST", "MediumServlet?t="+new Date(), false);
 xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
  xhttp.send("handler="+nameText+"&message="+messageText+"&sendname="+sendname);
  document.getElementById("message").value='';
 }
 
 
 var messagesWaitingLoginPage = false;
 function loadAsynLoginPage() {
 if(!messagesWaitingLoginPage)
 var xhttp=new XMLHttpRequest();
messagesWaitingLoginPage=true;
 xhttp.onreadystatechange=function(){
 
 if(this.readyState==4 && this.status==200)
 {messagesWaitingLoginPage=false;
    var contentElement = document.getElementById("table_body");

var str = new String(xhttp.responseText);

 contentElement.innerHTML=contentElement.innerHTML+"<tr><td><button onclick='buttonClick(this)' id='"+str+"' value='"+str+"'>"+str+"</button></td></tr>";
 
   
 

 
 }
 };
  
 xhttp.open("GET", "LoginPage?t="+new Date(), true);
 //xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
  xhttp.send();
 }
 setInterval(loadAsyn, 100);
 setInterval(loadAsynLoginPage, 1000);
     
 function buttonClick(myButton){
     var buttons = document.getElementsByTagName('Button');
     for (button of  buttons){
         button.style.backgroundColor = 'mediumaquamarine';
     }
     myButton.style.backgroundColor = 'white';
     switchscreen(myButton);
     sendname = myButton.value;
     
     console.log(sendname);
 }
 
 function switchscreen(myButton)
 {
   
 var xhttp=new XMLHttpRequest();
   var nameText = escape(document.getElementById("user name").innerHTML);
      
 var messageText = escape(document.getElementById("output").innerHTML);
 xhttp.open("POST", "Test?t="+new Date(), false);
 xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
 var currentname = myButton.value;
  xhttp.send("handler="+nameText+"&message="+messageText+"&sendname="+sendname+"&currentname="+currentname+"&flag="+flag);
  
  document.getElementById("output").innerHTML = xhttp.responseText;
    flag="one";
 }
 
 
 </script>
 


 
 
</body>
</html>