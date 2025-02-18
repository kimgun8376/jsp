package come.yedam;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardListControl implements Control {
	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("글 목록");
		String name = "홍길동";
		System.out.println(name);
		//boardList.do -> BoardListControl
		req.setAttribute("msg", name);
		
		try {
			// 요청재지정 (url:boardList.do(boardList.jsp))
			req.getRequestDispatcher("boardList.jsp").forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
