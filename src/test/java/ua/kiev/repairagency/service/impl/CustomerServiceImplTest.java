package ua.kiev.repairagency.service.impl;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.kiev.repairagency.dao.OrderDao;
import ua.kiev.repairagency.dao.UserDao;
import ua.kiev.repairagency.dao.impl.ResponseDaoImpl;
import ua.kiev.repairagency.domain.appliance.Appliance;
import ua.kiev.repairagency.domain.order.Order;
import ua.kiev.repairagency.domain.order.Response;
import ua.kiev.repairagency.domain.user.User;
import ua.kiev.repairagency.entity.appliance.ApplianceEntity;
import ua.kiev.repairagency.entity.order.OrderEntity;
import ua.kiev.repairagency.entity.order.ResponseEntity;
import ua.kiev.repairagency.entity.user.UserEntity;
import ua.kiev.repairagency.service.encoder.PasswordEncoder;
import ua.kiev.repairagency.service.mapper.ApplianceMapper;
import ua.kiev.repairagency.service.mapper.OrderMapper;
import ua.kiev.repairagency.service.mapper.ResponseMapper;
import ua.kiev.repairagency.service.mapper.UserMapper;
import ua.kiev.repairagency.service.validator.UserValidator;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceImplTest {
    private static final Appliance APPLIANCE = Appliance.builder().withDisrepair("broken").build();
    private static final ApplianceEntity APPLIANCE_ENTITY = ApplianceEntity.builder().withDisrepair("broken").build();
    private static final User USER = User.builder().withEmail("user@gmail.com").build();
    private static final UserEntity USER_ENTITY = UserEntity.builder().withEmail("user@gmail.com").build();
    private static final Order ORDER = Order.builder().withAppliance(APPLIANCE).withCustomer(USER).withTitle("").build();
    private static final OrderEntity ORDER_ENTITY = OrderEntity.builder().withApplianceEntity(APPLIANCE_ENTITY)
            .withCustomerEntity(USER_ENTITY).withTitle("").build();

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    private OrderDao orderDao;
    @Mock
    private UserDao userDao;
    @Mock
    private OrderMapper orderMapper;
    @Mock
    private ApplianceMapper applianceMapper;
    @Mock
    private UserMapper userMapper;
    @Mock
    private ResponseDaoImpl responseDao;
    @Mock
    private ResponseMapper responseMapper;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private UserValidator userValidator;


    @Test
    public void makeOrderShouldSaveOrder() {
        customerService.makeOrder(APPLIANCE, USER, "");

        verify(orderDao).save(null);
    }


    @Test
    public void createResponseShouldSaveResponse() {
        Response response = new Response("", null);
        ResponseEntity responseEntity = new ResponseEntity("", null);
        when(responseMapper.mapResponseToResponseEntity(response)).thenReturn(responseEntity);
        customerService.createResponse(response);

        verify(responseDao).save(responseEntity);
    }

    @Test
    public void findAllOrdersShouldReturnEmptyListWhenThereIsNoOrders(){
        List<Order> expected = emptyList();

        when(orderDao.findAll(any(Integer.class) , any(Integer.class))).thenReturn(emptyList());
        when(userMapper.mapUserToUserEntity(USER)).thenReturn(USER_ENTITY);

        List<Order> actual = customerService.findAllOrders(USER,1 , 10);

        assertEquals(expected, actual);
    }

    @Test
    public void findAllOrdersShouldReturnAllOrders() {
        List<OrderEntity> entities = singletonList(ORDER_ENTITY);

        when(userMapper.mapUserToUserEntity(USER)).thenReturn(USER_ENTITY);
        when(orderDao.findUserOrders(USER_ENTITY,1 , 5)).thenReturn(entities);
        when(orderMapper.mapOrderToOrderEntity(ORDER)).thenReturn(ORDER_ENTITY);

        customerService.findAllOrders(USER,1 , 5);
        verify(orderDao).findUserOrders(USER_ENTITY,1,5);
    }
}