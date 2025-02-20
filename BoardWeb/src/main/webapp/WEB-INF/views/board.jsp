<%@page import="come.yedam.serv.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="includes/header.jsp"></jsp:include>
<%
BoardVO board =(BoardVO) request.getAttribute("board");
String msg =(String) request.getAttribute("msg");
String logId =(String) session.getAttribute("loginId");
%>
<h3>상세화면(board.jsp)</h3>
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
<th>제목</th>
<td colspan ="3"<%= board.getTitle() %>></td>
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
<%if (msg != null){ %>
<tr><td colspan="3"><span style="color:red;"><%=msg %></span></td></tr>
<%} %>
</td>
</tr>
</table>
</form>
<script>
 //삭제버튼에 클릭이벤트 등록.
 let logid="<%=logId %>"; // 자바의 변수값을 script 사용.
 document.querySelector('button.btn-danger')
 .addEventListener('click',function(e) {
	 let write = document.querySelector('table.table>tbody>tr:nth-of-type(4)>td').innerHTML;
	 let bno = document.querySelector('input[name="bno"]').value;
	// console.log(writer, logId); 
	 if(writer == logid)
	 location.href = "removeBoard.do?bno=" +bno;
	 else 
	   alert("권한을 확인하세요.");
 });
</script>
<jsp:include page="includes/footer.jsp"></jsp:include>