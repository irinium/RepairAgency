package ua.kiev.repairagency.entity.order;

import ua.kiev.repairagency.entity.appliance.ApplianceEntity;
import ua.kiev.repairagency.entity.user.UserEntity;

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
}
