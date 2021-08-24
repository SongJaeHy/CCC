package com.sjh.model;

public class MemberVO {
	private String m_name;
	private int m_no;
	private String m_id;
	private String m_pw;
	private int dept_no;
	private String m_phone;
	private String m_email;

	public MemberVO() {

	}

	public MemberVO(String m_name, int m_no, String m_id, String m_pw, int dept_no, String m_phone, String m_email) {
		super();
		this.m_name = m_name;
		this.m_no = m_no;
		this.m_id = m_id;
		this.m_pw = m_pw;
		this.dept_no = dept_no;
		this.m_phone = m_phone;
		this.m_email = m_email;
	}

	public String getM_name() {
		return m_name;
	}

	public void setM_name(String m_name) {
		this.m_name = m_name;
	}

	public int getM_no() {
		return m_no;
	}

	public void setM_no(int m_no) {
		this.m_no = m_no;
	}

	public String getM_id() {
		return m_id;
	}

	public void setM_id(String m_id) {
		this.m_id = m_id;
	}

	public String getM_pw() {
		return m_pw;
	}

	public void setM_pw(String m_pw) {
		this.m_pw = m_pw;
	}

	public int getDept_no() {
		return dept_no;
	}

	public void setDept_no(int dept_no) {
		this.dept_no = dept_no;
	}

	public String getM_phone() {
		return m_phone;
	}

	public void setM_phone(String m_phone) {
		this.m_phone = m_phone;
	}

	public String getM_email() {
		return m_email;
	}

	public void setM_email(String m_email) {
		this.m_email = m_email;
	}

	@Override
	public String toString() {
		return "MemberVO [m_name=" + m_name + ", m_no=" + m_no + ", m_id=" + m_id + ", m_pw=" + m_pw + ", dept_no="
				+ dept_no + ", m_phone=" + m_phone + ", m_email=" + m_email + "]";
	}





}
