package com.sjh.service;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sjh.model.MemberDAO;
import com.sjh.model.MemberVO;
import com.sjh.model.MemberPageDTO;

public class MemberPagingService implements IMemberService {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String strPage = request.getParameter("page");
		
		int page = 1;
		if(strPage != null) {
			page = Integer.parseInt(strPage)
;		}
		MemberDAO dao = MemberDAO.getinstance();
		
		//  현재 보고 있는 페이지의 전체 글 가져오가
		ArrayList<MemberVO> memberList = dao.getPageList((page-1)*10);
		System.out.println("페이징 글:" + memberList);
		
		int CountNum = dao.getMemberCount();
		System.out.println("글 전체 개수 :"+ CountNum);
		
		MemberPageDTO pageDTO = new MemberPageDTO(CountNum,page,memberList);
		System.out.println("링크버튼 정보: " + pageDTO);
		
		request.setAttribute("memberList", memberList);
		request.setAttribute("psgeDTO", pageDTO);
	}
}
