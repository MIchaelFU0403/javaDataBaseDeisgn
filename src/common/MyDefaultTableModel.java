package common;

import javax.swing.table.DefaultTableModel;

public class MyDefaultTableModel extends DefaultTableModel {

	//重写isCellEditable
	@Override
	public boolean isCellEditable(int row, int column) {
		//true:可修改;false:不可编辑
		return false;
	}
	//添加构造方法
	public MyDefaultTableModel(Object[][] data, Object[] columnNames) {
		//沿用父类的构造方法
		super(data, columnNames);
	}
	
}
