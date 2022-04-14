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

	JButton name1 = new JButton("한복이름1");
	JButton name2 = new JButton("한복이름2");
	JButton name3 = new JButton("한복이름3");
	JButton name4 = new JButton("한복이름4");
	JButton price1 = new JButton("가격1");
	JButton price2 = new JButton("가격2");
	JButton price3 = new JButton("가격3");
	JButton price4 = new JButton("가격4");
	JButton prevBtn = new JButton("이전");
	JButton nextBtn = new JButton("다음");
	
	Font fnt = new Font("함초롬돋움",Font.BOLD,14);

	JButton jb1,jb2,jb3,jb4; //한복사진이 보이는 버튼

	BufferedImage bi; //이미지불러오기
	int page=1; // 사진 4장씩 1페이지~....

	// 클릭한 제품 han_num 저장하는 변수
	int[] selectedNum = new int[4];
	
	public ProductMain() {
		pane.setBackground(new Color(255,255,255));
		paneSet();
		paneAdd();//신경쓰지말자
		btnSet();//신경쓰지말자

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
		}else if(obj == prevBtn) { //이전버튼
			if(page==1) {
				JOptionPane.showMessageDialog(null, "첫페이지 입니다","First Page",JOptionPane.CLOSED_OPTION);
			}else {
				page--;
				previewPutImage();
			}
		}else if(obj == nextBtn) { //다음버튼
		
			//0802 10:00
			ProductDAO pdao = new ProductDAO();
			int hanTotal = pdao.getTotalHanbok()/12; //3장의 사진동일(S,M,L) * 4장에 한페이지
			int hanChildTotal = pdao.getTotalHanbokAboutAge(1100,1400)/12; //아동 페이지
			int hanFemaleTotal = pdao.getTotalHanbokAboutAge(1400, 1700)/12; //성인여성 페이지
			int hanMaleTotal = pdao.getTotalHanbokAboutAge(1700, 2000)/12; //성인남성 페이지
			
			if(hanTotal<page) {
				JOptionPane.showMessageDialog(null, "마지막 페이지입니다.","Last Page",JOptionPane.CLOSED_OPTION);
			}
			//아동,여성,남성 클릭 시 페이지 전환은 아직 구현 못함(08/02 10:15)
			else {
				page++;
				previewPutImage();
			}
		}
	}
	
	public void previewPutImage() { //이전, 다음버튼 클릭시 화면전환
		pane.setLayout(null);
		pane.setVisible(false);
		pane.removeAll();
		ProductDAO imgO = new ProductDAO();
		List<ProductVO> list = new ArrayList<ProductVO>();
		list = imgO.getDBImageAll(page); //list에 제품번호,제품이미지,제품명,가격 들어가있음.


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

	public void paneSet() { //처음 화면에 사진 출력.
		pane.setLayout(null);
		pane.setVisible(false);
		pane.removeAll();
		ProductDAO imgO = new ProductDAO();
		List<ProductVO> list = new ArrayList<ProductVO>();
		list = imgO.getDBImageAll(page); //list에 제품번호,제품이미지,제품명,가격 들어가있음.


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
