package com.ctlovedove.util;

public class Pager {
	private int pageIndex = 1;// 当前页
	private int totalCount = 0;// 总记录数
	private int pageSize = 10;// 每页显示条数
	private int totalPage = 1;// 总页数
	private int tagNum = 10;// 默认显示的分页标签数量
	private String pagerStr;// 分页字符串
	private String pagerStr2;

	public String getPagerStr() {
		String pagerStr = "";

		totalPage = getTotalPage();

		pagerStr += "<li><a>共"+totalPage+"页</a></li>";
		
		//首页
		if(pageIndex != 1){
			pagerStr += "<li><a href='list.do?pageIndex=1&pageSize=" + pageSize + "'>首页</a></li>";
		}else{
			pagerStr += "<li><a>首页</a></li>";
		}
		
		// 上一页
		if (pageIndex > 1) {
			pagerStr += "<li><a href='list.do?pageIndex=" + (pageIndex - 1)
					+ "&pageSize=" + pageSize + "'>上一页</a></li>";
		} else {
			pagerStr += "<li><a>上一页</a></li>";
		}

		int startIndex = 1;
		int scrollPage = 0;
		/*if (pageIndex % tagNum == 0 && pageIndex - 2 > 0) {
			startIndex = pageIndex - 2;
			if (startIndex + tagNum - 1 < totalPage) {
				scrollPage = startIndex + tagNum - 1;
			} else {
				scrollPage = totalPage;
			}
		} else {
			startIndex = (pageIndex / tagNum) * tagNum + 1;
			if (pageIndex < tagNum) {
				scrollPage = tagNum;
			} else if (startIndex + tagNum - 1 < totalPage) {
				scrollPage = startIndex + tagNum - 1;
			} else {
				scrollPage = totalPage;
			}
		}*/
		
		if(pageIndex % tagNum != 0){
			startIndex = (pageIndex / tagNum) * tagNum + 1;
		}else {
			startIndex = (pageIndex / tagNum - 1) * tagNum + 1;
		}
		
		if (startIndex + tagNum - 1 < totalPage) {
			scrollPage = startIndex + tagNum - 1;
		} else {
			scrollPage = totalPage;
		}
		
		// 显示标签
		for (int i = startIndex; i <= scrollPage; i++) {
			if (pageIndex == i) {// 当前页
				pagerStr += "<li><a><font color='red'>" + i
						+ "</font></a></li>";
			} else if (i < totalPage) {// 既不是当前页也不是最后一页
				pagerStr += "<li><a href='list.do?pageIndex=" + i
						+ "&pageSize=" + pageSize + "'>" + i + "</a></li>";
			} else if (i == totalPage && pageIndex != totalPage) {// 最后一页
				pagerStr += "<li><a href='list.do?pageIndex=" + i
						+ "&pageSize=" + pageSize + "'>" + i + "</a></li>";
			} else {
				pagerStr += "<li><a>" + i + "</a></li>";
			}
		}

		// 下一页
		if (pageIndex < totalPage) {
			pagerStr += "<li><a href='list.do?pageIndex=" + (pageIndex + 1)
					+ "&pageSize=" + pageSize + "'>下一页</a></li>";
		} else {
			pagerStr += "<li><a>下一页</a></li>";
		}
		
		//尾页
		if(pageIndex != totalPage){
			pagerStr += "<li><a href='list.do?pageIndex="+totalPage+"&pageSize=" + pageSize + "'>尾页</a></li>";
		}else{
			pagerStr += "<li><a>尾页</a></li>";
		}

		return "<ul>" + pagerStr + "</ul>";
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		totalCount = getTotalCount();
		pageSize = getPageSize();
		totalPage = (totalCount % getPageSize()) == 0 ? (totalCount / pageSize)
				: (totalCount / pageSize + 1);
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public void setPagerStr(String pagerStr) {
		this.pagerStr = pagerStr;
	}

	public String getPagerStr2() {
		String pagerStr = "";

		totalPage = getTotalPage();

		pagerStr += "<li><a>共"+totalPage+"页</a></li>";
		
		//首页
		if(pageIndex != 1){
			pagerStr += "<li><a href='javascript:getPage(1);'>首页</a></li>";
		}else{
			pagerStr += "<li><a>首页</a></li>";
		}
		
		// 上一页
		if (pageIndex > 1) {
			pagerStr += "<li><a href='javascript:getPage("+(pageIndex - 1)+");'>上一页</a></li>";
		} else {
			pagerStr += "<li><a>上一页</a></li>";
		}

		int startIndex = 1;
		int scrollPage = 0;
		
		if(pageIndex % tagNum != 0){
			startIndex = (pageIndex / tagNum) * tagNum + 1;
		}else {
			startIndex = (pageIndex / tagNum - 1) * tagNum + 1;
		}
		
		if (startIndex + tagNum - 1 < totalPage) {
			scrollPage = startIndex + tagNum - 1;
		} else {
			scrollPage = totalPage;
		}
		
		// 显示标签
		for (int i = startIndex; i <= scrollPage; i++) {
			if (pageIndex == i) {// 当前页
				pagerStr += "<li><a><font color='red'>" + i
						+ "</font></a></li>";
			} else if (i < totalPage) {// 既不是当前页也不是最后一页
				pagerStr += "<li><a href='javascript:getPage("+i+");'>" + i + "</a></li>";
			} else if (i == totalPage && pageIndex != totalPage) {// 最后一页
				pagerStr += "<li><a href='javascript:getPage("+i+");'>" + i + "</a></li>";
			} else {
				pagerStr += "<li><a>" + i + "</a></li>";
			}
		}

		// 下一页
		if (pageIndex < totalPage) {
			pagerStr += "<li><a href='javascript:getPage("+(pageIndex + 1)+");'>下一页</a></li>";
		} else {
			pagerStr += "<li><a>下一页</a></li>";
		}
		
		//尾页
		if(pageIndex != totalPage){
			pagerStr += "<li><a href='javascript:getPage("+(totalPage)+");'>尾页</a></li>";
		}else{
			pagerStr += "<li><a>尾页</a></li>";
		}

		return "<ul>" + pagerStr + "</ul>";
	}

	public void setPagerStr2(String pagerStr2) {
		this.pagerStr2 = pagerStr2;
	}
	

}
