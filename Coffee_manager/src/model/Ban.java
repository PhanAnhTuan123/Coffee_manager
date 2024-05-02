package model;

public class Ban {
	private String maBan;
	private String tenBan;

	public Ban() {
		super();
	}

	public Ban(String maBan) {
		super();
		this.maBan = maBan;
	}

	public Ban(String maBan, String tenBan) {
		super();
		this.maBan = maBan;
		this.tenBan = tenBan;
	}

	public String getMaBan() {
		return maBan;
	}

	public void setMaBan(String maBan) {
		this.maBan = maBan;
	}

	public String getTenBan() {
		return tenBan;
	}

	public void setTenBan(String tenBan) {
		this.tenBan = tenBan;
	}

	@Override
	public String toString() {
		return "Ban [maBan=" + maBan + ", tenBan=" + tenBan + "]";
	}

}
