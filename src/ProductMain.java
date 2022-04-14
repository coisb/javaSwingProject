import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ProductMain extends JPanel implements ActionListener{
	JPanel pane = new JPanel();

	JButton name1 = new JButton("�Ѻ��̸�1");
	JButton name2 = new JButton("�Ѻ��̸�2");
	JButton name3 = new JButton("�Ѻ��̸�3");
	JButton name4 = new JButton("�Ѻ��̸�4");
	JButton price1 = new JButton("����1");
	JButton price2 = new JButton("����2");
	JButton price3 = new JButton("����3");
	JButton price4 = new JButton("����4");
	JButton prevBtn = new JButton("����");
	JButton nextBtn = new JButton("����");
	
	Font fnt = new Font("���ʷҵ���",Font.BOLD,14);

	JButton jb1,jb2,jb3,jb4; //�Ѻ������� ���̴� ��ư

	BufferedImage bi; //�̹����ҷ�����
	int page=1; // ���� 4�徿 1������~....

	// Ŭ���� ��ǰ han_num �����ϴ� ����
	int[] selectedNum = new int[4];
	
	public ProductMain() {
		pane.setBackground(new Color(255,255,255));
		paneSet();
		paneAdd();//�Ű澲������
		btnSet();//�Ű澲������

		name1.addActionListener(this);
		name2.addActionListener(this);
		name3.addActionListener(this);
		name4.addActionListener(this);
		price1.addActionListener(this);
		price2.addActionListener(this);
		price3.addActionListener(this);
		price4.addActionListener(this);

		prevBtn.addActionListener(this);
		nextBtn.addActionListener(this);


	}

	public void actionPerformed(ActionEvent ae) {

		Object obj = ae.getSource();

		if(obj == jb1 || obj == name1 || obj == price1) {
			
			NorthFrameMember.centerPanel.setVisible(false);
			NorthFrameMember.centerPanel.removeAll();
			BorrowClothes bc = new BorrowClothes(selectedNum[0]);
			NorthFrameMember.centerPanel.add(bc.centerPane);
			NorthFrameMember.centerPanel.setVisible(true);
		}
		else if(obj == jb2 || obj == name2 || obj == price2) {

			NorthFrameMember.centerPanel.setVisible(false);
			NorthFrameMember.centerPanel.removeAll();
			BorrowClothes bc = new BorrowClothes(selectedNum[1]);
			NorthFrameMember.centerPanel.add(bc.centerPane);
			NorthFrameMember.centerPanel.setVisible(true);
		}
		else if(obj == jb3 || obj == name3 || obj == price3) {

			NorthFrameMember.centerPanel.setVisible(false);
			NorthFrameMember.centerPanel.removeAll();
			BorrowClothes bc = new BorrowClothes(selectedNum[2]);
			NorthFrameMember.centerPanel.add(bc.centerPane);
			NorthFrameMember.centerPanel.setVisible(true);
		}
		else if(obj == jb4 || obj == name4 || obj == price4) {

			NorthFrameMember.centerPanel.setVisible(false);
			NorthFrameMember.centerPanel.removeAll();
			BorrowClothes bc = new BorrowClothes(selectedNum[3]);
			NorthFrameMember.centerPanel.add(bc.centerPane);
			NorthFrameMember.centerPanel.setVisible(true);
		}else if(obj == prevBtn) { //������ư
			if(page==1) {
				JOptionPane.showMessageDialog(null, "ù������ �Դϴ�","First Page",JOptionPane.CLOSED_OPTION);
			}else {
				page--;
				previewPutImage();
			}
		}else if(obj == nextBtn) { //������ư
		
			//0802 10:00
			ProductDAO pdao = new ProductDAO();
			int hanTotal = pdao.getTotalHanbok()/12; //3���� ��������(S,M,L) * 4�忡 ��������
			int hanChildTotal = pdao.getTotalHanbokAboutAge(1100,1400)/12; //�Ƶ� ������
			int hanFemaleTotal = pdao.getTotalHanbokAboutAge(1400, 1700)/12; //���ο��� ������
			int hanMaleTotal = pdao.getTotalHanbokAboutAge(1700, 2000)/12; //���γ��� ������
			
			if(hanTotal<page) {
				JOptionPane.showMessageDialog(null, "������ �������Դϴ�.","Last Page",JOptionPane.CLOSED_OPTION);
			}
			//�Ƶ�,����,���� Ŭ�� �� ������ ��ȯ�� ���� ���� ����(08/02 10:15)
			else {
				page++;
				previewPutImage();
			}
		}
	}
	
	public void previewPutImage() { //����, ������ư Ŭ���� ȭ����ȯ
		pane.setLayout(null);
		pane.setVisible(false);
		pane.removeAll();
		ProductDAO imgO = new ProductDAO();
		List<ProductVO> list = new ArrayList<ProductVO>();
		list = imgO.getDBImageAll(page); //list�� ��ǰ��ȣ,��ǰ�̹���,��ǰ��,���� ������.


		int cnt=0;
		int x =85;

		for(int i=0;i<list.size();i++) {
			ProductVO pv = list.get(i);

			selectedNum[i] = pv.getHan_num();
			Image img = pv.getHan_image();
			ImageIcon icon = new ImageIcon(img);
			Image changeImg = img.getScaledInstance(250, 350, Image.SCALE_SMOOTH);
			ImageIcon changeIcon = new ImageIcon(changeImg);

			if(cnt==0) {
				jb1 = new JButton(changeIcon);
				jb1.setBounds(x, 100, 250, 350);

				price1.setText(String.valueOf(pv.getHan_price()));
				name1.setText(pv.getHan_name());

				jb1.addActionListener(this);
				pane.add(jb1);
				x+=285;
				cnt=1;

			}else if(cnt==1) {
				jb2 = new JButton(changeIcon);
				jb2.setBounds(x, 100, 250, 350);

				price2.setText(String.valueOf(pv.getHan_price()));
				name2.setText(pv.getHan_name());

				jb2.addActionListener(this);
				pane.add(jb2);
				x+=285;
				cnt=2;

			}else if(cnt==2) {
				jb3 = new JButton(changeIcon);
				jb3.setBounds(x, 100, 250, 350);

				price3.setText(String.valueOf(pv.getHan_price()));
				name3.setText(pv.getHan_name());
	
				jb3.addActionListener(this);
				pane.add(jb3);
				x+=285;
				cnt=3;

			}else if(cnt==3) {
				jb4 = new JButton(changeIcon);
				jb4.setBounds(x, 100, 250, 350);

				price4.setText(String.valueOf(pv.getHan_price()));
				name4.setText(pv.getHan_name());

				jb4.addActionListener(this);
				pane.add(jb4);				
			}
		}		
		pane.add(name1);
		pane.add(name2);
		pane.add(name3);
		pane.add(name4);
		pane.add(price1);
		pane.add(price2);
		pane.add(price3);
		pane.add(price4);
		pane.add(prevBtn);
		pane.add(nextBtn);	

		pane.setVisible(true);
	}

	public void paneSet() { //ó�� ȭ�鿡 ���� ���.
		pane.setLayout(null);
		pane.setVisible(false);
		pane.removeAll();
		ProductDAO imgO = new ProductDAO();
		List<ProductVO> list = new ArrayList<ProductVO>();
		list = imgO.getDBImageAll(page); //list�� ��ǰ��ȣ,��ǰ�̹���,��ǰ��,���� ������.


		int cnt=0;
		int x =85;

		for(int i=0;i<list.size();i++) {
			ProductVO pv = list.get(i);

			selectedNum[i] = pv.getHan_num();
			Image img = pv.getHan_image();
			ImageIcon icon = new ImageIcon(img);
			Image changeImg = img.getScaledInstance(250, 350, Image.SCALE_SMOOTH);
			ImageIcon changeIcon = new ImageIcon(changeImg);
			

			if(cnt==0) {
				jb1 = new JButton(changeIcon);
				jb1.setBounds(x, 100, 250, 350);

				price1.setText(String.valueOf(pv.getHan_price()));
				name1.setText(pv.getHan_name());

				jb1.addActionListener(this);
				pane.add(jb1);
				x+=285;
				cnt=1;

			}else if(cnt==1) {
				jb2 = new JButton(changeIcon);
				jb2.setBounds(x, 100, 250, 350);

				price2.setText(String.valueOf(pv.getHan_price()));
				name2.setText(pv.getHan_name());

				jb2.addActionListener(this);
				pane.add(jb2);
				x+=285;
				cnt=2;

			}else if(cnt==2) {
				jb3 = new JButton(changeIcon);
				jb3.setBounds(x, 100, 250, 350);

				price3.setText(String.valueOf(pv.getHan_price()));
				name3.setText(pv.getHan_name());

				jb3.addActionListener(this);
				pane.add(jb3);
				x+=285;
				cnt=3;

			}else if(cnt==3) {
				jb4 = new JButton(changeIcon);
				jb4.setBounds(x, 100, 250, 350);

				price4.setText(String.valueOf(pv.getHan_price()));
				name4.setText(pv.getHan_name());

				jb4.addActionListener(this);
				pane.add(jb4);				
			}
		}
		pane.setVisible(true);

	}

	public void paneAdd() {
		pane.add(name1);
		pane.add(name2);
		pane.add(name3);
		pane.add(name4);
		pane.add(price1);
		pane.add(price2);
		pane.add(price3);
		pane.add(price4);
		pane.add(prevBtn);
		pane.add(nextBtn);
		add(pane);		
	}

	public void btnSet() {
		name1.setFont(fnt);
		name1.setBounds(115, 460, 200, 30);
		name2.setFont(fnt);
		name2.setBounds(400, 460, 200, 30);
		name3.setFont(fnt);
		name3.setBounds(685, 460, 200, 30);
		name4.setFont(fnt);
		name4.setBounds(970, 460, 200, 30);
		price1.setFont(fnt);
		price1.setBounds(165, 480, 100, 30);
		price2.setFont(fnt);
		price2.setBounds(450, 480, 100, 30);
		price3.setFont(fnt);
		price3.setBounds(735, 480, 100, 30);
		price4.setFont(fnt);
		price4.setBounds(1020, 480, 100, 30);
		prevBtn.setFont(fnt);
		prevBtn.setBounds(500, 580, 100, 30);
		nextBtn.setFont(fnt);
		nextBtn.setBounds(650, 580, 100, 30);

		prevBtn.setBorderPainted(false);
		nextBtn.setBorderPainted(false);
		prevBtn.setContentAreaFilled(false);
		nextBtn.setContentAreaFilled(false);
		prevBtn.setFocusPainted(false);
		nextBtn.setFocusPainted(false);
		name1.setBorderPainted(false);
		name2.setBorderPainted(false);
		name3.setBorderPainted(false);
		name4.setBorderPainted(false);
		price1.setBorderPainted(false);
		price2.setBorderPainted(false);
		price3.setBorderPainted(false);
		price4.setBorderPainted(false);
		name1.setContentAreaFilled(false);
		name2.setContentAreaFilled(false);
		name3.setContentAreaFilled(false);
		name4.setContentAreaFilled(false);
		price1.setContentAreaFilled(false);
		price2.setContentAreaFilled(false);
		price3.setContentAreaFilled(false);
		price4.setContentAreaFilled(false);
		name1.setFocusPainted(false);
		name2.setFocusPainted(false);
		name3.setFocusPainted(false);
		name4.setFocusPainted(false);
		price1.setFocusPainted(false);
		price2.setFocusPainted(false);
		price3.setFocusPainted(false);
		price4.setFocusPainted(false);
	}

}
