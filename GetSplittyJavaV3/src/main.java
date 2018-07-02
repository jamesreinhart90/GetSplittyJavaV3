import helpers.addressParser;
import helpers.checkInput;
import objects.address;

public class main {

    public static void main(String[] args) {
        address address = new address();
        addressParser parseInput = new addressParser();

        // Print to screen and receive user input
        System.out.println("\nGet Splitty with it \nwritten by James Reinhart\n");
        System.out.println("This is an small program used to input a string a split it into its respective parts.\n");
        System.out.println("Allowed address formats are (Street, City State, Zip Code) or (Street City, State Zip Code)");
        System.out.println("Please enter a address:");
        parseInput.parseInput(address);

        checkOutput(address);
    }

    private static void outputData (address address) {
        // Output user data
        System.out.println("Street: " + address.getStreet());
        System.out.println("City: " + address.getCity());
        System.out.println("State: " + address.getState());
        System.out.println("Zip-Code: " + address.getZipcode());
    }

    public static void checkOutput (address address) {
        String userInput;
        outputData(address);
        System.out.println("Is everything correct that you see?");
        if (!checkInput.checkYes()) {
            System.out.println("What input is wrong?  You can say street, city, state, or zip.");
            userInput = checkInput.userInput();
            if (userInput.equalsIgnoreCase("street")) {
                address.setStreet(addressParser.reEnterStreet());
                checkOutput(address);
            } else if (userInput.equalsIgnoreCase("city")) {
                address.setCity(addressParser.reEnterCity());
                checkOutput(address);
            } else if (userInput.equalsIgnoreCase("state")) {
                address.setState(addressParser.reEnterState());
                checkOutput(address);
            } else if (userInput.equalsIgnoreCase("zip")) {
                address.setZipcode(addressParser.reEnterZipCode());
                checkOutput(address);
            } else {
                System.out.println("Not a valid input lets retry");
                checkOutput(address);
            }
        } else {
            System.out.println("Thank-you for using Get Splitty");
        }
    }
}
