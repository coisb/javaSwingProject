import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;



public class ProductManage extends JFrame implements ActionListener {
	JPanel pane = new JPanel();
	JPanel formPane = new JPanel();
	JPanel centerPane = new JPanel();
	String formLbl[] = {"��ǰ��ȣ","��ǰ��","������","����","����","���ɴ�","����"};
	JPanel formCenter = new JPanel(new GridLayout(6,1));
	JTextField[] formTf = {new JTextField(5), new JTextField(15), new JTextField(20),
			new JTextField(30), new JTextField(45), new JTextField(20)};
	DefaultTableModel model;
	String title = "��ǰ��ȣ/��ǰ��/������/����/����/���ɴ�/����";
	JTable productList;
	JScrollPane sp;
	JButton addBtn = new JButton("�߰�");
	JButton deleteBtn = new JButton("����");
	JButton changeBtn = new JButton("����");
	Font fnt = new Font("���ʷҵ���",Font.BOLD,15);
	JLabel picture;
	Container c = getContentPane();
	MyCanvas mc = new MyCanvas();

	public ProductManage() {
		pane.setBackground(new Color(255,255,255));
		centerPane.setBackground(new Color(255,255,255));
		setButtonTable();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		memberAllList();

		addBtn.addActionListener(this);
		deleteBtn.addActionListener(this);
		changeBtn.addActionListener(this);
		productList.addMouseListener(new TableMouseEvent(productList));
	}
	
	class MyCanvas extends Canvas{
		public void paint(Graphics g) {
			g.drawImage(TableMouseEvent.bi, 0, 0, this);
		}
	}

	public void setButtonTable() {

		pane.setLayout(null);
		pane.add(centerPane);
		centerPane.setBounds(600, 30, 650, 500);
		model = new DefaultTableModel(title.split("/"), 0);
		productList = new JTable(model);
		sp = new JScrollPane(productList);
		centerPane.add(sp);
		btn();
		picture();
	}

	public void btn() {
		addBtn.setFont(fnt);
		pane.add(addBtn).setBounds(740, 530, 100, 30);
		addBtn.setContentAreaFilled(false); addBtn.setFocusPainted(false);
		deleteBtn.setFont(fnt);
		pane.add(deleteBtn).setBounds(872, 530, 100, 30);
		deleteBtn.setContentAreaFilled(false); deleteBtn.setFocusPainted(false);
		changeBtn.setFont(fnt);
		pane.add(changeBtn).setBounds(1000, 530, 100, 30);
		changeBtn.setContentAreaFilled(false); changeBtn.setFocusPainted(false);
	}

	public void picture() {
		pane.add(mc);
		mc.setBounds(120, 30, 450, 563);
	}

	// ������ ���̽����� ȸ����ü ���(�̸�������) �������� - JTable�� �����´�.
	public void memberAllList() {
		ProductDAO dao = new ProductDAO();
		List<ProductVO> list = dao.productManageAllRecord();
		setMemberModel(list);
	}

	public void setMemberModel(List<ProductVO> list) {
		// ���� JTable�� ����� ����� ���� ����Ʈ�� ����Ѵ�.
		model.setRowCount(0);
		// �÷����� vo�� get�Ͽ� JTable�� ������� �߰��Ѵ�.
		for (int i = 0; i < list.size(); i++) {
			ProductVO vo = list.get(i);
			Object[] obj = { vo.getHan_num(), vo.getHan_name(), vo.getHan_size(), vo.getHan_price(), vo.getHan_gender(),
					vo.getHan_age(), vo.getHan_sum() };
			model.addRow(obj);
		}
	}

	public void actionPerformed(ActionEvent ae) {
		String eventBtn = ae.getActionCommand();
		if (eventBtn.equals("�߰�")) {
			NorthFrameAdmin.centerPanel.setVisible(false);
			NorthFrameAdmin.centerPanel.removeAll();
			ProductManageInsert pmi = new ProductManageInsert();
			NorthFrameAdmin.centerPanel.add(pmi.centerPanel);
			NorthFrameAdmin.centerPanel.setVisible(true);
		} else if (eventBtn.equals("����")) {
			productDelete();
		} else if (eventBtn.equals("����")) {
			///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			NorthFrameAdmin.centerPanel.setVisible(false);
			NorthFrameAdmin.centerPanel.removeAll();
			ProductManageCorrection pmi = new ProductManageCorrection();
			NorthFrameAdmin.centerPanel.add(pmi.centerPanel);
			NorthFrameAdmin.centerPanel.setVisible(true);
		}
	}

	public void productInsert() {

	}
	//////////////////
	public void productDelete() {
		// ������ ���
		String delNum = String.valueOf(TableMouseEvent.han_num);
		if (delNum == null || delNum.equals("")) {
			JOptionPane.showMessageDialog(this, "������ ��ǰ�� ������ ������ư�� Ŭ���ϼ���..");
		}else {
			ProductDAO dao = new ProductDAO();
			dao.deleteRecord("product_amount" ,TableMouseEvent.han_num);
			dao.deleteRecord("product_img", TableMouseEvent.han_num);
			int result = dao.deleteRecord("product", TableMouseEvent.han_num);
			if (result > 0) { // ȸ��������
				JOptionPane.showMessageDialog(this, delNum + "��ǰ�� �����Ͽ����ϴ�.");
				memberAllList();
			} else { // ȸ������������.
				JOptionPane.showMessageDialog(this, delNum + "��ǰ������ �����Ͽ����ϴ�.");
			}
		}
	}

	public void productChange() {

	}
}