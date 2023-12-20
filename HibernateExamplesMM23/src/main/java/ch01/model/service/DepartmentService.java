package ch01.model.service;

import java.util.List;

import ch01.model.Department;

public interface DepartmentService {
	// 經由Session介面的get()查詢資料庫內的紀錄
	Department findById(Integer id);
	Department findByIdWithoutLazyLoading(Integer id);
	List<Department> findAll();
	Object save(Department dept);
	public void closeFactory();
}
