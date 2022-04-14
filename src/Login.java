/////////////////////////// 멤버로그인, 관리자 로그인 선택 안할시 오류 메세지 출력 필요해보임 ///////////////////////////////////////////////////////////////////////

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
    
public class Login extends JFrame{
	
	JLayeredPane layeredPane;
	BufferedImage img = null;
	                //"Han Sans,본고딕"
	Font titleFnt = new Font("CentSchbook BT", 1, 50);
	Font loginFnt = new Font("CentSchbook BT", 0, 30);
	
	Font fnt = new Font("함초롬돋움",Font.PLAIN,13); 
	Font fnt2 = new Font("함초롬돋움",Font.BOLD,14); 
	
	JPanel loginPane = new JPanel();
	JLabel titleLbl = new JLabel("Bit hanbok");
	JLabel loginLbl = new JLabel("LOGIN");
	JLabel id = new JLabel("ID");
	JLabel pw = new JLabel("PW");
	JTextField idTf = new JTextField(20);
	JPasswordField pwTf = new JPasswordField(20);
	JRadioButton memRb = new JRadioButton("회원로그인");
	JRadioButton adminRb = new JRadioButton("관리자로그인");
	JButton loginBtn = new JButton("로그인");
	JButton joinBtn = new JButton("회원가입");
	ButtonGroup bg = new ButtonGroup();
	
	
	String master_id = "apple";
	String master_pw = "1111";
	
	//로그인 한 멤버의..
	static String mem_id; // mem_id 회원아이디
	static int loginNum; //mem_num 회원번호
	
