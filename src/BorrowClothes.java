import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.LineBorder;
// 한복 대여화면.
public class BorrowClothes{

	public JPanel centerPane; // 보더레이아웃 센터에 들어가는 패널 (여기에 버튼,레이블,텍스트필드 등이 들어감...)
	//변수 이름 선언.
	JLabel hanbokName, priceTitle, price, borrowDate, size, picture, clothcountlbl;
	public static HintTextField bDFd, clothamount; //이용일,수량 필드
	JComboBox<String> sizeCom;	
	JRadioButton card,bankbook;
	ButtonGroup bg;
	JButton borrowBtn, cancelBtn, CalendarBtn;
	Font ft,ft1,ft2;
	
	static String choiceHanName=""; //선택한 한복명
	static int choiceHanPrice; //선택한 한복의 가격

	
	////
	int x = 800;  int xr=900;  int y = 50; //라벨 x,y좌표 설정

	//static으로 선언한 것
	static String sizeChoice="", choiceNum=""; //대여선택한 사이즈,수량
	// 이용일: bDFd, 대여수량선택: choiceNum, 대여사이즈: sizeChoice

	////////////상품정보 저장할 변수 (사진, 이름, 가격
	
	public BorrowClothes() {} //
	
	public BorrowClothes(int han_num) {
		
		centerPane = new JPanel();
		centerPane.setBackground(new Color(255,255,255));
		centerPane.setLayout(null);
		//  ft: 한복 이름 사이즈 조절 				ft1: 레이블 글씨 지정
		ft = new Font("함초롬돋움",Font.BOLD,30);	ft1 = new Font("함초롬돋움",Font.PLAIN,15); 
		ft2 = new Font("함초롬돋움",Font.BOLD,15);

		// han_num이용해서 상품 정보 받아오는 메소드
		getProductInfo(han_num);
		
		//int leftHanbok = pdD.leftHanbok(han_num);
		
		priceTitle = new JLabel("가격"); priceTitle.setBounds(815,y+80,200,60); priceTitle.setFont(ft1);
		
		borrowDate = new JLabel("이용일 [필수]", JLabel.RIGHT); borrowDate.setBounds(x-150,y+160,200,60); borrowDate.setFont(ft1); //이용일레이블
		size = new JLabel("사이즈", JLabel.RIGHT); size.setBounds(x-10,y+240,60,60); size.setFont(ft1); //사이즈레이블

		bDFd = new HintTextField("예시: 20210303"); bDFd.setBounds(xr,210,250,50);//이용시작일 기입란 (HintTextField 클래스와 연동) <-> 같은패키지
		bDFd.setEnabled(false);

		CalendarBtn = new JButton("날짜선택"); //달력버튼 (나중에 달력이미지 넣기)
		CalendarBtn.setBounds(xr+260,215,100,30);
		CalendarBtn.setContentAreaFilled(false); CalendarBtn.setFocusPainted(false);
		CalendarBtn.setFont(ft1);

		String[] sizeList = {"S","M","L"};//사이즈
		sizeCom = new JComboBox<String>(sizeList); sizeCom.setBounds(xr, 290, 110, 50);//사이즈 콤보박스
		sizeCom.setBackground(Color.WHITE);

		clothcountlbl = new JLabel("수량",JLabel.RIGHT);	clothcountlbl.setBounds(x-10,y+320,60,60); clothcountlbl.setFont(ft1);
		clothamount = new HintTextField("숫자를 입력해주세요. 최대 10개 입력가능");	clothamount.setBounds(xr,y+320,250,50);

		card = new JRadioButton("신용카드 결제"); card.setBounds(x+80,450,150,60); card.setFont(ft1); //신용카드결제 라디오
		card.setContentAreaFilled(false); card.setFocusPainted(false);
		bankbook = new JRadioButton("무통장 입금"); bankbook.setBounds(x+230, 450, 120, 60); bankbook.setFont(ft1); //무통장입금 라디오
		bankbook.setContentAreaFilled(false); bankbook.setFocusPainted(false);
		bg = new ButtonGroup(); // 라디오 그룹화
		bg.add(card); bg.add(bankbook);

		borrowBtn = new JButton("대여하기"); borrowBtn.setBounds(xr, 540, 100, 45); borrowBtn.setFont(ft2);//대여하기 버튼
		borrowBtn.setContentAreaFilled(false);borrowBtn.setFocusPainted(false);
		borrowBtn.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 커서를 버튼 위로 옮기면 커서가 손바닥 모양으로 바뀜
		cancelBtn = new JButton("취소"); cancelBtn.setBounds(xr+130, 540, 100, 45); cancelBtn.setFont(ft2);//취소 버튼 (이전)
		cancelBtn.setContentAreaFilled(false); cancelBtn.setFocusPainted(false);
		cancelBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

		
		picture.setOpaque(true); picture.setBackground(new Color(200,200,200));		

		//센터패널에 컴포넌트 추가
		centerPane.add(hanbokName); centerPane.add(priceTitle); centerPane.add(price); centerPane.add(borrowDate); centerPane.add(size);
		centerPane.add(picture);  centerPane.add(bDFd); centerPane.add(sizeCom);
		centerPane.add(card); centerPane.add(bankbook); centerPane.add(borrowBtn); centerPane.add(cancelBtn);
		centerPane.add(CalendarBtn); centerPane.add(clothcountlbl);	centerPane.add(clothamount);


		//이벤트 등록
		CalendarBtn.addActionListener(new ClickEvent()); //달력버튼 
		sizeCom.addActionListener(new ClickEvent()); //사이즈 선택 콤보박스
		card.addActionListener(new ClickEvent());//신용카드 결제 라디오버튼
		bankbook.addActionListener(new ClickEvent()); // 무통장입금 라디오버튼
		borrowBtn.addActionListener(new ClickEvent()); // 대여하기 버튼
		cancelBtn.addActionListener(new ClickEvent()); // 취소버튼


		//이미지 라벨에 넣기, 별점넣기 구현해야 함.

	}
	
