<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<h3>글등록화면(addForm.jsp)</h3>

<form action="addBoard.do" method="post" enctype="multipart/form-data">
    <table class="table">
        <tr>
            <th>제목</th>
            <td><input class="form-control" type="text" name="title"></td>
        </tr>
        <tr>
            <th>내용</th>
            <td><textarea class="form-control" rows="3" cols="45" name="content"></textarea></td>
        </tr>
        <tr>
            <th>작성자</th>
            <td>
                <input class="form-control" type="hidden" name="writer" value="${loginId}">${loginId}
            </td>
        </tr>
        <tr>
        <th>이미지</th>
        <td><input type="file" name="img" class="form-control"></td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input class="btn btn-primary" type="submit" value="등록">
                <input class="btn btn-warning" type="reset" value="취소">
            </td>
        </tr>
    </table>
</form>

