package ua.kiev.repairagency.domain.user;

public class CustomerEntity extends UserEntity {
    private final String name;
    private final String surname;
    private final AddressEntity addressEntity;
    private final String phoneNumber;


    private CustomerEntity(CustomerBuilder customerBuilder) {
        super(customerBuilder);
        this.name = customerBuilder.name;
        this.surname = customerBuilder.surname;
        this.addressEntity = customerBuilder.addressEntity;
        this.phoneNumber = customerBuilder.phoneNumber;
    }

    public static class CustomerBuilder extends UserBuilder<CustomerBuilder> {
        private String name;
        private String surname;
        private AddressEntity addressEntity;
        private String phoneNumber;

        public CustomerBuilder() {
        }

        @Override
        public CustomerBuilder self() {
            return this;
        }

        public CustomerEntity build() {
            return new CustomerEntity(self());
        }


        public CustomerBuilder withName(String name) {
            this.name = name;
            return self();
        }

        public CustomerBuilder withSurname(String surname) {
            this.surname = surname;
            return self();
        }

        public CustomerBuilder withAddress(AddressEntity addressEntity) {
            if (this.addressEntity != null) {
                this.addressEntity = new AddressEntity(addressEntity.getHouseNumber(), addressEntity.getStreet(), addressEntity.getTown());
            } else {
                this.addressEntity = null;
            }
            return self();
        }

        public CustomerBuilder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return self();
        }
    }
}
