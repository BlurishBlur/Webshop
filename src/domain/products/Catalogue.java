package domain.products;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * @author Niels
 */
public class Catalogue implements ProductManagable {
    
    private List<Product> products;
    private Product selectedProduct;
    
    public Catalogue() {
        products = new ArrayList<>();
    }
    
    @Override
    public void loadProducts() {
        try(Scanner in = new Scanner(new File("data/Products.txt"))) {
            in.nextLine(); // kommentarlinje
            while(in.hasNextLine()) {
                String[] tokens = in.nextLine().split(", ");
                int id = Integer.parseInt(tokens[0]);
                String name = tokens[1];
                String category = tokens[2];
                boolean small = Boolean.parseBoolean(tokens[3]);
                boolean medium = Boolean.parseBoolean(tokens[4]);
                boolean large = Boolean.parseBoolean(tokens[5]);
                String color = tokens[6];
                String gender = tokens[7];
                String manufactorer = tokens[8];
                int price = Integer.parseInt(tokens[9]);
                products.add(new Product(id, name, category, small, medium, large, color, gender, "", "file:icons/PHshirtIcon.png", manufactorer, price));
            }
            Collections.sort(products);
        }
        catch(IOException e) {
            System.err.println(e);
        }
    }

    @Override
    public List<Product> searchProducts(String searchTerm, double maxPrice, Set<String> genders,
            Set<String> categories, Set<String> colors, boolean small, boolean medium, boolean large) {
        List<Product> gender = searchGender(products, genders);
        List<Product> category = searchCategory(gender, categories);
        List<Product> color = searchColor(category, colors);
        List<Product> size = searchSize(color, small, medium, large);
        return search(size, searchTerm, maxPrice);
        //return search(searchSize(searchColor(searchCategory(searchGender(products, genders), categories), colors), sizes), searchTerm, maxPrice); // alternativ måde (slet ikke forvirrende)
    }

    private List<Product> search(List<Product> listToSearch, String searchTerm, double maxPrice) {
        List<Product> results = new ArrayList<>();
        for(Product p : listToSearch) {
            if(p.getName().toLowerCase().contains(searchTerm.toLowerCase()) && p.getPrice() < maxPrice) {
                results.add(p);
            }
        }
        return results;
    }

    private List<Product> searchGender(List<Product> listToSearch, Set<String> genders) {
        List<Product> results = new ArrayList<>();
        for(Product p : listToSearch) {
            for(String gender : genders) {
                if(p.getGender().equals(gender)) {
                    results.add(p);
                }
            }
        }
        return results;
    }

    private List<Product> searchCategory(List<Product> listToSearch, Set<String> categories) {
        List<Product> results = new ArrayList<>();
        for(Product p : listToSearch) {
            for(String category : categories) {
                if(p.getCategory().equals(category)) {
                    results.add(p);
                }
            }
        }
        return results;
    }

    private List<Product> searchColor(List<Product> listToSearch, Set<String> colors) {
        List<Product> results = new ArrayList<>();
        for(Product p : listToSearch) {
            for(String color : colors) {
                if(p.getColor().equals(color)) {
                    results.add(p);
                }
            }
        }
        return results;
    }

    private List<Product> searchSize(List<Product> listToSearch, boolean small, boolean medium, boolean large) {
        List<Product> results = new ArrayList<>();
        for(Product p : listToSearch) {
            if((p.isSmall()) && small || p.isMedium() && medium || p.isLarge() && large) { // VIRKER IKKE,
                    if(!results.contains(p)) {
                        results.add(p);
                    }
                }
        }
        return results;
    }
    
    @Override
    public void sortNameAscending(List listToSort) {
        Collections.sort(listToSort);
    }
    
    @Override
    public void sortNameDescending(List listToSort) {
        sortNameAscending(listToSort);
        Collections.reverse(listToSort);
    }
    
    @Override
    public void sortPriceAscending(List listToSort) {
        Collections.sort(listToSort, new ProductPriceComparator());
    }
    
    @Override
    public void sortPriceDescending(List listToSort) {
        Collections.sort(listToSort, Collections.reverseOrder(new ProductPriceComparator()));
    }

    @Override
    public List<Product> getProducts() {
        return products;
    }

    @Override
    public void setSelectedProduct(Product product) {
        selectedProduct = product;
    }

    @Override
    public Product getSelectedProduct() {
        return selectedProduct;
    }
}