	public void getProductInfo(int han_num){
		ProductDAO dao = new ProductDAO();
		ProductVO vo = new ProductVO();
		List<ProductVO> list = dao.getProductInfo(han_num); 
		vo = list.get(0);
		
		hanbokName = new JLabel(vo.getHan_name(), JLabel.RIGHT);	hanbokName.setBounds(x-300,y-30,600,80); hanbokName.setFont(ft); //한복이름레이블				
		price = new JLabel(String.valueOf(vo.getHan_price()), JLabel.RIGHT);	price.setBounds(x+70,y+80,100,60); price.setFont(ft1); //가격레이블
		
		choiceHanName = vo.getHan_name();
		choiceHanPrice = vo.getHan_price();
		
		Image img = vo.getHan_image();
		ImageIcon icon = new ImageIcon(img);
		picture = new JLabel(icon); picture.setBounds(200, 50, 400, 500); //사진 필드
	}
	
	class ClickEvent implements ActionListener{
		public void actionPerformed(ActionEvent ae) {
			Object obj = ae.getSource();

			String amount="";

			if(obj instanceof JButton) { //버튼클릭 시 (달력,대여하기,취소버튼)
				if(ae.getActionCommand().equals("날짜선택")) {
					CalLabel calBtn = new CalLabel();
				}				
				if(ae.getActionCommand().equals("대여하기")) {

					if(bDFd.getText().trim().contains("예")) { //이용일을 선택안했을 때 
						JOptionPane.showMessageDialog(null, "이용일을 선택해주세요","NOT CHOICE",JOptionPane.CLOSED_OPTION);

					}else if(clothamount.getText().trim().contains("숫")) {
						JOptionPane.showMessageDialog(null, "수량을 입력해주세요","NOT CHOICE",JOptionPane.CLOSED_OPTION);
					}else if(OnlyNumberMethod(amount)==1) {
						JOptionPane.showMessageDialog(null, "숫자를 입력해주세요","NOT CHOICE",JOptionPane.CLOSED_OPTION);
					}
					else if(card.getSelectedObjects()==null && bankbook.getSelectedObjects()==null) { //결제수단 라디오버튼 클릭안하고 대여하기 누르면 경고메시지 출력함.
						JOptionPane.showMessageDialog(null, "결제수단을 선택해주세요.","NOT CHOICE",JOptionPane.CLOSED_OPTION);
					}
					//					else {
					//						amount=clothamount.getText().trim();
					//						choiceNum=amount; //static변수에 선택한 수량 담음.
					//						int clothesNum = Integer.parseInt(amount);
					//						//DB에서 해당상품의 수량을 가져오는 메소드 (han_name을 통해 수량을 얻음. -> ProductMain클래스에서 제품명을 얻어와 클래스에 저장해야함.)
					//						ProductDAO pdD = new ProductDAO();
					//						int leftHanbok = pdD.leftHanbok(han_name);
					//												
					//						//선택한 수량이 총수량보다 많을 때 조건문						
					//						if(clothesNum>leftHanbok) {
					//							JOptionPane.showMessageDialog(null, "최대 "+leftHanbok+" 벌만 대여가능합니다","AMOUNT ERROR",JOptionPane.CLOSED_OPTION);
					//						}
					else {						
						sizeChoice= sizeCom.getSelectedItem().toString(); //선택한 사이즈

						//이용일, 사이즈, 수량의 정보를 VO에 저장
						if(card.getSelectedObjects()!=null) { //카드결제 선택

							//CardPayment 클래스 들어가는 곳.
							NorthFrameMember.centerPanel.setVisible(false);
							NorthFrameMember.centerPanel.removeAll();
							CardPayment cp = new CardPayment();
							NorthFrameMember.centerPanel.add(cp.cardPane);
							NorthFrameMember.centerPanel.setVisible(true);
						}
						else if(bankbook.getSelectedObjects()!=null){ //무통장입금 선택
							//BankbookPayment 클래스 들어가는 곳.
							NorthFrameMember.centerPanel.setVisible(false);
							NorthFrameMember.centerPanel.removeAll();
							BankbookPayment bp = new BankbookPayment();
							NorthFrameMember.centerPanel.add(bp.pane);
							NorthFrameMember.centerPanel.setVisible(true);
						}
					}
				}else if(ae.getActionCommand().equals("취소")) {
					//취소 버튼 시 ProductMain 화면으로 전환.
					NorthFrameMember.centerPanel.setVisible(false);
					NorthFrameMember.centerPanel.removeAll();
					ProductMain pm = new ProductMain();
					NorthFrameMember.centerPanel.add(pm.pane);
					NorthFrameMember.centerPanel.setVisible(true);
				}				
			}
		}

	}
	//수량 입력란에 숫자 외를 입력했을 때 예외처리하는 메소드
	public int OnlyNumberMethod(String amount) { 
		int check= 0;
		char alpha; 
		int code; 
		for(int i=0; i<amount.length(); i++) { 
			alpha = amount.charAt(i); 
			code = (int)alpha; 
			if(!(code>=48&&code<=57)) { //숫자만 가능
				check = 1; //즉 알파벳 대문자,소문자 안됨
			} 
		}
		return check; 
	}



