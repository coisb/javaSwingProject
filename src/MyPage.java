import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class MyPage extends JPanel implements ActionListener {
	JPanel pane = new JPanel();
	JLabel lbl = new JLabel("����������");
	JButton rentListBtn = new JButton("�뿩���");
	JButton infoCorrBtn = new JButton("��������");
	JInternalFrame rentList;
	JButton goBackBtn = new JButton("�ڷΰ���");
	
	Font fnt = new Font("���ʷҵ���",Font.PLAIN,13); 
	Font fnt2 = new Font("���ʷҵ���",Font.BOLD,25); 
	
	public MyPage() {
		pane.setBackground(new Color(255,255,255));
		pane.setLayout(null);
		pane.add(lbl);
		pane.add(rentListBtn);
		pane.add(infoCorrBtn);
		lbl.setFont(fnt2);
		lbl.setBounds(585, 70, 150, 100);
		rentListBtn.setFont(fnt);
		rentListBtn.setBounds(590, 250, 100, 40);
		rentListBtn.setContentAreaFilled(false); rentListBtn.setFocusPainted(false);
		infoCorrBtn.setFont(fnt);
		infoCorrBtn.setBounds(590, 400, 100, 40);
		infoCorrBtn.setContentAreaFilled(false); infoCorrBtn.setFocusPainted(false);
		
		rentListBtn.addActionListener(this);
		infoCorrBtn.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == rentListBtn) {
			NorthFrameMember.centerPanel.setVisible(false);
			NorthFrameMember.centerPanel.removeAll();
			RentalList rl = new RentalList();
			NorthFrameMember.centerPanel.add(rl.pane);
			NorthFrameMember.centerPanel.setVisible(true);
		}else if(obj == infoCorrBtn) {
			NorthFrameMember.centerPanel.setVisible(false);
			NorthFrameMember.centerPanel.removeAll();
			ChangeInfor ci = new ChangeInfor();
			NorthFrameMember.centerPanel.add(ci.pane);
			NorthFrameMember.centerPanel.setVisible(true);
		}
	}
}