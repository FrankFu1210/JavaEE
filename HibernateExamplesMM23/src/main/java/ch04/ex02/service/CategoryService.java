package ch04.ex02.service;

import java.util.List;

import ch04.ex02.model.Category;

public interface CategoryService {

	Category findById(int id);

	Category findByName(String categoryName);
	
	public void closeFactory();

	Object save(Category category);

	List<Category> findAll();
}
