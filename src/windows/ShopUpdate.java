package windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import common.MyDefaultTableModel;

import dao.ShopDao;
import entity.Shop;
import java.awt.Toolkit;

public class ShopUpdate extends JFrame {

	private JPanel contentPane;
	private JTextField txtCstPrc;
	private JTextField txtName;
	private JTextField txtSllPrc;
	private JTextField txtStorage;
	private JTextField txtMarker;
	private JTextField txtID;
	private JTable table;
	private JComboBox cboCategory;
	private ShopDao shpDao=new ShopDao();
	private Shop shop = new Shop();
/*	private static ShopUpdate us= new ShopUpdate();
	//返回
	public static ShopUpdate getInstance(){
		return us;
	}*/

	/**
	 * Create the frame.
	 */
	public ShopUpdate() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ShopUpdate.class.getResource("/images/login_icon.png")));
		setTitle("\u7EF4\u62A4\u5546\u54C1\u5E93\u5B58");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 522, 369);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u6761\u7801\uFF1A");
		lblNewLabel.setBounds(22, 10, 54, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u540D\u79F0\uFF1A\r\n");
		lblNewLabel_1.setBounds(213, 12, 54, 15);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u8FDB\u4EF7\uFF1A");
		lblNewLabel_2.setBounds(22, 41, 54, 15);
		contentPane.add(lblNewLabel_2);
		
		txtCstPrc = new JTextField();
		txtCstPrc.setBounds(66, 35, 77, 21);
		contentPane.add(txtCstPrc);
		txtCstPrc.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("\u552E\u4EF7\uFF1A");
		lblNewLabel_3.setBounds(213, 43, 54, 15);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("\u5E93\u5B58\u91CF\uFF1A");
		lblNewLabel_4.setBounds(22, 66, 54, 15);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("\u5206\u7C7B\uFF1A");
		lblNewLabel_5.setBounds(213, 68, 54, 15);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("\u5382\u5BB6\uFF1A");
		lblNewLabel_6.setBounds(22, 91, 54, 15);
		contentPane.add(lblNewLabel_6);
		
		txtName = new JTextField();
		txtName.setBounds(256, 9, 129, 21);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		txtSllPrc = new JTextField();
		txtSllPrc.setBounds(256, 37, 66, 21);
		contentPane.add(txtSllPrc);
		txtSllPrc.setColumns(10);
		
		txtStorage = new JTextField();
		txtStorage.setBounds(66, 66, 77, 21);
		contentPane.add(txtStorage);
		txtStorage.setColumns(10);
		
		cboCategory = new JComboBox();
		cboCategory.setModel(new DefaultComboBoxModel(new String[] {"\u8BF7\u9009\u62E9", "\u5305\u88C5\u98DF\u54C1 ", "\u996E\u6599\u70DF\u9152 ", "\u852C\u83DC\u6C34\u679C ", "\u7CAE\u6CB9 ", "\u8089\u7C7B ", "\u65E5\u5E38\u7528\u54C1 ", "\u529E\u516C\u7528\u54C1 ", "\u6D17\u6DA4\u7528\u54C1 ", "\u6563\u88C5\u52A0\u5DE5"}));
		cboCategory.setBounds(256, 65, 129, 21);
		contentPane.add(cboCategory);
		
		txtMarker = new JTextField();
		txtMarker.setBounds(67, 91, 164, 21);
		contentPane.add(txtMarker);
		txtMarker.setColumns(10);
		
		txtID = new JTextField();
		txtID.setEnabled(false);
		txtID.setBounds(66, 7, 77, 21);
		contentPane.add(txtID);
		txtID.setColumns(10);
		
		
		JButton btnUpdate = new JButton("\u4FEE\u6539");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//有效性判断
				//条码
				String strID= txtID.getText().trim();
				shop.setShopID(strID);
				//名称
				String strName= txtName.getText().trim();
				if(!(strName.length()>0)){
					JOptionPane.showMessageDialog(ShopUpdate.this, "名称不能为空!", "提示信息", JOptionPane.ERROR_MESSAGE);;
					return;
				}
				shop.setShopName(strName);
				//进价
				String strCstPrc=null;
				strCstPrc= txtCstPrc.getText().trim();
				if(strCstPrc.length()>0){
					shop.setCostPrice(Double.parseDouble(strCstPrc));
				}
				else{
					JOptionPane.showMessageDialog(null, "请输入有效的进价!", "提示信息", JOptionPane.ERROR_MESSAGE);;
					return;
				}
				//售价
				String strSllPrc=null;
				strSllPrc = txtSllPrc.getText().trim();
				if(strSllPrc.length()>0){
					shop.setSellPrice(Double.parseDouble(strSllPrc));
				}
				else{
					JOptionPane.showMessageDialog(null, "请输入有效的售价!", "提示信息", JOptionPane.ERROR_MESSAGE);;
					return;
				}
				//库存
				String num= null;
				num= txtStorage.getText().trim();
				if(num.length()>0){
					shop.setNum(Integer.parseInt(num));
				}
				else{
					JOptionPane.showMessageDialog(null, "请输入有效的库存!", "提示信息", JOptionPane.ERROR_MESSAGE);;
					return;
				}
				//分类
				int category=cboCategory.getSelectedIndex();
				shop.setCategory(category);
				//厂家
				String strMarker= null;
				strMarker= txtMarker.getText().trim();
				if(strMarker.length()>0){
					shop.setMarker(strMarker);
				}
				else{
					JOptionPane.showMessageDialog(null, "请输入厂家!", "提示信息", JOptionPane.ERROR_MESSAGE);;
					return;
				}
				if(shpDao.updateShp(shop)>0){
					JOptionPane.showMessageDialog(null, "修改成功!", "提示信息", JOptionPane.INFORMATION_MESSAGE);;
				}
				fillTable();
			}
		});
		btnUpdate.setBounds(266, 89, 93, 23);
		contentPane.add(btnUpdate);
		
		JButton btnDel = new JButton("\u5220\u9664");
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String strID = txtID.getText().trim();
				int i=JOptionPane.showConfirmDialog(ShopUpdate.this, "要删除该数据吗？", "提示", JOptionPane.YES_NO_OPTION);
				if(i==JOptionPane.YES_OPTION){
					if(shpDao.delShop(strID)>0){
						JOptionPane.showMessageDialog(ShopUpdate.this, "删除成功!", "提示", JOptionPane.INFORMATION_MESSAGE);
						fillTable();
						txtClear();
					}
				}
			}
		});
		btnDel.setBounds(369, 89, 93, 23);
		contentPane.add(btnDel);
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//选取商品数据，把数据填充到维护区域
				int curRow =table.getSelectedRow();
				//条码
				txtID.setText(String.valueOf(table.getValueAt(curRow, 0)));
				//名称
				txtName.setText(String.valueOf(table.getValueAt(curRow, 1)));
				//进价
				txtCstPrc.setText(String.valueOf(table.getValueAt(curRow, 2)));
				//售价
				txtSllPrc.setText(String.valueOf(table.getValueAt(curRow, 3)));
				//库存
				txtStorage.setText(String.valueOf(table.getValueAt(curRow, 4)));
				//分类
				cboCategory.setSelectedIndex(Integer.parseInt(String.valueOf(table.getValueAt(curRow, 7))));
				//厂家
				txtMarker.setText(String.valueOf(table.getValueAt(curRow, 6)));
			}
		});
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(11, 125, 485, 182);
		contentPane.add(scrollPane);
		
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"\u6761\u7801", "\u540D\u79F0", "\u8FDB\u4EF7", "\u552E\u4EF7", "\u5E93\u5B58\u91CF", "\u5206\u7C7B", "\u5382\u5BB6", "type", "\u8D27\u67B6\u53F7"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(7).setPreferredWidth(0);
		table.getColumnModel().getColumn(7).setMinWidth(0);
		table.getColumnModel().getColumn(7).setMaxWidth(0);
		table.setBounds(23, 150, 391, 170);
		fillTable();
	}
	public void fillTable(){
		DefaultTableModel dtm= (DefaultTableModel)table.getModel();
		dtm.setRowCount(0);
		//取商品数据
		Vector<Vector<Object>> vV=shpDao.getShpLst();
		//shopID,name,转换后的类别,厂商 getString
		//进价,售价 getDouble
		//库存 ,类别 getInt
		//遍历vV
		for (int i = 0; i < vV.size(); i++) {
			//添加
			dtm.addRow(vV.get(i));
		}
	}
	public void txtClear(){
		txtID.setText("");
		txtCstPrc.setText("");
		txtMarker.setText("");
		txtName.setText("");
		txtSllPrc.setText("");
		txtStorage.setText("");
		cboCategory.setSelectedIndex(0);
		
	}
	public static void main(String[] args) {
		new ShopUpdate().setVisible(true);
	}
}
