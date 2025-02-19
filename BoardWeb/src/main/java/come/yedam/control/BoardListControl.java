package come.yedam.control;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import come.yedam.Control;
import come.yedam.dao.BoardDAO;
import come.yedam.dao.EmpDAO;
import come.yedam.serv.BoardVO;
import come.yedam.vo.Employee;

public class BoardListControl implements Control {
	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		String name = "홍길동";
		// boardList.do -> BoardListControl
		req.setAttribute("msg", name);

		BoardDAO edao = new BoardDAO();
		List<BoardVO> list = edao.selectBoard();
		req.setAttribute("list", list);
		
		
		try {
			// 요청재지정 (url:boardList.do(boardList.jsp))
			req.getRequestDispatcher("/WEB-INF/views/boardList.jsp").forward(req, resp); // 해당파일 열려면 폴더 - 파일이름 
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
