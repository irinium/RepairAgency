package ua.kiev.repairagency.entity.user;

public class CustomerEntity extends UserEntity {

    protected CustomerEntity(CustomerBuilder customerBuilder) {
        super(customerBuilder);
    }

    public static class CustomerBuilder extends UserBuilder<CustomerBuilder> {


        public CustomerBuilder() {
        }

        @Override
        public CustomerBuilder self() {
            return this;
        }

        public CustomerEntity build() {
            return new CustomerEntity(self());
        }

    }
}
