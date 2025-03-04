package come.yedam.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import come.yedam.Control;
import come.yedam.mapper.ReplyMapper;

public class RemoveData implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// param(title, start, end)
		String title = req.getParameter("title");
		String start = req.getParameter("start");
		String end = req.getParameter("end");

		SqlSession sqlSeesion = DataSource.getInstance().openSession(true);
	    ReplyMapper mapper = sqlSeesion.getMapper(ReplyMapper.class);
	    int row =  mapper.deleteEvent(title, start, end);
	  
	    if (row == 1) {
	    	// {"retCode" : "OK"
	    	resp.getWriter().print("{\"retCode\" : \"OK\"}");
	    } else {
	    	resp.getWriter().print("{\"retCode\" : \"NG\"}");
	    }
	} 

}
