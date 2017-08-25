package kr.hm.board.vo;

public class PageResultVO {
	private int listSize = 20;
	private int tabSize = 10;
	private int pageNo;
	private int count;

	private int lastPage;
	private int beginPage;
	private int endPage;
	
	private boolean prev, next;
	
	public PageResultVO(int pageNo, int count) {
		this.pageNo = pageNo;
		this.count = count;
		this.lastPage = (int)Math.ceil(count / (double)listSize);
		
		int currTab = (pageNo - 1) / tabSize + 1;
		this.beginPage = (currTab - 1) * tabSize + 1;
		this.endPage = (currTab * tabSize > lastPage) ? lastPage : currTab * tabSize;
		this.prev = beginPage != 1;
		this.next = endPage != lastPage;
	}
	
	public int getListSize() {
		return listSize;
	}
	public int getPageNo() {
		return pageNo;
	}

	public int getCount() {
		return count;
	}

	public int getBeginPage() {
		return beginPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public boolean getPrev() {
		return prev;
	}

	public boolean getNext() {
		return next;
	}
}
