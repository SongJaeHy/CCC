package com.sjh.service;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sjh.model.MemberDAO;





public class MemberDeleteService implements IMemberService{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = null;
		session = request.getSession();
		String idSession = (String) session.getAttribute("i_s");

		if (idSession == null) {
			try {
				String ui = "/member/member_login_form.jsp";
				RequestDispatcher dp = request.getRequestDispatcher(ui);
				dp.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			// bId 파라미터 받기(디테일 페이지에서 post방식으로 날려준) 받기
			String mId = request.getParameter("mId");

			// DAO생성
			MemberDAO dao = MemberDAO.getinstance();
			// delete로직에 mId 넣어서 실행
			dao.DeleteMember(null, mId);
		
		}
	}
}
