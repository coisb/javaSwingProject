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
	String formLbl[] = {"�뿩��ȣ","��ǰ��ȣ","��ǰ��","������","����","�뿩����","�ݳ�����","�뿩����"};
	JPanel formCenter = new JPanel(new GridLayout(7,1));
	JTextField[] formTf = {new JTextField(5), new JTextField(5), new JTextField(15), new JTextField(20),
			new JTextField(30), new JTextField(45), new JTextField(20), new JTextField(10)};
	JPanel centerPane = new JPanel(new BorderLayout());
	DefaultTableModel model;
	String title = "�뿩��ȣ/��ǰ��ȣ/��ǰ��/������/����/�뿩����/�ݳ�����/�뿩����/��ü��";
	JTable memberList;
	JScrollPane sp;
	JButton cancelBtn = new JButton("�뿩���");
	JButton returnBtn = new JButton("�ݳ��ϱ�");
	Font fnt = new Font("���� ��ü L",Font.PLAIN,14); 

	public RentalList() {
		formPane.setBackground(new Color(255,255,255));//�Ʒ��� ������� ��
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

	// ��ü ��Ʈ�� �ƴ϶� �� ��Ʈ ����Ʈ
	public void memberAllList() {
		RentDAO rdao = new RentDAO();
		List<ProductVO> plist = rdao.rentAllRecord();
		setProductModel(plist);
	}

	public void setProductModel(List<ProductVO> plist) {
		//���� JTable�� ����� ����� ���� ����Ʈ�� ����Ѵ�.
		model.setRowCount(0);
		//�÷����� vo�� get�Ͽ� JTable�� ������� �߰��Ѵ�.
		for(int i=0; i<plist.size(); i++) {
			ProductVO vo= plist.get(i);
			Object[] obj = {vo.getRent_num(),vo.getHan_num(),vo.getHan_name(),vo.getHan_size(),vo.getHan_price(),
					vo.getRent_start(),vo.getRent_return(),vo.getRent_status(),vo.getRent_fee()};
			model.addRow(obj);
		}

	}

	public void actionPerformed(ActionEvent ae) {
		String eventBtn = ae.getActionCommand();
		if(eventBtn.equals("�뿩���")) {
			rentCancel();
		}else if(eventBtn.equals("�ݳ��ϱ�")) {
			rentReturn();
		}
	}

	public void rentCancel() {
		// ����� �뿩��ȣ
		String delNum = String.valueOf(TableMouseEvent.han_num);
		if (delNum == null || delNum.equals("")) {
			JOptionPane.showMessageDialog(this, "�뿩����� ǰ���� ������ ��ҹ�ư�� Ŭ���ϼ���..");
		}else if(TableMouseEventRent.rent_status.equals("N")) {//0802 12:31 ����
			JOptionPane.showMessageDialog(this, "�ݳ��� �Ϸ�� ��ǰ�� �뿩��Ҹ� �� �� �����ϴ�.");
		}
		else {
			RentDAO dao = new RentDAO();
			int result = dao.deleteRecord(TableMouseEventRent.rent_num);
			if (result > 0) { // �뿩��ҵ�
				JOptionPane.showMessageDialog(this, "�뿩�� ����Ͽ����ϴ�.");
				memberAllList();
			} else { // �뿩��ҽ�����.
				JOptionPane.showMessageDialog(this, "�뿩��Ҹ� �����Ͽ����ϴ�.");
			}
		}
	}

	public void rentReturn() {
		String delNum = String.valueOf(TableMouseEvent.han_num);
		if (delNum == null || delNum.equals("")) {
			JOptionPane.showMessageDialog(this, "�ݳ��� ǰ���� ������ ��ҹ�ư�� Ŭ���ϼ���.");
		} else {
			if(TableMouseEventRent.rent_status.equals("N")) {
				JOptionPane.showMessageDialog(this, "�뿩���� ǰ���� �ƴմϴ�.");
			}else if(TableMouseEventRent.rent_status.equals("Y")) {
				RentDAO dao = new RentDAO();
				int result = dao.returnProduct(TableMouseEventRent.rent_num);
				if (result > 0) { // �뿩��ҵ�
					JOptionPane.showMessageDialog(this, "�ݳ��� �Ϸ�Ǿ����ϴ�.");
					memberAllList();
				} else { // �뿩��ҽ�����.
					JOptionPane.showMessageDialog(this, "�ݳ��� �����Ͽ����ϴ�.");
				}
			}
		}
	}
}

