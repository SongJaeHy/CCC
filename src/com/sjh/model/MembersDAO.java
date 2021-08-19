package com.sjh.model;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import javax.naming.*;
import javax.sql.*;

import com.hgs.dept.model.DeptVO;


public class MembersDAO {
	private DataSource ds;
	
	private static final int MEMBER_DELETE_SUCCESS = 1;
	private static final int MEMBER_DELETE_FAIL = 0;
	
	private static final int MEMBER_LOGIN_SUCCESS = 1;
	private static final int MEMBER_LOGIN_FAIL = 0;
	
	private static final int MEMBER_UPDATE_SUCCESS = 1;
	private static final int MEMBER_UPDATE_FAIL = 0;
	
	private static final int CHECK_ID_SUCCESS = 1;
	private static final int CHECK_ID_FAIL = 0;
	
	private MembersDAO() {
		try {
			Context ct = new InitialContext();
			ds = (DataSource)ct.lookup("java:comp/env/jdbc/mysql");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static MembersDAO dao = new MembersDAO();
	
	public static MembersDAO getinstance() {
		if(dao == null) {
			dao = new MembersDAO();
		}
		return dao;
	}
	
	// ȸ�������� ó���ϴ� �޼��� ����
	// DB�� ���� ��, Ȥ�� DB���� ��µǾ� ������ ������
	// ��ΰ� UsersVO ���� �ڷ������� ��� �� �����Ƿ�
	// ��ټ� ������� ����VO�� �Ű��� �����մϴ�.
	
	
	public int joinMember(MembersVO member, DeptVO dept) {
		// members_join.jsp�ּ� ������ �ڵ带 �̰��� �ٿ��ֱ⸦ �մϴ�.
		// DB������ ���� Connector ����
		// Connection ��ü ����
		Connection con = null;
		// ������ ������ ���� pstmt ��ü ����
		PreparedStatement pstmt = null;
		int updateCount = 0;
		try {
			// Ŀ�ؼ� Ǯ
			con = ds.getConnection();
			// ������
			
			String sql = "INSERT INTO member(m_id,m_pw, m_name, dept_no,m_phone, m_email)"
					+ "VALUES(?,?,?,?,?,?)";
			
			
			// ������ ���� �� ������ ����
			
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getM_Id());
			pstmt.setString(2, member.getM_Pw());
			pstmt.setString(3,  member.getM_Name());
			pstmt.setInt(4, member.getDept_no());
			pstmt.setString(5, member.getM_Phone());
			pstmt.setString(6, member.getM_Email());
	
			pstmt.executeUpdate();
			
			if(updateCount != 0) {
				System.out.println("ȸ������ ����");
			}else {
				System.out.println("ȸ������ ����");
			}
		}catch(Exception e) {
			System.out.println("���� : " + e);
		}finally {
			try {
				if(con != null && !con.isClosed()) {
					con.close();
				}if(pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return updateCount;
		
	}// end joinMember
	
	// �α��� ����
	public int login(String m_id, String m_pw) {
		// Connection, PreparedStatement ��ü ����
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		MembersVO userinfo = new MembersVO();
		// ���� �ۼ�

		String sql = "SELECT mpw FROM member WHERE m_id=?";
		
		
		try {
			// Ŀ�ؼ� ���� �� pstmt�� ������ �ְ� �ϼ����Ѽ� ������� �ϰ�
			// close()�� �޸�ȸ������ ���ֻ���.
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, m_id);
			
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
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		 //end login
		return -2;
	}
	// ��� ���� ����
	public int updateMember(MembersVO member) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			String sql="UPDATE member SET m_pw=?, m_name=?, m_email=? WHERE m_id=?";
			
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
				if(con != null  && !con.isClosed()) {
					con.close();
				}if(pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return MEMBER_UPDATE_FAIL;
	
	} // end UpdateMember;
	
	// ��� ��������
	// MembersDelete
	// ����
	// ���� ��ټ� DAO�� MembersVO �ϳ��� ��� ó���� �ذ��� �� �ֽ��ϴ�.
	// �ٸ� ���������� ������ ���� ��й�ȣ�� ���� DB�� ����Ǿ��ִ� ��й�ȣ��
	// ���ؾ� �ϱ� ������ ������ ���� ��й�ȣ�� �߰��� �Է¹޽��ϴ�.
	public int DeleteMember(MembersVO member, String dpw) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			if(member.getM_Pw().equals(dpw)) {
			con = ds.getConnection();
			String sql="DELETE FROM member WHERE m_id=?";
		
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getM_Id());
			
			pstmt.executeUpdate();
		}else{
			return MEMBER_DELETE_FAIL;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(con != null && !con.isClosed()) {
					con.close();
				}
				if(pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return 0;
	} // end DeleteMember
	
	// getUserInfo �޼���
	// ���� ������ ����ϱ� ���� ������ Ÿ�� ���̵��� ������ ������ ����
	// ����ϴ� �޼���� ���̵�, ��й�ȣ, �μ�, �̸���, ��ȭ��ȣ��, UsersVO�� �־
	// �����մϴ�.
	public MembersVO getMemberInfo(MembersVO member) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		MembersVO resultData = new MembersVO();
		try {
			con = ds.getConnection();
			
			String sql = "SELECT * FROM member WHERE m_id=?";
			
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, member.getM_Id());
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				resultData.setM_Id("m_Id");
				resultData.setM_Pw("m_Pw");
				resultData.setM_Email("m_Email");
				resultData.setM_Phone("m_Phone");
			}
		}catch(SQLException e) {
			System.out.println("���� :" + e);
		}finally {
			try {
				if(con!=null && !con.isClosed()) {
					con.close();
				}// con�ݱ�
				if(pstmt!=null && !pstmt.isClosed()) {
					pstmt.close();
				}// pstmt�ݱ�
				if(rs!=null && !rs.isClosed()) {
					rs.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return resultData;
	}
	// ��ü ����� ���� ���
	public ArrayList<MembersVO> getAllMember(){
		ArrayList<MembersVO> memberList = new ArrayList<>(); 
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM member ORDER BY mId DESC";
		
		try {
			// Ŀ�ؼ� ���� �� DB�� ����
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MembersVO member = new MembersVO();
				
				member.setM_Id(rs.getString("m_id"));
				member.setM_Pw(rs.getString("m_pw"));
				member.setM_Name(rs.getString("m_name"));
				member.setM_Phone(rs.getString("m_phone"));
				member.setM_Email(rs.getString("m_email"));
				member.setDept_no(rs.getInt("dept_no"));
				
				memberList.add(member);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(con != null && !con.isClosed()) {
					con.close();
				}// con�ݱ�
				if(pstmt!=null && !pstmt.isClosed()) {
					pstmt.close();
				}// pstmt�ݱ�
				if(rs!=null && !rs.isClosed()) {
				rs.close();
			} // rs�ݱ�
		} catch(Exception e) {
			e.printStackTrace();
			}
		}
		return memberList;
	}
	public String getMemberDept(int no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String dept="";
		String sql = "SELECT * FROM dept WHERE dept_no=?";
		
		try {
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dept = rs.getString("d_name");
			}
		}catch(SQLException e) {
				System.out.println("���� �ڵ�:" + e);
			}finally {
				try {
					if(con != null && !con.isClosed()) {
						con.close();
					}	
					if(pstmt!= null && !pstmt.isClosed()) {
					pstmt.close();
				}
					if(rs!= null && !rs.isClosed()) {
						rs.close();
				}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
		return sql;
	}
	public int idCheck(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM member WHERE id = ?";
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next())
				return CHECK_ID_FAIL;
		} catch (SQLException e) {
			System.out.println("�����ڵ� : " + e);
		} finally {
			try {
				if(con != null && !con.isClosed()) {
					con.close();
				}
				if(pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return CHECK_ID_SUCCESS;
	}

	public List<DeptVO> getDept() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<DeptVO> result = new ArrayList<DeptVO>();
		String sql = "SELECT * FROM dept";

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				DeptVO dept = new DeptVO();
				dept.setDept_no(rs.getInt("dept_no"));
				dept.setDept_name(rs.getString("d_name"));
				result.add(dept);
			}
		} catch (SQLException e) {
			System.out.println("�����ڵ� : " + e);
		} finally {
			try {
				if (con != null && !con.isClosed()) {
					con.close();
				}
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}


