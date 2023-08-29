package entity;
import enumeration.*;
import java.time.LocalDate;

public class Product {

    private int id;
    private String name;
    private int quantity;
    private ProductCategory category;
    private float price;
    private LocalDate produceDate;

    public Product(int id, String name, int quantity, ProductCategory category, float price, LocalDate productDate) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.category = category;
        this.price = price;
        this.produceDate = productDate;
    }

    public Product(String name, int quantity, ProductCategory category, float price, LocalDate productDate) {
        this.name = name;
        this.quantity = quantity;
        this.category = category;
        this.price = price;
        this.produceDate = productDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public LocalDate getProduceDate() {
        return produceDate;
    }

    public void setProduceDate(LocalDate produceDate) {
        this.produceDate = produceDate;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity='" + quantity + '\'' +
                ", category=" + category +
                ", price=" + price +
                ", produceDate=" + produceDate +
                '}';
    }
}
