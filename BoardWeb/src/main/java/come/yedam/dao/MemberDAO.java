package come.yedam.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import come.yedam.vo.MemberVO;

public class MemberDAO extends DAO {
    
    // 회원 삭제
    public boolean deleteMember(String id) {
        String query = "DELETE FROM tbl_member WHERE member_id = ?";
        try (PreparedStatement psmt = getConnect().prepareStatement(query)) {
            psmt.setString(1, id); // id 파라미터 바인딩
            int r = psmt.executeUpdate();
            return r > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            disConnect();
        }
    }

    // 로그인 처리
    public MemberVO login(String id, String pw) {
        String query = "SELECT * FROM tbl_member WHERE member_id = ? AND passd = ?";
        try (PreparedStatement psmt = getConnect().prepareStatement(query)) {
            psmt.setString(1, id);
            psmt.setString(2, pw);
            try (ResultSet rs = psmt.executeQuery()) {
                if (rs.next()) {
                    MemberVO mvo = new MemberVO();
                    mvo.setMemberId(rs.getString("member_id"));
                    mvo.setPasswd(rs.getString("passd"));
                    mvo.setMemberName(rs.getString("member_name"));
                    mvo.setResponsibility(rs.getString("responsibility"));
                    return mvo;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disConnect();
        }
        return null; // 로그인 실패
    }

    // 회원 목록 조회
    public List<MemberVO> members() {
        List<MemberVO> members = new ArrayList<>();
        String query = "SELECT * FROM tbl_member";
        try (PreparedStatement psmt = getConnect().prepareStatement(query);
             ResultSet rs = psmt.executeQuery()) {

            while (rs.next()) {
                MemberVO mvo = new MemberVO();
                mvo.setMemberId(rs.getString("member_id"));
                mvo.setPasswd(rs.getString("passd"));
                mvo.setMemberName(rs.getString("member_name"));
                mvo.setResponsibility(rs.getString("responsibility"));
                members.add(mvo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disConnect();
        }
        return members; // 조회된 전체 회원 리스트 반환
    }

    // 회원 삭제 (매개변수로 memberId를 받아서 삭제)
    public boolean removeMember(String memberId) {
        String query = "DELETE FROM tbl_member WHERE member_id = ?";
        try (PreparedStatement psmt = getConnect().prepareStatement(query)) {
            psmt.setString(1, memberId);
            int r = psmt.executeUpdate();
            return r > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disConnect();
        }
        return false;
    }

    // 회원 추가 메서드
    public boolean addMember(MemberVO mvo) {
        String query = "INSERT INTO tbl_member (member_id, passwd, member_name, responsibility) VALUES (?, ?, ?, ?)";
        try (PreparedStatement psmt = getConnect().prepareStatement(query)) {
            psmt.setString(1, mvo.getMemberId());
            psmt.setString(2, mvo.getPasswd());
            psmt.setString(3, mvo.getMemberName());
            psmt.setString(4, mvo.getResponsibility() != null ? mvo.getResponsibility() : ""); // 책임 항목이 null이면 빈 문자열로 처리

            int r = psmt.executeUpdate();
            return r > 0; // 회원 추가 성공 여부 반환
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disConnect();
        }
        return false; // 회원 추가 실패
    }
}
