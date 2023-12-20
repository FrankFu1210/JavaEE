package ch01.model.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ch00.HibernateUtils;
import ch01.model.Employee;
import ch01.model.dao.EmployeeDao;

public class EmployeeDaoImpl implements EmployeeDao {

	SessionFactory factory;

	public EmployeeDaoImpl() {
		factory = HibernateUtils.getSessionFactory();
	}

	@Override
	public void persist(Employee emp) {
		Session session = factory.getCurrentSession();
		session.persist(emp);
	}

	public Object save(Employee emp) {
		Session session = factory.getCurrentSession();
//		session.save(emp);
		session.persist(emp);
		return emp;
	}
	
	// 更新紀錄
	public void update(Employee employee) {
		Session session = factory.getCurrentSession();
		session.saveOrUpdate(employee);
	}

	// 刪除紀錄
	public void delete(Integer id) {
		Session session = factory.getCurrentSession();
		Employee emp = findById(id);
		if (emp  != null ) {
			emp.setEmployeeId(null);
			session.delete(emp);
		} else {
			throw new RuntimeException("紀錄不存在，無法刪除");
		} 
	}

	public void close() {
		factory.close();
	}

	@Override
	public Employee findById(Integer id) {
		Session session = factory.getCurrentSession();
		Employee emp = (Employee) session.get(Employee.class, id);
		return emp;
	}
	
	// 查詢所有紀錄
	@Override
	public List<Employee> findAll() {
		Session session = factory.getCurrentSession();
		List<Employee> 	employees = session.createQuery("FROM ch01_Employee", Employee.class)
					                       .getResultList();
		return employees;
	}

}
