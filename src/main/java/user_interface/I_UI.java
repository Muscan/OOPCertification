package user_interface;

import entity.Product;

/*n interfața I_UI, conceptul de abstractizare se aplică prin intermediul metodelor definite în interfață.
Abstractizarea este unul dintre principiile fundamentale ale programării orientate pe obiect
și se referă la ascunderea detaliilor interne ale unei entități și expunerea doar a aspectelor relevante
sau esențiale ale acelei entități.

În acest caz, interfața I_UI abstractizează operațiile legate de gestionarea produselor într-o aplicație.
Ea definește semnăturile metodelor, precum addProduct(), editProduct(), deleteProduct(), și run(),
fără a specifica implementarea detaliată a acestor operații. Aceasta permite clasei care implementează
această interfață să ofere
propriile implementări specifice pentru aceste operații în funcție de necesitățile aplicației respective.*/
public interface I_UI {
    Product addProduct();

    Product editProduct();

    int deleteProduct();

    void run();
}
