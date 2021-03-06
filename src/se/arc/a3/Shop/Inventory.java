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

import se.arc.a3.Storage.Database;

/**
 * The inventory is a singleton which means that it isn't instantiated until
 * requested.  Because of this behavior during some instances of the program
 * we might never even need to load in the items from the database if the user
 * just signs in and then signs out.  This ensures that resources are only loaded
 * when they need too.
 * @author Eli
 */
public class Inventory {
    
    private static Inventory instance = null;

    private final Item[] items;
    
    /**
     * Made private so that only the Inventory class can instantiate itself
     */
    private Inventory(){
        this.items = Database.getItems();
    }
    
    /**
     * Creates an instance of the inventory if it doesn't already exist
     * @return The inventory.
     */
    public static Inventory getInstance(){
        
        if(instance == null){
            instance = new Inventory();
        }
        
        return instance;        
    }
    
    /**
     * @return All items found in the database
     */
    public Item[] getItems(){
        return this.items;
    }
    
}
