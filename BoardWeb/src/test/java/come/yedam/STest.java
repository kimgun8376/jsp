package come.yedam;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import come.yedam.common.DataSource;
import come.yedam.mapper.ReplyMapper;

public class STest {
    public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("Mon dd, yyyy, hh:mm:ss ");
    	System.out.println(sdf.format(new Date()));
    	
    	SqlSessionFactory sqlSessionFactory = DataSource.getInstance();
    	SqlSession  sqlSession = sqlSessionFactory.openSession();
    	
    	ReplyMapper mapper = sqlSession.getMapper(ReplyMapper.class);
    	List<Map<String, Object>> list = mapper.fullData();
    	
    	Gson gson = new GsonBuilder().setPrettyPrinting().create();
    	System.out.println(gson.toJson(list));
    			
    			
    	
	}
}
