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
import model.ChiTietHoaDon;


public class ChiTietHoaDon_DAO extends AbstractConnect implements CRUD<ChiTietHoaDon>{

	@Override
	public ArrayList<ChiTietHoaDon> getAll() throws SQLException {
		HangHoa_DAO hanghoa_dao = new HangHoa_DAO();
		HoaDon_DAO hoadon_dao = new HoaDon_DAO();
		ArrayList<ChiTietHoaDon>list = new ArrayList<ChiTietHoaDon>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from chiTietHoaDon";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				list.add(new ChiTietHoaDon(
						Integer.parseInt(rs.getString("SoLuong")),
						hanghoa_dao.get(Integer.parseInt(rs.getString("maHH"))),
						hoadon_dao.get(Integer.parseInt(rs.getString("maHD"))),
						Float.parseFloat(rs.getString("price"))
						)
						);
			}
			
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public ChiTietHoaDon get(int id) throws SQLException {
		ChiTietHoaDon cthd = new ChiTietHoaDon();
		HangHoa_DAO hanghoa_dao = new HangHoa_DAO();
		HoaDon_DAO hoadon_dao = new HoaDon_DAO();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from ChiTietHoaDon where maHD =?";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				cthd = (new ChiTietHoaDon(
						Integer.parseInt(rs.getString("SoLuong")),
						hanghoa_dao.get(Integer.parseInt(rs.getString("maHH"))),
						hoadon_dao.get(Integer.parseInt(rs.getString("maHD"))),
						Float.parseFloat(rs.getString("price"))
						)
						);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cthd;
	}

	@Override
	public void save(ChiTietHoaDon t) throws SQLException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "insert into ChiTietHoaDon (maHD,soLuong,maHH,price) values (?,?,?,?)";
		
		try {
			
		stm = con.prepareStatement(sql);
		stm.setString(1, t.getHoaDon().getMaHD());
		stm.setString(2, String.valueOf(t.getSoLuong()));
		stm.setString(3, String.valueOf(t.getHangHoa().getMaHH()));
		stm.setString(4, String.valueOf(t.getPrice()));
		stm.executeUpdate();
		
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(stm);
		}
	}

	@Override
	public void update(ChiTietHoaDon t) throws SQLException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "Update ChiTietHoaDon set soLuong =?, maHH = ?, price =? where maHD = ?";
		try {
			stm = con.prepareStatement(sql);
			
			stm.setString(1, String.valueOf(t.getSoLuong()));
			stm.setString(2, String.valueOf(t.getHangHoa().getMaHH()));
			stm.setString(3, String.valueOf(t.getPrice()));
			stm.setString(4, String.valueOf(t.getHoaDon().getMaHD()));
			stm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(stm);
		}
		
		
		
		
	}

	@Override
	public void delete(ChiTietHoaDon t) throws SQLException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "delete ChiTietHoaDon where maHD = ?";
		
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1, t.getHoaDon().getMaHD());
			stm.executeUpdate();
			
		} catch (SQLException e) {

		}finally {
			close(stm);
		}
	}

	@Override
	public void deleteById(int id) throws SQLException {
		
		
	}

	public ArrayList<ChiTietHoaDon> getForCondition(String maHD) {
		HangHoa_DAO hanghoa_dao = new HangHoa_DAO();
		HoaDon_DAO hoadon_dao = new HoaDon_DAO();
		ArrayList<ChiTietHoaDon>list = new ArrayList<ChiTietHoaDon>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from chiTietHoaDon where maHD = '"+ maHD+"'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				list.add(new ChiTietHoaDon(
						Integer.parseInt(rs.getString("SoLuong")),
						hanghoa_dao.getHangHoaForID(rs.getString("maHH")),
						hoadon_dao.getById(rs.getString("maHD")),
						Float.parseFloat(rs.getString("price"))
						)
						);
			}
			
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		return list;
	}

}
