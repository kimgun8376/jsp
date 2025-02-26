package come.yedam.control;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import come.yedam.Control;
import come.yedam.dao.ReplyDAO;

public class ChartData implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ReplyDAO rdao = new ReplyDAO();
		List<Map<String, Object>> list = rdao.chartData();

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(list);
 
		  resp.getWriter().print(json);
		
	}

}
