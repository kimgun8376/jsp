package come.yedam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Control {
   public void exec(HttpServletRequest req, HttpServletResponse resp); 	   
   
}
