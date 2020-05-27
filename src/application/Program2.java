package application;

import java.util.ArrayList;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {
		
		DepartmentDao departmentDao = DaoFactory.creatDepartmentDao();
		
		System.out.println("===Find byId: Department===");
		Department department = departmentDao.findById(2);
		System.out.println(department);
		
		System.out.println("\n===Find All: Department");
		List <Department> list = new ArrayList<>();
		list=departmentDao.findAll();
		for(Department obj : list) {
			
			System.out.println(obj);
		}
		
		System.out.println("\n===Insert: Department===");
		Department dp = new Department(null, "Design");
		departmentDao.insert(dp);
		System.out.println("Insert Done!");
		
		System.out.println("\n===Update: Department");
		department = departmentDao.findById(8);
		department.setName("Business");
		departmentDao.update(department);
		System.out.println("Update Done!");
		
		System.out.println("\n===Delete: Department===");
		departmentDao.deleteById(9);
		System.out.println("Delete done!");
	}

}
