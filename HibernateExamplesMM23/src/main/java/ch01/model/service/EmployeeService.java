package ch01.model.service;

import java.util.List;

import ch01.model.Employee;

public interface EmployeeService {
	// 新增一筆Employee物件到資料庫
	Object save(Employee emp);
	
	// 新增一筆Employee物件到資料庫
	void persist(Employee emp);
	
	// 經由Session介面的get()查詢資料庫內的紀錄
	Employee findById(Integer id);

	// 更新紀錄
	void update(Employee e);

	// 刪除紀錄
	void delete(Integer id);

	// 查詢所有紀錄
	List<Employee> findAll();

	void close();
}
