import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BankbookPayment extends JFrame implements ActionListener{
	
	Font fnt = new Font("함초롬돋움",Font.PLAIN,15); 
	Font fnt2 = new Font("함초롬돋움",Font.PLAIN,17); 
	Font titleFnt = new Font("함초롬돋움",Font.BOLD,32); 
	
	JPanel pane = new JPanel();
	JLabel title = new JLabel("무통장 입금");
	JLabel account = new JLabel("입금계좌");
	JLabel accountNum = new JLabel("신한은행 / 564-1043939-19038(예금주:비트한복)");
	JLabel price = new JLabel("입금액");
	JLabel priceHanbok = new JLabel("한복가격표시");
	JLabel payUntilDate = new JLabel(" ");
	JLabel payUntil = new JLabel("   23시 59분까지 미입금시 자동취소됩니다.");
	JLabel imgLbl = new JLabel();
	ImageIcon img = new ImageIcon("img/exclamationmark.jpg");
	JButton okBtn = new JButton("확인");
	JButton cancelBtn = new JButton("취소");
	
//	////////////////DB 확인용 임의의 데이터////////////////////////////

	public BankbookPayment() {
		pane.setBackground(new Color(255,255,255));
		pane.setLayout(null);
		buttonSet();
		
		priceHanbok.setText(String.valueOf(BorrowClothes.choiceHanPrice));
		
		Calendar cal = Calendar.getInstance();
		String format = "yyyy년 MM월 dd일";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		cal.add(cal.DATE, +1); //날짜를 하루 더한다.
		String date = sdf.format(cal.getTime());
		
		add(pane);
		pane.add(title);
		pane.add(account);
		pane.add(accountNum);
		pane.add(price);
		pane.add(accountNum);
		pane.add(price);
		pane.add(priceHanbok);
		pane.add(payUntilDate);
		pane.add(payUntil);
		pane.add(imgLbl);
		pane.add(okBtn);
		pane.add(cancelBtn);
		
		imgLbl.setIcon(img);
		title.setBounds(550,50, 200, 30);
		title.setFont(titleFnt);
		account.setBounds(400, 150, 100, 30);
		account.setFont(fnt);
		accountNum.setBounds(500, 150, 500, 30);
		accountNum.setFont(fnt);
		price.setBounds(400, 250, 100, 30);
		price.setFont(fnt);
		priceHanbok.setBounds(500, 250, 300, 30);
		priceHanbok.setFont(fnt);

		payUntilDate.setBounds(430, 400, 200, 30);
		payUntilDate.setText(date);
		payUntilDate.setFont(fnt2);
		
		payUntil.setBounds(560, 400, 500, 30);
		payUntil.setFont(fnt2);
		imgLbl.setBounds(400,500,55,55);
		
		
		okBtn.setBounds(500,550,100,30); //확인 버튼
		okBtn.setFont(fnt);
		
		cancelBtn.setBounds(630,550,100,30); //취소 버튼
		cancelBtn.setFont(fnt);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		okBtn.addActionListener(this);//확인
 	    cancelBtn.addActionListener(this);//취소

	}
	
	public void actionPerformed(ActionEvent ae) { 
		  
		Object obj = ae.getSource(); 
		
		if(obj instanceof JButton) { 
			  
			String btn = ae.getActionCommand(); 
			
			if(btn.equals("확인")) { 
				rentInsert();  
				NorthFrameMember.centerPanel.setVisible(false);
				NorthFrameMember.centerPanel.removeAll();
				ProductMain pm = new ProductMain();
				NorthFrameMember.centerPanel.add(pm.pane);
				NorthFrameMember.centerPanel.setVisible(true);
			}else if(btn.equals("취소")){
				NorthFrameMember.centerPanel.setVisible(false);
				NorthFrameMember.centerPanel.removeAll();
				ProductMain pm = new ProductMain();
				NorthFrameMember.centerPanel.add(pm.pane);
				NorthFrameMember.centerPanel.setVisible(true);
			}//취소 버튼 누르면 로그인 후 메인화면으로 돌아간다
				     
		}
	 }
	/////////////////////////수정된 곳///////////////////////////
	public void rentInsert() {
			CardPayment cp = new CardPayment();
			int cnt = cp.rentSuccess();
			
			if(cnt>0) {
				JOptionPane.showMessageDialog(this, "무통장입금이 완료되었습니다.");
				new NorthFrameMember();
			}else {
				JOptionPane.showMessageDialog(this, "무통장입금에 실패하였습니다.");
			}
		}
	 ///////////////////////////////////////////////////////////  
	
	  public void buttonSet() {
	    okBtn.setContentAreaFilled(false);  
	    okBtn.setFocusPainted(false);
	    cancelBtn.setContentAreaFilled(false);  
	    cancelBtn.setFocusPainted(false);
       }
	
}
