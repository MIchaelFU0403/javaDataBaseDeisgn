package windows;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import common.LoginInfo;
import dao.DayMoneyDao;
import dao.EmpDao;

public class Main extends JFrame {

	private JPanel contentPane;
	private EmpDao empDAO=new EmpDao();
	private DayMoneyDao dmDao= new DayMoneyDao();
	private JLabel lblRushNum;
	private JLabel lblDayMoney;
	private JLabel lblSrvrStrt;

	/**
	 * Create the frame.
	 */
	public Main() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				//判断是否按下"f2"
				if(e.getKeyCode()==KeyEvent.VK_F2){
					//打开锁屏画面
					new Lock(Main.this, true).setVisible(true);;
				}
			}
		});
		setTitle("\u540E\u53F0\u7BA1\u7406");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 583, 434);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menuService = new JMenu("\u670D\u52A1");
		menuBar.add(menuService);
		
		JMenuItem itemStart = new JMenuItem("\u542F\u52A8\u670D\u52A1");
		itemStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//启动服务
				empDAO.setServiceOn();
				//设置服务器状态
				lblSrvrStrt.setText("已启动");
			}
		});
		itemStart.setIcon(new ImageIcon(Main.class.getResource("/images/start.png")));
		menuService.add(itemStart);
		
		JMenuItem itemClose = new JMenuItem("\u5173\u95ED\u670D\u52A1");
		itemClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//启动服务
				empDAO.setServiceOff();
				//设置服务器状态
				lblSrvrStrt.setText("未启动");
			}
		});
		itemClose.setIcon(new ImageIcon(Main.class.getResource("/images/stop.png")));
		menuService.add(itemClose);
		
		JMenuItem itemExit = new JMenuItem("\u9000\u51FA\u7CFB\u7EDF");
		itemExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//在线->离线
				//f5:进入被调方法;f6:单步;f7:返回;f8:跑完
				empDAO.setLoginOff(LoginInfo.empID);
				//退出系统
				System.exit(0);
			}
		});
		itemExit.setIcon(new ImageIcon(Main.class.getResource("/images/exit.png")));
		menuService.add(itemExit);
		
		JMenu menuEmp = new JMenu("\u5458\u5DE5\u7BA1\u7406");
		menuBar.add(menuEmp);
		
		JMenuItem itemAdd = new JMenuItem("\u65B0\u589E\u5458\u5DE5");
		itemAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//打开员工插入画面
				new EmpInsert().setVisible(true);
			}
		});
		itemAdd.setIcon(new ImageIcon(Main.class.getResource("/images/add.png")));
		menuEmp.add(itemAdd);
		
		JMenuItem itemUpdate = new JMenuItem("\u5458\u5DE5\u7EF4\u62A4");
		itemUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//打开员工的维护画面
				new EmpUpdate().setVisible(true);
			}
		});
		itemUpdate.setIcon(new ImageIcon(Main.class.getResource("/images/chart3.png")));
		menuEmp.add(itemUpdate);
		
		JMenuItem itemList = new JMenuItem("\u5458\u5DE5\u5217\u8868");
		itemList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//打开员工查询
				new EmpFind().setVisible(true);
			}
		});
		itemList.setIcon(new ImageIcon(Main.class.getResource("/images/employee.png")));
		menuEmp.add(itemList);
		
		JMenu menuShop = new JMenu("\u5546\u54C1\u7BA1\u7406");
		menuBar.add(menuShop);
		
		JMenuItem itemShpAdd = new JMenuItem("\u65B0\u589E\u5546\u54C1");
		itemShpAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//打开商品的插入画面
				new ShopInsert().setVisible(true);
			}
		});
		itemShpAdd.setIcon(new ImageIcon(Main.class.getResource("/images/icon.gif")));
		menuShop.add(itemShpAdd);
		
		JMenuItem itemStock = new JMenuItem("\u7EF4\u62A4\u5E93\u5B58");
		itemStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//打开商品的维护画面
				new ShopUpdate().setVisible(true);
			}
		});
		itemStock.setIcon(new ImageIcon(Main.class.getResource("/images/money.png")));
		menuShop.add(itemStock);
		
		JMenuItem itemShpLst = new JMenuItem("\u5546\u54C1\u5217\u8868");
		itemShpLst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//打开商品的查询
				new ShopFind().setVisible(true);
			}
		});
		itemShpLst.setIcon(new ImageIcon(Main.class.getResource("/images/goods.png")));
		menuShop.add(itemShpLst);
		
		JMenu menuHelp = new JMenu("\u5E2E\u52A9");
		menuBar.add(menuHelp);
		
		JMenuItem itemChgPWD = new JMenuItem("\u4FEE\u6539\u5BC6\u7801");
		itemChgPWD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//打开修改密码画面
				new PWDUpdate().setVisible(true);
			}
		});
		itemChgPWD.setIcon(new ImageIcon(Main.class.getResource("/images/password.png")));
		menuHelp.add(itemChgPWD);
		
		JMenuItem itemSoftInfo = new JMenuItem("\u8F6F\u4EF6\u4FE1\u606F");
		itemSoftInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Help().setVisible(true);
			}
		});
		itemSoftInfo.setIcon(new ImageIcon(Main.class.getResource("/images/data2.png")));
		menuHelp.add(itemSoftInfo);
		
		JMenuItem itemShelf = new JMenuItem("\u8D27\u67B6\u7BA1\u7406");
		itemShelf.setIcon(new ImageIcon(Main.class.getResource("/images/data3.png")));
		menuHelp.add(itemShelf);
		
		itemShelf.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				new shelfInsert().setVisible(true);
			}
			
		});
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblDayMoney = new JLabel("\u00A50\u5143");
		lblDayMoney.setForeground(Color.GREEN);
		lblDayMoney.setBounds(425, 134, 80, 15);
		contentPane.add(lblDayMoney);
		//实时更新当天的营业额
		new Thread(){
			public void run(){
				while(true){
					//设置当前的营业额
					lblDayMoney.setText(dmDao.getCurDayMoney(dmDao.getDBCurDay()));
					//休眠
					try {
						sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
		lblRushNum = new JLabel("0\u4EBA");
		lblRushNum.setForeground(Color.GREEN);
		lblRushNum.setBounds(425, 98, 67, 15);
		contentPane.add(lblRushNum);
		//创建一个线程,实时跟新在线收银员人数
		new Thread(){
			@Override
			public void run() {
				while(true){
					//设置在线的收银员人数
					lblRushNum.setText(String.valueOf(empDAO.getOnLineRushNum())); 
					//休眠
					try {
						sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
		
		lblSrvrStrt = new JLabel("\u672A\u542F\u52A8");
		lblSrvrStrt.setForeground(Color.GREEN);
		lblSrvrStrt.setBounds(425, 59, 67, 15);
		contentPane.add(lblSrvrStrt);
		
		JLabel lblNewLabel_3 = new JLabel("\u5F53\u5929\u8425\u4E1A\u989D\uFF1A");
		lblNewLabel_3.setIcon(new ImageIcon(Main.class.getResource("/images/money.png")));
		lblNewLabel_3.setForeground(Color.YELLOW);
		lblNewLabel_3.setBounds(312, 130, 103, 22);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_2 = new JLabel("\u6536\u94F6\u5458\u4EBA\u6570\uFF1A");
		lblNewLabel_2.setIcon(new ImageIcon(Main.class.getResource("/images/employee.png")));
		lblNewLabel_2.setForeground(Color.YELLOW);
		lblNewLabel_2.setBounds(312, 91, 103, 29);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1 = new JLabel("\u670D\u52A1\u5668\u72B6\u6001\uFF1A");
		lblNewLabel_1.setIcon(new ImageIcon(Main.class.getResource("/images/server_state.png")));
		lblNewLabel_1.setForeground(Color.YELLOW);
		lblNewLabel_1.setBounds(312, 52, 103, 29);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Main.class.getResource("/images/masterup.jpg")));
		lblNewLabel.setBounds(-418, 0, 1001, 517);
		contentPane.add(lblNewLabel);
		
	}
	public static void main(String[] args) {
		new Main().setVisible(true);
	}
}
