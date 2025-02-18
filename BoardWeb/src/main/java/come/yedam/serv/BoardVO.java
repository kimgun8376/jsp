package come.yedam.serv;

import java.util.Date;

public class BoardVO { //tbl_board
     private int boardNo; // board_no
     //title......view_cnt 
     private String title;
     private String content;
     private String writer;
     private Date writeDate;
     private int viewCnt;
	
     public int getBoardNo() {
		return boardNo;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public String getWriter() {
		return writer;
	}
	public Date getWriteDate() {
		return writeDate;
	}
	public int getViewCnt() {
		return viewCnt;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}
	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}
	@Override
	public String toString() {
		return "BoardVO [boardNo=" + boardNo + ", title=" + title + ", content=" + content + ", writer=" + writer
				+ ", viewCnt=" + viewCnt + "]";
	}

     



}
