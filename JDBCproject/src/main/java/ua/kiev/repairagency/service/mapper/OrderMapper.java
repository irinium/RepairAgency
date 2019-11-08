package ua.kiev.repairagency.service.mapper;

import ua.kiev.repairagency.domain.order.Order;
import ua.kiev.repairagency.domain.user.Customer;
import ua.kiev.repairagency.domain.user.Master;
import ua.kiev.repairagency.entity.order.OrderEntity;
import ua.kiev.repairagency.entity.user.CustomerEntity;

public class OrderMapper {
    private final ApplianceMapper applianceMapper;
    private final UserMapper userMapper;

    public OrderMapper(ApplianceMapper applianceMapper, UserMapper userMapper) {
        this.applianceMapper = applianceMapper;
        this.userMapper = userMapper;
    }

    public Order mapOrderEntityToOrder(OrderEntity orderEntity) {
        return new Order.OrderBuilder()
                .withId(orderEntity.getId())
                .withTitle(orderEntity.getTitle())
                .withPrice(orderEntity.getPrice())
                .withAppliance(applianceMapper.mapApplianceEntityToAppliance(orderEntity.getApplianceEntity()))
                .withCustomer((Customer) userMapper.mapUserEntityToUser(orderEntity.getCustomerEntity()))
                .withMaster((Master) userMapper.mapUserEntityToUser(orderEntity.getMasterEntity()))
                .withState(orderEntity.getState())
                .build();
    }

    public OrderEntity mapOrderToOrderEntity(Order order) {
        return new OrderEntity.OrderBuilder()
                .withTitle(order.getTitle())
                .withPrice(order.getPrice())
                .withApplianceEntity(applianceMapper.mapApplianceToApplianceEntity(order.getAppliance()))
                .withCustomerEntity((CustomerEntity) userMapper.mapUserToUserEntity(order.getCustomer()))
                .build();
    }
}
