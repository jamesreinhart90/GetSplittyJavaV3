package helpers;

import java.io.InputStreamReader;
import java.util.Scanner;

public class checkInput {
    public checkInput() {}

    public static String userInput () {
        String userInput;
        Scanner scanner = new Scanner(new InputStreamReader(System.in));

        userInput = String.valueOf(scanner.nextLine());
        return userInput.trim();
    }

    public static boolean checkArrayLength (String addressString) {
        String[] spaceDelimitedArray;

        spaceDelimitedArray = addressString.split(" ");

        if (spaceDelimitedArray.length < 4) { // Address array needs at minimum (Zip-code, State, City, Street and House Number) 5 elements.
            System.out.println("Address does not meet the required length.  Address must contain,");
            System.out.println("House Number, Street, City, State and Zip-Code.  Please try again.");
            return false;
        }
        return true;
    }

    public static boolean checkZipCode (String zipCode) { // Checks length of zip-code and make sure that it is a number
        if (zipCode.length() != 5) {
            System.out.println("Zip-Code not valid length, please re-enter Zip-Code.");
            return false;
        } else {
            if (!tryParseInt(zipCode)) {
                System.out.println("Zip-Code is not valid number, please re-enter Zip-Code.");
                return false;
            } else { return true; }
        }
    }

    public static boolean checkState (String[] stateArray) {
        if (stateArray.length == 1) {
            if (stateArray[0].length() == 2) {
                return true;
            } else {
                System.out.println("Your state does not seem to match a 2 digit state code, is your state " + String.join(" ", stateArray) + "?");
                if (checkYes()) {
                    return true;
                } else {
                    return false;
                }
            }
        } else {
            System.out.println("Your state does not seem to match a 2 digit state code, is your state " + String.join(" ", stateArray) + "?");
            if (checkYes()) {
                return true;
            } else {
                return false;
            }
        }
    }

    public static boolean checkCity (String[] cityArray) {
        System.out.println("Lets confirm your city.  Is your city " + String.join(" ", cityArray) + "?");
        if (!checkYes()) {
            return false;
        } else {
            return true;
        }
    }

    public static Integer checkCityLength (String[] CityAndOtherArray, Integer requiredArrayLength, String stringInput) {
        Integer words;
        String userInput;

        if (CityAndOtherArray.length - requiredArrayLength != 1) {
            System.out.println("How many words are in your city?  Your " + stringInput + " input: " + String.join(" ",CityAndOtherArray));
            userInput = userInput();
            if (tryParseInt(userInput)) {
                words = Integer.valueOf(userInput);
                if (CityAndOtherArray.length < (words + requiredArrayLength)) {
                    System.out.println("Your input exceeds the normal address length, please re-enter a number for how many words are in your city.");
                    words = Integer.valueOf(checkCityLength(CityAndOtherArray, requiredArrayLength, stringInput));
                }
            } else {
                System.out.println("This is not a valid input, please enter a number for how many words are in your city.");
                words = Integer.valueOf(checkCityLength(CityAndOtherArray, requiredArrayLength, stringInput));
            }
        } else {
            words = 1;
        }
        return words;
    }

    public static boolean checkStreet (String[] streetArray) {
        if (streetArray.length >= 2) {
            if (tryParseInt(streetArray[0])) {
                return true;
            } else {
                System.out.println("House Number is not a number.");
                return false;
            }
        } else {
            System.out.println("Not long enough to be a valid address");
            return false;
        }
    }

    private static boolean tryParseInt (String value){// Test to see if String can be parsed to int
        try {
            Integer.parseInt(value);
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    public static boolean checkYes () {
        String userInput = userInput();
        if (userInput.equalsIgnoreCase("y") || userInput.equalsIgnoreCase("yes")) { // Can this be turned into a ternary operator
            return true;
        } else {
            return false;
        }
    }
}
