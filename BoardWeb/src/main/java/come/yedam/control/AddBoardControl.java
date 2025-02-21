package come.yedam.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import come.yedam.Control;
import come.yedam.dao.BoardDAO;
import come.yedam.serv.BoardVO;

public class AddBoardControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// &title=test&stream.2종류의 파일타입(multipart)
		String saveDir = req.getServletContext().getRealPath("images");
		MultipartRequest mr = new MultipartRequest(req// 1.요청객체
				, saveDir // 2.파일저장경로
				, 1024 * 1024 * 5// 3.최대파일크기
				, "utf-8"// 4.인코딩
				, new DefaultFileRenamePolicy()// 5.리네임정책
		);

		String title = mr.getParameter("title");
		String content = mr.getParameter("content");
		String writer = mr.getParameter("writer");
		String img = mr.getFilesystemName("img");

		// 3개 파라미터 활용 db 저장.목록으로 이동.
		// String title = req.getParameter("title");
		// String content = req.getParameter("content");
		// String writer = req.getParameter("writer");

		// BoardVO 객체 생성
		BoardVO bvo = new BoardVO();
		bvo.setTitle(title);
		bvo.setContent(title);
		bvo.setWriter(writer);
		bvo.setImg(img);

		// BoardVO에 파라미터 값 세팅

		// BoardDAO 객체 생성 후 데이터베이스에 저장
		BoardDAO bdao = new BoardDAO();

		// 데이터베이스에 삽입이 성공하면 목록 페이지로 리다이렉트
		if (bdao.insertBoard(bvo)) {
			resp.sendRedirect("boardList.do"); // sendRedirect로 수정
		} else {
			System.out.println("실패");
		}
	}
}
