package test;

import java.sql.SQLException;

import com.mysql.jdbc.Driver;

public class MyDriver1 extends Driver{

	static {
		try {
			Dm.registerDriver(new MyDriver1());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public MyDriver1() throws SQLException {
		super();
	}

}
