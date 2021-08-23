package com.sjh.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;



public class MemberDAO {
	private DataSource ds;

	private MemberDAO() {
		try {
			Context ct = new InitialContext();
			ds = (DataSource)ct.lookup("java:comp/env/jdbc/mysql");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	private static MemberDAO dao = new MemberDAO();

	public static MemberDAO getinstance() {
		if(dao==null) {
			dao = new MemberDAO();
		}
		return dao;
	}
	// 회원가입 호직
	public void joinMember(MemberVO member) {
		// DB������ ���� Connector ����
		// Connection ��ü ����
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();

			String sql = "INSERT INTO (m_id,m_pw,m_name,dept_no,m_phone,m_email)"
					+ "VALUES(?,?,?,?,?,?)";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getM_Id());
			pstmt.setString(2, member.getM_Pw());
			pstmt.setString(3, member.getM_Name());
			pstmt.setInt(4, member.getDept_no());
			pstmt.setString(5, member.getM_Phone());
			pstmt.setString(6, member.getM_Email());
			pstmt.executeUpdate();

		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(con!=null && !con.isClosed()) {
					con.close();
				}
				if(pstmt!=null && !pstmt.isClosed()) {
					pstmt.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}// end joinMember

	// 사원 수정 로직
	public int UpdateMember(MemberVO member) {
		Connection con = null;
		PreparedStatement pstmt =null;

		try {
			con = ds.getConnection();
			String sql = "UPDATE member SET m_pw=?, m_name=?, m_email=? WHERE m_id=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getM_Pw());
			pstmt.setString(2, member.getM_Name());
			pstmt.setString(3, member.getM_Email());
			pstmt.setString(4, member.getM_Id());
			pstmt.executeUpdate();

		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(con!=null&&!con.isClosed()) {
					con.close();
				}
				if(pstmt!=null&&!pstmt.isClosed()) {
					pstmt.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	return 1;
	}// end UpdateMember

	// 사원 삭제 로직
	public int DeleteMember(MemberVO member, String dpw) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
				if(member.getM_Pw().equals(dpw)) {
			con = ds.getConnection();
			String sql = "DELETE FROM m_pw WHERE m_id=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getM_Id());

			pstmt.executeUpdate();

				}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(con!=null && !con.isClosed()) {
					con.close();
				}
				if(pstmt!=null&&!pstmt.isClosed()) {
					pstmt.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	return 0;
	} // end DeleteMember
	// 사원 로그인
	public int login(String m_id, String m_pw) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		MemberVO userinfo = new MemberVO();

		String sql = "SELECT m_pw FROM member WHERE m_id=?";
		try {
			con = ds.getConnection();

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, m_id);
			userinfo.getM_Name();
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).equals(m_pw)) {
					return 1;
				}else {
					return 0;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(con!=null && !con.isClosed()) {
					con.close();
				}
				if(pstmt!=null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if(rs!=null && !rs.isClosed()) {
					rs.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return 0;
	}// end login
	// 수정 로직 사용하기 전 수정할 타겟 아이디 정보 얻어오기.
	public MemberVO getMemberInfo(MemberVO member) {
		Connection con = null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;

		MemberVO resultData = new MemberVO();
		try {
			con = ds.getConnection();

			String sql= "SELECT * FROM member WHERE m_id=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getM_Id());

			rs = pstmt.executeQuery();
			if(rs.next()) {
				resultData.setM_Id("m_Id");
				resultData.setM_Pw("m_PW");
				resultData.setM_Email("m_Email");
				resultData.setM_Phone("m_Phone");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(con!=null && !con.isClosed()) {
					con.close();
				}
				if(pstmt!=null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if(rs!=null && !rs.isClosed()) {
					rs.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return resultData;
	}
	// 사원 전체 목록
	public ArrayList<MemberVO> getAllMember(){
		ArrayList<MemberVO> memberList = new ArrayList<>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM member ORDER BY m_id DESC";
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while(rs.next()) {
				MemberVO member = new MemberVO();

				member.setM_Id(rs.getString("m_id"));
				member.setM_Pw(rs.getString("m_pw"));
				member.setM_Name(rs.getString("m_name"));
				member.setDept_no(rs.getInt("dept_no"));
				member.setM_Phone(rs.getString("m_phone"));
				member.setM_Email(rs.getString("m_email"));

				memberList.add(member);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(con!=null&&!con.isClosed()) {
					con.close();
				}
				if(pstmt!=null&&!pstmt.isClosed()) {
					pstmt.close();
				}
				if(pstmt!=null&&!pstmt.isClosed()) {
					pstmt.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return memberList;
	} // end getallData
	// 사원 하나에 대한 상세 정보를 가져오는 로직
	public MemberVO getBoardDetail(String m_Id) {
		//
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO member = new MemberVO();

		String sql = "SELECT * FROM member WHERE m_id=?";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, m_Id);
			rs = pstmt.executeQuery();

		if(rs.next()) {
			member.setM_Id(rs.getString("m_id"));
			member.setM_Name(rs.getString("m_name"));
			member.setDept_no(rs.getInt("dept_no"));
			member.setM_Phone(rs.getString("m_phone"));
			member.setM_Email(rs.getString("m_email"));

		};

	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		try {
			if(con!=null && !con.isClosed()) {
				con.close();
			}
			if(pstmt!=null && !pstmt.isClosed()) {
				pstmt.close();
			}
			if(rs!=null && !rs.isClosed()) {
				rs.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
		return member;

	}
	// id중복 체크
	public boolean idCheck(String m_Id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM member WHERE m_id=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, m_Id);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				String id = rs.getString("id");
				System.out.printf("%s:아이디존재!\n", m_Id);
				return false;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(con!=null && !con.isClosed()) {
					con.close();
				}
				if(pstmt!=null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if(rs!=null && !rs.isClosed()) {
					rs.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return true;
	} //end idCheck
	// 페이지 번호에 맏는 게시물 가져오기
	public ArrayList<MemberVO> getPageList(int pageNum){
		//
		ArrayList<MemberVO> memberList = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// 쿼리문
		String sql = "SELECT * FROM member ORDER BY m_id DESC LIMIT ?, 5";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, pageNum);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				MemberVO member = new MemberVO();

				member.setM_Id(rs.getString("m_id"));
				member.setM_Name(rs.getString("m_name"));
				member.setDept_no(rs.getInt("dept_no"));
				member.setM_Phone(rs.getString("m_phone"));
				member.setM_Email(rs.getString("m_email"));

				memberList.add(member);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(con!=null&&!con.isClosed()) {
					con.close();
				}
				if(pstmt!=null&&!pstmt.isClosed()) {
					pstmt.close();
				}
				if(rs!=null&&!rs.isClosed()) {
					rs.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return memberList;
	}// end getPageList
	// 페이징 처리를 위해 DB내 전체 데이터 개수 알아오기
	public int getMemberCount() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int CountNum = 0;

		String sql="SELECT COUNT(*) FROM member";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();
			if(rs.next()) {
				CountNum = rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(con!=null&&!con.isClosed()) {
					con.close();
				}
				if(pstmt!=null&&!pstmt.isClosed()) {
					pstmt.close();
				}
				if(rs!=null&&!rs.isClosed()) {
					rs.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return CountNum;
	}

}

