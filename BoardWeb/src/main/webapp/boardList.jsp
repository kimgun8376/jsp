<%@page import="come.yedam.vo.Employee"%>
<%@page import="java.util.List"%>
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
	List<Employee> list = (List<Employee>) request.getAttribute("list");
	%>
	<p>msg의 값은<%=result%></p>
	<h3>반복문</h3>
	<ul>
		<%
		for (Employee emp : list) {
		%>
		<li><%=emp.getEmpNo()%>,<%=emp.getEmpName()%>,<%=emp.getSalary()%></li>
		<%
		}
		%>
	</ul>
</body>
</html>  // 컨트롤 + 스페이스 = import