package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.ConnectionManager;

public class DayMoneyDao {
	/**
	 * 根据某一天,获取当天的营业额
	 * @return String 当天的营业额
	 */
	public String getCurDayMoney(String curDay) {
		//声明返回变量
		String rtn="";
		//返回数据库连接对象
		Connection con=ConnectionManager.getCon();
		//声明PreparedStatement引用变量
		PreparedStatement ps=null;
		//声明ResultSet引用变量
		ResultSet rs=null;
		//sql
		String sql="SELECT money FROM daymoney WHERE DAY=?";
		try {
			//返回ps
			ps=con.prepareStatement(sql);
			//设置
			ps.setString(1, curDay);
			//执行
			rs=ps.executeQuery();
			//遍历
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
		
		//返回
		return rtn;
	}
	/**
	 * 获取DB当天的日期
	 * @return DB当天的日期
	 */
	public String getDBCurDay() {
		//声明返回变量
		String rtn="";
		//返回数据库连接对象
		Connection con=ConnectionManager.getCon();
		//声明PreparedStatement引用变量
		PreparedStatement ps=null;
		//声明ResultSet引用变量
		ResultSet rs=null;
		//sql
		String sql="SELECT CURDATE() AS DBCurDate";
	
		try {
			//返回ps
			ps=con.prepareStatement(sql);
			//执行
			rs=ps.executeQuery();
			//遍历
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
		//返回
		return rtn;
	}
	public boolean ifExistsDBDate(String dbDate) {
		//声明返回变量
		boolean result=false;
		//返回数据库连接对象
		Connection con=ConnectionManager.getCon();
		//声明PreparedStatement引用变量
		PreparedStatement ps=null;
		//声明ResultSet引用变量
		ResultSet rs=null;
		//sql
		String sql="SELECT COUNT(*)AS num FROM daymoney where day=?";
		try {
			//返回ps
			ps=con.prepareStatement(sql);
			ps.setString(1, dbDate);
			//执行
			rs=ps.executeQuery();
			//遍历
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
		//返回
		return result;
	}
	
	public void updateCurDayMoney(String dbDate,double curMoney) {
		Connection con=ConnectionManager.getCon();
		//声明PreparedStatement引用变量
		PreparedStatement ps=null;
		//sql
		String sql="UPDATE daymoney SET money=money+? where DAY=?";
		try {
			//返回ps
			ps=con.prepareStatement(sql);
			//执行
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
		//声明PreparedStatement引用变量
		PreparedStatement ps=null;
		//sql
		String sql="INSERT INTO daymoney VALUES(?,?)";
		try {
			//返回ps
			ps=con.prepareStatement(sql);
			//执行
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
		//声明PreparedStatement引用变量
		PreparedStatement ps=null;
		//声明ResultSet引用变量
		ResultSet rs=null;
		//sql
		String sql="CALL fuc2()";
	
		try {
			//返回ps
			ps=con.prepareStatement(sql);
			//执行
			rs=ps.executeQuery();
			//遍历
			if(rs.next()){
				time=rs.getString("种类");
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
			//声明PreparedStatement引用变量
			PreparedStatement ps=null;
			//声明ResultSet引用变量
			ResultSet rs=null;
			//sql
			String sql="CALL fuc1()";
		
			try {
				//返回ps
				ps=con.prepareStatement(sql);
				//执行
				rs=ps.executeQuery();
				//遍历
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
