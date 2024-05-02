package list;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import constraint.CRUD;
import model.Ban;
import model.HangHoa;
import service.Ban_DAO;
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
		return hh_dao.get(id);
	}

	@Override
	public void save(HangHoa t) throws SQLException {
		hh_dao.save(t);
	}

	@Override
	public void update(HangHoa t) throws SQLException {
		hh_dao.update(t);
	}

	@Override
	public void delete(HangHoa t) throws SQLException {
		hh_dao.delete(t);
	}

	@Override
	public void deleteById(int id) throws SQLException {
		hh_dao.deleteById(id);
	}
	
	public String sinhMaHH() {
		return hh_dao.sinhMaBan();
	}
	
	public ArrayList<HangHoa>findByName(String name){
		return hh_dao.findByName(name);
	}

	

}
