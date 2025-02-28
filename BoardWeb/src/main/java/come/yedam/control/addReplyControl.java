package come.yedam.control;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import come.yedam.Control;
import come.yedam.common.DataSource;
import come.yedam.dao.ReplyDAO;
import come.yedam.mapper.ReplyMapper;
import come.yedam.vo.ReplyVO;

public class addReplyControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/json;charset=utf-8");
		// 댓글내용, 작성자, 원본글번호.
		String reply = req.getParameter("reply");
		String replyer = req.getParameter("replyer");
		String bno = req.getParameter("bno");

		// 매개값.
		ReplyVO rvo = new ReplyVO();
		rvo.setBoardNo(Integer.parseInt(bno));
		rvo.setReply(reply);
		rvo.setReplyer(replyer);
		rvo.setReplyDate(new Date()); 
		// Fri Feb 28 14:14:17 KST 2025
        // Feb 28 2025

		// DB반영.
//		ReplyDAO rdao = new ReplyDAO();
//		boolean run = rdao.insertReply(rvo);

		SqlSession sqlSession = DataSource.getInstance().openSession();
		ReplyMapper mapper = sqlSession.getMapper(ReplyMapper.class);
		boolean run = mapper.insertReply(rvo) == 1;

		// 결과값.
		Map<String, Object> result = new HashMap<>();

		if (run) {
			// {"retCode" : "OK"}
			// resp.getWriter().print("{\"retCode\" : \"OK\"}");
			result.put("retCode", "OK");
			result.put("retVal", rvo);
			sqlSession.commit(true);
			
		} else {
			// resp.getWriter().print("{\"retCode\" : \"NG\"}");
			result.put("retCode", "NG");
		}

		// json 생성.
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(result);

		resp.getWriter().print(json);
	}

}
