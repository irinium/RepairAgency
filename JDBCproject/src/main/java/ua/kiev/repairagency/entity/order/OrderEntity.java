package ua.kiev.repairagency.entity.order;

import ua.kiev.repairagency.entity.appliance.ElectricApplianceEntity;
import ua.kiev.repairagency.entity.user.CustomerEntity;
import ua.kiev.repairagency.entity.user.MasterEntity;
import ua.kiev.repairagency.entity.user.UserEntity;

public class OrderEntity {
    private final Long id;
    private final String title;
    private final Long price;
    private final CustomerEntity customerEntity;
    private final ElectricApplianceEntity applianceEntity;
    private final MasterEntity masterEntity;
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

    public CustomerEntity getCustomerEntity() {
        return customerEntity;
    }

    public MasterEntity getMasterEntity() {
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
        private CustomerEntity customerEntity;
        private ElectricApplianceEntity applianceEntity;
        private MasterEntity masterEntity;
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

        public OrderBuilder withCustomerEntity(CustomerEntity customerEntity) {
            this.customerEntity = customerEntity;
            return this;
        }

        public OrderBuilder withMasterEntity(MasterEntity masterEntity) {
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

    @Override
    public String toString() {
        return "Order [id=" + id + ", title=" + applianceEntity.toString() + ", price=" + price + "master=" + masterEntity.getName() + masterEntity.getSurname() + "]";
    }
}
