package list;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import constraint.CRUD;
import model.KhachHang;




import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import constraint.CRUD;
import model.KhachHang;
import model.NhanVien;
import service.KhachHang_DAO;
import service.NhanVien_DAO;

public class List_KhachHang{

	private KhachHang_DAO kh_dao;

	public List_KhachHang() {
		kh_dao = new KhachHang_DAO();
	}
	
	public List<KhachHang> getAll() throws SQLException {
		return kh_dao.getAll();
	}
	
	public KhachHang get(int id) throws SQLException {
		return kh_dao.get(id);
	}
	
	public boolean add(KhachHang kh) throws SQLException {
		if(kh_dao.getAll().contains(kh)) {
			return false;
		}
		kh_dao.save(kh);
		return true;
	}
	
	public boolean update(KhachHang kh) throws SQLException {
		try {
			kh_dao.update(kh);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean remove(KhachHang kh) {
		try {
			kh_dao.delete(kh);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean removeById(int id) {
		try {
			kh_dao.deleteById(id);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}	
	

}
