package com.sjh.service;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sjh.model.MemberDAO;
import com.sjh.model.MemberVO;



public class MemberDetailService implements IMemberService{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = null;
		session = request.getSession();
		String idSession = (String) session.getAttribute("i_s");

		if (idSession == null) {
			try {
				// 서비스 내부에서 포워딩을 시키면
				// 리다이렉트가 아니기 때문에 실행됨
				String ui = "/member/member_login_form.jsp";
				RequestDispatcher dp = request.getRequestDispatcher(ui);
				dp.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			// url에 묻어온 글 번호를 getParameter를 이용해 얻습니다.
			String m_Id = request.getParameter("m_id");
	// DAO를 생성합니다.
			MemberDAO dao = MemberDAO.getinstance();
			
			MemberVO member = dao.getBoardDetail(m_Id);

			// 포워딩을 위해 setAttribute()로 데이터를 실어줍니다.
			request.setAttribute("member", member);
		}
	}
}
