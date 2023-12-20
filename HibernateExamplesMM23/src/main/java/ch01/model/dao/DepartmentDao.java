package ch01.model.dao;

import java.util.List;

import ch01.model.Department;

public interface DepartmentDao {
	// 經由Session介面的get()查詢資料庫內的紀錄
	Department findById(Integer id);

	Department findByName(String deptName);
	
	public void closeFactory();

	Object save(Department dept);

	List<Department> findAll();
}
