package user_interface;

import entity.Product;
import enumeration.ProductCategory;
import repository.ProductFileRepo;

import java.time.LocalDate;
import java.util.*;

// Definim clasa `UI`, care implementează interfața `I_UI`.
public class UI implements I_UI {

    // Declaram o variabilă privată `fileRepo`, care reprezintă repo-ul (repositoriul) de produse
    // utilizat pentru operațiile CRUD (Create, Read, Update, Delete) asupra produselor.
    private ProductFileRepo fileRepo;

    // Constructorul clasei `UI` care primește un obiect de tip `ProductFileRepo` ca argument.
    public UI(ProductFileRepo fr) {
        this.fileRepo = fr; // Inițializăm variabila `fileRepo` cu obiectul primit ca argument.
    }

    // Metodă pentru afișarea categoriilor disponibile de produse.
    public void displayProductCategories() {
        // Afișăm în consolă un mesaj care indică că urmează să se afișeze categoriile disponibile.
        System.out.println("Available categories:");

        // Afișăm în consolă lista categoriilor de produse disponibile, fiecare pe o linie separată.
        System.out.println("FRUITS");
        System.out.println("VEGETABLES");
        System.out.println("SWEETS");
        System.out.println("TOOLS");
        System.out.println("FURNITURE");
    }


    public String readProductCategory() {
        // declarare lista cu cele 5 categorii
        // citit de la tastatura categoria
        // daca categoria nu se afla in lista, recistesti categorie si reverifici
        // practic vei fi intr-un while pana citesti calumea

        List<String> categories = new ArrayList<>() {
            {
                add("FRUITS");
                add("VEGETABLES");
                add("SWEETS");
                add("TOOLS");
                add("FURNITURE");
            }
        };
        Scanner scanner = new Scanner(System.in);
        System.out.print("Insert category: ");
        String category = scanner.nextLine();
        boolean found = false;
        for(String c : categories) {
            if(c.equalsIgnoreCase(category)){
                found = true;
            }
        }
        while(!found){
            System.out.print("Reinsert cateogry: ");
            category = scanner.nextLine();
            for(String c : categories) {
                if(c.equalsIgnoreCase(category)){
                    found = true;
                }
            }
        }
        return category;
    }

    public int readInt(String message){
        Scanner scanner = new Scanner(System.in);
        while(true){
            try{
                System.out.print(message);
                return scanner.nextInt();
            }
            catch(InputMismatchException e){
                System.out.println("You need to read a number!");
                scanner.nextLine();
            }
        }
    }
    @Override
    public Product addProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("*********** Add product functionality ***********");
        System.out.print("Insert name: ");
        String name = scanner.nextLine();
        int quantity = readInt("Insert quantity: ");
        displayProductCategories();
        String categoryName = readProductCategory();
        ProductCategory category = ProductCategory.valueOf(categoryName.toUpperCase());
        System.out.print("Insert price: ");
        float price = scanner.nextFloat();
        scanner.nextLine();
        System.out.print("Insert date (yyyy-MM-dd): ");
        String date = scanner.nextLine();
        LocalDate productdate = LocalDate.parse(date);
        return new Product(name,quantity,category,price,productdate);
    }

    @Override
    public Product editProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("*********** Edit product functionality ***********");
        int id = readInt("Insert id: ");
        System.out.print("Insert name: ");
        scanner.nextLine();
        String name = scanner.nextLine();
        int quantity = readInt("Insert quantity: ");
        displayProductCategories();
        String categoryName = readProductCategory();
        ProductCategory category = ProductCategory.valueOf(categoryName.toUpperCase());
        System.out.print("Insert price: ");
        float price = scanner.nextFloat();
        scanner.nextLine();
        System.out.print("Insert date (yyyy-MM-dd): ");
        String date = scanner.nextLine();
        LocalDate productDate = LocalDate.parse(date);
        return new Product(id, name, quantity, category, price, productDate);
    }

    @Override
    public int deleteProduct() {
        Scanner scanner = new Scanner(System.in);
        this.fileRepo.displayProducts();
        System.out.print("Insert product id: ");
        return scanner.nextInt();
    }

    public void displayProductMenu()
    {
        System.out.println("*********** Product Menu ***********");
        System.out.println("1. Add product");
        System.out.println("2. Show products");
        System.out.println("3. Edit product");
        System.out.println("4. Delete product");
        System.out.println("0. Back to main menu");
    }
    public void productMenu()
    {
        Scanner scanner = new Scanner(System.in);
        boolean end = false;
        while(!end){
            displayProductMenu();
            System.out.print("Insert option: ");
            int option = scanner.nextInt();

            switch(option)
            {
                case(1) -> {
                    Product product = addProduct();
                    this.fileRepo.addProduct(product);
                }
                case(2) -> {
                    this.fileRepo.displayProducts();
                }
                case(3) -> {
                    Product product = editProduct();
                    this.fileRepo.update(product);
                }
                case(4) -> {
                    int id = deleteProduct();
                    this.fileRepo.deleteProduct(id);
                }
                case(0) -> {
                    System.out.println("Back to main menu...");
                    end = true;
                }
            }
        }
    }

    public void displayMainMenu(){
        System.out.println("*********** Main Menu ***********");
        System.out.println("1. Product menu");
        System.out.println("0. Exit");
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean end = false;
        while(!end){
            displayMainMenu();
            System.out.print("Insert option: ");
            int option = scanner.nextInt();

            switch(option)
            {
                case(1) -> {
                    productMenu();
                }
                case(0) -> {
                    System.out.println("Exit of application...");
                    end = true;
                }
            }
        }
    }
}
