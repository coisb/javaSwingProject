import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JTable;

public class TableMouseEvent extends MouseAdapter {
	JTable table;
	static int han_num;
	static BufferedImage bi;
	public TableMouseEvent() {
		
	}
	public TableMouseEvent(JTable table) {
		this.table = table;
	}
	
	public void mousePressed(MouseEvent me) {
		if(me.getButton()==1) { // 왼쪽버튼 클리되면
			// 현재 클릭한 행을 구하여
			NorthFrameAdmin.centerPanel.setVisible(false);
			int row = table.getSelectedRow(); // 0, 1, 2, 3, 4
			han_num = (int)table.getValueAt(row, 0);
			ProductDAO dao = new ProductDAO();
			bi = dao.getSelectedImage(han_num);
			NorthFrameAdmin.centerPanel.setVisible(true);
		}
	}
}
