	package EMPLOYEE.model;

import java.sql.Timestamp;

public class EmpVO implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5116103690270000183L;
	private String empno;
	private String empname;
	private String emptel;
	private String empemail;
	private String empid;
	private String emppsw;
	private byte[] empphoto;
 	private String empposition;
	private Timestamp emphiredate;
	private Timestamp empchangedate;
	private String empchangeman;
	private String empstatus;
	private String empnotes;
	public String getEmpno() {
		return empno;
	}
	public void setEmpno(String empno) {
		this.empno = empno;
	}
	public String getEmpname() {
		return empname;
	}
	public void setEmpname(String empname) {
		this.empname = empname;
	}
	public String getEmptel() {
		return emptel;
	}
	public void setEmptel(String emptel) {
		this.emptel = emptel;
	}
	public String getEmpemail() {
		return empemail;
	}
	public void setEmpemail(String empemail) {
		this.empemail = empemail;
	}
	public String getEmpid() {
		return empid;
	}
	public void setEmpid(String empid) {
		this.empid = empid;
	}
	public String getEmppsw() {
		return emppsw;
	}
	public void setEmppsw(String emppsw) {
		this.emppsw = emppsw;
	}
	public byte[] getEmpphoto() {
		return empphoto;
	}
	public void setEmpphoto(byte[] empphoto) {
		this.empphoto = empphoto;
	}
	public String getEmpposition() {
		return empposition;
	}
	public void setEmpposition(String empposition) {
		this.empposition = empposition;
	}
	public Timestamp getEmphiredate() {
		return emphiredate;
	}
	public void setEmphiredate(Timestamp emphiredate) {
		this.emphiredate = emphiredate;
	}
	public Timestamp getEmpchangedate() {
		return empchangedate;
	}
	public void setEmpchangedate(Timestamp empchangedate) {
		this.empchangedate = empchangedate;
	}
	public String getEmpchangeman() {
		return empchangeman;
	}
	public void setEmpchangeman(String empchangeman) {
		this.empchangeman = empchangeman;
	}
	public String getEmpstatus() {
		return empstatus;
	}
	public void setEmpstatus(String empstatus) {
		this.empstatus = empstatus;
	}
	public String getEmpnotes() {
		return empnotes;
	}
	public void setEmpnotes(String empnotes) {
		this.empnotes = empnotes;
	}
	
}