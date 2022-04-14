import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JTable;

public class TableMouseEventRent extends MouseAdapter {
	JTable table;
	static int rent_num;
	static String rent_status;
	static BufferedImage bi;
	public TableMouseEventRent() {
		
	}
	public TableMouseEventRent(JTable table) {
		this.table = table;
	}
	
	public void mousePressed(MouseEvent me) {
		if(me.getButton()==1) { // 왼쪽버튼 클리되면
			// 현재 클릭한 행을 구하여
			int row = table.getSelectedRow(); // 0, 1, 2, 3, 4
			rent_num = (int)table.getValueAt(row, 0);
			rent_status = (String)table.getValueAt(row, 7);
		}
	}
}
