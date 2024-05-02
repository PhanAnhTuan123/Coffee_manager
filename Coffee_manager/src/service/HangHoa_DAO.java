package service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import constraint.CRUD;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(HangHoa t) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(HangHoa t) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(HangHoa t) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(int id) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
