package com.bit2017.guestbook.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bit2017.guestbook.vo.GuestbookVO;

public class GuestbookDAO {

	public boolean insert(GuestbookVO vo) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@192.168.1.23:1521:orcl";
			conn = DriverManager.getConnection(url, "teemo", "2543");

			String sql = "INSERT INTO guestbook VALUES(seq_guestbook.nextval, ?, ?, ?, SYSDATE )";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getContent());

			int cnt = pstmt.executeUpdate();
			result = cnt == 1;
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error : " + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error : " + e);
			}
		}
		return result;

	}

	public boolean delete(GuestbookVO vo) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@192.168.1.23:1521:orcl";
			conn = DriverManager.getConnection(url, "teemo", "2543");

			String sql = "DELETE FROM guestbook WHERE no = ? AND password = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, vo.getNo());
			pstmt.setString(2, vo.getPassword());

			int cnt = pstmt.executeUpdate();
			result = cnt == 1;
		} catch (ClassNotFoundException e) {
			System.out.println("error : 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error : " + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error : " + e);
			}
		}

		return result;
	}

	public List<GuestbookVO> getList() {
		List<GuestbookVO> list = new ArrayList<GuestbookVO>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@192.168.1.23:1521:orcl";
			conn = DriverManager.getConnection(url, "teemo", "2543");
			
			String sql = "SELECT no, name, content, TO_CHAR(reg_date, 'yyyy-mm-dd') " +
					"FROM guestbook ORDER BY reg_date desc";
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				String content = rs.getString(3);
				String regDate = rs.getString(4);
				
				GuestbookVO vo = new GuestbookVO();
				vo.setNo(no);
				vo.setName(name);
				vo.setContent(content);
				vo.setRegDate(regDate);
				
				list.add(vo);
			}
		} catch ( ClassNotFoundException e ) {
			System.out.println("error : 드라이버 로딩 실패 - " + e);
		} catch ( SQLException e) {
			System.out.println("error : " + e);
		} finally { 
			try {
				if (rs != null) {
					rs.close();
				}
				if(stmt != null) {
					stmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch(SQLException e ) {
				System.out.println("error : " + e);
			}
		}
		return list;
	}
}
