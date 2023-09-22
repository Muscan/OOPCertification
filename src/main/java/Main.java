import repository.ProductFileRepo;
import user_interface.UI;
public class Main {

    public static void main(String[] args) {
        // Creăm o instanță a clasei ProductFileRepo, care gestionează datele despre produse dintr-un fișier.
        ProductFileRepo productFileRepo = new ProductFileRepo("utils/products.txt");

        // Creăm o instanță a clasei UI și îi furnizăm ProductFileRepo ca parametru.
        UI ui = new UI(productFileRepo);

        // Rulăm interfața de utilizator pentru gestionarea produselor.
        ui.run();
    }
}

