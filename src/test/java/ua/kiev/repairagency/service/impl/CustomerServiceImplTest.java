package ua.kiev.repairagency.service.impl;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.kiev.repairagency.dao.ApplianceDao;
import ua.kiev.repairagency.dao.OrderDao;
import ua.kiev.repairagency.dao.UserDao;
import ua.kiev.repairagency.dao.impl.ResponseDaoImpl;
import ua.kiev.repairagency.domain.order.Order;
import ua.kiev.repairagency.domain.order.Response;
import ua.kiev.repairagency.domain.user.User;
import ua.kiev.repairagency.entity.user.UserEntity;
import ua.kiev.repairagency.service.CustomerService;
import ua.kiev.repairagency.service.PasswordEncoder;
import ua.kiev.repairagency.service.mapper.OrderMapper;
import ua.kiev.repairagency.service.mapper.ResponseMapper;
import ua.kiev.repairagency.service.mapper.UserMapper;
import ua.kiev.repairagency.service.validator.Validator;

import static java.util.Collections.emptyList;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceImplTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();
    private CustomerService customerService;

    @Mock
    private OrderDao orderDao;
    @Mock
    private UserDao userDao;
    @Mock
    private ApplianceDao applianceDao;
    @Mock
    private OrderMapper orderMapper;
    @Mock
    private UserMapper userMapper;
    @Mock
    private ResponseDaoImpl responseDao;
    @Mock
    private ResponseMapper responseMapper;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private Validator validator;

    @Before
    public void init(){
        customerService = new CustomerServiceImpl(userDao,orderDao,applianceDao,passwordEncoder,
                validator,userMapper,orderMapper,responseDao,responseMapper);
    }


    @Test
    public void registrationShouldNotReturnNull() {
        User user = User.builder().build();
        when(userDao.save(any(UserEntity.class))).thenReturn(1);
        User user1 = customerService.register(user);
        assertNotNull(user);
    }

    @Test(expected = Exception.class)
    public void loginShouldThrownExceptionWithIllegalArguments() {
        when(userDao.findByEmail("null")).thenThrow(NullPointerException.class);
        assertThat(customerService.login("null", "null"),null);
    }

    @Test
    public void makeOrderShouldReturnInt() {
        Order order = Order.builder().build();
        when(orderDao.save(orderMapper.mapOrderToOrderEntity(order))).thenReturn(1);
        assertEquals(customerService.makeOrder(order.getAppliance(), order.getCustomer(), order.getTitle()), 1);
    }

    @Test
    public void whenDaoReturnedEmptyListServiseReturnedToo() {
        when(orderDao.findAll(1,5)).thenReturn(emptyList());
        assertEquals(emptyList(),customerService.findAllOrders(null,1,5));
    }

    @Test
    public void createResponseShouldReturnSameValAsDaoMethodSave() {
        Response response = new Response("",null);
        when(responseDao.save(responseMapper.mapResponseToResponseEntity(response))).thenReturn(1);
        assertEquals(1,customerService.createResponse(response));
    }
}