package come.yedam.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import come.yedam.Control;
import come.yedam.common.DataSource;
import come.yedam.common.PageVO;
import come.yedam.common.SearchVO;
import come.yedam.mapper.BoardMapper;
import come.yedam.vo.BoardVO;

public class BoardListControl implements Control {
	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String page = req.getParameter("page");
		page = page == null ? "1" : page;
		String sc = req.getParameter("searchCondition");
		String kw = req.getParameter("keyword");
		sc = sc == null ? "" : sc; // null 값처리
		kw = kw == null ? "" : kw; // null 값처리

		// SearchVO : 파라미터
		SearchVO search = new SearchVO(Integer.parseInt(page), sc, kw);

		String name = "홍길동";
		// boardList.do -> (BoardListControl) -> boardList.jsp
		req.setAttribute("msg", name);

//		BoardDAO edao = new BoardDAO();
//		List<BoardVO> list = edao.selectBoard(search);
		
		SqlSession sqlSession = DataSource.getInstance().openSession();
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		List<BoardVO> list = mapper.selectBoard(search);
		
		req.setAttribute("list", list);

		// 페이징
		//int totalCnt = edao.getTotalCount(search); // 실제건수.
		int totalCnt = mapper.getTotalCount(search); // 실제건수.


		PageVO paging = new PageVO(Integer.parseInt(page), totalCnt);
		req.setAttribute("paging", paging);
		
		// searchCondition, keyword 전달.
		req.setAttribute("searchCondition", sc);
		req.setAttribute("keyword", kw);

		// 요청재지정 (url:boardList.do(boardList.jsp))
		req.getRequestDispatcher("board/boardList.tiles").forward(req, resp); // 해당파일 열려면 폴더 - 파일이름

	}
}
