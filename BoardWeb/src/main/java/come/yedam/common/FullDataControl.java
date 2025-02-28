package come.yedam.common;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import come.yedam.Control;
import come.yedam.mapper.ReplyMapper;

public class FullDataControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(null); 
        
        // DB
       SqlSession sqlSession = DataSource.getInstance().openSession();
    	ReplyMapper mapper =sqlSession.getMapper(ReplyMapper.class);
     List<Map<String, Object>> list = mapper.fullData();
     
       
        // 웹.출.
     Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(list);
		  // Send the JSON response
	    resp.getWriter().print(json);
	}

	
}
