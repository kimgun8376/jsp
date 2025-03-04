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

import come.yedam.control.FullCalendarControl;
import come.yedam.common.AddData;
import come.yedam.common.ApiControl;
import come.yedam.common.FullData;
import come.yedam.common.MapControl;
import come.yedam.common.RemoveData;
import come.yedam.common.FullData;
import come.yedam.control.AddBoardControl;
import come.yedam.control.AddFormControl;
import come.yedam.control.AddMemberControl;
import come.yedam.control.AjaxControl;
import come.yedam.control.BoardControl;
import come.yedam.control.BoardListControl;
import come.yedam.control.ChartControl;
import come.yedam.control.ChartData;
import come.yedam.control.DataControl;
import come.yedam.control.DataTableControl;
import come.yedam.control.FullCalendarControl;
import come.yedam.control.LoginControl;
import come.yedam.control.LogoutControl;
import come.yedam.control.MainControl;
import come.yedam.control.MemberListControl;
import come.yedam.control.ModifyBoardControl;
import come.yedam.control.ModifyControl;
import come.yedam.control.RemoveBoardControl;
import come.yedam.control.RemoveMeberControl;
import come.yedam.control.RemoveReplyConttrol;
import come.yedam.control.ReplyCount;
import come.yedam.control.ReplyListControl;
import come.yedam.control.addReplyControl;


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
        //회원등록.
        map.put("/addMember.do", new AddMemberControl());

        //댓글관련.
        map.put("/replyList.do", new ReplyListControl()); // 목록.
        map.put("/addReply.do", new addReplyControl()); // 등록.
        map.put("/removeReply.do", new RemoveReplyConttrol()); // 삭제.
        map.put("/getReplyCnt.do", new ReplyCount()); // 삭제.
        
        // 차트.
        map.put("/chart.do", new ChartControl()); // .
        map.put("/chartData.do", new ChartData()); // 삭제.
       
        //datatable
        map.put("/datatable.do", new DataTableControl()); //
        map.put("/full.do", new FullCalendarControl());
		map.put("/fullData.do", new FullData()); // 조회.
		map.put("/addData.do", new AddData()); // 등록.
		map.put("/removeData.do", new RemoveData()); //삭제.

		map.put("/api.do", new ApiControl()); //삭제.
		map.put("/map.do", new MapControl()); //삭제.

                                     
    }

    @Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		System.out.println("front control");
		// http://localhost:8080/BoardWeb/addStudent.do => url
		// /BoardWeb/addStudent.do => uri
		String uri = req.getRequestURI(); // "/BoardWeb/addStudent.do"
		String context = req.getContextPath(); // "/BoardWeb"
		String page = uri.substring(context.length());

		System.out.println(page);
		// map컬렉션에서 key를 입력하면 val반환.
		Control control = map.get(page);
		control.exec(req, resp);
	}
}
