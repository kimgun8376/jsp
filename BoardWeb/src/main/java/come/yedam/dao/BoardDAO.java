package come.yedam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import come.yedam.common.SearchVO;
import come.yedam.vo.BoardVO;

/*
 * CRUD: Create, Read, Update, Delete
 */
public class BoardDAO extends DAO {
	// 페이징의 처리를 위한 실체데이터.
	public int getTotalCount(SearchVO search) {
		String sql = "select count(1) from tbl_board";

		if (search.getSearchCondition().equals("T")) {
			sql += "          where title like '%'||?||'%' ";
		} else if (search.getSearchCondition().equals("W")) {
			sql += "          where writer like '%'||?||'%' ";
		} else if (search.getSearchCondition().equals("TW")) {
			sql += "          where title like '%'||?||'%' or writer like '%'||?||'%' ";
		}

		try {
			psmt = getConnect().prepareStatement(sql);
			int cnt = 1;
			if (search.getSearchCondition().equals("T")) { // 제목검색.
				psmt.setString(cnt++, search.getKeyword());
			} else if (search.getSearchCondition().equals("W")) { // 작성자검색.
				psmt.setString(cnt++, search.getKeyword());
			} else if (search.getSearchCondition().equals("TW")) { // 제목, 작성자 검색.
				psmt.setString(cnt++, search.getKeyword());
				psmt.setString(cnt++, search.getKeyword());
			}
			rs = psmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1); // count(1) 값. }
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect(); // 정상실행이거나 예외발생이나 반드시 실행할 코드. }
		}
		return 0;

	}

	// 글조회수 증가.
	public void updateCount(int boardNo) {
		String sql = "update tbl_board" + "set     title = ?" + "     ,content = ?" + "where board_no = ?";
		try {
			psmt = getConnect().prepareStatement(sql);
			psmt.setInt(1, boardNo);
			psmt.executeUpdate(); // 쿼리 실행.

		} catch (SQLException e) {
		} finally {
		}
		disConnect(); // 정상실행이거나 예외발생이나 반드시 실행할 코드.

	}

	// 상세조회. 글번호 => 전체정보 반환.
	public BoardVO getBoard(int boardNo) {
		String sql = "select board_no" //
				+ "       ,title" //
				+ "        ,content" //
				+ "        ,writer" //
				+ "        ,write_date" //
				+ "        ,view_cnt" //
				+ "  from tbl_board" //
				+ "  where board_no = ?"; //
		try {
			psmt = getConnect().prepareStatement(sql);
			psmt.setInt(1, boardNo);
			rs = psmt.executeQuery();
			// 조회결과 존재하면...
			if (rs.next()) {
				BoardVO board = new BoardVO();
				board.setBoardNo(rs.getInt("board_no"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setWriter(rs.getString("writer"));
				board.setWriteDate(rs.getDate("write_date"));
				board.setViewCnt(rs.getInt("view_cnt"));
				// 결과반환.
				return board;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect(); // 정상실행이거나 예외발생이나 반드시 실행할 코드. }
		}
		return null; // 조회결과 없음.
	} // end of getBoard.

	// 조회 (select)
	public List<BoardVO> selectBoard(SearchVO search) {
		List<BoardVO> list = new ArrayList<>();
		String qry = "select tbl_b.*" + ""
				+ "from(select rownum rn, tbl_a.*"
				+ " from( select board_no, title, "
				+ "content, writer, write_date, view_cnt" 
				+ "  from tbl_board";
		if (search.getSearchCondition().equals("T")) {
			qry += "          where title like '%'||?||'%' ";
		} else if (search.getSearchCondition().equals("W")) {
			qry += "          where writer like '%'||?||'%' ";
		} else if (search.getSearchCondition().equals("TW")) {
			qry += "          where title like '%'||?||'%' or writer like '%'||?||'%' ";
		}
		qry += "  order by board_no desc) tbl_a) tbl_b" 
		    + " where tbl_b.rn >= (? - 1 ) * 5 + 1"
				+ " and   tbl_b.rn <= ? * 5";
		// 데이터베이스 연결을 위한 변수 선언
		try {
			psmt = getConnect().prepareStatement(qry);
			// 조건.
			int cnt = 1;
			if (search.getSearchCondition().equals("T")) { // 제목검색.
				psmt.setString(cnt++, search.getKeyword());
			} else if (search.getSearchCondition().equals("W")) { // 작성자검색.
				psmt.setString(cnt++, search.getKeyword());
			} else if (search.getSearchCondition().equals("TW")) { // 제목, 작성자 검색.
				psmt.setString(cnt++, search.getKeyword());
				psmt.setString(cnt++, search.getKeyword());
			}

			psmt.setInt(cnt++, search.getPage()); // 페이지.
			psmt.setInt(cnt++, search.getPage()); // 페이지.
			rs = psmt.executeQuery();

			while (rs.next()) {
				BoardVO board = new BoardVO();
				board.setBoardNo(rs.getInt("board_no")); // 실제 컬럼 이름에 맞게 설정
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setWriter(rs.getString("writer"));
				board.setWriteDate(rs.getDate("write_date"));
				list.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect(); // 정상실행이거나 예외발생이나 반드시 실행할 코드.
		}
		return list;
	}

	// 추가 (insert)
	public boolean insertBoard(BoardVO board) {
		String sql = "INSERT into tbl_board (board_no, title, content, writer, img) VALUES (board_seq.nextval, ?, ?, ?, ?)";
		try (Connection conn = getConnect(); PreparedStatement psmt = conn.prepareStatement(sql)) {

			psmt.setString(1, board.getTitle());
			psmt.setString(2, board.getContent());
			psmt.setString(3, board.getWriter());
			psmt.setString(4, board.getImg());

			int result = psmt.executeUpdate();
			return result > 0; // 추가 성공하면 true 반환
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect(); // 정상실행이거나 예외발생이나 반드시 실행할 코드. }

		}
		return false; // 추가 실패 시 false 반환
	}

	// 수정 (update)
	public boolean updateBoard(BoardVO board) {
		String sql = "update tbl_board set     title = ?" 
	                   + "     ,content = ? " 
				       + "where board_no = ?";

		try {
			psmt = getConnect().prepareStatement(sql);
			psmt.setString(1, board.getTitle());
			psmt.setString(2, board.getContent());
			psmt.setInt(3, board.getBoardNo());
			int r = psmt.executeUpdate();
			if (r > 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect(); // 정상실행이거나 예외발생이나 반드시 실행할 코드.}
		}
		return false; // 수정 실패 시 false 반환
	}

	// 삭제 (delete)
	public boolean deleteBoard(int boardNo) {
		String query = "delete from tbl_board where board_no = ?";

		try {
			psmt = getConnect().prepareStatement(query);
			psmt.setInt(1, boardNo); // ?에 값 지정.

			int r = psmt.executeUpdate(); // 쿼리 실행.
			if (r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect(); // 정상실행이거나 예외발생이나 반드시 실행할 코드.}
		}
		return false;
	}// end of deleteBoard
}
