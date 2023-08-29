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
    static ProductFileRepo productFileRepo;
    static UI ui;
    @BeforeClass
    public static void initializeObjects(){
        productFileRepo = new ProductFileRepo("src/main/java/utils/products.txt");
        ui = new UI(productFileRepo);
    }
    @Test
    public void testAdaugareProdus(){
        Product productForTest = new Product("Test", 10, ProductCategory.TOOLS, 21.2f, LocalDate.now());
        productFileRepo.addProduct(productForTest);
        List<Product> products = productFileRepo.getProducts();
        assertTrue("Produsul adăugat ar trebui să fie prezent", products.contains(productForTest));
    }
    @Test
    public void testStergereProdus(){
        int id = 1;
        productFileRepo.deleteProduct(id);
        List<Product> products = productFileRepo.getProducts();
        boolean found = false;
        for (int i = 0; i < products.size(); i++){
            if (products.get(i).getId() == id) {
                found = true;
            }
        }
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
