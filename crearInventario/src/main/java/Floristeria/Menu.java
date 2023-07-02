package Floristeria;

import model.*;
import services.IDDBBManager;
import services.txtManagerImpl;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
/*Luis-Miquel*/
public class Menu {

    static IDDBBManager DDBBManager = new txtManagerImpl();
    //pido como parámetro el array de productos de la clase rienda
    public static void Options(ArrayList<Product>stock, ArrayList<Ticket>sales){

        int option;

        do {
            String choose= JOptionPane.showInputDialog("""
                    Elige una opción:\s
                                    
                    0-Salir.
                    1-Crear Floristeria.
                    2-Agregar un Árbol.
                    3-Agregar una Flor.
                    4-Agregar una Decoración.
                    5-Imprimir Stock.
                    6-Retirar Árbol.
                    7-Retirar Flor.
                    8-Retirar Decoración.
                    9-Stock de Cantidades.
                    10-Valor total de Floristería.
                    11-Crear ticket de compra.
                    12-Mostrar lista de compras.
                    13-Mostrar Ganancias.""");

            option = Integer.parseInt(choose);
            RunMenu(option, stock, sales);

        }while (option != 0);
    }

    public static void RunMenu(int option, ArrayList<Product>stock, ArrayList<Ticket>sales){

        switch (option) {
            case 0 -> JOptionPane.showMessageDialog(null, "Hasta Pronto :)");
            case 1 -> NewFlorist();
            case 2 -> NewTree(stock);
            case 3 -> NewFlower(stock);
            case 4 -> NewDecor(stock);
            case 5 -> printStock(stock);
            case 6 -> RemoveTree(stock);
            case 7 -> RemoveFlower(stock);
            case 8 -> RemoveDecor(stock);
            case 9 -> PrintQuantity(stock);
            case 10 -> PrintValueFlorist(stock);
            case 11 -> NewTicket(stock, sales);
            case 12 -> PrintTickets(sales);
            case 13 -> Profits(sales);
            default -> JOptionPane.showMessageDialog(null, "Opcion no encontrada");
        }

    }

    //Métodos menu---------------------------------------->>

    //crear floristeria
    public static void NewFlorist(){
        String name = JOptionPane.showInputDialog("Introduce el nombre de la nueva Floristería");
        new Store(name);
        JOptionPane.showMessageDialog(null, "Tienda " + name + " Creada correctamente :)");
    }

    //Agregar arbol
    public static void NewTree(ArrayList<Product>stock){

        String height = JOptionPane.showInputDialog("Introduce su altura");
        double valueHeight = Double.parseDouble(height);

        String price = JOptionPane.showInputDialog("Introduce su precio");
        double valuePrice = Double.parseDouble(price);

        Tree tree = new Tree(valueHeight, DDBBManager,valuePrice);
        stock.add(tree);
        DDBBManager.addProduct(new ArrayList<>(List.of(tree)));

        JOptionPane.showMessageDialog(null, "Árbol agregado correctamente al Stock ;)");

    }

    //Agregar Flor
    public static void NewFlower(ArrayList<Product>stock){

        String color = JOptionPane.showInputDialog("Introduce su Color");

        String price = JOptionPane.showInputDialog("Introduce su precio");
        double valuePrice = Double.parseDouble(price);

        Flower flower = new Flower(valuePrice,DDBBManager, color );
        stock.add(flower);
        DDBBManager.addProduct(new ArrayList<>(List.of(flower)));

        JOptionPane.showMessageDialog(null, "Flor ingresada correctamente al Stock ;)");

    }

    //Agregar Decoracion
    public static void NewDecor(ArrayList<Product>stock){

        String material = JOptionPane.showInputDialog("Introduce su Material");

        String price = JOptionPane.showInputDialog("Introduce su precio");
        double valuePrice = Double.parseDouble(price);

        Decor decoration = new Decor(valuePrice, DDBBManager, material);
        stock.add(decoration);
        DDBBManager.addProduct(new ArrayList<>(List.of(decoration)));

        JOptionPane.showMessageDialog(null, "Decoración agregada correctamente al Stock ;)");

    }

    //imprimir Stock
     public static void printStock(ArrayList<Product>stock){
        //recorrer stock con metodo foreach y lambda
         stock.stream().map(Product::ToString).forEach(System.out::println);

     }

