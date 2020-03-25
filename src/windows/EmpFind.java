package windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import dao.EmpDao;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class EmpFind extends JFrame {
	private JTextField txtID;
	private JTextField txtName;
	private JTextField txtPhone;
	private JTable table;
	private final ButtonGroup bgSex = new ButtonGroup();
	private final ButtonGroup bgPower = new ButtonGroup();
	private EmpDao empDao=new EmpDao();
	public EmpFind() {
		setTitle("\u67E5\u770B\u5458\u5DE5\u4FE1\u606F");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setSize(600, 430);
		txtID = new JTextField();
		txtID.setBounds(58, 31, 139, 21);
		getContentPane().add(txtID);
		txtID.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("\u7F16\u53F7\uFF1A");
		lblNewLabel.setBounds(21, 34, 54, 15);
		getContentPane().add(lblNewLabel);
		
		JLabel label = new JLabel("\u59D3\u540D\uFF1A");
		label.setBounds(279, 35, 54, 15);
		getContentPane().add(label);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(321, 31, 100, 21);
		getContentPane().add(txtName);
		
		JLabel label_1 = new JLabel("\u6027\u522B\uFF1A");
		label_1.setBounds(21, 73, 54, 15);
		getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("\u6743\u9650\uFF1A");
		label_2.setBounds(277, 71, 54, 15);
		getContentPane().add(label_2);
		
		JRadioButton rdoMale = new JRadioButton("\u7537");
		bgSex.add(rdoMale);
		rdoMale.setBounds(57, 69, 39, 23);
		getContentPane().add(rdoMale);
		
		JRadioButton rdoFemale = new JRadioButton("\u5973");
		bgSex.add(rdoFemale);
		rdoFemale.setBounds(98, 69, 121, 23);
		getContentPane().add(rdoFemale);
		
		JRadioButton rdoRush = new JRadioButton("\u6536\u94F6\u5458");
		bgPower.add(rdoRush);
		rdoRush.setBounds(399, 69, 121, 23);
		getContentPane().add(rdoRush);
		
		JRadioButton rdoManager = new JRadioButton("\u7BA1\u7406\u5458");
		bgPower.add(rdoManager);
		rdoManager.setBounds(321, 69, 76, 23);
		getContentPane().add(rdoManager);
		
		txtPhone = new JTextField();
		txtPhone.setColumns(10);
		txtPhone.setBounds(58, 113, 139, 21);
		getContentPane().add(txtPhone);
		
		JLabel label_3 = new JLabel("\u7535\u8BDD\uFF1A");
		label_3.setBounds(21, 116, 54, 15);
		getContentPane().add(label_3);
		
		JButton btnReset = new JButton("\u590D\u4F4D");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fillTbl("");
				clearSrchArea();
			}
		});
		btnReset.setBounds(213, 112, 93, 23);
		getContentPane().add(btnReset);
		
		JButton btnSearch = new JButton("\u67E5\u8BE2");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//存储where部分
				String where="WHERE 1=1";
				//获取编号
				String empID =txtID.getText().trim();
			if(empID.length()>0){
				where+=" AND empID="+empID;
			}//获取姓名
			String empName=txtName.getText().trim();
			//判断姓名是否为空
			if(empName.length()>0){
				where+=" AND empName LIKE '%"+empName+"%'";
			}
			//获取性别
			if(rdoMale.isSelected()){
				where+=" AND empSex=1";
			}else if(rdoFemale.isSelected()){
				where+=" AND empSex=0";
			  }
			//获取权选
			if(rdoManager.isSelected()){
				where+=" AND empPower=1";
			}else if(rdoRush.isSelected()){
				where+=" AND empPower=2";
			  }
			String empPhone=txtPhone.getText().trim();
			if(empPhone.length()>0){
			where+=" AND empPhone LIKE'%"+empPhone+"%'";
			}
			fillTbl(where);
			
			}
		});
		btnSearch.setBounds(321, 112, 93, 23);
		getContentPane().add(btnSearch);
		
		JButton btnCancel = new JButton("\u53D6\u6D88");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(424, 112, 93, 23);
		getContentPane().add(btnCancel);
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
			},
			new String[] {
				"\u7F16\u53F7", "\u59D3\u540D", "\u6027\u522B", "\u5E74\u9F84", "\u624B\u673A", "\u5730\u5740", "\u6743\u9650", "\u72B6\u6001"
			}
		));	
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 145, 564, 245);
		getContentPane().add(scrollPane);
		scrollPane.setViewportView(table);
		//加载数据
		fillTbl("");
	}
	//给table提供model
		private void fillTbl(String where){
		//返回table的model属性
		DefaultTableModel dtm=(DefaultTableModel)table.getModel();
		//清空的空白行
		dtm.setRowCount(0);
		//根据查询条件返回本次的员工列表
		Vector<Vector<Object>> vV=empDao.getEmpInfoSrch(where);
		//遍历vV
		for (Vector<Object> v : vV) {
			dtm.addRow(v);
			}
		
		}
		//清空查询区域
		private void clearSrchArea(){
			//设置编号为空
			txtID.setText("");
			//设置姓名为空
			txtName.setText("");
			//手机号
			txtPhone.setText("");
			//清楚选择性别的单选扭
			bgSex.clearSelection();
			//清除权限的单选扭
			bgPower.clearSelection();
		}
		public static void main(String[] args) {
			new EmpFind().setVisible(true);
		}
}
