package ch04.ex02.dao;

import java.util.List;

import ch04.ex02.model.ProductEntity;

public interface ProductEntityDao {

	// 新增一筆ProductEntity物件到資料庫
	Object save(ProductEntity emp);

	ProductEntity findById(int id);
	// 更新紀錄
	void update(ProductEntity e);

	// 刪除紀錄
	void delete(int id);

	// 查詢所有紀錄
	List<ProductEntity> findAll();

	void close();


}