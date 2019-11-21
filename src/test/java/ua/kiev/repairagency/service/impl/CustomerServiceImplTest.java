package ua.kiev.repairagency.service.impl;

import org.junit.Before;
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

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceImplTest {
    private static final Appliance APPLIANCE = Appliance.builder().build();
    private static final ApplianceEntity APPLIANCE_ENTITY = ApplianceEntity.builder().build();
    private static final User USER = User.builder().build();
    private static final UserEntity USER_ENTITY = UserEntity.builder().build();
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

    @Before
    public void init() {
        customerService = new CustomerServiceImpl(userDao, orderDao, passwordEncoder,
                userValidator, userMapper, orderMapper, responseDao, responseMapper);
    }


    @Test
    public void makeOrderShouldSaveOrder() {
        when(userMapper.mapUserToUserEntity(USER)).thenReturn(null);
        when(applianceMapper.mapApplianceToApplianceEntity(APPLIANCE)).thenReturn(null);
        when(orderMapper.mapOrderToOrderEntity(ORDER)).thenReturn(ORDER_ENTITY);

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
}