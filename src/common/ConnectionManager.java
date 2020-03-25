package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionManager {
	//driver
	private static  final String DRIVER="com.mysql.jdbc.Driver";
	//url
	private static final String URL="jdbc:mysql://localhost:3306/supermarket";
	//user
	private static final String USER="root";
	//pwd
	private static final String PWD="123456";
	
	//返回数据库连接对象
	public static Connection getCon(){
		//声明返回变量
		Connection con=null;
		try {
			//注册驱动
			Class.forName(DRIVER);
			//返回数据库连接
			con=DriverManager.getConnection(URL, USER, PWD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//返回
		return con;
	}
	//关闭st
	public static void closeStatement(Statement st){
		try {
			//判断是否关闭过
			if (st!=null&&!st.isClosed()) {
				//关闭
				st.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//关闭con
	public static void closeCon(Connection con){
		try {
			//判断是否关闭过
			if (con!=null&&!con.isClosed()) {
				con.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//关闭rs
	public static void closeRS(ResultSet rs){
		try {
			//判断是否关闭过
			if (rs!=null&&!rs.isClosed()) {
				rs.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
