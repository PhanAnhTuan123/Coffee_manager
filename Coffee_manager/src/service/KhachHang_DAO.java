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
		ArrayList<KhachHang> customers = new ArrayList<>();
//		ConnectDB.getInstance();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM KhachHang;";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {      
        	customers.add(new KhachHang(
        			rs.getString("maKH"),
        			rs.getString("tenKH"),
        			rs.getString("DiaChi"),
        			rs.getString("SDT")
        			));
        }
        return customers;
	}

	@Override
	public KhachHang get(String id) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM KhachHang WHERE maKH = ?");
        stmt.setNString(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
        	KhachHang employee = KhachHang.getFromResultSet(rs);
            return employee;
        }
        return null;
	}

	@Override
	public void save(KhachHang t) throws SQLException {
		if (t == null) {
            throw new SQLException("Khách Hàng rỗng");
        }
        String query = "INSERT INTO KhachHang(maKH,TenKH,DiaChi,SDT)"
        		+ " VALUES (?,?,?,?)";

        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setNString(1, t.getMaKH());
        stmt.setNString(2, t.getTenKH());
        stmt.setNString(3, t.getDiaChi());
        stmt.setNString(4, t.getSdt());
        
        int row = stmt.executeUpdate();
	}

	@Override
	public void update(KhachHang t) throws SQLException {
		if (t == null) {
            throw new SQLException("Khách Hàng rỗng");
        }
        String query = "UPDATE KhachHang SET TenKH = ?,DiaChi = ?,SDT = ? WHERE maKH = ?";
        PreparedStatement stmt = conn.prepareStatement(query);

        stmt.setNString(1, t.getTenKH());
        stmt.setNString(2, t.getDiaChi());
        stmt.setNString(3, t.getSdt());
        stmt.setNString(4, t.getMaKH());
        stmt.executeUpdate();
	}

	@Override
	public void delete(KhachHang t) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("DELETE FROM KhachHang WHERE maKH = ?");
        stmt.setNString(1, t.getMaKH());
        stmt.executeUpdate();
	}
	
	public String sinhMaKH() {
		String ma = "";
		try {
			String sql = "select top 1 maKH from KhachHang where maKH like 'KH%' order by maKH desc";
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				ma = rs.getString("maKH");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (!ma.equalsIgnoreCase("")) {
			ma = ma.substring(2);
			int so = Integer.parseInt(ma) + 1;
//			System.out.println(so);
			String numberPart = String.format("%03d", so);
			ma = "KH" + numberPart;
		} else {
			ma = "KH001";
		}
		System.out.println(ma);
		return ma;
	}
	
	public ArrayList<KhachHang> findByName(String ten) {
		ArrayList<KhachHang> list = new ArrayList<KhachHang>();
		try {
			String sql = "select * from KhachHang where TenKH like '%" + ten + "%' ";
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				list.add(new KhachHang(rs.getString("maKH"),rs.getString("TenKH"),rs.getString("DiaChi"),rs.getString("SDT")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void deleteById(int id) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("DELETE FROM KhachHang WHERE maKH = ?");
        stmt.setInt(1, id);
        stmt.executeUpdate();
	}

	public KhachHang getById(String id) throws SQLException {
		Statement statement = conn.createStatement();
        String query = "SELECT * FROM KhachHang WHERE maKH = '" + id+"'";
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
        	KhachHang employee = new KhachHang(
        			rs.getString("maKH"),
        			rs.getString("tenKH"),
        			rs.getString("DiaChi"),
        			rs.getString("SDT")
        			);
            return employee;
        }
        return null;
	}


}
