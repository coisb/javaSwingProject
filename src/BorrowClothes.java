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
// �Ѻ� �뿩ȭ��.
public class BorrowClothes{

	public JPanel centerPane; // �������̾ƿ� ���Ϳ� ���� �г� (���⿡ ��ư,���̺�,�ؽ�Ʈ�ʵ� ���� ��...)
	//���� �̸� ����.
	JLabel hanbokName, priceTitle, price, borrowDate, size, picture, clothcountlbl;
	public static HintTextField bDFd, clothamount; //�̿���,���� �ʵ�
	JComboBox<String> sizeCom;	
	JRadioButton card,bankbook;
	ButtonGroup bg;
	JButton borrowBtn, cancelBtn, CalendarBtn;
	Font ft,ft1,ft2;
	
	static String choiceHanName=""; //������ �Ѻ���
	static int choiceHanPrice; //������ �Ѻ��� ����

	
	////
	int x = 800;  int xr=900;  int y = 50; //�� x,y��ǥ ����

	//static���� ������ ��
	static String sizeChoice="", choiceNum=""; //�뿩������ ������,����
	// �̿���: bDFd, �뿩��������: choiceNum, �뿩������: sizeChoice

	////////////��ǰ���� ������ ���� (����, �̸�, ����
	
	public BorrowClothes() {} //
	
	public BorrowClothes(int han_num) {
		
		centerPane = new JPanel();
		centerPane.setBackground(new Color(255,255,255));
		centerPane.setLayout(null);
		//  ft: �Ѻ� �̸� ������ ���� 				ft1: ���̺� �۾� ����
		ft = new Font("���ʷҵ���",Font.BOLD,30);	ft1 = new Font("���ʷҵ���",Font.PLAIN,15); 
		ft2 = new Font("���ʷҵ���",Font.BOLD,15);

		// han_num�̿��ؼ� ��ǰ ���� �޾ƿ��� �޼ҵ�
		getProductInfo(han_num);
		
		//int leftHanbok = pdD.leftHanbok(han_num);
		
		priceTitle = new JLabel("����"); priceTitle.setBounds(815,y+80,200,60); priceTitle.setFont(ft1);
		
		borrowDate = new JLabel("�̿��� [�ʼ�]", JLabel.RIGHT); borrowDate.setBounds(x-150,y+160,200,60); borrowDate.setFont(ft1); //�̿��Ϸ��̺�
		size = new JLabel("������", JLabel.RIGHT); size.setBounds(x-10,y+240,60,60); size.setFont(ft1); //������̺�

		bDFd = new HintTextField("����: 20210303"); bDFd.setBounds(xr,210,250,50);//�̿������ ���Զ� (HintTextField Ŭ������ ����) <-> ������Ű��
		bDFd.setEnabled(false);

		CalendarBtn = new JButton("��¥����"); //�޷¹�ư (���߿� �޷��̹��� �ֱ�)
		CalendarBtn.setBounds(xr+260,215,100,30);
		CalendarBtn.setContentAreaFilled(false); CalendarBtn.setFocusPainted(false);
		CalendarBtn.setFont(ft1);

		String[] sizeList = {"S","M","L"};//������
		sizeCom = new JComboBox<String>(sizeList); sizeCom.setBounds(xr, 290, 110, 50);//������ �޺��ڽ�
		sizeCom.setBackground(Color.WHITE);

		clothcountlbl = new JLabel("����",JLabel.RIGHT);	clothcountlbl.setBounds(x-10,y+320,60,60); clothcountlbl.setFont(ft1);
		clothamount = new HintTextField("���ڸ� �Է����ּ���. �ִ� 10�� �Է°���");	clothamount.setBounds(xr,y+320,250,50);

		card = new JRadioButton("�ſ�ī�� ����"); card.setBounds(x+80,450,150,60); card.setFont(ft1); //�ſ�ī����� ����
		card.setContentAreaFilled(false); card.setFocusPainted(false);
		bankbook = new JRadioButton("������ �Ա�"); bankbook.setBounds(x+230, 450, 120, 60); bankbook.setFont(ft1); //�������Ա� ����
		bankbook.setContentAreaFilled(false); bankbook.setFocusPainted(false);
		bg = new ButtonGroup(); // ���� �׷�ȭ
		bg.add(card); bg.add(bankbook);

		borrowBtn = new JButton("�뿩�ϱ�"); borrowBtn.setBounds(xr, 540, 100, 45); borrowBtn.setFont(ft2);//�뿩�ϱ� ��ư
		borrowBtn.setContentAreaFilled(false);borrowBtn.setFocusPainted(false);
		borrowBtn.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Ŀ���� ��ư ���� �ű�� Ŀ���� �չٴ� ������� �ٲ�
		cancelBtn = new JButton("���"); cancelBtn.setBounds(xr+130, 540, 100, 45); cancelBtn.setFont(ft2);//��� ��ư (����)
		cancelBtn.setContentAreaFilled(false); cancelBtn.setFocusPainted(false);
		cancelBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

		
		picture.setOpaque(true); picture.setBackground(new Color(200,200,200));		

		//�����гο� ������Ʈ �߰�
		centerPane.add(hanbokName); centerPane.add(priceTitle); centerPane.add(price); centerPane.add(borrowDate); centerPane.add(size);
		centerPane.add(picture);  centerPane.add(bDFd); centerPane.add(sizeCom);
		centerPane.add(card); centerPane.add(bankbook); centerPane.add(borrowBtn); centerPane.add(cancelBtn);
		centerPane.add(CalendarBtn); centerPane.add(clothcountlbl);	centerPane.add(clothamount);


		//�̺�Ʈ ���
		CalendarBtn.addActionListener(new ClickEvent()); //�޷¹�ư 
		sizeCom.addActionListener(new ClickEvent()); //������ ���� �޺��ڽ�
		card.addActionListener(new ClickEvent());//�ſ�ī�� ���� ������ư
		bankbook.addActionListener(new ClickEvent()); // �������Ա� ������ư
		borrowBtn.addActionListener(new ClickEvent()); // �뿩�ϱ� ��ư
		cancelBtn.addActionListener(new ClickEvent()); // ��ҹ�ư


		//�̹��� �󺧿� �ֱ�, �����ֱ� �����ؾ� ��.

	}
	
