package model;

import java.sql.ResultSet;

public class KhachHang {
	private String maKH;
	private String tenKH;
	private String diaChi;
	private String sdt;

	

	public KhachHang(String maKH, String tenKH, String diaChi, String sdt) {
		super();
		this.maKH = maKH;
		this.tenKH = tenKH;
		this.diaChi = diaChi;
		this.sdt = sdt;
	}

	

	public KhachHang() {
		// TODO Auto-generated constructor stub
	}



	@Override
	public String toString() {
		return "KhachHang [maKH=" + maKH + ", tenKH=" + tenKH + ", diaChi=" + diaChi + ", sdt=" + sdt + "]";
	}



	public String getMaKH() {
		return maKH;
	}



	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}



	public String getTenKH() {
		return tenKH;
	}



	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}



	public String getDiaChi() {
		return diaChi;
	}



	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}



	public String getSdt() {
		return sdt;
	}



	public void setSdt(String sdt) {
		this.sdt = sdt;
	}



	public static KhachHang getFromResultSet(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}



	public boolean getGioiTinh() {
		// TODO Auto-generated method stub
		return false;
	}



	public void setGioiTinh(boolean b) {
		// TODO Auto-generated method stub
		
	}

}