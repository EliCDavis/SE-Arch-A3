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
import se.arc.a3.Checkout.Purchase;
import se.arc.a3.Checkout.CartEntry;
import se.arc.a3.Shop.Item;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eli and Hunter
 */
public class Cart {

    private final List<CartEntry> cartEntries;

    public Cart() {
        cartEntries = new ArrayList<>();
    }

    public double getPriceTotal() {

        double cartEntryPrice = 0;

        cartEntryPrice = cartEntries.stream().map((currentEntry) -> currentEntry.getPrice()).reduce(cartEntryPrice, (accumulator, _item) -> accumulator + _item);

        return cartEntryPrice;
    }

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

    public CartEntry[] getEntries() {
        CartEntry[] temp = new CartEntry[cartEntries.size()];
        temp = cartEntries.toArray(temp);
        return temp;
    }

    public Purchase purchase(User customer, String address) {

        Purchase purchase = new Purchase(customer, address, getEntries());
        cartEntries.clear();
        
        return purchase;
    }

}
