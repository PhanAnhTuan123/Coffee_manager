package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import constraint.CRUD;
import model.ChiTietNhanVien;

public class ChiTietNhanVien_DAO implements CRUD<ChiTietNhanVien> {

	@Override
	public ArrayList<ChiTietNhanVien> getAll() throws SQLException {
		ArrayList<ChiTietNhanVien> employees_detail = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM ChiTietNhanVien;";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
        	ChiTietNhanVien employee_detail = ChiTietNhanVien.getFromResultSet(rs);
        	employees_detail.add(employee_detail);
        }
        return employees_detail;
	}

	@Override
	public ChiTietNhanVien get(String id) throws SQLException {
		Statement statement = conn.createStatement();
        String query = "SELECT * FROM ChiTietNhanVien WHERE maNV = " + id;
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
        	ChiTietNhanVien employee_detail = ChiTietNhanVien.getFromResultSet(rs);
            return employee_detail;
        }
        return null;
	}

	@Override
	public void save(ChiTietNhanVien t) throws SQLException {
		if (t == null) {
            throw new SQLException("Nhân Viên rỗng");
        }
        String query = "INSERT INTO ChiTietNhanVien(maNV,TongSoCaLam,Luong)"
        		+ " VALUES (?,?,?)";

        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setNString(1, t.getNhanVien().getMaNV());
        stmt.setInt(2, t.getTongSoCaLam());
        stmt.setDouble(3, t.getLuong());
        int row = stmt.executeUpdate();
	}

	@Override
	public void update(ChiTietNhanVien t) throws SQLException {
		if (t == null) {
            throw new SQLException("Nhân Viên rỗng");
        }
        String query = "UPDATE ChiTietNhanVien"
        		+ "SET TongSoCaLam = ?, Luong = ?"
        		+ "WHERE maNV = ?;";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, t.getTongSoCaLam());
        stmt.setDouble(2, t.getLuong());
        stmt.setNString(3, t.getNhanVien().getMaNV());
        stmt.executeUpdate();
	}

	@Override
	public void delete(ChiTietNhanVien t) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("DELETE FROM ChiTietNhanVien WHERE maNV = ?");
        stmt.setNString(1, t.getNhanVien().getMaNV());
        stmt.executeUpdate();
	}

	@Override
	public void deleteById(int id) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("DELETE FROM ChiTietNhanVien WHERE maNV = ?");
        stmt.setInt(1, id);
        stmt.executeUpdate();
	}

}
