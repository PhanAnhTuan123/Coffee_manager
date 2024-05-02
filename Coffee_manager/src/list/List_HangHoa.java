package list;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import constraint.CRUD;
import model.HangHoa;
import service.HangHoa_DAO;

public class List_HangHoa implements CRUD<HangHoa>{
	
	private HangHoa_DAO hh_dao;
	
	
	
	public List_HangHoa() {
		hh_dao = new HangHoa_DAO();
	}

	@Override
	public ArrayList<HangHoa> getAll() throws SQLException {
		return hh_dao.getAll();
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
