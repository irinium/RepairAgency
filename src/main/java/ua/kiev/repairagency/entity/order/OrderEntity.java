package ua.kiev.repairagency.entity.order;

import ua.kiev.repairagency.entity.appliance.ElectricApplianceEntity;
import ua.kiev.repairagency.entity.user.UserEntity;

public class OrderEntity {
    private final Long id;
    private final String title;
    private final Long price;
    private final UserEntity customerEntity;
    private final ElectricApplianceEntity applianceEntity;
    private final UserEntity masterEntity;
    private final Boolean state; //access -true, denied - false

    public OrderEntity(OrderBuilder orderBuilder) {
        this.id = orderBuilder.id;
        this.applianceEntity = orderBuilder.applianceEntity;
        this.price = orderBuilder.price;
        this.customerEntity = orderBuilder.customerEntity;
        this.masterEntity = orderBuilder.masterEntity;
        this.title = orderBuilder.title;
        this.state = orderBuilder.state;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public ElectricApplianceEntity getApplianceEntity() {
        return applianceEntity;
    }

    public Long getPrice() {
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

    public OrderBuilder builder(){
        return new OrderBuilder();
    }

    public static class OrderBuilder {
        private Long id;
        private String title;
        private Long price;
        private UserEntity customerEntity;
        private ElectricApplianceEntity applianceEntity;
        private UserEntity masterEntity;
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

        public OrderBuilder withApplianceEntity(ElectricApplianceEntity appliance) {
            this.applianceEntity = appliance;
            return this;
        }

        public OrderBuilder withPrice(Long price) {
            this.price = price;
            return this;
        }

        public OrderBuilder withCustomerEntity(UserEntity customerEntity) {
            this.customerEntity = customerEntity;
            return this;
        }

        public OrderBuilder withMasterEntity(UserEntity masterEntity) {
            this.masterEntity = masterEntity;
            return this;
        }
        public OrderBuilder withState(Boolean state) {
            this.state = state;
            return this;
        }

        public OrderEntity build(){
            return new OrderEntity(this);
        }
    }
}
