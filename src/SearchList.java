import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class SearchList extends JFrame implements ActionListener {
	JPanel pane = new JPanel(new BorderLayout());
	JPanel formPane = new JPanel();
	JPanel centerPane = new JPanel();
	String formLbl[] = {"제품번호","제품명","가격"};
	JPanel formCenter = new JPanel(new GridLayout(3,1));
	JTextField[] formTf = {new JTextField(5), new JTextField(30), new JTextField(10)};
	DefaultTableModel model;
	String title = "제품명/가격";
	JTable ProductList;
	JScrollPane sp;
	JLabel picture;
//	Container c = getContentPane();
	String search;

	public SearchList(String search) {
		this.search = search;
		setButtonTable();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		ProductList.addMouseListener(new TableMouseEventSearch(ProductList));

		
		//memberAllList();
	}

	public void setButtonTable() {
		pane.setBackground(new Color(255,255,255));
		pane.setLayout(null);
		pane.add(centerPane);
		centerPane.setBackground(new Color(255,255,255));
		centerPane.setBounds(600, 80, 650, 500);
		model = new DefaultTableModel(title.split("/"), 0);
		ProductList = new JTable(model);
		sp = new JScrollPane(ProductList);
		centerPane.add(sp);
		memberSearch();
		picture();
	}

	public void picture() {
		ImageIcon icon = new ImageIcon("img/hanbok_pink.jpg");
		ImageIcon icon2 = new ImageIcon("img/hanbok_purple.jpg");
		Image img = icon.getImage();
		Image img2 = icon2.getImage();
		Image changeImg = img.getScaledInstance(450, 563, Image.SCALE_SMOOTH);
		Image changeImg2 = img2.getScaledInstance(450, 563, Image.SCALE_SMOOTH);
		ImageIcon changeIcon = new ImageIcon(changeImg);
		ImageIcon changeIcon2 = new ImageIcon(changeImg2);
		JButton btn = new JButton(changeIcon);
		pane.add(btn).setBounds(120, 30, 450, 563);
		btn.setRolloverIcon(changeIcon2);

	}

	/*
		// 데이터 베이스에서 회원전체 목록(이름순으로) 가져오기 - JTable을 가져온다.
		public void memberAllList() {
			ProductDAO dao = new ProductDAO();
			List<ProductVO> list = dao.productSearchRecord(search,fieldName);
			setMemberModel(list);
		}
	 */
	public void setMemberModel(List<ProductVO> list) {
		// 기존 JTable의 목록을 지우고 새로 리스트를 출력한다.
		model.setRowCount(0);
		// 컬렉션의 vo를 get하여 JTable에 목록으로 추가한다.
		for (int i = 0; i < list.size(); i++) {
			ProductVO vo = list.get(i);
			Object[] obj = { vo.getHan_name(), vo.getHan_price() };
			model.addRow(obj);
		}

	}

	//레코드 검색
    public void memberSearch() {
       //검색어가 입력되었는지 확인
       NorthFrameMember nfm = new NorthFrameMember();
       JTextField searchWord = nfm.searchTf;
       if(search!=null && !search.equals("")) { //검색어가 있다.
          //String searchField = (String)searchKey.getSelectedItem(); //검색키 "이름", "전화번호", "주소"
       //   String searchField="";
          String fieldName="";
          if(search.equals("이름")) {
             fieldName = "han_name";
          }
          
          ProductDAO dao = new ProductDAO();
          List<ProductVO> list = dao.productSearchRecord(search,fieldName);
          if(list.isEmpty()) {
             JOptionPane.showMessageDialog(null, "검색어와 일치하는 항목이 없습니다.", "SEARCH ERROR", JOptionPane.CLOSED_OPTION);
          }
          setMemberModel(list);
          searchWord.setText(""); //끝난 후 지우기
       }
       //
    }


	public void actionPerformed(ActionEvent e) {
		
		
	}
    
//	
//	public void productDetail() {
//		// 대여화면으로 넘어갈 항목 선택
//		String delNum = String.valueOf(TableMouseEvent.han_num);
//			ProductDAO dao = new ProductDAO();
//			int result = dao.getSelectedSearch(TableMouseEvent.han_num);
//		}
    
//    public void mousePressed(MouseEvent me) {
//		if(me.getButton()==1) { // 왼쪽버튼 클리되면
//			// 현재 클릭한 행을 구하여
//			int row = ProductList.getSelectedRow(); // 0, 1, 2, 3, 4
//			int han_num = (int)ProductList.getValueAt(row, 0);
//			ProductDAO dao = new ProductDAO();
//			
//			NorthFrameAdmin.centerPanel.setVisible(false);
//			NorthFrameMember.centerPanel.removeAll();
//			BorrowClothes bc = new BorrowClothes();
//			NorthFrameMember.centerPanel.add(bc.centerPane);
//			NorthFrameAdmin.centerPanel.setVisible(true);
//		}
//	}
	}


