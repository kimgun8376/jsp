package come.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import come.yedam.Control;
import come.yedam.dao.BoardDAO;

public class RemoveBoardControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// ?bno=22
		String bno = req.getParameter("bno");

		BoardDAO bdao = new BoardDAO();
		if (bdao.deleteBoard(Integer.parseInt(bno))) { //목록이동 
			resp.sendRedirect("boardList.do");
		} else {
			System.out.println("처리실패.");
		}
	}

}
