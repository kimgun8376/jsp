package come.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import come.yedam.Control;
import come.yedam.dao.MemberDAO;

public class RemoveMeberControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String id =req.getParameter("mid");
      
      //MemberDAO에 삭제. boolean
      MemberDAO mdao = new MemberDAO();
      // 정상삭제 : true, 처리예외:false;
      boolean isOK = mdao.deleteMember(id);
      if(isOK) {
    	  // {"retCode":"ok"}
    	  resp.getWriter().print("{\"retCode\":\"OK\"}");
      } else {
    	  // {"retCode" : "NG"
    	  resp.getWriter().print("{\"retCode\":\"NG\"}");

      }
	}

}
