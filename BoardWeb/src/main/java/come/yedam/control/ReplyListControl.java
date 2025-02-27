package come.yedam.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import come.yedam.Control;
import come.yedam.dao.ReplyDAO;
import come.yedam.vo.ReplyVO;

public class ReplyListControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/json;charset=utf-8");
		// 원본글 번호.
		String bno = req.getParameter("bno");
		String page = req.getParameter("page");

		 System.out.print(bno);
		 System.out.print(page);


		// DAO 활용.
		ReplyDAO rdao = new ReplyDAO();
		List<ReplyVO> list = rdao.replyList(Integer.parseInt(bno),Integer.parseInt(page));

		// gson 활용
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(list); // 자바객체 -> json문자열.
		
		System.out.println(json); //콘솔.
		resp.getWriter().print(json);//웹브라우저.
	}
}
