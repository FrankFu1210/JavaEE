package ch01.model.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import ch00.HibernateUtils;
import ch01.model.Department;
import ch01.model.dao.DepartmentDao;
import ch01.model.dao.impl.DepartmentDaoImpl;
import ch01.model.service.DepartmentService;

public class DepartmentServiceImpl implements DepartmentService {

	DepartmentDao departmentDao;
	SessionFactory factory;
	
	public DepartmentServiceImpl() {
		departmentDao = new DepartmentDaoImpl();
		factory = HibernateUtils.getSessionFactory();
	}

	@Override
	public Department findById(Integer id) {
		Department dept = null;
		dept = departmentDao.findById(id);
		return dept;
	}
	
	public Department findByIdWithoutLazyLoading(Integer id) {
		Department dept = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			dept = departmentDao.findById(id);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		return dept;
	}
	
	public Department findByIdWithLazyLoading(Integer id) {
		Department dept = null;
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
			dept = departmentDao.findById(id);
//			tx.commit();
//		} catch (Exception e) {
//			if (tx != null) {
//				tx.rollback();
//          }			
//			e.printStackTrace();
//			throw new RuntimeException(e.getMessage());
//		}
		return dept;
	}

	@Override
	public void closeFactory() {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			departmentDao.closeFactory();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		
	}

	@Override
	public Object save(Department dept) {
		Object obj = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			obj = departmentDao.save(dept);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		return obj;
	}

	@Override
	public List<Department> findAll() {
		List<Department> list = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			list = departmentDao.findAll();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		return list;
	}
}
