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

import come.yedam.control.AddBoardControl;
import come.yedam.control.BoardListControl;
import come.yedam.control.Control;

/*
 * MVC에서 Control 역할.
 * url 요청 -> 서블릿.
 */
@WebServlet("*.do")
public class FronController extends HttpServlet {
    Map<String, Control> map;

    public FronController() {
        map = new HashMap<>(); // map 필드의 초기화.
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        // URL과 해당 컨트롤러를 매핑
    //    map.put("/addStudent.do", new AddStudentControl()); 
        map.put("/boardList.do", new BoardListControl()); 
        map.put("/addBoard.do", new AddBoardControl()); 
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("FrontController: service()");

        // URI를 통해 요청된 URL을 가져옵니다.
        String uri = req.getRequestURI(); // 예: "/BoardWeb/addStudent.do"
        String context = req.getContextPath(); // 예: "/BoardWeb"
        String page = uri.substring(context.length()); // 예: "/addStudent.do"

        System.out.println("Request URI: " + page);

        // map 컬렉션에서 해당 URL에 맞는 Control 객체를 가져옵니다.
        Control control = map.get(page);
        
        // Control 객체가 null이 아니면 exec() 메서드를 실행
        if (control != null) {
            control.exec(req, resp);
        } else {
            // 존재하지 않는 URL에 대한 처리를 추가할 수 있습니다.
            resp.getWriter().print("잘못된 요청입니다.");
        }
    }
}