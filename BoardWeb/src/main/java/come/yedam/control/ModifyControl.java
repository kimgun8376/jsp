package come.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import come.yedam.Control;
import come.yedam.dao.BoardDAO;
import come.yedam.serv.BoardVO;

public class ModifyControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 수정화면 open.
		String bno = req.getParameter("bno");

		BoardDAO bdao = new BoardDAO();
		BoardVO board = bdao.getBoard(Integer.parseInt(bno));

		// 요청정보의 attribute 활용.
		req.setAttribute("board", board);
		req.getRequestDispatcher("/WEB-INF/views/modifyBoard.jsp").forward(req, resp);
	}

}
