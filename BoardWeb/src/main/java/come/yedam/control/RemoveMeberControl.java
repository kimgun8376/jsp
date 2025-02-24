package come.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import come.yedam.Control;

public class RemoveMeberControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String id =req.getParameter("mid");
      
      //MemberDAO에 삭제. boolean
      boolean isOK = true;
      if(isOK) {
    	  // {"retCode":"ok"}
    	  resp.getWriter().print("{\"retCode\":\"OK\"}");
      } else {
    	  // {"retCode" : "NG"
    	  resp.getWriter().print("{\"retCode\":\"NG\"}");

      }
	}

}
