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
<!-- 부트스트랩 css. js -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
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
<table class="table table-striped">
		<tr>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
       <td></td>
		</tr>
	
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
		</tbody>
	</table>
</body>
</html>  