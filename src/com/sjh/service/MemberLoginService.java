package com.sjh.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sjh.model.MembersDAO;
import com.sjh.model.MembersVO;

public class MemberLoginService implements IMemberService{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			
			// �уR���� ����
			response.setCharacterEncoding("utf-8");
			request.setCharacterEncoding("utf-8");
			// ȸ������ ������ ���� �Ķ���� ��������
			
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
