package management;

import java.util.List;
import java.util.Random;

import model.Produkt;
import model.Category;
import dao.ProduktDAO;
import dao.DBProduktDAO;

public class Produktmanagement {
	private static Produktmanagement produktmanagementInstance=null;
	private ProduktDAO dao;
	
	private Produktmanagement() {
		dao = new DBProduktmanagementDAO();
	}
    
    public static Produktmanagement getInstance(){
		if(produktmanagementInstance == null) produktmanagementInstance = new Produktmanagement();
		return produktmanagementInstance;
	}
    
    
    public boolean produktAnlegen(int prodID, String prodName, double price, String prodDescription, int categoryID) {
        Random randomGenerator = new Random();
		int id = randomGenerator.nextInt(Integer.MAX_VALUE);
		System.out.println("Produktmanagement:produktAnlegen: "+prodID+", "+prodName+", "+price+", "+prodDescription+", "+categoryID+", anlegen!");
		return dao.speichereProdukt(new Produkt(prodID, prodName, price, prodDescription, categoryID));
    }
  
    public boolean categoryAnlegen(int categoryID, String categoryName, String categoryDescription) {
        Random randomGenerator = new Random();
		int id = randomGenerator.nextInt(Integer.MAX_VALUE);
		System.out.println("Produktmanagement:categoryAnlegen: "+categoryID+", "+categoryName+", "+categoryDescription+", anlegen!");
		return dao.speichereCategory(new Category(categoryID, categoryName, categoryDescription));
    }
    
    
    public List<Produkt> getAlleProdukt(){
		return dao.getProduktList();
	}
	public List<Category> getAlleCategory(){
		return dao.getCategoryList();
	}
    
    
    public Produkt getProduktByProduktID(int prodID){
		return dao.getProduktByProduktID(prodID);
	}
	public Category getCategorybyCategoryID(int categoryID){
		return dao.getCategoryByCategoryID(categoryID);
	}
    
    
    public boolean loescheProdukt(int prodID){
		return dao.loescheProduktByProdID(prodID);
	}
	public boolean loescheCategory(int categoryID){
		return dao.loescheCategoryByCategoryID(categoryID);
	}
    
}
