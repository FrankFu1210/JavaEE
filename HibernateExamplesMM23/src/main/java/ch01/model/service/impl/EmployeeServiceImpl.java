package ch01.model.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import ch00.HibernateUtils;
import ch01.model.Department;
import ch01.model.Employee;
import ch01.model.dao.DepartmentDao;
import ch01.model.dao.EmployeeDao;
import ch01.model.dao.impl.DepartmentDaoImpl;
import ch01.model.dao.impl.EmployeeDaoImpl;
import ch01.model.service.EmployeeService;
 
public class EmployeeServiceImpl implements EmployeeService {

	EmployeeDao    employeeDao;
	DepartmentDao  departmentDao;
	SessionFactory factory;
	
	public EmployeeServiceImpl() {
		employeeDao = new EmployeeDaoImpl();
		departmentDao = new DepartmentDaoImpl();
		factory = HibernateUtils.getSessionFactory();
	}

	@Override
	public Object save(Employee emp) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		Object key = null;
		try {
			tx = session.beginTransaction();
			String depName = emp.getDept().getDepName();
			if ( depName != null ) {
				Department dept = departmentDao.findByName(depName);
				if (dept != null) {
					emp.setDept(dept);
				}
			}
            key = employeeDao.save(emp);
            tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		return key;
	}

	@Override
	public Employee findById(Integer id) {
		Employee emp = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
		    emp =  employeeDao.findById(id);
		    tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		return emp;
	}

	@Override
	public void update(Employee emp) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			employeeDao.update(emp);
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
	public void delete(Integer id) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			employeeDao.delete(id);
            tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			if (e.getClass() == RuntimeException.class && e.getMessage().equals("紀錄不存在，無法刪除" ))  {
				System.out.println("紀錄不存在，無法刪除");
			} else {
			   e.printStackTrace();
			}
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public void persist(Employee emp) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			employeeDao.persist(emp);
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
	public List<Employee> findAll() {
		List<Employee> allEmployees = new ArrayList<Employee>();
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			allEmployees =  employeeDao.findAll();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		return allEmployees;
	}
	
	@Override
	public void close() {
		employeeDao.close();
	}

}
