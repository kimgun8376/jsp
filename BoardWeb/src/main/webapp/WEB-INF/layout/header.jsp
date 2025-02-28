<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!-- Sidebar-->
            <div class="border-end bg-white" id="sidebar-wrapper">
                <div class="sidebar-heading border-bottom bg-light">Start Bootstrap</div>
                <div class="list-group list-group-flush">
                  
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="boardList.do">게시글목록</a>
                     <c:choose>
                    <c:when test="${empty loginId }">
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="loginForm.do">로그인(화면)</a>
                    </c:when>
                    <c:otherwise>         
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="addForm.do">글등록(화면))</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="logout.do">로그아웃</a>
                    </c:otherwise>
                    </c:choose>
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="chart.do">차트</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="full.do!">캘린더</a>
                </div>
            </div>
