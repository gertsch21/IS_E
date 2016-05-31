package dao;

import java.util.List;

import model.Produkt;
import model.Category;

public interface ProduktDAO {
	public boolean speichereProdukt(Produkt p);
	public boolean speichereCategory(Category c);
	
	public List<Produkt> getProduktList();
	public List<Category> getCategoryList();
	public Produkt getProduktByProduktID(int prodID);
	public Category getCategoryByCategoryID(int categoryID);
	
    
	public boolean loescheProduktByProdID(prodID);
	public boolean loescheCategoryByCategoryID(categoryID);
	
	Produkt getProduktByProduktName(String prodName);
}
