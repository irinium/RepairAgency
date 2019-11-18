package ua.kiev.repairagency.service.mapper;

import ua.kiev.repairagency.domain.order.Order;
import ua.kiev.repairagency.entity.order.OrderEntity;

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
                .withCustomer(userMapper.mapUserEntityToUser(orderEntity.getCustomerEntity()))
                .withState(orderEntity.getState())
                .build();
    }

    public OrderEntity mapOrderToOrderEntity(Order order) {
        return new OrderEntity.OrderBuilder()
                .withId(order.getId())
                .withTitle(order.getTitle())
                .withPrice(order.getPrice())
                .withApplianceEntity(applianceMapper.mapApplianceToApplianceEntity(order.getAppliance()))
                .withCustomerEntity(userMapper.mapUserToUserEntity(order.getCustomer()))
                .build();
    }
}
