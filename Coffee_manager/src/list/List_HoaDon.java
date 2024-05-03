package list;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import constraint.CRUD;
import model.HoaDon;
import service.HoaDon_DAO;

public class List_HoaDon implements CRUD<HoaDon>{

	@Override
	public ArrayList<HoaDon> getAll() throws SQLException {
		return new HoaDon_DAO().getAll();
	}

	@Override
	public HoaDon get(int id) throws SQLException {
		return new HoaDon_DAO().get(id);
	}

	@Override
	public void save(HoaDon t) throws SQLException {
		 new HoaDon_DAO().save(t);
	}

	@Override
	public void update(HoaDon t) throws SQLException {
		new HoaDon_DAO().update(t);
		
	}

	@Override
	public void delete(HoaDon t) throws SQLException {
		new HoaDon_DAO().delete(t);
	}

	@Override
	public void deleteById(int id) throws SQLException {
		new HoaDon_DAO().deleteById(id);
	}
	public String sinhMa() {
		return new HoaDon_DAO().sinhMa();
	}

	
	
}
