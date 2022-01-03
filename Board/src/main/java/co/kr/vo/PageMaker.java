package co.kr.vo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class PageMaker {

	private int totalCount; //총 게시물 수
	private int startPage; // 시작페이지 값
	private int endPage; // 마지막페이지 값
	private boolean prev; // 이전버튼
	private boolean next; // 다음버튼
	private int displayPageNum = 10; //블럭의 수
	
	//JSP에서 표현할 페이징 HTML코드를 저장할 곳!
	private Criteria cri; 

	public void setCri(Criteria cri) {
		this.cri = cri;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calcData();
	}

	public int getTotalCount() {
		return totalCount;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public boolean isNext() {
		return next;
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public Criteria getCri() {
		return cri;
	}

	private void calcData() {
		endPage = (int) (Math.ceil(cri.getPage() / (double) displayPageNum) * displayPageNum);
		startPage = (endPage - displayPageNum) + 1;
		
		// 전체게시물의 수를 =통해 전체 페이지값 구하기
		int tempEndPage = (int) (Math.ceil(totalCount / (double) cri.getPerPageNum()));
		
		// 마지막페이지의 값이 전체페이지의 값보다 크다면
				// 마지막페이지 값을 전체페이지 값으로 지정
		if (endPage > tempEndPage) {
			endPage = tempEndPage;
		}
		// 이전기능 가능여부 확인
		prev = startPage == 1 ? false : true;
		
		
		// 다음기능 가능여부 확인
		next = endPage * cri.getPerPageNum() >= totalCount ? false : true;
	}

	//perPageNum 파라미터로 전송??
	public String makeQuery(int page) {
		UriComponents uriComponents = 
				UriComponentsBuilder.newInstance().queryParam("page", page).
					queryParam("perPageNum", cri.getPerPageNum()).build();
		return uriComponents.toUriString();
	}
	
	public String makeSearch(int page) {
		UriComponents uriComponents = 
				UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("perPageNum", cri.getPerPageNum())
				.queryParam("searchType", ((SearchCriteria) cri).getSearchType())
				.queryParam("keyword", encoding(((SearchCriteria) cri).getKeyword())).build();
		return uriComponents.toUriString();
	}

	private String encoding(String keyword) {
		if (keyword == null || keyword.trim().length() == 0) {
			return "";
		}
		try {
			return URLEncoder.encode(keyword, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

}
