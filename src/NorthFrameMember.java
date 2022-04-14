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

public class NorthFrameMember extends JFrame implements ActionListener {
	JPanel topPane = new JPanel(new BorderLayout());
	JPanel topWest = new JPanel();
	JPanel topCenter = new JPanel();
	JPanel topEast = new JPanel();
	JPanel topSouth = new JPanel(new GridLayout(0,5,5,5));
	JPanel btnPanel = new JPanel();
	static JPanel centerPanel;
	JPanel testPane = new JPanel();
	JButton mypageBtn = new JButton("����������");
	JButton logoutBtn = new JButton("�α׾ƿ�");
	JButton searchBtn = new JButton("�˻�");
	//JButton recommendBtn = new JButton("��õ");
	JButton seeAllBtn = new JButton("ALL");
	JButton womanBtn = new JButton("�����Ѻ�");
	JButton manBtn = new JButton("�����Ѻ�");
	JButton childBtn = new JButton("�Ƶ��Ѻ�");
	JTextField searchTf = new JTextField(10);
	//Font fnt = new Font("����", 1, 30);
	Font fnt = new Font("���ʷҵ���",Font.BOLD,15);
	Font titleFnt = new Font("CentSchbook BT", 1, 44);
	JLabel logoLbl = new JLabel("Bit hanbok      ");
	static String selectedMenu = "";
	
	public NorthFrameMember() {
	}

	public void start(JPanel pane) {
		topWest.setBackground(new Color(255,216,216)); //255,255,255 ȭ��Ʈ 255,216,216 ����ũ
		topCenter.setBackground(new Color(255,216,216));
		topEast.setBackground(new Color(255,216,216));
		topSouth.setBackground(new Color(255,216,216));//���� �����Ҷ� ���� �ٲ����1
		btnPanel.setBackground(new Color(255,216,216));//���� �����Ҷ� ���� �ٲ����2
		centerPanel = new JPanel(new BorderLayout());
		add(centerPanel);
		centerPanel.add(pane);
		setFrame();
		buttonSet();
		mypageBtn.setFont(fnt);
		topWest.add(mypageBtn);
		logoutBtn.setFont(fnt);
		topWest.add(logoutBtn);
		topPane.add(topCenter);
		logoLbl.setFont(titleFnt);
		topCenter.add(logoLbl);
		searchTf.setFont(fnt);
		topEast.add(searchTf);
		searchBtn.setFont(fnt);
		topEast.add(searchBtn);
		btnPanel.add(topSouth);
	//	recommendBtn.setFont(fnt);
	//	topSouth.add(recommendBtn);
		seeAllBtn.setFont(fnt);
		topSouth.add(seeAllBtn);
		womanBtn.setFont(fnt);
		topSouth.add(womanBtn);
		manBtn.setFont(fnt);
		topSouth.add(manBtn);
		childBtn.setFont(fnt);
		topSouth.add(childBtn);


		setSize(1300, 800);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		
		mypageBtn.addActionListener(this);
		logoutBtn.addActionListener(this);
		searchBtn.addActionListener(this);
	//	recommendBtn.addActionListener(this);
		seeAllBtn.addActionListener(this);
		womanBtn.addActionListener(this);
		manBtn.addActionListener(this);
		childBtn.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == mypageBtn) {
			centerPanel.setVisible(false);
			centerPanel.removeAll();
			MyPage mp = new MyPage();
			centerPanel.add(mp.pane);
			centerPanel.setVisible(true);
		}else if(obj == logoutBtn) {
			setVisible(false);
			Login login = new Login();
		}else if(obj == searchBtn) {
			String search = searchTf.getText();
			centerPanel.setVisible(false);
			centerPanel.removeAll();
			SearchList sl = new SearchList(search);
			centerPanel.add(sl.pane);
			centerPanel.setVisible(true);
		}
//		else if(obj == recommendBtn) {
//			centerPanel.setVisible(false);
//			centerPanel.removeAll();
//			ProductMain pm = new ProductMain();
//			centerPanel.add(pm.pane);
//			centerPanel.setVisible(true);
//		}
		else if(obj == seeAllBtn) {
			selectedMenu = "";
			
			centerPanel.setVisible(false);
			centerPanel.removeAll();
			ProductMain pm = new ProductMain();
			centerPanel.add(pm.pane);
			centerPanel.setVisible(true);
		}else if(obj == womanBtn) {
			selectedMenu = "and han_gender = 'F' and han_age = '����'";
			
			centerPanel.setVisible(false);
			centerPanel.removeAll();
			ProductMain pm = new ProductMain();
			centerPanel.add(pm.pane);
			centerPanel.setVisible(true);
		}else if(obj == manBtn) {
			selectedMenu = "and han_gender = 'M' and han_age = '����'";
			
			centerPanel.setVisible(false);
			centerPanel.removeAll();
			ProductMain pm = new ProductMain();
			centerPanel.add(pm.pane);
			centerPanel.setVisible(true);
		}else if(obj == childBtn) {
			selectedMenu = "and han_age = '�Ƶ�'";
			
			centerPanel.setVisible(false);
			centerPanel.removeAll();
			ProductMain pm = new ProductMain();
			centerPanel.add(pm.pane);
			centerPanel.setVisible(true);
		}
		///////////////��õ, All, �����Ѻ�, �����Ѻ�, �Ƶ��Ѻ� Ŭ������ ���� ����� �����ٰ���
		///////////////ProductMainŬ������ �����ٶ� sql���� �̿��ؼ� �ٸ� ��ǰ�� ��������??
	}

	public void setFrame() {
		add(BorderLayout.NORTH, topPane);
		topPane.add(BorderLayout.EAST, topEast);
		topPane.add(BorderLayout.SOUTH, btnPanel);
		topPane.add(BorderLayout.WEST, topWest);
	}

	public void buttonSet() {
		mypageBtn.setBorderPainted(false);
		logoutBtn.setBorderPainted(false);
		searchBtn.setBorderPainted(false);
//		recommendBtn.setBorderPainted(false);
		seeAllBtn.setBorderPainted(false);
		womanBtn.setBorderPainted(false);
		manBtn.setBorderPainted(false);
		childBtn.setBorderPainted(false);
		mypageBtn.setContentAreaFilled(false);
		logoutBtn.setContentAreaFilled(false);
		searchBtn.setContentAreaFilled(false);
//		recommendBtn.setContentAreaFilled(false);
		seeAllBtn.setContentAreaFilled(false);
		womanBtn.setContentAreaFilled(false);
		manBtn.setContentAreaFilled(false);
		childBtn.setContentAreaFilled(false);
		mypageBtn.setFocusPainted(false);
		logoutBtn.setFocusPainted(false);
		searchBtn.setFocusPainted(false);
//		recommendBtn.setFocusPainted(false);
		seeAllBtn.setFocusPainted(false);
		womanBtn.setFocusPainted(false);
		manBtn.setFocusPainted(false);
		childBtn.setFocusPainted(false);
	}

}