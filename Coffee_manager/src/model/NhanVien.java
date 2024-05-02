package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NhanVien {
	private String maNV;
	private String tenNV;
	private String diaChi;
	private String sdt;
	private String chucVu;
	private Boolean gioiTinh;
	
	public NhanVien() {
		
	}

	public NhanVien(String maNV) {
		super();
		this.maNV = maNV;
	}

	public NhanVien(String maNV, String tenNV, String diaChi, String sdt, String chucVu, Boolean gioiTinh) {
		super();
		this.maNV = maNV;
		this.tenNV = tenNV;
		this.diaChi = diaChi;
		this.sdt = sdt;
		this.chucVu = chucVu;
		this.gioiTinh = gioiTinh;
	}

	public String getTenNV() {
		return tenNV;
	}
	
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
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

	public String getChucVu() {
		return chucVu;
	}

	public void setChucVu(String chucVu) {
		this.chucVu = chucVu;
	}

	public Boolean getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(Boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getMaNV() {
		return maNV;
	}
	
	public static NhanVien getFromResultSet(ResultSet rs) throws SQLException {
		NhanVien e = new NhanVien();
        e.setMaNV(rs.getString("maNV"));
        e.setTenNV(rs.getString("TenNV"));
        e.setDiaChi(rs.getString("DiaChi"));
        e.setSdt(rs.getString("SDT"));
        e.setChucVu(rs.getString("ChucVu"));
        e.setGioiTinh(rs.getBoolean("GioiTinh"));
        return e;
    }
	
	

	@Override
	public String toString() {
		return "NhanVien [maNV=" + maNV + ", tenNV=" + tenNV + ", diaChi=" + diaChi + ", sdt=" + sdt + ", chucVu="
				+ chucVu + ", gioiTinh=" + gioiTinh + "]";
	}

}
