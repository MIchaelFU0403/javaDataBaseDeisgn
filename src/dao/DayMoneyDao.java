package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.ConnectionManager;

public class DayMoneyDao {
	/**
	 * ����ĳһ��,��ȡ�����Ӫҵ��
	 * @return String �����Ӫҵ��
	 */
	public String getCurDayMoney(String curDay) {
		//�������ر���
		String rtn="";
		//�������ݿ����Ӷ���
		Connection con=ConnectionManager.getCon();
		//����PreparedStatement���ñ���
		PreparedStatement ps=null;
		//����ResultSet���ñ���
		ResultSet rs=null;
		//sql
		String sql="SELECT money FROM daymoney WHERE DAY=?";
		try {
			//����ps
			ps=con.prepareStatement(sql);
			//����
			ps.setString(1, curDay);
			//ִ��
			rs=ps.executeQuery();
			//����
			if(rs.next()){
				rtn=String.valueOf(rs.getDouble("money"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionManager.closeStatement(ps);
			ConnectionManager.closeRS(rs);
			ConnectionManager.closeCon(con);
		}		
		
		//����
		return rtn;
	}
	/**
	 * ��ȡDB���������
	 * @return DB���������
	 */
	public String getDBCurDay() {
		//�������ر���
		String rtn="";
		//�������ݿ����Ӷ���
		Connection con=ConnectionManager.getCon();
		//����PreparedStatement���ñ���
		PreparedStatement ps=null;
		//����ResultSet���ñ���
		ResultSet rs=null;
		//sql
		String sql="SELECT CURDATE() AS DBCurDate";
	
		try {
			//����ps
			ps=con.prepareStatement(sql);
			//ִ��
			rs=ps.executeQuery();
			//����
			if(rs.next()){
				rtn=rs.getString("DBCurDate");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionManager.closeStatement(ps);
			ConnectionManager.closeRS(rs);
			ConnectionManager.closeCon(con);
		}	
		//����
		return rtn;
	}
	public boolean ifExistsDBDate(String dbDate) {
		//�������ر���
		boolean result=false;
		//�������ݿ����Ӷ���
		Connection con=ConnectionManager.getCon();
		//����PreparedStatement���ñ���
		PreparedStatement ps=null;
		//����ResultSet���ñ���
		ResultSet rs=null;
		//sql
		String sql="SELECT COUNT(*)AS num FROM daymoney where day=?";
		try {
			//����ps
			ps=con.prepareStatement(sql);
			ps.setString(1, dbDate);
			//ִ��
			rs=ps.executeQuery();
			//����
			if (rs.next()){
			if(rs.getInt("num")>0){
				result=true;
			}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionManager.closeStatement(ps);
			ConnectionManager.closeRS(rs);
			ConnectionManager.closeCon(con);
		}	
		//����
		return result;
	}
	
	public void updateCurDayMoney(String dbDate,double curMoney) {
		Connection con=ConnectionManager.getCon();
		//����PreparedStatement���ñ���
		PreparedStatement ps=null;
		//sql
		String sql="UPDATE daymoney SET money=money+? where DAY=?";
		try {
			//����ps
			ps=con.prepareStatement(sql);
			//ִ��
			ps.setDouble(1, curMoney);
			ps.setString(2, dbDate);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionManager.closeStatement(ps);
			ConnectionManager.closeCon(con);
		}	
		
	}
	public void insertCurDayMoney(String dbDate,double curMoney) {
		Connection con=ConnectionManager.getCon();
		//����PreparedStatement���ñ���
		PreparedStatement ps=null;
		//sql
		String sql="INSERT INTO daymoney VALUES(?,?)";
		try {
			//����ps
			ps=con.prepareStatement(sql);
			//ִ��
			ps.setDouble(2, curMoney);
			ps.setString(1, dbDate);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionManager.closeStatement(ps);
			ConnectionManager.closeCon(con);
		}	
		
	}
	public String getT() {
	  String time = "";
		Connection con=ConnectionManager.getCon();
		//����PreparedStatement���ñ���
		PreparedStatement ps=null;
		//����ResultSet���ñ���
		ResultSet rs=null;
		//sql
		String sql="CALL fuc2()";
	
		try {
			//����ps
			ps=con.prepareStatement(sql);
			//ִ��
			rs=ps.executeQuery();
			//����
			if(rs.next()){
				time=rs.getString("����");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionManager.closeStatement(ps);
			ConnectionManager.closeRS(rs);
			ConnectionManager.closeCon(con);
		}	
	  
		return time;
	}
	public String getcharacter() {
		 String time = "";
			Connection con=ConnectionManager.getCon();
			//����PreparedStatement���ñ���
			PreparedStatement ps=null;
			//����ResultSet���ñ���
			ResultSet rs=null;
			//sql
			String sql="CALL fuc1()";
		
			try {
				//����ps
				ps=con.prepareStatement(sql);
				//ִ��
				rs=ps.executeQuery();
				//����
				if(rs.next()){
					time=rs.getString("F");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				ConnectionManager.closeStatement(ps);
				ConnectionManager.closeRS(rs);
				ConnectionManager.closeCon(con);
			}	
		  
			return time;
		}
	
}
