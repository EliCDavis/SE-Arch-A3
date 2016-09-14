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
import se.arc.a3.Checkout.*;
import static java.lang.Integer.parseInt;
import static java.lang.Integer.parseInt;

/**
 *
 * @author Josh Church && Eli Davis
 */
public class SEArcA3 {

    private static User userloggedIn;

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
                    userloggedIn = user;
                    menu(user.getName());
                }
            }

            count--;
            System.out.println("Sorry, that username isn't in our system.");
            System.out.println(count + " attempts remaining.");
        }
    }

    /**
     * Print out a list of commands the user can take while in the shop menu
     */
    private static void listShopOptions() {
        System.out.println("Options:");

        System.out.println("\tadd <choice> [<quantity>]");
        System.out.println("\t\tex: 'add 3 10', which adds item 3, 10 times to cart\n");

        System.out.println("\trm  <choice> [<quantity>]");
        System.out.println("\t\tex: 'rm 3 10', which removes item 3, 10 times from cart");
        System.out.println("\t\tex: 'rm 2', removes all instances of item 2\n");

        System.out.println("\tcheckout");
        System.out.println("\t\tDescription");
        System.out.println("\t\t\tTake what you have added to your cart");
        System.out.println("\t\t\tand build a purchase associated with your user.");

        System.out.println("\tview <choice>");
        System.out.println("\t\tDescription");
        System.out.println("\t\t\tGives a description about the item from the inventory (not cart)");
        System.out.println("\t\tex 'view 9', which will print out information about 9\n");

        System.out.println("\tmain");
        System.out.println("\t\tDescription");
        System.out.println("\t\t\tTakes you back to main menu");
    }

    private static Item attemptGrabbingItem(String userInput, Item[] inventory) {
        try {

            int itemId = -1;
            Item foundItem;

            try {
                itemId = parseInt(userInput);
            } finally {
                foundItem = inventory[itemId - 1];
            }

            return foundItem;

        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            System.out.println("Couldn't find item!");
            return null;
        }

    }

    private static String getItemName(Item item){
        return item.getClass().getSimpleName().substring(0, item.getClass().getSimpleName().length()-4);
    }
    
    private static void listItems(Item[] items) {
        System.out.printf("%-8s %-9s      %-13s%s%n", "choice", "price", "category", "name");
        for (int i = 0; i < items.length; i++) {
            System.out.printf("%-8s $%-,9.2f     %-13s%s%n", "[" + (i + 1) + "]", items[i].getPrice(), getItemName(items[i]), items[i].getName());
        }
    }

    private static void listItems(CartEntry[] entries) {
        System.out.printf("%-8s %-9s    %-10s      %-13s%s%n", "choice", "price", "quantity", "category", "name");
        for (int i = 0; i < entries.length; i++) {
            System.out.printf("%-8s $%-,9.2f   %-10d      %-13s%s%n", "[" + (i + 1) + "]", entries[i].getPrice(), entries[i].getQuantity(),getItemName(entries[i].getItem()), entries[i].getItem().getName());
        }
    }

    /**
     * 
     * @param command
     * @param input
     * @return the arguments of the command of an empty array.  Null if it's not the command
     */
    private static String[] getArguments(String command, String input){
        
        if(input.length() >= command.length() + 1 && input.substring(0, command.length()).equals(command)){
            return input.substring(command.length() + 1, input.length()).split(" ");
        }
        
        return null;
    }
    
    private static void enterShop() {

        Item[] items = Inventory.getInstance().getItems();

        Scanner choice = new Scanner(System.in);

        while (true) {
            System.out.println("\n========================   Inventory   ========================");
            listItems(items);

            System.out.println("\n========================     Cart      ========================");

            if (userloggedIn.getCart().getEntries().length == 0) {
                System.out.println("Cart Is Empty!");
            } else {
                listItems(userloggedIn.getCart().getEntries());
                System.out.printf("%nTotal: $%,.2f%nType checkout to complete transaction%n", userloggedIn.getCart().getPriceTotal());
            }

            System.out.println("===============================================================");
            while (true) {

                System.out.print("Command (type help to view commands): ");
                String input = choice.nextLine();

                // Display the help command
                if (input.equals("help")) {
                    listShopOptions();
                    continue;
                }

                if (input.equals("main")) {
                    // Return breaks out of the entire method (both whiles) and returns us to main.
                    return;
                }

                // View Command
                if (getArguments("view", input) != null) {

                    Item itemtoView = attemptGrabbingItem(getArguments("view", input)[0], items);

                    if (itemtoView != null) {
                        System.out.println(itemtoView);
                    } else {
                        System.out.println("Couldn't find item!");
                    }
                    continue;
                }

                // Add command
                if (getArguments("add", input) != null) {

                    String[] args = getArguments("add", input);

                    if (args.length == 0) {
                        System.out.println("You must choose an item choice!");
                        continue;
                    }

                    if (args.length > 0) {

                        Item itemToPurchase = attemptGrabbingItem(args[0], items);
                        int qunatity = 1;

                        if (args.length == 2) {
                            try {
                                qunatity = parseInt(args[1]);
                            } finally {
                                System.out.println("Invalid quantity value! Defaulting to 1");
                            }
                        }

                        if (itemToPurchase != null) {
                            userloggedIn.getCart().addCartEntry(itemToPurchase, qunatity);
                            break;
                        }

                    }

                    continue;
                }

                // Remove commmand
                if (getArguments("rm", input) != null) {

                    String[] args = getArguments("rm", input);

                    if (args.length == 0) {
                        System.out.println("You must choose an item choice!");
                        continue;
                    }

                    if (args.length > 0) {

                        int entryId = -1;
                        CartEntry foundEntry;

                        try {

                            try {
                                entryId = parseInt(args[0]);
                            } finally {
                                foundEntry = userloggedIn.getCart().getEntries()[entryId - 1];
                            }

                        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
                            System.out.println("Couldn't find entry!");
                            continue;
                        }

                        Item itemToRemove = foundEntry.getItem();
                        int qunatity = 1;

                        if (args.length == 2) {
                            try {
                                qunatity = parseInt(args[1]);
                            } finally {
                                System.out.println("Invalid quantity value! Defaulting to 1");
                            }
                        }

                        if (itemToRemove != null) {
                            userloggedIn.getCart().removeCartEntry(itemToRemove, qunatity);
                            break;
                        }

                    }

                }
                
                // Begin Checkout process
                if(input.contains("checkout")){
                    if (userloggedIn.getCart().getEntries().length == 0) {
                        System.out.println("\nERROR: You cannot checkout with an empty cart, " + userloggedIn.getName() + "!");
                        return;
                    } else {
                                            
                        while(true){

                            // Get the credit card number
                            System.out.print("Please enter your 10-Digit OSC Card Number: ");
                            Scanner s = new Scanner(System.in);
                            String creditcardNumber = s.nextLine();

                            if(creditcardNumber.length() != 10) {
                                System.out.println("Sorry, OSC Card Number not valid.");
                                break;

                            } 

                            // Get the address
                            System.out.print("Please enter your address: ");
                            String address = s.nextLine();

                            // If no address is entered, break;
                            if (address.equals("")){
                                System.out.println("Sorry, invalid address.\n");
                                break;

                            } else {
                                System.out.printf("CreditCard: %s || Shipping Address: %s || Total Price: %,.2f%n", creditcardNumber, address, userloggedIn.getCart().getPriceTotal());
                                System.out.print("Confirm Purchase(y/n): ");
                                input = choice.nextLine();

                                if(input.charAt(0) == 'y'){
                                    userloggedIn.getCart().purchase(userloggedIn, address, creditcardNumber);
                                    System.out.println("\n===========================================================================");
                                    System.out.println("Thank you, " + userloggedIn.getName() + "! Your purchase is being processed.");
                                    System.out.println("===========================================================================\n");
                                    return;
                                } else if(input.charAt(0) == 'n') {
                                    System.out.println("\n*** Returning to main menu *** ");
                                    return;
                                }
                            }
                        }
                    }
                }         
            }
        }
    }

    private static void listPurchaseHistory(){
        
        Purchase[] purchaseHistory = userloggedIn.viewPastPurchases();
        
        for(Purchase purchase : purchaseHistory){
            
            System.out.printf("Purchase (%d entries; $%,.2f):%n", purchase.getItems().length, purchase.getPrice());
            System.out.printf("\t%-9s %-9s %-9s%n", "Item", "Quantity", "Total Price");
            
            for(CartEntry entry : purchase.getItems()){
                System.out.printf("\t%-9s %-9d $%-,9.2f%n", entry.getItem().getName(), entry.getQuantity(), entry.getPrice());
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
                    listPurchaseHistory();
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
