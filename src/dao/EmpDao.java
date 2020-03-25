package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JLabel;

import common.ConnectionManager;
import entity.Emp;

public class EmpDao {
	/**
	 * 员工插入
	 * @param emp 员工对象
	 * @return boolean true:插入成功;false:失败
	 */
	public boolean empInsert(Emp emp) {
		//存储影响结果集个数
		int count=0; 
		//返回数据库连接对象
		Connection con=ConnectionManager.getCon();
		//声明PreparedStatement引用变量
		PreparedStatement ps=null;
		//sql
		String sql="INSERT INTO emp(empID,empName,empSex,empAge,empPhone,empAddress,empPower,empPWD ) VALUES(?,?,?,?,?,?,?,?)";
		try {
			//返回ps对象
			ps=con.prepareStatement(sql);
			//设置参数
			//编号
			ps.setString(1, emp.getEmpID());
			//姓名
			ps.setString(2, emp.getEmpName());
			//性别
			ps.setInt(3, emp.getEmpSex());
			//年龄
			ps.setInt(4, emp.getEmpAge());
			//手机号
			ps.setString(5, emp.getEmpPhone());
			//地址
			ps.setString(6, emp.getEmpAddress());
			//权限
			ps.setInt(7, emp.getEmpPower());
			//密码
			ps.setString(8, emp.getEmpPWD());
			//执行sql
			count=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionManager.closeStatement(ps);
			ConnectionManager.closeCon(con);
		}
		//返回
		return count>0;
	}

