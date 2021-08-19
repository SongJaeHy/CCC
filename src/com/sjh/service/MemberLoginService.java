package com.sjh.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sjh.model.MembersDAO;
import com.sjh.model.MembersVO;

public class MemberLoginService implements IMemberService{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			
			// 한긓깨짐 방지
			response.setCharacterEncoding("utf-8");
			request.setCharacterEncoding("utf-8");
			// 회원가입 폼에서 던진 파라미터 가져오기
			
			String m_id = request.getParameter("m_id");
			String m_pw = request.getParameter("m_pw");
	
			MembersDAO dao = MembersDAO.getinstance();
			
			MembersVO member = new MembersVO();
		
			member.setM_Id(m_id);
			member.setM_Pw(m_pw);
		
			dao.joinMember(member);
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
}
