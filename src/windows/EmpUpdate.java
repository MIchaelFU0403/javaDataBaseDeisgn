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
	//����"empDao"
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
				//����һ���Ի���
				int rtn=JOptionPane.showConfirmDialog(EmpUpdate.this, "��ȷ��Ҫ����������?", "��ʾ��Ϣ", JOptionPane.YES_NO_OPTION);
				if(rtn==JOptionPane.YES_OPTION){
					//��ȡ���
					String empID=txtID.getText();
					//��������
					boolean result= empDao.empPWDReset(empID);
					//�ж�
					if (result) {
						JOptionPane.showMessageDialog(EmpUpdate.this, "�������óɹ�!", "��ʾ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		btnPWDReset.setBounds(10, 123, 99, 23);
		contentPane.add(btnPWDReset);
		
		JButton btnUpdate = new JButton("\u4FEE\u6539");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//����Emp����
				Emp emp= new Emp();
				//��Ҫ����check����:
				//��ȡ���
				String empID=txtID.getText().trim();
				emp.setEmpID(empID);
				//��ȡ����
				String empName= txtName.getText().trim();
				//��������Ϊ�գ�
				if (empName!=null&&empName.length()>0) {
					//��������
					emp.setEmpName(empName);
				} else {
					//����һ������Ի���
					JOptionPane.showMessageDialog(EmpUpdate.this, "��������¼��!", "��ʾ��Ϣ", JOptionPane.ERROR_MESSAGE);
					return;
				}
				//��ȡ�Ա�
				//�ж�
				if (rdoMale.isSelected()) {
					//�����Ա�
					emp.setEmpSex(1);
				} else if(rdoFemale.isSelected()){
					//�����Ա�
					emp.setEmpSex(0);
					
				}
				//��ȡ����,���洢����
				String empAge=txtAge.getText();
				if (empAge.length()>0) {
					//��������
					emp.setEmpAge(Integer.parseInt(empAge));
				}
				//��ȡ�ֻ���
				String empPhone= txtPhone.getText().trim();
				//�ֻ��Ų���Ϊ��
				if (empPhone!=null&&empPhone.length()>0) {
					//�ֻ��ű���Ϊ11λ
					if (empPhone.length()==11) {
						//�����ֻ���
						emp.setEmpPhone(empPhone);
					} else {
						//����һ������Ի���
						JOptionPane.showMessageDialog(EmpUpdate.this, "�ֻ��ű���Ϊ11λ!", "��ʾ��Ϣ", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
				} else {
					//����һ������Ի���
					JOptionPane.showMessageDialog(EmpUpdate.this, "�ֻ��ű�������!", "��ʾ��Ϣ", JOptionPane.ERROR_MESSAGE);
					return;
				}
				//��ȡ��ַ,������
				String empAddress=txtAddresss.getText().trim();
				//���õ�ַ
				emp.setEmpAddress(empAddress);
				//�ж��Ƿ�Ϊ����Ա
				if (rdoManager.isSelected()) {
					//����Ȩ��
					emp.setEmpPower(1);
				} else if(rdoRush.isSelected()){
					//����Ȩ��
					emp.setEmpPower(2);
				}
				//Ա���޸�;true:�ɹ�;false:ʧ��
				if (empDao.empUpdate(emp)) {
					//����һ������Ի���
					JOptionPane.showMessageDialog(EmpUpdate.this, "Ա���޸ĳɹ�!", "��ʾ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
				}
				//ˢ��table
				fillTbl();
			}
		});
		btnUpdate.setBounds(129, 123, 85, 23);
		contentPane.add(btnUpdate);
		
		JButton btnDel = new JButton("\u5220\u9664");
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//��ȡѡ�е��к�
				int curRow=table.getSelectedRow();
				//���û��ѡ���еĻ�
				if (curRow==-1) {
					JOptionPane.showMessageDialog(EmpUpdate.this, "û��ѡ��ɾ����Ա��", "��ʾ", JOptionPane.ERROR_MESSAGE);
					return;
				}
				//��ȡҪɾ�����
				String empID=txtID.getText();
				//��ǰ�û�����ɾ��
				if (empID.equals(LoginInfo.empID)) {
					JOptionPane.showMessageDialog(EmpUpdate.this, "��ǰԱ������ɾ��!", "��ʾ", JOptionPane.ERROR_MESSAGE);
					return;
				}
				//�����Ի���
				int rtn=JOptionPane.showConfirmDialog(EmpUpdate.this, "��ȷ��Ҫɾ����Ա����?", "��ʾ��Ϣ", JOptionPane.YES_NO_OPTION);
				//�ж�,�����������"yes"
				if (rtn==JOptionPane.YES_OPTION) {
					//ɾ��
					empDao.empDel(empID);
					//ˢ�±��
					fillTbl();
					//ѡ�е�һ��
					table.setRowSelectionInterval(0, 0);
					//��ѡ���е����ݴ���ά������
					setValue();
				}
			}
		});
		btnDel.setBounds(234, 123, 85, 23);
		contentPane.add(btnDel);
		
		JButton btnCancel = new JButton("\u53D6\u6D88");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//�رյ�ǰ�Ĵ���
				dispose();
			}
		});
		btnCancel.setBounds(338, 123, 85, 23);
		contentPane.add(btnCancel);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//��ѡ���е����ݴ���ά������
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
		//��table��������
		fillTbl();
	}
	//��table�ṩmodel
	private void fillTbl(){
		//����table��model����
		DefaultTableModel dtm=(DefaultTableModel)table.getModel();
		//��յĿհ���
		dtm.setRowCount(0);
		//���ر��ε�Ա���б�
		Vector<Vector<Object>> vV=empDao.getAllEmpInfo();
		//����vV
		for (Vector<Object> v : vV) {
			dtm.addRow(v);
		}
	}
	/**
	 * ��ѡ���е����ݴ���ά������
	 */
	private void setValue(){
		//����table��model����
		DefaultTableModel dtm= (DefaultTableModel)table.getModel();
		//����table��ѡ����к�
		int curRow= table.getSelectedRow();
		//���ñ��
		txtID.setText(String.valueOf(dtm.getValueAt(curRow, 0)));
		//��������
		txtName.setText(String.valueOf(dtm.getValueAt(curRow, 1)));
		//�洢��ǰ���Ա�
		String sex=String.valueOf(dtm.getValueAt(curRow, 7));
		//�ж�
		if (sex.equals("1")) {
			rdoMale.setSelected(true);
		} else if(sex.equals("0")) {
			rdoFemale.setSelected(true);
		}
		//��������
		txtAge.setText(String.valueOf(dtm.getValueAt(curRow, 3)));
		//���õ绰
		txtPhone.setText(String.valueOf(dtm.getValueAt(curRow, 4)));
		//����Ȩ��
		String power= String.valueOf(dtm.getValueAt(curRow, 8));
		if (power.equals("1")) {
			rdoManager.setSelected(true);
		} else if(power.equals("2")){
			rdoRush.setSelected(true);
		}
		//���õ�ַ
		txtAddresss.setText(String.valueOf(dtm.getValueAt(curRow, 5)));
	}
public static void main(String[] args) {
	new EmpUpdate().setVisible(true);
}}
