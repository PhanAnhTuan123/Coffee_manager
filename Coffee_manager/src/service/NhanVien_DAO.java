package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import constraint.CRUD;
import model.NhanVien;

public class NhanVien_DAO implements CRUD<NhanVien> {
	@Override
	public ArrayList<NhanVien> getAll() throws SQLException {
		ArrayList<NhanVien> employees = new ArrayList<>();
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM NhanVien;";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
        	NhanVien employee = NhanVien.getFromResultSet(rs);
            employees.add(employee);
        }
        return employees;
	}

	@Override
	public NhanVien get(int id) throws SQLException {
		Statement statement = conn.createStatement();
        String query = "SELECT * FROM NhanVien WHERE maNV = " + id;
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
        	NhanVien employee = NhanVien.getFromResultSet(rs);
            return employee;
        }
        return null;
	}

	@Override
	public void save(NhanVien t) throws SQLException {
		if (t == null) {
            throw new SQLException("Nhân Viên rỗng");
        }
        String query = "INSERT INTO NhanVien(maNV,TenNV,DiaChi,SDT,ChucVu,GioiTinh)"
        		+ " VALUES (?,?,?,?,?,?)";

        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setNString(1, t.getMaNV());
        stmt.setNString(2, t.getTenNV());
        stmt.setNString(3, t.getDiaChi());
        stmt.setNString(4, t.getSdt());
        stmt.setNString(5, t.getChucVu());
        stmt.setBoolean(6, t.getGioiTinh());
        int row = stmt.executeUpdate();
	}

	@Override
	public void update(NhanVien t) throws SQLException {
		if (t == null) {
            throw new SQLException("Nhân Viên rỗng");
        }
        String query = "UPDATE NhanVien"
        		+ "SET TenNV = ?, DiaChi = ?,SDT = ?, ChucVu = ?,GioiTinh = ?"
        		+ "WHERE maNV = ?;";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setNString(1, t.getTenNV());
        stmt.setNString(2, t.getDiaChi());
        stmt.setNString(3, t.getSdt());
        stmt.setNString(4, t.getChucVu());
        stmt.setBoolean(5, t.getGioiTinh());
        stmt.setNString(6, t.getMaNV());
        stmt.executeUpdate();
	}

	@Override
	public void delete(NhanVien t) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("DELETE FROM NhanVien WHERE maNV = ?");
        stmt.setNString(1, t.getMaNV());
        stmt.executeUpdate();
	}

	@Override
	public void deleteById(int id) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("DELETE FROM NhanVien WHERE maNV = ?");
        stmt.setInt(1, id);
        stmt.executeUpdate();
	}
}
