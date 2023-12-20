package ch04.ex01.service;

import java.util.List;

import ch04.ex01.model.Category;

public interface CategoryService {

	Category findById(int id);

	Category findByName(String categoryName);
	
	Object save(Category category);

	List<Category> findAll();
}
