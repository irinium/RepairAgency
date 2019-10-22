package ua.kiev.appliances.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kiev.appliances.domain.order.OrderEntity;
import ua.kiev.appliances.repository.OrderRepository;

import java.util.List;

@Service
public class OrderServiceImpl {
    private OrderRepository orderRepository;

@Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void save(OrderEntity orderEntity) {
        if (orderEntity != null) {
            List<OrderEntity> orderEntities = orderRepository.getAll();
            if (!orderEntities.isEmpty()) {
                OrderEntity lastOrderEntity = orderEntities.get(orderEntities.size() - 1);
                orderEntity.setId(lastOrderEntity.getId() + 1);
                orderRepository.save(orderEntity);
            }
        }
    }

    public void delete(OrderEntity orderEntity) {
        if (orderEntity != null) {
            orderRepository.delete(orderEntity);
        }
    }

    public List<OrderEntity> getAll() {
        return orderRepository.getAll();
    }

}
