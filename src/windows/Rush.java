package windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import common.LoginInfo;
import common.MyDefaultTableModel;
import dao.DayMoneyDao;
import dao.EmpDao;
import dao.ShopDao;
import entity.Shop;
import javax.swing.JTextField;

public class Rush extends JFrame {

	private JPanel categories;
	private JTable table;
	private EmpDao empDao= new EmpDao();
	private JLabel lblSum;
	private JLabel lblInfo;
	//该收银员收的钱
	private double curRushMoney;
	private JButton btnAdd;
	private JButton btnAccount;
	private JButton btnCancel;
	private JButton btnExit;
	private JButton btnDel;
	private JButton btnPlus;
	private JButton btnReduce;
	private JButton btnUpdateNum;
	private ShopDao shpDao= new ShopDao();
	private DayMoneyDao dmDao=new DayMoneyDao();
	private static Rush r= new Rush();
	private JLabel categorycontent;
	//创建
	DecimalFormat df= new DecimalFormat("#.00");
	private JTextField txt;
	private JLabel label_1;
	/**
	 * Create the frame.
	 */
	public Rush() {

		setTitle("\u6536\u94F6\u754C\u9762");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 654, 373);
		categories = new JPanel();
		categories.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(categories);
		categories.setLayout(null);
		
