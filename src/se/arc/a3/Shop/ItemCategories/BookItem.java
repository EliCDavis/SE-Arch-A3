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
package se.arc.a3.Shop.ItemCategories;

import se.arc.a3.Shop.Item;

/**
 *
 * @author Eli
 */
public class BookItem extends Item {
    
    private String isbn;
    private String title;
    private String author;
    private String publisher;
    
    public BookItem(String name, double price, String productId, String isbn, String title, String author, String publisher) {
        super(name, price, productId);
        this.isbn = isbn;
        this.author = author;
        this.title = title;
        this.publisher = publisher;
    }
    
    @Override
    public String toString(){
        return String.format("ISBN: %s; %s By: %s; $%,.2f; Published by: %s;", isbn, title, author, getPrice(), publisher);
    }
    
}
