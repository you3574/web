package net.skhu.model;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Pagination {

    int now = 1;  // 현재 페이지
    int size = 15; // 페이지 당 레코드 수
    int category;      // 분류 검색
    String searchWord;   // 검색어
    int orderBy;      //정렬순서
    int recordCount; //전체학생수

    public String getQueryString() {
        String url = null;
        try {
            String temp = (searchWord == null) ? "" : URLEncoder.encode(searchWord, "UTF-8");
            url = String.format("now=%d&sz=%d&ob=%d&cg=%d&word=%s", now, size, orderBy,category , temp);
            //현재페이지와 페이지당 레코드 수를 전달 어떻게 정렬했고 어떤 카테고리로 검색했는지 전달
        } catch (UnsupportedEncodingException e) { }
        return url;
    }

	public int getNow() {
		return now;
	}

	public void setNow(int now) {
		this.now = now;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	public int getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

}
