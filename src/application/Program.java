package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		System.out.println("=== Test findById: Seller ===");
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);
		
		System.out.println("\n=== Test findByDepartment: Seller ===");
		Department department = new Department(2, null);
		List<Seller> list = sellerDao.findByDepartment(department);
		for(Seller obj : list) {
			System.out.println(obj);
		}
		
		System.out.println("\n=== Test findAll: Seller ===");
		list = sellerDao.findAll();
		for(Seller obj : list) {
			System.out.println(obj);
		}
		
		System.out.println("\n==== Test Insert: Seller ===");
		Seller newSeller = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.0, new Department(2, null));
		sellerDao.insert(newSeller);
		System.out.println("OKAy "+ newSeller.getId());
		
		System.out.println("\n==== Test Update: Seller ===");
		seller = sellerDao.findById(8);
		seller.setName("Camila Miranda");
		sellerDao.update(seller);
		System.out.println("Update done!");
		
		System.out.println("\n==== Test Delete: Seller ===");
		System.out.println("Enter id :");
		int id = sc.nextInt();
		sellerDao.deleteById(id);
		System.out.println("Deleted!");
		sc.close();

	}

}
