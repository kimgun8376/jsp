package come.yedam;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import come.yedam.dao.BoardDAO;
import come.yedam.serv.BoardVO;



public class AddFormControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 3개 파라미터 활용 db 저장.목록으로 이동.
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String writer = req.getParameter("writer");
        
        // BoardVO 객체 생성
        BoardVO bvo = new BoardVO();
        
        // BoardVO에 파라미터 값 세팅
        bvo.setTitle(title);
        bvo.setContent(content);
        bvo.setWriter(writer);

        // BoardDAO 객체 생성 후 데이터베이스에 저장
        BoardDAO bdao = new BoardDAO();
        
        // 데이터베이스에 삽입이 성공하면 목록 페이지로 리다이렉트
        if(bdao.insertBoard(bvo)) {
            resp.sendRedirect("boardList.do"); // sendRedirect로 수정
        } else {
            System.out.println("실패");
        }
	}
}
