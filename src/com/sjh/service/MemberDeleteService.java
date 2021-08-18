package com.sjh.service;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sjh.model.MembersDAO;



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
			// bId �Ķ���� �ޱ�(������ ���������� post������� ������) �ޱ�
			String mId = request.getParameter("mId");

			// DAO����
			MembersDAO dao = MembersDAO.getinstance();
			// delete������ mId �־ ����
			dao.DeleteMember(null, mId);
		
		}
	}
}
