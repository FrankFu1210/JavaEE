package ch04.ex01.service;

import java.util.List;

import ch04.ex01.model.ProductEntity;

public interface ProductEntityService {

	// 新增一筆ProductEntity物件到資料庫
	ProductEntity save(ProductEntity emp);

	ProductEntity findById(int id);
	// 更新紀錄
	void update(ProductEntity e);

	// 刪除紀錄
	void delete(int id);

	// 查詢所有紀錄
	List<ProductEntity> findAll();

}