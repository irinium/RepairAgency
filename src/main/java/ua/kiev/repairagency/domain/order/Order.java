package ua.kiev.repairagency.domain.order;

import ua.kiev.repairagency.domain.appliance.Appliance;
import ua.kiev.repairagency.domain.user.User;

import java.util.Objects;

public class Order {
    private final Long id;
    private final String title;
    private final Double price;
    private final User customer;
    private final Appliance appliance;
    private final User master;
    /**
     * Indicates accepting (accepted -true, denied - false)
     */
    private final Boolean state;
    /**
     * Indicates activity status (active -true, archive - false)
     */
    private Boolean status; 

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

    public static Builder builder() {
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

        public Order build() {
            return new Order(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || o.getClass() != this.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(getId(), order.getId()) &&
                Objects.equals(getTitle(), order.getTitle()) &&
                Objects.equals(getPrice(), order.getPrice()) &&
                Objects.equals(getCustomer(), order.getCustomer()) &&
                Objects.equals(getAppliance(), order.getAppliance()) &&
                Objects.equals(getMaster(), order.getMaster()) &&
                Objects.equals(getState(), order.getState()) &&
                Objects.equals(getStatus(), order.getStatus());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAppliance() == null) ? 0 : getAppliance().hashCode());
        result = prime * result + ((getCustomer() == null) ? 0 : getCustomer().hashCode());
        result = prime * result + ((getMaster() == null) ? 0 : getMaster().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = (int) (prime * result + (getId() == null ? 0: getId()));
        result = (int) (prime * result + (getPrice() == null ? 0 : getPrice()));
        return result;
    }
}
