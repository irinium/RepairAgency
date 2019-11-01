package ua.kiev.repairagency.service.mapper;

import ua.kiev.repairagency.domain.order.Order;
import ua.kiev.repairagency.entity.order.OrderEntity;

public class OrderMapper {
    public Order mapOrderEntityToOrder(OrderEntity orderEntity) {
        return new Order(
                orderEntity.getId(),
                orderEntity.getApplianceId(),
                orderEntity.getPrice(),
                orderEntity.getUserId(),
                orderEntity.getMasterId(),
                orderEntity.getTitle());
    }

    public OrderEntity mapOrderToOrderEntity(Order order) {
        return new OrderEntity(
                order.getId(),
                order.getApplianceId(),
                order.getPrice(),
                order.getUserId(),
                order.getMasterId(),
                order.getTitle());
    }
}
