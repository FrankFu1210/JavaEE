package lab01.dao.impl;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import lab01.dao.MemberDao;
import lab01.model.MemberBean;

public class MemberJdbcDaoImpl implements MemberDao {

	DataSource ds = null;

	public MemberJdbcDaoImpl() {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/MemberDB");
		} catch (NamingException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	private static final String SELECT_BY_MEMBERID = "SELECT memberId, password, name, phone, birthday, registerdate, picture, weight FROM memberlab01 WHERE memberId = ?";

	public MemberBean findByMemberId(String id) {
		MemberBean result = null;
		try (Connection conn = ds.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SELECT_BY_MEMBERID);) {
			stmt.setString(1, id);
			try (ResultSet rset = stmt.executeQuery();) {
				if (rset.next()) {
					result = new MemberBean();
					result.setMemberId(rset.getString("memberId"));
					result.setPassword(rset.getString("password"));
					result.setName(rset.getString("name"));
					result.setPhone(rset.getString("phone"));
					result.setBirthday(rset.getDate("birthday"));
					result.setWeight(rset.getDouble("weight"));
					result.setRegisterDate(rset.getTimestamp("registerDate"));
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		return result;
	}

	private static final String SELECT_ALL = "SELECT id, memberId, password, name, phone, birthday, registerdate, picture, weight FROM memberlab01";

	public List<MemberBean> findAll() {
		List<MemberBean> result = null;
		try (Connection conn = ds.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SELECT_ALL);
				ResultSet rset = stmt.executeQuery();) {
			result = new Vector<>();
			while (rset.next()) {
				MemberBean temp = new MemberBean();
				temp.setId(rset.getInt("id"));
				temp.setMemberId(rset.getString("memberid"));
				temp.setPassword(rset.getString("password"));
				temp.setName(rset.getString("name"));
				temp.setPhone(rset.getString("phone"));
				temp.setBirthday(rset.getDate("birthday"));
				temp.setWeight(rset.getDouble("weight"));
				temp.setRegisterDate(rset.getTimestamp("registerDate"));
				result.add(temp);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		return result;
	}

	private static final String INSERT = "INSERT INTO memberlab01 (memberId, password, name, phone, birthday, registerdate, picture, weight) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

	public void save(MemberBean bean) {
		try (Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(INSERT);) {
			stmt.setString(1, bean.getMemberId());
			stmt.setString(2, bean.getPassword());
			stmt.setString(3, bean.getName());
			stmt.setString(4, bean.getPhone());

			java.util.Date temp = bean.getBirthday();
			if (temp != null) {
				java.sql.Date someTime = new java.sql.Date(temp.getTime());
				stmt.setDate(5, someTime);
			} else {
				stmt.setDate(5, null);
			}

			Timestamp ts = new Timestamp(System.currentTimeMillis());
			stmt.setTimestamp(6, ts);
			Blob b = null;
			stmt.setBlob(7, b);
			stmt.setDouble(8, bean.getWeight());

			stmt.executeUpdate();
		} catch (SQLException ex) {
			throw new RuntimeException(ex.getMessage());
		}

	}

	private static final String DELETE_BY_MEMBERID = "DELETE FROM memberlab01 WHERE memberId=?";

	public void deleteByMemberId(String memberId) {
		try (Connection conn = ds.getConnection();
				PreparedStatement stmt = conn.prepareStatement(DELETE_BY_MEMBERID);) {
			stmt.setString(1, memberId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		return;
	}

	@Override
	public boolean existsByMemberId(String memberId) {
		MemberBean memberBean = findByMemberId(memberId);
		return (memberBean != null);
	}

	private static final String SELECT_BY_ID = "SELECT id, memberId, password, name, phone, birthday, registerdate, picture, weight FROM memberlab01 WHERE id = ?";

	@Override
	public MemberBean findById(Integer id) {
		MemberBean memberBean = null;
		try (Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID);) {
			stmt.setInt(1, id);
			try (ResultSet rset = stmt.executeQuery();) {
				if (rset.next()) {
					memberBean = new MemberBean();
					memberBean.setId(rset.getInt("Id"));
					memberBean.setMemberId(rset.getString("memberId"));
					memberBean.setPassword(rset.getString("password"));
					memberBean.setName(rset.getString("name"));
					memberBean.setPhone(rset.getString("phone"));
					memberBean.setBirthday(rset.getDate("birthday"));
					memberBean.setWeight(rset.getDouble("weight"));
					memberBean.setRegisterDate(rset.getTimestamp("registerDate"));
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		return memberBean;
	}

	
	
	private static final String DELETE_BY_ID = "DELETE FROM memberlab01 WHERE id=?";

	@Override
	public void deleteById(Integer id) {
		try (Connection conn = ds.getConnection();
				PreparedStatement stmt = conn.prepareStatement(DELETE_BY_ID);) {
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		return;
	}

	private static final String UPDATE = "UPDATE memberlab01 set name = ?,  password = ?,  phone = ?, birthday = ?,  weight = ? WHERE id = ?";

	@Override
	public void update(MemberBean memberBean) {
		try (
			Connection conn = ds.getConnection(); 
			PreparedStatement stmt = conn.prepareStatement(UPDATE);
		) {
			stmt.setString(1, memberBean.getName());
			stmt.setString(2, memberBean.getPassword());
			stmt.setString(3, memberBean.getPhone());

			java.util.Date temp = memberBean.getBirthday();
			if (temp != null) {
				java.sql.Date someTime = new java.sql.Date(temp.getTime());
				stmt.setDate(4, someTime);
			} else {
				stmt.setDate(4, null);
			}
			stmt.setDouble(5, memberBean.getWeight());
			stmt.setInt(6, memberBean.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	

}