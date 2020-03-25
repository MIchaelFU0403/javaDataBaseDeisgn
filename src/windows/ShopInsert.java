package windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.ShopDao;
import entity.Shop;

public class ShopInsert extends JFrame {

	private JPanel contentPane;
	private JTextField txtShpID;
	private JTextField txtShpName;
	private JTextField txtCstPrc;
	private JTextField txtSllPrc;
	private JTextField txtNum;
	private JTextField txtMarker;
	private JComboBox cboCategory;
	private Shop shop= new Shop();
	private ShopDao shopDao = new ShopDao();
	private JTextField txtShelf;
/*	private static ShopInsert is= new ShopInsert();
	public static ShopInsert getInstance(){
		return is;
	}*/

	/**
	 * Create the frame.
	 */
	public ShopInsert() {
		setTitle("\u6DFB\u52A0\u65B0\u5546\u54C1");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 397, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u6761\u7801\uFF1A");
		lblNewLabel.setBounds(26, 10, 54, 15);
		contentPane.add(lblNewLabel);
		
		txtShpID = new JTextField();
		txtShpID.setBounds(90, 7, 122, 21);
		contentPane.add(txtShpID);
		txtShpID.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\u540D\u79F0\uFF1A");
		lblNewLabel_1.setBounds(26, 46, 54, 15);
		contentPane.add(lblNewLabel_1);
		
		txtShpName = new JTextField();
		txtShpName.setBounds(90, 43, 150, 21);
		contentPane.add(txtShpName);
		txtShpName.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("\u8FDB\u4EF7\uFF1A");
		lblNewLabel_2.setBounds(26, 81, 54, 15);
		contentPane.add(lblNewLabel_2);
		
		txtCstPrc = new JTextField();
		txtCstPrc.setBounds(90, 74, 66, 21);
		contentPane.add(txtCstPrc);
		txtCstPrc.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("\u552E\u4EF7\uFF1A");
		lblNewLabel_3.setBounds(26, 112, 54, 15);
		contentPane.add(lblNewLabel_3);
		
		txtSllPrc = new JTextField();
		txtSllPrc.setBounds(90, 109, 66, 21);
		contentPane.add(txtSllPrc);
		txtSllPrc.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("\u5E93\u5B58\u91CF\uFF1A");
		lblNewLabel_4.setBounds(26, 143, 54, 15);
		contentPane.add(lblNewLabel_4);
		
		txtNum = new JTextField();
		txtNum.setBounds(90, 140, 66, 21);
		contentPane.add(txtNum);
		txtNum.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("\u5206\u7C7B\uFF1A");
		lblNewLabel_5.setBounds(26, 178, 54, 15);
		contentPane.add(lblNewLabel_5);
		
		cboCategory = new JComboBox();
		cboCategory.setModel(new DefaultComboBoxModel(new String[] {"\u8BF7\u9009\u62E9", "\u5305\u88C5\u98DF\u54C1 ", "\u996E\u6599\u70DF\u9152 ", "\u852C\u83DC ", "\u7CAE\u6CB9 ", "\u8089\u7C7B ", "\u65E5\u5E38\u7528\u54C1 ", "\u529E\u516C\u7528\u54C1 ", "\u6D17\u6DA4\u7528\u54C1 ", "\u6563\u88C5\u52A0\u5DE5"}));
		cboCategory.setBounds(90, 175, 122, 21);
		contentPane.add(cboCategory);
		
		JLabel lblNewLabel_6 = new JLabel("\u5382\u5BB6\uFF1A");
		lblNewLabel_6.setBounds(26, 213, 54, 15);
		contentPane.add(lblNewLabel_6);
		
		txtMarker = new JTextField();
		txtMarker.setBounds(90, 210, 247, 21);
		contentPane.add(txtMarker);
		txtMarker.setColumns(10);
		
		txtShelf = new JTextField();
		txtShelf.setColumns(10);
		txtShelf.setBounds(240, 78, 66, 21);
		contentPane.add(txtShelf);
		JButton btnInsert = new JButton("\u6DFB\u52A0");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//��Ч���ж�
				//����
				String strID= txtShpID.getText().trim();
				
				if(strID.length()!=6){
					JOptionPane.showMessageDialog(ShopInsert.this, "��������!", "��ʾ��Ϣ", JOptionPane.ERROR_MESSAGE);;
					return;
				}
				if(shopDao.getIDDublicate(strID)>0){
					JOptionPane.showMessageDialog(ShopInsert.this, "��Ʒ�Ѵ���!", "��ʾ��Ϣ", JOptionPane.ERROR_MESSAGE);;
					return;
				}
				shop.setShopID(strID);
				//����
				String strName= txtShpName.getText().trim();
				if(!(strName.length()>0)){
					JOptionPane.showMessageDialog(null, "���Ʋ���Ϊ��!", "��ʾ��Ϣ", JOptionPane.ERROR_MESSAGE);;
					return;
				}
				shop.setShopName(strName);
				//����
				String strCstPrc=null;
				strCstPrc= txtCstPrc.getText().trim();
				if(strCstPrc.length()>0){
					shop.setCostPrice(Double.parseDouble(strCstPrc));
				}
				else{
					JOptionPane.showMessageDialog(null, "��������Ч�Ľ���!", "��ʾ��Ϣ", JOptionPane.ERROR_MESSAGE);;
					return;
				}
				//�ۼ�
				String strSllPrc=null;
				strSllPrc = txtSllPrc.getText().trim();
				if(strSllPrc.length()>0){
					shop.setSellPrice(Double.parseDouble(strSllPrc));
				}
				else{
					JOptionPane.showMessageDialog(null, "��������Ч���ۼ�!", "��ʾ��Ϣ", JOptionPane.ERROR_MESSAGE);;
					return;
				}
				//���
				String num= null;
				num= txtNum.getText().trim();
				if(num.length()>0){
					shop.setNum(Integer.parseInt(num));
				}
				else{
					JOptionPane.showMessageDialog(null, "��������Ч�Ŀ��!", "��ʾ��Ϣ", JOptionPane.ERROR_MESSAGE);;
					return;
				}
				//����
				int category=cboCategory.getSelectedIndex();
				if(category>0){
					shop.setCategory(category);
				}
				//����
				String strMarker= null;
				strMarker= txtMarker.getText().trim();
				if(strMarker.length()>0){
					shop.setMarker(strMarker);
				}
				else{
					JOptionPane.showMessageDialog(null, "�����볧��!", "��ʾ��Ϣ", JOptionPane.ERROR_MESSAGE);;
					return;
				}
				String strShelf= txtShelf.getText().trim();
				//�жϻ����Ƿ��Ѿ�����
				shop.setShelfID(strShelf);
				if(shopDao.insertShop(shop)>0){
					//���¼������
					clearInputArea();
					JOptionPane.showMessageDialog(null, "¼��ɹ�!", "��ʾ��Ϣ", JOptionPane.INFORMATION_MESSAGE);;
					
				}
			}
		});
		btnInsert.setBounds(77, 250, 93, 23);
		contentPane.add(btnInsert);
		
		JButton btnCancel = new JButton("\u53D6\u6D88");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(222, 250, 93, 23);
		contentPane.add(btnCancel);
		
		JLabel label = new JLabel("\u8D27\u67B6\uFF1A");
		label.setBounds(201, 81, 54, 15);
		contentPane.add(label);
		
		
	}
	//���¼������
	private void clearInputArea(){
		txtCstPrc.setText("");
		txtMarker.setText("");
		txtNum.setText("");
		txtShpID.setText("");
		txtShpName.setText("");
		txtSllPrc.setText("");
		txtShelf.setText("");
		//���
		cboCategory.setSelectedIndex(0);
	}
	public static void main(String[] args) {
		new ShopInsert().setVisible(true);
	}
}
