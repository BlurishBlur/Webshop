/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.products.Item;
import domain.products.Product;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Morten
 */
public interface IWebshopDriver {

    /**
     * Styrer tilføjelsen af items i GUI så den kommunikerer med domæne-laget.
     *
     * @param quantity antallet valgt af den specifikke item
     */
    void addItem(int quantity, String size);

    /**
     * Styrer ændringen af kvantiteten på et item i shoppingBasket, i GUI så de
     * kommunikerer med domæne-laget.
     *
     * @param email emailen på den specifikke bruger som ændringen skal
     * foretages på.
     * @param quantity det oplyste nummer som kvantiteten af en specifik item
     * skal ændres til.
     * @param item den specifikke item det drejer sig om.
     */
    void changeQuantity(int quantity, Item item);

    /**
     * Styrer oprettelsen af en bruger i GUI så den kommunikerer med
     * domæne-laget.
     *
     * @param email den indtastet email
     * @param password den indtastet kode
     * @param phoneNumber det indtastet telefonnummer
     * @param firstName det indtastet fornavn
     * @param lastName det indtastet efternavn
     * @param houseNumber det indtastet husnummer
     * @param streetName det indtastet vejnummer
     * @param zipCode det indtastet postnummer
     * @param city den indtastet by
     * @param country det indtastet land
     * @param birthDay det indtastet dag-nummer
     * @param birthMonth det indtastet månedsnummer
     * @param birthYear det indtastet år
     */
    void createUser(String email, String password, String phoneNumber, String firstName, String lastName, String houseNumber, String streetName, String zipCode, String city, String country, String birthDay, String birthMonth, String birthYear);

    /**
     * Finder brugerens fødselsdag (kun dato nummeret)
     *
     * @return nummeret på dagen som brugeren har fødselsdag
     */
    String getBirthDay();

    /**
     * Finder brugerens fødselsdag (kun månedsnummer)
     *
     * @return nummeret på måneden som brugeren har fødselsdag
     */
    String getBirthMonth();

    /**
     * Finder årstallet som brugeren er født i
     *
     * @return brugerens fødselsår
     */
    String getBirthYear();

    /**
     * Identificerer by fra Address klassen {@link util.Address}
     *
     * @return byen som den specifikke User har indtastet på sin profil
     */
    String getCity();

    /**
     * Identificerer land fra Address klassen {@link util.Address}
     *
     * @return landet som den specifikke User har indtastet på sin profil
     */
    String getCountry();

    /**
     * Finder brugerens email adresse
     *
     * @return den specifikke Users email-adresse
     */
    String getEmail();

    /**
     * Benytter et oprette Name-klasse der opdeler et navn i fornavn og
     * efternavn.
     *
     * @return fornavnet på den specifikke User.
     */
    String getFirstName();

    /**
     * Identificerer husnummer fra Address klassen {@link util.Address}
     *
     * @return husnummeret som den specifikke User har indtastet på sin profil
     */
    String getHouseNumber();

    /**
     * Benytter et oprette Name-klasse {@link util.Name} der opdeler et navn i
     * fornavn og efternavn.
     *
     * @return efternavnet på den specifikke User.
     */
    String getLastName();

    /**
     * Finder Brugerens telefonnummer
     *
     * @return brugerens telefonnummer
     */
    String getPhoneNumber();

    /**
     *
     * @return
     */
    List<Product> getProducts();

    /**
     * Styrer udvælgelsen af de specifikke produkt i GUI så den kommunikerer med
     * domæne-laget.
     *
     * @return oplysningerne om det udvalgte produkt
     */
    Product getSelectedProduct();

    /**
     * Styrer valget af shoppingBasket siden i GUI så den kommunikerer med
     * domæne-laget.
     *
     * @return informationerne om den specifikke shoppingBasket
     */
    List<Item> getShoppingBasket();

    /**
     * Identificerer vejnavn fra Address klassen {@link util.Address}
     *
     * @return vejnavnet som den specifikke User har indtastet på sin profil
     */
    String getStreetName();

    /**
     * Identificerer postnummer fra Address klassen {@link util.Address}
     *
     * @return postnummeret som den specifikke User har indtastet på sin profil
     */
    String getZipCode();

    /**
     * Styrer valideringen af om en email eksisterer i GUI så den kommunikerer
     * med domæne-laget.
     *
     * @param email den indtastet email
     * @return om emailen findes eller ej
     */
    boolean isValidEmail(String email);

    /**
     * Styrer indlæsning af produkter på GUI. Indlæser objekterne af
     * {@link Product} som {@link Catalogue} indeholder.
     *
     */
    void loadProducts();

    /*public void connect() {
    database.connect();
    }
    public void disconnect() {
    database.disconnect();
    }
    public boolean isConnected() {
    return database.isConnected();
    }*/
    /**
     * Logger den en indtastet bruger ind i systemet. Beder det aktive objekt af
     * UserManageren om at validere at den indtastet email og kode passer på en
     * bruger i systemet.
     *
     * @param email den indtastet email adresse
     * @param password den indtastet kode
     * @return burgeren hvis email og kode passer, eller returneres en
     * fejlmeddelelse
     */
    boolean login(String email, String password);

    /**
     * Logger det User objekt der er aktivt i systemet af.
     */
    void logout();

    /**
     * Fjerner en item fra shoppingBasket, hvis antallet er lig med 0
     *
     * @param item den specifikke item det får antallet 0
     */
    void removeItem(Item item);

    /**
     * Styrer søgekriterier valg i GUI så de kommunikere med domæne-laget.
     *
     * @param searchWord indtastet søgeord
     * @param maxPrice udvalgte maks pris på pris-slideren
     * @param genders udvalgte checkbox for køn
     * @param categories udvalgte kategori fra checkboxene for kategorier
     * @param colors udvalgte farve fra checkboxene af farver
     * @param sizes udvalgte størrelse fra checkboxene af størrelser
     * @return Domænelagets match af de udvalgte søgekriterier
     */
    List<Product> searchProducts(String searchWord, double maxPrice, Set<String> genders, Set<String> categories, Set<String> colors, boolean small, boolean medium, boolean large);

    /**
     *
     * @param product
     */
    void setSelectedProduct(Product product);

    /**
     * Styrer søgekriterier valg i GUI så de kommunikerer med domæne-laget.
     *
     * @param sortTerm
     * @param listToSort
     * @return
     */
    List<Product> sortProducts(String sortTerm, List listToSort);

    void changeProductDetails(int id, String name, String category, boolean small, boolean medium, boolean large, String color,
            String gender, String description, String imagePath, String manufactorer, double price);

}
