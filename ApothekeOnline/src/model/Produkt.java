package model;

public class Produkt {
    private int prodID;
    private String prodName;
    private double price;
    private String prodDescription;
    private int categoryID;
    
    
    public Produkt(int prodID, String prodName, double price, String prodDescription, int categoryID) {
        this.prodID = prodID;
        this.prodName = prodName;
        this.price = price;
        this.prodDescription = prodDescription;
        this.categoryID = categoryID;
    }
    
//getters

    public int getprodID() {
        return prodID;
    }
    public String getprodName(){
        return prodName;
    }
    public String getprice(){
        return price;
    }
    public String getprodDescription(){
        return prodDescription;
    }
    public String getcategoryID(){
        return categoryID;
    }
    
//setters    
    
    public void setprodID() {
        this.prodID = prodID;
    }
    public void setprodName(){
        this.prodName = prodName;
    }
    public void setprice(){
        this.price = price;
    }
    public void setprodDescription(){
        this.prodDescription = prodDescription;
    }
    public void setcategoryID(){
        this.categoryID = categoryID;
    }
}