package ch01.ex00.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import ch01.ex00.dao.MemberDao;
import ch01.ex00.model.Member;
import ch04.ude.RecordNotFoundException;

// JDBC版
public class MemberDaoImpl_Jdbc implements MemberDao {
	Context ctx;

	public MemberDaoImpl_Jdbc() {
		try {
			ctx = new InitialContext();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 新增一筆Member紀錄(OK)
	@Override
	public Member save(Member mem) {
		String sql = "INSERT INTO ch04_MemberExample " 
			+ "(account, password, name, phoneNo, birthday, "
			+ " experience, registerTime) VALUES( ?, ?, ?, ?, ?, ?, ?)";
		try {
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/MemberDataBase");
			try (
				Connection conn = ds.getConnection(); 
				PreparedStatement stmt = conn.prepareStatement(sql,
						                 PreparedStatement.RETURN_GENERATED_KEYS);
			) {
				stmt.setString(1, mem.getUserId());
				stmt.setString(2, mem.getPassword());
				stmt.setString(3, mem.getName());
				stmt.setString(4, mem.getPhoneNo());
				stmt.setDate(5, mem.getBirthday());
				stmt.setInt(6, mem.getExperience());
				stmt.setTimestamp(7, mem.getRegisterTime());
				int affectedRows = stmt.executeUpdate();

		        if (affectedRows == 0) {
		            throw new RuntimeException("新增失敗.");
		        }
		        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
		            if (generatedKeys.next()) {
		            	mem.setId(generatedKeys.getInt(1));
		            } else {
		                throw new RuntimeException("新增失敗.無法得到主鍵值");
		            }
		        }
			}
		} catch (Exception e) {
			throw new RecordNotFoundException(e);
		}
		return mem;
	}

	// 查詢一筆Member紀錄(OK)
	@Override
	public Member findById(Integer id) {
		Member member = null;
		String sql = "SELECT * FROM ch04_MemberExample WHERE id = ?";
		try {
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/MemberDataBase");
			try (
				Connection conn = ds.getConnection(); 
				PreparedStatement stmt = conn.prepareStatement(sql);
			) {
				stmt.setInt(1, id);
				try (ResultSet rset = stmt.executeQuery();) {
					if (rset.next()) {
						member = new Member(rset.getInt("id"), 
											rset.getString("account"), 
											rset.getString("password"),
											rset.getString("name"), 
											rset.getString("PhoneNo"), 
											rset.getInt("experience"),
											rset.getDate("birthday"),
											rset.getTimestamp("registerTime")
											);
					}
				}
			}
		} catch (Exception e) {
			throw new RecordNotFoundException(e);
		}
		return member;
	}

	// 刪除一筆Member紀錄
	@Override
	public void delete(Integer id) {
		String sql = "DELETE FROM ch04_MemberExample WHERE id = ?";
		try {
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/MemberDataBase");
			try (
				Connection conn = ds.getConnection(); 
				PreparedStatement stmt = conn.prepareStatement(sql);
			) {
				stmt.setInt(1, id);
				int count = stmt.executeUpdate();

				if (count == 0) {
					throw new RecordNotFoundException("無法刪除紀錄或該筆紀錄不存在");
				}
			}
		} catch (Exception e) {
			throw new RecordNotFoundException(e);
		}
		return;
	}

	
	// 更新一筆Member紀錄
	@Override
	public void update(Member mem) {

		String sql = "UPDATE ch04_MemberExample SET account=?, password=?, name=?, phoneNo=?, experience=?, birthday=? where id = ?";
		try {
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/MemberDataBase");
			try (
				Connection conn = ds.getConnection(); 
				PreparedStatement stmt = conn.prepareStatement(sql);
			) {
				stmt.setString(1, mem.getUserId());
				stmt.setString(2, mem.getPassword());
				stmt.setString(3, mem.getName());
				stmt.setString(4, mem.getPhoneNo());
				stmt.setInt(5, mem.getExperience());
				stmt.setDate(6, mem.getBirthday());
				stmt.setInt(7, mem.getId());
				int count = stmt.executeUpdate();
				if (count == 0) {
					throw new RecordNotFoundException("無法更新紀錄或該筆紀錄不存在");
				}
			}
		} catch (Exception e) {
			throw new RecordNotFoundException(e);
		}
		return;
	}

	// 查詢多筆Member紀錄(OK)
	public List<Member> findAll() {
		List<Member> allMembers = new ArrayList<Member>();
		String sql = "SELECT * FROM  ch04_MemberExample";
		try {
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/MemberDataBase");
			try (
				Connection conn = ds.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();
			) {
				Member mem = null;
				while (rs.next()) {
					mem = new Member(
									rs.getInt("id"), 
									rs.getString("account"), 
									rs.getString("password"),
									rs.getString("name"), 
									rs.getString("PhoneNo"), 
									rs.getInt("experience"),
									rs.getDate("birthday"),
									rs.getTimestamp("RegisterTime")
									);
					allMembers.add(mem);
				}
			}
		} catch (Exception e) {
			throw new RecordNotFoundException(e);
		}
		return allMembers;
	}
	// (OK)
	public boolean existsByUserId(String userId) {
		boolean exist = false;
		String sql = "SELECT * FROM ch04_MemberExample WHERE account = ?";
		try {
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/MemberDataBase");
			try (
				Connection conn = ds.getConnection(); 
				PreparedStatement stmt = conn.prepareStatement(sql);
			) {
				stmt.setString(1, userId);
				try (ResultSet rs = stmt.executeQuery();) {
					if (rs.next()) {
						exist = true;
					}
				}
			}
		} catch (Exception e) {
			throw new RecordNotFoundException(e);
		}
		return exist;
	}

	
	public void close() {
		try {
			ctx.close();
		} catch (NamingException e) {
			throw new RuntimeException(e);
		}
	}
}
