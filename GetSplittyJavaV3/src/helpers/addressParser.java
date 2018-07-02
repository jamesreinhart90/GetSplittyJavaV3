package helpers;

import objects.address;

import java.util.Arrays;

public class addressParser {

    public addressParser () {}

    public static address parseInput(address address) {
        // Get user input
        String addressString;
        addressString = checkInput.userInput();

        if (!checkInput.checkArrayLength(addressString)) { parseInput(address); } // Check to make sure that user input is the right length

        addressSwitch((addressString.split(",")), address); // Decide how to parse the address depending on how many commas
        return address;
    }

    private static void addressSwitch(String[] array, address address) {
        switch (array.length) {
            case 1:
                System.out.println("You have used no commas, please enter a comma after the city or enter 2 commas, 1 after the street and 1 after the state.\nPlease re-enter your address: ");
                parseInput(address);
                break;
            case 2:
                oneComma(array, address);
                break;
            case 3:
                twoCommas(array, address);
                break;
            default:
                System.out.println("You have " + (array.length - 3) + " to many commas.  Please use 1 or 2 commas in your address.\nPlease re-enter your address: ");
                parseInput(address);
        }
    }

    private static void oneComma (String[] addressArray, address address) {
        String[] stateZipArray, streetCityArray, streetArray, cityArray;
        Integer stateLength, wordsInCity;

        stateZipArray = addressArray[1].trim().split(" ");
        streetCityArray = addressArray[0].trim().split(" ");

        if (stateZipArray.length > 1) {
            // Check zip-code
            if (checkInput.checkZipCode(stateZipArray[stateZipArray.length - 1].trim())) {
                address.setZipcode(stateZipArray[stateZipArray.length - 1].trim());
            } else {
                address.setZipcode(reEnterZipCode());
            }

            // Check State
            if (checkInput.checkState(Arrays.copyOf(stateZipArray, stateZipArray.length - 1))) {
                address.setState(String.join(" ", Arrays.copyOf(stateZipArray, stateZipArray.length - 1)));
            } else {
                address.setState(reEnterState());
            }
        } else {
            System.out.println("There was an issue with your State and Zip-Code input.");
            address.setState(reEnterState());
            address.setZipcode(reEnterZipCode());
        }

        // Prevent State input from being entered into the city and Street.
        stateLength = address.getState().split(" ").length;
        if (address.getState().equalsIgnoreCase(String.join("",Arrays.copyOfRange(streetCityArray, (streetCityArray.length - stateLength), streetCityArray.length)))) {
            streetCityArray = Arrays.copyOf(streetCityArray, streetCityArray.length-stateLength);
        }

        if (streetCityArray.length > 3) {
            // Check City
            wordsInCity = checkInput.checkCityLength(streetCityArray, 3, "Street City");
            cityArray = Arrays.copyOfRange(streetCityArray, (streetCityArray.length - wordsInCity), streetCityArray.length);
            if (checkInput.checkCity(cityArray)) {
                address.setCity(String.join(" ",cityArray));
            } else {
                reEnterCity();
            }

            // Check Street
            streetArray = Arrays.copyOf(streetCityArray, streetCityArray.length - wordsInCity);
            if (checkInput.checkStreet(streetArray)) {
                System.out.println("Lets just confirm your street address, is your street " + String.join(" ", streetArray) + "?");
                if (checkInput.checkYes()) {
                    address.setStreet(String.join(" ", streetArray));
                } else {
                    address.setStreet(reEnterStreet());
                }
            } else {
                address.setStreet(reEnterStreet());
            }
            address.setStreet(String.join(" ",  streetArray));
        } else {
            System.out.println("Something happened, lets retry entering the street and city.");
            address.street = reEnterStreet();
            address.city = reEnterCity();
        }
    }

    private static void twoCommas (String[] addressArray, address address) {
        String[] streetArray, cityStateArray,zipCodeArray, cityArray, stateArray;
        String zipCodeString;
        Integer wordsInCity;

        streetArray = addressArray[0].trim().split(" ");
        cityStateArray = addressArray[1].trim().split(" ");
        zipCodeArray = addressArray[2].trim().split(" ");

        // Check ZipCode
        zipCodeString = String.join(" ", zipCodeArray);
        if(zipCodeArray.length > 1) {
            System.out.println("Your Zip Code is the wrong length");
            address.setZipcode(reEnterZipCode());
        } else {
            if (checkInput.checkZipCode(zipCodeString)) {
                address.setZipcode(zipCodeString);
            } else {
                address.setZipcode(reEnterZipCode());
            }
        }

        // Check Address
        if (checkInput.checkStreet(streetArray)) {
            address.setStreet(String.join(" ", streetArray));
        } else {
            address.setStreet(reEnterStreet());
        }

        // Check City State
        if (cityStateArray.length > 1) {
            //Check City
            wordsInCity = checkInput.checkCityLength(cityStateArray, 1, "City State");
            cityArray = Arrays.copyOf(cityStateArray, wordsInCity);
            if (checkInput.checkCity(cityArray)) {
                address.setCity(String.join(" ", cityArray));
            } else {
                address.setCity(reEnterCity());
            }

            // Check State
            stateArray = Arrays.copyOfRange(cityStateArray, wordsInCity, cityStateArray.length);
            if (checkInput.checkState(stateArray)) {
                address.setState(String.join(" ", stateArray));
            } else {
                address.setState(reEnterState());
            }
        } else {
            System.out.println("City and State are not long enough lets retry entering the data.");
            address.setCity(reEnterCity());
            address.setState(reEnterState());
        }
    }

    public static String reEnterZipCode () {
        String zipCode;

        System.out.println("Please re-enter your Zip-Code.");
        zipCode = checkInput.userInput();
        if (!checkInput.checkZipCode(zipCode)) {
            zipCode = reEnterZipCode();
        }
        return zipCode;
    }

    public static String reEnterState () {
        String state;

        System.out.println("Please re-enter your State.");
        state = checkInput.userInput();
        if (!checkInput.checkState(state.split(" "))) {
            state = reEnterState();
        }
        return state;
    }

    public static String reEnterCity () {
        String city;

        System.out.println("Please re-enter your City.");
        city = checkInput.userInput();
        if (!checkInput.checkCity(city.split(" "))) {
            city = reEnterCity();
        }
        return city;
    }


    public static String reEnterStreet () {
        String street;

        System.out.println("Please re-enter your Street.");
        street = checkInput.userInput();
        if(!checkInput.checkStreet(street.split(" "))) {
            street = reEnterStreet();
        }
        return street;
    }
}