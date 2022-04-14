import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField; 

public class ChangeInfor extends JPanel implements ActionListener{
	JPanel pane = new JPanel();
	Font fnt = new Font("���ʷҵ���",Font.PLAIN,14); 
	Font fnt1 = new Font("���ʷҵ���",Font.BOLD,14);
	Font fnt2 = new Font("����ü",Font.PLAIN,14); 
	Font titleFnt = new Font("�޸ո���T",Font.BOLD,32); 
	JLabel titleLbl = new JLabel("�� �� �� ��"); 	  
	JLabel mem_num_Lbl = new JLabel("ȸ����ȣ"); 
	JTextField mem_num_Field = new JTextField(30); 
	JLabel idLbl = new JLabel("���̵�"); 
	JTextField idField = new JTextField(30); 
	JLabel pwdLbl = new JLabel("��й�ȣ"); 
	JPasswordField pwdField = new JPasswordField(30); 
	JLabel pwdcheckLbl = new JLabel("��й�ȣ Ȯ��"); 
	JPasswordField pwdcheckField = new JPasswordField(30); 
	JLabel nameLbl = new JLabel("�̸�"); 
	JTextField nameField = new JTextField(30);
	JLabel birthLbl = new JLabel("�������"); 
	JTextField birthField = new JTextField(30); 
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

	JButton changeBtn = new JButton("�����ϱ�"); 
	JButton cancelBtn = new JButton("���"); 
	String mem_num, mem_name, mem_gender, mem_tel, mem_birth, mem_email;

	public ChangeInfor() { 
		pane.setBackground(new Color(255,255,255));
		pane.setLayout(null); 
		buttonSet();

		MemberDAO dao = new MemberDAO();
		MemberVO vo = new MemberVO();
		List<MemberVO> list = dao.getInfo(Login.mem_id);
		vo = list.get(0);
		mem_num = String.valueOf(vo.getMem_num());
		mem_name = vo.getMem_name();
		mem_gender = vo.getMem_gender();
		mem_tel = vo.getMem_tel();
		mem_birth = vo.getMem_birth();
		mem_email = vo.getMem_email();

		mem_num_Field.setText(mem_num);
		idField.setText(Login.mem_id);
		genderCombo.setSelectedItem(mem_gender);
		nameField.setText(mem_name);
		telField.setText(mem_tel);
		birthField.setText(mem_birth);
		emailField.setText(mem_email);

		changeBtn.addActionListener(this);//�����ϱ�
		cancelBtn.addActionListener(this);//���

	}  

