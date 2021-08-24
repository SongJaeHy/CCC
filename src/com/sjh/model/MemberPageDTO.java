package com.sjh.model;
import java.util.ArrayList;
//import java.util.List;
public class MemberPageDTO {
	private int total;  // �전체
	private int currentPage; // 현재보고있는페이지
	private ArrayList<MemberVO> memberList;
	private int totalPages; // 전체
	private int startPage; // 시작
	private int endPage; // 끝


	public MemberPageDTO(int total, int currentPage,ArrayList<MemberVO> memberList) {
		this.total = total;
		this.currentPage = currentPage;
		this.memberList = memberList;
		// 아래부터 위 3개를 토대로 가공해서 나머지 변수를 채웁니다.
		// 글이 없는 경우 페이지 및 버튼 자체가 필요없음
		if(total == 0) {
			this.totalPages=0;
			this.startPage =0;
			this.endPage =0;
		}else {
		// 게시글 총 개수를 이용해 전체 페이지 개수부터 구하기
		this.totalPages = total/10;
		if(this.total % 5 >0) {
			// 만약 나눴을때 5개로 딱떨어지지 않으면
			// 마지막에 페이지를 하나 더 추가해야 함
			this.totalPages += 1;
		}
		// 현재 보고 있는 페이지 그룹 시작 번호
		int moVal = this.currentPage % 10;
		this.startPage = this.currentPage/ 10 * 10+1;

		if(moVal ==0) {
			this.startPage -= 10;
		}
		// 끝 페이지 보기 위한 곳
		endPage = startPage + (10-1);
		if(endPage>totalPages) {
			endPage = totalPages;
		}
	}// end constructor
	}
	public int getTotal() {
		return total;
	}
	public boolean hasNoMember() {
		return total ==0;
	}
	public boolean hasMember() {
		return total > 0;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public ArrayList<MemberVO> getMemberList(){
		return memberList;
	}
	public int getStartPage() {
		return startPage;
	}
	public int getEndPage() {
		return endPage;
	}

}
