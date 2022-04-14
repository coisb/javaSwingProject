/////////////////////////// ����α���, ������ �α��� ���� ���ҽ� ���� �޼��� ��� �ʿ��غ��� ///////////////////////////////////////////////////////////////////////

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
	                //"Han Sans,�����"
	Font titleFnt = new Font("CentSchbook BT", 1, 50);
	Font loginFnt = new Font("CentSchbook BT", 0, 30);
	
	Font fnt = new Font("���ʷҵ���",Font.PLAIN,13); 
	Font fnt2 = new Font("���ʷҵ���",Font.BOLD,14); 
	
	JPanel loginPane = new JPanel();
	JLabel titleLbl = new JLabel("Bit hanbok");
	JLabel loginLbl = new JLabel("LOGIN");
	JLabel id = new JLabel("ID");
	JLabel pw = new JLabel("PW");
	JTextField idTf = new JTextField(20);
	JPasswordField pwTf = new JPasswordField(20);
	JRadioButton memRb = new JRadioButton("ȸ���α���");
	JRadioButton adminRb = new JRadioButton("�����ڷα���");
	JButton loginBtn = new JButton("�α���");
	JButton joinBtn = new JButton("ȸ������");
	ButtonGroup bg = new ButtonGroup();
	
	
	String master_id = "apple";
	String master_pw = "1111";
	
	//�α��� �� �����..
	static String mem_id; // mem_id ȸ�����̵�
	static int loginNum; //mem_num ȸ����ȣ
	
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
		
		pwTf.setEchoChar('*'); //pw�� �Է��� �� pw�ʵ忡 **���� ����.
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
		
		loginBtn.addActionListener(new MainEvent()); //�α��� ��ư �̺�Ʈ ���
		joinBtn.addActionListener(new MainEvent()); // ȸ������ ��ư �̺�Ʈ ���
		
	}
	class MainEvent implements ActionListener{ //��ư �̺�Ʈ ->����Ŭ������ ó��
		public void actionPerformed(ActionEvent ae) {
			Object event = ae.getSource();
			
			String searchId = idTf.getText(); // idTextField�� ����ڰ� �Է��� ���ڿ�.
			
			mem_id= searchId;
			
			String searchPw = new String(pwTf.getPassword()); //��й�ȣ �Է��� ���� str ������ ����.
			if(event instanceof JButton) { //Ŭ���� ���� JButton�� ���
								
				if(ae.getActionCommand().equals("�α���")) { //�α��� ��ư Ŭ����
					//id,pw,�α��ζ�����ư ���þ����� �� �����϶�� �����޼��� �����.
					if(searchId.trim()==null ||searchId.trim().equals("")) { //ID�� �Է¾����� ��
						JOptionPane.showMessageDialog(null, "ID�� �Է����ּ���.","INPUT ID",JOptionPane.CLOSED_OPTION);
					}else if(searchPw.trim()==null || searchPw.trim().equals("")) {// PW�� �Է¾����� ��
						JOptionPane.showMessageDialog(null, "PW�� �Է����ּ���.","INPUT PW",JOptionPane.CLOSED_OPTION);
					}else if(memRb.getSelectedObjects()==null && adminRb.getSelectedObjects()==null) { //ȸ�� �Ǵ� ������ ��ư�� ���þ����� ��
						JOptionPane.showMessageDialog(null, "ȸ�� �Ǵ� �����ڸ� �������ּ���.","Click CheckBox",JOptionPane.CLOSED_OPTION);
					}else if(adminRb.getSelectedObjects()!=null){ //������ �α��� ��
						if(searchId.equals(master_id)&&searchPw.equals(master_pw)) { //������ �α��� ����
							JOptionPane.showMessageDialog(null, "������ �α��� ����!","MASTER LOGIN",JOptionPane.CLOSED_OPTION);
							//������ �������� �̵�..
							setVisible(false);
							NorthFrameAdmin nfm = new NorthFrameAdmin();
							MemberList ml = new MemberList(); 
							nfm.start(ml.centerPane);
							
						}else { //������ �α��� ����
							JOptionPane.showMessageDialog(null, "ID�� PW�� ��ġ���� �ʽ��ϴ�.","MASTER LOGIN ERROR",JOptionPane.CLOSED_OPTION);
						}
					}else { //ȸ���α��� ��
						int check = idpwSearch(searchId,searchPw);
						if(check==1) {
							//�α��� �� member�� ������ȣ�� ����.
							MemberDAO mD = new MemberDAO();
							loginNum = mD.getMemNum(mem_id);
							
							//ProductMain ����ϴ� �κ�.
							NorthFrameMember nfm = new NorthFrameMember();
							ProductMain pm = new ProductMain();
							nfm.start(pm.pane);
						}
						
					}
				}else if(ae.getActionCommand().equals("ȸ������")) {
					setVisible(false);
					JoinMember jm = new JoinMember();
					
				}				
			}			
		}
		 //�α��� �� id,pw�� ��ġ�ϴ� �� Ȯ���ϴ� �޼ҵ�.
		public int idpwSearch(String searchId, String searchPw) { 
			MemberDAO DAO = new MemberDAO();
			int idpwequals = DAO.getIDPW(searchId, searchPw); //idpwequals -> 0�̸� �α��ν���, 1�̸� �α��� ����
			if(idpwequals==0) {
				JOptionPane.showMessageDialog(null, "ID�� PW�� ��ġ���� �ʽ��ϴ�.","ID&PW ERROR",JOptionPane.CLOSED_OPTION);
				
			}
			else if(idpwequals==1) {
				JOptionPane.showMessageDialog(null, "�α��� ����!","MEMBER LOGIN",JOptionPane.CLOSED_OPTION);
				setVisible(false); //�α��� ���� ȭ�� ����				
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

/////////////////////////// ����α���, ������ �α��� ���� ���ҽ� ���� �޼��� ��� �ʿ��غ��� ///////////////////////////////////////////////////////////////////////