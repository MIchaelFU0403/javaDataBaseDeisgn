package windows;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import common.LoginInfo;
import common.MyDefaultTableModel;
import dao.EmpDao;
import entity.Emp;

public class EmpUpdate extends JFrame {

	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtPhone;
	private JTextField txtAddresss;
	private JTextField txtName;
	private JTextField txtAge;
	private JRadioButton rdoMale;
	private JRadioButton rdoFemale;
	private JRadioButton rdoRush;
	private JRadioButton rdoManager;
	private JTable table;
	//依赖"empDao"
	private EmpDao empDao= new EmpDao();

	/**
	 * Create the frame.
	 */
	public EmpUpdate() {
		setTitle("\u5458\u5DE5\u7EF4\u62A4\u4FE1\u606F");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 465, 399);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u7F16\u53F7\uFF1A");
		lblNewLabel.setBounds(24, 10, 54, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u6027\u522B\uFF1A");
		lblNewLabel_1.setBounds(24, 43, 54, 15);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u7535\u8BDD\uFF1A");
		lblNewLabel_2.setBounds(24, 71, 54, 15);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\u5730\u5740\uFF1A");
		lblNewLabel_3.setBounds(24, 98, 54, 15);
		contentPane.add(lblNewLabel_3);
		
		txtID = new JTextField();
		txtID.setBackground(Color.PINK);
		txtID.setEnabled(false);
		txtID.setBounds(72, 7, 66, 21);
		contentPane.add(txtID);
		txtID.setColumns(10);
		
		rdoMale = new JRadioButton("\u7537");
		rdoMale.setBounds(72, 39, 47, 23);
		contentPane.add(rdoMale);
		
		rdoFemale = new JRadioButton("\u5973");
		rdoFemale.setBounds(121, 39, 66, 23);
		contentPane.add(rdoFemale);
		ButtonGroup bgSex = new ButtonGroup();
		bgSex.add(rdoMale);
		bgSex.add(rdoFemale);
		
		txtPhone = new JTextField();
		txtPhone.setBackground(Color.PINK);
		txtPhone.setBounds(72, 68, 130, 21);
		contentPane.add(txtPhone);
		txtPhone.setColumns(10);
		
		txtAddresss = new JTextField();
		txtAddresss.setBounds(72, 96, 322, 21);
		contentPane.add(txtAddresss);
		txtAddresss.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("\u59D3\u540D\uFF1A");
		lblNewLabel_4.setBounds(234, 10, 54, 15);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("\u5E74\u9F84\uFF1A");
		lblNewLabel_5.setBounds(234, 43, 54, 15);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("\u6743\u9650\uFF1A");
		lblNewLabel_6.setBounds(234, 71, 54, 15);
		contentPane.add(lblNewLabel_6);
		
		txtName = new JTextField();
		txtName.setBackground(Color.PINK);
		txtName.setBounds(289, 7, 105, 21);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		txtAge = new JTextField();
		txtAge.setBounds(289, 40, 66, 21);
		contentPane.add(txtAge);
		txtAge.setColumns(10);
		
		rdoRush = new JRadioButton("\u6536\u94F6\u5458");
		rdoRush.setBounds(285, 67, 61, 23);
		contentPane.add(rdoRush);
		
		rdoManager = new JRadioButton("\u7BA1\u7406\u5458");
		rdoManager.setBounds(348, 67, 88, 23);
		contentPane.add(rdoManager);
		ButtonGroup bgPower = new ButtonGroup();
		bgPower.add(rdoManager);
		bgPower.add(rdoRush);
		
		JButton btnPWDReset = new JButton("\u5BC6\u7801\u590D\u4F4D");
		btnPWDReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//弹出一个对话框
				int rtn=JOptionPane.showConfirmDialog(EmpUpdate.this, "你确定要密码重置吗?", "提示信息", JOptionPane.YES_NO_OPTION);
				if(rtn==JOptionPane.YES_OPTION){
					//获取编号
					String empID=txtID.getText();
					//密码重置
					boolean result= empDao.empPWDReset(empID);
					//判断
					if (result) {
						JOptionPane.showMessageDialog(EmpUpdate.this, "密码重置成功!", "提示信息", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		btnPWDReset.setBounds(10, 123, 99, 23);
		contentPane.add(btnPWDReset);
		
		JButton btnUpdate = new JButton("\u4FEE\u6539");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//创建Emp对象
				Emp emp= new Emp();
				//需要做的check如下:
				//获取编号
				String empID=txtID.getText().trim();
				emp.setEmpID(empID);
				//获取姓名
				String empName= txtName.getText().trim();
				//姓名不能为空！
				if (empName!=null&&empName.length()>0) {
					//设置姓名
					emp.setEmpName(empName);
				} else {
					//弹出一个错误对话框
					JOptionPane.showMessageDialog(EmpUpdate.this, "姓名必须录入!", "提示信息", JOptionPane.ERROR_MESSAGE);
					return;
				}
				//获取性别
				//判断
				if (rdoMale.isSelected()) {
					//设置性别
					emp.setEmpSex(1);
				} else if(rdoFemale.isSelected()){
					//设置性别
					emp.setEmpSex(0);
					
				}
				//获取年龄,并存储年龄
				String empAge=txtAge.getText();
				if (empAge.length()>0) {
					//设置年龄
					emp.setEmpAge(Integer.parseInt(empAge));
				}
				//获取手机号
				String empPhone= txtPhone.getText().trim();
				//手机号不能为空
				if (empPhone!=null&&empPhone.length()>0) {
					//手机号必须为11位
					if (empPhone.length()==11) {
						//设置手机号
						emp.setEmpPhone(empPhone);
					} else {
						//弹出一个错误对话框
						JOptionPane.showMessageDialog(EmpUpdate.this, "手机号必须为11位!", "提示信息", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
				} else {
					//弹出一个错误对话框
					JOptionPane.showMessageDialog(EmpUpdate.this, "手机号必须输入!", "提示信息", JOptionPane.ERROR_MESSAGE);
					return;
				}
				//获取地址,并储存
				String empAddress=txtAddresss.getText().trim();
				//设置地址
				emp.setEmpAddress(empAddress);
				//判断是否为管理员
				if (rdoManager.isSelected()) {
					//设置权限
					emp.setEmpPower(1);
				} else if(rdoRush.isSelected()){
					//设置权限
					emp.setEmpPower(2);
				}
				//员工修改;true:成功;false:失败
				if (empDao.empUpdate(emp)) {
					//弹出一个错误对话框
					JOptionPane.showMessageDialog(EmpUpdate.this, "员工修改成功!", "提示信息", JOptionPane.INFORMATION_MESSAGE);
				}
				//刷新table
				fillTbl();
			}
		});
		btnUpdate.setBounds(129, 123, 85, 23);
		contentPane.add(btnUpdate);
		
		JButton btnDel = new JButton("\u5220\u9664");
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//获取选中的行号
				int curRow=table.getSelectedRow();
				//如果没有选中行的话
				if (curRow==-1) {
					JOptionPane.showMessageDialog(EmpUpdate.this, "没有选中删除的员工", "提示", JOptionPane.ERROR_MESSAGE);
					return;
				}
				//获取要删除编号
				String empID=txtID.getText();
				//当前用户不能删除
				if (empID.equals(LoginInfo.empID)) {
					JOptionPane.showMessageDialog(EmpUpdate.this, "当前员工不能删除!", "提示", JOptionPane.ERROR_MESSAGE);
					return;
				}
				//弹出对话框
				int rtn=JOptionPane.showConfirmDialog(EmpUpdate.this, "你确定要删除该员工吗?", "提示信息", JOptionPane.YES_NO_OPTION);
				//判断,如果单击的是"yes"
				if (rtn==JOptionPane.YES_OPTION) {
					//删除
					empDao.empDel(empID);
					//刷新表格
					fillTbl();
					//选中第一行
					table.setRowSelectionInterval(0, 0);
					//把选中行的数据代入维护区域
					setValue();
				}
			}
		});
		btnDel.setBounds(234, 123, 85, 23);
		contentPane.add(btnDel);
		
