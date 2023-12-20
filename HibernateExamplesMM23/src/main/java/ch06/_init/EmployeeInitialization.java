package ch06._init;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ch00.HibernateUtils;
import ch06.model.Employee;
import ch06.model.EmployeeA;


public class EmployeeInitialization {
	// 本類別產生初始測試資料
	Session session;
	public EmployeeInitialization() { }
	
	//將Emp.txt的資料新增到Employee表格	
	public void insertIntoEmployee(){
		int count = 0 ;
		try (
			BufferedReader br = new BufferedReader(
			                      new InputStreamReader(
			            	        new FileInputStream("Emp.txt"),"UTF-8"));
		)
		{
			String line = null;
			Employee emp = null;
			//....
			
			while ( (line = br.readLine())!= null ){
				++count;
				//每讀入一橫列資料，將它分解開來，然後存到Employee表格內
				//下面的if敘述可去掉BOM字元
				if (line.startsWith(String.valueOf('\uFEFF')) ||
					line.startsWith(String.valueOf('\uFFFE'))	
					){
					System.out.println("第" + count +"行發現BOM字元");
					line = line.substring(1);
				}
				String[] sa = line.split("\\|");
				emp = new Employee(sa[0].trim(), 
						Integer.parseInt(sa[1].trim()),
						Date.valueOf(sa[2].trim()),
						Integer.parseInt(sa[3].trim())
						);
				saveEmployee(emp);				
			}
			System.out.println("Employee資料新增完畢，計" + count + "筆");
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	public void saveEmployee(Employee e){
		Transaction tx = null;
		session = HibernateUtils.getSessionFactory().openSession();
		try {
			tx = session.beginTransaction();
			session.save(e);
			tx.commit();			
		} catch(Exception ex){
			if (tx != null) tx.rollback();
			System.out.println(ex.getMessage());
		} finally {
			session.close();
		}
	}
	public static void main(String[] args) {
		EmployeeInitialization ea = new EmployeeInitialization();
		ea.insertIntoEmployee(); 
		ea.insertIntoEmployeeA();
		System.out.println("------------");
		ea.displayEmployee();
		System.out.println("------------");
		ea.displayEmployeeA();
		System.out.println("------------");
		// 關閉SessionFactory物件
     	HibernateUtils.close();
	}
	public List<Employee> displayEmployee(){
		session = HibernateUtils.getSessionFactory().openSession();
		String hql = "FROM Employee" ;
		List<Employee> list = session.createQuery(hql, Employee.class)
				                     .getResultList();
		session.close();	
		for(Employee e : list){
			System.out.printf("%2d %-6s %6d %11s %2d\n", e.getId(), e.getName(), e.getSalary(), e.getBirthday(), e.getDepId());
		}
		return list;		
	}
	public List<EmployeeA> displayEmployeeA(){
		session = HibernateUtils.getSessionFactory().openSession();
		String hql = "FROM EmployeeA" ;
		List<EmployeeA> list = session.createQuery(hql, EmployeeA.class)
				                      .getResultList();
		session.close();	
		for(EmployeeA e : list){
			System.out.printf("%2d %-6s %6d %11s %2d\n", e.getId(), e.getName(), e.getSalary(), e.getBirthday(), e.getDepId());
		}
		return list;		
	}
	//將EmpA.txt的資料新增到EmployeeA表格	
		public void insertIntoEmployeeA(){
			try (
				BufferedReader br = new BufferedReader(
				                      new InputStreamReader(
				            	        new FileInputStream("EmpA.txt"),"UTF-8"));
			)
			{
				String line = null;
				EmployeeA empA = null;
				while ( (line = br.readLine())!= null ){
					//每讀入一橫列資料，將它分解開來，然後存到Employee表格內
					//下面的if敘述可去掉BOM字元
					if (line.startsWith(String.valueOf('\uFEFF')) ||
						line.startsWith(String.valueOf('\uFFFE'))	
					){
						line = line.substring(1);
					}
					String[] sa = line.split("\\|");
					empA = new EmployeeA(sa[0].trim(), 
							Integer.parseInt(sa[1].trim()),
							Date.valueOf(sa[2].trim()),
							Integer.parseInt(sa[3].trim())
							);
					saveEmployeeA(empA);				
				}
			
			} catch(Exception e){
				e.printStackTrace();
			}
		}
	public void saveEmployeeA(EmployeeA e){
		Transaction tx = null;
		session = HibernateUtils.getSessionFactory().openSession();
		try {
			tx = session.beginTransaction();
			session.save(e);
			tx.commit();			
		} catch(Exception ex){
			if (tx != null) tx.rollback();
			ex.printStackTrace();
			System.out.println(ex.getMessage());
		} finally {
			session.close();
		}
	}
	public List<Object> query2(String hql){
		session = HibernateUtils.getSessionFactory().openSession();
		List<Object>  list = null;
	    list = session.createQuery(hql, Object.class)
	    		      .getResultList();
	    for(Object obj : list){
			System.out.printf("%6s \n", obj.toString());
		}
	    session.close();
		return list;
	}

}