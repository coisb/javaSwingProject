import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;


public class ProductManageInsert extends JFrame implements ActionListener  {
	Font previewFnt = new Font("times new roman", 1, 20);
	Font fnt = new Font("함초롬돋움",Font.BOLD,14); 
	JPanel centerPanel = new JPanel(new BorderLayout());
	JLabel previewLbl = new JLabel("Product\nPreview");
	JLabel lbl1 = new JLabel("제품번호");
	JLabel lbl2 = new JLabel("제품명");
	JLabel lbl3 = new JLabel("사이즈");
	JLabel lbl4 = new JLabel("성별");
	JLabel lbl5 = new JLabel("연령");
	JLabel lbl6 = new JLabel("가격");
	JLabel lbl7 = new JLabel("수량");
	JTextField productNumTf = new JTextField(20);
	JTextField productNameTf = new JTextField(20);
	JTextField productPriceTf = new JTextField(20);
	JTextField productSumTf = new JTextField(20);
	JComboBox cb1 = new JComboBox();
	String[] size = {"", "S", "M", "L"};
	DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(size);
	JRadioButton female = new JRadioButton("여성");
	JRadioButton male = new JRadioButton("남성");
	JRadioButton child = new JRadioButton("아동");
	JRadioButton adult = new JRadioButton("성인");
	ButtonGroup sex = new ButtonGroup();
	ButtonGroup age = new ButtonGroup();
	JButton btn1 = new JButton("이미지 선택");
	JButton btn2 = new JButton("추가");
	JButton btn3 = new JButton("취소");
	String han_image;
	
