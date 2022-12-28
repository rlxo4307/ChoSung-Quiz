package vo;
public class VO { 

	private int score; 	  
	private String id;  
	private int dap_all;
	
	public VO() {}
	
	public VO(String id, int score, int dap_all) {
		this.id=id;
		this.score=score;
		this.dap_all=dap_all;
	}
	
	public int getDap_all() {
		return dap_all;
	}

	public void setDap_all(int dap_all) {
		this.dap_all = dap_all;
	}
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}