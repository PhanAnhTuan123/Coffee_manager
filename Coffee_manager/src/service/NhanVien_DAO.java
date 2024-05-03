package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import constraint.CRUD;
import model.HangHoa;
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
        String query = "UPDATE NhanVien SET TenNV = ?,DiaChi = ?,SDT = ?, ChucVu = ?, GioiTinh = ? WHERE maNV = ?";
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
	
	public String sinhMaNV() {
		String ma = "";
		try {
			String sql = "select top 1 maNV from NhanVien where maNV like 'NV%' order by maNV desc";
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				ma = rs.getString("maNV");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (!ma.equalsIgnoreCase("")) {
			ma = ma.substring(2);
			int so = Integer.parseInt(ma) + 1;
//			System.out.println(so);
			String numberPart = String.format("%03d", so);
			ma = "NV" + numberPart;
		} else {
			ma = "NV001";
		}
		System.out.println(ma);
		return ma;
	}
	
	public ArrayList<NhanVien> findByName(String ten) {
		ArrayList<NhanVien> list = new ArrayList<NhanVien>();
		try {
			String sql = "select * from NhanVien where TenNV like '%" + ten + "%' ";
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				list.add(new NhanVien(rs.getString("maNV"),rs.getString("TenNV"),rs.getString("DiaChi"),rs.getString("SDT"),rs.getString("ChucVu"),rs.getBoolean("GioiTinh")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void deleteById(int id) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("DELETE FROM NhanVien WHERE maNV = ?");
        stmt.setInt(1, id);
        stmt.executeUpdate();
	}
	
	public String getTenNV(String id) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM NhanVien WHERE maNV = ?");
        stmt.setNString(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
        	NhanVien employee = NhanVien.getFromResultSet(rs);
            return employee.getTenNV();
        }
        return null;
	}
	public NhanVien getNV(String id) throws SQLException {
		Statement statement = conn.createStatement();
        String query = "SELECT * FROM NhanVien WHERE maNV = '" + id+ "'";
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
        	NhanVien employee = NhanVien.getFromResultSet(rs);
            return employee;
        }
        return null;
	}
}
