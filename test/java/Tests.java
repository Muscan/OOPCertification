import entity.Product;
import enumeration.ProductCategory;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.ProductFileRepo;
import user_interface.UI;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;
public class Tests {
    // Variabilă statică pentru obiectul ProductFileRepo
    // ceea ce înseamnă că pot fi accesate direct din clasa în care sunt declarate,
    // fără a fi necesară crearea unei instanțe a clasei respective.
    // Ele sunt folosite în testele unitare pentru a reține obiectele ProductFileRepo și UI care
    // vor fi utilizate în cadrul testelor. Variabilele sunt declarate static pentru a putea
    // fi partajate între mai multe metode de testare
    // și pentru a nu fi legate de o instanță specifică a clasei de test.

    static ProductFileRepo productFileRepo;

    // Variabilă statică pentru obiectul UI
    static UI ui;


    @BeforeClass
    public static void initializeObjects() {
        // Inițializăm variabila statică `productFileRepo` cu o instanță a clasei ProductFileRepo.
        // Această clasă gestionează datele produselor dintr-un fișier "products.txt" din directorul "utils".
        productFileRepo = new ProductFileRepo("utils/products.txt");

        // Inițializăm variabila statică `ui` cu o instanță a clasei UI.
        // UI reprezintă interfața utilizatorului pentru gestionarea produselor și primește `productFileRepo` ca parametru.
        ui = new UI(productFileRepo);
    }

    @Test
    public void testAdaugareProdus() {
        // Creăm un obiect Product pentru testare cu anumite valori
        Product productForTest = new Product("Test", 10, ProductCategory.TOOLS, 21.2f, LocalDate.now());

        // Adăugăm produsul de test în repository
        productFileRepo.addProduct(productForTest);

        // Obținem lista de produse din repository
        List<Product> products = productFileRepo.getProducts();

        // Verificăm dacă produsul adăugat este prezent în lista de produse
        assertTrue("Produsul adăugat ar trebui să fie prezent", products.contains(productForTest));
    }

    @Test
    public void testStergereProdus() {
        // Definim un ID de produs pentru testare
        int id = 1;

        // Apelăm metoda `deleteProduct` a obiectului `productFileRepo` pentru a șterge un produs cu ID-ul specificat
        productFileRepo.deleteProduct(id);

        // Obținem lista actuală de produse după ștergere
        List<Product> products = productFileRepo.getProducts();

        // Inițializăm o variabilă booleană `found` cu `false`, care va fi folosită pentru a verifica
        // dacă produsul cu ID-ul dat a fost găsit
        boolean found = false;

        // Parcurgem lista de produse pentru a căuta produsul cu ID-ul specificat
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                found = true; // Dacă găsim produsul, setăm `found` la `true`
            }
        }

        // Folosim aserțiunea `assertFalse` pentru a verifica că produsul cu ID-ul dat nu mai există în listă după ștergere
        assertFalse("Produsul ar trebui să fie eliminat", found);
    }

    @Test
    public void testPrimulIdDisponibil() {
        List<Product> products = productFileRepo.getProducts();
        int dimensiuneInitiala = products.size();

        // Creați un produs pentru a asigura că unele ID-uri sunt folosite
        Product newProduct = new Product("Produs Nou", 5, ProductCategory.FRUITS, 5.0f, LocalDate.now());
        productFileRepo.addProduct(newProduct);

        // Verificați că ID-ul disponibil nu este utilizat
        int idDisponibil = productFileRepo.getFirstAvailableId();
        for (Product product : products) {
            assertNotEquals("ID-ul disponibil nu ar trebui să coincidă cu niciun ID de produs existent", idDisponibil, product.getId());
        }

        // Ștergeți produsul nou adăugat
        productFileRepo.deleteProduct(newProduct.getId());

        // Verificați că dimensiunea s-a întors la valoarea inițială
        assertEquals("Numărul de produse ar trebui să fie același după ștergere", dimensiuneInitiala, productFileRepo.getProducts().size());
    }
    @Test
    public void testAdaugareProdusLegume(){
        Product produsLegume = new Product("Morcovi", 5, ProductCategory.VEGETABLES, 2.5f, LocalDate.now());
        productFileRepo.addProduct(produsLegume);
        List<Product> produse = productFileRepo.getProducts();
        assertTrue("Produsul de legume adăugat ar trebui să fie prezent", produse.contains(produsLegume));
    }
    @Test
    public void testAdaugareProdusDulciuri(){
        Product produsDulciuri = new Product("Ciocolată", 10, ProductCategory.SWEETS, 3.0f, LocalDate.now());
        productFileRepo.addProduct(produsDulciuri);
        List<Product> produse = productFileRepo.getProducts();
        assertTrue("Produsul de dulciuri adăugat ar trebui să fie prezent", produse.contains(produsDulciuri));
    }

    @Test
    public void testAdaugareProdusMobila(){
        Product produsMobila = new Product("Masă", 1, ProductCategory.FURNITURE, 150.0f, LocalDate.now());
        productFileRepo.addProduct(produsMobila);
        List<Product> produse = productFileRepo.getProducts();
        assertTrue("Produsul de mobilă adăugat ar trebui să fie prezent", produse.contains(produsMobila));
    }
}
