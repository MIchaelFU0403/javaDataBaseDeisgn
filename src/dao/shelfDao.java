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
		//�������ر���
				Vector<Vector<Object>>  rtn= new Vector<Vector<Object>>();
				//�������ݿ����Ӷ���
				Connection con=ConnectionManager.getCon();
				//����PreparedStatement���ñ���
				PreparedStatement ps=null;
				//����ResultSet���ñ���
				ResultSet rs=null;
				//sql
				//String sql="SELECT shelfID, shelfLayer FROM shelf";
				String sql="select *from view6";
				try {	//����ps����
					ps=con.prepareStatement(sql);
					//ִ��
					rs=ps.executeQuery();
					//����rs
					while(rs.next()){
						//����Vector����
						Vector<Object> v= new Vector<Object>();
						//���ñ��
						v.add(rs.getString("shelfID"));//"shelfID"
						//���ò���
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
				//����
				return rtn;
			}


	public  static int insertShelf(shelf sf) {
		int rtn=0;
		//��ȡ���ݿ�����
		Connection con = ConnectionManager.getCon();
		PreparedStatement ps=null;
		try {
			ps=con.prepareStatement("INSERT INTO shelf VALUES(?,?)");
			//�����
			ps.setString(1, sf.getShelfID());
			ps.setString(2, sf.getShelfLayer());
			rtn =ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//�ر�ps
			ConnectionManager.closeStatement(ps);
			//�ر����ݿ�����
			ConnectionManager.closeCon(con);
		}
		return rtn;
	}


	public static  int getIDDublicate(String strID) {
		int rtn=0;
		//��ȡ���ݿ�����
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
			//�ر�rs
			ConnectionManager.closeRS(rs);
			//�ر�ps
			ConnectionManager.closeStatement(ps);
			//�ر����ݿ�����
			ConnectionManager.closeCon(con);
		}
		return rtn;
	}


	public int delshelf(String shelfID) {
		int rtn=0;
		//��ȡ���ݿ�����
		con = ConnectionManager.getCon();		
		try {
			ps=con.prepareStatement("DELETE FROM shelf WHERE shelfID=?");
			ps.setString(1, shelfID);
			rtn=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//�ر�ps
			ConnectionManager.closeStatement(ps);
			//�ر����ݿ�����
			ConnectionManager.closeCon(con);
		}
		return rtn;
	}
	}
	
	
