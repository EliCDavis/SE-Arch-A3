/*
 * The MIT License
 *
 * Copyright 2016 Josh Church && Eli Davis.
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
package se.arc.a3;

import java.io.FileNotFoundException;
import java.util.Scanner;
import se.arc.a3.Shop.Inventory;
import se.arc.a3.Shop.Item;
import se.arc.a3.Shop.ItemCategories.*;
import se.arc.a3.Storage.Database;
import se.arc.a3.User.User;

/**
 * 
 * @author Josh Church && Eli Davis
 */
public class SEArcA3 {

    public static void main(String[] args) throws FileNotFoundException {

        User[] loadedUsers = Database.getUsers();

        int count = 3;
        while (count > 0) {
            Scanner s = new Scanner(System.in);
            System.out.print("Please enter your username: ");

            String dataEntry = s.nextLine();

            for (User user : loadedUsers) {
                System.out.println(user);
                if (user.getUsername().equals(dataEntry)) {
                    count = 0;
                    menu(user.getName());
                }
            }

            count--;
            System.out.println("Sorry, that username isn't in our system.");
            System.out.println(count + " attempts remaining.");
        }
    }

    public static void viewItems(Class specifier) {

        Item[] items = Inventory.getInstance().getItems();

        System.out.println("\nInventory:");
        for (Item item : items) {
            if (specifier == null || item.getClass().equals(specifier)) {
                System.out.println("\t" + item);
            }
        }

    }

    public static void menu(String name) {

        System.out.println("Welcome, " + name + "!");

        Scanner choice = new Scanner(System.in);

        while (true) {

            System.out.println("\n**********************************");
            System.out.println("What would you like to do today?");
            System.out.println("1. View Purchase History");
            System.out.println("2. Browse inventory");
            System.out.println("\t2b. View only books");
            System.out.println("\t2e. View only electronics");
            System.out.println("\t2h. View only household items");
            System.out.println("\t2t. View only toys");
            System.out.println("3. Logout");
            System.out.println("**********************************");
            System.out.print("Select your choice: ");

            switch (choice.nextLine()) {
                case "1":
                    System.out.println("Purchase history choice needs to go here.");
                    break;
                case "2":
                    viewItems(null);
                    break;
                case "2b":
                    viewItems(BookItem.class);
                    break;
                case "2e":
                    viewItems(ElectronicItem.class);
                    break;
                case "2h":
                    viewItems(HouseholdItem.class);
                    break;
                case "2t":
                    viewItems(ToyItem.class);
                    break;
                case "3":
                    System.out.println("You have successfully logged out!");
                    System.exit(0);
                default:
                    System.out.println("Sorry, invalid choice.");
            }
        }
    }
}
