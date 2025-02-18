package come.yedam;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardListControl implements Control {
	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		String name = "홍길동";
		// boardList.do -> BoardListControl
		req.setAttribute("msg", name);

		EmpDAO edao = new EmpDAO();
		List<Employee> list = edao.search(new Employee());
		req.setAttribute("list", list);

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
