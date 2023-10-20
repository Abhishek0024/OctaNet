package com.atm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ATM_Interface {
	
	private Scanner scanner;
	private List<User> users;
	private User currentUser;
	
	public ATM_Interface() {
		scanner = new Scanner(System.in);
		users = new ArrayList<>();
		
		// 10 users added (modifiable)
		for (int i = 1; i <= 10; i++) 
		{
			users.add(new User("User" + i, "1234", 10000.0));
	    }
	}

	    public void start() {
	        System.out.println("!!!!Welcome to the ATM system!!!!");

	        // User Login
	        boolean loggedIn = false;
	        while (!loggedIn) {
	            System.out.print("Enter your user ID: ");
	            String userID = scanner.nextLine().trim();

	            System.out.print("Enter your PIN: ");
	            String pin = scanner.nextLine().trim();

	            for (User user : users) {
	                if (user.getUserID().equals(userID) && user.getPin().equals(pin)) {
	                    currentUser = user;
	                    loggedIn = true;
	                    break;
	                }
	            }

	            if (!loggedIn) {
	                System.out.println("Invalid credentials. Please try again.");
	            }
	        }

	        
	        // ATM Interface Loop
	        while (true) {
	            displayMenu();
	            String choice = scanner.nextLine().trim();

	            switch (choice) {
	                case "1":
	                    amountDeposit();
	                    break;
	                case "2":
	                    amountWithdrawal();
	                    break;
	                case "3":
	                    amountTransfer();
	                    break;
	                case "4":
	                    displayTransactionHistory();
	                    break;
	                case "5":
	                    System.out.println("Thank you for using the ATM. Goodbye!");
	                    return;
	                default:
	                    System.out.println("Invalid choice. Please try again.");
	                    break;
	            }
	        }
	    }
	    
	    // ATM Menu
	    private void displayMenu() {
	        System.out.println("\n---------- ATM MENU ----------");
	        System.out.println("1. Deposit");
	        System.out.println("2. Withdraw");
	        System.out.println("3. Transfer");
	        System.out.println("4. Transaction History");
	        System.out.println("5. Quit");
	        System.out.print("Enter your choice: ");

	    }

	    // Add amount
	    private void amountDeposit() {
	        System.out.print("Enter the deposit amount: ");
	        double amount = Double.parseDouble(scanner.nextLine().trim());
	        currentUser.deposit(amount);
	        System.out.println("Deposit successful. New balance: $" + currentUser.getBalance());
	        System.out.println("\n*******************************\n*******************************");

	    }

	    // Withdraw amount
	    private void amountWithdrawal() {
	        System.out.print("Enter the withdrawal amount: ");
	        double amount = Double.parseDouble(scanner.nextLine().trim());

	        if (currentUser.withdraw(amount)) {
	            System.out.println("Withdrawal successful. New balance: $" + currentUser.getBalance());
	        } else {
	            System.out.println("Insufficient funds.");
	        }
	        System.out.println("\n*******************************\n*******************************");

	    }

	    // Transfer amount
	    private void amountTransfer() {
	        System.out.print("Enter the receiver's user ID: ");
	        String receiverID = scanner.nextLine().trim();

	        User receiver = null;
	        for (User user : users) {
	            if (user.getUserID().equals(receiverID)) {
	                receiver = user;
	                break;
	            }
	        }

	        if (receiver == null) {
	            System.out.println("Receiver not found.");
	            return;
	        }

	        System.out.print("Enter the transfer amount: ");
	        double amount = Double.parseDouble(scanner.nextLine().trim());

	        currentUser.transfer(receiver, amount);
	        System.out.println("Transfer successful. New balance: $" + currentUser.getBalance());
	        System.out.println("\n*******************************\n*******************************");
	    }

	    // Transaction history
	    private void displayTransactionHistory() {
	        System.out.println("Transaction History for " + currentUser.getUserID() + ":");
	        List<String> history = currentUser.getTransactionHistory();
	        for (String entry : history) {
	            System.out.println(entry);
	        }
	        System.out.println("\n*******************************\n*******************************");

	    }
	}