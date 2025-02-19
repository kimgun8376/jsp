<%@page import="come.yedam.serv.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="includes/header.jsp"></jsp:include>
<h3>수정화면(modifyBoard.jsp)</h3>
<%
BoardVO board =(BoardVO) request.getAttribute("board");
%>

<table class = "table">
<tr>
<th>글 번호</th><td><%=board.getBoardNo() %></td>
<th>조회수</th><td><%=board.getViewCnt() %></td>
</tr>
<tr>
<th>제목</th>
<td colspan="3">
<input type="text" class="form-control" value="<%=board.getTitle() %>">
</td>
</tr>

<tr>
<th>내용</th>
<td colspan ="3">
<textarea cols="45" rows="3" class="form-control"> <%=board.getContent() %></textarea>
</td>
</tr>

<tr>
<th>작성자</th>
<td><%=board.getWriter() %>></td>
<th>작성일시</th>
<td><%=board.getWriteDate() %></td>
</tr>
</form>
</table>
<jsp:include page="includes/footer.jsp"></jsp:include>