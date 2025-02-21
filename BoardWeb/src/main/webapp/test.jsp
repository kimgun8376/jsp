<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL연습</title>
</head>
<body>
  <h3>안녕하세요.</h3>
  <c:set var="msg" value="Hello"></c:set>
  <p>msg의 값은 <c:out value = "${msg }"></c:out></p>

<c:set var="myAge" value="25" />
<c:if test="${myAge >= 20 }" >
<p>당신은 성인입니다.</p>
</c:if>

<c:choose>
<c:when test="${myAge >= 20 }">
<p>당신은 성인입니다.</p>
</c:when>
<c:otherwise>
<p>당신은 미성년자입니다.</p>
</c:otherwise>
</c:choose>

<c:forEach var="i" begin="1" end ="10" step="2">   <!-- 반복문 -->
   <p>i의 값은 ${i % 2}</p>
</c:forEach>


</body>
</html>