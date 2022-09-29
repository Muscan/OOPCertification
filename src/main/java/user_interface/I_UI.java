package user_interface;

import entity.Product;

public interface I_UI {
    Product addProduct();

    Product editProduct();

    int deleteProduct();

    void run();
}
