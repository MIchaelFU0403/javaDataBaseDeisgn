package common;

import javax.swing.table.DefaultTableModel;

public class MyDefaultTableModel extends DefaultTableModel {

	//��дisCellEditable
	@Override
	public boolean isCellEditable(int row, int column) {
		//true:���޸�;false:���ɱ༭
		return false;
	}
	//��ӹ��췽��
	public MyDefaultTableModel(Object[][] data, Object[] columnNames) {
		//���ø���Ĺ��췽��
		super(data, columnNames);
	}
	
}