	//달력 내부클래스 
	class CalLabel extends JFrame implements ActionListener {

		Color col = new Color(255,255,255);
		Calendar cal = Calendar.getInstance();
		int yearnow, monthnow, datenow;

		// 최상단
		JPanel panel = new JPanel(new BorderLayout());
		JPanel select = new JPanel(new GridLayout(1, 6)); // 버튼 선택
		// 이전, 다음달 버튼
		JButton before = new JButton("◀");
		JButton next = new JButton("▶");

		// 년도와 달력 선택 버튼
		JComboBox<Integer> yearCb = new JComboBox<Integer>();
		DefaultComboBoxModel<Integer> yearDcb = new DefaultComboBoxModel<Integer>();
		JComboBox<Integer> monthCb = new JComboBox<Integer>();
		DefaultComboBoxModel<Integer> monthDcb = new DefaultComboBoxModel<Integer>();

		// 콤보박스 우측에 년 과 월을 나타내는 단어
		JLabel yearchar = new JLabel("년",JLabel.CENTER);
		JLabel monthchar = new JLabel("월",JLabel.CENTER);

		// 중앙
		JPanel panel2 = new JPanel(new BorderLayout());
		JPanel weekchar = new JPanel(new GridLayout(1, 7)); // 상단 무슨 요일을 나타냄
		JPanel dayint = new JPanel(new GridLayout(0, 7)); // 일수를 나타냄