	public void getProductInfo(int han_num){
		ProductDAO dao = new ProductDAO();
		ProductVO vo = new ProductVO();
		List<ProductVO> list = dao.getProductInfo(han_num); 
		vo = list.get(0);
		
		hanbokName = new JLabel(vo.getHan_name(), JLabel.RIGHT);	hanbokName.setBounds(x-300,y-30,600,80); hanbokName.setFont(ft); //�Ѻ��̸����̺�				
		price = new JLabel(String.valueOf(vo.getHan_price()), JLabel.RIGHT);	price.setBounds(x+70,y+80,100,60); price.setFont(ft1); //���ݷ��̺�
		
		choiceHanName = vo.getHan_name();
		choiceHanPrice = vo.getHan_price();
		
		Image img = vo.getHan_image();
		ImageIcon icon = new ImageIcon(img);
		picture = new JLabel(icon); picture.setBounds(200, 50, 400, 500); //���� �ʵ�
	}
	
	class ClickEvent implements ActionListener{
		public void actionPerformed(ActionEvent ae) {
			Object obj = ae.getSource();

			String amount="";

			if(obj instanceof JButton) { //��ưŬ�� �� (�޷�,�뿩�ϱ�,��ҹ�ư)
				if(ae.getActionCommand().equals("��¥����")) {
					CalLabel calBtn = new CalLabel();
				}				
				if(ae.getActionCommand().equals("�뿩�ϱ�")) {

					if(bDFd.getText().trim().contains("��")) { //�̿����� ���þ����� �� 
						JOptionPane.showMessageDialog(null, "�̿����� �������ּ���","NOT CHOICE",JOptionPane.CLOSED_OPTION);

					}else if(clothamount.getText().trim().contains("��")) {
						JOptionPane.showMessageDialog(null, "������ �Է����ּ���","NOT CHOICE",JOptionPane.CLOSED_OPTION);
					}else if(OnlyNumberMethod(amount)==1) {
						JOptionPane.showMessageDialog(null, "���ڸ� �Է����ּ���","NOT CHOICE",JOptionPane.CLOSED_OPTION);
					}
					else if(card.getSelectedObjects()==null && bankbook.getSelectedObjects()==null) { //�������� ������ư Ŭ�����ϰ� �뿩�ϱ� ������ ���޽��� �����.
						JOptionPane.showMessageDialog(null, "���������� �������ּ���.","NOT CHOICE",JOptionPane.CLOSED_OPTION);
					}
					//					else {
					//						amount=clothamount.getText().trim();
					//						choiceNum=amount; //static������ ������ ���� ����.
					//						int clothesNum = Integer.parseInt(amount);
					//						//DB���� �ش��ǰ�� ������ �������� �޼ҵ� (han_name�� ���� ������ ����. -> ProductMainŬ�������� ��ǰ���� ���� Ŭ������ �����ؾ���.)
					//						ProductDAO pdD = new ProductDAO();
					//						int leftHanbok = pdD.leftHanbok(han_name);
					//												
					//						//������ ������ �Ѽ������� ���� �� ���ǹ�						
					//						if(clothesNum>leftHanbok) {
					//							JOptionPane.showMessageDialog(null, "�ִ� "+leftHanbok+" ���� �뿩�����մϴ�","AMOUNT ERROR",JOptionPane.CLOSED_OPTION);
					//						}
					else {						
						sizeChoice= sizeCom.getSelectedItem().toString(); //������ ������

						//�̿���, ������, ������ ������ VO�� ����
						if(card.getSelectedObjects()!=null) { //ī����� ����

							//CardPayment Ŭ���� ���� ��.
							NorthFrameMember.centerPanel.setVisible(false);
							NorthFrameMember.centerPanel.removeAll();
							CardPayment cp = new CardPayment();
							NorthFrameMember.centerPanel.add(cp.cardPane);
							NorthFrameMember.centerPanel.setVisible(true);
						}
						else if(bankbook.getSelectedObjects()!=null){ //�������Ա� ����
							//BankbookPayment Ŭ���� ���� ��.
							NorthFrameMember.centerPanel.setVisible(false);
							NorthFrameMember.centerPanel.removeAll();
							BankbookPayment bp = new BankbookPayment();
							NorthFrameMember.centerPanel.add(bp.pane);
							NorthFrameMember.centerPanel.setVisible(true);
						}
					}
				}else if(ae.getActionCommand().equals("���")) {
					//��� ��ư �� ProductMain ȭ������ ��ȯ.
					NorthFrameMember.centerPanel.setVisible(false);
					NorthFrameMember.centerPanel.removeAll();
					ProductMain pm = new ProductMain();
					NorthFrameMember.centerPanel.add(pm.pane);
					NorthFrameMember.centerPanel.setVisible(true);
				}				
			}
		}

	}
	//���� �Է¶��� ���� �ܸ� �Է����� �� ����ó���ϴ� �޼ҵ�
	public int OnlyNumberMethod(String amount) { 
		int check= 0;
		char alpha; 
		int code; 
		for(int i=0; i<amount.length(); i++) { 
			alpha = amount.charAt(i); 
			code = (int)alpha; 
			if(!(code>=48&&code<=57)) { //���ڸ� ����
				check = 1; //�� ���ĺ� �빮��,�ҹ��� �ȵ�
			} 
		}
		return check; 
	}



