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
	//������Ա�յ�Ǯ
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
	//����
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
			//String ->int//��ת�ɲ�ת
				//��������
				String shpID= JOptionPane.showInputDialog(Rush.this, "��������Ʒ������");
		        //�ж�
				if(shpDao.ifShpIDExists(shpID)){
					//�Ƿ��Ѿ�����ӣ������ó�����һ��
					 //true ��ӹ� false δ���
					if(!ifExistsGridShpID(shpID)){
						gridAddRows(shpID);
					}
					else{
						JOptionPane.showMessageDialog(Rush.this, "����ӹ�����Ʒ","��ʾ��Ϣ",JOptionPane.ERROR_MESSAGE);
					}
				}
				else{
					JOptionPane.showMessageDialog(Rush.this, "û�и���Ʒ","��ʾ��Ϣ",JOptionPane.ERROR_MESSAGE);
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

			       //1.���ҵ��Ľ��
				double curMoney =getCurMoney();
					//2.��ȡ��ǰ���ݿ�����
					String dbDate=dmDao.getDBCurDay();
					//3.����ǰ�����Ƿ����
					//3.1 ���û�еĻ�����Insert
					//3.2 ����еĻ�����update
					
					if(dmDao.ifExistsDBDate(dbDate)){
						dmDao.updateCurDayMoney(dbDate,curMoney);
					}else
						dmDao.insertCurDayMoney(dbDate,curMoney);
					//4.���ٿ��
					storageReduce();
					//5.��ӡ"����ܼ�"
					lblSum.setText(String.valueOf(curMoney));
					//6.��ȡ��ǰ���ս��
					curRushMoney+=curMoney;
					//7.��ӡʱ���
					//7.1��ӡʱ��
					Date d=new Date();
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E");
					
					String cha=dmDao.getcharacter();
					lblInfo.setText(cha+":"+sdf.format(d)+"������"+df.format(curRushMoney));
					//7.3��ӡ���ս��
					
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
				//����һ���Ի���
				int rtn=JOptionPane.showConfirmDialog(Rush.this, "ȷ����λ��","��ʾ��Ϣ",JOptionPane.YES_NO_OPTION);
	         if(rtn==JOptionPane.YES_NO_OPTION){
	         dtm.setRowCount(0);
	       //���table����
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
				//todo ����->����
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
				//��ȡѡ�е��к�
				int curRow=table.getSelectedRow();
			//�ж�
			if(curRow==-1){
				JOptionPane.showMessageDialog(Rush.this, "��ѡ��Ҫ�������Ʒ","��ʾ��Ϣ",JOptionPane.ERROR_MESSAGE);
			return;
			  }
			//��ȡ���е�����
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
	//		//��ȡѡ�е��к�
				int curRow=table.getSelectedRow();
			//�ж�
			if(curRow==-1){
				JOptionPane.showMessageDialog(Rush.this, "��ѡ��Ҫ�������Ʒ","��ʾ��Ϣ",JOptionPane.ERROR_MESSAGE);
			return;
			  }
			//��ȡ���е�����
			DefaultTableModel dtm=(DefaultTableModel) table.getModel();
			int buyNum=Integer.parseInt(String.valueOf(dtm.getValueAt(curRow, 3)));
			//��ȡ�������
			int storage=Integer.parseInt(String.valueOf(dtm.getValueAt(curRow, 5)));
			if(buyNum+1>storage){
				//�����Ի���
				JOptionPane.showMessageDialog(Rush.this, "��������>���","��ʾ��Ϣ",JOptionPane.ERROR_MESSAGE);
				return;
			}
			double price=Double.parseDouble(String.valueOf(dtm.getValueAt(curRow, 2)) );
			//���ü۸�
			//���ù�������
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
			//��ȡѡ�е��к�
				int curRow=table.getSelectedRow();
			//�ж�
			if(curRow==-1){
				JOptionPane.showMessageDialog(Rush.this, "��ѡ��Ҫ�������Ʒ","��ʾ��Ϣ",JOptionPane.ERROR_MESSAGE);
			return;
			  }
			//��ȡ���е�����
			DefaultTableModel dtm=(DefaultTableModel) table.getModel();
			int buyNum=Integer.parseInt(String.valueOf(dtm.getValueAt(curRow, 3)));
			if(buyNum-1<=0){
				//�����Ի���
				JOptionPane.showMessageDialog(Rush.this, "������������Ϊ��","��ʾ��Ϣ",JOptionPane.ERROR_MESSAGE);

			}
			double price=Double.parseDouble(String.valueOf(dtm.getValueAt(curRow, 2)) );
			//���ü۸�
			//���ù�������
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
				//��ȡ�к�
				int curRow=table.getSelectedRow();
				if(curRow==-1){
					JOptionPane.showMessageDialog(Rush.this, "��ѡ��Ҫ�������Ʒ","��ʾ��Ϣ",JOptionPane.ERROR_MESSAGE);
				return;
				}
				else{
					//��������
					int num=Integer.parseInt(JOptionPane.showInputDialog(Rush.this, "������Ҫ���������"));
			         //���
			         DefaultTableModel dtm= (DefaultTableModel) table.getModel();
				    int storage=Integer.parseInt(String.valueOf(dtm.getValueAt(curRow, 5)));
			        //��������>���
				if(num>storage){
					JOptionPane.showMessageDialog(Rush.this, "��������>���","��ʾ��Ϣ",JOptionPane.ERROR_MESSAGE);
				       return;
				}	
				//���ù�������
				dtm.setValueAt(num, curRow, 3);
				//����
				double price=Double.parseDouble(String.valueOf(dtm.getValueAt(curRow, 2)) );
				//���ü۸�
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
		//��DefaultTableModelʹ��vector������Ԫ��
		//ʵ����TableModel �ӿ�                                                         //object��Ҫת��
		DefaultTableModel dtm=(DefaultTableModel) table.getModel();
		int count =dtm.getRowCount();
		for (int i = 0; i < count; i++) {
			//�������룬���Ԫ�ĵ�i�У���0��
			String curShpID=String.valueOf(dtm.getValueAt(i, 0));
			
		}
		return rtn;
	}
	protected void gridAddRows(String shpID) {
            //������Ʒshpʵ����Ϣ
		Shop shp=shpDao.getShpNmPrc(shpID);
		//�����ṩ�� JTable ����ʾ���ݵ� TableModel��
		DefaultTableModel dtm=(DefaultTableModel) table.getModel();
		Vector<Object> v=new Vector<Object>();
		//ID����
		v.add(shpID);
		//����
		v.add(shp.getShopName());
		//����
		v.add(shp.getSellPrice());
		//�������
		v.add(1);
		//���
		v.add(shp.getSellPrice());
		//���
		v.add(shp.getNum());
		//���һ��
		dtm.addRow(v);
		
	}

public static void main(String[] args) {
	new Rush().setVisible(true);
}
}

