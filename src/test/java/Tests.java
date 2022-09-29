import entity.Product;
import enumeration.ProductCategory;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.ProductFileRepo;
import user_interface.UI;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Tests {
    static ProductFileRepo productFileRepo;
    static UI ui;

    @BeforeClass
    public static void initializeObjects(){
        productFileRepo = new ProductFileRepo("src/main/java/utils/products.txt");
        ui = new UI(productFileRepo);
    }

    @Test
    public void addProductTest(){
        Product productForTest = new Product("Test",10, ProductCategory.TOOLS, 21.2f, LocalDate.now());
        productFileRepo.addProduct(productForTest);
        List<Product> products = productFileRepo.getProducts();
        boolean expected = true;
        boolean actual = products.contains(productForTest);
        assertEquals(expected, actual);
    }

    @Test
    public void deleteProductTest(){
        int id=1;
        productFileRepo.deleteProduct(id);
        List<Product> products = productFileRepo.getProducts();
        boolean found = false;
        for (int i = 0; i< products.size(); i++){
            if (products.get(i).getId() == id) {
                found = true;
            }
        }
        boolean expected = false;
        assertEquals(expected, found);
    }
}
