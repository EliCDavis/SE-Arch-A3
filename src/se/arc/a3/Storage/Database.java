/*
 * The MIT License
 *
 * Copyright 2016 Josh & Eli.
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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import se.arc.a3.Checkout.*;
import se.arc.a3.Shop.Inventory;
import se.arc.a3.Shop.Item;
import se.arc.a3.User.User;
import se.arc.a3.Shop.ItemCategories.*;

/**
 * "Static" class for interfacing with our "database".
 *
 * @author Josh & Eli
 */
public class Database {

    public static void addPurchase(Purchase purchase) {
        Writer output;
        try {
            
            String entry = purchase.getUser().getUsername() + " - ";
            for(CartEntry cartEntry: purchase.getItems()){
                for(int i = 0; i < cartEntry.getQuantity(); i ++) {
                    entry += cartEntry.getItem().getId() + " ";
                }
            }
            entry += "- "+purchase.getAddress();
            entry += "- "+purchase.getCreditCardNumber();
            
            output = new BufferedWriter(new FileWriter("./src/se/arc/a3/Storage/db_purchase.txt", true));
            output.append("\n"+entry);
            output.close();
        } catch (IOException e) {
            System.out.println("Unable to save purchase!");
        }

    }

    /**
     * Looks at our dedicated file for storing Users and then looks at inventory
     * and purchases to correctly build everything we need to know about the
     * user.
     *
     * @return All users found in the database
     * @throws FileNotFoundException
     */
    public static User[] getUsers() throws FileNotFoundException {

        File file = new File("./src/se/arc/a3/Storage/db_users.txt");
        Scanner s = new Scanner(file);
        List<User> users = new ArrayList<>();
        String[][] purchases = getRawPurchaseData();
        Item[] items = Inventory.getInstance().getItems();

        while (s.hasNext()) {

            // Build the user object
            String[] vars = s.nextLine().split("-");
            User user = new User(vars[1].trim(), vars[0].trim());
            users.add(user);

            // Go through all the purchases
            for (String[] purchase : purchases) {

                // If this purchase belongs to this user
                if (purchase[0].trim().equals(user.getUsername())) {

                    // Build the cart
                    String[] itemIds = purchase[1].trim().split(" ");
                    Cart cart = new Cart();
                    for (String itemId : itemIds) {
                        for (Item item : items) {
                            if (item.getId().equals(itemId)) {
                                cart.addCartEntry(item, 1);
                            }
                        }
                    }

                    // Add the purchase to the User
                    user.addPurchase(new Purchase(user, purchase[2], purchase[3], cart.getEntries()));
                }

            }

        }

        User[] temp = new User[users.size()];
        temp = users.toArray(temp);
        return temp;
    }

    /**
     * Looks at our dedicated file for storing items and begins constructing
     * them with the appropriate subclass.
     *
     * @return All Items found in our database
     */
    public static Item[] getItems() {

        File file = new File("./src/se/arc/a3/Storage/db_inventory.txt");
        try {
            Scanner s = new Scanner(file);
            List<Item> items = new ArrayList<>();

            String curType = "";
            while (s.hasNext()) {
                String[] vars = s.nextLine().split("-");

                // We're assuming it's a category change
                if (vars.length == 1) {
                    curType = vars[0];
                    continue;
                }

                switch (curType) {
                    case "Books":
                        items.add(new BookItem(vars[0].trim(), parseInt(vars[2].trim()), vars[1].trim(), vars[4].trim(), vars[0].trim(), vars[3].trim(), vars[5].trim()));
                        break;

                    case "Toys":
                        items.add(new ToyItem(vars[0].trim(), parseInt(vars[2].trim()), vars[1].trim(), vars[3].trim(), vars[4].trim()));
                        break;

                    case "Household":
                        items.add(new HouseholdItem(vars[0].trim(), parseInt(vars[2].trim()), vars[1].trim(), vars[3].trim(), vars[4].trim(), vars[5].trim()));
                        break;

                    case "Electronics":
                        items.add(new ElectronicItem(vars[0].trim(), parseInt(vars[2].trim()), vars[1].trim(), vars[3].trim(), vars[4].trim()));
                        break;
                }

            }

            Item[] temp = new Item[items.size()];
            temp = items.toArray(temp);
            return temp;
        } catch (FileNotFoundException e) {
            System.out.println("Could not connect to Inventory Database!");
        }

        return null;

    }

    private static String[][] getRawPurchaseData() {
        File file = new File("./src/se/arc/a3/Storage/db_purchase.txt");
        try {
            Scanner s = new Scanner(file);
            List<String[]> purchases = new ArrayList<>();

            while (s.hasNext()) {
                purchases.add(s.nextLine().split("-"));
            }

            return purchases.toArray(new String[purchases.size()][]);
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    /**
     * Private constructor so Database can never be instantiated
     */
    private Database() {
    }

}
