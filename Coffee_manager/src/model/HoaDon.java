package model;

import java.util.Date;

public class HoaDon {
	private String maHD;
	private Date ngay;
	private Double tongTien;
	private NhanVien nhanVien;
	private KhachHang khachHang;
	private Ban ban;
	
	public HoaDon() {
		super();
	}

	public HoaDon(String maHD, Date ngay, Double tongTien, NhanVien nhanVien, KhachHang khachHang, Ban ban) {
		super();
		this.maHD = maHD;
		this.ngay = ngay;
		this.tongTien = tongTien;
		this.nhanVien = nhanVien;
		this.khachHang = khachHang;
		this.ban = ban;
	}

	public String getMaHD() {
		return maHD;
	}

	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}

	public Date getNgay() {
		return ngay;
	}

	public void setNgay(Date ngay) {
		this.ngay = ngay;
	}

	public Double getTongTien() {
		return tongTien;
	}

	public void setTongTien(Double tongTien) {
		this.tongTien = tongTien;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public Ban getBan() {
		return ban;
	}

	public void setBan(Ban ban) {
		this.ban = ban;
	}

	@Override
	public String toString() {
		return "HoaDon [maHD=" + maHD + ", ngay=" + ngay + ", tongTien=" + tongTien + ", nhanVien=" + nhanVien
				+ ", khachHang=" + khachHang + ", ban=" + ban + "]";
	}

}
