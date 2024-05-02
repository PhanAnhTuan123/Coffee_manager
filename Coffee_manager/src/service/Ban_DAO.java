package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import constraint.AbstractConnect;
import constraint.CRUD;
import db.ConnectDB;
import model.Ban;

public class Ban_DAO extends AbstractConnect implements CRUD<Ban>{

	@Override
	public ArrayList<Ban> getAll() throws SQLException {
		ConnectDB.getInstance();
		db.ConnectDB.getConnection();
		ArrayList<Ban>list = new ArrayList<Ban>();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from Ban";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				list.add(new Ban(
							rs.getString("maBan"),
							rs.getString("tenBan")
							
						));
			}
			
		} catch (Exception e) {
			
		}
		
		
		return list;
	}

	@Override
	public Ban get(int id) throws SQLException {
		Ban ban = new Ban();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			
		} catch (Exception e) {
			String sql = "select * from Ban where maBan = ?";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				ban = new Ban(
						rs.getString("maBan"),
						rs.getString("tenBan")
						);
			}
			
		}
		return ban;
	}

	@Override
	public void save(Ban t) throws SQLException {
		
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "insert into Ban(maBan,tenBan) values (?,?))";
		
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1,t.getMaBan());
			stm.setString(2, t.getTenBan());
			stm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(stm);
		}
		
		
	}

	@Override
	public void update(Ban t) throws SQLException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "Update Ban set tenBan = ? where maBan = ?";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1, t.getTenBan());
			stm.setString(2, t.getMaBan());
			stm.executeUpdate();
		} catch (Exception e) {
		
			e.printStackTrace();
			
		}finally {
			close(stm);
		}
		
	}

	@Override
	public void delete(Ban t) throws SQLException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "DELETE Ban where maBan = ?";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1, t.getMaBan());
			stm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			close(stm);
		}
	}

	@Override
	public void deleteById(int id) throws SQLException {
			
	}

}
