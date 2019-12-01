package ua.kiev.repairagency.entity.order;

import ua.kiev.repairagency.domain.order.Order;
import ua.kiev.repairagency.entity.appliance.ApplianceEntity;
import ua.kiev.repairagency.entity.user.UserEntity;

import java.util.Objects;

public class OrderEntity {
    private final Long id;
    private final String title;
    private final Double price;
    private final UserEntity customerEntity;
    private final ApplianceEntity applianceEntity;
    private final UserEntity masterEntity;
    private final Boolean state; //access -true, denied - false
    private final Boolean status; //active -true, complete - false

    private OrderEntity(Builder builder) {
        this.id = builder.id;
        this.applianceEntity = builder.applianceEntity;
        this.price = builder.price;
        this.customerEntity = builder.customerEntity;
        this.masterEntity = builder.masterEntity;
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

    public ApplianceEntity getApplianceEntity() {
        return applianceEntity;
    }

    public Double getPrice() {
        return price;
    }

    public UserEntity getCustomerEntity() {
        return customerEntity;
    }

    public UserEntity getMasterEntity() {
        return masterEntity;
    }

    public Boolean getState() {
        return state;
    }

    public Boolean getStatus() {
        return status;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String title;
        private Double price;
        private UserEntity customerEntity;
        private ApplianceEntity applianceEntity;
        private UserEntity masterEntity;
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

        public Builder withApplianceEntity(ApplianceEntity appliance) {
            this.applianceEntity = appliance;
            return this;
        }

        public Builder withPrice(Double price) {
            this.price = price;
            return this;
        }

        public Builder withCustomerEntity(UserEntity customerEntity) {
            this.customerEntity = customerEntity;
            return this;
        }

        public Builder withMasterEntity(UserEntity masterEntity) {
            this.masterEntity = masterEntity;
            return this;
        }
        public Builder withState(Boolean state) {
            this.state = state;
            return this;

        }public Builder withStatus(Boolean status) {
            this.status = status;
            return this;
        }

        public OrderEntity build(){
            return new OrderEntity(this);
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
                Objects.equals(getCustomerEntity(), order.getCustomer()) &&
                Objects.equals(getApplianceEntity(), order.getAppliance()) &&
                Objects.equals(getMasterEntity(), order.getMaster()) &&
                Objects.equals(getState(), order.getState()) &&
                Objects.equals(getStatus(), order.getStatus());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getApplianceEntity() == null) ? 0 : getApplianceEntity().hashCode());
        result = prime * result + ((getCustomerEntity() == null) ? 0 : getCustomerEntity().hashCode());
        result = prime * result + ((getMasterEntity() == null) ? 0 : getMasterEntity().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = (int) (prime * result + (getId() == null ? 0: getId()));
        result = (int) (prime * result + (getPrice() == null ? 0 : getPrice()));
        return result;
    }
}
