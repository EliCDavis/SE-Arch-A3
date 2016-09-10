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
package se.arc.a3.Storage;

import java.io.File;
import java.io.FileNotFoundException;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import se.arc.a3.Shop.Item;
import se.arc.a3.User.User;
import se.arc.a3.Shop.ItemCategories.*;

/**
 *
 * @author Josh
 */
public class Database {

    public static User[] getUsers() throws FileNotFoundException {

        File file = new File("./src/se/arc/a3/Storage/db_users.txt");
        Scanner s = new Scanner(file);
        List<User> users = new ArrayList<>();

        while (s.hasNext()) {
            String[] vars = s.nextLine().split("-");
            users.add(new User(vars[1].trim(), vars[0].trim()));
        }

        User[] temp = new User[users.size()];
        temp = users.toArray(temp);
        return temp;
    }

    public static Item[] getItems() {

        File file = new File("./src/se/arc/a3/Storage/db_inventory.txt");
        try {
            Scanner s = new Scanner(file);
            List<Item> items = new ArrayList<>();

            String curType = "";
            while (s.hasNext()) {
                String[] vars = s.nextLine().split("-");
                
                // We're assuming it's a category change
                if(vars.length == 1){
                    curType = vars[0];
                    continue;
                }
                
                switch(curType){
                    case "Books":
                        items.add(new BookItem(vars[0].trim(), parseInt(vars[2].trim()), vars[1].trim()));
                        break;
                        
                    case "Toys":
                        items.add(new ToyItem(vars[0].trim(), parseInt(vars[2].trim()), vars[1].trim()));
                        break;
                        
                    case "Household":
                        items.add(new HouseholdItem(vars[0].trim(), parseInt(vars[2].trim()), vars[1].trim()));
                        break;
                        
                    case "Electronics":
                        items.add(new ElectronicItem(vars[0].trim(), parseInt(vars[2].trim()), vars[1].trim()));
                        break;
                }
                
            }

            Item[] temp = new Item[items.size()];
            temp = items.toArray(temp);
            return temp;
        } catch(FileNotFoundException e){
            System.out.println("Could not connect to Inventory Database!");
        }

        return null;
        
    }

    /**
     * Private constructor so Database can never be instantiated
     */
    private Database() {
    }
}
