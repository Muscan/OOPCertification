package repository;

import entity.Product;

import java.util.Comparator;

class SortById implements Comparator<Product> {
    SortById() {
    }

    public int compare(Product o1, Product o2) {
        return o1.getId() - o2.getId();
    }
}
