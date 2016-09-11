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

    private List <CartEntry> cartEntries

    public double getPriceTotal() {

        double cartEntryPrice = 0;

        for (CartEntry currentEntry: cartEntries) {
            cartEntryPrice = cartEntryPrice + (currentEntry.getPrice() * currentEntry.getQuantity())
        }

        return cartEntryPrice;
    }

    public addCartEntry(Item item, int quantity) {

        if (item != null && quantity > 0) {
            for (CartEntry entry: cartEntries) {
                if (entry.getItem() == item) {
                    entry.setQuantity(entry.getQuantity() + quantity);
                    return;
                }
            }

            cartEntries.add(new CartEntry(item, quantity));
        }
    }

    public removeCartEntry(Item item, int quantity) {

        for (int i = 0; i < cartEntries.length(); i++) {
            if (cartEntries[i].getItem() == item) {
                cartEntries[i].setQuantity(cartEntries[i].getQuantity() - quantity);

                if (cartEntries[i].getQuantity <= 0) {
                    cartEntries.remove(i);
                }

                return;
            }
        }
    }

    public Purchase purchase(User customer, string address) {

        CartEntry[] temp = new CartEntry[cartEntries.size()];
        temp = cartEntries.toArray(temp);

        return purchase = new Purchase(customer, address, temp);
    }

}
