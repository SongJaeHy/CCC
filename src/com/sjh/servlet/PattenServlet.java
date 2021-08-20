package com.sjh.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sjh.service.IMemberService;
import com.sjh.service.MemberDetailService;
import com.sjh.service.MemberJoinService;
import com.sjh.service.MemberListService;
import com.sjh.service.MemberLoginService;
import com.sjh.service.MemberUpdateService;




/**
 * Servlet implementation class PattenServlet
 */
@WebServlet("*.do")
public class PattenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PattenServlet() {
        super();
        System.out.println("������ ����");
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		System.out.println("������ ����");
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		System.out.println("���� �Ҹ�");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		doRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doRequest(request, response);
	}
	protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IMemberService sv = null;
		
		// �ش� ������ ���� �ڿ� �Ѿ .jsp ���� ����
		String ui = null;
		
		// ���� ���� ��
		HttpSession session = null;
		session = request.getSession();
		
		// doget�� �ִ� ��� �ڵ带 �����ɳ���.
		// Ȯ���� ���Ͽ��� Ȯ���ڸ� ������ �ּҰ��� �������� ���ؼ�
		// �Ʒ� �ڵ带 ����մϴ�.
		String uri = request.getRequestURI();	
		System.out.println("uri ����:" + uri);
		
		// �ܼ��� �ƴ� ����ڰ� Ȯ���� �� �ֵ��� .jsp ȭ�鿡
		// ������ ��� �ڷḦ ��� out.print(); ����� ����
		// ���� �غ�
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		// jsp�������� html�������� �̷��� ������ �˷��ִ� �ڵ�
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		
		if(uri.equals("/ccs/memberjoin.do")) {
			sv = new MemberJoinService();
			sv.execute(request, response);
			ui="/member/member_login_form.jsp";
		}else if(uri.equals("/ccs/login.do")) {
			// 1. ���� ��ü ����
			sv = new MemberLoginService();
			// 2. ���� �޼��� ����
			sv.execute(request, response);
			String result=(String)session.getAttribute("login");
			System.out.println(result);
			if(result != null&&result.equals("fail")) {
				session.invalidate();
				ui = "/member/member_login_form.jsp";
			}else if(result.equals("success")) {
				ui = "/member/member_login_ok.jsp";
			}
		}else if(uri.equals("/ccs/update.do")){
			
		}else if(uri.equals("/ccs/updateok.do")) {
			sv = new MemberUpdateService();
			sv.execute(request, response);
			
			String strmId = request.getParameter("mId");
			ui = "/memberdetail.do?mId=" + strmId;
		}else if(uri.equals("/ccs/delete.do")) {
			
		}else if(uri.equals("/ccs/memberdetail.do")) {
			sv = new MemberListService();
			sv.execute(request, response);
			
			ui = "/member/member_get_all.jsp";
		
		}else if(uri.equals("/ccs/memberselect.do")) {
			//sv = new MemberDetailService();
			sv.execute(request, response);
			
			ui = "/member/member_get_all.jsp";
		
		}
		
		
		else {
			System.out.println("�߸��� �����Դϴ�.");
		}
		
		
		
		RequestDispatcher dp = request.getRequestDispatcher(ui);
		dp.forward(request, response);
	}
}
