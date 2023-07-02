package model;

import services.IDDBBManager;

/*Luis-Miquel*/
public abstract class Product {
    private final int idProduct;
    public double price;


    public Product(double price, IDDBBManager managerDDBB) {
        this.idProduct = managerDDBB.nextIdBBDD();
        this.price = price;
    }

    public Product(int idProducto, double price){
        this.idProduct = idProducto;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
    public int getIdProduct() {
        return idProduct;
    }

    public abstract String ToString();
    public abstract String writeTXT();

}
