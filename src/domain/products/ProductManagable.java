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
 * @author Alexander
 */
public interface ProductManagable {

    List<Product> getProducts();

    Product getSelectedProduct();

    void loadProducts();

    List<Product> searchProducts(String searchTerm, double maxPrice, Set<String> genders, Set<String> categories, Set<String> colors, Set<String> sizes);

    void setSelectedProduct(Product product);

    void sortNameAscending(List listToSort);

    void sortNameDescending(List listToSort);

    void sortPriceAscending(List listToSort);

    void sortPriceDescending(List listToSort);
    
}
