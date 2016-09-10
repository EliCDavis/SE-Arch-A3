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
import se.arc.a3.Checkout.Cart;
import se.arc.a3.Checkout.Purchase;
        
public class User {
    
    private final String name;
    private final String id;
    private int  creditCardNumber;
    private final Cart cart;
    private final Purchase[] purchase;
    
    public User(String name, String id){
        this.name = name;
        this.id = id;
        this.cart = new Cart();
        this.purchase = null;
    }
    
    public Purchase[] viewPastPurchases() {
        return purchase;
    }
    
    public int getCreditCardNumber() {
        return creditCardNumber;
    }
    public String getUsername() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    @Override
    public String toString() {
       return "Id: " + id + "; Name: " + name + ";";
    }
    
}
