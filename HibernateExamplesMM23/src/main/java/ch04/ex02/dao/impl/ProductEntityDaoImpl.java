package ch04.ex02.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ch00.HibernateUtils;
import ch04.ex02.dao.ProductEntityDao;
import ch04.ex02.model.ProductEntity;

public class ProductEntityDaoImpl implements ProductEntityDao {

	SessionFactory factory;

	public ProductEntityDaoImpl() {
		this.factory = HibernateUtils.getSessionFactory();
	}

	@Override
	public Object save(ProductEntity product) {
		Session session = factory.getCurrentSession();
		session.save(product);
		return findById(product.getProductId());
	}

	@Override
	public ProductEntity findById(int id) {
		Session session = factory.getCurrentSession();
		return session.get(ProductEntity.class, id);
	}

	@Override
	public void update(ProductEntity e) {
		Session session = factory.getCurrentSession();
		session.update(e);
	}

	@Override
	public void delete(int id) {
		Session session = factory.getCurrentSession();
		String hql = "DELETE FROM ProductEntity WHERE productId = :id";
		session.createQuery(hql, ProductEntity.class)
		       .setParameter("id", id)
        	   .executeUpdate();
	}

	@Override
	public List<ProductEntity> findAll() {
		Session session = factory.getCurrentSession();
		String hql = "FROM ProductEntity";
		List<ProductEntity> productEntities = session.createQuery(hql, ProductEntity.class).getResultList();
		return productEntities;
	}

	@Override
	public void close() {
		factory.close();
	}

}
