<%@page import="come.yedam.serv.BoardVO"%>
<%@page import="come.yedam.dao.BoardDAO"%>
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
	List<BoardVO> list = (List<BoardVO>) request.getAttribute("list");
	%>
	<p>msg의 값은<%=result%></p>
	<h3>게시글 목록</h3>
<table border ="2">
		<%
		for (BoardVO board : list) {
		%>
		<tr>
		<td><%= board.getBoardNo() %></td>
		<td><%= board.getTitle() %></td>
		<td><%= board.getWriter() %></td>
		<td><%= board.getWriteDate() %></td>
       <td><%=board.getViewCnt() %></td>
		</tr>
		<%
		} // for 반복문 종료 
		%>
	</table>
</body>
</html>  // 컨트롤 + 스페이스 = import