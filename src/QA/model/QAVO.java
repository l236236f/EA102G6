package QA.model;
import java.sql.Timestamp;

public class QAVO implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1348180571529499695L;
	private String qano;
	private String qachangeman;
	private String qatext;
	private Timestamp qachangedate;
	private String qastatus;
	private String qakind;
	private String qakind2;
	private String qakind3;
	
	
	public String getQakind2() {
		return qakind2;
	}
	public void setQakind2(String qakind2) {
		this.qakind2 = qakind2;
	}
	public String getQakind3() {
		return qakind3;
	}
	public void setQakind3(String qakind3) {
		this.qakind3 = qakind3;
	}
	public String getQano() {
		return qano;
	}
	public void setQano(String qano) {
		this.qano = qano;
	}
	public String getQachangeman() {
		return qachangeman;
	}
	public void setQachangeman(String qachangeman) {
		this.qachangeman = qachangeman;
	}
	public String getQatext() {
		return qatext;
	}
	public void setQatext(String qatext) {
		this.qatext = qatext;
	}
	public Timestamp getQachangedate() {
		return qachangedate;
	}
	public void setQachangedate(Timestamp qachangedate) {
		this.qachangedate = qachangedate;
	}
	public String getQastatus() {
		return qastatus;
	}
	public void setQastatus(String qastatus) {
		this.qastatus = qastatus;
	}
	public String getQakind() {
		return qakind;
	}
	public void setQakind(String qakind) {
		this.qakind = qakind;
	}
	
}