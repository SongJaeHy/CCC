package com.sjh.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sjh.model.MemberDAO;
import com.sjh.model.MemberVO;

public class MemberListService implements IMemberService{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = null;
		session = request.getSession();
		String idSession = (String) session.getAttribute("i_s");

		if (idSession == null) {
			try {
				request.setCharacterEncoding("utf-8");
				response.setCharacterEncoding("utf-8");
				// 서비스 내부에서 포워딩을 시키면
				// 리다이렉트가 아니기 때문에 실행됨
				String ui = "/member/member_login_form.jsp";
				RequestDispatcher dp = request.getRequestDispatcher(ui);
				dp.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			// DAO생성
			MemberDAO dao = MemberDAO.getinstance();
			//전체 사원 리스트 가져오기
			ArrayList<MemberVO> memberList = dao.getAllMember();
			
			// 받아온 리스트를 .jsp에 전달하기.
			// request에 데이터를 실어놔야 합니다.
			// request.setAttribute("명칭", 데이터);
			request.setAttribute("memberList", memberList);
		}
	}

}
