/*
 * The MIT License
 *
 * Copyright 2016 Eli.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package se.arc.a3.User;

import java.util.ArrayList;
import java.util.List;
import se.arc.a3.Checkout.Cart;
import se.arc.a3.Checkout.Purchase;

/**
 * Class that represents a single user which has an account with our shopping
 * system.
 * 
 * @author Eli
 */
public class User {

    /**
     * Birth name of user
     */
    private final String name;
    
    /**
     * username of the user of what they use to log in with also doubles as 
     * an ID
     */
    private final String id;
    
    /**
     * The users credit card
     */
    private String creditCardNumber;
    
    /**
     * A collection of items and quantities that the user has decided they 
     * wanted to buy.
     */
    private final Cart cart;
    
    /**
     * A history of purchases the user has made since the creation of their 
     * account (persistent in a database)
     */
    private final List<Purchase> purchases;

    public User(String name, String id) {
        this.name = name;
        this.id = id;
        this.cart = new Cart();
        this.purchases = new ArrayList<>();
    }

    /**
     * @return All purchases the user has made since account creation
     */
    public Purchase[] viewPastPurchases() {
        return purchases.toArray(new Purchase[purchases.size()]);
    }

    /**
     * Adds a purchase to the user history.
     * **This does not add the purchase to the database**
     * 
     * @param newPurchase The purchase to add
     */
    public void addPurchase(Purchase newPurchase) {

        if (newPurchase == null) {
            return;
        }

        this.purchases.add(newPurchase);
    }

    /**
     * @return the user's credit card number
     */
    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    /**
     * @return The user name
     */
    public String getUsername() {
        return id;
    }

    /**
     * @return The birth name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * @return The cart that the user has been building. 
     */
    public Cart getCart() {
        return cart;
    }

    @Override
    public String toString() {
        return "Id: " + id + "; Name: " + name + ";";
    }

}
