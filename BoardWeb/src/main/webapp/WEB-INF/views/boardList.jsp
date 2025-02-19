<%@page import="come.yedam.serv.BoardVO"%>
<%@page import="come.yedam.dao.BoardDAO"%>
<%@page import="come.yedam.vo.Employee"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="includes/header.jsp"></jsp:include>
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
		<td>지은이</td>
		<td>제목</td>
		<td>작가</td>
		<td>작가정보</td>
       <td>가격</td>
		</tr>
	
		<%
		for (BoardVO board : list) {
		%>
		<tr>
		<td><%= board.getBoardNo() %></td>
		<td><a href = "board.do?bno=<%= board.getBoardNo() %>"><%=board.getTitle() %></a></td>
		<td><%= board.getWriter() %></td>
		<td><%= board.getWriteDate() %></td>
       <td><%=board.getViewCnt() %></td>
		</tr>
		<%
		} // for 반복문 종료 
		%>
		</tbody>
	</table>
<jsp:include page="includes/footer.jsp"></jsp:include>
