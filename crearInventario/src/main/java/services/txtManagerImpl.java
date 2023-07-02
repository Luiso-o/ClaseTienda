package services;

import model.Decor;
import model.Flower;
import model.Product;
import model.Tree;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
/*Miquel*/
public class txtManagerImpl implements IDDBBManager{

    static private final String PATH_TXT_DDBB ="crearInventario"+File.separator+"src"+File.separator+"main"+File.separator+"java"+File.separator+"resources"+File.separator+"txtDDBB.txt";
    static private final String PATH_TXT_maxID = "crearInventario"+File.separator+"src"+File.separator+"main"+File.separator+"java"+File.separator+"resources"+File.separator+"maxID.txt";

    @Deprecated
    public int calculateDDBBMaxID(){
        int currentMaxId = 0;
        FileReader fileReader = null;
        BufferedReader bufferReader = null;

        try{
            fileReader = new FileReader(PATH_TXT_DDBB);
            bufferReader = new BufferedReader(fileReader);
            while(bufferReader.readLine() != null){
            }
        }catch (FileNotFoundException fnfe){
            System.out.println("There is no such file");
        } catch (IOException e) {
            System.out.println("Somethig happend with the file");
        }catch (ArrayIndexOutOfBoundsException | NumberFormatException siobe){
            System.out.println("The are extra blank lines in the file");
        } finally {
            try {
                assert fileReader != null;
                fileReader.close();
                assert bufferReader != null;
                bufferReader.close();
            } catch (IOException ignored) {
            }
        }
        return currentMaxId;
    }

    public void overrideMaxIDD(int id) {
        try (FileWriter fileWriterID = new FileWriter(PATH_TXT_maxID)) {
            fileWriterID.write(id + "\n");
        } catch (FileNotFoundException fnfe) {
            System.out.println("There is no such file");
        } catch (IOException e) {
            System.out.println("Something happened with the file: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException siobe) {
            System.out.println("There are extra blank lines in the file");
        }
    }


    //Este metodo solo se puede utilizar en el constructor de Producto
    public int nextIdBBDD() {
        int allTimeMaxId = 0;
        FileReader fileReader = null;
        BufferedReader bufferReader = null;

        try {
            System.out.println("File path: " + PATH_TXT_maxID);
            fileReader = new FileReader(PATH_TXT_maxID);
            bufferReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferReader.readLine()) != null) {
                String[] parts = line.split(" ");
                allTimeMaxId = Integer.parseInt(parts[0]);
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("There is no such file");
        } catch (IOException e) {
            System.out.println("Something happened with the file");
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException siobe) {
            System.out.println("There are extra blank lines in the file");
        } finally {
            try {
                if (fileReader != null) {
                    fileReader.close();
                }
                if (bufferReader != null) {
                    bufferReader.close();
                }
            } catch (IOException ignored) {
            }
        }
        overrideMaxIDD(++allTimeMaxId);
        return allTimeMaxId;
    }

    @Override
    public ArrayList<Product> getAllBBDD() {
        ArrayList<Product> newStock = new ArrayList<>();
        try (FileReader fileReader = new FileReader(PATH_TXT_DDBB);
             BufferedReader bufferReader = new BufferedReader(fileReader)) {

            String line;
            while ((line = bufferReader.readLine()) != null) {
                line = line.trim(); // Eliminar espacios en blanco al inicio y final de la línea
                if (!line.isEmpty()) { // Ignorar líneas vacías
                    String[] parts = line.split(" ");
                    int id = Integer.parseInt(parts[0]);
                    String productType = parts[1];
                    double price = Double.parseDouble(parts[2]);
                    String attribute = parts[3];

                    switch (productType) {
                        case "Tree" -> {
                            double height = Double.parseDouble(attribute);
                            Tree arbol = new Tree(id, price, height);
                            newStock.add(arbol);
                        }
                        case "Flower" -> {
                            Flower flor = new Flower(id, price, attribute);
                            newStock.add(flor);
                        }
                        case "Decoration" -> {
                            Decor adorno = new Decor(id, price, attribute);
                            newStock.add(adorno);
                        }
                    }
                }
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("There is no such file");
        } catch (IOException e) {
            System.out.println("Something happened with the file");
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException siobe) {
            System.out.println("There are extra blank lines in the file");
        }
        return newStock;
    }

    @Override
    public void updateDDBB(List<Product> products){
        try (FileWriter fileWriter = new FileWriter(PATH_TXT_DDBB)) {
            for (Product product : products) {
                fileWriter.write(product.writeTXT() + "\n");
            }
        } catch (IOException e) {
            System.out.println("There is no such File add Products");
        }
    }

    @Override
    public void addProduct(List<Product> products) {
        try (FileWriter fileWriter = new FileWriter(PATH_TXT_DDBB, true)) {
            for (Product product : products) {
                fileWriter.write(product.writeTXT() + "\n");
            }
        } catch (IOException e) {
            System.out.println("There is no such File add Products");
        }
    }

    @Override
    public void removeProduct(List<Integer> idProducts) {
    }
}
