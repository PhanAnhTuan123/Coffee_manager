package service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import constraint.CRUD;
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
	public TaiKhoan get(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(TaiKhoan t) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(TaiKhoan t) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(TaiKhoan t) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(int id) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	
}
