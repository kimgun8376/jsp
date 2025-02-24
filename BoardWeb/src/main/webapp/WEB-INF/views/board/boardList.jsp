<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h3>게시글 목록(boardList.JSP)</h3>

<!-- 검색 폼 -->
	<div class="center">
		<div class="row">
			<!-- 검색 조건 선택 -->
			<div class="col-sm-4">
				<select class="form-control" name="searchCondition">
					<option value="">선택하세요</option>
					<option value="T" ${searchCondition == "T" ? "selected" : ""}>제목</option>
					<option value="W" ${searchCondition == "W" ? "selected" : ""}>작성자</option>
					<option value="TW" ${searchCondition == "TW" ? "selected" : ""}>제목&작성자</option>
				</select>

			</div>

			<!-- 검색 키워드 입력 -->
			<div class="col-sm-5">
				<input type="text" class="form-control" name="keyword"
					placeholder="검색어를 입력하세요" value="${keyword }">
			</div>

			<!-- 검색 버튼 -->
			<div class="col-sm-2">
				<input value="조회" type="submit" class="btn btn-primary">
			</div>
		</div>
	</div>
</form>

<!-- 게시글 목록 테이블 -->
<table class="table table-striped">
	<thead>
		<tr>
			<th>글번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일시</th>
			<th>조회수</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="board" items="${list}">
			<tr>
				<td><c:out value="${board.boardNo}"></c:out></td>
				<td><a href="board.do?bno=${board.boardNo}"><c:out
							value="${board.title}"></c:out></a></td>
				<td><c:out value="${board.writer}"></c:out></td>
				<td><c:out value="${board.writeDate}"></c:out></td>
				<td><c:out value="${board.viewCnt}"></c:out></td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<!-- 페이징 처리 -->
<nav aria-label="Page navigation">
	<ul class="pagination">
		<!-- 이전 페이지 여부 -->
		<c:choose>
			<c:when test="${paging.prev}">
				<li class="page-item"><a class="page-link"
					href="boardList.do?page=&searchCondition=${searchCondition}&keyword=${keyword}">Previous</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item disabled"><span class="page-link">Previous</span></li>
			</c:otherwise>
		</c:choose>

		<!-- 페이지 번호 표시 -->
		<c:forEach var="p" begin="${paging.startPage}" end="${paging.endPage}">
			<c:choose>
				<c:when test="${p == paging.currentPage}">
					<li class="page-item active" aria-current="page"><span
						class="page-link"><c:out value="${p}" /></span></li>
				</c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link"
						href="boardList.do?page=${p}&searchCondition=${searchCondition}&keyword=${keyword}"><c:out
								value="${p}" /></a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>

		<!-- 다음 페이지 여부 -->
		<c:choose>
			<c:when test="${paging.next}">
				<li class="page-item"><a class="page-link"
					href="boardList.do?page=${p }
                &searchCondition=${searchCondition}&keyword=${keyword}">Next</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item disabled"><span class="page-link">Next</span></li>
			</c:otherwise>
		</c:choose>
	</ul>
</nav>

