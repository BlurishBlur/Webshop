package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Niels
 */
public class PIM extends AbstractDatabase {

    public PIM() {
        super("pim");
    }
    
    public void getProducts() {
        List<String> results = new ArrayList<>();
        try(PreparedStatement st = connection.prepareStatement("SELECT *"
                    + "                 FROM product, product_size"
                    + "                 WHERE product.id = product_size.id")) {
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                results.add(Integer.toString(rs.getInt(1)));
                results.add(rs.getString(2));
                results.add(rs.getString(3));
                results.add(rs.getString(4));
                results.add(rs.getString(5));
                results.add(rs.getString(6));
                results.add(rs.getString(7));
                results.add(rs.getString(8));
                results.add(Double.toString(rs.getDouble(9)));
            }
        }
        catch(SQLException e) {
            System.err.println(e);
        }
        System.out.println(results);
    }
    
    public void updateProduct(int id, String name, String category, boolean small, boolean medium, boolean large, String color,
            String gender, String description, String imagePath, String manufactorer, double price) {
        
    }
    
}
