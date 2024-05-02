package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import constraint.AbstractConnect;
import constraint.CRUD;
import db.ConnectDB;
//import model.Ban;
import model.KhachHang;
//import model.NhanVien;

public class KhachHang_DAO extends AbstractConnect  implements CRUD<KhachHang> {

	@Override
	public ArrayList<KhachHang> getAll() throws SQLException {
		ArrayList<KhachHang>list = new ArrayList<KhachHang>();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from KhachHang ";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				list.add(new KhachHang(
							rs.getString("maKH"),
							rs.getString("tenKH"),
							rs.getString("DiaChi"),
							rs.getString("SDT")
						));
			}

		} catch (Exception e) {
			
		}
		
		
		return list;
	}

	public String sinhMaKhachHang() {
		String ma = "";
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select top 1 maKhachHang from KhachHang where maKhachHang like 'BN%' order by maKhachHang desc";
			Statement statement =con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
//			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		while(rs.next()) {
			ma = rs.getString("maKhachHang");
		}			
		}catch (SQLException e) {
			e.printStackTrace();
		}

		if(!ma.equalsIgnoreCase("")) {
			ma = ma.substring(2);
			int so = Integer.parseInt(ma) + 1;
//			System.out.println(so);
			String numberPart = String.format("%03d",so);
			ma = "BN"+ numberPart;
		}else {
			ma = "BN001";
		}
		System.out.println(ma);
		return ma;
	}
	public ArrayList<KhachHang> findByName(String tenKH) {
		ArrayList<KhachHang>list = new ArrayList<KhachHang>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		try {
			String sql = "select * from Ban where tenBan like '%"+tenKH+"%' ";
			Statement statement =con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
//			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		while(rs.next()) {
			list.add(new KhachHang(
					rs.getString("maKH"),
					rs.getString("tenKH"),
					rs.getString("DiaChi"),
					rs.getString("SDT")
					));
		}			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public KhachHang get(int id) throws SQLException {
		KhachHang KhachHang  = new KhachHang();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			
		} catch (Exception e) {
			String sql = "select * from Ban where maKhachHang = ?";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				KhachHang = new KhachHang(
						rs.getString("maKH"),
						rs.getString("tenKH"),
						rs.getString("DiaChi"),
						rs.getString("SDT")
						);
			}
			
		}
		return KhachHang;
	}

	@Override
	public void save(KhachHang t) throws SQLException {
		
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "insert into KhachHang(maKH,tenKH) values (?,?)";
		
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1,t.getMaKH());
			stm.setString(2, t.getTenKH());
			stm.setString(3, t.getDiaChi());
			stm.setString(4, t.getSdt());
		
			stm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(stm);
		}
		
		
	}

	@Override
	public void update(KhachHang t) throws SQLException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "Update Ban set tenKhachHang = ? where maKhachHang = ?";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1,t.getMaKH());
			stm.setString(2, t.getTenKH());
			stm.setString(3, t.getDiaChi());
			stm.setString(4, t.getSdt());
		
			stm.executeUpdate();
		} catch (Exception e) {
		
			e.printStackTrace();
			
		}finally {
			close(stm);
		}
		
	}

	@Override
	public void delete(KhachHang t) throws SQLException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "DELETE Ban where maKhachHang = ?";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1, t.getMaKH());
			stm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			close(stm);
		}
	}

	
	
	@Override
	public void deleteById(int id) throws SQLException {
			
	}

}
