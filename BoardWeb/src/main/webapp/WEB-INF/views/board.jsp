<%@page import="come.yedam.serv.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="includes/header.jsp"></jsp:include>
<h3>상세화면(board.jsp)</h3>
<%
BoardVO board =(BoardVO) request.getAttribute("board");
%>
<form action ="modifyForm.do">
<input type ="hidden" name ="bno" value="<%=board.getBoardNo() %>">
<table class = "table">
<tr>
<th>글 번호</th><td><%=board.getBoardNo() %></td>
<th>조회수</th><td><%=board.getViewCnt() %></td>
</tr>

<tr>
<th>내용</th>
<td colspan ="3"<%= board.getContent() %>></td>
</tr>

<tr>
<th>작성자</th>
<td><%=board.getWriter() %>></td>
<th>작성일시</th>
<td><%=board.getWriteDate() %></td>
</tr>
<tr>
<td colspan="3" align = "center">
<button class ="btn btn-warning" type ="submit">수정</button>
<button class ="btn btn-danger" type ="button">삭제</button>

</td>
</tr>
</table>
</form>
<jsp:include page="includes/footer.jsp"></jsp:include>