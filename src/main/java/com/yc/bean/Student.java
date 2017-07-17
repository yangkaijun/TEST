package com.yc.bean;

import java.io.Serializable;

public class Student  implements Serializable{

	private static final long serialVersionUID = -1351827268623354614L;
	private  int sid;
	private  String cname;
	private  String sname;
	
	
	public Student() {
		super();
	}
	public Student(int sid, String sname) {
		super();
		this.sid = sid;
		this.sname = sname;
	}
	
	public Student(  String sname,String cname) {
		super();
		this.cname = cname;
		this.sname = sname;
	}
	
	
	public Student(int sid, String cname, String sname) {
		super();
		this.sid = sid;
		this.cname = cname;
		this.sname = sname;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	
	@Override
	public String toString() {
		return "Class [sid=" + sid + ", cname=" + cname + ", sname=" + sname + "]";
	}

}
