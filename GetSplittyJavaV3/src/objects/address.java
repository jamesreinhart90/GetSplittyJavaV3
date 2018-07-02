package objects;

public class address {
    public static String street;
    public static String city;
    public static String state;
    public static String zipCode;

    public address () {}

    public String getStreet() {
        return this.street;
    }
    public void setStreet(String value) {
        this.street = value;
    }

    public String getCity() {
        return this.city;
    }
    public void setCity(String value) {
        this.city = value;
    }

    public String getState() {
        return this.state;
    }
    public void setState(String value) {
        this.state = value;
    }

    public String getZipcode() {
        return this.zipCode;
    }
    public void setZipcode(String value) {
        this.zipCode = value;
    }
}
