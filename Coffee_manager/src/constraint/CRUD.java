package constraint;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import db.ConnectDB;


public interface CRUD<T> {
	Connection conn = ConnectDB.getConnection();

    public ArrayList<T> getAll() throws SQLException;

    public T get(int id) throws SQLException;

    public void save(T t) throws SQLException;

    public void update(T t) throws SQLException;

    public void delete(T t) throws SQLException;

    public void deleteById(int id) throws SQLException;
    
    static 	void close(PreparedStatement stm) {
		// TODO Auto-generated method stub
		if(stm!=null) {
			try {
				stm.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
    }

}
