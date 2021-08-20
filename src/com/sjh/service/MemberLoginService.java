package com.sjh.service;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sjh.model.MemberDAO;
import com.sjh.model.MemberVO;


public class MemberLoginService implements IMemberService{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			String m_Id = request.getParameter("m_Id");
			String m_Pw = request.getParameter("m_Pw");
			
			HttpSession session = null;
			session = request.getSession();
			String idSession = (String) session.getAttribute("i_s");
			
			// 한글깨짐 방지
			response.setCharacterEncoding("utf-8");
			request.setCharacterEncoding("utf-8");
			// 회원가입 폼에서 던진 파라미터 가져오기
			
			String m_id = request.getParameter("m_id");
			String m_pw = request.getParameter("m_pw");
	
			MemberDAO dao = MemberDAO.getinstance();
			int resultCode = dao.login(m_id, m_pw);
			if(resultCode == 1) {
				session.setAttribute("i_s", m_Id);
				session.setAttribute("p_s", m_Pw);
				session.setAttribute("login", "success");
			}else if(resultCode==0) {
				session.setAttribute("login", "fail");
			}
			
			MemberVO member = new MemberVO();
			
		
			member.setM_Id(m_id);
			member.setM_Pw(m_pw);
			dao.login(m_id, m_pw);
			
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
}
