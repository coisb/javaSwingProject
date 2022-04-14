import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton; 
import javax.swing.JComboBox; 
import javax.swing.JFrame; 
import javax.swing.JLabel; 
import javax.swing.JOptionPane; 
import javax.swing.JPanel; 
import javax.swing.JPasswordField; 
import javax.swing.JTextField; 


public class JoinMember extends JFrame implements ActionListener {
	JPanel pane = new JPanel();
	Font fnt = new Font("���ʷҵ���",Font.PLAIN,14); 
    Font fnt1 = new Font("���ʷҵ���",Font.PLAIN,12); 
	Font fnt2 = new Font("����ü",Font.PLAIN,14);
	Font titleFnt = new Font("���ʷҵ���",Font.BOLD,32);
	JLabel titleLbl = new JLabel("ȸ �� �� ��"); 
	JLabel idLbl = new JLabel("���̵�"); 
	JTextField idField = new JTextField(30); 
	JButton duplicateBtn = new JButton("�ߺ� Ȯ��"); 
	JLabel pwdLbl = new JLabel("��й�ȣ"); 
    JLabel inpwdLbl = new JLabel("6~20�ڸ� ���� ����,���� ����");
	JPasswordField pwdField = new JPasswordField(30); 
	JLabel pwdcheckLbl = new JLabel("��й�ȣ Ȯ��"); 
	JPasswordField pwdcheckField = new JPasswordField(30); 
	JLabel nameLbl = new JLabel("�̸�"); 
	JTextField nameField = new JTextField(30);
	JLabel birthLbl = new JLabel("�������"); 
	JTextField birthField = new JTextField(30); 
	JLabel inbirthLbl = new JLabel("ex.19810101");
	JLabel genderLbl = new JLabel("����"); 
	String gender[] = {"F","M"}; 
	JLabel ingender = new JLabel("F :����  M :����");
	DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(gender); 
	JComboBox<String> genderCombo = new JComboBox<String>(model); 
	JLabel telLbl = new JLabel("����ó"); 
	JTextField telField = new JTextField(30); 
	JLabel intel = new JLabel("ex.010-0000-0000");
	JLabel emailLbl = new JLabel("�̸���"); 
	JTextField emailField = new JTextField(30);

	JButton signUpBtn = new JButton("�����ϱ�"); 
	JButton cancelBtn = new JButton("���"); 


	public JoinMember() { 
		pane.setBackground(new Color(255,255,255));
		add(pane);
		pane.setLayout(null); 
		buttonSet();

		int x = 400;//���� 300
		int x1 = 510; 
		int x2 = 515;

		pane.add(titleLbl).setBounds(540,100,200,30); //Ÿ��Ʋ 'ȸ������'
		titleLbl.setFont(titleFnt); 

		pane.add(idLbl).setBounds(x,200,100,30); //���̵�
		pane.add(idField).setBounds(x1,200,250,30); 
		pane.add(duplicateBtn).setBounds(770,200,110,30); //�ߺ�Ȯ�� ��ư
		idLbl.setFont(fnt); 
		idField.setFont(fnt); 
		duplicateBtn.setFont(fnt); 
		duplicateBtn.setBackground(new Color(213,213,213)); 
		duplicateBtn.setForeground(Color.black); 

		pane.add(pwdLbl).setBounds(x,250,100,30); //��й�ȣ
		pane.add(pwdField).setBounds(x1,250,250,30); 
		pwdLbl.setFont(fnt); 
		pwdField.setFont(fnt2); 

        pane.add(inpwdLbl).setBounds(770,250,300,30); //��й�ȣ ���� �˷��ִ� ��
		inpwdLbl.setFont(fnt1); 

		pane.add(pwdcheckLbl).setBounds(x,300,100,30); //��й�ȣ ��Ȯ��
		pane.add(pwdcheckField).setBounds(x1, 300, 250, 30); 
		pwdcheckLbl.setFont(fnt); 
		pwdcheckField.setFont(fnt2); 

		pane.add(nameLbl).setBounds(x,350,100,30);  //�̸�
		pane.add(nameField).setBounds(x1,350,250,30); 
		nameLbl.setFont(fnt); 
		nameField.setFont(fnt); 

		pane.add(birthLbl).setBounds(x,400,100,30); //�������
		pane.add(inbirthLbl).setBounds(770,400,250,30);
		pane.add(birthField).setBounds(x1,400,250,30);
		birthLbl.setFont(fnt); 
		birthField.setFont(fnt); 
        inbirthLbl.setFont(fnt1);

		pane.add(genderLbl).setBounds(x,450,100,30); //���� ����
		pane.add(genderCombo).setBounds(x1,450,250,30); 
		pane.add(ingender).setBounds(770,450,250,30); 
                        ingender.setFont(fnt1);
		genderLbl.setFont(fnt); 
		genderCombo.setFont(fnt); 
		genderCombo.setBackground(Color.white);

		pane.add(telLbl).setBounds(x,500,100,30);  //����ó
		pane.add(intel).setBounds(770,500,250,30);
		pane.add(telField).setBounds(x1,500,250,30); 
		telLbl.setFont(fnt); 
		telField.setFont(fnt); 
        intel.setFont(fnt1);

		pane.add(emailLbl).setBounds(x,550,100,30);  //�̸���
		pane.add(emailField).setBounds(x1,550,250,30); 
		emailLbl.setFont(fnt); 
		emailField.setFont(fnt); 

		pane.add(signUpBtn).setBounds(500,620,100,30); //�����ϱ� ��ư
		pane.add(cancelBtn).setBounds(630,620,100,30); //��� ��ư
		signUpBtn.setFont(fnt); 
		signUpBtn.setBackground(new Color(213,213,213)); 
		signUpBtn.setForeground(Color.black); 
		signUpBtn.setEnabled(true); 
		cancelBtn.setFont(fnt); 
		cancelBtn.setBackground(new Color(213,213,213));
		cancelBtn.setForeground(Color.black); 

		setSize(1300, 800);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);


