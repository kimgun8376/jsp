package come.yedam.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import come.yedam.vo.MemberVO;

public class MemberDAO extends DAO {

    public MemberVO login(String id, String pw) {
        String sql = "SELECT member_id, passd, member_name, responsibility " 
                     + "FROM tbl_member WHERE member_id = ? AND passd = ?";  // 로그인에 조건 추가
        
        MemberVO mvo = null;

        try {
            psmt = getConnect().prepareStatement(sql);
            psmt.setString(1, id);  // id 파라미터 바인딩
            psmt.setString(2, pw);  // pw 파라미터 바인딩
            rs = psmt.executeQuery();

            if (rs.next()) {  // 조회된 결과가 있으면
                mvo = new MemberVO();
                mvo.setMemberId(rs.getString("member_id"));
                mvo.setPasswd(rs.getString("passd"));
                mvo.setMemberName(rs.getString("member_name"));  // 수정된 메서드 이름
                mvo.setResponsibility(rs.getString("responsibility"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disConnect();
        }

        return mvo;  // 조회된 MemberVO 객체 반환
    }

    public List<MemberVO> members() {
        List<MemberVO> list = new ArrayList<>();  // 리스트 초기화
        String sql = "SELECT member_id, passd, member_name, responsibility FROM tbl_member";

        try {
            psmt = getConnect().prepareStatement(sql);
            rs = psmt.executeQuery();

            while (rs.next()) {
                MemberVO mvo = new MemberVO();
                mvo.setMemberId(rs.getString("member_id"));
                mvo.setPasswd(rs.getString("passd"));
                mvo.setMemberName(rs.getString("member_name"));
                mvo.setResponsibility(rs.getString("responsibility"));
                list.add(mvo);  // 리스트에 추가
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disConnect();
        }

        return list;  // 조회된 전체 회원 리스트 반환
    }
}
