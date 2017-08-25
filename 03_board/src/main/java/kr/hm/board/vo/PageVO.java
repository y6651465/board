package kr.hm.board.vo;

public class PageVO {
	private int pageNo = 1;
	private int listSize = 20;
	private String whatSearch = "";
	private String wantSearch = "";
	public String getWantSearch() {
		return wantSearch;
	}
	public void setListSize(int listSize) {
		this.listSize = listSize;
	}
	public String getWhatSearch() {
		return whatSearch;
	}
	public void setWhatSearch(String whatSearch) {
		this.whatSearch = whatSearch;
	}
	
	public void setWantSearch(String wantSearch) {
		this.wantSearch = wantSearch;
	}
	public int getBegin() {
		return (pageNo -1) * 10;
	}
	public int getEnd() {
		return pageNo * 10;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getListSize() {
		return listSize;
	}

	
}
