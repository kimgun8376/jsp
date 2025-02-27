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

public class ModifyBoardControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       //http://localhost/BoardWeb/modifyBoard.do?bno=%EC%B2%AB%EB%B2%88%EC%A8%B0+%EA%B8%80
	    String bno = req.getParameter("bno");	
	    String title = req.getParameter("title");
	    String content = req.getParameter("content");
	    
	    BoardVO board = new BoardVO(); //메소드의 파라미터.
	    board.setBoardNo(Integer.parseInt(bno));
	    board.setTitle(title);
	    board.setContent(content);
	    
	    
		//BoardDAO bdao = new BoardDAO(); 
	    SqlSession sqlSession = DataSource.getInstance().openSession();
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		
		if (mapper.updateBoard(board) == 1) {
			resp.sendRedirect("boardList.do");
		} else {
			System.out.println("처리실패.");
		}
	}

}
