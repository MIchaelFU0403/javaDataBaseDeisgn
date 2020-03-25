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
	
	//�������ݿ����Ӷ���
	public static Connection getCon(){
		//�������ر���
		Connection con=null;
		try {
			//ע������
			Class.forName(DRIVER);
			//�������ݿ�����
			con=DriverManager.getConnection(URL, USER, PWD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//����
		return con;
	}
	//�ر�st
	public static void closeStatement(Statement st){
		try {
			//�ж��Ƿ�رչ�
			if (st!=null&&!st.isClosed()) {
				//�ر�
				st.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//�ر�con
	public static void closeCon(Connection con){
		try {
			//�ж��Ƿ�رչ�
			if (con!=null&&!con.isClosed()) {
				con.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//�ر�rs
	public static void closeRS(ResultSet rs){
		try {
			//�ж��Ƿ�رչ�
			if (rs!=null&&!rs.isClosed()) {
				rs.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
