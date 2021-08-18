package com.sjh.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hgs.dept.model.DeptVO;
import com.sjh.model.MembersDAO;

public class MemberJoinService implements IMemberService{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		MembersDAO dao = MembersDAO.getinstance();
		List<DeptVO> deptlist = dao.getDept();
		request.setAttribute("deptlist", deptlist);
	}

}
