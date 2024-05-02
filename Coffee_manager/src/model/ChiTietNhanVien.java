package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ChiTietNhanVien {
	private Integer tongSoCaLam;
	private Double luong;
	private NhanVien nhanVien;

	public ChiTietNhanVien() {
		super();
	}

	public ChiTietNhanVien(Integer tongSoCaLam, Double luong, NhanVien nhanVien) {
		super();
		this.tongSoCaLam = tongSoCaLam;
		this.luong = luong;
		this.nhanVien = nhanVien;
	}

	public Integer getTongSoCaLam() {
		return tongSoCaLam;
	}

	public void setTongSoCaLam(Integer tongSoCaLam) {
		this.tongSoCaLam = tongSoCaLam;
	}

	public Double getLuong() {
		return luong;
	}

	public void setLuong(Double luong) {
		this.luong = luong;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	@Override
	public String toString() {
		return "ChiTietNhanVien [tongSoCaLam=" + tongSoCaLam + ", luong=" + luong + ", nhanVien=" + nhanVien;
	}

	public static ChiTietNhanVien getFromResultSet(ResultSet rs) throws SQLException {
		ChiTietNhanVien e = new ChiTietNhanVien();
		e.setNhanVien(new NhanVien(rs.getString("maNV")));
		e.setTongSoCaLam(rs.getInt("TongSoCaLam"));
		e.setLuong(rs.getDouble("Luong"));
		return e;
	}

}
