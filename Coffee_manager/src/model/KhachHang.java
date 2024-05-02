package model;

import java.sql.ResultSet;

public class KhachHang {
	private String maKH;
	private String tenKH;
	private String diaChi;
	private String sdt;
	private Integer diemTL;

	public KhachHang(String maKH, String tenKH, String diaChi, String sdt, Integer diemTL) {
		super();
		this.maKH = maKH;
		this.tenKH = tenKH;
		this.diaChi = diaChi;
		this.sdt = sdt;
		this.diemTL = diemTL;
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

	public Integer getDiemTL() {
		return diemTL;
	}

	public void setDiemTL(Integer diemTL) {
		this.diemTL = diemTL;
	}


	public String getMaKH() {
		return maKH;
	}

	@Override
	public String toString() {
		return "KhachHang [maKH=" + maKH + ", tenKH=" + tenKH + ", diaChi=" + diaChi + ", sdt=" + sdt + ", diemTL="
				+ diemTL;
	}

	public static KhachHang getFromResultSet(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}

}