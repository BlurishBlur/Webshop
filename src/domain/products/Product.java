package domain.products;

/**
 * @author Niels
 */
public class Product implements Comparable<Product> {
    
    private final int id;
    private final String name, category, color, gender, manufacturer, description, imagePath;
    private final boolean small, medium, large;
    private final double price;

    public Product(int id, String name, String category, boolean small, boolean medium, boolean large, String color,
            String gender, String description, String imagePath, String manufactorer, double price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.small = small;
        this.medium = medium;
        this.large = large;
        this.color = color;
        this.gender = gender;
        this.description = "Placeholder beskrivelse: mew";
        this.imagePath = imagePath;
        this.manufacturer = manufactorer;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }
    
    public boolean isSmall() {
        return small;
    }
    
    public boolean isMedium() {
        return medium;
    }
    
    public boolean isLarge() {
        return large;
    }

    public String getColor() {
        return color;
    }

    public String getGender() {
        return gender;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getDescription() {
        return description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", category=" + 
                category + ", size=" + ", color=" + color + ", gender=" + 
                gender + ", manufacturer=" + manufacturer + ", description=" + 
                description + ", imagePath=" + imagePath + ", price=" + price + '}';
    }

    @Override
    public int compareTo(Product other) {
        return this.getName().compareTo(other.getName());
    }
}