	//�޷� ����Ŭ���� 
	class CalLabel extends JFrame implements ActionListener {

		Color col = new Color(255,255,255);
		Calendar cal = Calendar.getInstance();
		int yearnow, monthnow, datenow;

		// �ֻ��
		JPanel panel = new JPanel(new BorderLayout());
		JPanel select = new JPanel(new GridLayout(1, 6)); // ��ư ����
		// ����, ������ ��ư
		JButton before = new JButton("��");
		JButton next = new JButton("��");

		// �⵵�� �޷� ���� ��ư
		JComboBox<Integer> yearCb = new JComboBox<Integer>();
		DefaultComboBoxModel<Integer> yearDcb = new DefaultComboBoxModel<Integer>();
		JComboBox<Integer> monthCb = new JComboBox<Integer>();
		DefaultComboBoxModel<Integer> monthDcb = new DefaultComboBoxModel<Integer>();

		// �޺��ڽ� ������ �� �� ���� ��Ÿ���� �ܾ�
		JLabel yearchar = new JLabel("��",JLabel.CENTER);
		JLabel monthchar = new JLabel("��",JLabel.CENTER);

		// �߾�
		JPanel panel2 = new JPanel(new BorderLayout());
		JPanel weekchar = new JPanel(new GridLayout(1, 7)); // ��� ���� ������ ��Ÿ��
		JPanel dayint = new JPanel(new GridLayout(0, 7)); // �ϼ��� ��Ÿ��

		Font fnt = new Font("����", Font.BOLD, 30);
		Font fnt2 = new Font("����",Font.BOLD,17);

		String day[] = { "��", "��", "ȭ", "��", "��", "��", "��" };

		Color backRed = new Color(255,204,204);
		Color backBlue = new Color(000,204,255);
		Color backWhite = new Color(255,255,255);

		JLabel choiceDay = new JLabel("            ",JLabel.CENTER); //��¥�� �����ϸ� ���⿡ ������ ��¥�� ����.	

