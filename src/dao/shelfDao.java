package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import common.ConnectionManager;
import entity.shelf;

public class shelfDao {
	private Connection con = null;
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	
	public static Vector<Vector<Object>> getAllInfo() {
		//声明返回变量
				Vector<Vector<Object>>  rtn= new Vector<Vector<Object>>();
				//返回数据库连接对象
				Connection con=ConnectionManager.getCon();
				//声明PreparedStatement引用变量
				PreparedStatement ps=null;
				//声明ResultSet引用变量
				ResultSet rs=null;
				//sql
				//String sql="SELECT shelfID, shelfLayer FROM shelf";
				String sql="select *from view6";
				try {	//返回ps对象
					ps=con.prepareStatement(sql);
					//执行
					rs=ps.executeQuery();
					//遍历rs
					while(rs.next()){
						//创建Vector对象
						Vector<Object> v= new Vector<Object>();
						//设置编号
						v.add(rs.getString("shelfID"));//"shelfID"
						//设置层数
						v.add(rs.getInt("shelfLayer"));//"shelfLayer"
						rtn.add(v);
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


	public  static int insertShelf(shelf sf) {
		int rtn=0;
		//获取数据库连接
		Connection con = ConnectionManager.getCon();
		PreparedStatement ps=null;
		try {
			ps=con.prepareStatement("INSERT INTO shelf VALUES(?,?)");
			//设参数
			ps.setString(1, sf.getShelfID());
			ps.setString(2, sf.getShelfLayer());
			rtn =ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//关闭ps
			ConnectionManager.closeStatement(ps);
			//关闭数据库连接
			ConnectionManager.closeCon(con);
		}
		return rtn;
	}


	public static  int getIDDublicate(String strID) {
		int rtn=0;
		//获取数据库连接
		Connection con = ConnectionManager.getCon();
		PreparedStatement ps=null;
		 ResultSet rs=null;
		try {
			ps=con.prepareStatement("SELECT COUNT(*) FROM shelf WHERE shelfID=?");
			ps.setString(1, strID);
			rs=ps.executeQuery();
			if(rs.next()){
				rtn=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//关闭rs
			ConnectionManager.closeRS(rs);
			//关闭ps
			ConnectionManager.closeStatement(ps);
			//关闭数据库连接
			ConnectionManager.closeCon(con);
		}
		return rtn;
	}


	public int delshelf(String shelfID) {
		int rtn=0;
		//获取数据库连接
		con = ConnectionManager.getCon();		
		try {
			ps=con.prepareStatement("DELETE FROM shelf WHERE shelfID=?");
			ps.setString(1, shelfID);
			rtn=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//关闭ps
			ConnectionManager.closeStatement(ps);
			//关闭数据库连接
			ConnectionManager.closeCon(con);
		}
		return rtn;
	}
	}
	
	
