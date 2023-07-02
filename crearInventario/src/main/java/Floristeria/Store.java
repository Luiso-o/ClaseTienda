package Floristeria;

import model.Product;
import model.Ticket;
import services.IDDBBManager;
import services.txtManagerImpl;

import java.util.ArrayList;

/*Luis-Miquel*/

public class Store {

    private final String name;
    private final ArrayList<Product> products;
    private final ArrayList<Ticket> sales;

    public Store(String name) {
        IDDBBManager DDBBManager = new txtManagerImpl();
        this.name = name;
        products = DDBBManager.getAllBBDD();//creamos una arrayList de objetos con la información del textotxt
        sales = new ArrayList<>();
    }

    //Getters
    public ArrayList<Product> getProducts() {
        return products;
    }

    public ArrayList<Ticket> getSales() {
        return sales;
    }

}