     //retirar Arbol
    public static void RemoveTree(ArrayList<Product>stock){

        //imprimo por consola la lista de árboles que tenemos en stock
        stock.stream().filter(product -> product instanceof Tree).map(Product::ToString).forEach(System.out::println);

        //pido al usuario que ingrese el índice del arbol que desee
        String Text = JOptionPane.showInputDialog("Introduce el índice del árbol que deseas remover:");
        int number = Integer.parseInt(Text);

        //Verificamos el indice del árbol dentro del stock
        int index = SearchIndex(number,stock);

        //Lógica para remover árbol del arrayList
        if(index == -1){
            JOptionPane.showMessageDialog(null,"No hemos encontrado el índice de este árbol :(");
        }else{
            stock.remove(index);
            DDBBManager.updateDDBB(stock);
            JOptionPane.showMessageDialog(null,"Árbol retirado correctamente :D");
        }

    }

    //Retirar flor
    public static void RemoveFlower(ArrayList<Product>stock){

        //imprimo por consola la lista de flores que tenemos en stock
        stock.stream().filter(product -> product instanceof Flower).map(Product::ToString).forEach(System.out::println);

        //pido al usuario que ingrese el índice de la flor que desee
        String Text = JOptionPane.showInputDialog("Introduce el índice de la flor que deseas remover:");
        int number = Integer.parseInt(Text);

        //Verificamos el índice de la flor dentro del stock
        int index = SearchIndex(number,stock);

        //Lógica para remover la flor del arrayList
        if(index == -1){
            JOptionPane.showMessageDialog(null,"No hemos encontrado el índice de esta flor :(");
        }else{
            stock.remove(index);
            DDBBManager.updateDDBB(stock);
            JOptionPane.showMessageDialog(null,"Flor retirada correctamente :D");
        }


    }

    //Retirar Decoración
    public static void RemoveDecor(ArrayList<Product>stock){

        //imprimo por consola la lista de decoraciones que tenemos en stock
        stock.stream().filter(product -> product instanceof Decor).map(Product::ToString).forEach(System.out::println);

        //pido al usuario que ingrese el índice de la decoración que desee
        String Text = JOptionPane.showInputDialog("Introduce el índice de la decoración que deseas remover:");
        int number = Integer.parseInt(Text);

        //Verificamos el índice de la decoración dentro del stock
        int index = SearchIndex(number,stock);

        //Lógica para remover la decoración del arrayList
        if(index == -1){
            JOptionPane.showMessageDialog(null,"No hemos encontrado el índice de esta decoración :(");
        }else{
            stock.remove(index);
            DDBBManager.updateDDBB(stock);
            JOptionPane.showMessageDialog(null,"Decoración retirada correctamente :D");
        }


    }

    //imprimir cantidad de stock
    public static void PrintQuantity(ArrayList<Product> stock){

        int treeCounter = 0;
        int flowerCounter = 0;
        int decorCounter = 0;

        for (Product product : stock) {
            if (product instanceof Tree) {
                treeCounter++;
            } else if (product instanceof Flower) {
                flowerCounter++;
            } else if (product instanceof Decor) {
                decorCounter++;
            }
        }

        System.out.println("En el stock tenemos: \n\n" +
                "\tÁrboles " + treeCounter + " unidades\n" +
                "\tFlores " + flowerCounter + " unidades\n" +
                "\tDecoraciones " + decorCounter + " unidades.");

    }

    //Imprimir Valor total de floristeria
    public static void PrintValueFlorist(ArrayList<Product>stock){

        int ValueFlorist = 0;

        for (Product product : stock) {
            ValueFlorist += product.getPrice();
        }

        System.out.println("El valor total del stock en la floristeria es de: " + ValueFlorist + " Euros.");

    }

    //Crear Ticket con multiples productos

