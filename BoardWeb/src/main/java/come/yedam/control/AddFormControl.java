package come.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import come.yedam.Control;

public class AddFormControl implements Control {
	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp)
	    throws ServletException, IOException{
      
		
		//글동록화면 요청재지정.
		req.getRequestDispatcher("board/addForm.tiles").forward(req, resp);
	}
}
