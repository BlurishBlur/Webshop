/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.users;

import domain.products.Item;
import domain.products.Product;
import java.util.List;

/**
 *
 * @author Alexander
 */
public interface UserManagable {

    void addItem(Product product, int quantity);

    void changeQuantity(String email, Item item, int quantity);

    void createGuestUser();

    void createOrder(int orderID);

    void createUser(String email, String password, String phoneNumber, String firstName, String lastName, String houseNumber, String streetName, String zipCode, String city, String country, int right, String birthDay, String birthMonth, String birthYear);

    User getLoggedInUser();

    List<Item> getShoppingBasket();

    boolean hasBasket();

    boolean isUserLoggedIn();

    boolean isValidEmail(String email);

    void logout();

    void removeItem(String email, Item item);

    boolean validate(String email, String password);
    
}
