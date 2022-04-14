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
		String title = "회원번호/회원명/성별/전화번호/생년월일/가입일/대여상태";
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
	
	//데이터 베이스에서 회원전체 목록(이름순으로) 가져오기 - JTable을 가져온다.
	public void memberAllList() {
		MemberDAO mdao = new MemberDAO();
		List<MemberVO> mlist = mdao.memberAllRecord();
		setMemberModel(mlist);
	}
	public void setMemberModel(List<MemberVO> mlist) {
		//기존 JTable의 목록을 지우고 새로 리스트를 출력한다.
		model.setRowCount(0);
		//컬렉션의 vo를 get하여 JTable에 목록으로 추가한다.
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
