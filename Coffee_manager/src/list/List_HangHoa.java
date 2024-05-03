package list;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import constraint.CRUD;
import model.Ban;
import model.HangHoa;
import model.NhanVien;
import service.HangHoa_DAO;
import service.NhanVien_DAO;
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
	
	public HangHoa get(int id) throws SQLException {
		return hh_dao.get(id);
	}
	
	public boolean add(HangHoa hh) throws SQLException {
		if(hh_dao.getAll().contains(hh)) {
			return false;
		}
		hh_dao.save(hh);
		return true;
	}
	
	public void update(HangHoa hh) throws SQLException {
		try {
			hh_dao.update(hh);
//			return true;
		}catch(Exception e) {
			e.printStackTrace();
//			return false;
		}
		
	}
	
	public boolean remove(HangHoa hh) {
		try {
			hh_dao.delete(hh);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean removeById(int id) {
		try {
			hh_dao.deleteById(id);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}	

	@Override
	public void save(HangHoa t) throws SQLException {
		hh_dao.save(t);
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
