package come.yedam.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import come.yedam.vo.ReplyVO;


public interface ReplyMapper {

    public int insertReply(ReplyVO reply);
    public List<ReplyVO> replyList(@Param("boardNo") int boardNo, @Param("page") int page);
    public List<ReplyVO> replyListAll(int boardNo);
    
    public List<Map<String,Object>> fullData();
    public int insertEvent(@Param("title")String title//
    		, @Param("start") String start//
    		, @Param("end") String end );
    
    //삭제.
    public int deleteEvent(@Param("title")String title//
    		, @Param("start") String start//
    		, @Param("end") String end );
 
}
