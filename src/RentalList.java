import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class RentalList extends JFrame implements ActionListener{
	JPanel pane = new JPanel(new BorderLayout());
	JPanel formPane = new JPanel();
	String formLbl[] = {"대여번호","제품번호","제품명","사이즈","가격","대여일자","반납일자","대여상태"};
	JPanel formCenter = new JPanel(new GridLayout(7,1));
	JTextField[] formTf = {new JTextField(5), new JTextField(5), new JTextField(15), new JTextField(20),
			new JTextField(30), new JTextField(45), new JTextField(20), new JTextField(10)};
	JPanel centerPane = new JPanel(new BorderLayout());
	DefaultTableModel model;
	String title = "대여번호/제품번호/제품명/사이즈/가격/대여일자/반납일자/대여상태/연체료";
	JTable memberList;
	JScrollPane sp;
	JButton cancelBtn = new JButton("대여취소");
	JButton returnBtn = new JButton("반납하기");
	Font fnt = new Font("한컴 윤체 L",Font.PLAIN,14); 

	public RentalList() {
		formPane.setBackground(new Color(255,255,255));//아래만 흰색으로 됨
		setButtonTable();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		memberAllList();

		cancelBtn.addActionListener(this);
		returnBtn.addActionListener(this);
		memberList.addMouseListener(new TableMouseEventRent(memberList));
	}

	public void setButtonTable() {
		pane.add(centerPane);
		model = new DefaultTableModel(title.split("/"),0);
		memberList = new JTable(model);
		sp = new JScrollPane(memberList);
		centerPane.add(sp);
		btn();

	}
	public void btn(){
		pane.add(BorderLayout.SOUTH, formPane);
		cancelBtn.setFont(fnt);
		cancelBtn.setContentAreaFilled(false); cancelBtn.setFocusPainted(false);
		formPane.add(cancelBtn);
		returnBtn.setFont(fnt);
		returnBtn.setContentAreaFilled(false); returnBtn.setFocusPainted(false);
		formPane.add(returnBtn);

	}

	// 전체 렌트가 아니라 내 렌트 리스트
	public void memberAllList() {
		RentDAO rdao = new RentDAO();
		List<ProductVO> plist = rdao.rentAllRecord();
		setProductModel(plist);
	}

	public void setProductModel(List<ProductVO> plist) {
		//기존 JTable의 목록을 지우고 새로 리스트를 출력한다.
		model.setRowCount(0);
		//컬렉션의 vo를 get하여 JTable에 목록으로 추가한다.
		for(int i=0; i<plist.size(); i++) {
			ProductVO vo= plist.get(i);
			Object[] obj = {vo.getRent_num(),vo.getHan_num(),vo.getHan_name(),vo.getHan_size(),vo.getHan_price(),
					vo.getRent_start(),vo.getRent_return(),vo.getRent_status(),vo.getRent_fee()};
			model.addRow(obj);
		}

	}

	public void actionPerformed(ActionEvent ae) {
		String eventBtn = ae.getActionCommand();
		if(eventBtn.equals("대여취소")) {
			rentCancel();
		}else if(eventBtn.equals("반납하기")) {
			rentReturn();
		}
	}

	public void rentCancel() {
		// 취소할 대여번호
		String delNum = String.valueOf(TableMouseEvent.han_num);
		if (delNum == null || delNum.equals("")) {
			JOptionPane.showMessageDialog(this, "대여취소할 품목을 선택후 취소버튼을 클릭하세요..");
		}else if(TableMouseEventRent.rent_status.equals("N")) {//0802 12:31 수정
			JOptionPane.showMessageDialog(this, "반납이 완료된 상품은 대여취소를 할 수 없습니다.");
		}
		else {
			RentDAO dao = new RentDAO();
			int result = dao.deleteRecord(TableMouseEventRent.rent_num);
			if (result > 0) { // 대여취소됨
				JOptionPane.showMessageDialog(this, "대여를 취소하였습니다.");
				memberAllList();
			} else { // 대여취소실패함.
				JOptionPane.showMessageDialog(this, "대여취소를 실패하였습니다.");
			}
		}
	}

	public void rentReturn() {
		String delNum = String.valueOf(TableMouseEvent.han_num);
		if (delNum == null || delNum.equals("")) {
			JOptionPane.showMessageDialog(this, "반납할 품목을 선택후 취소버튼을 클릭하세요.");
		} else {
			if(TableMouseEventRent.rent_status.equals("N")) {
				JOptionPane.showMessageDialog(this, "대여중인 품목이 아닙니다.");
			}else if(TableMouseEventRent.rent_status.equals("Y")) {
				RentDAO dao = new RentDAO();
				int result = dao.returnProduct(TableMouseEventRent.rent_num);
				if (result > 0) { // 대여취소됨
					JOptionPane.showMessageDialog(this, "반납이 완료되었습니다.");
					memberAllList();
				} else { // 대여취소실패함.
					JOptionPane.showMessageDialog(this, "반납을 실패하였습니다.");
				}
			}
		}
	}
}