		public CalLabel() {

			yearnow = cal.get(Calendar.YEAR);
			monthnow = cal.get(Calendar.MONTH) + 1;
			datenow = cal.get(Calendar.DATE);
			dayNum(yearnow, monthnow);

			// JComboBox�� �⵵ �ֱ�
			for (int i = yearnow; i <= yearnow+1; i++) {
				yearDcb.addElement(i);
			}
			yearCb.setModel(yearDcb);
			yearCb.setSelectedItem(yearnow);

			// JComboBox�� �� �ֱ�
			for (int i = 1; i <= 12; i++) {
				monthDcb.addElement(i); 
			}
			monthCb.setModel(monthDcb); 
			monthCb.setSelectedItem(monthnow); 

			// ���� ǥ���ϱ�
			for (int i = 0; i < day.length; i++) {
				JLabel week = new JLabel(day[i], JLabel.CENTER); // �� ���ͷ� �۾� �ֱ�
				// ������ ���� �߰��ϱ�.
				week.setFont(fnt);
				week.setBorder(new LineBorder(Color.LIGHT_GRAY,1));	
				week.setOpaque(true);

				if (i == 0) { // i�� 0�̸� �迭index0 ���� "��"�� ���õǰ� �װ��� ����������
					week.setBackground(backRed);
					week.setForeground(Color.RED);
					week.setFont(fnt);

				} else if (i == 6) { // i�� 6�̸� �迭index6 ���� "��"�� ���õǰ� �װ��� �Ķ�������
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

			setTitle("�޷�");
			select.setBorder(new LineBorder(Color.LIGHT_GRAY,2));
			select.add(before);
			select.add(yearCb);
			select.add(yearchar);
			select.add(monthCb);
			select.add(monthchar);
			select.add(next);
			// ���� �ǳ� �����ӿ� �ֱ�
			panel.add(BorderLayout.NORTH, select);
			panel.add(BorderLayout.SOUTH,choiceDay);
			// �߾� �ǳڿ� ���ϰ� �ϼ��� �ֱ�
			panel2.add(BorderLayout.NORTH, weekchar);
			panel2.add(BorderLayout.CENTER, dayint);
			//�����ӿ� ��,���� �гγֱ�
			add(BorderLayout.NORTH, panel);
			add(BorderLayout.CENTER, panel2);
			// �̺�Ʈ ���
			before.addActionListener(this);
			next.addActionListener(this);
			yearCb.addActionListener(this);
			monthCb.addActionListener(this);

			// ȭ�� ���
			setSize(400, 300);
			setVisible(true);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			setLocationRelativeTo(null);
		}

		// �������̵� action��ư
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
				if (btn.equals(before)) { // ��ü �񱳴� equals ���
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

		// ���� ��¥ 
		public void dayNum(int year, int month) {		
			cal.set(year, month-1, 1); // ��, ��, �� 0�� 1�� 1�Ϸ� ����
			int today = cal.get(Calendar.DAY_OF_WEEK); // ���� �������� ���� 1:��, 7:��
			int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH); // �ش� ���� ������ �� 31,30,29,28

			// ����
			for (int i=1; i<today; i++) {				
				dayint.add(new JLabel("\t"));				
			}
			// ��¥
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
						String str = dayClick.getText();  //�޷¿��� Ŭ���� ���� ���� (01~31)

						String year = ""+yearCb.getSelectedItem(); // �޺��ڽ� �⵵�� String���� ����.
						String month = ""+monthCb.getSelectedItem();// �޺��ڽ� ���� String���� ����.

						if(str.equals(""));
						else if(str.length()==1) str="0"+str; //�տ� 0����.  01,02...09��

						if(month.length()==1) month="0"+month; //�� �տ� 0����. 01,02,..09��

						choiceDay.setText(year+"/"+month+"/"+str);
						String dayChoiceStr = choiceDay.getText().replace("/", "");

						//���ó�¥ ���ϱ�
						Calendar now = Calendar.getInstance();
						SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");
						String fo = format1.format(now.getTime());

						//���ó�¥�� �뿩���� ���ؼ� ���ó�¥���� ������ �����ϸ� �ٽ� ���ÿ䱸
						int today = Integer.parseInt(fo);
						int choice = Integer.parseInt(dayChoiceStr);						

						if(today>choice) {
							JOptionPane.showMessageDialog(null, "���ú��� �������� ������ �� �����ϴ�.","DATE ERROR",JOptionPane.CLOSED_OPTION);
						}else {
							bDFd.setText(dayChoiceStr); //�̿��� �ؽ�Ʈ�ڽ��� �̿��� ����.							
						}				
					}
				});
			}		
		}
  }
}