	public Login() {
		setLayout(null);
		buttonSet();
		
		try {
			img = ImageIO.read(new File("img/222.png"));
		}catch(Exception e) {
		  e.printStackTrace();
		}
		MyPanel panel = new MyPanel();
		panel.setBounds(0,0,Main.WIDTH_SIZE, Main.HEIGHT_SIZE);
		
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(0,0,Main.WIDTH_SIZE, Main.HEIGHT_SIZE);
		layeredPane.setLayout(null);
				
		add(layeredPane);
		
		pwTf.setEchoChar('*'); //pw를 입력할 때 pw필드에 **으로 보임.
		setResizable(false);
		add(loginPane);
		setBounds(0,0,Main.WIDTH_SIZE, Main.HEIGHT_SIZE);
		loginPane.setLayout(null);
		
		layeredPane.add(titleLbl);
		layeredPane.add(loginLbl);
		layeredPane.add(id);
		layeredPane.add(pw);
		layeredPane.add(idTf);
		layeredPane.add(pwTf);
		layeredPane.add(memRb);
		layeredPane.add(adminRb);
		bg.add(memRb);
		bg.add(adminRb);

		layeredPane.add(loginBtn);
		layeredPane.add(joinBtn);
		
		titleLbl.setFont(titleFnt);
		titleLbl.setBounds(910, 100, 300, 200);
		loginLbl.setFont(loginFnt);
		loginLbl.setBounds(1000, 250, 200, 200);
		id.setFont(fnt);
		id.setBounds(950, 400, 100, 16);//
		idTf.setBounds(1000, 400, 100, 16);
		pw.setFont(fnt);
		pw.setBounds(950, 430, 100, 16);
		pwTf.setBounds(1000, 430, 100, 16);
		memRb.setFont(fnt);
		memRb.setBounds(940, 460, 90, 16);
		adminRb.setFont(fnt);
		adminRb.setBounds(1050, 460, 110, 16);
		loginBtn.setFont(fnt2);
		loginBtn.setBounds(940, 500, 100, 25);
		joinBtn.setFont(fnt2);
		joinBtn.setBounds(1060, 500, 100, 25);

		setSize(Main.WIDTH_SIZE, Main.HEIGHT_SIZE);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		layeredPane.add(panel);
		
		loginBtn.addActionListener(new MainEvent()); //로그인 버튼 이벤트 등록
		joinBtn.addActionListener(new MainEvent()); // 회원가입 버튼 이벤트 등록
		
	}
	class MainEvent implements ActionListener{ //버튼 이벤트 ->내부클래스로 처리
		public void actionPerformed(ActionEvent ae) {
			Object event = ae.getSource();
			
			String searchId = idTf.getText(); // idTextField에 사용자가 입력한 문자열.
			
			mem_id= searchId;
			
			String searchPw = new String(pwTf.getPassword()); //비밀번호 입력한 것을 str 변수에 담음.
			if(event instanceof JButton) { //클릭한 것이 JButton일 경우
								
				if(ae.getActionCommand().equals("로그인")) { //로그인 버튼 클릭시
					//id,pw,로그인라디오버튼 선택안했을 때 선택하라고 오류메세지 출력함.
					if(searchId.trim()==null ||searchId.trim().equals("")) { //ID를 입력안했을 때
						JOptionPane.showMessageDialog(null, "ID를 입력해주세요.","INPUT ID",JOptionPane.CLOSED_OPTION);
					}else if(searchPw.trim()==null || searchPw.trim().equals("")) {// PW를 입력안했을 때
						JOptionPane.showMessageDialog(null, "PW를 입력해주세요.","INPUT PW",JOptionPane.CLOSED_OPTION);
					}else if(memRb.getSelectedObjects()==null && adminRb.getSelectedObjects()==null) { //회원 또는 관리자 버튼을 선택안했을 때
						JOptionPane.showMessageDialog(null, "회원 또는 관리자를 선택해주세요.","Click CheckBox",JOptionPane.CLOSED_OPTION);
					}else if(adminRb.getSelectedObjects()!=null){ //관리자 로그인 시
						if(searchId.equals(master_id)&&searchPw.equals(master_pw)) { //관리자 로그인 성공
							JOptionPane.showMessageDialog(null, "관리자 로그인 성공!","MASTER LOGIN",JOptionPane.CLOSED_OPTION);
							//관리자 페이지로 이동..
							setVisible(false);
							NorthFrameAdmin nfm = new NorthFrameAdmin();
							MemberList ml = new MemberList(); 
							nfm.start(ml.centerPane);
							
						}else { //관리자 로그인 실패
							JOptionPane.showMessageDialog(null, "ID와 PW가 일치하지 않습니다.","MASTER LOGIN ERROR",JOptionPane.CLOSED_OPTION);
						}
					}else { //회원로그인 시
						int check = idpwSearch(searchId,searchPw);
						if(check==1) {
							//로그인 한 member의 유저번호를 얻어옴.
							MemberDAO mD = new MemberDAO();
							loginNum = mD.getMemNum(mem_id);
							
							//ProductMain 출력하는 부분.
							NorthFrameMember nfm = new NorthFrameMember();
							ProductMain pm = new ProductMain();
							nfm.start(pm.pane);
						}
						
					}
				}else if(ae.getActionCommand().equals("회원가입")) {
					setVisible(false);
					JoinMember jm = new JoinMember();
					
				}				
			}			
		}
		 //로그인 시 id,pw가 일치하는 지 확인하는 메소드.
		public int idpwSearch(String searchId, String searchPw) { 
			MemberDAO DAO = new MemberDAO();
			int idpwequals = DAO.getIDPW(searchId, searchPw); //idpwequals -> 0이면 로그인실패, 1이면 로그인 성공
			if(idpwequals==0) {
				JOptionPane.showMessageDialog(null, "ID와 PW가 일치하지 않습니다.","ID&PW ERROR",JOptionPane.CLOSED_OPTION);
				
			}
			else if(idpwequals==1) {
				JOptionPane.showMessageDialog(null, "로그인 성공!","MEMBER LOGIN",JOptionPane.CLOSED_OPTION);
				setVisible(false); //로그인 메인 화면 끄기				
			}
			return idpwequals;
		}
		
	}
	
	 class MyPanel extends JPanel{
		 public void paint (Graphics g) {
			 g.drawImage(img,0,0,Main.WIDTH_SIZE, Main.HEIGHT_SIZE,this);
		 }
	 }
	 
	 public void buttonSet() {

		 memRb.setContentAreaFilled(false);
		 adminRb.setContentAreaFilled(false);
		 loginBtn.setContentAreaFilled(false);
		 joinBtn.setContentAreaFilled(false);

	     memRb.setFocusPainted(false);
	     adminRb.setFocusPainted(false);
	     loginBtn.setFocusPainted(false);
		 joinBtn.setFocusPainted(false);
	   }
}

/////////////////////////// 멤버로그인, 관리자 로그인 선택 안할시 오류 메세지 출력 필요해보임 ///////////////////////////////////////////////////////////////////////