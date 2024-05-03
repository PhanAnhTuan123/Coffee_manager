package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import constraint.AbstractConnect;
import constraint.CRUD;
import db.ConnectDB;
import model.Ban;

public class Ban_DAO extends AbstractConnect implements CRUD<Ban> {

	@Override
	public ArrayList<Ban> getAll() throws SQLException {
		ArrayList<Ban> list = new ArrayList<Ban>();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from Ban";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				list.add(new Ban(rs.getString("maBan"), rs.getString("tenBan")

				));
			}

		} catch (Exception e) {

		}

		return list;
	}

	public String sinhMaBan() {
		String ma = "";
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select top 1 maBan from Ban where maBan like 'BN%' order by maBan desc";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
//			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			while (rs.next()) {
				ma = rs.getString("maBan");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (!ma.equalsIgnoreCase("")) {
			ma = ma.substring(2);
			int so = Integer.parseInt(ma) + 1;
//			System.out.println(so);
			String numberPart = String.format("%03d", so);
			ma = "BN" + numberPart;
		} else {
			ma = "BN001";
		}
		System.out.println(ma);
		return ma;
	}

	public ArrayList<Ban> findByName(String tenBan) {
		ArrayList<Ban> list = new ArrayList<Ban>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();

		try {
			String sql = "select * from Ban where tenBan like '%" + tenBan + "%' ";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				list.add(new Ban(rs.getString("maBan"), rs.getString("tenBan")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Ban get(int id) throws SQLException {
		Ban ban = new Ban();
		try {
			String sql = "select * from Ban where maBan = ?";
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				ban = new Ban(rs.getString("maBan"), rs.getString("tenBan"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ban;
	}

	@Override
	public void save(Ban t) throws SQLException {
		PreparedStatement stm = null;
		String sql = "insert into Ban(maBan,tenBan) values (?,?)";

		try {
			stm = conn.prepareStatement(sql);
			stm.setString(1, t.getMaBan());
			stm.setString(2, t.getTenBan());
			stm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(stm);
		}
	}

	@Override
	public void update(Ban t) throws SQLException {
		PreparedStatement stm = null;
		String sql = "Update Ban set tenBan = ? where maBan = ?";
		try {
			stm = conn.prepareStatement(sql);
			stm.setString(1, t.getTenBan());
			stm.setString(2, t.getMaBan());
			stm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(stm);
		}

	}

	@Override
	public void delete(Ban t) throws SQLException {
		PreparedStatement stm = null;
		String sql = "DELETE Ban where maBan = ?";
		try {
			stm = conn.prepareStatement(sql);
			stm.setString(1, t.getMaBan());
			stm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			close(stm);
		}
	}

	@Override
	public void deleteById(int id) throws SQLException {

	}

	public Ban getBanById(String id) {
		Ban ban = new Ban();
		try {
			String sql = "select * from Ban where maBan = "+id;
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				ban = new Ban(rs.getString("maBan"), rs.getString("tenBan"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ban;
	}

	public Ban getById(String string) {
		Ban ban = new Ban();
		try {
			String sql = "select * from Ban where maBan = '"+ string+"'";
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				ban = new Ban(rs.getString("maBan"), rs.getString("tenBan"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ban;
	}

}
