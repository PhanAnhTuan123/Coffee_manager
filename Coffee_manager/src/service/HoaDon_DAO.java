package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import constraint.AbstractConnect;
import constraint.CRUD;
import db.ConnectDB;
import list.List_HoaDon;
import model.HoaDon;
import model.NhanVien;

public class HoaDon_DAO extends AbstractConnect implements CRUD<HoaDon> {

	@Override
	public ArrayList<HoaDon> getAll() throws SQLException {
		ArrayList<HoaDon> list = new ArrayList<HoaDon>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		NhanVien_DAO nv_dao = new NhanVien_DAO();
		KhachHang_DAO kh_dao = new KhachHang_DAO();
		Ban_DAO ban_dao = new Ban_DAO();
		String sql = "select * from hoaDon";
		java.sql.Statement statement = con.createStatement();
		ResultSet rs = statement.executeQuery(sql);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		while (rs.next()) {
			try {
				list.add(new HoaDon(rs.getString("maHD"), dateFormat.parse(rs.getString("Ngay")),
						Double.parseDouble(rs.getString("TongTien")), Integer.parseInt(rs.getString("ChietKhau")),
						Integer.parseInt(rs.getString("diemTL")), nv_dao.get(Integer.parseInt(rs.getString("maNV"))),
						kh_dao.get(Integer.parseInt(rs.getString("maKH"))),
						ban_dao.get(Integer.parseInt(rs.getString("maBan")))));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public HoaDon get(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(HoaDon t) throws SQLException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = dateFormat.format(t.getNgay());
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "insert into hoaDon(maHD,Ngay,TongTien,ChietKhau,DiemTL,maNV,maKH,maBan)"
				+ " values(?,?,?,?,?,?,?,?)";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1, t.getMaHD());
			stm.setString(2, dateString);
			stm.setString(3, String.valueOf(t.getTongTien()));
			stm.setString(4, String.valueOf(t.getChietKhau()));
			stm.setString(5, String.valueOf(t.getDiemTL()));
			stm.setString(6, t.getNhanVien().getMaNV());
			stm.setString(7, t.getKhachHang().getMaKH());
			stm.setString(8, t.getBan().getMaBan());

			stm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(stm);
		}
	}

	@Override
	public void update(HoaDon t) throws SQLException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "Update hoaDon set tongTien = ?, ChietKhau = ?, DiemTL = ? where maHD = ?";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1, String.valueOf(t.getTongTien()));
			stm.setString(2, String.valueOf(t.getChietKhau()));
			stm.setString(3, String.valueOf(t.getDiemTL()));
			stm.setString(4, String.valueOf(t.getMaHD()));

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			close(stm);
		}
	}

	@Override
	public void delete(HoaDon t) throws SQLException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "DELETE from hoaDon where maHD = ? ";
		try {
				stm = con.prepareStatement(sql);
				stm.setString(1,t.getMaHD());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			close(stm);
			CRUD.close(stm);
		}

	}

	@Override
	public void deleteById(int id) throws SQLException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "DELETE from hoaDon where maHD = ? ";
		try {
				stm = con.prepareStatement(sql);
				stm.setString(1,String.valueOf(id));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			close(stm);
			CRUD.close(stm);
		}
	}

}
