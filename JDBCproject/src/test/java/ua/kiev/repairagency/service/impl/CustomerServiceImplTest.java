package ua.kiev.repairagency.service.impl;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.kiev.repairagency.dao.ApplianceDao;
import ua.kiev.repairagency.dao.OrderDao;
import ua.kiev.repairagency.dao.UserDao;
import ua.kiev.repairagency.dao.impl.ResponseDaoImpl;
import ua.kiev.repairagency.service.CustomerService;
import ua.kiev.repairagency.service.PasswordEncoder;
import ua.kiev.repairagency.service.mapper.OrderMapper;
import ua.kiev.repairagency.service.mapper.ResponseMapper;
import ua.kiev.repairagency.service.mapper.UserMapper;
import ua.kiev.repairagency.service.validator.Validator;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceImplTest {
    private CustomerService customerService;

    @InjectMocks
    OrderDao orderDao;
    @InjectMocks
    UserDao userDao;
    @InjectMocks
    ApplianceDao applianceDao;
    @Mock
    OrderMapper orderMapper;
    @Mock
    UserMapper userMapper;
    @InjectMocks
    ResponseDaoImpl responseDao;
    @Mock
    ResponseMapper responseMapper;
    @Mock
    PasswordEncoder passwordEncoder;
    @Mock
    Validator validator;

    @Before
    public void init(){
        customerService = new CustomerServiceImpl(userDao,orderDao,applianceDao,passwordEncoder,
                validator,userMapper,orderMapper,responseDao,responseMapper);
    }

   /* @Test(expected = Exception.class)
    public void checkForNullArgument() {
        if (customerService.createResponse(null) == 0){
            throw new NullPointerException("");
        }
    }

    @Test
    public void whileListIsEmptyFirstNodeIs0() {
        User user = new User.UserBuilder().build();
        if(customerService.findAllOrders(user).isEmpty())
            assertEquals(customerService.findAllOrders(user).get(0),null);
    }

    @Test
    public void whenMakingOrderApplianceSavedToOrderRepo() {
        ElectricAppliance appliance = new ElectricAppliance.ElectricApplianceBuilder()
                .withId(13L)
                .withName("refrigerator")
                .withDisrepair("crashed")
                .build();
        Customer customer = new Customer.CustomerBuilder().build();
        customerService.makeOrder(appliance,customer,"");
        Optional<OrderEntity> byId = orderDao.findById(1L);
        assertSame(byId.get().getId(), appliance.getId());
    }

    @Test
    public void checkMassagingBetweenRepoAndServiceInFindingOrders() {
        User user = new User.UserBuilder().build();
       when(customerService.findAllOrders(user)).thenReturn(Collections.emptyList());
    }*/
}