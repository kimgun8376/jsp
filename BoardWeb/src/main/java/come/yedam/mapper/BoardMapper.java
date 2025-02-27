package come.yedam.mapper;

import java.util.List;

import come.yedam.common.SearchVO;
import come.yedam.vo.BoardVO;

public interface BoardMapper {
	public int getTotalCount(SearchVO search);
	public int updateCount(int boardNo);
	public BoardVO getBoard(int boardNo);
	public List<BoardVO> selectBoard(SearchVO search);
	public int insertBoard(BoardVO board);
	int updateBoard(BoardVO board);
	public int deleteBoard(int boardNo);
}