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
	Font fnt = new Font("함초롬돋움",Font.PLAIN,14); 
    Font fnt1 = new Font("함초롬돋움",Font.PLAIN,12); 
	Font fnt2 = new Font("굴림체",Font.PLAIN,14);
	Font titleFnt = new Font("함초롬돋움",Font.BOLD,32);
	JLabel titleLbl = new JLabel("회 원 가 입"); 
	JLabel idLbl = new JLabel("아이디"); 
	JTextField idField = new JTextField(30); 
	JButton duplicateBtn = new JButton("중복 확인"); 
	JLabel pwdLbl = new JLabel("비밀번호"); 
    JLabel inpwdLbl = new JLabel("6~20자리 이하 숫자,영문 가능");
	JPasswordField pwdField = new JPasswordField(30); 
	JLabel pwdcheckLbl = new JLabel("비밀번호 확인"); 
	JPasswordField pwdcheckField = new JPasswordField(30); 
	JLabel nameLbl = new JLabel("이름"); 
	JTextField nameField = new JTextField(30);
	JLabel birthLbl = new JLabel("생년월일"); 
	JTextField birthField = new JTextField(30); 
	JLabel inbirthLbl = new JLabel("ex.19810101");
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

	JButton signUpBtn = new JButton("가입하기"); 
	JButton cancelBtn = new JButton("취소"); 


	public JoinMember() { 
		pane.setBackground(new Color(255,255,255));
		add(pane);
		pane.setLayout(null); 
		buttonSet();

		int x = 400;//원래 300
		int x1 = 510; 
		int x2 = 515;

		pane.add(titleLbl).setBounds(540,100,200,30); //타이틀 '회원가입'
		titleLbl.setFont(titleFnt); 

		pane.add(idLbl).setBounds(x,200,100,30); //아이디
		pane.add(idField).setBounds(x1,200,250,30); 
		pane.add(duplicateBtn).setBounds(770,200,110,30); //중복확인 버튼
		idLbl.setFont(fnt); 
		idField.setFont(fnt); 
		duplicateBtn.setFont(fnt); 
		duplicateBtn.setBackground(new Color(213,213,213)); 
		duplicateBtn.setForeground(Color.black); 

		pane.add(pwdLbl).setBounds(x,250,100,30); //비밀번호
		pane.add(pwdField).setBounds(x1,250,250,30); 
		pwdLbl.setFont(fnt); 
		pwdField.setFont(fnt2); 

        pane.add(inpwdLbl).setBounds(770,250,300,30); //비밀번호 조건 알려주는 라벨
		inpwdLbl.setFont(fnt1); 

		pane.add(pwdcheckLbl).setBounds(x,300,100,30); //비밀번호 재확인
		pane.add(pwdcheckField).setBounds(x1, 300, 250, 30); 
		pwdcheckLbl.setFont(fnt); 
		pwdcheckField.setFont(fnt2); 

		pane.add(nameLbl).setBounds(x,350,100,30);  //이름
		pane.add(nameField).setBounds(x1,350,250,30); 
		nameLbl.setFont(fnt); 
		nameField.setFont(fnt); 

		pane.add(birthLbl).setBounds(x,400,100,30); //생년월일
		pane.add(inbirthLbl).setBounds(770,400,250,30);
		pane.add(birthField).setBounds(x1,400,250,30);
		birthLbl.setFont(fnt); 
		birthField.setFont(fnt); 
        inbirthLbl.setFont(fnt1);

		pane.add(genderLbl).setBounds(x,450,100,30); //성별 선택
		pane.add(genderCombo).setBounds(x1,450,250,30); 
		pane.add(ingender).setBounds(770,450,250,30); 
                        ingender.setFont(fnt1);
		genderLbl.setFont(fnt); 
		genderCombo.setFont(fnt); 
		genderCombo.setBackground(Color.white);

		pane.add(telLbl).setBounds(x,500,100,30);  //연락처
		pane.add(intel).setBounds(770,500,250,30);
		pane.add(telField).setBounds(x1,500,250,30); 
		telLbl.setFont(fnt); 
		telField.setFont(fnt); 
        intel.setFont(fnt1);

		pane.add(emailLbl).setBounds(x,550,100,30);  //이메일
		pane.add(emailField).setBounds(x1,550,250,30); 
		emailLbl.setFont(fnt); 
		emailField.setFont(fnt); 

		pane.add(signUpBtn).setBounds(500,620,100,30); //가입하기 버튼
		pane.add(cancelBtn).setBounds(630,620,100,30); //취소 버튼
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


		//이벤트 등록
		duplicateBtn.addActionListener(this);
		signUpBtn.addActionListener(this);
		cancelBtn.addActionListener(this);

	}

	public void actionPerformed(ActionEvent ae) { 
		Object obj = ae.getSource(); 
		if(obj instanceof JButton) { 
			String btn = ae.getActionCommand(); 
			if(btn.equals("가입하기")) { //가입하기 버튼을 누르고 아래조건을 충족시키면 vo에 회원정보 등록됨

				String id = idField.getText(); 
				String password = pwdField.getText(); 
				String pwdCheck = pwdcheckField.getText(); 

				if(id.equals("")) { 
					JOptionPane.showMessageDialog(this, "아이디를 입력해주세요."); } 
				else if(password.equals("")) { 
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
				else if(birthField.getText().equals("")) { 
					JOptionPane.showMessageDialog(this, "생년월일 8자리를 입력해주세요."); } 
				else if(telField.getText().equals("")) { 
					JOptionPane.showMessageDialog(this, "연락처를 입력해주세요."); } 
				else if(telCheck(telField.getText())==0) { 
					JOptionPane.showMessageDialog(this, "연락처는 숫자로 입력해주세요."); } 
				else if(telCheck(telField.getText())==2) { 
					JOptionPane.showMessageDialog(this, "연락처 형식이 잘못되었습니다."); } 
				else if(birthCheck(birthField.getText())==0) { 
					JOptionPane.showMessageDialog(this, "생년월일은 8자리 숫자로 입력해주세요."); } 
				else if(birthCheck(birthField.getText())==2) { 
					JOptionPane.showMessageDialog(this, "생년월일 형식이 잘못되었습니다."); } 
				else if(emailField.getText().equals("")){ 
					JOptionPane.showMessageDialog(this, "이메일을 입력해주세요."); } 
				else if(emailCheck(emailField.getText())==1){ 
					JOptionPane.showMessageDialog(this, "이메일 형식이 잘못되었습니다."); } 
				else if(emailCheck(emailField.getText())==2){ 
					JOptionPane.showMessageDialog(this, "이메일은 소문자만 입력 가능합니다."); } 
				else { 

					//폼의 데이터를 vo에 대입
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

					if(cnt>0) {//회원추가
						JOptionPane.showMessageDialog(this, "회원가입이 완료되었습니다.");
						dispose();
						new Login(); 
					}else {//추가실패
						JOptionPane.showMessageDialog(this, "회원가입 실패하였습니다.");
					}
				} 
			}else if(btn.equals("취소")){ //취소버튼을 누르면 다시 메인화면으로 돌아감
				dispose();
				new Login(); 
			}
			else if(btn.equals("중복 확인")) { 
				String idSearch = idField.getText(); 
				//		  System.out.println(idSearch.length()); 
				if(idSearch.equals("")) { 
					JOptionPane.showMessageDialog(this, "아이디를 입력해주세요."); //id 특수문자 포함 확인
				}else if(idSearch.length() < 6 || idSearch.length() > 20) { 
					JOptionPane.showMessageDialog(this, "6~20자리 영문자,숫자로 입력 가능 합니다.");
				}else if(checkIDMethod(idSearch)==1){ 
					JOptionPane.showMessageDialog(this, "특수문자를 제외한 영문자,숫자만 입력 가능합니다.");  
				}else if(checkIDMethod(idSearch)==2){ 
					JOptionPane.showMessageDialog(this, "아이디는 소문자만 입력 가능합니다.");  
				}else{ 
					MemberDAO dao = new MemberDAO();
					List<MemberVO> result = dao.getidCheck(idSearch); 

					if(result.size()==0) { //db에서 확인했는데 입력한 아이디가 없으면 사용가능 아이디.
						JOptionPane.showMessageDialog(this, "사용 가능한 아이디 입니다"); 
						signUpBtn.setEnabled(true); //가입하기 버튼이 활성화되고
						idField.setEnabled(false); //아이디 입력칸이 다시 재입력 불가능하도록 비활성화
					}else { 
						JOptionPane.showMessageDialog(this, "등록되어 있는 아이디 입니다"); 
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
			code = (int)alpha;                      //원래는 63
			if(code>=0 && code<=47 || code>=58 && code<=64 || code>=91 && code <=96 || code>=123 && code <= 127){ //유니코드로 특수문자를 나타냄. 저 특수문자들이 포함됐을 경우 안된다고 메세지 띄우기
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
				check = 1; //즉 특수문자중 !#@를 제외한 특수문자는 비밀번호에 쓰일 수 없다.
			} 
		}
		return check; 
	}

	public int emailCheck(String email) { 
		int emailCheck = 0; 
		char alpha;
		int code;
		// email에 @ 가 있는가? -1이 나왔다라는 것은 이메일 안에 @가 없다는 뜻.       email에 .이 올바르게 있나 ?    email에 특수문자가 있나?
		for(int i = 0; i < email.length(); i++) {
			alpha = email.charAt(i); 
			code = (int)alpha; 
			if(code!=64 || (code > 122 && code < 97)) { 
				emailCheck = 1; //즉 특수문자중 !#@를 제외한 특수문자는 비밀번호에 쓰일 수 없다.
			}
		}
		if(email.indexOf("@")== -1 || period(email)==true || specialCharacter(email)==0) { 
			emailCheck = 1; 
		}// 0이면 이메일 체크결과 이상 없다, 1이면 이상 있다 
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