	/**
	 * 判断编号是否存在
	 * @param empID 要判断的编号
	 * @return boolean true:存在;false:不存在
	 */
	public boolean ifEmpIDRepeat(String empID) {
		//声明返回变量
		boolean rtn=false;
		//返回数据库连接对象
		Connection con=ConnectionManager.getCon();
		//声明PreparedStatement引用变量
		PreparedStatement ps=null;
		//声明ResultSet引用变量
		ResultSet rs=null;
		//sql
		String sql="SELECT COUNT(*) AS empIDCount FROM  emp WHERE empID=?";
		try {
			//返回ps对象
			ps=con.prepareStatement(sql);
			//设置参数
			ps.setString(1, empID);
			//执行
			rs=ps.executeQuery();
			//遍历rs
			if (rs.next()) {
				if (rs.getInt("empIDCount")>0) {
					//设置返回变量
					rtn=true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			ConnectionManager.closeStatement(ps);
			ConnectionManager.closeRS(rs);
			ConnectionManager.closeCon(con);
		}
		//返回
		return rtn;
	}
	/**
	 * 获取所有的员工的集合(vector的形式)
	 * @return Vector<Vector<Object>>  empVec
	 */
	public Vector<Vector<Object>> getAllEmpInfo() {
		//声明返回变量
		Vector<Vector<Object>>  rtn= new Vector<Vector<Object>>();
		//返回数据库连接对象
		Connection con=ConnectionManager.getCon();
		//声明PreparedStatement引用变量
		PreparedStatement ps=null;
		//声明ResultSet引用变量
		ResultSet rs=null;
		//sql
		
		//String sql="SELECT empID,empName,CASE WHEN 
		//empSex=1 THEN '男'  WHEN empSex=0 THEN '女'
		//END AS 'sexTxt',empAge,empPhone,empAddress,
		//CASE WHEN empPower=1 THEN '管理员' WHEN 
		//empPower=2 THEN '收银员' END AS 'powerTxt',
		//empSex,empPower FROM emp";
		String sql="SELECT * FROM VIEW1";
		try {
			//返回ps对象
			ps=con.prepareStatement(sql);
			//执行
			rs=ps.executeQuery();
			//遍历rs
			while(rs.next()){
				//创建Vector对象
				Vector<Object> v= new Vector<Object>();
				//设置编号
				v.add(rs.getInt("empID"));
				//设置姓名
				v.add(rs.getString("empName"));
				//设置性别的文本
				v.add(rs.getString("sexTxt"));
				//设置年龄
				v.add(rs.getInt("empAge"));
				//设置手机号
				v.add(rs.getString("empPhone"));
				//设置地址
				v.add(rs.getString("empAddress"));
				//设置权限的文本
				v.add(rs.getString("powerTxt"));
				//设置性别
				v.add(rs.getInt("empSex"));
				//设置权限
				v.add(rs.getInt("empPower"));
				//搁置
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
	/**
	 * 根据员工编号,修改员工信息
	 * @param emp 封装的员工信息
	 * @return
	 */
	public boolean empUpdate(Emp emp) {
		//存储影响结果集个数
		int count=0;
		//返回数据库连接对象
		Connection con=ConnectionManager.getCon();
		//声明PreparedStatement引用变量
		PreparedStatement ps=null;
		//sql
		String sql="UPDATE emp SET empName=?,empSex=?,empAge=?,empPhone=?,empAddress=?,empPower=? WHERE empID=?";
		try {
			//返回ps对象
			ps=con.prepareStatement(sql);
			//设置参数
			//姓名
			ps.setString(1, emp.getEmpName());
			//性别
			ps.setInt(2, emp.getEmpSex());
			//年龄
			ps.setInt(3, emp.getEmpAge());
			//手机号
			ps.setString(4, emp.getEmpPhone());
			//地址
			ps.setString(5, emp.getEmpAddress());
			//权限
			ps.setInt(6, emp.getEmpPower());
			//编号
			ps.setString(7, emp.getEmpID());
			//执行
			count=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionManager.closeStatement(ps);
			ConnectionManager.closeCon(con);
		}
		//返回
		return count>0;
	}
	/**
	 * 根据员工编号,删除员工
	 * @param empId 要删的员工编号 
	 */
	public void empDel(String empId) {
		//返回数据库连接对象
		Connection con=ConnectionManager.getCon();
		//声明PreparedStatement引用变量
		PreparedStatement ps=null;
		//sql
		String sql="DELETE FROM emp WHERE empID=?";
		try {
			//返回ps对象
			ps=con.prepareStatement(sql);
			//设置参数
			ps.setString(1, empId);
			//执行
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionManager.closeStatement(ps);
			ConnectionManager.closeCon(con);
		}
	}
	/**
	 * 重置该员工的密码
	 * @param empID 要重置密码的员工编号
	 * @return boolean true:成功;false:失败
	 */
	public boolean empPWDReset(String empID) {
		//存储影响结果集个数
		int count=0;
		//返回数据库连接对象
		Connection con=ConnectionManager.getCon();
		//声明PreparedStatement引用变量
		PreparedStatement ps=null;
		//sql
		String sql="UPDATE emp SET empPWD='000000' WHERE empID=?";
		try {
			//返回ps对象
			ps=con.prepareStatement(sql);
			//设置参数
			ps.setString(1, empID);
			//执行
			count=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionManager.closeStatement(ps);
			ConnectionManager.closeCon(con);
		}
		//返回
		return count>0;
	}
	/**
	 * 根据查询条件,获取员工列表(检索画面)
	 * @return Vector<Vector<Object>> 员工列表
	 */
	public Vector<Vector<Object>> getEmpInfoSrch(String where) {
		//声明返回变量
		Vector<Vector<Object>>  rtn= new Vector<Vector<Object>>();
		//返回数据库连接对象
		Connection con=ConnectionManager.getCon();
		//声明PreparedStatement引用变量
		PreparedStatement ps=null;
		//声明ResultSet引用变量
		ResultSet rs=null;
		//sql
		//String sql="SELECT empID,empName,CASE WHEN empSex=1 THEN '男'  WHEN empSex=0 THEN '女' END AS 'sexTxt',empAge,empPhone,empAddress,CASE WHEN empPower=1 THEN '管理员' WHEN empPower=2 THEN '收银员' END AS 'powerTxt',empSex,CASE WHEN empStatus=1 THEN '在线' WHEN empStatus=2 THEN '离线' END AS 'statusTxt' FROM emp ";
		String sql="SELECT * FROM VIEW5";
		sql+=where;
		try {
			//返回ps对象
			ps=con.prepareStatement(sql);
			//执行
			rs=ps.executeQuery();
			//遍历rs
			while(rs.next()){
				//创建Vector对象
				Vector<Object> v= new Vector<Object>();
				//设置编号
				v.add(rs.getInt("empID"));
				//设置姓名
				v.add(rs.getString("empName"));
				//设置性别的文本
				v.add(rs.getString("sexTxt"));
				//设置年龄
				v.add(rs.getInt("empAge"));
				//设置手机号
				v.add(rs.getString("empPhone"));
				//设置地址
				v.add(rs.getString("empAddress"));
				//设置权限的文本
				v.add(rs.getString("powerTxt"));
				//设置状态文本
				v.add(rs.getString("statusTxt"));
				//搁置
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
	/**
	 * 判断管理员是否存在
	 * @param empID 编号
	 * @param empPWD 密码
	 * @return boolean true:存在;false:不存在;
	 */
	public boolean ifExistsManager(String empID, String empPWD) {
		//声明返回变量
		boolean result=false;
		//返回数据库连接对象
		Connection con=ConnectionManager.getCon();
		//声明PreparedStatement引用变量
		PreparedStatement ps=null;
		//声明ResultSet引用变量
		ResultSet rs=null;
		//sql
		String sql="SELECT COUNT(*) AS num FROM emp WHERE empID=? AND empPWD=? AND empPower=1";
		try {
			//返回ps对象
			ps=con.prepareStatement(sql);
			//设置参数
			ps.setString(1, empID);
			ps.setString(2, empPWD);
			//执行
			rs=ps.executeQuery();
			//遍历rs
			if (rs.next()) {
				if (rs.getInt("num")>0) {
					//设置返回变量
					result=true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			ConnectionManager.closeStatement(ps);
			ConnectionManager.closeRS(rs);
			ConnectionManager.closeCon(con);
		}
		//返回
		return result;
	}
	/**
	 * 判断收银员是否存在
	 * @param empID 编号
	 * @param empPWD 密码
	 * @return boolean true:存在;false:不存在;
	 */
	public boolean ifExistsRush(String empID, String empPWD) {
		//声明返回变量
		boolean result=false;
		//返回数据库连接对象
		Connection con=ConnectionManager.getCon();
		//声明PreparedStatement引用变量
		PreparedStatement ps=null;
		//声明ResultSet引用变量
		ResultSet rs=null;
		//sql
		String sql="SELECT COUNT(*) AS num FROM emp WHERE empID=? AND empPWD=? AND empPower=2 AND empLoginOn=1";
		
		try {
			//返回ps对象
			ps=con.prepareStatement(sql);
			//设置参数
			ps.setString(1, empID);
			ps.setString(2, empPWD);
			//执行
			rs=ps.executeQuery();
			//遍历rs
			if (rs.next()) {
				if (rs.getInt("num")>0) {
					//设置返回变量
					result=true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			ConnectionManager.closeStatement(ps);
			ConnectionManager.closeRS(rs);
			ConnectionManager.closeCon(con);
		}
		//返回
		return result;
	}
	/**
	 * 把当前用户离线->在线
	 * @param empID 员工编号
	 */
	public boolean setLoginOn(String empID) {
		//存储影响结果集个数
		int count=0;
		//返回数据库连接对象
		Connection con=ConnectionManager.getCon();
		//声明PreparedStatement引用变量
		PreparedStatement ps=null;
		//sql
		String sql="UPDATE emp SET empStatus=1 WHERE empID=?";
		try {
			//返回ps对象
			ps=con.prepareStatement(sql);
			//设置参数
			ps.setString(1, empID);
			//执行
			count=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionManager.closeStatement(ps);
			ConnectionManager.closeCon(con);
		}
		//返回
		return count>0;
		
	}
	/**
	 * 开启所有收银员的服务
	 */
	public void setServiceOn() {
		//返回数据库连接对象
		Connection con=ConnectionManager.getCon();
		//声明PreparedStatement引用变量
		PreparedStatement ps=null;
		//sql
		String sql="UPDATE emp SET empLoginOn=1 WHERE empPower=2 ";
		try {
			//返回ps对象
			ps=con.prepareStatement(sql);
			//执行
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionManager.closeStatement(ps);
			ConnectionManager.closeCon(con);
		}
	}
	/**
	 * 关闭所有收银员的服务
	 */
	public void setServiceOff() {
		//返回数据库连接对象
		Connection con=ConnectionManager.getCon();
		//声明PreparedStatement引用变量
		PreparedStatement ps=null;
		//sql
		String sql="UPDATE emp SET empLoginOn=0 WHERE empPower=2 ";
		try {
			//返回ps对象
			ps=con.prepareStatement(sql);
			//执行
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionManager.closeStatement(ps);
			ConnectionManager.closeCon(con);
		}
	}
	/**
	 *  在线->离线
	 * @param empID
	 */
	public void setLoginOff(String empID) {
		//返回数据库连接对象
		Connection con=ConnectionManager.getCon();
		//声明PreparedStatement引用变量
		PreparedStatement ps=null;
		//sql
		String sql="UPDATE emp SET empStatus=2 WHERE empID=?";
		try {
			//返回ps对象
			ps=con.prepareStatement(sql);
			//设置参数
			ps.setString(1, empID);
			//执行
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionManager.closeStatement(ps);
			ConnectionManager.closeCon(con);
		}
		
	}
	/**
	 * 统计在线的收银员人数
	 * @return 在线的收银员人数
	 */
	public int getOnLineRushNum() {
		//声明返回变量
		int rtn=0;
		//返回数据库连接对象
		Connection con=ConnectionManager.getCon();
		//声明PreparedStatement引用变量
		PreparedStatement ps=null;
		//声明ResultSet引用变量
		ResultSet rs=null;
		//sql  使用视图
		//String sql="SELECT COUNT(*) AS num FROM emp WHERE empPower=2 AND empStatus=1";
		String sql="select COUNT(*) AS num from view2";
		try {
			//返回ps对象
			ps=con.prepareStatement(sql);
			//执行
			rs=ps.executeQuery();
			//遍历rs
			if (rs.next()) {
				rtn=rs.getInt("num");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			ConnectionManager.closeStatement(ps);
			ConnectionManager.closeRS(rs);
			ConnectionManager.closeCon(con);
		}		
		//返回rtn
		return rtn;
	}
	/**
	 * 修改管理员的密码
	 * @param nw 修改的密码
	 * @param empID 员工编号
	 * @return
	 */
	    public boolean updateManagerPWD(String nw, String empID) {
		//存储影响结果集个数
		int count=0;
		//返回数据库连接对象
		Connection con=ConnectionManager.getCon();
		//声明PreparedStatement引用变量
		PreparedStatement ps=null;
		//sql
		String sql="UPDATE emp SET empPWD=? WHERE empID=?";
		try {
			//返回ps对象
			ps=con.prepareStatement(sql);
			//设置参数
			ps.setString(1, nw);
			ps.setString(2, empID);
			//执行
			count=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionManager.closeStatement(ps);
			ConnectionManager.closeCon(con);
		}		
		//返回
		return count>0;
	}
}
