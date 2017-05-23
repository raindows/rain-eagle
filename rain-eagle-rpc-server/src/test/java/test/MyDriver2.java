package test;

import java.sql.SQLException;

import com.mysql.jdbc.Driver;

public class MyDriver2 extends Driver{

	static {
		try {
			Dm.registerDriver(new MyDriver2());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public MyDriver2() throws SQLException {
		super();
	}

}
