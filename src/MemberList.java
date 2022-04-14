import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MemberList extends JFrame {
	JPanel pane = new JPanel(new BorderLayout());
	JPanel formPane = new JPanel();

	JPanel centerPane = new JPanel(new BorderLayout());
		DefaultTableModel model;
		String title = "ȸ����ȣ/ȸ����/����/��ȭ��ȣ/�������/������/�뿩����";
		JTable memberList;
		JScrollPane sp;
	
	public MemberList() {
		setButtonTable();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		memberAllList();
		
	}

	public void setButtonTable() {
	
		pane.add(centerPane);
		model = new DefaultTableModel(title.split("/"),0);
		memberList = new JTable(model);
		sp = new JScrollPane(memberList);
		centerPane.add(sp);
	}
	
	//������ ���̽����� ȸ����ü ���(�̸�������) �������� - JTable�� �����´�.
	public void memberAllList() {
		MemberDAO mdao = new MemberDAO();
		List<MemberVO> mlist = mdao.memberAllRecord();
		setMemberModel(mlist);
	}
	public void setMemberModel(List<MemberVO> mlist) {
		//���� JTable�� ����� ����� ���� ����Ʈ�� ����Ѵ�.
		model.setRowCount(0);
		//�÷����� vo�� get�Ͽ� JTable�� ������� �߰��Ѵ�.
		for(int i=0; i<mlist.size(); i++) {
			MemberVO vo= mlist.get(i);
			Object[] obj = {vo.getMem_num(), vo.getMem_name(), vo.getMem_gender(),
							vo.getMem_tel(), vo.getMem_birth(), vo.getMem_date(),vo.getRent_status()};
			model.addRow(obj);
		}
				
	}
	
				
	
	
	public static void main(String[] args) {
		
		new MemberList();

	}

}
