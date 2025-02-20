package come.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import come.yedam.Control;

public class LogoutControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
           HttpSession session = req.getSession(); //jsessionid쿠키.
           session.invalidate();
           
           resp.sendRedirect("loginForm.do");
	}

}
