package list;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import constraint.CRUD;
import model.NhanVien;
import model.TaiKhoan;
import service.NhanVien_DAO;
import service.TaiKhoan_DAO;

public class List_TaiKhoan {
	private TaiKhoan_DAO tk_dao;

	public List_TaiKhoan() {
		tk_dao = new TaiKhoan_DAO();
	}
	
	public List<TaiKhoan> getAll() throws SQLException {
		return tk_dao.getAll();
	}
	
	public TaiKhoan get(int id) throws SQLException {
		return tk_dao.get(id);
	}
	
	public boolean add(TaiKhoan tk) throws SQLException {
		if(tk_dao.getAll().contains(tk)) {
			return false;
		}
		tk_dao.save(tk);
		return true;
	}
	
	public boolean update(TaiKhoan tk) throws SQLException {
		try {
			tk_dao.update(tk);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean remove(TaiKhoan tk) {
		try {
			tk_dao.delete(tk);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean removeById(int id) {
		try {
			tk_dao.deleteById(id);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public TaiKhoan getTK(String id) throws SQLException {
		return tk_dao.getTK(id);
	}
}
