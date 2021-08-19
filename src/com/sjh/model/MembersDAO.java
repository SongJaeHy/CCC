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
	
	// 회원가입을 처리하는 메서드 선언
	// DB에 들어가는 데, 혹은 DB에서 출력되어 나오는 데이터
	// 모두가 UsersVO 내부 자료형식을 벗어날 수 없으므로
	// 대다수 입출력은 전부VO를 매개로 진행합니다.
	
	
	public int joinMember(MembersVO member, DeptVO dept) {
		// members_join.jsp애서 가져온 코드를 이곳에 붙여넣기를 합니다.
		// DB연동을 위한 Connector 설정
		// Connection 객체 생성
		Connection con = null;
		// 쿼리문 실행을 위한 pstmt 객체 생성
		PreparedStatement pstmt = null;
		int updateCount = 0;
		try {
			// 커넥션 풀
			con = ds.getConnection();
			// 쿼리문
			
			String sql = "INSERT INTO member(m_id,m_pw, m_name, dept_no,m_phone, m_email)"
					+ "VALUES(?,?,?,?,?,?)";
			
			
			// 쿼리문 실행 및 나머지 로직
			
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getM_Id());
			pstmt.setString(2, member.getM_Pw());
			pstmt.setString(3,  member.getM_Name());
			pstmt.setInt(4, member.getDept_no());
			pstmt.setString(5, member.getM_Phone());
			pstmt.setString(6, member.getM_Email());
	
			pstmt.executeUpdate();
			
			if(updateCount != 0) {
				System.out.println("회원가입 성공");
			}else {
				System.out.println("회원가입 실패");
			}
		}catch(Exception e) {
			System.out.println("에러 : " + e);
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
	
	// 로그인 로직
	public int login(String m_id, String m_pw) {
		// Connection, PreparedStatement 객체 선언
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		MembersVO userinfo = new MembersVO();
		// 구문 작성

		String sql = "SELECT mpw FROM member WHERE m_id=?";
		
		
		try {
			// 커넥션 생성 및 pstmt에 쿼리문 넣고 완성시켜서 실행까지 하고
			// close()로 메모리회수까지 해주새요.
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
	// 사원 수정 로직
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
	
	// 사원 삭제로직
	// MembersDelete
	// 삭제
	// 원래 대다수 DAO는 MembersVO 하나로 모든 처리를 해결할 수 있습니다.
	// 다만 삭제로직은 폼에서 날린 비밀번호와 원래 DB에 저장되어있던 비밀번호를
	// 비교해야 하기 때문에 폼에서 날린 비밀번호를 추가로 입력받습니다.
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
	
	// getUserInfo 메서드
	// 수정 로직을 사용하기 전에 수정할 타겟 아이디의 정보를 얻어오기 위헤
	// 사용하는 메서드로 아이디, 비밀번호, 부서, 이메일, 전화번호를, UsersVO에 넣어서
	// 리턴합니다.
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
			System.out.println("에러 :" + e);
		}finally {
			try {
				if(con!=null && !con.isClosed()) {
					con.close();
				}// con닫기
				if(pstmt!=null && !pstmt.isClosed()) {
					pstmt.close();
				}// pstmt닫기
				if(rs!=null && !rs.isClosed()) {
					rs.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return resultData;
	}
	// 전체 사원에 대한 목록
	public ArrayList<MembersVO> getAllMember(){
		ArrayList<MembersVO> memberList = new ArrayList<>(); 
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM member ORDER BY mId DESC";
		
		try {
			// 커넥션 연결 후 DB에 쿼리
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
				}// con닫기
				if(pstmt!=null && !pstmt.isClosed()) {
					pstmt.close();
				}// pstmt닫기
				if(rs!=null && !rs.isClosed()) {
				rs.close();
			} // rs닫기
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
				System.out.println("에러 코드:" + e);
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
			System.out.println("에러코드 : " + e);
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
			System.out.println("에러코드 : " + e);
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


