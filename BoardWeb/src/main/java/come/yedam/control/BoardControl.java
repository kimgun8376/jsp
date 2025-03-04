package come.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import come.yedam.Control;
import come.yedam.common.DataSource;
import come.yedam.dao.BoardDAO;
import come.yedam.mapper.BoardMapper;
import come.yedam.vo.BoardVO;

public class BoardControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// BoardWeb/board.do?bno=12
		String bno = req.getParameter("bno");

//		BoardDAO bdao = new BoardDAO();
		SqlSession sqlSession = DataSource.getInstance().openSession();
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);

		BoardVO board = mapper.getBoard(Integer.parseInt(bno)); // 문자열 "14" -> int 14 변경.
		mapper.updateCount(Integer.parseInt(bno)); // 조회수 증가.
		
		// 요청정보의 attribute활용.
		req.setAttribute("board", board); //
		req.getRequestDispatcher("board/board.tiles").forward(req, resp);
	}

}
