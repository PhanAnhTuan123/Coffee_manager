package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.jfree.data.category.DefaultCategoryDataset;

import constraint.AbstractConnect;
import constraint.CRUD;
import db.ConnectDB;
import list.List_HoaDon;
import model.HoaDon;
import model.NhanVien;

public class HoaDon_DAO extends AbstractConnect implements CRUD<HoaDon> {
	
	public String sinhMaHD() {
		String ma = "";
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select top 1 maHD from hoaDon where maHD like 'HD%' order by maHD desc";
			Statement statement =con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
//			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		while(rs.next()) {
			ma = rs.getString("maHD");
		}			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		if(!ma.equalsIgnoreCase("")) {
			ma = ma.substring(2);
			int so = Integer.parseInt(ma) + 1;
			String numberPart = String.format("%03d",so);
		}else {
			ma = "HD001";
		}
		
		return ma;
		
	}
	
	@Override
	public ArrayList<HoaDon> getAll() throws SQLException {
		ArrayList<HoaDon> list = new ArrayList<HoaDon>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		NhanVien_DAO nv_dao = new NhanVien_DAO();
		KhachHang_DAO kh_dao = new KhachHang_DAO();
		Ban_DAO ban_dao = new Ban_DAO();
		String sql = "select * from hoaDon";
		java.sql.Statement statement = con.createStatement();
		ResultSet rs = statement.executeQuery(sql);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		while (rs.next()) {
			try {
				list.add(new HoaDon(rs.getString("maHD"), 
						dateFormat.parse(rs.getString("Ngay")),
						Double.parseDouble(rs.getString("TongTien")),
						nv_dao.getNV(rs.getString("maNV")),
						kh_dao.getById(rs.getString("maKH")),
						ban_dao.getById(rs.getString("maBan"))));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public HoaDon get(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(HoaDon t) throws SQLException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = dateFormat.format(t.getNgay());
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "insert into hoaDon(maHD,Ngay,TongTien,maNV,maKH,maBan)"
				+ " values(?,?,?,?,?,?)";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1, t.getMaHD());
			stm.setString(2, dateString);
			stm.setString(3, String.valueOf(t.getTongTien()));
			stm.setString(4, t.getNhanVien().getMaNV());
			stm.setString(5, t.getKhachHang().getMaKH());
			stm.setString(6, t.getBan().getMaBan());

			stm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(stm);
		}
	}

	@Override
	public void update(HoaDon t) throws SQLException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "Update hoaDon set tongTien = ? where maHD = ?";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1, String.valueOf(t.getTongTien()));
			stm.setString(4, String.valueOf(t.getMaHD()));

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			close(stm);
		}
	}

	@Override
	public void delete(HoaDon t) throws SQLException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "DELETE from hoaDon where maHD = ? ";
		try {
				stm = con.prepareStatement(sql);
				stm.setString(1,t.getMaHD());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			close(stm);
			CRUD.close(stm);
		}

	}

	@Override
	public void deleteById(int id) throws SQLException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "DELETE from hoaDon where maHD = ? ";
		try {
				stm = con.prepareStatement(sql);
				stm.setString(1,String.valueOf(id));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			close(stm);
			CRUD.close(stm);
		}
	}

	public String sinhMa() {
		String ma = "";
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select top 1 maHD from HoaDon where maHD like 'HD%' order by maHD desc\r\n"
					+ "";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
//			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			while (rs.next()) {
				ma = rs.getString("maHD");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (!ma.equalsIgnoreCase("")) {
			ma = ma.substring(2);
			int so = Integer.parseInt(ma) + 1;
//			System.out.println(so);
			String numberPart = String.format("%03d", so);
			ma = "HD" + numberPart;
		} else {
			ma = "HD001";
		}
		System.out.println(ma);
		return ma;
	}
	public HoaDon getById(String id) throws SQLException {
		return null;
	}
	
	public List<String[]> getThongKeDoanhThu() throws SQLException {
		List<String[]> thongTinDoanhThu = new ArrayList<String[]>();
	        String query = "SELECT CAST(hd.Ngay AS DATE) AS Ngay,hh.TenHH, SUM(hd.TongTien) AS TongDoanhThu\r\n"
	        		+ "FROM \r\n"
	        		+ "    HoaDon hd\r\n"
	        		+ "JOIN\r\n"
	        		+ "	ChiTietHoaDon cthd on hd.maHD = cthd.maHD\r\n"
	        		+ "JOIN\r\n"
	        		+ "	HangHoa hh on hh.maHH = cthd.maHH\r\n"
	        		+ "GROUP BY \r\n"
	        		+ "    CAST(hd.Ngay AS DATE),\r\n"
	        		+ "	hh.TenHH\r\n"
	        		+ "ORDER BY \r\n"
	        		+ "    CAST(hd.Ngay AS DATE);";
	        
	        Statement smt = conn.createStatement();
	        ResultSet rs = smt.executeQuery(query);
	        // Lấy ra dịch vụ được gọi nhiều nhất
	        while (rs.next()) {
	        	thongTinDoanhThu.add(new String[]{rs.getString("Ngay"),rs.getString("TenHH"),rs.getString("TongDoanhThu")});
	        }

	        return thongTinDoanhThu;
	}
	
	public String getThongKeDoanhThuCaoNhatTrongNam() throws SQLException{
		String tenHangHoaDonhThuCaoNhat = "";
		String query = "SELECT TOP 1\r\n"
				+ "        YEAR(hd.Ngay) AS Nam,\r\n"
				+ "        hh.TenHH,\r\n"
				+ "        SUM(hd.TongTien) AS TongDoanhThu\r\n"
				+ "    FROM\r\n"
				+ "        HoaDon hd\r\n"
				+ "    JOIN\r\n"
				+ "        ChiTietHoaDon cthd ON hd.maHD = cthd.maHD\r\n"
				+ "    JOIN\r\n"
				+ "        HangHoa hh ON cthd.maHH = hh.maHH\r\n"
				+ "    WHERE\r\n"
				+ "        YEAR(hd.Ngay) = 2024 -- Thay đổi năm theo đề bài của bạn\r\n"
				+ "    GROUP BY\r\n"
				+ "        hd.Ngay,\r\n"
				+ "        hh.TenHH\r\n"
				+ "	ORDER BY\r\n"
				+ "    TongDoanhThu DESC;";
		
		Statement smt = conn.createStatement();
        ResultSet rs = smt.executeQuery(query);
        // Lấy ra dịch vụ được gọi nhiều nhất
        while (rs.next()) {
        	tenHangHoaDonhThuCaoNhat = rs.getString(2);
        }

        return tenHangHoaDonhThuCaoNhat;
	}
	
	public DefaultCategoryDataset drawThongKeDoanhThuCaoNhatTrongNam() throws SQLException{
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		String query = "SELECT CAST(hd.Ngay AS DATE) AS Ngay,hh.TenHH, SUM(hd.TongTien) AS TongDoanhThu\r\n"
        		+ "FROM \r\n"
        		+ "    HoaDon hd\r\n"
        		+ "JOIN\r\n"
        		+ "	ChiTietHoaDon cthd on hd.maHD = cthd.maHD\r\n"
        		+ "JOIN\r\n"
        		+ "	HangHoa hh on hh.maHH = cthd.maHH\r\n"
        		+ "GROUP BY \r\n"
        		+ "    CAST(hd.Ngay AS DATE),\r\n"
        		+ "	hh.TenHH\r\n"
        		+ "ORDER BY \r\n"
        		+ "    CAST(hd.Ngay AS DATE);";
		
		Statement smt = conn.createStatement();
        ResultSet rs = smt.executeQuery(query);
        // Lấy ra dịch vụ được gọi nhiều nhất
        while (rs.next()) {
        	String Thang = rs.getString("Ngay");
        	Integer doanhThu = rs.getInt("TongDoanhThu");
        	dataset.addValue(doanhThu,"Doanh Thu",Thang);
        }

        return dataset;
	}
}
