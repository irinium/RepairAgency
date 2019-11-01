package ua.kiev.repairagency.domain.user;

public class Address {
    private final Long id;
    private final int houseNumber;
    private final String street;
    private final String town;
    private final String code;

    public Address(){
        this.id = 0L;
        this.street = null;
        this.houseNumber = 0;
        this.town = null;
        this.code = null;
    }

    public Address(Long id, int houseNumber, String street, String town, String code) {
        this.id = id;
        this.street = street;
        this.houseNumber = houseNumber;
        this.town = town;
        this.code = code;
    }

    public Long getId() {
        return id;
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

    public String getCode() {
        return code;
    }
}