	public void actionPerformed(ActionEvent ae) { 
		Object obj = ae.getSource(); 
		if(obj instanceof JButton) { 
			String btn = ae.getActionCommand(); 
			if(btn.equals("�����ϱ�")) { //�����ϱ� ��ư�� ������ �Ʒ������� ������Ű�� vo�� ȸ������ ��ϵ�

				String password = pwdField.getText(); 
				String pwdCheck = pwdcheckField.getText(); 

				if(password.equals("")) { 
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
				else if(telField.getText().equals("")) { 
					JOptionPane.showMessageDialog(this, "����ó�� �Է����ּ���."); } 
				else if(emailField.getText().equals("")){ 
					JOptionPane.showMessageDialog(this, "�̸����� �Է����ּ���."); } 
				else if(emailCheck(emailField.getText())==1){ 
					JOptionPane.showMessageDialog(this, "�̸��� ������ �߸��Ǿ����ϴ�."); } 
				else {  
					memberUpdate();
					NorthFrameMember.centerPanel.setVisible(false);
					NorthFrameMember.centerPanel.removeAll();
					MyPage mp = new MyPage();
					NorthFrameMember.centerPanel.add(mp.pane);
					NorthFrameMember.centerPanel.setVisible(true);
				}  
			}else if(btn.equals("���")){
				NorthFrameMember.centerPanel.setVisible(false);
				NorthFrameMember.centerPanel.removeAll();
				MyPage mp = new MyPage();
				NorthFrameMember.centerPanel.add(mp.pane);
				NorthFrameMember.centerPanel.setVisible(true);
			}
		}
	} 

	public void memberUpdate() {
		MemberVO vo = new MemberVO();

		vo.setMem_num(Integer.parseInt(mem_num_Field.getText()));
		vo.setMem_pw(pwdField.getText());
		vo.setMem_name(nameField.getText());
		vo.setMem_tel(telField.getText());
		vo.setMem_email(emailField.getText());

		MemberDAO dao = new MemberDAO();
		int cnt = dao.SignUpUpdate(vo);

		if(cnt>0) {
			JOptionPane.showMessageDialog(this, "���������� �Ϸ�Ǿ����ϴ�.");
			new NorthFrameMember();
		}else {
			JOptionPane.showMessageDialog(this, "���������� �����Ͽ����ϴ�.");
		}
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
		// email�� @ �� �ִ°�? -1�� ���Դٶ�� ���� �̸��� �ȿ� @�� ���ٴ� ��.       email�� .�� �ùٸ��� �ֳ� ?    email�� Ư�����ڰ� �ֳ�? 
		if(email.indexOf("@")== -1 || period(email)==true || specialCharacter(email)==0) { 
			emailCheck = 1; 
		} // 0�̸� �̸��� üũ��� �̻� ����, 1�̸� �̻� �ִ� 
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
		//	  signUpBtn.setBorderPainted(false);
		//	  cancelBtn.setBorderPainted(false);

		int x = 400;
		int x1 = 510; 
		int x2 = 515;

		pane.add(titleLbl).setBounds(550,70,200,30); //Ÿ��Ʋ '��������'
		titleLbl.setFont(titleFnt); 

		pane.add(mem_num_Lbl).setBounds(x,140,100,30); //ȸ����ȣ
		pane.add(mem_num_Field).setBounds(x1,140,250,30); 
		mem_num_Lbl.setFont(fnt); 
		mem_num_Field.setFont(fnt); 
		mem_num_Field.setEnabled(false);


		pane.add(idLbl).setBounds(x,190,100,30); //���̵�
		pane.add(idField).setBounds(x1,190,250,30); 
		idLbl.setFont(fnt); 
		idField.setFont(fnt); 
		idField.setEnabled(false); 


		pane.add(pwdLbl).setBounds(x,240,100,30); //��й�ȣ
		pane.add(pwdField).setBounds(x1,240,250,30); 
		pwdLbl.setFont(fnt); 
		pwdField.setFont(fnt2); 

		pane.add(pwdcheckLbl).setBounds(x,290,100,30); //��й�ȣ ��Ȯ��
		pane.add(pwdcheckField).setBounds(x1, 290, 250, 30); 
		pwdcheckLbl.setFont(fnt); 
		pwdcheckField.setFont(fnt2); 

		pane.add(nameLbl).setBounds(x,340,100,30);  //�̸�
		pane.add(nameField).setBounds(x1,340,250,30); 
		nameLbl.setFont(fnt); 
		nameField.setFont(fnt); 

		pane.add(birthLbl).setBounds(x,390,100,30); //�������
		pane.add(birthField).setBounds(x1,390,250,30);
		birthLbl.setFont(fnt); 
		birthField.setFont(fnt); 
		birthField.setEnabled(false); 

		pane.add(genderLbl).setBounds(x,440,100,30); //���� ����
		pane.add(genderCombo).setBounds(x1,440,250,30); 
		//add(ingender).setBounds(770,450,250,30); 
		genderLbl.setFont(fnt); 
		genderCombo.setFont(fnt); 
		genderCombo.setBackground(Color.white);
		genderCombo.setEnabled(false); 

		pane.add(telLbl).setBounds(x,490,100,30);  //����ó
		pane.add(intel).setBounds(770,490,250,30);
		pane.add(telField).setBounds(x1,490,250,30); 
		telLbl.setFont(fnt); 
		telField.setFont(fnt); 

		pane.add(emailLbl).setBounds(x,540,100,30);  //�̸���
		pane.add(emailField).setBounds(x1,540,250,30); 
		emailLbl.setFont(fnt); 
		emailField.setFont(fnt); 

		pane.add(changeBtn).setBounds(510,600,100,30); //�����ϱ� ��ư
		pane.add(cancelBtn).setBounds(640,600,100,30); //��� ��ư
		changeBtn.setFont(fnt1); 
		changeBtn.setBackground(new Color(213,213,213)); 
		changeBtn.setForeground(Color.black); 
		changeBtn.setEnabled(true); 
		cancelBtn.setFont(fnt1); 
		cancelBtn.setBackground(new Color(213,213,213));
		cancelBtn.setForeground(Color.black); 


		changeBtn.setContentAreaFilled(false);
		cancelBtn.setContentAreaFilled(false);

		changeBtn.setFocusPainted(false);
		cancelBtn.setFocusPainted(false);

	}
}

