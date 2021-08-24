package com.sjh.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sjh.model.MemberDAO;
import com.sjh.model.MemberVO;


public class MemberLoginService implements IMemberService{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setCharacterEncoding("utf-8");
			request.setCharacterEncoding("utf-8");

			String m_id = request.getParameter("m_id");
			String m_pw = request.getParameter("m_pw");
			//세션
			HttpSession session = null;
			session = request.getSession();
			String idSession = (String) session.getAttribute("i_s");

			MemberDAO dao = MemberDAO.getinstance();
			int resultCode = dao.login(m_id, m_pw);
			if(resultCode == 1) {
				session.setAttribute("i_s", m_id);
				session.setAttribute("p_s", m_pw);
				session.setAttribute("login", "success");
			}else if(resultCode==0) {
				session.setAttribute("login", "fail");
			}

			MemberVO member = new MemberVO();

			member.setM_id(m_id);
			member.setM_pw(m_pw);
			dao.login(m_id, m_pw);


		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
