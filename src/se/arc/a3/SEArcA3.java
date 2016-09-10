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

    public static void listShopOptions(){
        System.out.println("Options:");
        
        System.out.println("\tadd <choice> [<quantity>]");
        System.out.println("\t\tex: 'add 3 10', which adds item 3, 10 times to cart");
        
        System.out.println("\trm  <choice> [<quantity>]");
        System.out.println("\t\tex: 'rm 3 10', which removes item 3, 10 times from cart");
        System.out.println("\t\tex: 'rm 2', removes all instances of item 2");
        
        System.out.println("\tcheckout  <creditcard number> <shipping adress>");
        System.out.println("\t\tDescription");
        System.out.println("\t\t\tTake what you have added to your cart");
        System.out.println("\t\t\tand build a purchase associated with your user.");
        System.out.println("\t\tex: 'checkout 1121212423451125 2152 Bridlewood Cove'");
        
        System.out.println("\tmain");
        System.out.println("\t\tDescription");
        System.out.println("\t\t\tTakes you back to main menu");
    }
    
    public static void enterShop() {

        Item[] items = Inventory.getInstance().getItems();

        Scanner choice = new Scanner(System.in);
        
        System.out.println("\n========================   Inventory   ========================");

        while (true) {
            System.out.printf("%-8s %-9s     %s%n", "choice", "price", "name");
            for (int i = 0; i < items.length; i++) {
                System.out.printf("%-8s $%-,9.2f     %s%n", "["+(i+1)+"]", items[i].getPrice(), items[i].getName());
            }
            System.out.println("===============================================================");
            listShopOptions();
            System.out.print("Command: ");
            String input = choice.nextLine();
            
            if(input.equals("main")){
                return;
            }
        }

    }

    public static void menu(String name) {

        System.out.println("Welcome, " + name + "!");

        Scanner choice = new Scanner(System.in);

        while (true) {

            System.out.println("\n**********************************");
            System.out.println("What would you like to do today?");
            System.out.println("[1] View Purchase History");
            System.out.println("[2] Shop");
            System.out.println("[3] Logout");
            System.out.println("**********************************");
            System.out.print("Select your choice: ");

            switch (choice.nextLine()) {
                case "1":
                    System.out.println("Purchase history choice needs to go here.");
                    break;
                case "2":
                    enterShop();
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
