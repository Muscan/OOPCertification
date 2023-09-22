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

    // Constructorul clasei care primește numele fișierului și inițializează
    // lista de produse citind din fișier.
    public ProductFileRepo(String fileName) {
        this.fileName = fileName;
        this.products = new ArrayList<>();
        this.read();
    }

    // Metodă pentru scrierea produselor în fișier și afișarea mesajului corespunzător.
    public void write(String actionMessage) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.fileName))) {
            // Iterăm prin produse și le scriem în fișier
            for (Product product : products) {
                bw.write(product.getId() + "," + product.getName() + "," +
                        product.getQuantity() + "," + product.getCategory() + "," +
                        product.getPrice() + "," + product.getProduceDate());
                bw.newLine(); // Adăugăm o linie nouă după fiecare produs
            }
            System.out.println(actionMessage);

        } catch (IOException e) {
            e.printStackTrace(); // Tratăm erorile de scriere în fișier
        }
    }

    // Metodă pentru adăugarea unui produs în repo și apelul metodei write() pentru actualizarea fișierului.

    public void addProduct(Product product) {
        // Setăm ID-ul produsului adăugat utilizând metoda `getFirstAvailableId()`,
        // care returnează primul ID disponibil pentru produse.
        product.setId(getFirstAvailableId());

        // Adăugăm produsul în lista de produse a repo-ului.
        this.products.add(product);

        // Definim un mesaj de acțiune care indică că produsul a fost adăugat cu succes.
        String actionMessage = "Produsul a fost adăugat";

        // Apelăm metoda `write` pentru a actualiza fișierul cu informații despre produse,
        // și transmitem mesajul de acțiune pentru a specifica tipul acțiunii (în acest caz, adăugarea unui produs).
        write(actionMessage);
    }

    // Metodă pentru ștergerea unui produs din repo după ID și apelul metodei write() pentru actualizarea fișierului.
    public void deleteProduct(int id) {
        try {
            boolean deleted = false; // Inițializăm o variabilă pentru a urmări dacă produsul a fost șters
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getId() == id) { // Verificăm dacă ID-ul produsului curent corespunde cu ID-ul dat
                    products.remove(i); // Ștergem produsul din listă
                    deleted = true; // Setăm "deleted" la true pentru a indica că produsul a fost șters
                    break; // Ieșim din buclă deoarece am găsit și șters produsul
                }
            }
            String actionMessage = deleted ? "Produsul a fost șters" : "Produsul cu id-ul " + id + " nu a fost găsit";
            write(actionMessage);
        } catch (Exception e) {
            e.printStackTrace(); // Tratăm erorile care pot apărea în timpul procesului
        }
    }

    // Metodă pentru actualizarea unui produs în repo și apelul metodei write() pentru actualizarea fișierului.
    public void update(Product updatedProduct) {
        for (var product : this.products) {
            if (product.getId() == updatedProduct.getId()) { // Verificăm dacă produsul cu ID-ul dat există
                // Actualizăm toate câmpurile produsului existent cu datele din obiectul `updatedProduct`
                product.setCategory(updatedProduct.getCategory());
                product.setProduceDate(updatedProduct.getProduceDate());
                product.setPrice(updatedProduct.getPrice());
                product.setName(updatedProduct.getName());
                product.setQuantity(updatedProduct.getQuantity());

                String actionMessage = "Produsul a fost editat";
                write(actionMessage);
                return; // Ieșim din buclă deoarece am actualizat produsul
            }
        }
        // Dacă nu am găsit produsul, afișăm un mesaj de avertizare
        String actionMessage = "Obiectul cu id-ul " + updatedProduct.getId() + " nu există";
        write(actionMessage);
    }

    // Metodă pentru citirea produselor din fișier și adăugarea lor în lista de produse.
    /**
     * Metodă pentru citirea datelor despre produse dintr-un fișier și adăugarea acestora în lista de produse.
     * Utilizează un șablon de formatare pentru data de producție și gestionează eventuale erori de citire din fișier.
     */
    public void read() {
        // Definim un șablon de formatare pentru data de producție (formatul "yyyy-MM-dd")
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try (BufferedReader br = new BufferedReader(new FileReader(this.fileName))) {
            String line;

            // Iterăm prin fiecare linie din fișier
            while ((line = br.readLine()) != null) {
                String[] args = line.split(","); // Separăm valorile din linie folosind
                // virgula ca separator

                // Creăm un obiect Product utilizând valorile din linia citită din fișier,
                // și le adăugăm în lista de produse
                this.products.add(new Product(
                        Integer.parseInt(args[0]), // ID
                        args[1], // Nume
                        Integer.parseInt(args[2]), // Cantitate
                        ProductCategory.valueOf(args[3]), // Categorie
                        Float.parseFloat(args[4]), // Preț
                        LocalDate.parse(args[5], dateFormatter) // Data de producție
                ));
            }
        } catch (IOException e) {
            e.printStackTrace(); // Tratăm erorile de citire din fișier prin afișarea stivei
            // de urmărire a excepției
        }
    }

    // Metodă pentru generarea unui ID disponibil pentru produse.
    /**
     * Metodă pentru generarea unui ID disponibil pentru produse.
     * Sortează lista de produse în ordine crescătoare după ID,
     * apoi identifică primul ID disponibil care nu este folosit de niciun produs.
     *
     * @return ID-ul disponibil
     */
    public int getFirstAvailableId() {
        products.sort(new SortById()); // Sortăm lista de produse în ordine crescătoare după ID

        int id = 1; // ID-ul inițial este 1
        for (Product product : products) { // Iterăm prin produse
            if (product.getId() != id) // Dacă ID-ul curent nu este egal cu ID-ul așteptat
                return id; // Returnăm ID-ul disponibil
            id++; // Altfel, incrementăm ID-ul așteptat
        }
        return id; // Dacă nu există niciun ID disponibil mai mic decât cel mai mare existent,
        // returnăm următorul ID disponibil
    }

    // Metodă pentru afișarea tuturor produselor din repo.
    public void displayProducts() {
        for (Product product : products) {
            System.out.println(product);
        }
    }

    // Metodă pentru returnarea listei de produse.
    public List<Product> getProducts() {
        return products;
    }
}
