import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NorthFrameAdmin extends JFrame implements ActionListener {
	JPanel topPane = new JPanel(new BorderLayout());
	JPanel topWest = new JPanel(); // 보더레이아웃 서쪽이 비어있으면 라벨이 중간에 있지 못해 마지못해 추가....
	JPanel topCenter = new JPanel();
	JPanel topEast = new JPanel();
	JPanel topSouth = new JPanel(new GridLayout(0,4,10,10));
	JPanel btnPanel = new JPanel();
	JButton searchBtn = new JButton("검색");
	JButton memberBtn = new JButton("회원관리");
	JButton productBtn = new JButton("상품관리");
	JButton rentBtn = new JButton("대여관리");
	JButton logoutBtn = new JButton("로그아웃");
	JButton blankBtn1 = new JButton("             "); // 보더레이아웃 서쪽이 비어있으면 라벨이 중간에 있지 못해 마지못해 추가
	JButton blankBtn2 = new JButton("             "); // 보더레이아웃 서쪽이 비어있으면 라벨이 중간에 있지 못해 마지못해 추가
	JTextField searchTf = new JTextField(10);
	//Font fnt = new Font("굴림", 1, 30);
	JLabel logoLbl = new JLabel("Bit hanbok            ");
	static JPanel centerPanel;
	
	Font fnt = new Font("함초롬돋움",Font.BOLD,15);
	Font titleFnt = new Font("CentSchbook BT", 1, 44);

	public NorthFrameAdmin() {
	}
	
	public void start(JPanel pane) {
		topWest.setBackground(new Color(255,255,255));
		topCenter.setBackground(new Color(255,255,255));
		topEast.setBackground(new Color(255,255,255));
		topSouth.setBackground(new Color(255,255,255));//색상 변경할때 같이 바꿔야함1
		btnPanel.setBackground(new Color(255,255,255));//색상 변경할때 같이 바꿔야함2
		setPanel();
		setBtnDesign();
		logoLbl.setFont(titleFnt);
		centerPanel = new JPanel(new BorderLayout());
		add(centerPanel);
		centerPanel.add(pane);
		
		setSize(1300, 800);
		setVisible(true); 
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		
		searchBtn.addActionListener(this);
		memberBtn.addActionListener(this);
		productBtn.addActionListener(this);
		rentBtn.addActionListener(this);
		logoutBtn.addActionListener(this);
	}

	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		if(obj == searchBtn) {
			// db 데이터 삽입 후 구현필요
		}else if(obj == memberBtn) {
			// 
			centerPanel.setVisible(false);
			centerPanel.removeAll();
			MemberList ml = new MemberList();
			centerPanel.add(ml.centerPane);
			centerPanel.setVisible(true);
		}else if(obj == productBtn) {
			centerPanel.setVisible(false);
			centerPanel.removeAll();
			ProductManage pl = new ProductManage();
			centerPanel.add(pl.pane);
			centerPanel.setVisible(true);
		}else if(obj == rentBtn) {
			centerPanel.setVisible(false);
			centerPanel.removeAll();
			RentManage rm = new RentManage();
			centerPanel.add(rm.centerPane);
			centerPanel.setVisible(true);
		}else if(obj == logoutBtn) {
			setVisible(false);
			Login login = new Login();
		}
	}

	public void setPanel() {
		add(BorderLayout.NORTH, topPane);
		topPane.add(topCenter);
		topCenter.add(logoLbl);
		topPane.add(BorderLayout.WEST, topWest);
		topWest.add(blankBtn1);
		topWest.add(blankBtn2);
		topPane.add(BorderLayout.EAST, topEast);
	//	topEast.add(searchTf);
	//	topEast.add(searchBtn);
		topPane.add(BorderLayout.SOUTH, btnPanel);
		btnPanel.add(topSouth);
		memberBtn.setFont(fnt);
		topSouth.add(memberBtn);
		productBtn.setFont(fnt);
		topSouth.add(productBtn);
		rentBtn.setFont(fnt);
		topSouth.add(rentBtn);
		logoutBtn.setFont(fnt);
		topSouth.add(logoutBtn);
	}
	
	public void setBtnDesign() {
		blankBtn1.setBorderPainted(false);
		blankBtn2.setBorderPainted(false);
		blankBtn1.setContentAreaFilled(false);
		blankBtn2.setContentAreaFilled(false);
		blankBtn1.setFocusPainted(false);
		blankBtn2.setFocusPainted(false);
		searchBtn.setBorderPainted(false);
		memberBtn.setBorderPainted(false);
		productBtn.setBorderPainted(false);
		rentBtn.setBorderPainted(false);
		logoutBtn.setBorderPainted(false);
		searchBtn.setContentAreaFilled(false);
		memberBtn.setContentAreaFilled(false);
		productBtn.setContentAreaFilled(false);
		rentBtn.setContentAreaFilled(false);
		logoutBtn.setContentAreaFilled(false);
		searchBtn.setFocusPainted(false);
		memberBtn.setFocusPainted(false);
		productBtn.setFocusPainted(false);
		rentBtn.setFocusPainted(false);
		logoutBtn.setFocusPainted(false);
	}
	
}
