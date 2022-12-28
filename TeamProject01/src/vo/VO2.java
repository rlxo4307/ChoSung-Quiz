package vo;

import java.util.Date;

public class VO2 {
	
	private String id;
	private int score;
	private Date fdate;
	private int v_count;
	private int dap_all;
	private int cau;
	private int savetime;
	private int countdownStarter;
	private int qnum; 
	private int sido;
	private int q_type;
	private String dap;
	private int nowscore;
	private int[] q;
	
	public VO2() {}
	
	public VO2(String id, int score, Date fdate, int v_count, int dap_all, int cau, int savetime, int countdownStarter, int qnum, int[] q, int sido, int sido2, int q_type, String dap, int nowscore ) {
		this.id=id;
		this.score=score;
		this.fdate=fdate;
		this.v_count=v_count;
		this.dap_all=dap_all;
		this.cau=cau;
		this.savetime=savetime;
		this.countdownStarter=countdownStarter;
		this.qnum=qnum;
		this.q=q;
		this.sido=sido;
		this.q_type=q_type;
		this.dap=dap;
		this.nowscore=nowscore;
	}
	
	public int getNowscore() {
		return nowscore;
	}

	public void setNowscore(int nowscore) {
		this.nowscore = nowscore;
	}

	public String getDap() {
		return dap;
	}

	public void setDap(String dap) {
		this.dap = dap;
	}
	
	public int getSido() {
		return sido;
	}

	public void setSido(int sido) {
		this.sido = sido;
	}

	public int getCau() {
		return cau;
	}

	public void setCau(int cau) {
		this.cau = cau;
	}

	public int getSavetime() {
		return savetime;
	}

	public void setSavetime(int savetime) {
		this.savetime = savetime;
	}

	public int getCountdownStarter() {
		return countdownStarter;
	}

	public void setCountdownStarter(int countdownStarter) {
		this.countdownStarter = countdownStarter;
	}

	public int getQnum() {
		return qnum;
	}

	public void setQnum(int qnum) {
		this.qnum = qnum;
	}

	public int[] getQ() {
		return q;
	}

	public void setQ(int[] q) {
		this.q = q;
	}

//	public int getSido2() {
//		return sido2;
//	}

//	public void setSido2(int sido2) {
//		this.sido2 = sido2;
//	}

	public int getQ_type() {
		return q_type;
	}

	public void setQ_type(int q_type) {
		this.q_type = q_type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Date getFdate() {
		return fdate;
	}

	public void setFdate(Date fdate) {
		this.fdate = fdate;
	}

	public int getV_count() {
		return v_count;
	}

	public void setV_count(int v_count) {
		this.v_count = v_count;
	}

	public int getDap_all() {
		return dap_all;
	}

	public void setDap_all(int dap_all) {
		this.dap_all = dap_all;
	}
	
	


}
