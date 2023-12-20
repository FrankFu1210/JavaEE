package ch01.model.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ch00.HibernateUtils;
import ch01.model.Department;
import ch01.model.dao.DepartmentDao;


public class DepartmentDaoImpl implements DepartmentDao {
	
	SessionFactory factory; 
	
	public DepartmentDaoImpl()  { 
		factory = HibernateUtils.getSessionFactory();
	}

	// 經由Session介面的get()查詢資料庫內的紀錄
	public Department findById(Integer id) {
		Department dept = null;
		Session session = factory.getCurrentSession();
//		System.out.println("session=" + session);
		dept = (Department) session.get(Department.class, id);
		return dept;
	}

	public void closeFactory() {
		factory.close();
	}

	@Override
	public Object save(Department dept) {
		Object obj = null;
		Session session = factory.getCurrentSession();
		obj = session.save(dept);
		return obj;
	}

	@Override
	public List<Department> findAll() {
		Session session = factory.getCurrentSession();
		List<Department> departments = session.createQuery("FROM ch01_Department", Department.class)
					                          .getResultList();
		return departments;
	}

	@Override
	public Department findByName(String deptName) {
		Department dept = null;
		Session session = factory.getCurrentSession();
		String hql = "FROM ch01_Department WHERE depName = :dname";
		List<Department> list = session.createQuery(hql, Department.class)
					                   .setParameter("dname", deptName)
					                   .getResultList();
		if (!list.isEmpty()) {
			dept = list.get(0);
		}   
		return dept;
	}
}
