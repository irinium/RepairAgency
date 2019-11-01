package ua.kiev.repairagency.entity.user;

public class CustomerEntity extends UserEntity {
    private final AddressEntity addressEntity;


    protected CustomerEntity(CustomerBuilder customerBuilder) {
        super(customerBuilder);
        this.addressEntity = customerBuilder.addressEntity;
    }

    public AddressEntity getAddressEntity() {
        return addressEntity;
    }

    public static class CustomerBuilder extends UserBuilder<CustomerBuilder> {
        private String name;
        private String surname;
        private AddressEntity addressEntity;

        public CustomerBuilder() {
        }

        @Override
        public CustomerBuilder self() {
            return this;
        }

        public CustomerEntity build() {
            return new CustomerEntity(self());
        }

        public CustomerBuilder withAddress(AddressEntity addressEntity) {
            if (this.addressEntity != null) {
                this.addressEntity = new AddressEntity(
                        addressEntity.getId(),
                        addressEntity.getHouseNumber(),
                        addressEntity.getStreet(),
                        addressEntity.getTown(),
                        addressEntity.getCode());
            } else {
                this.addressEntity = null;
            }
            return self();
        }
    }
}
