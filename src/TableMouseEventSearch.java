import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JTable;

public class TableMouseEventSearch extends MouseAdapter {
	JTable table;
	static int han_num;
	
	public TableMouseEventSearch() {
	}
	
	public TableMouseEventSearch(JTable table) {
		this.table = table;
	}
	
	public void mouseReleased(MouseEvent me) {
		if(me.getButton()==1) { // 왼쪽버튼 클리되면
			// 현재 클릭한 행을 구하여
			int row = table.getSelectedRow(); // 0, 1, 2, 3, 4
			String han_name = (String)table.getValueAt(row, 0);
			ProductDAO dao = new ProductDAO();
			int han_num = dao.getHanNum(han_name, "S");
			
			NorthFrameMember.centerPanel.setVisible(false);
			NorthFrameMember.centerPanel.removeAll();
			BorrowClothes bc = new BorrowClothes(han_num);
			NorthFrameMember.centerPanel.add(bc.centerPane);
			NorthFrameMember.centerPanel.setVisible(true);
		}
	}
}
