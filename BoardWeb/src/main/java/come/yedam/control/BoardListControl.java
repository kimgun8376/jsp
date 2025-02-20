package come.yedam.control;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import come.yedam.Control;
import come.yedam.PageVO;
import come.yedam.dao.BoardDAO;
import come.yedam.dao.EmpDAO;
import come.yedam.serv.BoardVO;
import come.yedam.vo.Employee;

public class BoardListControl implements Control {
	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp)
	throws SecurityException,IOException {
		
		String page = req.getParameter("page");
		page = page == null ? "1" : page; 
		
		
		String name = "홍길동";
		// boardList.do -> BoardListControl
		req.setAttribute("msg", name);

		BoardDAO edao = new BoardDAO();
		List<BoardVO> list = edao.selectBoard(Integer.parseInt(page));
		req.setAttribute("list", list);
		
		//페이징
		int totalCnt = edao.getTotalCount(); // 실제건수.
		PageVO paging = new PageVO(Integer.parseInt(page), totalCnt);
		req.setAttribute("paging", paging);
		
		
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
