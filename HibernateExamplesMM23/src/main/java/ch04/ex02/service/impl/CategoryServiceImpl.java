package ch04.ex02.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import ch00.HibernateUtils;
import ch04.ex02.dao.CategoryDao;
import ch04.ex02.dao.impl.CategoryDaoImpl;
import ch04.ex02.model.Category;
import ch04.ex02.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {

	CategoryDao  categoryDao;
	SessionFactory  factory;
	
	public CategoryServiceImpl() {
		this.categoryDao = new CategoryDaoImpl();
		this.factory = HibernateUtils.getSessionFactory();
	}

	@Override
	public Category findById(int id) {
		Category category = null;
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
			category = categoryDao.findById(id);
//			tx.commit();
//		} catch(Exception ex) {
//			if (tx != null) {
//				tx.rollback();
//			}
//			throw new RuntimeException(ex.getMessage());
//		}
		return category;
	}

	@Override
	public Category findByName(String categoryName) {
		Category category = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
		    category = categoryDao.findByName(categoryName);
		    tx.commit();
		} catch(Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(ex.getMessage());
		}
		return category;
	}

	@Override
	public void closeFactory() {
		categoryDao.closeFactory();
	}

	@Override
	public Object save(Category category) {
		Object obj = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			obj = categoryDao.save(category);
			tx.commit();
		} catch(Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(ex.getMessage());
		}
		return obj;
	}

	@Override
	public List<Category> findAll() {
		List<Category>  categories = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
		    categories = categoryDao.findAll();
		    tx.commit();
		} catch(Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(ex.getMessage());
		}
		return categories;
	}
}
