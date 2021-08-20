package com.sjh.model;

public class MemberVO {
	private String m_Name;
	private int m_No;
	private String m_Id;
	private String m_Pw;
	private int Dept_no;
	private String m_Phone;
	private String m_Email;
	
	public MemberVO() {
		
	}

	public MemberVO(String m_Name, int m_No, String m_Id, String m_Pw, int dept_no, String m_Phone, String m_Email) {
		super();
		this.m_Name = m_Name;
		this.m_No = m_No;
		this.m_Id = m_Id;
		this.m_Pw = m_Pw;
		Dept_no = dept_no;
		this.m_Phone = m_Phone;
		this.m_Email = m_Email;
	}

	public String getM_Name() {
		return m_Name;
	}

	public void setM_Name(String m_Name) {
		this.m_Name = m_Name;
	}

	public int getM_No() {
		return m_No;
	}

	public void setM_No(int m_No) {
		this.m_No = m_No;
	}

	public String getM_Id() {
		return m_Id;
	}

	public void setM_Id(String m_Id) {
		this.m_Id = m_Id;
	}

	public String getM_Pw() {
		return m_Pw;
	}

	public void setM_Pw(String m_Pw) {
		this.m_Pw = m_Pw;
	}

	public int getDept_no() {
		return Dept_no;
	}

	public void setDept_no(int dept_no) {
		Dept_no = dept_no;
	}

	public String getM_Phone() {
		return m_Phone;
	}

	public void setM_Phone(String m_Phone) {
		this.m_Phone = m_Phone;
	}

	public String getM_Email() {
		return m_Email;
	}

	public void setM_Email(String m_Email) {
		this.m_Email = m_Email;
	}

	@Override
	public String toString() {
		return "MemberVO [m_Name=" + m_Name + ", m_No=" + m_No + ", m_Id=" + m_Id + ", m_Pw=" + m_Pw + ", Dept_no="
				+ Dept_no + ", m_Phone=" + m_Phone + ", m_Email=" + m_Email + "]";
	}
	
	
	
	
}
