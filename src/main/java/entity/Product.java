package entity;
import enumeration.*;
import java.time.LocalDate;

public class Product {
    // Variabile private pentru a stoca datele produsului
    private int id;
    private String name;
    private int quantity;
    private ProductCategory category;
    private float price;
    private LocalDate produceDate;

    // Constructor cu toate detaliile produsului
    public Product(int id, String name, int quantity, ProductCategory category, float price, LocalDate productDate) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.category = category;
        this.price = price;
        this.produceDate = productDate;
    }

    // Constructor pentru crearea produsului fără ID (poate fi generat ulterior)
    public Product(String name, int quantity, ProductCategory category, float price, LocalDate productDate) {
        this.name = name;
        this.quantity = quantity;
        this.category = category;
        this.price = price;
        this.produceDate = productDate;
    }

    // Metode getter pentru a obține valori private ale produsului
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public float getPrice() {
        return price;
    }

    public LocalDate getProduceDate() {
        return produceDate;
    }

    // Metode setter pentru a modifica valori private ale produsului
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setProduceDate(LocalDate produceDate) {
        this.produceDate = produceDate;
    }

    // Suprascrierea metodei toString pentru a afișa detaliile produsului
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
