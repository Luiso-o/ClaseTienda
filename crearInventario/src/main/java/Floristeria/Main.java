package Floristeria;

import model.Product;
import model.Ticket;

import java.util.ArrayList;
/*Luis-Miquel*/
public class Main {

    public static void main(String[] args) {

        Store store1 = new Store("Floristeria Amapola");

        //Trabajamos con el Stock y sales de store1

        /*He movido el arrayList que creas en el método getAllBBDD
        a la clase store, el ArrayList que se crea al instanciar el objeto
        store (stock) es el mismo que creas al momento de transformar el txt a objetos*/
        ArrayList<Product> stock = store1.getProducts();
        ArrayList<Ticket> sales = store1.getSales();

        /*aquí trabajamos con la tienda ya con el stock actualizado de acuerdo ala
        mercadería que tiene el archivo txt*/
        Menu.Options(stock,sales);


    }

}
