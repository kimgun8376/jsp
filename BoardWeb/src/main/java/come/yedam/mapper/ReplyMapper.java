package come.yedam.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import come.yedam.vo.ReplyVO;


public interface ReplyMapper {
    public int insertReply(ReplyVO reply);
    public List<ReplyVO> replyList(@Param("boardNo") int boardNo, @Param("page") int page);
    public List<ReplyVO> replyListAll(int boardNo);
 
}
