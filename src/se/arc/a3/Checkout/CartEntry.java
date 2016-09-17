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

import se.arc.a3.Shop.Item;

/**
 * A cart entry keeps up with a single item and the quantity of that item.
 * So if you ordered 4 pairs of socks then the cart entry would have an item
 * sock and the quantity of the entry would be 4.
 * 
 * @author Eli
 */
public class CartEntry {
    
    private final Item item;
    private int quantity;
    
    public CartEntry(Item item, int quantity){
        this.item = item;
        this.quantity = quantity;
    }
    
    /**
     * @return Item price multiplied by the quantity
     */
    public double getPrice() {
        return this.item.getPrice()*this.quantity;
    }

    /**
     * @return Item in the cart
     */
    public Item getItem() {
        return this.item;
    }
    
    /**
     * How many number of items you would like of that item.
     * 
     * @param quantity that the cart entry will be set too
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    /**
     * @return the quantity
     */
    public int getQuantity() {
        return this.quantity;
    }
    
}
