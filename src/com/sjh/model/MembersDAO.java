package com.sjh.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.*;
import javax.sql.*;


public class MembersDAO {
	private DataSource ds;
	
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
	
	
	public int joinMember(MembersVO member) {
		// members_join.jsp�ּ� ������ �ڵ带 �̰��� �ٿ��ֱ⸦ �մϴ�.
		// DB������ ���� Connector ����
		// Connection ��ü ����
		Connection con = null;
		// ������ ������ ���� pstmt ��ü ����
		PreparedStatement pstmt = null;
		
		try {
			// Ŀ�ؼ� Ǯ
			con = ds.getConnection();
			// ������
			String sql = "INSERT INTO member(mid,mpw,mname,mno,dept_no,mphone,meamil)"
					+ "VALUES(?,?,?,?,?,?,?)";
			
			
			// ������ ���� �� ������ ����
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getmId());
			pstmt.setString(2, member.getmPw());
			pstmt.setString(3,  member.getmName());
			pstmt.setInt(4, member.getmNo());
			pstmt.setInt(5, member.getmDeptno());
			pstmt.setString(6, member.getmPhone());
			pstmt.setString(7, member.getmEmail());
	
			pstmt.executeUpdate();
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
		return 1;
	}// end joinMember
	public int login(MembersVO member) {
		// Connection, PreparedStatement ��ü ����
		Connection con = null;
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		
		// ���� �ۼ�

		String sql = "SELECT * FROM member WHERE mid=?";
		
		
		try {
			// Ŀ�ؼ� ���� �� pstmt�� ������ �ְ� �ϼ����Ѽ� ������� �ϰ�
			// close()�� �޸�ȸ������ ���ֻ���.
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getmId());
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String dbId = rs.getString("mid");
				String dbPw = rs.getString("mpw");
				
				if(member.getmId().equals(dbId) && member.getmPw().equals(dbPw)) {
					return 1;
				}else {
					return 0;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			return 0;
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
		return 0; //end login
	}
	// ��� ���� ����
	public int updateMember(MembersVO member) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			String sql="UPDATE member SET mpw=?, mname=?, memail=? WHERE mid=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getmPw());
			pstmt.setString(2, member.getmName());
			pstmt.setString(3, member.getmEmail());
			pstmt.setString(4, member.getmId());
			
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
		return 0;
	
	} // end UpdateMember;
	
	// ��� ��������
	public int DeleteMember(MembersVO member) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			String sql="DELETE FROM member WHERE mid=?";
		
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getmId());
			
			pstmt.executeUpdate();
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
}

