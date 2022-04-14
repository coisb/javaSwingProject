import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class CardPayment {
	
	public JPanel cardPane;
	
	Font fnt = new Font("함초롬돋움", Font.PLAIN, 14);
	Font titleFnt = new Font("함초롬돋움", Font.BOLD, 32);
	JLabel titleLbl = new JLabel("신용카드 결제");

	// 가격표시
	JLabel priceLbl = new JLabel("가격");
	JTextField priceField = new JTextField(30);

	// 카드 선택
	JLabel choiceCardLbl = new JLabel("카드선택");
	String card[] = { "삼성카드", "현대카드", "우리카드", "신한카드", "BC카드", "롯데카드", "KB국민카드", "NH농협카드", "하나카드" };
	DefaultComboBoxModel<String> cardModel = new DefaultComboBoxModel<String>(card);
	JComboBox<String> cardCombo = new JComboBox<String>(cardModel);

	// 할부개월 선택
	JLabel monthLbl = new JLabel("할부개월");
	String month[] = { "일시불", "2개월", "3개월", "4개월", "5개월", "6개월" };
	DefaultComboBoxModel<String> monthModel = new DefaultComboBoxModel<String>(month);
	JComboBox<String> monthCombo = new JComboBox<String>(monthModel);

	// 카드번호 입력
	JLabel cardNumLbl = new JLabel("카드번호");
	JTextField cardNumField1 = new JTextField(12);
	JTextField cardNumField2 = new JTextField(12);
	JTextField cardNumField3 = new JTextField(12);
	JTextField cardNumField4 = new JTextField(12);

	// 카드 유효기간 입력
	JLabel expiryDateLbl = new JLabel("카드 유효기간");
	JTextField expiryDateField = new JTextField(30);
	JLabel monthYearLbl = new JLabel("(월/년)");

	// CVC 3자리 입력
	JLabel cvcLbl = new JLabel("CVC 3자리");
	JTextField cvcField = new JTextField(10);

	JButton payBtn = new JButton("결제하기");
	JButton cancelBtn = new JButton("취소");

	public CardPayment() {
		cardPane = new JPanel();
		cardPane.setBackground(new Color(255,255,255));
		cardPane.setLayout(null);
		int x = 400;// 원래 300
		int x1 = 510;
		int x2 = 515;
		// 원래 550,200
		
		priceField.setText(String.valueOf(BorrowClothes.choiceHanPrice));
		
		
		titleLbl.setFont(titleFnt);
		// y좌표 70씩 증가

		priceLbl.setFont(fnt);
		priceField.setFont(fnt);
		priceField.setEnabled(false);

		choiceCardLbl.setFont(fnt);
		cardCombo.setFont(fnt);
		cardCombo.setBackground(Color.white);


		monthLbl.setFont(fnt);
		monthCombo.setFont(fnt);
		monthCombo.setBackground(Color.white);


		cardNumLbl.setFont(fnt);
		cardNumField1.setFont(fnt);
		cardNumField2.setFont(fnt);
		cardNumField3.setFont(fnt);
		cardNumField4.setFont(fnt);


		expiryDateLbl.setFont(fnt);
		expiryDateField.setFont(fnt);
		monthYearLbl.setFont(fnt);

		
		cvcLbl.setFont(fnt);
		cvcField.setFont(fnt);

		
		payBtn.setFont(fnt);
		payBtn.setBackground(new Color(213, 213, 213));
		payBtn.setForeground(Color.black);
		payBtn.setEnabled(true);
		cancelBtn.setFont(fnt);
		cancelBtn.setBackground(new Color(213, 213, 213));
		cancelBtn.setForeground(Color.black);
		
		cardPane.add(titleLbl).setBounds(520, 80, 250, 30); // 타이틀 '신용카드 결제'
		
		cardPane.add(priceLbl).setBounds(x, 170, 100, 30); // 가격
		cardPane.add(priceField).setBounds(x1, 170, 250, 30);
		
		cardPane.add(choiceCardLbl).setBounds(x, 240, 100, 30); // 카드 선택
		cardPane.add(cardCombo).setBounds(x1, 240, 250, 30);
		
		cardPane.add(monthLbl).setBounds(x, 310, 100, 30); // 할부개월 선택
		cardPane.add(monthCombo).setBounds(x1, 310, 250, 30);
		
		cardPane.add(cardNumLbl).setBounds(x, 380, 100, 30); // 카드번호 입력
		cardPane.add(cardNumField1).setBounds(x1, 380, 50, 30); // x1:510 x 60씩 증가
		cardPane.add(cardNumField2).setBounds(570, 380, 50, 30);
		cardPane.add(cardNumField3).setBounds(630, 380, 50, 30);
		cardPane.add(cardNumField4).setBounds(690, 380, 50, 30);
		
		cardPane.add(expiryDateLbl).setBounds(x, 450, 100, 30); // 카드 유효기간 입력
		cardPane.add(expiryDateField).setBounds(x1, 450, 70, 30);
		cardPane.add(monthYearLbl).setBounds(590, 450, 100, 30);
		
		cardPane.add(cvcLbl).setBounds(x, 520, 100, 30); // CVC 3자리 입력
		cardPane.add(cvcField).setBounds(x1, 520, 50, 30);
		
		payBtn.setContentAreaFilled(false);
		cardPane.add(payBtn).setBounds(500, 590, 100, 30); // 결제하기 버튼
		cancelBtn.setContentAreaFilled(false);
		cardPane.add(cancelBtn).setBounds(630, 590, 100, 30); // 취소 버튼
		
		payBtn.addActionListener(new CardEvent());
		cancelBtn.addActionListener(new CardEvent());
	}
	
	class CardEvent implements ActionListener{
		public void actionPerformed(ActionEvent ae) {
			if(ae.getActionCommand().equals("결제하기")) {
				
				//카드번호 입력 필드			
				String cardfield1 = cardNumField1.getText().trim();
				String cardfield2 = cardNumField2.getText().trim();
				String cardfield3 = cardNumField3.getText().trim();
				String cardfield4 = cardNumField4.getText().trim();
				// 유효기간,CVC 입력 필드
				String expriyDate = expiryDateField.getText().trim(); //카드 유효기간
				String cvc = cvcField.getText().trim(); // CVC 번호
				 
				if(cardfield1.equals("")||cardfield2.equals("")||cardfield3.equals("")||cardfield4.equals("")) {
					JOptionPane.showMessageDialog(null, "카드번호를 입력해주세요.","CARD NUM INPUT",JOptionPane.CLOSED_OPTION);
				}
				else if(cardfield1.length()>4||cardfield2.length()>4||cardfield3.length()>4||cardfield4.length()>4) {
					JOptionPane.showMessageDialog(null, "카드번호는 4자리까지 입력가능합니다.","CARD LENGTH ERROR",JOptionPane.CLOSED_OPTION);
				}
				else if(OnlyNumberMethod(cardfield1)==1||OnlyNumberMethod(cardfield2)==1
						||OnlyNumberMethod(cardfield3)==1||OnlyNumberMethod(cardfield4)==1){
					JOptionPane.showMessageDialog(null, "카드번호는 숫자만 입력가능합니다","CARD ONLY NUMBER",JOptionPane.CLOSED_OPTION);
				}else if(!(expriyDate.length()==4)) {
					JOptionPane.showMessageDialog(null, "유효기간은 4자리까지 입력가능합니다","CARD LENGTH ERROR",JOptionPane.CLOSED_OPTION);
				}else if(!(cvc.length()==3)) {
					JOptionPane.showMessageDialog(null, "CVC번호는 3자리까지 입력가능합니다","CARD LENGTH ERROR",JOptionPane.CLOSED_OPTION);
				}else if(OnlyNumberMethod(expriyDate)==1) {
					JOptionPane.showMessageDialog(null, "유효기간은 숫자만 입력가능합니다","DATE ONLY NUMBER",JOptionPane.CLOSED_OPTION);
				}else if(OnlyNumberMethod(cvc)==1) {
					JOptionPane.showMessageDialog(null, "CVC번호는 숫자만 입력가능합니다","CVC ONLY NUMBER",JOptionPane.CLOSED_OPTION);
				}else { //카드결제 시 필요한(카드번호,CVC번호..) 입력 조건을 만족하고 DB에 저장되는 부분
					
					int result = rentSuccess();
					
					if(result>0) {
						JOptionPane.showMessageDialog(null, "카드 결제에 성공하였습니다.","대여 성공",JOptionPane.CLOSED_OPTION);
						NorthFrameMember.centerPanel.setVisible(false);
						NorthFrameMember.centerPanel.removeAll();
						ProductMain pm = new ProductMain();
						NorthFrameMember.centerPanel.add(pm.pane);
						NorthFrameMember.centerPanel.setVisible(true);
					}
				}
			}
			else if(ae.getActionCommand().equals("취소")) {
//				
				NorthFrameMember.centerPanel.setVisible(false);
				NorthFrameMember.centerPanel.removeAll();
				ProductMain pm = new ProductMain();
				NorthFrameMember.centerPanel.add(pm.pane);
				NorthFrameMember.centerPanel.setVisible(true);
			}
		}
	}
	public int rentSuccess() {
		HintTextField borrowDate = BorrowClothes.bDFd;
		String choiceBorrowDate = borrowDate.getText().trim(); //이용일 필드에 있는 날짜값
		
		//문자열 년도,월,일 로 분리
		String yearStr = choiceBorrowDate.substring(0,4);
		String monthStr = choiceBorrowDate.substring(4,6);
		String dayStr = choiceBorrowDate.substring(6);
		
		// 분리한 년도,월,일을 int로 형변환					
		int year = Integer.parseInt(yearStr);
		int month = Integer.parseInt(monthStr);
		int day = Integer.parseInt(dayStr);
		
		//선택한 대여이용일에 +7일로 날짜셋팅					
		Calendar now = Calendar.getInstance();
		now.set(year, month-1,day+6); //반납예정일 1주일 추가
		SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");
		String rentReturn = format1.format(now.getTime()); //반납예정일					
		
		String amountStr = BorrowClothes.clothamount.getText().trim();//고객이 대여 수량
		int rentAmount = Integer.parseInt(amountStr); //대여수량 int로 형변환
		
		String sizeRent = BorrowClothes.sizeChoice.trim(); //빌리는 사이즈
		
		String hanName = BorrowClothes.choiceHanName; //한복명
		int hanPrice = BorrowClothes.choiceHanPrice; //한복 가격
		
		ProductDAO pdao = new ProductDAO();
		int hanNum = pdao.getHanNum(hanName, sizeRent); //한복번호
		
		RentVO rv = new RentVO();
		rv.setHan_num(hanNum);
		rv.setMem_num(Login.loginNum);
		rv.setRent_start(choiceBorrowDate);
		rv.setRent_return(rentReturn);
		rv.setRent_status("Y");  //대여상태 Y:대여중 N:대여안하는중
		rv.setRent_sum(rentAmount); //대여수량
		
		RentDAO rD = new RentDAO();					
		int result = rD.rentInsert(rv);
		return result;
	}
	
	
	//카드번호 입력란에 숫자 외를 입력했을 때 예외처리하는 메소드
	public int OnlyNumberMethod(String cardNum) { 
        int check= 0;
        char alpha; 
        int code; 
        for(int i=0; i<cardNum.length(); i++) { 
           alpha = cardNum.charAt(i); 
           code = (int)alpha; 
           if(!(code>=48&&code<=57)) { //숫자만 가능
              check = 1; //즉 알파벳 대문자,소문자 안됨
              } 
           }
          return check; 
        }
	
}
