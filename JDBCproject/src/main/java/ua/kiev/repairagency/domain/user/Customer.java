package ua.kiev.repairagency.domain.user;

public class Customer extends User {
    private final String name;
    private final String surname;
    private final Address address;


    protected Customer(CustomerBuilder customerBuilder) {
        super(customerBuilder);
        this.name = customerBuilder.name;
        this.surname = customerBuilder.surname;
        this.address = customerBuilder.address;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Address getAddress() {
        return address;
    }

    public static class CustomerBuilder extends UserBuilder<CustomerBuilder> {
        private String name;
        private String surname;
        private Address address;

        public CustomerBuilder() {
        }

        @Override
        public CustomerBuilder self() {
            return this;
        }

        public Customer build() {
            return new Customer(self());
        }


        public CustomerBuilder withName(String name) {
            this.name = name;
            return self();
        }

        public CustomerBuilder withSurname(String surname) {
            this.surname = surname;
            return self();
        }

        public CustomerBuilder withAddress(Address address) {
            if (this.address != null) {
                this.address = new Address(
                        address.getId(),
                        address.getHouseNumber(),
                        address.getStreet(),
                        address.getTown(),
                        address.getCode());
            } else {
                this.address = null;
            }
            return self();
        }
    }
}
