package come.yedam;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * MVC에서 Control역할.
 * url요청 -> 서블릿.
 */
@WebServlet("*.do")
public class FronController extends HttpServlet {
	Map<String, String> map;
	
	public FronController() {
		map = new HashMap<>(); //map 필드의 초기화.
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		map.put("url","servlet"); // addStudent.do AddStudentServlet
		map.put("/boardList.do",getServletInfo()); 
	}
	
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("front control");
		// http://localhost:8080/BoardWeb/addStudent.do => url
		// /BoardWeb/addStudent.do => url
		String uri = req.getRequestURI();// "/BoardWeb/addStudent.do"
		String context = req.getContextPath(); // "/BoardWeb"
		String page = uri.substring(context.length());
		
		System.out.println(page);
	}
}