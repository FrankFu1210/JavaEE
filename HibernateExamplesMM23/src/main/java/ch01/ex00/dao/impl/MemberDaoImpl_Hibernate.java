package ch01.ex00.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ch00.HibernateUtils;
import ch01.ex00.dao.MemberDao;
import ch01.ex00.model.Member;

public class MemberDaoImpl_Hibernate implements MemberDao {
	
	SessionFactory factory = HibernateUtils.getSessionFactory();

	public boolean existsByUserId(String id) {
		boolean exist = false;
		String hql = "FROM Member WHERE userId = :uid";
		Session session = factory.getCurrentSession();
//  方法 A: 			
//			List<Member> list = session.createQuery(hql)
//								.setParameter("uid", id)
//								.getResultList();
//			if (list.size() > 0) { 
//				exist = true;
//			}
//  方法 B: 						
			Member member = null;
			try {
				member = (Member) session.createQuery(hql)
						.setParameter("uid", id)
						.getSingleResult();
			} catch(NoResultException e){
				exist = false;
			}
			if (member != null) { 
				exist = true;
			}
		return exist;
	}
	// 新增一筆Member物件到資料庫
	public Member save(Member mem){
		Session session = factory.getCurrentSession();
		session.save(mem);
		return mem;
	}
	
	// 經由Session介面的get()查詢資料庫內的紀錄
	public Member findById(Integer id) {
		Member member = null;
		Session session = factory.getCurrentSession();
		member = session.get(Member.class, id);
		return member;
	}
	// 更新方法一   
	public void update0(Member mem)  {
		Member member = null;
		Session session = factory.getCurrentSession();
		// member為永續物件
        member = (Member)session.get(Member.class, mem.getId()); 
		member.setExperience(mem.getExperience());
		member.setName(mem.getName());
		member.setPhoneNo(mem.getPhoneNo());
	}
	// 更新方法二
	public void update2(Member mem)  {
		Session session = factory.getCurrentSession();
		session.merge(mem);        // mem為Transient物件
		
	}
	// 更新方法三			
	public void update(Member mem)  {
		Session session = factory.getCurrentSession();
		session.saveOrUpdate("Member", mem);  // mem為Transient物件
	}
	// 刪除紀錄
	
	// 查詢所有紀錄
	public List<Member> findAll()  {
		String hql = "FROM Member"; 
		List<Member> allMembers = null;
		Session session = factory.getCurrentSession();
		allMembers = session.createQuery(hql, Member.class)
				            .getResultList();
		return allMembers;
	}
	
	@Override
	public void delete(Integer id) {
		Session session = factory.getCurrentSession();
		Member mem0 = findById(id);
		session.delete(mem0);  
	}
	
}
