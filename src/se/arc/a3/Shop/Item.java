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
package se.arc.a3.Shop;

/**
 * Represents a single item from the database.
 * The item is created when the inventory is requested for the first time
 * and is created from a text file serving as a database.
 * 
 * @author Eli
 */
public abstract class Item {
    
    /**
     * name of the item
     */
    private final String name;
    
    /**
     * The price of the item
     */
    private final double price;
    
    /**
     * The id of the item
     */
    private final String id;
    
    public Item(String name, double price, String productId) {
        this.name = name;
        this.price = price;
        this.id = productId;
    }
    
    /**
     * @return The name of the item
     */
    public String getName() {
        return name;
    }
    
    /**
     * @return The price of the item
     */
    public double getPrice() {
        return price;
    }
    
    /**
     * @return The id of the item
     */
    public String getId(){
        return id;
    }
    
    @Override
    public String toString() {
        return "Item: " + id +" - " + name + " ($"+ price +");";
    }
}
