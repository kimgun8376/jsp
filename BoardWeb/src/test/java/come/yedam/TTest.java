package come.yedam;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import come.yedam.dao.ReplyDAO;

public class TTest {
	public static void main(String[] args) {
		System.out.println("srar");
		ReplyDAO rdao = new ReplyDAO();
		List<Map<String, Object>> list = rdao.chartData();

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(list);

		System.out.println(json);
	}
}
