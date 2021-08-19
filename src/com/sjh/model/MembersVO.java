package com.sjh.model;

public class MembersVO {
	private String m_id;
	private String m_Pw;
	private String m_Name;
	private int m_No;
	private int dept_no;
	private String m_Phone;
	private String m_Email;
	

public MembersVO() {
	
}


public MembersVO(String m_id, String m_Pw, String m_Name, int m_No, int dept_no, String m_Phone, String m_Email) {
	super();
	this.m_id = m_id;
	this.m_Pw = m_Pw;
	this.m_Name = m_Name;
	this.m_No = m_No;
	this.dept_no = dept_no;
	this.m_Phone = m_Phone;
	this.m_Email = m_Email;
}


public String getM_Id() {
	return m_id;
}


public void setM_Id(String m_Id) {
	this.m_id = m_Id;
}


public String getM_Pw() {
	return m_Pw;
}


public void setM_Pw(String m_Pw) {
	this.m_Pw = m_Pw;
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


public int getDept_no() {
	return dept_no;
}


public void setDept_no(int dept_no) {
	this.dept_no = dept_no;
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
	return "MembersVO [m_id=" + m_id + ", m_Pw=" + m_Pw + ", m_Name=" + m_Name + ", m_No=" + m_No + ", dept_no="
			+ dept_no + ", m_Phone=" + m_Phone + ", m_Email=" + m_Email + "]";
}












}

