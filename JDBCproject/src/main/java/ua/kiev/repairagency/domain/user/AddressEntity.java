package ua.kiev.repairagency.domain.user;

public class AddressEntity {
    private final String street;
    private final int houseNumber;
    private final String town;


    public AddressEntity(int houseNumber, String street, String town) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.town = town;
    }

    public String getStreet() {
        return street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public String getTown() {
        return town;
    }

     public AddressEntity(){
        this.street = null;
        this.houseNumber = 0;
        this.town = null;

    }
}
