import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class RentManage extends JFrame {
	JPanel centerPane = new JPanel(new BorderLayout());
		DefaultTableModel model;
		String title = "�뿩��ȣ/��ǰ��ȣ/ȸ����ȣ/�뿩��/�ݳ�������/�����ݳ���/��ü��";
		JTable memberList;
		JScrollPane sp;
	
	public RentManage() {
		setButtonTable();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		memberAllList();
	}

	public void setButtonTable() {
		add(centerPane);
		model = new DefaultTableModel(title.split("/"),0);
		memberList = new JTable(model);
		sp = new JScrollPane(memberList);
		centerPane.add(sp);

	}
	
	public void memberAllList() {
		ProductDAO dao = new ProductDAO();
		List<RentVO> list = dao.productAllRecord();
		setRentModel(list);
	}
	

	public void setRentModel(List<RentVO> list) {
		//���� JTable�� ����� ����� ���� ����Ʈ�� ����Ѵ�.
		model.setRowCount(0);
		//�÷����� vo�� get�Ͽ� JTable�� ������� �߰��Ѵ�.
		for(int i=0; i<list.size(); i++) {
			RentVO vo= list.get(i);
			Object[] obj = {vo.getRent_num(),vo.getHan_num(),vo.getMem_num(),vo.getRent_start(),vo.getRent_return(), vo.getRent_real_return(), vo.getRent_fee()};
			model.addRow(obj);
		}
	}
}