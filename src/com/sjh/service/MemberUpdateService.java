package com.sjh.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sjh.model.MembersDAO;
import com.sjh.model.MembersVO;

public class MemberUpdateService implements IMemberService{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String mPw = request.getParameter("m_pw");
		String mName = request.getParameter("m_name");
		String mEmail = request.getParameter("m_email");
		String mId = request.getParameter("m_id");
		
		MembersVO member = new MembersVO();
		member.setM_Pw(mPw);
		member.setM_Name(mName);
		member.setM_Email(mEmail);
		member.setM_Id(mId);
		
		MembersDAO dao = MembersDAO.getinstance();
		
		dao.updateMember(member);
		
	}

}
