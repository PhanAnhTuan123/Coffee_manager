package service;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import constraint.CRUD;
import db.ConnectDB;
import model.Ban;
import model.HangHoa;
import model.NhanVien;

public class HangHoa_DAO implements CRUD<HangHoa> {

	@Override
	public ArrayList<HangHoa> getAll() throws SQLException {
		ArrayList<HangHoa> stocks = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM HangHoa;";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
        	HangHoa stock = HangHoa.getFromResultSet(rs);
        	stocks.add(stock);
        }
        return stocks;
	}

	@Override
	public HangHoa get(int id) throws SQLException {
		Statement statement = conn.createStatement();
        String query = "SELECT * FROM HangHoa WHERE maHH = " + id;
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
        	HangHoa stock = HangHoa.getFromResultSet(rs);
            return stock;
        }
        return null;
	}

	@Override
	public void save(HangHoa t) throws SQLException {
		if (t == null) {
            throw new SQLException("Hàng Hóa rỗng");
        }
        String query = "INSERT INTO HangHoa(maHH,TenHH,Gia)"
        		+ " VALUES (?,?,?)";

        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setNString(1, t.getMaHH());
        stmt.setNString(2, t.getTenHH());
        stmt.setDouble(3, t.getGia());
        int row = stmt.executeUpdate();
	}

	@Override
	public void update(HangHoa t) throws SQLException {
		if (t == null) {
            throw new SQLException("Hàng Hóa rỗng");
        }
        String query = "UPDATE HangHoa SET TenHH = ?, Gia = ? WHERE maHH = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        try {
        	stmt.setNString(1, t.getTenHH());
            stmt.setDouble(2, t.getGia());
            stmt.setNString(3, t.getMaHH());
            stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			stmt.close();
		}
	}

	@Override
	public void delete(HangHoa t) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("DELETE FROM HangHoa WHERE maHH = ?");
        stmt.setNString(1, t.getMaHH());
        stmt.executeUpdate();
	}
	
	public String sinhMaBan() {
		String ma = "";
		try {
			String sql = "select top 1 maHH from HangHoa where maHH like 'HH%' order by maHH desc";
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				ma = rs.getString("maHH");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (!ma.equalsIgnoreCase("")) {
			ma = ma.substring(2);
			int so = Integer.parseInt(ma) + 1;
//			System.out.println(so);
			String numberPart = String.format("%03d", so);
			ma = "HH" + numberPart;
		} else {
			ma = "HH001";
		}
		System.out.println(ma);
		return ma;
	}
	
	public ArrayList<HangHoa> findByName(String ten) {
		ArrayList<HangHoa> list = new ArrayList<HangHoa>();
		try {
			String sql = "select * from HangHoa where TenHH like '%" + ten + "%' ";
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				list.add(new HangHoa(rs.getString("maHH"), rs.getString("tenHH"),rs.getDouble("Gia")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void deleteById(int id) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
