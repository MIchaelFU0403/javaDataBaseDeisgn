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
	 * Ա������
	 * @param emp Ա������
	 * @return boolean true:����ɹ�;false:ʧ��
	 */
	public boolean empInsert(Emp emp) {
		//�洢Ӱ����������
		int count=0; 
		//�������ݿ����Ӷ���
		Connection con=ConnectionManager.getCon();
		//����PreparedStatement���ñ���
		PreparedStatement ps=null;
		//sql
		String sql="INSERT INTO emp(empID,empName,empSex,empAge,empPhone,empAddress,empPower,empPWD ) VALUES(?,?,?,?,?,?,?,?)";
		try {
			//����ps����
			ps=con.prepareStatement(sql);
			//���ò���
			//���
			ps.setString(1, emp.getEmpID());
			//����
			ps.setString(2, emp.getEmpName());
			//�Ա�
			ps.setInt(3, emp.getEmpSex());
			//����
			ps.setInt(4, emp.getEmpAge());
			//�ֻ���
			ps.setString(5, emp.getEmpPhone());
			//��ַ
			ps.setString(6, emp.getEmpAddress());
			//Ȩ��
			ps.setInt(7, emp.getEmpPower());
			//����
			ps.setString(8, emp.getEmpPWD());
			//ִ��sql
			count=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionManager.closeStatement(ps);
			ConnectionManager.closeCon(con);
		}
		//����
		return count>0;
	}

	/**
	 * �жϱ���Ƿ����
	 * @param empID Ҫ�жϵı��
	 * @return boolean true:����;false:������
	 */
	public boolean ifEmpIDRepeat(String empID) {
		//�������ر���
		boolean rtn=false;
		//�������ݿ����Ӷ���
		Connection con=ConnectionManager.getCon();
		//����PreparedStatement���ñ���
		PreparedStatement ps=null;
		//����ResultSet���ñ���
		ResultSet rs=null;
		//sql
		String sql="SELECT COUNT(*) AS empIDCount FROM  emp WHERE empID=?";
		try {
			//����ps����
			ps=con.prepareStatement(sql);
			//���ò���
			ps.setString(1, empID);
			//ִ��
			rs=ps.executeQuery();
			//����rs
			if (rs.next()) {
				if (rs.getInt("empIDCount")>0) {
					//���÷��ر���
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
		//����
		return rtn;
	}
	/**
	 * ��ȡ���е�Ա���ļ���(vector����ʽ)
	 * @return Vector<Vector<Object>>  empVec
	 */
	public Vector<Vector<Object>> getAllEmpInfo() {
		//�������ر���
		Vector<Vector<Object>>  rtn= new Vector<Vector<Object>>();
		//�������ݿ����Ӷ���
		Connection con=ConnectionManager.getCon();
		//����PreparedStatement���ñ���
		PreparedStatement ps=null;
		//����ResultSet���ñ���
		ResultSet rs=null;
		//sql
		
		//String sql="SELECT empID,empName,CASE WHEN 
		//empSex=1 THEN '��'  WHEN empSex=0 THEN 'Ů'
		//END AS 'sexTxt',empAge,empPhone,empAddress,
		//CASE WHEN empPower=1 THEN '����Ա' WHEN 
		//empPower=2 THEN '����Ա' END AS 'powerTxt',
		//empSex,empPower FROM emp";
		String sql="SELECT * FROM VIEW1";
		try {
			//����ps����
			ps=con.prepareStatement(sql);
			//ִ��
			rs=ps.executeQuery();
			//����rs
			while(rs.next()){
				//����Vector����
				Vector<Object> v= new Vector<Object>();
				//���ñ��
				v.add(rs.getInt("empID"));
				//��������
				v.add(rs.getString("empName"));
				//�����Ա���ı�
				v.add(rs.getString("sexTxt"));
				//��������
				v.add(rs.getInt("empAge"));
				//�����ֻ���
				v.add(rs.getString("empPhone"));
				//���õ�ַ
				v.add(rs.getString("empAddress"));
				//����Ȩ�޵��ı�
				v.add(rs.getString("powerTxt"));
				//�����Ա�
				v.add(rs.getInt("empSex"));
				//����Ȩ��
				v.add(rs.getInt("empPower"));
				//����
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
	/**
	 * ����Ա�����,�޸�Ա����Ϣ
	 * @param emp ��װ��Ա����Ϣ
	 * @return
	 */
	public boolean empUpdate(Emp emp) {
		//�洢Ӱ����������
		int count=0;
		//�������ݿ����Ӷ���
		Connection con=ConnectionManager.getCon();
		//����PreparedStatement���ñ���
		PreparedStatement ps=null;
		//sql
		String sql="UPDATE emp SET empName=?,empSex=?,empAge=?,empPhone=?,empAddress=?,empPower=? WHERE empID=?";
		try {
			//����ps����
			ps=con.prepareStatement(sql);
			//���ò���
			//����
			ps.setString(1, emp.getEmpName());
			//�Ա�
			ps.setInt(2, emp.getEmpSex());
			//����
			ps.setInt(3, emp.getEmpAge());
			//�ֻ���
			ps.setString(4, emp.getEmpPhone());
			//��ַ
			ps.setString(5, emp.getEmpAddress());
			//Ȩ��
			ps.setInt(6, emp.getEmpPower());
			//���
			ps.setString(7, emp.getEmpID());
			//ִ��
			count=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionManager.closeStatement(ps);
			ConnectionManager.closeCon(con);
		}
		//����
		return count>0;
	}
	/**
	 * ����Ա�����,ɾ��Ա��
	 * @param empId Ҫɾ��Ա����� 
	 */
	public void empDel(String empId) {
		//�������ݿ����Ӷ���
		Connection con=ConnectionManager.getCon();
		//����PreparedStatement���ñ���
		PreparedStatement ps=null;
		//sql
		String sql="DELETE FROM emp WHERE empID=?";
		try {
			//����ps����
			ps=con.prepareStatement(sql);
			//���ò���
			ps.setString(1, empId);
			//ִ��
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
	 * ���ø�Ա��������
	 * @param empID Ҫ���������Ա�����
	 * @return boolean true:�ɹ�;false:ʧ��
	 */
	public boolean empPWDReset(String empID) {
		//�洢Ӱ����������
		int count=0;
		//�������ݿ����Ӷ���
		Connection con=ConnectionManager.getCon();
		//����PreparedStatement���ñ���
		PreparedStatement ps=null;
		//sql
		String sql="UPDATE emp SET empPWD='000000' WHERE empID=?";
		try {
			//����ps����
			ps=con.prepareStatement(sql);
			//���ò���
			ps.setString(1, empID);
			//ִ��
			count=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionManager.closeStatement(ps);
			ConnectionManager.closeCon(con);
		}
		//����
		return count>0;
	}
	/**
	 * ���ݲ�ѯ����,��ȡԱ���б�(��������)
	 * @return Vector<Vector<Object>> Ա���б�
	 */
	public Vector<Vector<Object>> getEmpInfoSrch(String where) {
		//�������ر���
		Vector<Vector<Object>>  rtn= new Vector<Vector<Object>>();
		//�������ݿ����Ӷ���
		Connection con=ConnectionManager.getCon();
		//����PreparedStatement���ñ���
		PreparedStatement ps=null;
		//����ResultSet���ñ���
		ResultSet rs=null;
		//sql
		//String sql="SELECT empID,empName,CASE WHEN empSex=1 THEN '��'  WHEN empSex=0 THEN 'Ů' END AS 'sexTxt',empAge,empPhone,empAddress,CASE WHEN empPower=1 THEN '����Ա' WHEN empPower=2 THEN '����Ա' END AS 'powerTxt',empSex,CASE WHEN empStatus=1 THEN '����' WHEN empStatus=2 THEN '����' END AS 'statusTxt' FROM emp ";
		String sql="SELECT * FROM VIEW5";
		sql+=where;
		try {
			//����ps����
			ps=con.prepareStatement(sql);
			//ִ��
			rs=ps.executeQuery();
			//����rs
			while(rs.next()){
				//����Vector����
				Vector<Object> v= new Vector<Object>();
				//���ñ��
				v.add(rs.getInt("empID"));
				//��������
				v.add(rs.getString("empName"));
				//�����Ա���ı�
				v.add(rs.getString("sexTxt"));
				//��������
				v.add(rs.getInt("empAge"));
				//�����ֻ���
				v.add(rs.getString("empPhone"));
				//���õ�ַ
				v.add(rs.getString("empAddress"));
				//����Ȩ�޵��ı�
				v.add(rs.getString("powerTxt"));
				//����״̬�ı�
				v.add(rs.getString("statusTxt"));
				//����
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
	/**
	 * �жϹ���Ա�Ƿ����
	 * @param empID ���
	 * @param empPWD ����
	 * @return boolean true:����;false:������;
	 */
	public boolean ifExistsManager(String empID, String empPWD) {
		//�������ر���
		boolean result=false;
		//�������ݿ����Ӷ���
		Connection con=ConnectionManager.getCon();
		//����PreparedStatement���ñ���
		PreparedStatement ps=null;
		//����ResultSet���ñ���
		ResultSet rs=null;
		//sql
		String sql="SELECT COUNT(*) AS num FROM emp WHERE empID=? AND empPWD=? AND empPower=1";
		try {
			//����ps����
			ps=con.prepareStatement(sql);
			//���ò���
			ps.setString(1, empID);
			ps.setString(2, empPWD);
			//ִ��
			rs=ps.executeQuery();
			//����rs
			if (rs.next()) {
				if (rs.getInt("num")>0) {
					//���÷��ر���
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
		//����
		return result;
	}
	/**
	 * �ж�����Ա�Ƿ����
	 * @param empID ���
	 * @param empPWD ����
	 * @return boolean true:����;false:������;
	 */
	public boolean ifExistsRush(String empID, String empPWD) {
		//�������ر���
		boolean result=false;
		//�������ݿ����Ӷ���
		Connection con=ConnectionManager.getCon();
		//����PreparedStatement���ñ���
		PreparedStatement ps=null;
		//����ResultSet���ñ���
		ResultSet rs=null;
		//sql
		String sql="SELECT COUNT(*) AS num FROM emp WHERE empID=? AND empPWD=? AND empPower=2 AND empLoginOn=1";
		
		try {
			//����ps����
			ps=con.prepareStatement(sql);
			//���ò���
			ps.setString(1, empID);
			ps.setString(2, empPWD);
			//ִ��
			rs=ps.executeQuery();
			//����rs
			if (rs.next()) {
				if (rs.getInt("num")>0) {
					//���÷��ر���
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
		//����
		return result;
	}
	/**
	 * �ѵ�ǰ�û�����->����
	 * @param empID Ա�����
	 */
	public boolean setLoginOn(String empID) {
		//�洢Ӱ����������
		int count=0;
		//�������ݿ����Ӷ���
		Connection con=ConnectionManager.getCon();
		//����PreparedStatement���ñ���
		PreparedStatement ps=null;
		//sql
		String sql="UPDATE emp SET empStatus=1 WHERE empID=?";
		try {
			//����ps����
			ps=con.prepareStatement(sql);
			//���ò���
			ps.setString(1, empID);
			//ִ��
			count=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionManager.closeStatement(ps);
			ConnectionManager.closeCon(con);
		}
		//����
		return count>0;
		
	}
	/**
	 * ������������Ա�ķ���
	 */
	public void setServiceOn() {
		//�������ݿ����Ӷ���
		Connection con=ConnectionManager.getCon();
		//����PreparedStatement���ñ���
		PreparedStatement ps=null;
		//sql
		String sql="UPDATE emp SET empLoginOn=1 WHERE empPower=2 ";
		try {
			//����ps����
			ps=con.prepareStatement(sql);
			//ִ��
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
	 * �ر���������Ա�ķ���
	 */
	public void setServiceOff() {
		//�������ݿ����Ӷ���
		Connection con=ConnectionManager.getCon();
		//����PreparedStatement���ñ���
		PreparedStatement ps=null;
		//sql
		String sql="UPDATE emp SET empLoginOn=0 WHERE empPower=2 ";
		try {
			//����ps����
			ps=con.prepareStatement(sql);
			//ִ��
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
	 *  ����->����
	 * @param empID
	 */
	public void setLoginOff(String empID) {
		//�������ݿ����Ӷ���
		Connection con=ConnectionManager.getCon();
		//����PreparedStatement���ñ���
		PreparedStatement ps=null;
		//sql
		String sql="UPDATE emp SET empStatus=2 WHERE empID=?";
		try {
			//����ps����
			ps=con.prepareStatement(sql);
			//���ò���
			ps.setString(1, empID);
			//ִ��
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
	 * ͳ�����ߵ�����Ա����
	 * @return ���ߵ�����Ա����
	 */
	public int getOnLineRushNum() {
		//�������ر���
		int rtn=0;
		//�������ݿ����Ӷ���
		Connection con=ConnectionManager.getCon();
		//����PreparedStatement���ñ���
		PreparedStatement ps=null;
		//����ResultSet���ñ���
		ResultSet rs=null;
		//sql  ʹ����ͼ
		//String sql="SELECT COUNT(*) AS num FROM emp WHERE empPower=2 AND empStatus=1";
		String sql="select COUNT(*) AS num from view2";
		try {
			//����ps����
			ps=con.prepareStatement(sql);
			//ִ��
			rs=ps.executeQuery();
			//����rs
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
		//����rtn
		return rtn;
	}
	/**
	 * �޸Ĺ���Ա������
	 * @param nw �޸ĵ�����
	 * @param empID Ա�����
	 * @return
	 */
	    public boolean updateManagerPWD(String nw, String empID) {
		//�洢Ӱ����������
		int count=0;
		//�������ݿ����Ӷ���
		Connection con=ConnectionManager.getCon();
		//����PreparedStatement���ñ���
		PreparedStatement ps=null;
		//sql
		String sql="UPDATE emp SET empPWD=? WHERE empID=?";
		try {
			//����ps����
			ps=con.prepareStatement(sql);
			//���ò���
			ps.setString(1, nw);
			ps.setString(2, empID);
			//ִ��
			count=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionManager.closeStatement(ps);
			ConnectionManager.closeCon(con);
		}		
		//����
		return count>0;
	}
}
