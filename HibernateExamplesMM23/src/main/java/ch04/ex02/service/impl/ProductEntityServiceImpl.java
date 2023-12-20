package ch04.ex02.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import ch00.HibernateUtils;
import ch04.ex02.dao.CategoryDao;
import ch04.ex02.dao.ProductEntityDao;
import ch04.ex02.dao.impl.CategoryDaoImpl;
import ch04.ex02.dao.impl.ProductEntityDaoImpl;
import ch04.ex02.model.Category;
import ch04.ex02.model.ProductEntity;
import ch04.ex02.service.ProductEntityService;

public class ProductEntityServiceImpl implements ProductEntityService {

	ProductEntityDao  productEntityDao;
	CategoryDao  categoryDao;
	SessionFactory  factory;
	
	public ProductEntityServiceImpl() {
		this.categoryDao = new CategoryDaoImpl();
		this.productEntityDao = new ProductEntityDaoImpl();
		this.factory = HibernateUtils.getSessionFactory();
	}
	
	@Override
	public Object save(ProductEntity product) {
		Object obj = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Category category = categoryDao.findByName(product.getCategory().getName());
			if (category != null) {
				product.setCategory(category);
			} else {
				categoryDao.save(product.getCategory());
			}
			obj = productEntityDao.save(product);
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
	public ProductEntity findById(int id) {
		ProductEntity productEntity = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			productEntity = productEntityDao.findById(id);
			tx.commit();
		} catch(Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(ex.getMessage());
		}
		
		return productEntity;
	}

	@Override
	public void update(ProductEntity product) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			productEntityDao.update(product);
			tx.commit();
		} catch(Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(ex.getMessage());
		}
	}

	@Override
	public void delete(int id) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			productEntityDao.delete(id);
			tx.commit();
		} catch(Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(ex.getMessage());
		}
	}

	@Override
	public List<ProductEntity> findAll() {
		List<ProductEntity>  ProductEntities = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			ProductEntities = productEntityDao.findAll();
		    tx.commit();
		} catch(Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(ex.getMessage());
		}
		return ProductEntities;
	}

	@Override
	public void close() {
		productEntityDao.close();
	}
}
