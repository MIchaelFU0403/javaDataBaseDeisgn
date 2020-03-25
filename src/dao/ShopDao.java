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
	//插入
	public int insertShop(Shop shop){
		int rtn=0;
		//获取数据库连接
		con = ConnectionManager.getCon();
		try {
			ps=con.prepareStatement("INSERT INTO shop VALUES(?,?,?,?,?,?,?,?)");
			//设参数
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
			//关闭ps
			ConnectionManager.closeStatement(ps);
			//关闭数据库连接
			ConnectionManager.closeCon(con);
		}
		return rtn;
	}
	//判断条码是否存在
	public int getIDDublicate(String shopID){
		int rtn=0;
		//获取数据库连接
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
			//关闭rs
			ConnectionManager.closeRS(rs);
			//关闭ps
			ConnectionManager.closeStatement(ps);
			//关闭数据库连接
			ConnectionManager.closeCon(con);
		}
		return rtn;
	}
	//检索商品数据
	public Vector<Vector<Object>> getShpLst(){
		//声明一个返回变量
		Vector<Vector<Object>> vV=new Vector<Vector<Object>>();
		//获取数据库连接
		con = ConnectionManager.getCon();		
		String sql=null;
		sql=" SELECT";
		sql+=" shopID,";
		sql+=" shopName,";
		sql+=" costPrice,";
		sql+=" sellPrice,";
		sql+=" num,";
		sql+=" CASE WHEN category=1 THEN '包装食品' WHEN category=2 THEN '饮料烟酒'  WHEN category=3 THEN '蔬菜水果'";
		sql+=" WHEN category=4 THEN '粮油' WHEN category=5 THEN '肉类'  WHEN category=6 THEN '日常用品'";
		sql+=" WHEN category=7 THEN '办公用品' WHEN category=8 THEN '洗涤用品'  WHEN category=9 THEN '散装加工' END AS typ,";
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
				//进价
				vec.add(rs.getDouble(3));
				//售价
				vec.add(rs.getDouble(4));
				//库存
				vec.add(rs.getInt(5));
				//转换后的类别
				vec.add(rs.getString(6));
				//厂商
				vec.add(rs.getString(7));
				//类别
				vec.add(rs.getInt(8));
				//搁置
				vV.add(vec);
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
		return vV;
	}
	//检索商品数据(检索画面用)
	public Vector<Vector<Object>> getShpLstSrch(String where){
		//声明一个返回变量
		Vector<Vector<Object>> vV=new Vector<Vector<Object>>();
		//获取数据库连接
		con = ConnectionManager.getCon();
		String sql=null;
		sql=" SELECT";
		sql+=" shopID,";
		sql+=" shopName,";
		sql+=" costPrice,";
		sql+=" sellPrice,";
		sql+=" num,";
		sql+=" CASE WHEN category=1 THEN '包装食品' WHEN category=2 THEN '饮料烟酒'  WHEN category=3 THEN '蔬菜水果'";
		sql+=" WHEN category=4 THEN '粮油' WHEN category=5 THEN '肉类'  WHEN category=6 THEN '日常用品'";
		sql+=" WHEN category=7 THEN '办公用品' WHEN category=8 THEN '洗涤用品'  WHEN category=9 THEN '散装加工' END AS typ,";
		sql+=" marker";
		sql+=" FROM";
		sql+=" shop";
		sql+=where;
		try {
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			//遍历
			while(rs.next()){
				Vector<Object> vec = new Vector<Object>();
				//id
				vec.add(rs.getString(1));
				//name
				vec.add(rs.getString(2));
				//进价
				vec.add(rs.getDouble(3));
				//售价
				vec.add(rs.getDouble(4));
				//库存
				vec.add(rs.getInt(5));
				//转换后的列表
	        	vec.add(rs.getString(6));
				//厂商
				vec.add(rs.getString(7));
				//搁置
				vV.add(vec);
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
		return vV;
	}
	public int updateShp(Shop shop){
		int rtn=0;
		//获取数据库连接
		con = ConnectionManager.getCon();		
		try {
			ps=con.prepareStatement("UPDATE shop SET shopID=?,shopName=?,costPrice=?,sellPrice=?,num=?,category=?,marker=?"
					+" WHERE shopID=?");
			//设置参数
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
			//关闭ps
			ConnectionManager.closeStatement(ps);
			//关闭数据库连接
			ConnectionManager.closeCon(con);
		}
		return rtn;
	}
	//删除商品
	public int delShop(String shopID){
		int rtn=0;
		//获取数据库连接
		con = ConnectionManager.getCon();		
		try {
			ps=con.prepareStatement("DELETE FROM shop WHERE shopID=?");
			ps.setString(1, shopID);
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
	//减少库存
	public int updateNum(String shpID,int number){
		//声明一个返回变量
		int rtn=0;
		//获取数据库连接
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
			//创建ps对象
			ps=con.prepareStatement(sql);
			//设置参数
			ps.setInt(1, number);
			ps.setString(2, shpID);
			//执行sql
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
		//返回
		return rtn;
	}
	//判断条码是否存在
	public boolean ifShpIDExists(String shpID){
		//声明返回变量
		boolean rtn=true;//true:存在;false:不存在
		//获取数据库连接
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
			//创建ps对象
			ps=con.prepareStatement(sql);
			//设置参数
			ps.setString(1, shpID);
			//执行
			rs=ps.executeQuery();
			//遍历
			if (rs.next()) {
				if (!(rs.getInt("shpID")>0)) {
					rtn=false;
				}
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
		//返回
		return rtn;
	}
	//根据条码,获取名称跟单价
	public Shop getShpNmPrc(String shpID){
		//声明返回变量
		Shop shp=new Shop();
		//获取数据库连接
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
		try {	//创建ps对象
			ps=con.prepareStatement(sql);//设置参数
			ps.setString(1, shpID);//执行sql
			rs=ps.executeQuery();//遍历
			while(rs.next()){
				//名称
				shp.setShopName(rs.getString(1));
				//售价
				shp.setSellPrice(rs.getDouble(2));
				//数量
				shp.setNum(rs.getInt(3));
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
		//返回
		return shp;
	}
	public void StorageReduceByShpID(String shopID, int buyNum) {
//根据条码修改shopID和buyNum		
		Connection con=ConnectionManager.getCon();
		//声明PreparedStatement引用变量
		PreparedStatement ps=null;
		//声明ResultSet引用变量
		ResultSet rs=null;
		//sql
		String sql="UPDATE SHOP SET num=num-? WHERE shopID=?";
		try {
			//返回ps
			ps=con.prepareStatement(sql);
			//执行
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
	

