package com.sda.Service;

import java.util.Scanner;

public class IOService {
    private Scanner scanner= new Scanner(System.in);
    public void displayMenu() {
        System.out.println("Welcome ! Please select one of the following options:");
        System.out.println("1 - Add a book");
        System.out.println("2 - Add an author");
        System.out.println("3 - Delete a book");
        System.out.println("4 - Delete an Author");
        System.out.println("5 - Update a book");
        System.out.println("6 - Update an Author");
        System.out.println("7 - Exit application");
    }

    public String getUserInput() {
        String userInput = scanner.nextLine();
        return userInput;
    }

    public boolean getUserInputToContinue(){
        System.out.println("Should we continue the process?");
        String answer=scanner.nextLine();
        if (answer.equals("yes")){
            return true;
        }
        return false;
    }

    public String getField(String field) {
        System.out.println("Please insert "+field+" :");
        String userInput= scanner.nextLine();
        return userInput;
    }

    public void displayError(String text) {
        System.out.println(text);
    }

    public void displayInfo(String message) {
        System.out.println(message);
    }

}


