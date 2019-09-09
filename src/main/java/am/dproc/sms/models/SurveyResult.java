package am.dproc.sms.models;

public class SurveyResult {
	private int studentId;
	private float ext;
	private float est;
	private float agr;
	private float csn;
	private float opn;
	
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public float getExt() {
		return ext;
	}
	public void setExt(float ext) {
		this.ext = ext;
	}
	public float getEst() {
		return est;
	}
	public void setEst(float est) {
		this.est = est;
	}
	public float getAgr() {
		return agr;
	}
	public void setAgr(float agr) {
		this.agr = agr;
	}
	public float getCsn() {
		return csn;
	}
	public void setCsn(float csn) {
		this.csn = csn;
	}
	public float getOpn() {
		return opn;
	}
	public void setOpn(float opn) {
		this.opn = opn;
	}	
}

