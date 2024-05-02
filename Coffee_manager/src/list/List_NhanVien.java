package list;

import java.sql.SQLException;
import java.util.List;

import model.NhanVien;
import service.NhanVien_DAO;

public class List_NhanVien{
	
	private NhanVien_DAO nv_dao;

	public List_NhanVien() {
		nv_dao = new NhanVien_DAO();
	}
	
	public List<NhanVien> getAll() throws SQLException {
		return nv_dao.getAll();
	}
	
	public NhanVien get(int id) throws SQLException {
		return nv_dao.get(id);
	}
	
	public boolean add(NhanVien nv) throws SQLException {
		if(nv_dao.getAll().contains(nv)) {
			return false;
		}
		nv_dao.save(nv);
		return true;
	}
	
	public boolean update(NhanVien nv) throws SQLException {
		try {
			nv_dao.update(nv);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean remove(NhanVien nv) {
		try {
			nv_dao.delete(nv);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean removeById(int id) {
		try {
			nv_dao.deleteById(id);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}	
}
