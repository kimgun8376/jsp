package come.yedam.common;

public class PageVO {
     // 67건. 1 ~ 14페이지.
	// 현재페이지 : 2페이지. 1~10페이지.
	private int startPage; // 첫페이지.
	private int endPage;
	private int currentPage;
	private boolean prev, next; // 이전,이후 페이지의 여부.
    private int totalCnt; // 전체 게시글 수
    private int pageSize = 10; // 페이지당 게시글 수 (10으로 가정)
    // 생성자
    public PageVO(int page, int totalCnt) {
        this.totalCnt = totalCnt;
        this.currentPage = page;

        // endPage 계산
        endPage = (int) Math.ceil(currentPage / (double) pageSize) * pageSize;
        startPage = endPage - pageSize + 1; // 계산상의 start, end 페이지

        // 실제 마지막 페이지
        int realEnd = (int) Math.ceil(totalCnt / (double) pageSize);

        // endPage가 실제 마지막 페이지를 넘지 않도록 조정
        endPage = endPage > realEnd ? realEnd : endPage;

        // 이전 페이지 여부
        prev = startPage == 1 ? false : true;

        // 이후 페이지 여부
        next = endPage * pageSize >= totalCnt ? false : true;
    }

    // Getter Methods
    public int getStartPage() {
        return startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public boolean isPrev() {
        return prev;
    }

    public boolean isNext() {
        return next;
    }
}
