import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class ProductDAO extends DBCON {
	BufferedImage bi;
	public ProductDAO() {

	}
	//검색상품선택
	public List<ProductVO> productSearchRecord(String search, String fieldName) {
		List<ProductVO> list = new ArrayList<ProductVO>();
		try {
			//1.db연결
			dbConn();
			System.out.println(fieldName);
			String sql = "select distinct han_name, han_price from product where han_name like ? ";
			//2. preparestatement 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+search+"%"); // "%김%"

			rs = pstmt.executeQuery();
			while(rs.next()) {
				ProductVO vo = new ProductVO();
				vo.setHan_name(rs.getString(1));
				vo.setHan_price(rs.getInt(2));
				list.add(vo);
			}
		}catch(Exception e) {
			System.out.println("검색 에러 발생...");
			e.printStackTrace();
		}finally {
			dbClose();
		}

		return list;
	}
	//상품전체선택
	public List<RentVO> productAllRecord() {
		List<RentVO> list = new ArrayList<RentVO>();
		try {
			//1.db연결
			dbConn();
			String sql = "select r.rent_num, p.han_num, m.mem_num, to_char(r.rent_start, 'YYYY-MM-DD'), to_char(r.rent_return, 'YYYY-MM-DD'), "
					+ "to_char(r.rent_real_return, 'YYYY-MM-DD'), r.rent_fee "
					+ "from product p join rent r on p.han_num = r.han_num "
					+ "join member m on r.mem_num = m.mem_num order by r.rent_num asc";
			//2. preparestatement 생성
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			while(rs.next()) {
				RentVO vo = new RentVO();
				vo.setRent_num(rs.getInt(1));
				vo.setHan_num(rs.getInt(2));
				vo.setMem_num(rs.getInt(3));
				vo.setRent_start(rs.getString(4));
				vo.setRent_return(rs.getString(5));
				vo.setRent_real_return(rs.getString(6));
				vo.setRent_fee(rs.getInt(7));
				list.add(vo);
			}
		}catch(Exception e) {
			System.out.println("전체상품선택 에러 발생...");
			e.printStackTrace();
		}finally {
			dbClose();
		}

		return list;
	}
	//상품전체선택
	public List<ProductVO> productManageAllRecord() {
		List<ProductVO> list = new ArrayList<ProductVO>();
		try {
			//1.db연결
			dbConn();
			String sql = "select p.han_num,p.han_name,p.han_size,p.han_price,p.han_gender,p.han_age,pa.han_sum "
					+ "from product p join product_amount pa on p.han_num = pa.han_num order by p.han_num asc";

			//2. preparestatement 생성
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			while(rs.next()) {
				ProductVO vo = new ProductVO();
				vo.setHan_num(rs.getInt(1));
				vo.setHan_name(rs.getString(2));
				vo.setHan_size(rs.getString(3));
				vo.setHan_price(rs.getInt(4));
				vo.setHan_gender(rs.getString(5));
				vo.setHan_age(rs.getString(6));
				vo.setHan_sum(rs.getInt(7));
				list.add(vo);
			}
		}catch(Exception e) {
			System.out.println("전체상품선택 에러 발생...");
			e.printStackTrace();
		}finally {
			dbClose();
		}

		return list;
	}
	//상품삭제
	public int deleteRecord(String table, int han_num) {
		int cnt = 0;
		try {
			dbConn();
			String sql1 = "delete from " + table + " where han_num=?";
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, han_num);

			cnt = pstmt.executeUpdate();

		}catch(Exception e) {
			System.out.println("상품삭제 에러 발생,,,");
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return cnt;
	}

	/////////////////////////////////////////////////////////
	// 상품 추가
	public int productInsert(ProductVO vo) { //0731 02:11 변경
		int cnt = 0;
		try {
			dbConn();
			String sql = "insert into product values(?, ?, ?, ?, ?, ?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, vo.getHan_num());
			pstmt.setString(2, vo.getHan_name());
			pstmt.setString(3, vo.getHan_size());
			pstmt.setString(4, vo.getHan_gender());
			pstmt.setString(5, vo.getHan_age());
			pstmt.setInt(6, vo.getHan_price());

			cnt = pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("상품 등록 에러!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return cnt;
	}

	public int productInsertAmount(ProductVO vo) { //0731 02:11 변경
		int cnt = 0;
		try {
			dbConn();
			String sql = "insert into product_amount values(?, ?, 0, ?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, vo.getHan_num());
			pstmt.setInt(2, vo.getHan_sum());
			pstmt.setInt(3, vo.getHan_sum());

			cnt = pstmt.executeUpdate();

		}catch(Exception e) {
			System.out.println("상품 등록 에러!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return cnt;
	}

	public int productInsertImg(ProductVO vo) { //0731 02:11 변경
		int cnt = 0;
		try {
			dbConn();
			String sql = "insert into product_img values(?, ?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, vo.getHan_num());
			File f = new File(vo.getHan_image_path());
			FileInputStream fis = new FileInputStream(f);
			pstmt.setBinaryStream(2, fis,fis.available());

			cnt = pstmt.executeUpdate();

		}catch(Exception e) {
			System.out.println("상품 등록 에러!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return cnt;
	}

	// 제품 클릭시 제품 사진 보여주는 dao
	public BufferedImage getSelectedImage(int han_num) {
		try {
			dbConn();
			String sql = "select han_image from product_img where han_num=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, han_num);

			rs = pstmt.executeQuery();

			rs.next();
			InputStream is = rs.getBinaryStream(1);
			bi = ImageIO.read(is);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return bi;
	}

	//상품 정보 받아오기
	public List<ProductVO> getProductInfo(int han_num) {
		List<ProductVO> list = new ArrayList<ProductVO>();
		try {
			dbConn();
			String sql = "select han_name, han_size, han_gender, han_age, han_price, han_sum, han_image"
					+ " from product p left join product_amount pa on p.han_num=pa.han_num"
					+ " join product_img pi on p.han_num=pi.han_num where p.han_num=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, han_num);

			rs = pstmt.executeQuery();

			while(rs.next()) {
				ProductVO vo = new ProductVO();
				vo.setHan_name(rs.getString(1));
				vo.setHan_size(rs.getString(2));
				vo.setHan_gender(rs.getString(3));
				vo.setHan_age(rs.getString(4));
				vo.setHan_price(rs.getInt(5));
				vo.setHan_sum(rs.getInt(6));
				InputStream is = rs.getBinaryStream(7);
				vo.setHan_image((Image)ImageIO.read(is));
				list.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return list;
	}

	//페이징으로 4개씩 불러오는 메소드(이미지사진,칼럼등..)       
	public List<ProductVO> getDBImageAll(int page) { //ProductMain에서 All 사진출력
		List<ProductVO> list = new ArrayList<ProductVO>();
		try {
			dbConn();
			String sql = "select * from (select * from (select p.han_num, han_image, han_name, han_price from product p join product_img pi "
					+ " on p.han_num=pi.han_num where han_size='S' " + NorthFrameMember.selectedMenu + "order by p.han_num desc) "
					+ " where rownum<=? order by han_num)"
					+ " where rownum<=? order by han_num desc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, page*4);
			pstmt.setInt(2, 4);

			rs = pstmt.executeQuery();

			while(rs.next()) {
				ProductVO vo = new ProductVO();
				vo.setHan_num(rs.getInt(1)); //제품번호

				InputStream is = rs.getBinaryStream(2);
				vo.setHan_image((Image)ImageIO.read(is)); //제품이미지
				vo.setHan_name(rs.getString(3)); //제품명
				vo.setHan_price(rs.getInt(4)); //한복가격
				list.add(vo);            
			}
		}catch(Exception e) {
			System.out.println("이미지 파일 불러오기 오류...");
			e.printStackTrace();
		}finally {
			dbClose();
		}

		return list;
	}


	//한복명,사이즈로 한복번호를 얻어오기  
	public int getHanNum(String hanName,String size) {
		int hanNum=0;
		try {
			dbConn();
			String sql = "select han_num from product where han_name = " +"'"+ hanName +"'"+ " and han_size = "+"'"+ size + "'";

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			while(rs.next()) {
				hanNum = rs.getInt(1);
			}
		}catch(Exception e) {
			System.out.println("한복번호 가져오기 오류.....");
			e.printStackTrace();
		}finally {
			dbClose();
		}

		return hanNum;
	}

	//0802 10:00 한복번호 전체개수 구하기
	public int getTotalHanbok() {
		int hanTotal =0;
		try {
			dbConn();
			String sql = "select count(han_num) from product";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ProductVO pv = new ProductVO();
				hanTotal = rs.getInt(1);
			}
		}catch(Exception e) {
			System.out.println("상품전체 가져오기 오류......");
			e.printStackTrace();
		}finally {
			dbClose();
		}			
		return hanTotal;
	}

	//0802 10:00 한복번호 전체개수 구하기 (아동,여성,남성)
	public int getTotalHanbokAboutAge(int start, int end) {
		int hanTotal =0;
		try {
			dbConn();
			String sql = "select count(han_num) from product where han_num between "+start+" and "+end;
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ProductVO pv = new ProductVO();
				hanTotal = rs.getInt(1);
			}
		}catch(Exception e) {
			System.out.println("상품(아동,여성,남성)전체 개수 가져오기 오류......");
			e.printStackTrace();
		}finally {
			dbClose();
		}

		return hanTotal;
	}
	
	public int productUpdateAmount(ProductVO vo) { //0731 02:11 변경
		int cnt = 0;
		try {
			dbConn();
			String sql = "update product_amount set han_num=?, "
					+ "han_sum=?, "
					+ "han_rend=0, "
					+ "han_left= ? where han_num=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, vo.getHan_num());
			pstmt.setInt(2, vo.getHan_sum());
			pstmt.setInt(3, vo.getHan_sum());
			pstmt.setInt(4, vo.getHan_num());

			cnt = pstmt.executeUpdate();

		}catch(Exception e) {
			System.out.println("상품 업데이트 에러!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return cnt;
	}

	public int productUpdateImg(ProductVO vo) { //0731 02:11 변경
		int cnt = 0;
		try {
			dbConn();
			String sql = "update product_img set han_num=?,"
					+ "han_image= ? where han_num=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, vo.getHan_num());
			File f = new File(vo.getHan_image_path());
			FileInputStream fis = new FileInputStream(f);
			pstmt.setBinaryStream(2, fis,fis.available());
			pstmt.setInt(3, vo.getHan_num());

			cnt = pstmt.executeUpdate();

		}catch(Exception e) {
			System.out.println("상품 업데이트 에러!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return cnt;
	}
	
	public int productUpdate(ProductVO vo) { //0731 02:11 변경
		int cnt = 0;
		try {
			dbConn();
			String sql = "update product set han_num = ?, "
					+ "han_name=?, "
					+ "han_size=?, "
					+ "han_gender=?, "
					+ "han_age=?, "
					+ "han_price=? where han_num=?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, vo.getHan_num());
			pstmt.setString(2, vo.getHan_name());
			pstmt.setString(3, vo.getHan_size());
			pstmt.setString(4, vo.getHan_gender());
			pstmt.setString(5, vo.getHan_age());
			pstmt.setInt(6, vo.getHan_price());
			pstmt.setInt(7, vo.getHan_num());
			
			cnt = pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("상품 등록 에러!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return cnt;
	}
}