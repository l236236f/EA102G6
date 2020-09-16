package ANNOUNCEMENT.model;
import java.sql.Timestamp;

public class ANNVO implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5693979691309873795L;
	private String annno;
	private String annchangeman;
	private String anntext;
	private Timestamp annchangedate;
	private String annstatus;
	private String anntitle;
	
	private byte[] annimg;

	public String getAnnno() {
		return annno;
	}
	public void setAnnno(String annno) {
		this.annno = annno;
	}
	public String getAnnchangeman() {
		return annchangeman;
	}
	public void setAnnchangeman(String annchangeman) {
		this.annchangeman = annchangeman;
	}
	public String getAnntext() {
		return anntext;
	}
	public void setAnntext(String anntext) {
		this.anntext = anntext;
	}
	public Timestamp getAnnchangedate() {
		return annchangedate;
	}
	public void setAnnchangedate(Timestamp annchangedate) {
		this.annchangedate = annchangedate;
	}
	public String getAnnstatus() {
		return annstatus;
	}
	public void setAnnstatus(String annstatus) {
		this.annstatus = annstatus;
	}
	public byte[] getAnnimg() {
		return annimg;
	}
	public void setAnnimg(byte[] annimg) {
		this.annimg = annimg;
	}
	public String getAnntitle() {
		return anntitle;
	}
	public void setAnntitle(String anntitle) {
		this.anntitle = anntitle;
	}

	
}