		Font fnt = new Font("굴림", Font.BOLD, 30);
		Font fnt2 = new Font("굴림",Font.BOLD,17);

		String day[] = { "일", "월", "화", "수", "목", "금", "토" };

		Color backRed = new Color(255,204,204);
		Color backBlue = new Color(000,204,255);
		Color backWhite = new Color(255,255,255);

		JLabel choiceDay = new JLabel("            ",JLabel.CENTER); //날짜를 선택하면 여기에 선택한 날짜가 보임.	

		public CalLabel() {

			yearnow = cal.get(Calendar.YEAR);
			monthnow = cal.get(Calendar.MONTH) + 1;
			datenow = cal.get(Calendar.DATE);
			dayNum(yearnow, monthnow);

			// JComboBox에 년도 넣기
			for (int i = yearnow; i <= yearnow+1; i++) {
				yearDcb.addElement(i);
			}
			yearCb.setModel(yearDcb);
			yearCb.setSelectedItem(yearnow);

			// JComboBox에 월 넣기
			for (int i = 1; i <= 12; i++) {
				monthDcb.addElement(i); 
			}
			monthCb.setModel(monthDcb); 
			monthCb.setSelectedItem(monthnow); 

			// 요일 표시하기
			for (int i = 0; i < day.length; i++) {
				JLabel week = new JLabel(day[i], JLabel.CENTER); // 라벨 센터로 글씨 넣기
				// 라인을 생성 추가하기.
				week.setFont(fnt);
				week.setBorder(new LineBorder(Color.LIGHT_GRAY,1));	
				week.setOpaque(true);

				if (i == 0) { // i가 0이면 배열index0 값이 "일"이 선택되고 그것을 빨간색으로
					week.setBackground(backRed);
					week.setForeground(Color.RED);
					week.setFont(fnt);

				} else if (i == 6) { // i가 6이면 배열index6 값이 "토"가 선택되고 그것을 파란색으로
					week.setBackground(backBlue);
					week.setForeground(Color.BLUE);
					week.setFont(fnt);				
				}
				else {
					week.setBackground(backWhite);
				}
				weekchar.setBackground(col);
				weekchar.add(week);				
			}
			yearchar.setFont(fnt2);		
			monthchar.setFont(fnt2);		

			setTitle("달력");
			select.setBorder(new LineBorder(Color.LIGHT_GRAY,2));
			select.add(before);
			select.add(yearCb);
			select.add(yearchar);
			select.add(monthCb);
			select.add(monthchar);
			select.add(next);
			// 만든 판넬 프레임에 넣기
			panel.add(BorderLayout.NORTH, select);
			panel.add(BorderLayout.SOUTH,choiceDay);
			// 중앙 판넬에 요일과 일수를 넣기
			panel2.add(BorderLayout.NORTH, weekchar);
			panel2.add(BorderLayout.CENTER, dayint);
			//프레임에 상,센터 패널넣기
			add(BorderLayout.NORTH, panel);
			add(BorderLayout.CENTER, panel2);
			// 이벤트 등록
			before.addActionListener(this);
			next.addActionListener(this);
			yearCb.addActionListener(this);
			monthCb.addActionListener(this);

			// 화면 출력
			setSize(400, 300);
			setVisible(true);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			setLocationRelativeTo(null);
		}

