package come.yedam.serv;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import come.yedam.dao.EmpDAO;
import come.yedam.vo.Employee;

// init - service - destroy : 서블릿의 생명주기.
@WebServlet("/addEmp")
public class AddEmployeeServ extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		?empNo=1004&empName=Kim&telNo=654-0107
		resp.setContentType("text/html;charset=utf-8");

		String eno = req.getParameter("empNo"); // empNo의 param에 담겨있는 값 반환.
		String ename = req.getParameter("empName");
		String tel = req.getParameter("telNo");

		// db등록.
		EmpDAO edao = new EmpDAO();
		boolean result = edao.registerEmp(new Employee(Integer.parseInt(eno), ename, tel));
		if (result) {
			resp.getWriter().print("처리성공");
			// resp.sendRedirect("sample"); // addEmpServlet -> sample 페이지이동.
		} else {
			resp.getWriter().print("처리실패");
		}
	}
}