		btnAdd = new JButton("\u6DFB\u52A0\u65B0\u5546\u54C1[F1]");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//String ->int//可转可不转
				//输入条码
				String shpID= JOptionPane.showInputDialog(Rush.this, "请输入商品的条码");
		        //判断
				if(shpDao.ifShpIDExists(shpID)){
					//是否已经被添加，否则不用长出来一行
					 //true 添加过 false 未添加
					if(!ifExistsGridShpID(shpID)){
						gridAddRows(shpID);
					}
					else{
						JOptionPane.showMessageDialog(Rush.this, "已添加过此商品","提示信息",JOptionPane.ERROR_MESSAGE);
					}
				}
				else{
					JOptionPane.showMessageDialog(Rush.this, "没有该商品","提示信息",JOptionPane.ERROR_MESSAGE);
				}
			}
			
		});
		btnAdd.setFocusable(false);
		
		btnAdd.setIcon(new ImageIcon(Rush.class.getResource("/images/add.png")));
		btnAdd.setBounds(20, 6, 151, 30);
		categories.add(btnAdd);
		
		btnAccount = new JButton("\u672C\u5355\u7ED3\u8D26[F2]");
		btnAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			       //1.这笔业务的金额
				double curMoney =getCurMoney();
					//2.获取当前数据库日期
					String dbDate=dmDao.getDBCurDay();
					//3.看当前日期是否存在
					//3.1 如果没有的话，就Insert
					//3.2 如果有的话，就update
					
					if(dmDao.ifExistsDBDate(dbDate)){
						dmDao.updateCurDayMoney(dbDate,curMoney);
					}else
						dmDao.insertCurDayMoney(dbDate,curMoney);
					//4.减少库存
					storageReduce();
					//5.打印"金额总计"
					lblSum.setText(String.valueOf(curMoney));
					//6.获取当前已收金额
					curRushMoney+=curMoney;
					//7.打印时间戳
					//7.1打印时间
					Date d=new Date();
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E");
					
					String cha=dmDao.getcharacter();
					lblInfo.setText(cha+":"+sdf.format(d)+"已输金额"+df.format(curRushMoney));
					//7.3打印已收金额
					
					String category=dmDao.getT();
					txt.setText("");
					txt.setText(category);
					
			}
		});
		btnAccount.setFocusable(false);
		btnAccount.setIcon(new ImageIcon(Rush.class.getResource("/images/money.png")));
		btnAccount.setBounds(171, 6, 151, 30);
		categories.add(btnAccount);
		
		btnCancel = new JButton("\u590D\u4F4D[F3]");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel dtm=(DefaultTableModel)table.getModel();
				//弹出一个对话框
				int rtn=JOptionPane.showConfirmDialog(Rush.this, "确定复位吗","提示信息",JOptionPane.YES_NO_OPTION);
	         if(rtn==JOptionPane.YES_NO_OPTION){
	         dtm.setRowCount(0);
	       //清空table的行
	         }
			}
		});
		btnCancel.setFocusable(false);
		btnCancel.setIcon(new ImageIcon(Rush.class.getResource("/images/cancel.png")));
		btnCancel.setBounds(334, 6, 151, 30);
		categories.add(btnCancel);
		
		btnExit = new JButton("\u9000\u51FA\u7CFB\u7EDF[F4]");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			 empDao.setLoginOff(LoginInfo.empID);
				//todo 在线->离线
				System.exit(0);
			}
		});
		btnExit.setFocusable(false);
		btnExit.setIcon(new ImageIcon(Rush.class.getResource("/images/exit.png")));
		btnExit.setBounds(492, 6, 151, 30);
		categories.add(btnExit);
		
		btnDel = new JButton("\u5220\u9664\u5546\u54C1[F5]");
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//获取选中的行号
				int curRow=table.getSelectedRow();
			//判断
			if(curRow==-1){
				JOptionPane.showMessageDialog(Rush.this, "请选中要购买的商品","提示信息",JOptionPane.ERROR_MESSAGE);
			return;
			  }
			//获取本行的数量
			DefaultTableModel dtm=(DefaultTableModel) table.getModel();
            dtm.removeRow(curRow);
			}
		});
		btnDel.setFocusable(false);
		btnDel.setIcon(new ImageIcon(Rush.class.getResource("/images/data1.png")));
		btnDel.setBounds(502, 43, 135, 40);
		categories.add(btnDel);
		
		btnPlus = new JButton("\u6570\u91CF\u52A0\u4E00[F6]");
		btnPlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	//		//获取选中的行号
				int curRow=table.getSelectedRow();
			//判断
			if(curRow==-1){
				JOptionPane.showMessageDialog(Rush.this, "请选中要购买的商品","提示信息",JOptionPane.ERROR_MESSAGE);
			return;
			  }
			//获取本行的数量
			DefaultTableModel dtm=(DefaultTableModel) table.getModel();
			int buyNum=Integer.parseInt(String.valueOf(dtm.getValueAt(curRow, 3)));
			//获取库存数量
			int storage=Integer.parseInt(String.valueOf(dtm.getValueAt(curRow, 5)));
			if(buyNum+1>storage){
				//弹出对话框
				JOptionPane.showMessageDialog(Rush.this, "购买数量>库存","提示信息",JOptionPane.ERROR_MESSAGE);
				return;
			}
			double price=Double.parseDouble(String.valueOf(dtm.getValueAt(curRow, 2)) );
			//设置价格
			//设置购买数量
			dtm.setValueAt(buyNum+1, curRow, 3);
			dtm.setValueAt(df.format(price*(buyNum+1)), curRow, 4);
			
			
			}
		});
		btnPlus.setFocusable(false);
		btnPlus.setIcon(new ImageIcon(Rush.class.getResource("/images/chart1.png")));
		btnPlus.setBounds(502, 103, 135, 40);
		categories.add(btnPlus);
		
		btnReduce = new JButton("\u6570\u91CF\u51CF\u4E00[F7]");
		btnReduce.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//获取选中的行号
				int curRow=table.getSelectedRow();
			//判断
			if(curRow==-1){
				JOptionPane.showMessageDialog(Rush.this, "请选中要购买的商品","提示信息",JOptionPane.ERROR_MESSAGE);
			return;
			  }
			//获取本行的数量
			DefaultTableModel dtm=(DefaultTableModel) table.getModel();
			int buyNum=Integer.parseInt(String.valueOf(dtm.getValueAt(curRow, 3)));
			if(buyNum-1<=0){
				//弹出对话框
				JOptionPane.showMessageDialog(Rush.this, "购买数量必须为正","提示信息",JOptionPane.ERROR_MESSAGE);

			}
			double price=Double.parseDouble(String.valueOf(dtm.getValueAt(curRow, 2)) );
			//设置价格
			//设置购买数量
			dtm.setValueAt(buyNum-1, curRow, 3);
			dtm.setValueAt(df.format(price*(buyNum-1)), curRow, 4);
			}
			
		});
		btnReduce.setFocusable(false);
		btnReduce.setIcon(new ImageIcon(Rush.class.getResource("/images/chart2.png")));
		btnReduce.setBounds(502, 163, 135, 40);
		categories.add(btnReduce);
		
		btnUpdateNum = new JButton("\u4FEE\u6539\u6570\u91CF[F8]");
		btnUpdateNum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//获取行号
				int curRow=table.getSelectedRow();
				if(curRow==-1){
					JOptionPane.showMessageDialog(Rush.this, "请选中要购买的商品","提示信息",JOptionPane.ERROR_MESSAGE);
				return;
				}
				else{
					//购买数量
					int num=Integer.parseInt(JOptionPane.showInputDialog(Rush.this, "请输入要购买的数量"));
			         //库存
			         DefaultTableModel dtm= (DefaultTableModel) table.getModel();
				    int storage=Integer.parseInt(String.valueOf(dtm.getValueAt(curRow, 5)));
			        //购买数量>库存
				if(num>storage){
					JOptionPane.showMessageDialog(Rush.this, "购买数量>库存","提示信息",JOptionPane.ERROR_MESSAGE);
				       return;
				}	
				//设置购买数量
				dtm.setValueAt(num, curRow, 3);
				//单价
				double price=Double.parseDouble(String.valueOf(dtm.getValueAt(curRow, 2)) );
				//设置价格
				dtm.setValueAt(df.format(price*num), curRow, 4);
				
				}
			}
		});
		btnUpdateNum.setFocusable(false);
		btnUpdateNum.setIcon(new ImageIcon(Rush.class.getResource("/images/chart3.png")));
		btnUpdateNum.setBounds(502, 229, 135, 40);
		categories.add(btnUpdateNum);
		
		JLabel lblNewLabel = new JLabel("\u91D1\u989D\u603B\u8BA1\uFF1A");
		lblNewLabel.setBounds(134, 279, 79, 15);
		categories.add(lblNewLabel);
		
		lblSum = new JLabel("0");
		lblSum.setBounds(223, 279, 54, 15);
		categories.add(lblSum);
		
		lblInfo = new JLabel("\u65F6\u95F4 \u64CD\u4F5C\u5458 ");
		lblInfo.setBounds(20, 314, 445, 15);
		categories.add(lblInfo);
		
		table = new JTable();
		table.setFocusable(false);
		table.setModel(new MyDefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"\u6761\u7801", "\u540D\u79F0", "\u5355\u4EF7", "\u6570\u91CF", "\u91D1\u989D", "num"
				}
			) {
				boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, true
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
		table.getColumnModel().getColumn(5).setPreferredWidth(0);
		table.getColumnModel().getColumn(5).setMinWidth(0);
		table.getColumnModel().getColumn(5).setMaxWidth(0);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 43, 475, 226);
		categories.add(scrollPane);
		
		scrollPane.setViewportView(table);
		
		txt = new JTextField();
		txt.setBounds(562, 276, 66, 21);
		categories.add(txt);
		txt.setColumns(10);
		
		label_1 = new JLabel("\u7EDF\u8BA1\u73B0\u6709\u5546\u54C1\u79CD\u7C7B");
		label_1.setBounds(445, 279, 54, 15);
		categories.add(label_1);
	}
   private void storageReduce() {
		DefaultTableModel dtm=(DefaultTableModel) table.getModel();
		int rowCount=dtm.getRowCount();
		for(int i=0;i<rowCount;i++){
			String shopID=String.valueOf(dtm.getValueAt(i,0));
			int buyNum=Integer.parseInt(String.valueOf(dtm.getValueAt(i, 3)));
	shpDao.StorageReduceByShpID(shopID,buyNum);
		}
	}
	private double getCurMoney() {
		double rtn=0;
		DefaultTableModel dtm=(DefaultTableModel) table.getModel();
		int rowCount=dtm.getRowCount();
		for(int i=0;i<rowCount;i++){
			rtn+=Double.parseDouble(String.valueOf(dtm.getValueAt(i, 4)));
		}
		return rtn;
	}
	protected boolean ifExistsGridShpID(String shpID) {
		boolean rtn=false;
		//用DefaultTableModel使用vector操作单元格
		//实现了TableModel 接口                                                         //object类要转型
		DefaultTableModel dtm=(DefaultTableModel) table.getModel();
		int count =dtm.getRowCount();
		for (int i = 0; i < count; i++) {
			//本行条码，表格单元的第i行，第0列
			String curShpID=String.valueOf(dtm.getValueAt(i, 0));
			
		}
		return rtn;
	}
	protected void gridAddRows(String shpID) {
            //返回商品shp实体信息
		Shop shp=shpDao.getShpNmPrc(shpID);
		//返回提供此 JTable 所显示数据的 TableModel。
		DefaultTableModel dtm=(DefaultTableModel) table.getModel();
		Vector<Object> v=new Vector<Object>();
		//ID条码
		v.add(shpID);
		//名称
		v.add(shp.getShopName());
		//单价
		v.add(shp.getSellPrice());
		//添加数量
		v.add(1);
		//金额
		v.add(shp.getSellPrice());
		//库存
		v.add(shp.getNum());
		//添加一行
		dtm.addRow(v);
		
	}

public static void main(String[] args) {
	new Rush().setVisible(true);
}
}