		JButton btnCancel = new JButton("\u53D6\u6D88");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//关闭当前的窗口
				dispose();
			}
		});
		btnCancel.setBounds(338, 123, 85, 23);
		contentPane.add(btnCancel);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//把选中行的数据代入维护区域
				setValue();
			}
		});
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 156, 452, 371);
		contentPane.add(scrollPane);
		table.setModel(new MyDefaultTableModel(
				new Object[][] {
						{null, null, null, null, null, null, null, null,null},
						{null, null, null, null, null, null, null, null,null},
						{null, null, null, null, null, null, null, null,null},
					},
				new String[] {
					"\u7F16\u53F7", "\u59D3\u540D", "\u6027\u522B", "\u5E74\u9F84", "\u624B\u673A\u53F7", "\u5730\u5740", "\u6743\u9650", "sex", "power"
				}
			));
		
		table.getColumnModel().getColumn(7).setPreferredWidth(0);
		table.getColumnModel().getColumn(7).setMinWidth(0);
		table.getColumnModel().getColumn(7).setMaxWidth(0);
		table.getColumnModel().getColumn(8).setPreferredWidth(0);
		table.getColumnModel().getColumn(8).setMinWidth(0);
		table.getColumnModel().getColumn(8).setMaxWidth(0);
		table.setBounds(0, 0, 1, 1);
		//给table加载数据
		fillTbl();
	}
	//给table提供model
	private void fillTbl(){
		//返回table的model属性
		DefaultTableModel dtm=(DefaultTableModel)table.getModel();
		//清空的空白行
		dtm.setRowCount(0);
		//返回本次的员工列表
		Vector<Vector<Object>> vV=empDao.getAllEmpInfo();
		//遍历vV
		for (Vector<Object> v : vV) {
			dtm.addRow(v);
		}
	}
	/**
	 * 把选中行的数据代入维护区域
	 */
	private void setValue(){
		//返回table的model属性
		DefaultTableModel dtm= (DefaultTableModel)table.getModel();
		//返回table被选择的行号
		int curRow= table.getSelectedRow();
		//设置编号
		txtID.setText(String.valueOf(dtm.getValueAt(curRow, 0)));
		//设置姓名
		txtName.setText(String.valueOf(dtm.getValueAt(curRow, 1)));
		//存储当前的性别
		String sex=String.valueOf(dtm.getValueAt(curRow, 7));
		//判断
		if (sex.equals("1")) {
			rdoMale.setSelected(true);
		} else if(sex.equals("0")) {
			rdoFemale.setSelected(true);
		}
		//设置年龄
		txtAge.setText(String.valueOf(dtm.getValueAt(curRow, 3)));
		//设置电话
		txtPhone.setText(String.valueOf(dtm.getValueAt(curRow, 4)));
		//设置权限
		String power= String.valueOf(dtm.getValueAt(curRow, 8));
		if (power.equals("1")) {
			rdoManager.setSelected(true);
		} else if(power.equals("2")){
			rdoRush.setSelected(true);
		}
		//设置地址
		txtAddresss.setText(String.valueOf(dtm.getValueAt(curRow, 5)));
	}
public static void main(String[] args) {
	new EmpUpdate().setVisible(true);
}}
