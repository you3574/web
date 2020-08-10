package net.skhu.VO;
//학과와 부서를 관리하는 테이블
//해당id,code,학과(부서)명,학과와 부서를 구분해줄 수 있는 학과별 최소이수학점으로 구성
public class Department {
	int id;
	String dcode;
	String dname;
	int minimumCredit;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public int getMinimumCredit() {
		return minimumCredit;
	}
	public void setMinimumCredit(int minimumCredit) {
		this.minimumCredit = minimumCredit;
	}
	public String getDcode() {
		return dcode;
	}
	public void setDcode(String dcode) {
		this.dcode = dcode;
	}

}
