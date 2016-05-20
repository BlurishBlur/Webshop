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
    
    public void createProduct(int id, String name, String manufactor, String category, Boolean small, Boolean medium, Boolean large, String color, String gender, Double price, String imagePath);
    
    
}
