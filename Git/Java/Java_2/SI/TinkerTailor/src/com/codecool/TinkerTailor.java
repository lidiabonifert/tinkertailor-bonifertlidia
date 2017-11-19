package com.codecool;

import static java.lang.Integer.parseInt;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class TinkerTailor {
    private static List<Integer> listOfNumbers = new ArrayList<>();

    private static List<Integer> createList(int lengthOfList) { // Fill the list with numbers ranging from 1 up to the given length.
        for (int i = 1; i <= lengthOfList; i++) {
            listOfNumbers.add(i);
        }
        return listOfNumbers;
    }

    private static String checkForWinner(List<Integer> listToCheck) {   // Changes the closing message depending on the "game's" status.
        String closingMessage;
        if (listToCheck.size() == 1) {
            closingMessage = "--> " + listOfNumbers.get(0) + " wins";
        } else {
            closingMessage = "--> {2} is out";
        }
        return closingMessage;
    }

    private static void countOutThief(int lengthOfList, int counter) {

        int nextNumberIndex = 0;    // The index of the number from which the counting starts.
        int nextNumber = 1;     // The actual number from which the counting starts.
        listOfNumbers = createList(lengthOfList);       // Create a list with a given length.

        while (listOfNumbers.size() >= 1) {     // Iterate through the list, until there's only 1 number's left in it.

            String closingMessage = checkForWinner(listOfNumbers);    // Change the winner string when that ^ happens.
            nextNumberIndex = (nextNumberIndex + counter - 1) % (listOfNumbers.size());     //Count the next number's index from the previous nextNumberIndex.
            int lastNumber = listOfNumbers.get(nextNumberIndex);    // Get back the last number for the print.

            System.out.println(MessageFormat.format("Starting from {0} --> {1} " + closingMessage,      //Print out the current status of the game.
                    nextNumber, listOfNumbers, lastNumber, closingMessage));

            if (listOfNumbers.size() == 1) {   // If it's win scenario, stop the program after the print.
                System.exit(2);
            }
            nextNumber = listOfNumbers.get(lastNumber % listOfNumbers.size());      // Get the next starting number depending on the last one.
            listOfNumbers.remove(nextNumberIndex);      // Remove the "thief".


        }
    }

    public static void main(String[] args) {
        try {
            int lengthOfList = parseInt(args[0]);       // First arg from the terminal will be the list's length.
            int counter = parseInt(args[1]);            // Second will the number to count out by.
            countOutThief(lengthOfList, counter);       // "Tinker, tailor, soldier, sailor..."

        } catch (ArrayIndexOutOfBoundsException e) {        // Handle negative and non-existent arg.
            System.out.println("The length of the list and the counter must be bigger then zero!");
        } catch (NumberFormatException e) {                 // Handle negative and float arg!");
            System.out.println("The length of the list and the counter must be an integer!");
        }
    }
}