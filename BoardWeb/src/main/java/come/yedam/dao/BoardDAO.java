package come.yedam.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import come.yedam.serv.BoardVO;

/*
 * CRUD: Create, Read, Update, Delete
 */
public class BoardDAO extends DAO {
	// 글조회수 증가.
	public void updateCount(int boardNo) {
		String sql = "update tbl_board" + "set     title = ?" + "     ,content = ?" + "where board_no = ?";
		try {
			psmt = getConnect().prepareStatement(sql);
			psmt.setInt(1, boardNo);
			psmt.executeUpdate(); // 쿼리 실행.

		} catch (SQLException e) {
		}
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
			if (rs.next())
				;
			{
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
		}
		return null; // 조회결과 없음.
	} // end of getBoard.

	// 조회 (select)
	public List<BoardVO> selectBoard() {
		List<BoardVO> list = new ArrayList<>();

		// 데이터베이스 연결을 위한 변수 선언
		String sql = "SELECT * FROM tbl_board"; // 예시 SQL문. 실제 테이블 구조에 맞게 수정 필요
		try (Connection conn = getConnect(); // 연결 가져오기
				PreparedStatement psmt = conn.prepareStatement(sql);
				ResultSet rs = psmt.executeQuery()) {

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
		}

		return list;
	}

	// 추가 (insert)
	public boolean insertBoard(BoardVO board) {
		String sql = "INSERT tbl_board (title, content, writer) VALUES (?, ?, ?)";
		try (Connection conn = getConnect(); PreparedStatement psmt = conn.prepareStatement(sql)) {

			psmt.setString(1, board.getTitle());
			psmt.setString(2, board.getContent());
			psmt.setString(3, board.getWriter());

			int result = psmt.executeUpdate();
			return result > 0; // 추가 성공하면 true 반환
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false; // 추가 실패 시 false 반환
	}

	// 수정 (update)
	public boolean updateBoard(BoardVO board) {
		String sql = "update tbl_board" + "set     title = ?" + "     ,content = ?" + "where board_no = ?";

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
		}
		return false; // 수정 실패 시 false 반환
	}

	// 삭제 (delete)
	public boolean deleteBoard(int boardNo) {
		String sql = "DELETE FROM tbl_board WHERE board_no = ?";
		try (Connection conn = getConnect(); PreparedStatement psmt = conn.prepareStatement(sql)) {

			psmt.setInt(1, boardNo);

			int result = psmt.executeUpdate();
			return result > 0; // 삭제 성공하면 true 반환
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false; // 삭제 실패 시 false 반환
	}

}
