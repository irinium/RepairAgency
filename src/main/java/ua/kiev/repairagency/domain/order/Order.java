package ua.kiev.repairagency.domain.order;

import ua.kiev.repairagency.domain.appliance.Appliance;
import ua.kiev.repairagency.domain.user.User;

public class Order {
    private final Long id;
    private final String title;
    private final Double price;
    private final User customer;
    private final Appliance appliance;
    private final User master;
    private final Boolean state; //access -true, denied - false
    private  Boolean status; //active -true, complete - false

    private Order(Builder builder) {
        this.id = builder.id;
        this.appliance = builder.appliance;
        this.price = builder.price;
        this.customer = builder.customer;
        this.master = builder.master;
        this.title = builder.title;
        this.state = builder.state;
        this.status = builder.status;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Appliance getAppliance() {
        return appliance;
    }

    public Double getPrice() {
        return price;
    }

    public User getCustomer() {
        return customer;
    }

    public User getMaster() {
        return master;
    }

    public Boolean getState() {
        return state;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String title;
        private Double price;
        private User customer;
        private Appliance appliance;
        private User master;
        private Boolean state; //access -true, denied - false
        private Boolean status; //active -true, complete - false

        private Builder() {
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withAppliance(Appliance appliance) {
            this.appliance = appliance;
            return this;
        }

        public Builder withPrice(Double price) {
            this.price = price;
            return this;
        }

        public Builder withCustomer(User customer) {
            this.customer = customer;
            return this;
        }

        public Builder withMaster(User master) {
            this.master = master;
            return this;
        }

        public Builder withState(Boolean state) {
            this.state = state;
            return this;
        }
        public Builder withStatus(Boolean status) {
            this.status = status;
            return this;
        }

        public Order build(){
            return new Order(this);
        }
    }
}
