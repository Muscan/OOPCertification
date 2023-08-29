package repository;

import entity.*;
import enumeration.*;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ProductFileRepo {
    private final List<Product> products;
    private final String fileName;

    public ProductFileRepo(String fileName) {
        this.fileName = fileName;
        this.products = new ArrayList<>();
        this.read();
    }

    public void write() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.fileName))) {
            for (Product product : products) {
                bw.write(product.getId() + "," + product.getName() + "," +
                        product.getQuantity() + "," + product.getCategory() + "," +
                        product.getPrice() + "," + product.getProduceDate());
                bw.newLine();
            }
            System.out.println("Fisierul " + fileName + " a fost modificat :)");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addProduct(Product product) {
        product.setId(getFirstAvailableId());
        this.products.add(product);
        write();
    }

    public void deleteProduct(int id){
        try {
            boolean deleted = false;
            for(int i=0; i<products.size(); i++) {
                if(products.get(i).getId() == id){
                    products.remove(i);
                    deleted = true;
                    break;
                }
            }
            if(deleted){
                this.write();
            } else {
                System.out.println("Produsul cu id-ul " + id + " nu a fost gasit");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     *
     * @param updatedProduct - updates an object based on the new object's ID
     */
    public void update(Product updatedProduct){
        for(var product : this.products){//for each produc from reapo - one by one
            if(product.getId() == updatedProduct.getId()) {// if  CURRENT has the id egal
                //with the product we will make update ->
                //-> update all the fields
                product.setCategory(updatedProduct.getCategory());// product takes the category of updatedProduct
                product.setProduceDate(updatedProduct.getProduceDate());//product takes the date of updatedProduct
                product.setPrice(updatedProduct.getPrice());//product takes the name of updatedProduct
                product.setName(updatedProduct.getName());//product takes the name of updatedProduct
                product.setQuantity(updatedProduct.getQuantity());

                this.write();
                return;
            }
        }
        System.out.println("Object with id " + updatedProduct.getId() + " does not exists");
    }

    public void read() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try (BufferedReader br = new BufferedReader(new FileReader(this.fileName))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] args = line.split(",");
                this.products.add(new Product(
                        Integer.parseInt(args[0]),
                        args[1],
                        Integer.parseInt(args[2]),
                        ProductCategory.valueOf(args[3]),
                        Float.parseFloat(args[4]),
                        LocalDate.parse(args[5], dateFormatter)
                ));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //generate id for the products
    public int getFirstAvailableId(){
        products.sort(new SortById());
        int id = 1;
        for(Product product: products){
            if(product.getId() != id)
                return id;
            id++;
        }
        return id;
    }

    public void displayProducts(){
        for(Product product: products){
            System.out.println(product);
        }
    }

    public List<Product> getProducts(){
        return products;
    }
}