    public static void NewTicket(ArrayList<Product>stock ,ArrayList<Ticket>sales){

        //creamos un objeto ticket
        Ticket ticket = new Ticket();

        //pedimos al usuario la cantidad de productos que desea agregar al ticket
        String text = JOptionPane.showInputDialog("Introduzca la cantidad de productos que desea agregar");
        int number = Integer.parseInt(text);

        //lógica para crear ticket productos
        for (int i = 0; i < number; i++) {
            String text1 = JOptionPane.showInputDialog("Introduzca el tipo del producto (arbol, flor, decoracion)" + (i+1));

            switch (text1.toLowerCase()) {
                case "arbol" -> {
                    //imprimo por consola la lista de árboles que tenemos en stock
                    stock.stream().filter(product -> product instanceof Tree).map(Product::ToString).forEach(System.out::println);

                    //pido al usuario que ingrese el índice del arbol que desee
                    String Text = JOptionPane.showInputDialog("Introduce el índice del árbol que deseas comprar:");
                    int number1 = Integer.parseInt(Text);

                    //Verificamos el indice del árbol dentro del stock
                    int index = SearchIndex(number1, stock);

                    //Lógica para remover árbol del arrayList
                    if (index == -1) {
                        JOptionPane.showMessageDialog(null, "No hemos encontrado el índice de este árbol :(");
                    } else {
                        //Agrego el producto a la lista de ventas del ticket creado
                        ticket.getProducts().add(stock.get(index));
                        //elimino el producto del stock
                        stock.remove(index);
                        //aviso que el proceso ocurrió con exito
                        JOptionPane.showMessageDialog(null, "Árbol agregado al ticket de compra :D");
                    }
                }
                case "flor" -> {
                    //imprimo por consola la lista de flores que tenemos en stock
                    stock.stream().filter(product -> product instanceof Flower).map(Product::ToString).forEach(System.out::println);

                    //pido al usuario que ingrese el índice de la flor que desee
                    String Text1 = JOptionPane.showInputDialog("Introduce el índice de la flor que deseas comprar:");
                    int number2 = Integer.parseInt(Text1);

                    //Verificamos el índice de la flor dentro del stock
                    int index1 = SearchIndex(number2, stock);

                    //Lógica para remover flor del arrayList
                    if (index1 == -1) {
                        JOptionPane.showMessageDialog(null, "No hemos encontrado el índice de esta flor :(");
                    } else {
                        //Agrego el producto a la lista de ventas del ticket creado
                        ticket.getProducts().add(stock.get(index1));
                        //elimino el producto del stock
                        stock.remove(index1);
                        //aviso que el proceso ocurrió con exito
                        JOptionPane.showMessageDialog(null, "Flor agregada al ticket de compra :D");
                    }
                }
                case "decoracion" -> {
                    //imprimo por consola la lista de decoraciones que tenemos en stock
                    stock.stream().filter(product -> product instanceof Decor).map(Product::ToString).forEach(System.out::println);

                    //pido al usuario que ingrese el índice de la decoración que desee
                    String Text2 = JOptionPane.showInputDialog("Introduce el índice de la decoración que deseas comprar:");
                    int number3 = Integer.parseInt(Text2);

                    //Verificamos el índice de la decoración dentro del stock
                    int index2 = SearchIndex(number3, stock);

                    //Lógica para remover decoración del arrayList
                    if (index2 == -1) {
                        JOptionPane.showMessageDialog(null, "No hemos encontrado el índice de esta flor :(");
                    } else {
                        //Agrego el producto a la lista de ventas del ticket creado
                        ticket.getProducts().add(stock.get(index2));
                        //elimino el producto del stock
                        stock.remove(index2);
                        //aviso que el proceso ocurrió con exito
                        JOptionPane.showMessageDialog(null, "Flor agregada al ticket de compra :D");
                    }
                }
                default -> JOptionPane.showMessageDialog(null, "Opcion no encontrada.");
            }
            DDBBManager.updateDDBB(stock);

        }

        //agregamos el tiquete al inventario de ventas de la tienda
        sales.add(ticket);
        JOptionPane.showMessageDialog(null, "Ticket generado exitosamente :D");

    }

    //Mostar Lista de tiquetes antiguos
    public static void PrintTickets(ArrayList<Ticket>sales){

        for (Ticket sale : sales) {
            System.out.println("Ticket " + sale.getIdTicket() + " :\n");

            for (int j = 0; j < sale.getProducts().size(); j++) {
                if (sale.getProducts().get(j) instanceof Tree) {
                    System.out.println(sale.getProducts().get(j).ToString());
                } else if (sale.getProducts().get(j) instanceof Flower) {
                    System.out.println(sale.getProducts().get(j).ToString());
                } else if (sale.getProducts().get(j) instanceof Decor) {
                    System.out.println(sale.getProducts().get(j).ToString());
                }
            }
        }

    }

    //total de dinero ganado en ventas
    public static void Profits(ArrayList<Ticket>sales){

        int sumProfit = 0;

        for (Ticket sale : sales) {
            for (int j = 0; j < sale.getProducts().size(); j++) {
                sumProfit += sale.getProducts().get(j).getPrice();
            }
        }

        System.out.println("El total de ganancias obtenidos" +
                " por la suma de los tickets de venta es de " + sumProfit + " euros.");

    }

    //Método para encontrar índices
    public static int SearchIndex(int number, ArrayList<Product>stock){

        int index = -1;
        int counter = 0;

        while(counter < stock.size() && index == -1){

            if(stock.get(counter).getIdProduct() == number){
                index = counter;
            }
            counter++;
        }
        return index;

    }
}
