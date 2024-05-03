package model;

import java.util.Date;

public class HoaDon {
	private String maHD;
	private Date ngay;
	private Double tongTien;
	private Integer chietKhau;
	private Integer diemTL;
	private NhanVien nhanVien;
	private KhachHang khachHang;
	private Ban ban;
	
	public HoaDon() {
		super();
	}

	public HoaDon(String maHD, Date ngay, Double tongTien, Integer chietKhau, Integer diemTL, NhanVien nhanVien,
			KhachHang khachHang, Ban ban) {
		super();
		this.maHD = maHD;
		this.ngay = ngay;
		this.tongTien = tongTien;
		this.chietKhau = chietKhau;
		this.diemTL = diemTL;
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

	public Integer getChietKhau() {
		return chietKhau;
	}

	public void setChietKhau(Integer chietKhau) {
		this.chietKhau = chietKhau;
	}

	public Integer getDiemTL() {
		return diemTL;
	}

	public void setDiemTL(Integer diemTL) {
		this.diemTL = diemTL;
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
		return "HoaDon [maHD=" + maHD + ", ngay=" + ngay + ", tongTien=" + tongTien + ", chietKhau=" + chietKhau
				+ ", diemTL=" + diemTL + ", nhanVien=" + nhanVien + ", khachHang=" + khachHang + ", ban=" + ban + "]";
	}

	

}
