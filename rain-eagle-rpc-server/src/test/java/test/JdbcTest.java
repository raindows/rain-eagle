package test;

import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Iterator;
import java.util.Properties;
import java.util.ServiceLoader;
import java.util.logging.Logger;

public class JdbcTest {

	public static void main(String[] args) {

		//testAccessController();

		// testDriverManager();

		// new MyStatic();
		java.sql.Connection connection = null;
		java.sql.Statement statement = null;
		java.sql.ResultSet resultSet = null;
		
		javax.sql.DataSource dataSource=null;
		try {
			// System.setProperty("jdbc.drivers","com.test.Driver:com.mysql.jdbc.Driver:test.MyDriver1");
			// DriverManager.setLogWriter(new PrintWriter(System.out));

			// java.sql.Driver driver=new com.mysql.jdbc.Driver();
			// Class.forName("com.mysql.jdbc.Driver");
			// Class.forName("test.MyDriver1");

			java.util.Properties properties = new java.util.Properties();
			properties.setProperty("user", "root");
			properties.setProperty("password", "root");
			connection = Dm.getConnection("jdbc:mysql://127.0.0.1:3306/qiyu", properties);

			System.out.println("***************");
			Dm.getDriver("jdbc:mysql://127.0.0.1:3306/qiyu");
			System.out.println("***************");

			java.sql.DatabaseMetaData databaseMetaData = connection.getMetaData();
			System.out.printf("%s.%s\t%s\t%s\n", databaseMetaData.getDatabaseMajorVersion(),
					databaseMetaData.getDatabaseMinorVersion(), databaseMetaData.getDefaultTransactionIsolation(),
					databaseMetaData.getDriverVersion());

			connection.setAutoCommit(false);
			statement = connection.createStatement();

			resultSet = statement.executeQuery("Select * from student0");

			while (resultSet.next()) {
				System.out.printf("%s\t%s\n", resultSet.getString(1), resultSet.getString(2));
			}

			java.sql.ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

			for (int column = 1; column <= resultSetMetaData.getColumnCount(); column++) {
				System.out.printf("%s\t%s\t%s\t%s\n", resultSetMetaData.getColumnName(column),
						resultSetMetaData.getColumnType(column), resultSetMetaData.getColumnDisplaySize(column),
						resultSetMetaData.getColumnClassName(column));
			}
			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	private static void testAccessController() {
		System.out.println("---------testAccessController--------");
		ServiceLoader<Driver> loadedDrivers = ServiceLoader.load(Driver.class);
		Iterator driversIterator = loadedDrivers.iterator();
		try {
			while (driversIterator.hasNext()) {
				System.out.println("test : " + driversIterator.next());
			}
		} catch (Throwable t) {

		}
		System.out.println("---------testAccessController--------");
		AccessController.doPrivileged(new PrivilegedAction<Void>() {
			public Void run() {
				ServiceLoader<Driver> loadedDrivers = ServiceLoader.load(Driver.class);
				Iterator driversIterator = loadedDrivers.iterator();
				try {
					while (driversIterator.hasNext()) {
						System.out.println("test Access Controller: " + driversIterator.next());
					}
				} catch (Throwable t) {

				}
				return null;
			}
		});
		System.out.println("---------testAccessController--------");
	}

	private static void testDriverManager() {
		try {
			Field[] fields = DriverManager.class.getDeclaredFields();
			for (Field field : fields) {
				System.out.printf("%s\t%s\t%s\n", Modifier.toString(field.getModifiers()), field.getName(),
						field.getType());
				if ("logWriter".equals(field.getName()) && field.isAccessible()) {
					field.set(DriverManager.class, new PrintWriter(System.out));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("---------------------");
		}

		try {
			Method[] methods = DriverManager.class.getDeclaredMethods();
			for (Method method : methods) {
				System.out.printf("%s\t%s\t%s\n", Modifier.toString(method.getModifiers()), method.getName(),
						method.getReturnType());
				if ("setLogWriter".equals(method.getName())) {
					System.out.println("=======invoke setLogWriter");
				}
				if ("getLogWriter".equals(method.getName())) {
					System.out.println("=======invoke getLogWriter");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("---------------------");
		}
	}
}

class MyStatic {
	public volatile static MyStatic m = new MyStatic();
	static {
		// System.out.println(m);
		System.out.println("static");
	}
	{
		System.out.println("code");
	}

	public MyStatic() {
		System.out.println("constructor");
	}

}