	public ProductManageInsert() {
		centerPanel.setBackground(new Color(255,255,255));
		
		addPanel();
		setPanelLocation();
		female.setSelected(true);
		child.setSelected(true);
		cb1.setModel(model);
		previewLbl.setFont(previewFnt);
		previewLbl.setBounds(50, 20, 450, 563);
		previewLbl.setOpaque(true);
		previewLbl.setBackground(new Color(75, 75, 242));

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(this);
	}

	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		if(obj == btn1) {
			fileOpen();
		}else if(obj == btn2) {
			if(productNumTf == null || productNumTf.getText().equals("")){
				JOptionPane.showMessageDialog(null, "제품번호를 입력해주세요.","INPUT ID",JOptionPane.CLOSED_OPTION);
			}else if(productNameTf == null || productNameTf.getText().equals("")){
				JOptionPane.showMessageDialog(null, "제품명을 입력해주세요.","INPUT ID",JOptionPane.CLOSED_OPTION);
			}else if(productPriceTf == null || productPriceTf.getText().equals("")){
				JOptionPane.showMessageDialog(null, "제품가격을 입력해주세요.","INPUT ID",JOptionPane.CLOSED_OPTION);
			}else if(productSumTf == null || productSumTf.getText().equals("")){
				JOptionPane.showMessageDialog(null, "제품 수량을 입력해주세요.","INPUT ID",JOptionPane.CLOSED_OPTION);
			}else if(cb1.getSelectedItem().equals("")) {
				JOptionPane.showMessageDialog(null, "제품 사이즈를 선택해주세요.","INPUT ID",JOptionPane.CLOSED_OPTION);
			}else if(han_image==null || han_image == ""){
				JOptionPane.showMessageDialog(null, "제품 사진을 업로드해주세요.","INPUT ID",JOptionPane.CLOSED_OPTION);
			}else {
				// db 올리기
				productUpdate();
				JOptionPane.showMessageDialog(null, "상품등록 완료","INPUT ID",JOptionPane.CLOSED_OPTION);
				// 올리고 ProductList로 화면 전환
				NorthFrameAdmin.centerPanel.setVisible(false);
				NorthFrameAdmin.centerPanel.removeAll();
				ProductManage pl = new ProductManage();
				NorthFrameAdmin.centerPanel.add(pl.pane);
				NorthFrameAdmin.centerPanel.setVisible(true);
			}
		}else if(obj == btn3) {
			// ProductList class로 돌아가기
			NorthFrameAdmin.centerPanel.setVisible(false);
			NorthFrameAdmin.centerPanel.removeAll();
			ProductManage pl = new ProductManage();
			NorthFrameAdmin.centerPanel.add(pl.pane);
			NorthFrameAdmin.centerPanel.setVisible(true);
		}
	}
	
	public void productUpdate() {
		ProductVO vo = new ProductVO();
		vo.setHan_num(Integer.parseInt(productNumTf.getText()));
		vo.setHan_name(productNameTf.getText());
		vo.setHan_size((String)cb1.getSelectedItem());
		if(male.isSelected()) {
			vo.setHan_gender("M");
		}else if(female.isSelected()) {
			vo.setHan_gender("F");
		}
		if(child.isSelected()) {
			vo.setHan_age(child.getText());
		}else if(adult.isSelected()){
			vo.setHan_age(adult.getText());
		}
		vo.setHan_price(Integer.parseInt(productPriceTf.getText()));
		vo.setHan_sum(Integer.parseInt(productSumTf.getText()));
		vo.setHan_image_path(han_image.substring(0,2) + "\\" + han_image.substring(2));
		
		ProductDAO dao = new ProductDAO();
		int cnt = dao.productInsert(vo);
		int cnt1 = dao.productInsertAmount(vo);
		int cnt2 = dao.productInsertImg(vo);
		
	}

	public void setPanelLocation() {
		// 라벨 위치
		lbl1.setFont(fnt);
		lbl1.setBounds(550, 80, 100, 30);
		lbl2.setFont(fnt);
		lbl2.setBounds(550, 150, 100, 30);
		lbl3.setFont(fnt);
		lbl3.setBounds(550, 220, 100, 30);
		lbl4.setFont(fnt);
		lbl4.setBounds(550, 290, 100, 30);
		lbl5.setFont(fnt);
		lbl5.setBounds(550, 360, 100, 30);
		lbl6.setFont(fnt);
		lbl6.setBounds(550, 430, 100, 30);
		lbl7.setFont(fnt);
		lbl7.setBounds(550, 500, 100, 30);
		// 라벨 추가
		productNumTf.setBounds(900, 80, 200, 30);
		productNameTf.setBounds(900, 150, 200, 30);
		cb1.setBounds(900, 220, 100, 30);
		cb1.setBackground(Color.WHITE);
		female.setFont(fnt);
		female.setBounds(900, 290, 100, 30);
		female.setContentAreaFilled(false); female.setFocusPainted(false);
		male.setFont(fnt);
		male.setBounds(1000, 290, 100, 30);
		male.setContentAreaFilled(false); male.setFocusPainted(false);
		child.setFont(fnt);
		child.setBounds(900, 360, 100, 30);
		child.setContentAreaFilled(false); child.setFocusPainted(false);
		adult.setFont(fnt);
		adult.setBounds(1000, 360, 100, 30);
		adult.setContentAreaFilled(false); adult.setFocusPainted(false);

		productPriceTf.setBounds(900, 430, 200, 30);
		productSumTf.setBounds(900, 500, 200, 30);
		// 이미지선택 버튼
		btn1.setFont(fnt);
		btn1.setBounds(100, 600, 329, 30);
		btn1.setContentAreaFilled(false);
		btn1.setFocusPainted(false);
		// 수정, 취소 버튼
		btn2.setFont(fnt);
		btn2.setBounds(700, 600, 70, 30);
		btn2.setContentAreaFilled(false);
		btn2.setFocusPainted(false);
		btn3.setFont(fnt);
		btn3.setBounds(800, 600, 70, 30);
		btn3.setContentAreaFilled(false);
		btn3.setFocusPainted(false);
	}

	public void addPanel() {
		add(centerPanel);
		centerPanel.add(lbl1);
		centerPanel.add(lbl2);
		centerPanel.add(lbl3);
		centerPanel.add(lbl4);
		centerPanel.add(lbl5);
		centerPanel.add(lbl6);
		centerPanel.add(lbl7);
		centerPanel.setLayout(null);
		centerPanel.add(previewLbl);
		centerPanel.add(productNumTf);
		centerPanel.add(productNameTf);
		centerPanel.add(productPriceTf);
		centerPanel.add(productSumTf);
		centerPanel.add(cb1);
		centerPanel.add(female);
		centerPanel.add(male);
		sex.add(female);
		sex.add(male);
		centerPanel.add(child);
		centerPanel.add(adult);
		age.add(child);
		age.add(adult);
		centerPanel.add(btn1);
		centerPanel.add(btn2);
		centerPanel.add(btn3);
	}

	public void fileOpen() {
		JFileChooser fc = new JFileChooser(new File("C://img"));
		fc.setMultiSelectionEnabled(false);
		FileFilter filter = new FileNameExtensionFilter("그림파일(*.jpg)", "jpg", "jpeg");
		FileFilter filter2 = new FileNameExtensionFilter("그림파일(*.png)", "png");
		fc.setFileFilter(filter2);
		fc.setFileFilter(filter);

		int choose = fc.showOpenDialog(this);
		if(choose == 0) {
			try {
				File f = fc.getSelectedFile();
				this.han_image = f.getPath();
				BufferedImage image = ImageIO.read(f);
				ImageIcon img = new ImageIcon(image);
				previewLbl.setIcon(img);
			}catch(Exception e) {}
		}
	}
}
