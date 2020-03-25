package windows;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import common.LoginInfo;

import dao.EmpDao;
import java.awt.Toolkit;

public class Login extends JFrame {

	private JPanel contentPane;
	//编号
	private JTextField txtID;
	//密码
	private JPasswordField txtPWD;
	//收银员
	private JRadioButton rdoRush;
	//管理者
	private JRadioButton rdoManager;
	
	private EmpDao empDao= new EmpDao();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/images/login_client_icon.png")));
		setTitle("\u7528\u6237\u767B\u5F55        \u5236\u4F5C\u4EBA\uFF1A\u4ED8\u60A6\u6615    \u7248\u672C\uFF1A1.0");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 229);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/images/login_client_icon.png")));
		lblNewLabel.setBounds(20, 24, 135, 149);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u7F16\u53F7\uFF1A");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(199, 24, 54, 15);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u5BC6\u7801\uFF1A");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(199, 67, 54, 15);
		contentPane.add(lblNewLabel_2);
		
		txtID = new JTextField();
		txtID.setBounds(260, 21, 117, 30);
		contentPane.add(txtID);
		txtID.setColumns(10);
		
		txtPWD = new JPasswordField();
		txtPWD.setBounds(260, 60, 117, 30);
		contentPane.add(txtPWD);
		
		rdoRush = new JRadioButton("\u6536\u94F6\u5458");
		rdoRush.setSelected(true);
		rdoRush.setBounds(194, 102, 77, 23);
		contentPane.add(rdoRush);
		
		rdoManager = new JRadioButton("\u7BA1\u7406\u5458");
		rdoManager.setBounds(290, 102, 108, 23);
		contentPane.add(rdoManager);
		ButtonGroup bgPower = new ButtonGroup();
		bgPower.add(rdoRush);
		bgPower.add(rdoManager);
		
		JButton btnLogin = new JButton("\u767B\u5F55");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//获取编号
				String empID=txtID.getText().trim();
				//获取密码
				String empPWD=new String(txtPWD.getPassword());
				//判断
				if (rdoManager.isSelected()) {
					//对管理员验证 true:存在;false:不存在
					if (empDao.ifExistsManager(empID,empPWD)) {
						//打开main画面
						new Main().setVisible(true);
						dispose();
					} else {
						//弹出对话框
						JOptionPane.showMessageDialog(Login.this, "账号或者密码有误", "登录失败", JOptionPane.ERROR_MESSAGE);
						return;
					}
				} else if(rdoRush.isSelected()) {
					//收银员登录
					if (empDao.ifExistsRush(empID,empPWD)) {
						//打开收银画面
						new Rush().setVisible(true);
						dispose();
					} else {
						JOptionPane.showMessageDialog(Login.this, "账号或者密码有误/初次登录，未开通服务", "登录", JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				//保存当前用户信息
				LoginInfo.empID=empID;
				LoginInfo.empPWD=empPWD;
				//离线->在线
				empDao.setLoginOn(empID);
			}
		});
		btnLogin.setBounds(165, 150, 93, 23);
		contentPane.add(btnLogin);
		
		JButton btnExit = new JButton("\u9000\u51FA");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//退出系统
				System.exit(0);
			}
		});
		btnExit.setBounds(292, 150, 93, 23);
		contentPane.add(btnExit);
	}
}
