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
	
	Font fnt = new Font("���ʷҵ���", Font.PLAIN, 14);
	Font titleFnt = new Font("���ʷҵ���", Font.BOLD, 32);
	JLabel titleLbl = new JLabel("�ſ�ī�� ����");

	// ����ǥ��
	JLabel priceLbl = new JLabel("����");
	JTextField priceField = new JTextField(30);

	// ī�� ����
	JLabel choiceCardLbl = new JLabel("ī�弱��");
	String card[] = { "�Ｚī��", "����ī��", "�츮ī��", "����ī��", "BCī��", "�Ե�ī��", "KB����ī��", "NH����ī��", "�ϳ�ī��" };
	DefaultComboBoxModel<String> cardModel = new DefaultComboBoxModel<String>(card);
	JComboBox<String> cardCombo = new JComboBox<String>(cardModel);

	// �Һΰ��� ����
	JLabel monthLbl = new JLabel("�Һΰ���");
	String month[] = { "�Ͻú�", "2����", "3����", "4����", "5����", "6����" };
	DefaultComboBoxModel<String> monthModel = new DefaultComboBoxModel<String>(month);
	JComboBox<String> monthCombo = new JComboBox<String>(monthModel);

	// ī���ȣ �Է�
	JLabel cardNumLbl = new JLabel("ī���ȣ");
	JTextField cardNumField1 = new JTextField(12);
	JTextField cardNumField2 = new JTextField(12);
	JTextField cardNumField3 = new JTextField(12);
	JTextField cardNumField4 = new JTextField(12);

	// ī�� ��ȿ�Ⱓ �Է�
	JLabel expiryDateLbl = new JLabel("ī�� ��ȿ�Ⱓ");
	JTextField expiryDateField = new JTextField(30);
	JLabel monthYearLbl = new JLabel("(��/��)");

	// CVC 3�ڸ� �Է�
	JLabel cvcLbl = new JLabel("CVC 3�ڸ�");
	JTextField cvcField = new JTextField(10);

	JButton payBtn = new JButton("�����ϱ�");
	JButton cancelBtn = new JButton("���");

	public CardPayment() {
		cardPane = new JPanel();
		cardPane.setBackground(new Color(255,255,255));
		cardPane.setLayout(null);
		int x = 400;// ���� 300
		int x1 = 510;
		int x2 = 515;
		// ���� 550,200
		
		priceField.setText(String.valueOf(BorrowClothes.choiceHanPrice));
		
		
		titleLbl.setFont(titleFnt);
		// y��ǥ 70�� ����

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
		
		cardPane.add(titleLbl).setBounds(520, 80, 250, 30); // Ÿ��Ʋ '�ſ�ī�� ����'
		
		cardPane.add(priceLbl).setBounds(x, 170, 100, 30); // ����
		cardPane.add(priceField).setBounds(x1, 170, 250, 30);
		
		cardPane.add(choiceCardLbl).setBounds(x, 240, 100, 30); // ī�� ����
		cardPane.add(cardCombo).setBounds(x1, 240, 250, 30);
		
		cardPane.add(monthLbl).setBounds(x, 310, 100, 30); // �Һΰ��� ����
		cardPane.add(monthCombo).setBounds(x1, 310, 250, 30);
		
		cardPane.add(cardNumLbl).setBounds(x, 380, 100, 30); // ī���ȣ �Է�
		cardPane.add(cardNumField1).setBounds(x1, 380, 50, 30); // x1:510 x 60�� ����
		cardPane.add(cardNumField2).setBounds(570, 380, 50, 30);
		cardPane.add(cardNumField3).setBounds(630, 380, 50, 30);
		cardPane.add(cardNumField4).setBounds(690, 380, 50, 30);
		
		cardPane.add(expiryDateLbl).setBounds(x, 450, 100, 30); // ī�� ��ȿ�Ⱓ �Է�
		cardPane.add(expiryDateField).setBounds(x1, 450, 70, 30);
		cardPane.add(monthYearLbl).setBounds(590, 450, 100, 30);
		
		cardPane.add(cvcLbl).setBounds(x, 520, 100, 30); // CVC 3�ڸ� �Է�
		cardPane.add(cvcField).setBounds(x1, 520, 50, 30);
		
		payBtn.setContentAreaFilled(false);
		cardPane.add(payBtn).setBounds(500, 590, 100, 30); // �����ϱ� ��ư
		cancelBtn.setContentAreaFilled(false);
		cardPane.add(cancelBtn).setBounds(630, 590, 100, 30); // ��� ��ư
		
		payBtn.addActionListener(new CardEvent());
		cancelBtn.addActionListener(new CardEvent());
	}
	
	class CardEvent implements ActionListener{
		public void actionPerformed(ActionEvent ae) {
			if(ae.getActionCommand().equals("�����ϱ�")) {
				
				//ī���ȣ �Է� �ʵ�			
				String cardfield1 = cardNumField1.getText().trim();
				String cardfield2 = cardNumField2.getText().trim();
				String cardfield3 = cardNumField3.getText().trim();
				String cardfield4 = cardNumField4.getText().trim();
				// ��ȿ�Ⱓ,CVC �Է� �ʵ�
				String expriyDate = expiryDateField.getText().trim(); //ī�� ��ȿ�Ⱓ
				String cvc = cvcField.getText().trim(); // CVC ��ȣ
				 
				if(cardfield1.equals("")||cardfield2.equals("")||cardfield3.equals("")||cardfield4.equals("")) {
					JOptionPane.showMessageDialog(null, "ī���ȣ�� �Է����ּ���.","CARD NUM INPUT",JOptionPane.CLOSED_OPTION);
				}
				else if(cardfield1.length()>4||cardfield2.length()>4||cardfield3.length()>4||cardfield4.length()>4) {
					JOptionPane.showMessageDialog(null, "ī���ȣ�� 4�ڸ����� �Է°����մϴ�.","CARD LENGTH ERROR",JOptionPane.CLOSED_OPTION);
				}
				else if(OnlyNumberMethod(cardfield1)==1||OnlyNumberMethod(cardfield2)==1
						||OnlyNumberMethod(cardfield3)==1||OnlyNumberMethod(cardfield4)==1){
					JOptionPane.showMessageDialog(null, "ī���ȣ�� ���ڸ� �Է°����մϴ�","CARD ONLY NUMBER",JOptionPane.CLOSED_OPTION);
				}else if(!(expriyDate.length()==4)) {
					JOptionPane.showMessageDialog(null, "��ȿ�Ⱓ�� 4�ڸ����� �Է°����մϴ�","CARD LENGTH ERROR",JOptionPane.CLOSED_OPTION);
				}else if(!(cvc.length()==3)) {
					JOptionPane.showMessageDialog(null, "CVC��ȣ�� 3�ڸ����� �Է°����մϴ�","CARD LENGTH ERROR",JOptionPane.CLOSED_OPTION);
				}else if(OnlyNumberMethod(expriyDate)==1) {
					JOptionPane.showMessageDialog(null, "��ȿ�Ⱓ�� ���ڸ� �Է°����մϴ�","DATE ONLY NUMBER",JOptionPane.CLOSED_OPTION);
				}else if(OnlyNumberMethod(cvc)==1) {
					JOptionPane.showMessageDialog(null, "CVC��ȣ�� ���ڸ� �Է°����մϴ�","CVC ONLY NUMBER",JOptionPane.CLOSED_OPTION);
				}else { //ī����� �� �ʿ���(ī���ȣ,CVC��ȣ..) �Է� ������ �����ϰ� DB�� ����Ǵ� �κ�
					
					int result = rentSuccess();
					
					if(result>0) {
						JOptionPane.showMessageDialog(null, "ī�� ������ �����Ͽ����ϴ�.","�뿩 ����",JOptionPane.CLOSED_OPTION);
						NorthFrameMember.centerPanel.setVisible(false);
						NorthFrameMember.centerPanel.removeAll();
						ProductMain pm = new ProductMain();
						NorthFrameMember.centerPanel.add(pm.pane);
						NorthFrameMember.centerPanel.setVisible(true);
					}
				}
			}
			else if(ae.getActionCommand().equals("���")) {
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
		String choiceBorrowDate = borrowDate.getText().trim(); //�̿��� �ʵ忡 �ִ� ��¥��
		
		//���ڿ� �⵵,��,�� �� �и�
		String yearStr = choiceBorrowDate.substring(0,4);
		String monthStr = choiceBorrowDate.substring(4,6);
		String dayStr = choiceBorrowDate.substring(6);
		
		// �и��� �⵵,��,���� int�� ����ȯ					
		int year = Integer.parseInt(yearStr);
		int month = Integer.parseInt(monthStr);
		int day = Integer.parseInt(dayStr);
		
		//������ �뿩�̿��Ͽ� +7�Ϸ� ��¥����					
		Calendar now = Calendar.getInstance();
		now.set(year, month-1,day+6); //�ݳ������� 1���� �߰�
		SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");
		String rentReturn = format1.format(now.getTime()); //�ݳ�������					
		
		String amountStr = BorrowClothes.clothamount.getText().trim();//���� �뿩 ����
		int rentAmount = Integer.parseInt(amountStr); //�뿩���� int�� ����ȯ
		
		String sizeRent = BorrowClothes.sizeChoice.trim(); //������ ������
		
		String hanName = BorrowClothes.choiceHanName; //�Ѻ���
		int hanPrice = BorrowClothes.choiceHanPrice; //�Ѻ� ����
		
		ProductDAO pdao = new ProductDAO();
		int hanNum = pdao.getHanNum(hanName, sizeRent); //�Ѻ���ȣ
		
		RentVO rv = new RentVO();
		rv.setHan_num(hanNum);
		rv.setMem_num(Login.loginNum);
		rv.setRent_start(choiceBorrowDate);
		rv.setRent_return(rentReturn);
		rv.setRent_status("Y");  //�뿩���� Y:�뿩�� N:�뿩���ϴ���
		rv.setRent_sum(rentAmount); //�뿩����
		
		RentDAO rD = new RentDAO();					
		int result = rD.rentInsert(rv);
		return result;
	}
	
	
	//ī���ȣ �Է¶��� ���� �ܸ� �Է����� �� ����ó���ϴ� �޼ҵ�
	public int OnlyNumberMethod(String cardNum) { 
        int check= 0;
        char alpha; 
        int code; 
        for(int i=0; i<cardNum.length(); i++) { 
           alpha = cardNum.charAt(i); 
           code = (int)alpha; 
           if(!(code>=48&&code<=57)) { //���ڸ� ����
              check = 1; //�� ���ĺ� �빮��,�ҹ��� �ȵ�
              } 
           }
          return check; 
        }
	
}
