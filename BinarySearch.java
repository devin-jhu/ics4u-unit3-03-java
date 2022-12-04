/*
* This program generates 250 random numbers in an array
* and allows the user to search the array for a number.
*
* @author  Mr Coxall
* @version 0.5
* @since   2020-09-01
*/

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * This is a Binary Search Program.
 */

final class BinarySearch {
    /**
    * The min number for array.
    */
    public static final int MIN = 0;

    /**
    * The max number for array.
    */
    public static final int MAX = 999;

    /**
    * The number of elements in the array.
    */
    public static final int ARRAY_SIZE = 250;

    /**
     * Prevent instantiation.
     * Throw an exception IllegalStateException.
     * if this is ever called
     *
     * @throws IllegalStateException
     *
     */
    private BinarySearch() {
        throw new IllegalStateException("Cannot be instantiated");
    }

    /**
    * Function finds the index of a number, using Binary Search recursively.
    *
    * @param numArray - array of random numbers generated in main
    * @param target - number that the user is trying to find
    * @param min - minimum index value
    * @param max - maxiumum index value
    * @return binarySearch
    */
    static int binarySearch(final int[] numArray, final int target,
                          final int min, final int max) {
        final int returnValue;
        // base case
        if (min > max) {
            returnValue = -1;
        } else {
            final int mid = min + (max - min) / 2;
            // true if target equals the matching index
            if (numArray[mid] == target) {
                returnValue = mid;
            // searches in the lower half if middle > target
            } else if (numArray[mid] > target) {
                returnValue = binarySearch(numArray, target, min, mid - 1);
            // searches in the upper half if middle < target
            } else {
                returnValue = binarySearch(numArray, target, mid + 1, max);
            }
        }
        return returnValue;
    }

    /**
     * The starting main() function.
     *
     * @param args No args will be used
     */
    public static void main(final String[] args) {
        System.out.println("Binary Search Program");
        try {
            // Initializing the random class
            final Random randNumber = new Random();

            // Initializing array of numbers
            final int[] randomNumberArray = new int[ARRAY_SIZE];

            // Adding numbers to the array
            for (int counter = 0; counter < randomNumberArray.length;
                counter++) {
                randomNumberArray[counter] = randNumber.nextInt(MAX) + 1;
            }

            // Sorting the array
            final int[] numberArray = randomNumberArray;
            Arrays.sort(numberArray);

            System.out.print("\nSorted list of numbers:\n");
            for (int element: numberArray) {
                final String padded = String.format("%03d", element);
                System.out.print(padded + ", ");
            }
            System.out.print("\n\n");

            // Getting user input as to what number they wish to search for
            final Scanner userInput = new Scanner(System.in);
            System.out.print(
                "What number are you searching for in the array");
            System.out.print(" (integer between 0 and 999): ");
            final int searchNumber = userInput.nextInt();
            userInput.close();
            System.out.println();

            // Ensuring the user inputs an appropriate integer
            if (searchNumber > MAX || searchNumber < MIN) {
                System.out.println("Target Number is Outside of Range.");
            } else {
                // Using binary search to find the user's number in the array
                final int searchResult = binarySearch(
                    numberArray, searchNumber, 0, numberArray.length - 1);

                // Outputing the results of the search
                System.out.println();
                System.out.println("Your number is in index: " + searchResult);
            }

        // Catches and tells the user that an error occured
        } catch (java.util.InputMismatchException ex) {
            System.out.println();
            System.out.println("ERROR: Invalid Input");
        }
    }
}
