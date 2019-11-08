package ua.kiev.repairagency.domain.user;

public class Customer extends User {

    protected Customer(CustomerBuilder customerBuilder) {
        super(customerBuilder);
    }

    public static class CustomerBuilder extends UserBuilder<CustomerBuilder> {

        public CustomerBuilder() {
        }

        @Override
        public CustomerBuilder self() {
            return this;
        }

        public Customer build() {
            return new Customer(self());
        }

    }
}
