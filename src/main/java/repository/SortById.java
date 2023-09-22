package repository;

import entity.Product;

import java.util.Comparator;

// Definim clasa `SortById` care implementează interfața `Comparator` pentru tipul `Product`.
class SortById implements Comparator<Product> {

    // Constructorul clasei `SortById`, care este gol (nu efectuează nicio acțiune).
    SortById() {
    }

    // Implementăm metoda `compare` din interfața `Comparator`.
    // Această metodă compară două obiecte de tip `Product` (o1 și o2) după ID-urile lor.
    public int compare(Product o1, Product o2) {
        // Comparăm ID-urile celor două obiecte și returnăm rezultatul.
        // Dacă `o1.getId()` este mai mic decât `o2.getId()`, va returna un număr negativ.
        // Dacă `o1.getId()` este mai mare decât `o2.getId()`, va returna un număr pozitiv.
        // Dacă ID-urile sunt egale, va returna 0.
        return o1.getId() - o2.getId();
    }
}

