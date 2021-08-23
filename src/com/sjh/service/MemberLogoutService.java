package com.sjh.service;

import javax.servlet.http.*;

public class MemberLogoutService implements IMemberService{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = null;
		session = request.getSession();
		
		session.invalidate();
	}
}
