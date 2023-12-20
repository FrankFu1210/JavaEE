package ch01.ex00.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import ch00.HibernateUtils;
import ch01.ex00.dao.MemberDao;
import ch01.ex00.dao.impl.MemberDaoImpl_Hibernate;
import ch01.ex00.model.Member;
import ch01.ex00.service.MemberService;
import ch04.ude.RecordNotFoundException;
 
public class MemberServiceImpl_Hibernate implements MemberService {
	MemberDao dao;
	SessionFactory factory;

	public MemberServiceImpl_Hibernate() {
		dao = new MemberDaoImpl_Hibernate();
		factory = HibernateUtils.getSessionFactory();
	}

	@Override
	public Member save(Member member) {
		Member obj = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			obj = dao.save(member);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			throw new RuntimeException(e);
		}
		return obj;
	}

	@Override
	public Member findById(Integer id) {
		Member member = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			member = dao.findById(id);
			if (member == null ) {
				throw new RecordNotFoundException(id);
			} 
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			} 
			if (e instanceof RecordNotFoundException) {
				throw new RecordNotFoundException(id);
			} else {
				throw new RuntimeException(e);
			}
		}
		return member;

	}
	
	@Override
	public void delete(Integer id) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Member mem0 = dao.findById(id);
			if (mem0 == null ) {
				throw new RecordNotFoundException(id);
			} 
			dao.delete(id);
			tx.commit();
		} catch (Exception e) {
			if (e instanceof RecordNotFoundException) {
				throw new RecordNotFoundException(id);
			} else {
				throw new RuntimeException(e);
			}
		}
	}

	@Override
	public void update(Member mem) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			dao.update(mem);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public List<Member> findAll() {
		List<Member> members = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			members = dao.findAll();
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			throw new RuntimeException(e);
		}
		return members;
	}
	
	@Override
	public boolean existsByUserId(String id) {
		boolean exist = false;

		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			exist = dao.existsByUserId(id);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			throw new RuntimeException(e);
		}
		return exist;
	}

}
