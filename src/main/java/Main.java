import entity.Product;
import enumeration.ProductCategory;
import repository.ProductFileRepo;
import user_interface.UI;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        ProductFileRepo productFileRepo = new ProductFileRepo("src/main/java/utils/products.txt");

        UI ui = new UI(productFileRepo);
        ui.run();
    }
}

