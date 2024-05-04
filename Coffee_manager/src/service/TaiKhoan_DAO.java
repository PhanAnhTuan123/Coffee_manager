package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import constraint.CRUD;
import model.KhachHang;
import model.NhanVien;
import model.TaiKhoan;

public class TaiKhoan_DAO implements CRUD<TaiKhoan> {

	@Override
	public ArrayList<TaiKhoan> getAll() throws SQLException {
		ArrayList<TaiKhoan> accounts = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM TaiKhoan;";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
        	TaiKhoan account = TaiKhoan.getFromResultSet(rs);
        	accounts.add(account);
        }
        return accounts;
	}

	@Override
	public TaiKhoan get(String id) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("SELECT * FROM TaiKhoan WHERE userName = ?");
        stmt.setNString(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
        	TaiKhoan account = TaiKhoan.getFromResultSet(rs);
            return account;
        }
        return null;
	}

	@Override
	public void save(TaiKhoan t) throws SQLException {
		if (t == null) {
            throw new SQLException("Tài Khoản rỗng");
        }
        String query = "INSERT INTO TaiKhoan(userName,passWord)"
        		+ " VALUES (?,?)";

        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setNString(1, t.getUserName().getMaNV());
        stmt.setNString(2, t.getPassWord());
        int row = stmt.executeUpdate();
	}

	@Override
	public void update(TaiKhoan t) throws SQLException {
		if (t == null) {
            throw new SQLException("Nhân Viên rỗng");
        }
        String query = "UPDATE TaiKhoan SET passWord = ? WHERE userName = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setNString(1, t.getPassWord());
        stmt.setNString(2, t.getUserName().getMaNV());
        stmt.executeUpdate();
	}

	@Override
	public void delete(TaiKhoan t) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("DELETE FROM TaiKhoan WHERE userName = ?");
        stmt.setNString(1, t.getUserName().getMaNV());
        stmt.executeUpdate();
		
	}

	@Override
	public void deleteById(int id) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	
	public ArrayList<TaiKhoan> findByName(String ten) {
		ArrayList<TaiKhoan> list = new ArrayList<TaiKhoan>();
		try {
			String sql = "select * from TaiKhoan where userName like '%" + ten + "%' ";
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				list.add(new TaiKhoan(new NhanVien(rs.getString("userName")), rs.getString("passWord")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
