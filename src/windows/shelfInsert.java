package windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.shelfDao;
import entity.shelf;

public class shelfInsert extends JFrame{
	private JTable table;
	private JButton trigger;
	private shelfDao shelfdao= new shelfDao();
	private JTextField txtID;
	private JTextField txtLayer;
	private shelf sf=new shelf();
	public shelfInsert() {
		getContentPane().setLayout(null);
		setSize(402,350);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(81, 129, 296, 171);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"\u8D27\u67B6\u53F7", "\u8D27\u67B6\u5C42"
			}
		));
		scrollPane.setViewportView(table);
		//��table��������
		fillTbl();
		
		JButton trigger = new JButton("\u6DFB\u52A0\u65B0\u7684\u8D27\u67B6");
		trigger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//��Ч���ж�
				//����
				String strID= txtID.getText().trim();
				
				if(strID.length()<=0){
					JOptionPane.showMessageDialog(shelfInsert.this, "���벻��Ϊ��!", "��ʾ��Ϣ", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(shelfDao.getIDDublicate(strID)>0){
					JOptionPane.showMessageDialog(shelfInsert.this, "�����Ѵ���!", "��ʾ��Ϣ", JOptionPane.ERROR_MESSAGE);
					return;
				}
				sf.setShelfID(strID);
				//����
				String strLayer= txtLayer.getText().trim();
				if(!(strLayer.length()>0)){
					JOptionPane.showMessageDialog(null, "��������Ϊ��!", "��ʾ��Ϣ", JOptionPane.ERROR_MESSAGE);;
					return;
				}
				sf.setShelfLayer(strLayer);
			
			if(shelfDao.insertShelf(sf)>0){
				//���¼������
				clearInputArea();
				JOptionPane.showMessageDialog(null, "¼��ɹ�!", "��ʾ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
				fillTbl();
				}
			}
		});
		trigger.setBounds(121, 66, 236, 23);
		getContentPane().add(trigger);
		
		txtID = new JTextField();
		txtID.setBounds(148, 22, 66, 21);
		getContentPane().add(txtID);
		txtID.setColumns(10);
		
		txtLayer = new JTextField();
		txtLayer.setColumns(10);
		txtLayer.setBounds(291, 22, 66, 21);
		getContentPane().add(txtLayer);
		
		JLabel label = new JLabel("\u8D27\u67B6\u7F16\u53F7");
		label.setBounds(98, 25, 54, 15);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u8D27\u67B6\u5C42\u6570");
		label_1.setBounds(238, 25, 54, 15);
		getContentPane().add(label_1);
		
		JButton btntrigger = new JButton("\u64A4\u51FA\u8D27\u67B6\uFF08trigger\uFF09");
		btntrigger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			////��Ч���ж�
			//����
				String shelfID = txtID.getText().trim();
				int i=JOptionPane.showConfirmDialog(shelfInsert.this, "Ҫɾ����������", "��ʾ", JOptionPane.YES_NO_OPTION);
				if(i==JOptionPane.YES_OPTION){
					if(shelfdao.delshelf(shelfID)>0){
						JOptionPane.showMessageDialog(shelfInsert.this, "ɾ���ɹ�!", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
						fillTbl();
						clearInputArea();
					}
				}
			}
		});
		btntrigger.setBounds(121, 99, 236, 23);
		getContentPane().add(btntrigger);
	}
	//���¼������
	private void clearInputArea(){
		txtID.setText("");
		txtLayer.setText("");
		
	}
	private void fillTbl() {
	        //����table��model����
			DefaultTableModel dtm=(DefaultTableModel)table.getModel();
			//��յĿհ���
			dtm.setRowCount(0);
			//���ر��ε��б�
			Vector<Vector<Object>> vV=shelfdao.getAllInfo();
			//����vV
			for (Vector<Object> v : vV) {
				dtm.addRow(v);
			}
	}
	public static void main(String[] args) {
		new shelfInsert().setVisible(true);
	}
	
}