package com.sjh.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sjh.model.MemberDAO;
import com.sjh.model.MemberVO;

public class MemberUpdateService implements IMemberService{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String mPw = request.getParameter("m_pw");
		String mName = request.getParameter("m_name");
		String mEmail = request.getParameter("m_email");
		String mId = request.getParameter("m_id");
		
		MemberVO member = new MemberVO();
		member.setM_Pw(mPw);
		member.setM_Name(mName);
		member.setM_Email(mEmail);
		member.setM_Id(mId);
		
		MemberDAO dao = MemberDAO.getinstance();
		
		dao.UpdateMember(member);
		
	}

}
