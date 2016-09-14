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

/**
 *
 * @author Eli
 */
public class Purchase {
    
    private final String address;
    private final CartEntry[] items;
    private final User user;
    private final String creditCardNumber;
    
    public Purchase(User user, String address, String creditcardNumber, CartEntry[] cart){
        this.user = user;
        this.creditCardNumber = creditcardNumber;
        this.items = cart;
        this.address = address;
    }
    
    public Purchase(User user, String address, CartEntry[] cart){
        this.user = user;
        this.creditCardNumber = user.getCreditCardNumber();
        this.items = cart;
        this.address = address;
    }
    
    /**
     * 
     * @return price of all cart entries added together 
     */
    public double getPrice() {
        
        double total = 0.0;
        
        for (CartEntry item : getItems()) {
            total += item.getPrice();
        }
        
        return total;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @return the items
     */
    public CartEntry[] getItems() {
        return items;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @return the creditCardNumber
     */
    public String getCreditCardNumber() {
        return creditCardNumber;
    }
    
}
