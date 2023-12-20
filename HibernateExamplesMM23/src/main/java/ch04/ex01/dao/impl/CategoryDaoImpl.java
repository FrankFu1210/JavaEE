package ch04.ex01.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ch00.HibernateUtils;
import ch04.ex01.dao.CategoryDao;
import ch04.ex01.model.Category;

public class CategoryDaoImpl implements CategoryDao {

	SessionFactory  factory;
	
	public CategoryDaoImpl() {
		this.factory = HibernateUtils.getSessionFactory();
	}

	@Override
	public Category findById(Integer id) {
		Session session = factory.getCurrentSession();
		return session.get(Category.class, id);
	}

	@Override
	public Category findByName(String categoryName) {
		Session session = factory.getCurrentSession();
		String  hql = "FROM ch04_ex01_Category c WHERE c.name = :name"; 
		List<Category> categories = session.createQuery(hql, Category.class)
				                           .setParameter("name", categoryName)
				                           .getResultList();
		if (categories.size() > 0) {
			return categories.get(0);
		}  else {
			return null;
		}
	}

	@Override
	public Category save(Category category) {
		Session session = factory.getCurrentSession();
		session.save(category);
		return findById(category.getCategoryId());
	}

	@Override
	public List<Category> findAll() {
		Session session = factory.getCurrentSession();
		String  hql = "FROM ch04_ex01_Category"; 
		List<Category> categories = session.createQuery(hql, Category.class)
				                           .getResultList();
		return categories;
	}

}
