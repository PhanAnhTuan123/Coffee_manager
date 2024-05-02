package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HangHoa {
	private String maHH;
	private String tenHH;
	private Double gia;

	public HangHoa(String maHH, String tenHH, Double gia) {
		super();
		this.maHH = maHH;
		this.tenHH = tenHH;
		this.gia = gia;
	}

	public HangHoa() {
		super();
	}

	public String getMaHH() {
		return maHH;
	}

	public void setMaHH(String maHH) {
		this.maHH = maHH;
	}

	public String getTenHH() {
		return tenHH;
	}

	public void setTenHH(String tenHH) {
		this.tenHH = tenHH;
	}

	public Double getGia() {
		return gia;
	}

	public void setGia(Double gia) {
		this.gia = gia;
	}

	public static HangHoa getFromResultSet(ResultSet rs) throws SQLException {
		HangHoa e = new HangHoa();
		e.setMaHH(rs.getString("maHH"));
		e.setTenHH(rs.getString("TenHH"));
		e.setGia(rs.getDouble("Gia"));
		return e;
	}

	@Override
	public String toString() {
		return "HangHoa [maHH=" + maHH + ", tenHH=" + tenHH + ", gia=" + gia + "]";
	}

	

}
