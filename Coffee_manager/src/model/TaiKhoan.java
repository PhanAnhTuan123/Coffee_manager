package model;

public class TaiKhoan {
	private NhanVien userName;
	private String passWord;
	
	public TaiKhoan(NhanVien userName, String passWord) {
		super();
		this.userName = userName;
		this.passWord = passWord;
	}

	public NhanVien getUserName() {
		return userName;
	}

	public void setUserName(NhanVien userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	@Override
	public String toString() {
		return "TaiKhoan [userName=" + userName + ", passWord=" + passWord + "]";
	}
	
	
}
