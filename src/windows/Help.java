package windows;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

public class Help extends JDialog {
/*	private static Help h= new Help();
	public static Help getInstance(){
		return h;
	}*/

	/**
	 * Create the dialog.
	 */
	public Help() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("\u8F6F\u4EF6\u76F8\u5173\u4FE1\u606F");
		setBounds(100, 100, 253, 164);
		getContentPane().setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("HEU\u8D85\u5E02\u7BA1\u7406\u7CFB\u7EDF1.0\u7248");
			lblNewLabel.setForeground(Color.BLUE);
			lblNewLabel.setFont(new Font("宋体", Font.BOLD, 14));
			lblNewLabel.setBounds(40, 10, 165, 15);
			getContentPane().add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("\u8F6F\u4EF63\u73ED\u5DE5\u4F5C\u5BA4");
			lblNewLabel_1.setForeground(Color.BLUE);
			lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 14));
			lblNewLabel_1.setBounds(73, 35, 98, 15);
			getContentPane().add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("\u8054\u7CFB\u7535\u8BDD\uFF1A18092054607");
			lblNewLabel_2.setForeground(Color.BLUE);
			lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 14));
			lblNewLabel_2.setBounds(40, 60, 165, 15);
			getContentPane().add(lblNewLabel_2);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("\u4F7F\u7528F2\u5FEB\u6377\u952E\u53EF\u9501\u5B9A\u7CFB\u7EDF");
			lblNewLabel_3.setForeground(Color.CYAN);
			lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 14));
			lblNewLabel_3.setBounds(45, 92, 154, 15);
			getContentPane().add(lblNewLabel_3);
		}
	}

}
