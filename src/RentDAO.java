import java.util.ArrayList;
import java.util.List;

public class RentDAO extends DBCON {

	public RentDAO() {

	}
	//��ü����
		public List<ProductVO> rentAllRecord() {
			List<ProductVO> list = new ArrayList<ProductVO>();
			try {
				//1.db����
				dbConn();
				String sql = "select r.rent_num, p.han_num, p.han_name, p.han_size, p.han_price, to_char(r.rent_start, 'YYYY-MM-DD'), "
						+ "to_char(r.rent_return, 'YYYY-MM-DD'), r.rent_status, r.rent_fee from product p join rent r on p.han_num = r.han_num "
						+ "where mem_num=? order by r.rent_num asc";
				//2. preparestatement ����
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, Login.loginNum);

				rs = pstmt.executeQuery();
				while(rs.next()) {
					ProductVO vo = new ProductVO();
					vo.setRent_num(rs.getInt(1));
					vo.setHan_num(rs.getInt(2));
					vo.setHan_name(rs.getString(3));
					vo.setHan_size(rs.getString(4));
					vo.setHan_price(rs.getInt(5));
					vo.setRent_start(rs.getString(6));
					vo.setRent_return(rs.getString(7));
					vo.setRent_status(rs.getString(8));
					vo.setRent_fee(rs.getInt(9));
					list.add(vo);
				}
			}catch(Exception e) {
				System.out.println("��ü�뿩���� ���� �߻�...");
				e.printStackTrace();
			}finally {
				dbClose();
			}
			return list;
		}
	//�뿩���
	public int deleteRecord(int rent_num) {
		int cnt = 0;
		try {
			dbConn();
			String sql = "delete from rent where rent_num=?";
			pstmt = conn.prepareStatement(sql);
			System.out.println(rent_num);
			pstmt.setInt(1, rent_num);

			cnt = pstmt.executeUpdate();

		}catch(Exception e) {
			System.out.println("�뿩��� ���� �߻�,,,");
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return cnt;
	}
	
	// RentalList �ݳ��ϱ� ��ư Ŭ���� �뿩���� Y->N���� �ٲٴ� �޼ҵ�
	public int returnProduct(int rent_num) {
		int cnt = 0;
		try {
			dbConn();
			String sql = "update rent set rent_real_return = sysdate, rent_status='N', "
					+ "rent_fee= (case when ((sysdate - rent_return) * 10000) >= 0 then ((round(sysdate) - round(rent_return)) * 10000) else 0 end) "
					+ "where rent_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rent_num);

			cnt = pstmt.executeUpdate();

		}catch(Exception e) {
			System.out.println("�ݳ��ϱ� ���� �߻�");
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return cnt;
	}
	
	// �����Ϸ�� DB�� �뿩���� �Է�
	public int rentInsert(RentVO vo) {
		
		int cnt = 0;
		
		try{
			dbConn(); 
			
			String sql = "insert into rent(rent_num, han_num, mem_num, rent_start, rent_return, rent_status, rent_sum) " 
					
			+ " values(rent_num_sq.nextval,?,?,to_date(?, 'YYYY-MM-DD'),to_date(?, 'YYYY-MM-DD'),?,?)";
			
			pstmt = conn.prepareStatement(sql); 
			
			pstmt.setInt(1, vo.getHan_num()); 
			pstmt.setInt(2, vo.getMem_num()); 
			pstmt.setString(3, vo.getRent_start()); 
			pstmt.setString(4, vo.getRent_return()); 
			pstmt.setString(5, vo.getRent_status()); 
			pstmt.setInt(6, vo.getRent_sum());
			
			cnt = pstmt.executeUpdate(); 
			
		} catch(Exception e) { 
			System.out.println("������ �Ա� - �뿩���� ���� �����߻�");
			e.printStackTrace(); 
			} finally{ 
				dbClose(); 
			} return cnt; 
	}
}