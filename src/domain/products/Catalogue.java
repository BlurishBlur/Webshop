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
                String size = tokens[3];
                String color = tokens[4];
                String gender = tokens[5];
                String manufactorer = tokens[6];
                int price = Integer.parseInt(tokens[7]);
                products.add(new Product(id, name, category, size, color, gender, manufactorer, price));
            }
            Collections.sort(products);
        }
        catch(IOException e) {
            System.err.println(e);
        }
    }

    @Override
    public List<Product> searchProducts(String searchTerm, double maxPrice, Set<String> genders,
            Set<String> categories, Set<String> colors, Set<String> sizes) {
        List<Product> gender = searchGender(products, genders);
        List<Product> category = searchCategory(gender, categories);
        List<Product> color = searchColor(category, colors);
        List<Product> size = searchSize(color, sizes);
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

    private List<Product> searchSize(List<Product> listToSearch, Set<String> sizes) {
        List<Product> results = new ArrayList<>();
        for(Product p : listToSearch) {
            for(String size : sizes) {
                if(p.getSize().equals(size)) {
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
