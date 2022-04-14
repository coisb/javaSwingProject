
import java.util.ArrayList;
import java.util.List;


public class MemberDAO extends DBCON{ 

	public MemberDAO() {

	} 

	//ȸ����ü���� // memberAllRecord���������� allRecord ����
	public List<MemberVO> memberAllRecord() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		try {
			//1.db����
			dbConn();
			String sql = "select distinct m.mem_num, m.mem_name, m.mem_gender, m.mem_tel, to_char(m.mem_birth, 'YYYY-MM-DD'), to_char(m.mem_date, 'YYYY-MM-DD'), r.rent_status "
					+ "from member m join rent r on r.mem_num = m.mem_num order by Mem_num asc";
			//2. preparestatement ����
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setMem_num(rs.getInt(1));
				vo.setMem_name(rs.getString(2));
				vo.setMem_gender(rs.getString(3));
				vo.setMem_tel(rs.getString(4));
				vo.setMem_birth(rs.getString(5));
				vo.setMem_date(rs.getString(6));
				vo.setRent_status(rs.getString(7));
				list.add(vo);
			}
		}catch(Exception e) {
			System.out.println("��üȸ������ ���� �߻�...");
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return list;
	}

	//ȸ����ü���� // memberAllRecord���������� allRecord ����
	public List<MemberVO> allRecord() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		try {
			//1.db����
			dbConn();
			String sql = "select Mem_num, Mem_name, Mem_gender, Mem_tel, to_char(Mem_birth, 'YYYY-MM-DD'), Mem_email,"
					+ "to_char(Mem_date, 'YYYY-MM-DD'), Mem_id, Mem_pw from member order by Mem_num asc";
			//2. preparestatement ����
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setMem_num(rs.getInt(1));
				vo.setMem_name(rs.getString(2));
				vo.setMem_gender(rs.getString(3));
				vo.setMem_tel(rs.getString(4));
				vo.setMem_birth(rs.getString(5));
				vo.setMem_email(rs.getString(6));
				vo.setMem_date(rs.getString(7));
				vo.setMem_id(rs.getString(8));
				vo.setMem_pw(rs.getString(9));
				list.add(vo);
			}
		}catch(Exception e) {
			System.out.println("��üȸ������ ���� �߻�...");
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return list;
	}

	// ȸ������ ������ DB�� insert 
	public int SignUpInsert(MemberVO vo) { 

		///////////////////////////////////
		System.out.println(Login.mem_id);
		//////////////////////////////////

		int result = 0; 

		try{
			dbConn(); 

			String sql = "insert into member(mem_num, mem_id, mem_pw, mem_name, mem_birth, mem_gender, mem_tel, mem_email,mem_date) " 

			+ " values(mem_num_sq.nextval,?,?,?,?,?,?,?,sysdate)";

			pstmt = conn.prepareStatement(sql); 

			pstmt.setString(1, vo.getMem_id()); //1�� ����ǥ�� �� ���� �����϶�
			pstmt.setString(2, vo.getMem_pw()); ////////////////////<- ����ٰ� ps.setString(2,Login.loginID) �̷������� �ֱ�
			pstmt.setString(3, vo.getMem_name()); 
			pstmt.setString(4, vo.getMem_birth()); 
			pstmt.setString(5, vo.getMem_gender()); 
			pstmt.setString(6, vo.getMem_tel()); 
			pstmt.setString(7, vo.getMem_email()); 

			result = pstmt.executeUpdate(); 

		} catch(Exception e) { 
			System.out.println("ȸ������ �����߻�");
			e.printStackTrace(); 
		} finally{ 
			dbClose(); 
		} return result; 
	} 


	//�������� �� DB�� update
	public int SignUpUpdate(MemberVO vo) { 

		int result = 0; 

		try{
			dbConn(); 

			String sql = "update member set mem_pw=?,mem_name=?,mem_tel=?,mem_email=? where mem_num = ? ";

			pstmt = conn.prepareStatement(sql); 


			pstmt.setString(1, vo.getMem_pw()); 
			pstmt.setString(2, vo.getMem_name()); 
			pstmt.setString(3, vo.getMem_tel()); 
			pstmt.setString(4, vo.getMem_email()); 
			pstmt.setInt(5, vo.getMem_num()); 

			result = pstmt.executeUpdate(); 

		} catch(Exception e) { 
			e.printStackTrace(); 
		} finally{ 
			dbClose(); 
		} return result; 
	} 

	public List<MemberVO> getInfo(String mem_id){ 
		List<MemberVO> list = new ArrayList<MemberVO>(); 
		try { 
			dbConn(); 
			String sql = "select mem_num, mem_name, mem_gender, mem_tel, to_char(mem_birth, 'YYMMDD'), mem_email from member where mem_id = ?"; 

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mem_id); 

			rs = pstmt.executeQuery(); 

			while(rs.next()) { 
				MemberVO vo = new MemberVO();
				vo.setMem_num(rs.getInt(1));
				vo.setMem_name(rs.getString(2));
				vo.setMem_gender(rs.getString(3));
				vo.setMem_tel(rs.getString(4));
				vo.setMem_birth(rs.getString(5));
				vo.setMem_email(rs.getString(6));
				list.add(vo);
			} 
		}catch(Exception e) { 
			e.printStackTrace(); 
		}finally { 
			dbClose(); 
		} 
		return list; 
	}

	// ���̵� �ߺ�Ȯ��: �Է¹��� ���̵� DB�� �ִ��� ������ Ȯ��
	public List<MemberVO> getidCheck(String mem_id){ 

		List<MemberVO> list = new ArrayList<MemberVO>(); 

		try { 

			dbConn(); 

			String sql = "select mem_id from member where mem_id = ?"; 

			pstmt = conn.prepareStatement(sql); 
			pstmt.setString(1, mem_id); 

			rs = pstmt.executeQuery(); 

			while(rs.next()) { 
				MemberVO vo = new MemberVO(); 
				vo.setMem_id(rs.getString(1)); 
				list.add(vo); 
			} 
		}catch(Exception e) { 
			e.printStackTrace(); 
		}finally { 
			dbClose(); 
		} 
		return list; 
	}


	//�α��� �� ID,PW��ġ ���� Ȯ��
	public int getIDPW(String searchId, String searchPw){  //searchId,searchPw�� JTextfield�� ����ڰ� �Է��� ���� ���ڿ�
		//		List<MemberVO> list = new ArrayList<MemberVO>();

		int cnt=0; //id�� pw�� ��ġ�ϴ� ���ڵ尡 ����� ���� ����.
		try {
			dbConn(); //db����

			String sql = "select mem_id,mem_pw from member"
					+ " where mem_id=? and mem_pw=?";  //id�� pw�� �Ѵ� ��ġ�ϴ� ���ڵ尡 �ִ��� select�ϴ� ���ڵ�
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchId);  //? �� �ڸ��� JTextfield���ڿ� ����.			
			pstmt.setString(2, searchPw);			

			rs= pstmt.executeQuery();

			if(rs.next()) { //���ڵ� ���� ����� Ȯ���ϴ� �޼ҵ�
				cnt=1; //��ġ�ϸ� cnt�� 1�� ����   ���� ���ٸ� cnt=0
			}
		}catch(Exception e) {
			System.out.println("�α��� id,pwȮ��");
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return cnt; //cnt 0�Ǵ� 1�� ��ȯ.
	}

	//�α��� �� ����� ȸ����ȣ �˾Ƴ�
	public int getMemNum(String mem_id) {
		int loginWhoNum =0;
		try {
			dbConn();
			String sql = "select mem_num from member where mem_id =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mem_id);

			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				loginWhoNum = rs.getInt(1);
			}
		}catch(Exception e) {
			System.out.println("�α��� �� ����� ȸ����ȣ �˾Ƴ��� ����....");
			e.printStackTrace();
		}finally {
			dbClose();
		}return loginWhoNum;
	}



}

