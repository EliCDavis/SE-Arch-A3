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
package se.arc.a3.Checkout;

import se.arc.a3.User.User;
import se.arc.a3.Shop.Item;
import se.arc.a3.Storage.Database;


import java.util.ArrayList;
import java.util.List;

/**
 * Each user has a single cart that they can add and remove items and then purchase 
 * when their satisfied
 * 
 * @author Eli and Hunter
 */
public class Cart {

    /**
     * List of cart entries that the cart contains that the user wants to purchase
     */
    private final List<CartEntry> cartEntries;

    public Cart() {
        cartEntries = new ArrayList<>();
    }

    /**
     * Adds up all the Cart Entries prices and returns the total
     * 
     * @return The price of purchasing the cart 
     */
    public double getPriceTotal() {
        double cartEntryPrice = 0;
        return cartEntries.stream().map((currentEntry) -> currentEntry.getPrice()).reduce(cartEntryPrice, (accumulator, _item) -> accumulator + _item);
    }

    /**
     * If the item your adding already exists the quantity will be added to the
     * existing quantity of said item entry
     * 
     * @param item The item you'd like to add to the cart
     * @param quantity How many instances of the item you'd like to add
     */
    public void addCartEntry(Item item, int quantity) {

        if (item != null && quantity > 0) {
            for (CartEntry entry : cartEntries) {
                if (entry.getItem() == item) {
                    entry.setQuantity(entry.getQuantity() + quantity);
                    return;
                }
            }

            cartEntries.add(new CartEntry(item, quantity));
        }
    }

    /**
     * Removes a certain item x amount of times form a cart entry where x
     * is quantity and item is the existing entry.
     * 
     * If the the existing quantity - passed in quantity <= 0, then the cart 
     * entry is removed entriely from the cart.
     * 
     * @param item Item to remove
     * @param quantity How many instances of the item you'd like to remove
     */
    public void removeCartEntry(Item item, int quantity) {

        for (int i = 0; i < cartEntries.size(); i++) {
            if (cartEntries.get(i).getItem() == item) {
                cartEntries.get(i).setQuantity(cartEntries.get(i).getQuantity() - quantity);

                if (cartEntries.get(i).getQuantity() <= 0) {
                    cartEntries.remove(i);
                }

                return;
            }
        }
    }

    /**
     * @return All carty entries that the cart contains
     */
    public CartEntry[] getEntries() {
        CartEntry[] temp = new CartEntry[cartEntries.size()];
        temp = cartEntries.toArray(temp);
        return temp;
    }

    /**
     * Creates a purchase object from the existing cart entries that the cart
     * contains and saves it to the database.
     * 
     * @param customer The customer making the purchase
     * @param address The address the items will be shipped to
     * @param card The credit card that the purchase will be charged too.
     * @return 
     */
    public Purchase purchase(User customer, String address, String card) {
        Purchase purchase = new Purchase(customer, address, card, getEntries());
        customer.addPurchase(purchase);
        cartEntries.clear();
        Database.addPurchase(purchase);
        return purchase;
    }

}
