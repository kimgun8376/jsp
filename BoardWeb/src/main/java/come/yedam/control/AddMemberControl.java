package come.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import come.yedam.Control;
import come.yedam.dao.MemberDAO;
import come.yedam.vo.MemberVO;

public class AddMemberControl implements Control {

    @Override
    public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // param(아이디, 비밀번호, 이름) 받아오기
        String id = req.getParameter("mid");
        String pw = req.getParameter("mpw");
        String name = req.getParameter("mname");
        
        // MemberDAO 객체 생성
        MemberDAO mdao = new MemberDAO();
        
        // MemberVO 객체 생성하여 받아온 데이터를 저장
        MemberVO mvo = new MemberVO();
        mvo.setMemberId(id);
        mvo.setPasswd(pw);
        mvo.setMemberName(name);
        
        // 회원 추가 메서드를 호출하여 성공 여부 확인
        boolean isOK = mdao.addMember(mvo); // addMember() 메서드를 호출하여 결과를 받아옵니다.
        
        // 응답 처리
        if (isOK) {
            resp.getWriter().print("{\"retCode\":\"OK\"}");
        } else {
            resp.getWriter().print("{\"retCode\":\"NG\"}");
        }
    }
}