		//�̺�Ʈ ���
		duplicateBtn.addActionListener(this);
		signUpBtn.addActionListener(this);
		cancelBtn.addActionListener(this);

	}

	public void actionPerformed(ActionEvent ae) { 
		Object obj = ae.getSource(); 
		if(obj instanceof JButton) { 
			String btn = ae.getActionCommand(); 
			if(btn.equals("�����ϱ�")) { //�����ϱ� ��ư�� ������ �Ʒ������� ������Ű�� vo�� ȸ������ ��ϵ�

				String id = idField.getText(); 
				String password = pwdField.getText(); 
				String pwdCheck = pwdcheckField.getText(); 

				if(id.equals("")) { 
					JOptionPane.showMessageDialog(this, "���̵� �Է����ּ���."); } 
				else if(password.equals("")) { 
					JOptionPane.showMessageDialog(this, "��й�ȣ�� �Է����ּ���."); } 
				else if(password.length()<6 || password.length()>20) {
					JOptionPane.showMessageDialog(this, "6~20�ڸ� ���� ����,Ư������ !@# ���� ���� �մϴ�."); } 
				else if(pwdCheck.equals("")) { 
					JOptionPane.showMessageDialog(this, "��й�ȣ�� ���Է����ּ���."); } 
				else if(!password.equals(pwdCheck)) { 
					JOptionPane.showMessageDialog(this, "��й�ȣ�� ��ġ���� �ʽ��ϴ�."); } 
				else if(checkPWDMethod(password)==1) { 
					JOptionPane.showMessageDialog(this, "��й�ȣ Ư�����ڴ� !@#�� ���� ���� �մϴ�"); } //!#@�� ����� ���԰���
				else if(nameField.getText().equals("")) { 
					JOptionPane.showMessageDialog(this, "�̸��� �Է����ּ���."); } 
				else if(birthField.getText().equals("")) { 
					JOptionPane.showMessageDialog(this, "������� 8�ڸ��� �Է����ּ���."); } 
				else if(telField.getText().equals("")) { 
					JOptionPane.showMessageDialog(this, "����ó�� �Է����ּ���."); } 
				else if(telCheck(telField.getText())==0) { 
					JOptionPane.showMessageDialog(this, "����ó�� ���ڷ� �Է����ּ���."); } 
				else if(telCheck(telField.getText())==2) { 
					JOptionPane.showMessageDialog(this, "����ó ������ �߸��Ǿ����ϴ�."); } 
				else if(birthCheck(birthField.getText())==0) { 
					JOptionPane.showMessageDialog(this, "��������� 8�ڸ� ���ڷ� �Է����ּ���."); } 
				else if(birthCheck(birthField.getText())==2) { 
					JOptionPane.showMessageDialog(this, "������� ������ �߸��Ǿ����ϴ�."); } 
				else if(emailField.getText().equals("")){ 
					JOptionPane.showMessageDialog(this, "�̸����� �Է����ּ���."); } 
				else if(emailCheck(emailField.getText())==1){ 
					JOptionPane.showMessageDialog(this, "�̸��� ������ �߸��Ǿ����ϴ�."); } 
				else if(emailCheck(emailField.getText())==2){ 
					JOptionPane.showMessageDialog(this, "�̸����� �ҹ��ڸ� �Է� �����մϴ�."); } 
				else { 

					//���� �����͸� vo�� ����
					MemberVO vo = new MemberVO();
					vo.setMem_id(idField.getText());
					vo.setMem_pw(pwdField.getText());
					vo.setMem_name(nameField.getText());
					vo.setMem_birth(birthField.getText());
					vo.setMem_gender(genderCombo.getSelectedItem().toString());
					vo.setMem_tel(telField.getText());
					vo.setMem_email(emailField.getText());

					MemberDAO dao = new MemberDAO();
					int cnt = dao.SignUpInsert(vo);

					if(cnt>0) {//ȸ���߰�
						JOptionPane.showMessageDialog(this, "ȸ�������� �Ϸ�Ǿ����ϴ�.");
						dispose();
						new Login(); 
					}else {//�߰�����
						JOptionPane.showMessageDialog(this, "ȸ������ �����Ͽ����ϴ�.");
					}
				} 
			}else if(btn.equals("���")){ //��ҹ�ư�� ������ �ٽ� ����ȭ������ ���ư�
				dispose();
				new Login(); 
			}
			else if(btn.equals("�ߺ� Ȯ��")) { 
				String idSearch = idField.getText(); 
				//		  System.out.println(idSearch.length()); 
				if(idSearch.equals("")) { 
					JOptionPane.showMessageDialog(this, "���̵� �Է����ּ���."); //id Ư������ ���� Ȯ��
				}else if(idSearch.length() < 6 || idSearch.length() > 20) { 
					JOptionPane.showMessageDialog(this, "6~20�ڸ� ������,���ڷ� �Է� ���� �մϴ�.");
				}else if(checkIDMethod(idSearch)==1){ 
					JOptionPane.showMessageDialog(this, "Ư�����ڸ� ������ ������,���ڸ� �Է� �����մϴ�.");  
				}else if(checkIDMethod(idSearch)==2){ 
					JOptionPane.showMessageDialog(this, "���̵�� �ҹ��ڸ� �Է� �����մϴ�.");  
				}else{ 
					MemberDAO dao = new MemberDAO();
					List<MemberVO> result = dao.getidCheck(idSearch); 

					if(result.size()==0) { //db���� Ȯ���ߴµ� �Է��� ���̵� ������ ��밡�� ���̵�.
						JOptionPane.showMessageDialog(this, "��� ������ ���̵� �Դϴ�"); 
						signUpBtn.setEnabled(true); //�����ϱ� ��ư�� Ȱ��ȭ�ǰ�
						idField.setEnabled(false); //���̵� �Է�ĭ�� �ٽ� ���Է� �Ұ����ϵ��� ��Ȱ��ȭ
					}else { 
						JOptionPane.showMessageDialog(this, "��ϵǾ� �ִ� ���̵� �Դϴ�"); 
					} 
				} 
			}
		}
	}

	public int birthCheck(String birth) {
		int check = 0;
		char alpha; 
		int code; 
		for(int i=0; i<birth.length(); i++) { 
			alpha = birth.charAt(i); 
			code = (int)alpha;
			if(code>=48 && code<=59){
				check = 1; 
			}
		}
		if(birth.length() != 8) {
			check = 0;
		}
		return check;
	}

	public int telCheck(String tel) {
		int check = 0;
		char alpha; 
		int code; 
		for(int i=0; i<tel.length(); i++) { 
			alpha = tel.charAt(i); 
			code = (int)alpha;
			if(code==77 || (code>=48 && code<=59)){
				check = 1; 
			}
		}
		if(tel.length() > 14 || tel.length() < 10) {
			check = 2;
		}
		return check;
	}

	public int checkIDMethod(String id) { 
		int check= 0; 
		char alpha; 
		int code; 
		for(int i=0; i<id.length(); i++) { 
			alpha = id.charAt(i); 
			code = (int)alpha;                      //������ 63
			if(code>=0 && code<=47 || code>=58 && code<=64 || code>=91 && code <=96 || code>=123 && code <= 127){ //�����ڵ�� Ư�����ڸ� ��Ÿ��. �� Ư�����ڵ��� ���Ե��� ��� �ȵȴٰ� �޼��� ����
				check = 1; 
			}else if(code>=65 && code<=90) {
				check = 2;
			}
		} 
		return check; 
	}

	public int checkPWDMethod(String pwd) { 
		int check= 0;
		char alpha; 
		int code; 
		for(int i=0; i<pwd.length(); i++) { 
			alpha = pwd.charAt(i); 
			code = (int)alpha; 
			if(code>=0 && code<=32 || code>=36 && code<=47 || code>=58 && code<=63 || code>=91 && code <=96 || code>=123 && code <= 127) { 
				check = 1; //�� Ư�������� !#@�� ������ Ư�����ڴ� ��й�ȣ�� ���� �� ����.
			} 
		}
		return check; 
	}

	public int emailCheck(String email) { 
		int emailCheck = 0; 
		char alpha;
		int code;
		// email�� @ �� �ִ°�? -1�� ���Դٶ�� ���� �̸��� �ȿ� @�� ���ٴ� ��.       email�� .�� �ùٸ��� �ֳ� ?    email�� Ư�����ڰ� �ֳ�?
		for(int i = 0; i < email.length(); i++) {
			alpha = email.charAt(i); 
			code = (int)alpha; 
			if(code!=64 || (code > 122 && code < 97)) { 
				emailCheck = 1; //�� Ư�������� !#@�� ������ Ư�����ڴ� ��й�ȣ�� ���� �� ����.
			}
		}
		if(email.indexOf("@")== -1 || period(email)==true || specialCharacter(email)==0) { 
			emailCheck = 1; 
		}// 0�̸� �̸��� üũ��� �̻� ����, 1�̸� �̻� �ִ� 
		return emailCheck;   
	}

	//�̸����ּҾȿ� .�� �ִ��� ������ �˻�
	public Boolean period(String email) { 

		String spl[] = email.split("@");//split�� @ �������� �̸����ּҸ� �յڷ� ������

		// spl[0] = ���̵� �κ�, spl[1] = ������ �κ�
		int num = spl[1].lastIndexOf(".") - spl[1].indexOf("."); // last�� index�� �����̸� �˻� 

		boolean clear = false; 

		// "@"�� �պκп� "." �ִ��� Ȯ��
		if(spl[0].indexOf(".")==-1); 
		else clear=true; 

		// "@"�� �� ������ �κ��� "." ���� ������ 4ĭ�̻� �������� �̸����� �ƴ϶�� �Ǵ�. 
		if(num <4); 
		else clear = true; 

		// �̸��Ͽ� .�� �߸��Ǿ��� ���, true�� ��������
		return clear; 

	}
	//�̸��� �ּҾȿ� Ư�����ڰ� ���ԵǾ����� Ȯ��
	public int specialCharacter(String email) { 
		String text[] = { "#", "!","$","%","^","&","*","(",")","-", "_","+","=",",","[","]","{","}",":",";","'","?","<",">"
		};  
		int result = 0; 
		for(int i=0; i<text.length; i++) { 
			if(email.indexOf(text[i]) == -1) { 
				result = 1; //�̸����ּ� �ȿ� @ �� . �� ������ Ư�����ڰ� ���ٸ� 1�� ��ȯ
			}else{ 
				break; // @ �� . �� ������ Ư�����ڰ� �ִٸ� ���߰� result���� �־���� 0�� ��ȯ
			} 
		} 
		// 0�� ��ȯ�ϸ� �̻��� Ư�����ڰ� ���ԵǾ��ٴ� �� �׷��� "�̸��� ������ �߸��Ǿ����ϴ�." �޼����� ���� �ȴ�
		return result; 
	}

	public void buttonSet() {
		//	  duplicateBtn.setBorderPainted(false);
		//	  signUpBtn.setBorderPainted(false);
		//	  cancelBtn.setBorderPainted(false);

		duplicateBtn.setContentAreaFilled(false);
		signUpBtn.setContentAreaFilled(false);
		cancelBtn.setContentAreaFilled(false);

		duplicateBtn.setFocusPainted(false);
		signUpBtn.setFocusPainted(false);
		cancelBtn.setFocusPainted(false);

	}

}


