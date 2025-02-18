<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <!-- html 주석문. -->
   <%
   String msg = "Hello";
   System.out.println(msg); 
   // boardList.do -> request -> boardList.jsp
   String result = (String) request.getAttribute("msg");
   %>
   <h3>반복문</h3>
   <%
   for (int i =1; i <= 10; i++){
   %>
   <p>i*2의 값은<%=i*2 %></p>
   <%
   }
   %>
</body>
</html>