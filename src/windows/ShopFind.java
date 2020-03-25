package windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.ShopDao;

public class ShopFind extends JFrame{
	private JTextField txtID;
	private JTextField txtName;
	private JTextField txtNum;
	private JTable table;
	private ShopDao shpDao=new ShopDao();
	private JComboBox cboSign;
	private JComboBox cboCategory;
	public ShopFind() {
		setTitle("\u5546\u54C1\u4FE1\u606F\u67E5\u770B");
		setSize(460,350);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u6761\u7801\uFF1A");
		lblNewLabel.setBounds(20, 24, 54, 15);
		getContentPane().add(lblNewLabel);
		
		JLabel label = new JLabel("\u540D\u79F0\uFF1A");
		label.setBounds(227, 24, 54, 15);
		getContentPane().add(label);
		
		JLabel l = new JLabel("\u5E93\u5B58\uFF1A");
		l.setBounds(20, 62, 54, 15);
		getContentPane().add(l);
		
		JLabel label_2 = new JLabel("\u5206\u7C7B\uFF1A");
		label_2.setBounds(227, 62, 54, 15);
		getContentPane().add(label_2);
		
		txtID = new JTextField();
		txtID.setColumns(10);
		txtID.setBounds(53, 21, 149, 21);
		getContentPane().add(txtID);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(261, 21, 127, 21);
		getContentPane().add(txtName);
		
		txtNum = new JTextField();
		txtNum.setColumns(10);
		txtNum.setBounds(117, 59, 77, 21);
		getContentPane().add(txtNum);
		
		JComboBox cboCategory = new JComboBox();
		cboCategory.setModel(new DefaultComboBoxModel(new String[] {"\u5305\u88C5\u98DF\u54C1", "\u996E\u6599\u70DF\u9152", "\u852C\u83DC\u6C34\u679C", "\u7CAE\u6CB9", "\u8089\u7C7B", "\u65E5\u5E38\u7528\u54C1", "\u529E\u516C\u7528\u54C1", "\u6D17\u6DA4\u7528\u54C1", "\u6563\u88C5\u52A0\u5DE5", "\t"}));
		cboCategory.setBounds(271, 59, 90, 21);
		getContentPane().add(cboCategory);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 127, 414, 165);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"\u6761\u7801", "\u540D\u79F0", "\u8FDB\u4EF7", "\u552E\u4EF7", "\u5E93\u5B58\u91CF", "\u5206\u7C7B", "\u5382\u5BB6"
			}
		));
		
		scrollPane.setViewportView(table);
          fillTbl("");
	
		JButton btnReset = new JButton("\u590D\u4F4D");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				fillTbl("");
				clearTxt();
			}});
		btnReset.setBounds(120, 94, 93, 23);
		getContentPane().add(btnReset);
		
		JButton btnSearch = new JButton("\u67E5\u8BE2");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//拼接Where部分的sql
				String where =" WHERE 1=1 ";
				//条码
				String strID= txtID.getText().trim();
				if(strID.length()>0){
					where+=" AND shopID="+strID;
				}
				//名称
				String strName=txtName.getText().trim();
				if(strName.length()>0){
					where+=" AND shopName like '%"+strName+"%'";
				}
				//取库存量符号位    cboSign.getItemAt(cboSign.getSelectedIndex())
				String strSign = String.valueOf(cboSign.getSelectedItem());
				
					//库存量
				String strNum = txtNum.getText().trim();
				if(strNum.length()>0){
					where+=" AND num "+strSign+strNum;
				}
				//分类
				if(cboCategory.getSelectedIndex()>0){
					String strCategory= String.valueOf(cboCategory.getSelectedIndex());
					where+=" AND category= "+strCategory;
				}
				//查询*/
				fillTbl(where);
			}
		});
		
		btnSearch.setBounds(228, 94, 93, 23);
		getContentPane().add(btnSearch);
		
		JButton btnCancel = new JButton("\u53D6\u6D88");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			dispose();
			}
		});
		btnCancel.setBounds(331, 94, 93, 23);
		getContentPane().add(btnCancel);
		
		JComboBox cboSign = new JComboBox();
		cboSign.setModel(new DefaultComboBoxModel(new String[] {">", ">=", "<", "<="}));;
		cboSign.setBounds(63, 59, 62, 21);
		getContentPane().add(cboSign);
        fillTbl("");
		
		
	}
	
//给table提供model
	
private void fillTbl(String where){
	DefaultTableModel dtm= (DefaultTableModel) table.getModel();
	dtm.setRowCount(0);
	Vector<Vector<Object>> vV= shpDao.getShpLstSrch(where);
	//遍历vV
	for (int i = 0; i < vV.size(); i++) {
		//添加
		dtm.addRow(vV.get(i));
	}
}
//清空查询区域
public void clearTxt(){
	txtID.setText("");
	txtName.setText("");
	txtNum.setText("");
	//cboSign.setSelectedIndex(0);
//	cboCategory.setSelectedIndex(0);
	
}
public static void main(String[] args) {
	new ShopFind().setVisible(true);
}
}