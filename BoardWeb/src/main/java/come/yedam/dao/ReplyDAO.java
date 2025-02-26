package come.yedam.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import come.yedam.vo.ReplyVO;

// 댓글목록, 등록, 삭제, 상세조회.
public class ReplyDAO extends DAO {

	// 부서별 인원현황 차트.
    public List<Map<String, Object>> chartData() {
    	String sql = "select emp.department_id"
    			+ "       , dept.department_name"
    			+ "       , count(1) cnt"
    			+ "   from employees emp"
    			+ "   join departments dept"
    			+ "   on   emp.department_id = dept.department_id"
    			+ "   group by emp.department_id, dept.department_name";
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	
    	try {
			psmt = getConnect().prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> map = new HashMap<>();
				map.put("dept_name", rs.getString(2));
				map.put("dept_count", rs.getInt(3));
                list.add(map);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
    	return list;
    }
	
	// 댓글의 건수 계산(페이징)
	public int replyCount(int boardNo) {
		String sql = "select count(1) from tbl_reply where board_no = ?";
		try {
			psmt = getConnect().prepareStatement(sql);
			psmt.setInt(1, boardNo);
			rs = psmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return 0;
	}

	// 목록.
	public List<ReplyVO> replyList(int boardNo, int page) {
		String sql = "select  tbl_a.* "//
				+ "from (select /*+ INDEX_DESC (r pk_reply) */ "//
				+ "             rownum rn, reply_no, reply, replyer, board_no, reply_date "//
				+ "      from tbl_reply r "//
				+ "      where board_no = ?) tbl_a "//
				+ "where tbl_a.rn > (? - 1) * 5 "//
				+ "and   tbl_a.rn <= ? * 5";
		List<ReplyVO> list = new ArrayList<>();
		// 조회.
		try {
			psmt = getConnect().prepareStatement(sql);
			psmt.setInt(1, boardNo);
			psmt.setInt(2, page);
			psmt.setInt(3, page);
			rs = psmt.executeQuery(); // 쿼리실행.

			while (rs.next()) { // 조회된 결과가 있으면.
				ReplyVO reply = new ReplyVO();
				reply.setBoardNo(rs.getInt("board_no"));
				reply.setReply(rs.getString("reply"));
				reply.setReplyDate(rs.getDate("reply_date"));
				reply.setReplyNo(rs.getInt("reply_no"));
				reply.setReplyer(rs.getString("replyer"));

				list.add(reply); // 건수만큼 list추가.
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return list;
	}

	// 상세.
	public ReplyVO selectReply(int replyNo) {
		String sql = "select reply_no "//
				+ "         ,reply "//
				+ "         ,replyer"//
				+ "         ,reply_date"//
				+ "         ,board_no"//
				+ "   from tbl_reply "//
				+ "   where reply_no = ?";

		// 조회.
		try {
			psmt = getConnect().prepareStatement(sql);
			psmt.setInt(1, replyNo);
			rs = psmt.executeQuery(); // 쿼리실행.

			while (rs.next()) { // 조회된 결과가 있으면.
				ReplyVO reply = new ReplyVO();
				reply.setBoardNo(rs.getInt("board_no"));
				reply.setReply(rs.getString("reply"));
				reply.setReplyDate(rs.getDate("reply_date"));
				reply.setReplyNo(rs.getInt("reply_no"));
				reply.setReplyer(rs.getString("replyer"));

				return reply;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return null;
	}

	// 등록.
	public boolean insertReply(ReplyVO reply) {
		String query1 = "select reply_seq.nextval from dual";
		String query = "insert into tbl_reply (reply_no, reply, replyer, board_no) " //
				+ "values(?, ?, ?, ?)";
		try {
			psmt = getConnect().prepareStatement(query1);
			rs = psmt.executeQuery();
			if (rs.next()) {
				reply.setReplyNo(rs.getInt(1)); // 첫번째 컬럼.
			}

			psmt = getConnect().prepareStatement(query);
			psmt.setInt(1, reply.getReplyNo());
			psmt.setString(2, reply.getReply()); // ?에 값 지정.
			psmt.setString(3, reply.getReplyer()); // ?에 값 지정.
			psmt.setInt(4, reply.getBoardNo()); // ?에 값 지정.
			int r = psmt.executeUpdate(); // 쿼리 실행.
			if (r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect(); // 정상실행이거나 예외발생이나 반드시 실행할 코드.
		}
		return false;
	}

	// 삭제.
	public boolean deleteReply(int replyNo) {
		String query = "delete from tbl_reply where reply_no = ?";
		try {
			psmt = getConnect().prepareStatement(query);
			psmt.setInt(1, replyNo); // ?에 값 지정.
			int r = psmt.executeUpdate(); // 쿼리 실행.
			if (r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect(); // 정상실행이거나 예외발생이나 반드시 실행할 코드.
		}
		return false;
	}

}
