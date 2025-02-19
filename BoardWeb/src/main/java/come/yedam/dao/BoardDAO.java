package come.yedam.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import come.yedam.serv.BoardVO;

/*
 * CRUD: Create, Read, Update, Delete
 */
public class BoardDAO extends DAO {
    
    // 조회 (select)
    public List<BoardVO> selectBoard() {
        List<BoardVO> list = new ArrayList<>();
        
        // 데이터베이스 연결을 위한 변수 선언
        String sql = "SELECT * FROM BOARD";  // 예시 SQL문. 실제 테이블 구조에 맞게 수정 필요
        try (Connection conn = getConnect(); // 연결 가져오기
             PreparedStatement psmt = conn.prepareStatement(sql);
             ResultSet rs = psmt.executeQuery()) {
            
            while (rs.next()) {
                BoardVO board = new BoardVO();
                board.setBoardNo(rs.getInt("board_no"));  // 실제 컬럼 이름에 맞게 설정
                board.setTitle(rs.getString("title"));
                board.setContent(rs.getString("content"));
                board.setWriter(rs.getString("writer"));
                board.setWriteDate(rs.getDate("date"));
                list.add(board);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return list;
    }

    // 추가 (insert)
    public boolean insertBoard(BoardVO board) {
        String sql = "INSERT INTO BOARD (title, content, writer) VALUES (?, ?, ?)";
        try (Connection conn = getConnect(); 
             PreparedStatement psmt = conn.prepareStatement(sql)) {
            
            psmt.setString(1, board.getTitle());
            psmt.setString(2, board.getContent());
            psmt.setString(3, board.getWriter());
            
            int result = psmt.executeUpdate();
            return result > 0;  // 추가 성공하면 true 반환
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;  // 추가 실패 시 false 반환
    }

    // 수정 (update)
    public boolean updateBoard(BoardVO board) {
        String sql = "UPDATE BOARD SET title = ?, content = ? WHERE board_no = ?";
        try (Connection conn = getConnect(); 
             PreparedStatement psmt = conn.prepareStatement(sql)) {
            
            psmt.setString(1, board.getTitle());
            psmt.setString(2, board.getContent());
            psmt.setInt(3, board.getBoardNo());
            
            int result = psmt.executeUpdate();
            return result > 0;  // 수정 성공하면 true 반환
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;  // 수정 실패 시 false 반환
    }

    // 삭제 (delete)
    public boolean deleteBoard(int boardNo) {
        String sql = "DELETE FROM BOARD WHERE board_no = ?";
        try (Connection conn = getConnect(); 
             PreparedStatement psmt = conn.prepareStatement(sql)) {
            
            psmt.setInt(1, boardNo);
            
            int result = psmt.executeUpdate();
            return result > 0;  // 삭제 성공하면 true 반환
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;  // 삭제 실패 시 false 반환
    }

   
}
