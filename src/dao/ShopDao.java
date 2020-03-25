package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import common.ConnectionManager;
import entity.Shop;
import entity.shelf;
public class ShopDao {
	private Connection con = null;
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	//����
	public int insertShop(Shop shop){
		int rtn=0;
		//��ȡ���ݿ�����
		con = ConnectionManager.getCon();
		try {
			ps=con.prepareStatement("INSERT INTO shop VALUES(?,?,?,?,?,?,?,?)");
			//�����
			ps.setString(1, shop.getShelfID());
			ps.setString(2, shop.getShopID());
			ps.setString(3, shop.getShopName());
			ps.setDouble(4, shop.getCostPrice());
			ps.setDouble(5, shop.getSellPrice());
			ps.setInt(6, shop.getNum());
			ps.setInt(7, shop.getCategory());
			ps.setString(8, shop.getMarker());
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
	//�ж������Ƿ����
	public int getIDDublicate(String shopID){
		int rtn=0;
		//��ȡ���ݿ�����
		con = ConnectionManager.getCon();		
		try {
			ps=con.prepareStatement("SELECT COUNT(*) FROM shop WHERE shopID=?");
			ps.setString(1, shopID);
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
	//������Ʒ����
	public Vector<Vector<Object>> getShpLst(){
		//����һ�����ر���
		Vector<Vector<Object>> vV=new Vector<Vector<Object>>();
		//��ȡ���ݿ�����
		con = ConnectionManager.getCon();		
		String sql=null;
		sql=" SELECT";
		sql+=" shopID,";
		sql+=" shopName,";
		sql+=" costPrice,";
		sql+=" sellPrice,";
		sql+=" num,";
		sql+=" CASE WHEN category=1 THEN '��װʳƷ' WHEN category=2 THEN '�����̾�'  WHEN category=3 THEN '�߲�ˮ��'";
		sql+=" WHEN category=4 THEN '����' WHEN category=5 THEN '����'  WHEN category=6 THEN '�ճ���Ʒ'";
		sql+=" WHEN category=7 THEN '�칫��Ʒ' WHEN category=8 THEN 'ϴ����Ʒ'  WHEN category=9 THEN 'ɢװ�ӹ�' END AS typ,";
		sql+=" marker,";
		sql+=" category";
		sql+=" FROM";
		sql+=" shop";
		//String SQL="select * from view4 ";
		try {
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				Vector<Object> vec = new Vector<Object>();
				//shopID
				vec.add(rs.getString(1));
				//name
				vec.add(rs.getString(2));
				//����
				vec.add(rs.getDouble(3));
				//�ۼ�
				vec.add(rs.getDouble(4));
				//���
				vec.add(rs.getInt(5));
				//ת��������
				vec.add(rs.getString(6));
				//����
				vec.add(rs.getString(7));
				//���
				vec.add(rs.getInt(8));
				//����
				vV.add(vec);
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
		return vV;
	}
	//������Ʒ����(����������)
	public Vector<Vector<Object>> getShpLstSrch(String where){
		//����һ�����ر���
		Vector<Vector<Object>> vV=new Vector<Vector<Object>>();
		//��ȡ���ݿ�����
		con = ConnectionManager.getCon();
		String sql=null;
		sql=" SELECT";
		sql+=" shopID,";
		sql+=" shopName,";
		sql+=" costPrice,";
		sql+=" sellPrice,";
		sql+=" num,";
		sql+=" CASE WHEN category=1 THEN '��װʳƷ' WHEN category=2 THEN '�����̾�'  WHEN category=3 THEN '�߲�ˮ��'";
		sql+=" WHEN category=4 THEN '����' WHEN category=5 THEN '����'  WHEN category=6 THEN '�ճ���Ʒ'";
		sql+=" WHEN category=7 THEN '�칫��Ʒ' WHEN category=8 THEN 'ϴ����Ʒ'  WHEN category=9 THEN 'ɢװ�ӹ�' END AS typ,";
		sql+=" marker";
		sql+=" FROM";
		sql+=" shop";
		sql+=where;
		try {
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			//����
			while(rs.next()){
				Vector<Object> vec = new Vector<Object>();
				//id
				vec.add(rs.getString(1));
				//name
				vec.add(rs.getString(2));
				//����
				vec.add(rs.getDouble(3));
				//�ۼ�
				vec.add(rs.getDouble(4));
				//���
				vec.add(rs.getInt(5));
				//ת������б�
	        	vec.add(rs.getString(6));
				//����
				vec.add(rs.getString(7));
				//����
				vV.add(vec);
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
		return vV;
	}
	public int updateShp(Shop shop){
		int rtn=0;
		//��ȡ���ݿ�����
		con = ConnectionManager.getCon();		
		try {
			ps=con.prepareStatement("UPDATE shop SET shopID=?,shopName=?,costPrice=?,sellPrice=?,num=?,category=?,marker=?"
					+" WHERE shopID=?");
			//���ò���
			ps.setString(1, shop.getShopID());
			ps.setString(2, shop.getShopName());
			ps.setDouble(3, shop.getCostPrice());
			ps.setDouble(4, shop.getSellPrice());
			ps.setInt(5, shop.getNum());
			ps.setInt(6, shop.getCategory());
			ps.setString(7, shop.getMarker());
			ps.setString(8, shop.getShopID());
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
	//ɾ����Ʒ
	public int delShop(String shopID){
		int rtn=0;
		//��ȡ���ݿ�����
		con = ConnectionManager.getCon();		
		try {
			ps=con.prepareStatement("DELETE FROM shop WHERE shopID=?");
			ps.setString(1, shopID);
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
	//���ٿ��
	public int updateNum(String shpID,int number){
		//����һ�����ر���
		int rtn=0;
		//��ȡ���ݿ�����
		con = ConnectionManager.getCon();		
		//sql
		String sql="";
		sql+=" UPDATE ";
		sql+="  shop ";
		sql+=" SET ";
		sql+="  num = num-? ";
		sql+=" WHERE ";
		sql+="  shopID = ? ";
		try {
			//����ps����
			ps=con.prepareStatement(sql);
			//���ò���
			ps.setInt(1, number);
			ps.setString(2, shpID);
			//ִ��sql
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
		//����
		return rtn;
	}
	//�ж������Ƿ����
	public boolean ifShpIDExists(String shpID){
		//�������ر���
		boolean rtn=true;//true:����;false:������
		//��ȡ���ݿ�����
		con = ConnectionManager.getCon();		
		//sql
		String sql=" ";
		sql+=" SELECT ";
		sql+="  COUNT(shopID) AS 'shpID' ";
		sql+=" FROM ";
		sql+="  shop ";
		sql+=" WHERE ";
		sql+="  shopID=? ";
		try {
			//����ps����
			ps=con.prepareStatement(sql);
			//���ò���
			ps.setString(1, shpID);
			//ִ��
			rs=ps.executeQuery();
			//����
			if (rs.next()) {
				if (!(rs.getInt("shpID")>0)) {
					rtn=false;
				}
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
		//����
		return rtn;
	}
	//��������,��ȡ���Ƹ�����
	public Shop getShpNmPrc(String shpID){
		//�������ر���
		Shop shp=new Shop();
		//��ȡ���ݿ�����
		con = ConnectionManager.getCon();		
		//sql
		String sql="";
		sql+=" SELECT ";
		sql+="  shopName,";
		sql+="  sellPrice, ";
		sql+="  num ";
		sql+=" FROM ";
		sql+="  shop ";
		sql+=" WHERE ";
		sql+="  shopID=? ";
		try {	//����ps����
			ps=con.prepareStatement(sql);//���ò���
			ps.setString(1, shpID);//ִ��sql
			rs=ps.executeQuery();//����
			while(rs.next()){
				//����
				shp.setShopName(rs.getString(1));
				//�ۼ�
				shp.setSellPrice(rs.getDouble(2));
				//����
				shp.setNum(rs.getInt(3));
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
		//����
		return shp;
	}
	public void StorageReduceByShpID(String shopID, int buyNum) {
//���������޸�shopID��buyNum		
		Connection con=ConnectionManager.getCon();
		//����PreparedStatement���ñ���
		PreparedStatement ps=null;
		//����ResultSet���ñ���
		ResultSet rs=null;
		//sql
		String sql="UPDATE SHOP SET num=num-? WHERE shopID=?";
		try {
			//����ps
			ps=con.prepareStatement(sql);
			//ִ��
			ps.setInt(1, buyNum);
			ps.setString(2, shopID);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionManager.closeStatement(ps);
			ConnectionManager.closeCon(con);
		}	
		
	}
	}
	

