package come.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import come.yedam.Control;
import come.yedam.dao.MemberDAO;
import come.yedam.vo.MemberVO;

public class LoginControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 요청방식 get 방식인지 post 방식인지
		if (req.getMethod().equals("GET")) {
			req.getRequestDispatcher("WEB-INF/views/member/login.jsp").forward(req, resp);
		} else if (req.getMethod().endsWith("POST")) {
			String id = req.getParameter("uname");
			String pw = req.getParameter("psw");

			// 로그인 체크
			MemberDAO mdao = new MemberDAO();
			MemberVO mvo = mdao.login(id, pw);

			if (mvo != null) { // 로그인 성공.
				System.out.println("환영합니다. " + mvo.getMemberName());
				// 세션객체에 로그인 아이디를 저장.
				HttpSession session = req.getSession();
				session.setAttribute("loginId", id); // attribute 활용.
				// 일반사용자 or 관리자
				if (mvo.getResponsibility().equals("Admin")) {
					resp.sendRedirect("memberList.do");

				} else {
					resp.sendRedirect("boardList.do");
				}

			} else { // 로그인 실패.
				System.out.println("id, pw 확인.");

			}

		}
		// 1.로그인 화면.
		// 2.로그인 기능.

	}

}