		// 오버라이딩 action버튼
		public void actionPerformed(ActionEvent ae) {
			Object btn = ae.getSource();
			if (btn instanceof JComboBox) {
				dayint.setVisible(false);
				dayint.removeAll();
				dayNum((Integer) yearCb.getSelectedItem(), (Integer) monthCb.getSelectedItem());
				dayint.setVisible(true);
			}
			else if (btn instanceof JButton) {			
				int yearCbClick = (Integer) yearCb.getSelectedItem();
				int monthCbClick = (Integer) monthCb.getSelectedItem();
				if (btn.equals(before)) { // 객체 비교는 equals 사용
					if (monthCbClick == 1) {
						yearCbClick--;
						monthCbClick = 12;
					} else {
						monthCbClick--;
					}
				} else if (btn.equals(next)) {
					if (monthCbClick == 12) {
						yearCbClick++;
						monthCbClick = 1;
					} else {
						monthCbClick++;
					}
				}
				yearCb.setSelectedItem(yearCbClick);
				monthCb.setSelectedItem(monthCbClick);
			}
		}

		// 숫자 날짜 
		public void dayNum(int year, int month) {		
			cal.set(year, month-1, 1); // 년, 월, 일 0년 1월 1일로 셋팅
			int today = cal.get(Calendar.DAY_OF_WEEK); // 오늘 무슨요일 숫자 1:일, 7:토
			int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH); // 해당 월의 마지막 일 31,30,29,28

			// 공백
			for (int i=1; i<today; i++) {				
				dayint.add(new JLabel("\t"));				
			}
			// 날짜
			for (int i=1; i<=lastDay; i++) {
				JLabel jb = new JLabel(String.valueOf(i), JLabel.CENTER);
				jb.setCursor(new Cursor(Cursor.HAND_CURSOR));
				jb.setBorder(new LineBorder(new Color(204,204,204),1));
				jb.setOpaque(true);
				cal.set(year, month-1,i);
				int colorday = cal.get(Calendar.DAY_OF_WEEK);
				if (colorday==1) {
					jb.setForeground(Color.RED);
					jb.setBackground(backRed);
				} else if (colorday==7) {
					jb.setForeground(Color.BLUE);
					jb.setBackground(backBlue);
				}else {
					jb.setBackground(backWhite);
				}
				dayint.add(jb);

				jb.addMouseListener(new MouseAdapter(){
					public void mouseClicked(MouseEvent me) {
						JLabel dayClick = (JLabel)me.getSource();
						String str = dayClick.getText();  //달력에서 클릭한 일을 담음 (01~31)

						String year = ""+yearCb.getSelectedItem(); // 콤보박스 년도를 String으로 담음.
						String month = ""+monthCb.getSelectedItem();// 콤보박스 월을 String으로 담음.

						if(str.equals(""));
						else if(str.length()==1) str="0"+str; //앞에 0붙임.  01,02...09일

						if(month.length()==1) month="0"+month; //월 앞에 0붙임. 01,02,..09월

						choiceDay.setText(year+"/"+month+"/"+str);
						String dayChoiceStr = choiceDay.getText().replace("/", "");

						//오늘날짜 구하기
						Calendar now = Calendar.getInstance();
						SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");
						String fo = format1.format(now.getTime());

						//오늘날짜와 대여일을 비교해서 오늘날짜보다 이전을 선택하면 다시 선택요구
						int today = Integer.parseInt(fo);
						int choice = Integer.parseInt(dayChoiceStr);						

						if(today>choice) {
							JOptionPane.showMessageDialog(null, "오늘보다 이전일을 선택할 수 없습니다.","DATE ERROR",JOptionPane.CLOSED_OPTION);
						}else {
							bDFd.setText(dayChoiceStr); //이용일 텍스트박스에 이용일 대입.							
						}				
					}
				});
			}		
		}
  }
}