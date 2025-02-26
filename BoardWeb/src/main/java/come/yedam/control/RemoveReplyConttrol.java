package come.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import come.yedam.Control;
import come.yedam.dao.ReplyDAO;

public class RemoveReplyConttrol implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 댓글번호
		String rno = req.getParameter("rno");

		// DB.
		ReplyDAO rdao = new ReplyDAO();
		boolean run = rdao.deleteReply(Integer.parseInt(rno));

		// json 반환.
		if (run) {
          // {"retCode" : "OK"}
			resp.getWriter().print("{\"retCode\": \"OK\"}");
		} else {
			resp.getWriter().print("{\"retCode\": \"NG\"}");

		}
	}

}
