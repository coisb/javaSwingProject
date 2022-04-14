
import java.util.ArrayList;
import java.util.List;


public class MemberDAO extends DBCON{ 

	public MemberDAO() {

	} 

	//회원전체선택 // memberAllRecord문제없으면 allRecord 삭제
	public List<MemberVO> memberAllRecord() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		try {
			//1.db연결
			dbConn();
			String sql = "select distinct m.mem_num, m.mem_name, m.mem_gender, m.mem_tel, to_char(m.mem_birth, 'YYYY-MM-DD'), to_char(m.mem_date, 'YYYY-MM-DD'), r.rent_status "
					+ "from member m join rent r on r.mem_num = m.mem_num order by Mem_num asc";
			//2. preparestatement 생성
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
			System.out.println("전체회원선택 에러 발생...");
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return list;
	}

	//회원전체선택 // memberAllRecord문제없으면 allRecord 삭제
	public List<MemberVO> allRecord() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		try {
			//1.db연결
			dbConn();
			String sql = "select Mem_num, Mem_name, Mem_gender, Mem_tel, to_char(Mem_birth, 'YYYY-MM-DD'), Mem_email,"
					+ "to_char(Mem_date, 'YYYY-MM-DD'), Mem_id, Mem_pw from member order by Mem_num asc";
			//2. preparestatement 생성
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
			System.out.println("전체회원선택 에러 발생...");
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return list;
	}

	// 회원가입 정보를 DB에 insert 
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

			pstmt.setString(1, vo.getMem_id()); //1번 물음표에 저 값을 셋팅하라
			pstmt.setString(2, vo.getMem_pw()); ////////////////////<- 여기다가 ps.setString(2,Login.loginID) 이런식으로 넣기
			pstmt.setString(3, vo.getMem_name()); 
			pstmt.setString(4, vo.getMem_birth()); 
			pstmt.setString(5, vo.getMem_gender()); 
			pstmt.setString(6, vo.getMem_tel()); 
			pstmt.setString(7, vo.getMem_email()); 

			result = pstmt.executeUpdate(); 

		} catch(Exception e) { 
			System.out.println("회원가입 에러발생");
			e.printStackTrace(); 
		} finally{ 
			dbClose(); 
		} return result; 
	} 


	//정보수정 후 DB에 update
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

	// 아이디 중복확인: 입력받은 아이디가 DB에 있는지 없는지 확인
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


	//로그인 시 ID,PW일치 여부 확인
	public int getIDPW(String searchId, String searchPw){  //searchId,searchPw는 JTextfield에 사용자가 입력할 때의 문자열
		//		List<MemberVO> list = new ArrayList<MemberVO>();

		int cnt=0; //id와 pw가 일치하는 레코드가 몇개인지 세기 위함.
		try {
			dbConn(); //db연결

			String sql = "select mem_id,mem_pw from member"
					+ " where mem_id=? and mem_pw=?";  //id와 pw가 둘다 일치하는 레코드가 있는지 select하는 레코드
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchId);  //? 각 자리에 JTextfield문자열 셋팅.			
			pstmt.setString(2, searchPw);			

			rs= pstmt.executeQuery();

			if(rs.next()) { //레코드 수가 몇개인지 확인하는 메소드
				cnt=1; //일치하면 cnt를 1로 변경   만약 없다면 cnt=0
			}
		}catch(Exception e) {
			System.out.println("로그인 id,pw확인");
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return cnt; //cnt 0또는 1을 반환.
	}

	//로그인 한 사람의 회원번호 알아냄
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
			System.out.println("로그인 한 사람의 회원번호 알아내지 못함....");
			e.printStackTrace();
		}finally {
			dbClose();
		}return loginWhoNum;
	}



}

