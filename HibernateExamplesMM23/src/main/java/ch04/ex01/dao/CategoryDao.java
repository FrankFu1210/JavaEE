package ch04.ex01.dao;

import java.util.List;

import ch04.ex01.model.Category;

public interface CategoryDao {

	Category findById(Integer id);

	Category findByName(String categoryName);
	
	Category save(Category category);

	List<Category> findAll();
}
