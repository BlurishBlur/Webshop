/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.products;

import java.util.List;
import java.util.Set;

/**
 *
 * @author Morten
 */
public interface ProductManagable {

    List<Product> getProducts();

    Product getSelectedProduct();

    void loadProducts();

    List<Product> searchProducts(String searchTerm, double maxPrice, Set<String> genders, Set<String> categories, Set<String> colors, boolean small, boolean medium, boolean large);

    void setSelectedProduct(Product product);

    void sortNameAscending(List listToSort);

    void sortNameDescending(List listToSort);

    void sortPriceAscending(List listToSort);

    void sortPriceDescending(List listToSort);

    /**
     * Bruges til at opdatere et produkt
     * @param id produktets id
     * @param name navnet på produktet
     * @param category hvilken kategori er tøjet
     *              3 booleans med størrelser
     * @param small 
     * @param medium
     * @param large
     * @param color farven på tøjet
     * @param gender hvilket køn tøjet er til
     * @param description beskrivelse af produktet
     * @param imagePath det er stigen til billedes placering
     * @param manufactorer hvilket firma har lavet tøjet
     * @param price prisen
     */
    
    void changeProductDetails(int id, String name, String category, boolean small, boolean medium, boolean large, String color,
            String gender, String description, String imagePath, String manufactorer, double price);
    
}
