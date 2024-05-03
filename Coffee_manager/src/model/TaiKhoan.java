package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaiKhoan {
	private NhanVien userName;
	private String passWord;
	
	
	
	public TaiKhoan() {
		super();
	}

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
	
	
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getPassWord() {
		return passWord;
	}

	public static TaiKhoan getFromResultSet(ResultSet rs) throws SQLException {
		TaiKhoan e = new TaiKhoan();
        e.setUserName(new NhanVien(rs.getString("userName")));
        e.setPassWord(rs.getString("passWord"));
        return e;
    }
	
	@Override
	public String toString() {
		return "TaiKhoan [userName=" + userName + ", passWord=" + passWord + "]";
	}
	
	
}
