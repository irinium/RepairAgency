package ua.kiev.repairagency.domain.order;

import ua.kiev.repairagency.domain.appliance.ElectricAppliance;
import ua.kiev.repairagency.domain.user.Customer;
import ua.kiev.repairagency.domain.user.Master;

public class Order {
    private final Long id;
    private final String title;
    private final Long price;
    private final Customer customer;
    private final ElectricAppliance appliance;
    private final Master master;
    private final Boolean state; //access -true, denied - false

    public Order(OrderBuilder orderBuilder) {
        this.id = orderBuilder.id;
        this.appliance = orderBuilder.appliance;
        this.price = orderBuilder.price;
        this.customer = orderBuilder.customer;
        this.master = orderBuilder.master;
        this.title = orderBuilder.title;
        this.state = orderBuilder.state;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public ElectricAppliance getAppliance() {
        return appliance;
    }

    public Long getPrice() {
        return price;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Master getMaster() {
        return master;
    }

    public Boolean getState() {
        return state;
    }

    public OrderBuilder builder(){
        return new OrderBuilder();
    }

    public static class OrderBuilder {
        private Long id;
        private String title;
        private Long price;
        private Customer customer;
        private ElectricAppliance appliance;
        private Master master;
        private Boolean state; //access -true, denied - false

        public OrderBuilder() {
        }

        public OrderBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public OrderBuilder withTitle(String title) {
            this.title = title;
            return this;
        }

        public OrderBuilder withAppliance(ElectricAppliance appliance) {
            this.appliance = appliance;
            return this;
        }

        public OrderBuilder withPrice(Long price) {
            this.price = price;
            return this;
        }

        public OrderBuilder withCustomer(Customer customer) {
            this.customer = customer;
            return this;
        }

        public OrderBuilder withMaster(Master master) {
            this.master = master;
            return this;
        }

        public OrderBuilder withState(Boolean state) {
            this.state = state;
            return this;
        }

        public Order build(){
            return new Order(this);
        }
    }

    @Override
    public String toString() {
        return "Order [id=" + id + ", title=" + title + ", price=" + price + "master=" + master.getName() + master.getSurname() + "]";
    }
}
