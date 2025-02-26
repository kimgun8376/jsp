package come.yedam.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import come.yedam.Control;
import come.yedam.dao.MemberDAO;
import come.yedam.vo.MemberVO; // MemberVO 클래스 import 필요

public class DataControl implements Control {

    @Override
    public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json;charset=utf-8");
        
        String json = "["; // JSON 배열 시작
        
        MemberDAO mdao = new MemberDAO();
        List<MemberVO> members = mdao.members(); // MemberVO 객체 리스트 가져오기
        
        for (int i = 0; i < members.size(); i++) {
            MemberVO member = members.get(i);
            json += "{\"memberId\":\"" + member.getMemberId() + 
                    "\",\"passwd\":\"" + member.getPasswd() + 
                    "\",\"memberName\":\"" + member.getMemberName() + 
                    "\",\"responsibility\":\"" + member.getResponsibility() + "\"}";


            // 마지막 항목이 아니면, 쉼표 추가
            if (i + 1 < members.size()) {
                json += ",";
            }
        }
        
        json += "]"; // JSON 배열 끝
        
        System.out.print(json);
        resp.getWriter().print(json); // JSON 출력
    }
}
