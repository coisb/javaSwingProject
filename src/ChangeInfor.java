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
	Font fnt = new Font("함초롬돋움",Font.PLAIN,14); 
	Font fnt1 = new Font("함초롬돋움",Font.BOLD,14);
	Font fnt2 = new Font("굴림체",Font.PLAIN,14); 
	Font titleFnt = new Font("휴먼모음T",Font.BOLD,32); 
	JLabel titleLbl = new JLabel("정 보 수 정"); 	  
	JLabel mem_num_Lbl = new JLabel("회원번호"); 
	JTextField mem_num_Field = new JTextField(30); 
	JLabel idLbl = new JLabel("아이디"); 
	JTextField idField = new JTextField(30); 
	JLabel pwdLbl = new JLabel("비밀번호"); 
	JPasswordField pwdField = new JPasswordField(30); 
	JLabel pwdcheckLbl = new JLabel("비밀번호 확인"); 
	JPasswordField pwdcheckField = new JPasswordField(30); 
	JLabel nameLbl = new JLabel("이름"); 
	JTextField nameField = new JTextField(30);
	JLabel birthLbl = new JLabel("생년월일"); 
	JTextField birthField = new JTextField(30); 
	JLabel genderLbl = new JLabel("성별"); 
	String gender[] = {"F","M"}; 
	JLabel ingender = new JLabel("F :여성  M :남성");
	DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(gender); 
	JComboBox<String> genderCombo = new JComboBox<String>(model); 
	JLabel telLbl = new JLabel("연락처"); 
	JTextField telField = new JTextField(30); 
	JLabel intel = new JLabel("ex.010-0000-0000");
	JLabel emailLbl = new JLabel("이메일"); 
	JTextField emailField = new JTextField(30);

	JButton changeBtn = new JButton("수정하기"); 
	JButton cancelBtn = new JButton("취소"); 
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

		changeBtn.addActionListener(this);//수정하기
		cancelBtn.addActionListener(this);//취소

	}  

	public void actionPerformed(ActionEvent ae) { 
		Object obj = ae.getSource(); 
		if(obj instanceof JButton) { 
			String btn = ae.getActionCommand(); 
			if(btn.equals("수정하기")) { //가입하기 버튼을 누르고 아래조건을 충족시키면 vo에 회원정보 등록됨

				String password = pwdField.getText(); 
				String pwdCheck = pwdcheckField.getText(); 

				if(password.equals("")) { 
					JOptionPane.showMessageDialog(this, "비밀번호를 입력해주세요."); } 
				else if(password.length()<6 || password.length()>20) {
					JOptionPane.showMessageDialog(this, "6~20자리 이하 영문,특수문자 !@# 포함 가능 합니다."); } 
				else if(pwdCheck.equals("")) { 
					JOptionPane.showMessageDialog(this, "비밀번호를 재입력해주세요."); } 
				else if(!password.equals(pwdCheck)) { 
					JOptionPane.showMessageDialog(this, "비밀번호가 일치하지 않습니다."); } 
				else if(checkPWDMethod(password)==1) { 
					JOptionPane.showMessageDialog(this, "비밀번호 특수문자는 !@#만 포함 가능 합니다"); } //!#@만 비번에 포함가능
				else if(nameField.getText().equals("")) { 
					JOptionPane.showMessageDialog(this, "이름을 입력해주세요."); } 
				else if(telField.getText().equals("")) { 
					JOptionPane.showMessageDialog(this, "연락처를 입력해주세요."); } 
				else if(emailField.getText().equals("")){ 
					JOptionPane.showMessageDialog(this, "이메일을 입력해주세요."); } 
				else if(emailCheck(emailField.getText())==1){ 
					JOptionPane.showMessageDialog(this, "이메일 형식이 잘못되었습니다."); } 
				else {  
					memberUpdate();
					NorthFrameMember.centerPanel.setVisible(false);
					NorthFrameMember.centerPanel.removeAll();
					MyPage mp = new MyPage();
					NorthFrameMember.centerPanel.add(mp.pane);
					NorthFrameMember.centerPanel.setVisible(true);
				}  
			}else if(btn.equals("취소")){
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
			JOptionPane.showMessageDialog(this, "정보수정이 완료되었습니다.");
			new NorthFrameMember();
		}else {
			JOptionPane.showMessageDialog(this, "정보수정이 실패하였습니다.");
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
				check = 1; //즉 특수문자중 !#@를 제외한 특수문자는 비밀번호에 쓰일 수 없다.
			} 
		}
		return check; 
	}

	public int emailCheck(String email) { 
		int emailCheck = 0; 
		// email에 @ 가 있는가? -1이 나왔다라는 것은 이메일 안에 @가 없다는 뜻.       email에 .이 올바르게 있나 ?    email에 특수문자가 있나? 
		if(email.indexOf("@")== -1 || period(email)==true || specialCharacter(email)==0) { 
			emailCheck = 1; 
		} // 0이면 이메일 체크결과 이상 없다, 1이면 이상 있다 
		return emailCheck;   
	}

	//이메일주소안에 .이 있는지 없는지 검사
	public Boolean period(String email) { 

		String spl[] = email.split("@");//split은 @ 기준으로 이메일주소를 앞뒤로 나눈다

		// spl[0] = 아이디 부분, spl[1] = 도메인 부분
		int num = spl[1].lastIndexOf(".") - spl[1].indexOf("."); // last와 index의 값차이를 검사 

		boolean clear = false; 

		// "@"의 앞부분에 "." 있는지 확인
		if(spl[0].indexOf(".")==-1); 
		else clear=true; 

		// "@"의 뒷 도메인 부분의 "." 들의 간격이 4칸이상 떨어지면 이메일이 아니라고 판단. 
		if(num <4); 
		else clear = true; 

		// 이메일에 .이 잘못되었을 경우, true를 내보낸다
		return clear; 

	}
	//이메일 주소안에 특수문자가 포함되었는지 확인
	public int specialCharacter(String email) { 
		String text[] = { "#", "!","$","%","^","&","*","(",")","-", "_","+","=",",","[","]","{","}",":",";","'","?","<",">"
		};  
		int result = 0; 
		for(int i=0; i<text.length; i++) { 
			if(email.indexOf(text[i]) == -1) { 
				result = 1; //이메일주소 안에 @ 와 . 을 제외한 특수문자가 없다면 1을 반환
			}else{ 
				break; // @ 와 . 을 제외한 특수문자가 있다면 멈추고 result값에 넣어놨던 0을 반환
			} 
		} 
		// 0을 반환하면 이상한 특수문자가 포함되었다는 뜻 그러면 "이메일 형식이 잘못되었습니다." 메세지를 띄우게 된다
		return result; 
	}

	public void buttonSet() {
		//	  signUpBtn.setBorderPainted(false);
		//	  cancelBtn.setBorderPainted(false);

		int x = 400;
		int x1 = 510; 
		int x2 = 515;

		pane.add(titleLbl).setBounds(550,70,200,30); //타이틀 '정보수정'
		titleLbl.setFont(titleFnt); 

		pane.add(mem_num_Lbl).setBounds(x,140,100,30); //회원번호
		pane.add(mem_num_Field).setBounds(x1,140,250,30); 
		mem_num_Lbl.setFont(fnt); 
		mem_num_Field.setFont(fnt); 
		mem_num_Field.setEnabled(false);


		pane.add(idLbl).setBounds(x,190,100,30); //아이디
		pane.add(idField).setBounds(x1,190,250,30); 
		idLbl.setFont(fnt); 
		idField.setFont(fnt); 
		idField.setEnabled(false); 


		pane.add(pwdLbl).setBounds(x,240,100,30); //비밀번호
		pane.add(pwdField).setBounds(x1,240,250,30); 
		pwdLbl.setFont(fnt); 
		pwdField.setFont(fnt2); 

		pane.add(pwdcheckLbl).setBounds(x,290,100,30); //비밀번호 재확인
		pane.add(pwdcheckField).setBounds(x1, 290, 250, 30); 
		pwdcheckLbl.setFont(fnt); 
		pwdcheckField.setFont(fnt2); 

		pane.add(nameLbl).setBounds(x,340,100,30);  //이름
		pane.add(nameField).setBounds(x1,340,250,30); 
		nameLbl.setFont(fnt); 
		nameField.setFont(fnt); 

		pane.add(birthLbl).setBounds(x,390,100,30); //생년월일
		pane.add(birthField).setBounds(x1,390,250,30);
		birthLbl.setFont(fnt); 
		birthField.setFont(fnt); 
		birthField.setEnabled(false); 

		pane.add(genderLbl).setBounds(x,440,100,30); //성별 선택
		pane.add(genderCombo).setBounds(x1,440,250,30); 
		//add(ingender).setBounds(770,450,250,30); 
		genderLbl.setFont(fnt); 
		genderCombo.setFont(fnt); 
		genderCombo.setBackground(Color.white);
		genderCombo.setEnabled(false); 

		pane.add(telLbl).setBounds(x,490,100,30);  //연락처
		pane.add(intel).setBounds(770,490,250,30);
		pane.add(telField).setBounds(x1,490,250,30); 
		telLbl.setFont(fnt); 
		telField.setFont(fnt); 

		pane.add(emailLbl).setBounds(x,540,100,30);  //이메일
		pane.add(emailField).setBounds(x1,540,250,30); 
		emailLbl.setFont(fnt); 
		emailField.setFont(fnt); 

		pane.add(changeBtn).setBounds(510,600,100,30); //수정하기 버튼
		pane.add(cancelBtn).setBounds(640,600,100,30); //취소 버튼
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

