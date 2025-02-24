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
import come.yedam.control.AjaxControl;
import come.yedam.control.BoardControl;
import come.yedam.control.BoardListControl;
import come.yedam.control.DataControl;
import come.yedam.control.LoginControl;
import come.yedam.control.LogoutControl;
import come.yedam.control.MainControl;
import come.yedam.control.MemberListControl;
import come.yedam.control.ModifyBoardControl;
import come.yedam.control.ModifyControl;
import come.yedam.control.RemoveBoardControl;
import come.yedam.control.RemoveMeberControl;


/*
 * MVC에서 Control 역할.
 * url 요청 -> 서블릿.
 */
//@WebServlet("*.do")
public class FronController extends HttpServlet {
    Map<String, Control> map;

    public FronController() {
        map = new HashMap<>(); // map 필드의 초기화.
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        // URL과 해당 컨트롤러를 매핑
    //    map.put("/addStudent.do", new AddStudentControl());
        map.put("/main.do", new MainControl()); 
        map.put("/boardList.do", new BoardListControl()); //글목록 
        map.put("/addForm.do", new AddFormControl()); //등록화면 
        map.put("/addBoard.do", new AddBoardControl()); //등록처리 
        map.put("/board.do", new BoardControl()); //상세화면 
        map.put("/modifyForm.do", new ModifyControl()); //수정화면 
        map.put("/modifyBoard.do", new ModifyBoardControl()); //수정처리 
        // 삭제화면,삭제처리
        map.put("/removeBoard.do", new RemoveBoardControl());
   //로그인.
        map.put("/loginForm.do", new LoginControl()); //로그인화면.
        map.put("/login.do", new LoginControl());  // 로그인처리.
        map.put("/logout.do", new LogoutControl());  // 로그아웃.
       
        
        map.put("/memberList.do", new MemberListControl());  //회원목록.
        map.put("/testAjax.do", new AjaxControl());         
        map.put("/testData.do", new DataControl()); 
        // 회원삭제.
        map.put("/removeMember.do", new RemoveMeberControl());




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
            // 존재하지 않는 URL에 대한 처리를 추가할 수 있습니다...........................................
            resp.getWriter().print("잘못된 요청입니다.");
        }
    }
}