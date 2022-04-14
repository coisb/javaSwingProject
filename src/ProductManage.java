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
	String formLbl[] = {"제품번호","제품명","사이즈","가격","성별","연령대","수량"};
	JPanel formCenter = new JPanel(new GridLayout(6,1));
	JTextField[] formTf = {new JTextField(5), new JTextField(15), new JTextField(20),
			new JTextField(30), new JTextField(45), new JTextField(20)};
	DefaultTableModel model;
	String title = "제품번호/제품명/사이즈/가격/성별/연령대/수량";
	JTable productList;
	JScrollPane sp;
	JButton addBtn = new JButton("추가");
	JButton deleteBtn = new JButton("삭제");
	JButton changeBtn = new JButton("변경");
	Font fnt = new Font("함초롬돋움",Font.BOLD,15);
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

	// 데이터 베이스에서 회원전체 목록(이름순으로) 가져오기 - JTable을 가져온다.
	public void memberAllList() {
		ProductDAO dao = new ProductDAO();
		List<ProductVO> list = dao.productManageAllRecord();
		setMemberModel(list);
	}

	public void setMemberModel(List<ProductVO> list) {
		// 기존 JTable의 목록을 지우고 새로 리스트를 출력한다.
		model.setRowCount(0);
		// 컬렉션의 vo를 get하여 JTable에 목록으로 추가한다.
		for (int i = 0; i < list.size(); i++) {
			ProductVO vo = list.get(i);
			Object[] obj = { vo.getHan_num(), vo.getHan_name(), vo.getHan_size(), vo.getHan_price(), vo.getHan_gender(),
					vo.getHan_age(), vo.getHan_sum() };
			model.addRow(obj);
		}
	}

	public void actionPerformed(ActionEvent ae) {
		String eventBtn = ae.getActionCommand();
		if (eventBtn.equals("추가")) {
			NorthFrameAdmin.centerPanel.setVisible(false);
			NorthFrameAdmin.centerPanel.removeAll();
			ProductManageInsert pmi = new ProductManageInsert();
			NorthFrameAdmin.centerPanel.add(pmi.centerPanel);
			NorthFrameAdmin.centerPanel.setVisible(true);
		} else if (eventBtn.equals("삭제")) {
			productDelete();
		} else if (eventBtn.equals("변경")) {
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
		// 삭제할 사원
		String delNum = String.valueOf(TableMouseEvent.han_num);
		if (delNum == null || delNum.equals("")) {
			JOptionPane.showMessageDialog(this, "삭제할 상품을 선택후 삭제버튼을 클릭하세요..");
		}else {
			ProductDAO dao = new ProductDAO();
			dao.deleteRecord("product_amount" ,TableMouseEvent.han_num);
			dao.deleteRecord("product_img", TableMouseEvent.han_num);
			int result = dao.deleteRecord("product", TableMouseEvent.han_num);
			if (result > 0) { // 회원삭제됨
				JOptionPane.showMessageDialog(this, delNum + "상품을 삭제하였습니다.");
				memberAllList();
			} else { // 회원삭제실패함.
				JOptionPane.showMessageDialog(this, delNum + "상품삭제를 실패하였습니다.");
			}
		}
	}

	public void productChange() {

	}
}