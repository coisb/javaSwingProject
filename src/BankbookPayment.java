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
	
	Font fnt = new Font("���ʷҵ���",Font.PLAIN,15); 
	Font fnt2 = new Font("���ʷҵ���",Font.PLAIN,17); 
	Font titleFnt = new Font("���ʷҵ���",Font.BOLD,32); 
	
	JPanel pane = new JPanel();
	JLabel title = new JLabel("������ �Ա�");
	JLabel account = new JLabel("�Աݰ���");
	JLabel accountNum = new JLabel("�������� / 564-1043939-19038(������:��Ʈ�Ѻ�)");
	JLabel price = new JLabel("�Աݾ�");
	JLabel priceHanbok = new JLabel("�Ѻ�����ǥ��");
	JLabel payUntilDate = new JLabel(" ");
	JLabel payUntil = new JLabel("   23�� 59�б��� ���Աݽ� �ڵ���ҵ˴ϴ�.");
	JLabel imgLbl = new JLabel();
	ImageIcon img = new ImageIcon("img/exclamationmark.jpg");
	JButton okBtn = new JButton("Ȯ��");
	JButton cancelBtn = new JButton("���");
	
//	////////////////DB Ȯ�ο� ������ ������////////////////////////////

	public BankbookPayment() {
		pane.setBackground(new Color(255,255,255));
		pane.setLayout(null);
		buttonSet();
		
		priceHanbok.setText(String.valueOf(BorrowClothes.choiceHanPrice));
		
		Calendar cal = Calendar.getInstance();
		String format = "yyyy�� MM�� dd��";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		cal.add(cal.DATE, +1); //��¥�� �Ϸ� ���Ѵ�.
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
		
		
		okBtn.setBounds(500,550,100,30); //Ȯ�� ��ư
		okBtn.setFont(fnt);
		
		cancelBtn.setBounds(630,550,100,30); //��� ��ư
		cancelBtn.setFont(fnt);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		okBtn.addActionListener(this);//Ȯ��
 	    cancelBtn.addActionListener(this);//���

	}
	
	public void actionPerformed(ActionEvent ae) { 
		  
		Object obj = ae.getSource(); 
		
		if(obj instanceof JButton) { 
			  
			String btn = ae.getActionCommand(); 
			
			if(btn.equals("Ȯ��")) { 
				rentInsert();  
				NorthFrameMember.centerPanel.setVisible(false);
				NorthFrameMember.centerPanel.removeAll();
				ProductMain pm = new ProductMain();
				NorthFrameMember.centerPanel.add(pm.pane);
				NorthFrameMember.centerPanel.setVisible(true);
			}else if(btn.equals("���")){
				NorthFrameMember.centerPanel.setVisible(false);
				NorthFrameMember.centerPanel.removeAll();
				ProductMain pm = new ProductMain();
				NorthFrameMember.centerPanel.add(pm.pane);
				NorthFrameMember.centerPanel.setVisible(true);
			}//��� ��ư ������ �α��� �� ����ȭ������ ���ư���
				     
		}
	 }
	/////////////////////////������ ��///////////////////////////
	public void rentInsert() {
			CardPayment cp = new CardPayment();
			int cnt = cp.rentSuccess();
			
			if(cnt>0) {
				JOptionPane.showMessageDialog(this, "�������Ա��� �Ϸ�Ǿ����ϴ�.");
				new NorthFrameMember();
			}else {
				JOptionPane.showMessageDialog(this, "�������Աݿ� �����Ͽ����ϴ�.");
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
