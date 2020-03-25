package windows;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import common.LoginInfo;

import dao.EmpDao;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PWDUpdate extends JDialog {
	private JTextField txtOld;
	private JTextField txtNew;
	private JTextField txtConfirm;
	private EmpDao empDao = new EmpDao();
	private static PWDUpdate up= new PWDUpdate();
	public static PWDUpdate getInstance(){
		return up;
	}
	
public PWDUpdate() {
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	setTitle("\u4FEE\u6539\u5BC6\u7801");
setBounds(100, 100, 307, 207);
getContentPane().setLayout(null);
{
	JLabel lblNewLabel = new JLabel("\u539F\u5BC6\u7801\uFF1A");
	lblNewLabel.setBounds(34, 29, 54, 15);
	getContentPane().add(lblNewLabel);
}
{
	JLabel lblNewLabel_1 = new JLabel("\u65B0\u5BC6\u7801\uFF1A");
	lblNewLabel_1.setBounds(34, 58, 54, 15);
	getContentPane().add(lblNewLabel_1);
}
{
	JLabel lblNewLabel_2 = new JLabel("\u786E\u8BA4\u5BC6\u7801\uFF1A");
	lblNewLabel_2.setBounds(34, 91, 69, 15);
	getContentPane().add(lblNewLabel_2);
}
{
	txtOld = new JTextField();
	txtOld.setBounds(109, 26, 150, 21);
	getContentPane().add(txtOld);
	txtOld.setColumns(10);
}
{
	txtNew = new JTextField();
	txtNew.setBounds(109, 55, 150, 21);
	getContentPane().add(txtNew);
	txtNew.setColumns(10);
}
{
	txtConfirm = new JTextField();
	txtConfirm.setBounds(109, 88, 150, 21);
	getContentPane().add(txtConfirm);
	txtConfirm.setColumns(10);
}
{
	JButton btnUpdate = new JButton("\u4FEE\u6539");
	btnUpdate.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			/*
			 *`1.源密码ok
			 * 2.新密码为6位
			 * 3.原密码跟确认密码一致
		 */
			String old =txtOld.getText().trim();
	        String nw=txtNew.getText().trim();            String confirm=txtConfirm.getText().trim();			//判断
			if (!old.equals(LoginInfo.empPWD)) {
				JOptionPane.showMessageDialog(PWDUpdate.this, "原密码错误!", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			} else {
					//验证新密码的长度      if (nw.length()>0&&nw.length()==6){
        	if (nw.equals(confirm)) {					
		 if	(empDao.updateManagerPWD(nw, LoginInfo.empID)){
						JOptionPane.showMessageDialog(null, "新密码修改成功!", "提示", JOptionPane.INFORMATION_MESSAGE);
						//退出系统
						System.exit(0);
						//在线->离线						empDao.setLoginOff(LoginInfo.empID);
				} else {
					JOptionPane.showMessageDialog(PWDUpdate.this, "确认密码跟新密码不一致", "错误", JOptionPane.ERROR_MESSAGE);					return;
				}
        	}else {
        		JOptionPane.showMessageDialog(PWDUpdate.this, "密码长度有误!", "错误", JOptionPane.ERROR_MESSAGE);		      return;
				   }
        	}
      }
		}
});		btnUpdate.setBounds(3, 128, 93,23);
		getContentPane().add(btnUpdate);
		
	JButton btnCancel = new JButton("\u53D6\u6D88");
	btnCancel.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		dispose();
		}
	});
			btnCancel.setBounds(159, 128, 93,23);		getContentPane().add(btnCancel);
		}
}
public static void main(String[] args) {
	new PWDUpdate().setVisible(true);
}
}