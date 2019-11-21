package ua.kiev.repairagency.service.mapper;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.kiev.repairagency.domain.appliance.Appliance;
import ua.kiev.repairagency.domain.order.Order;
import ua.kiev.repairagency.domain.user.User;
import ua.kiev.repairagency.entity.appliance.ApplianceEntity;
import ua.kiev.repairagency.entity.order.OrderEntity;
import ua.kiev.repairagency.entity.user.UserEntity;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderMapperTest {
    private static final Long ID = 1L;
    private static final String TITLE = "Title";
    private static final Double PRICE = 0.00;
    private static final Boolean STATE = true;
    private static final Appliance APPLIANCE = Appliance.builder().build();
    private static final ApplianceEntity APPLIANCE_ENTITY = ApplianceEntity.builder().build();
    private static final User USER = User.builder().build();
    private static final UserEntity USER_ENTITY = UserEntity.builder().build();

    @InjectMocks
    private OrderMapper orderMapper;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Mock
    private ApplianceMapper applianceMapper;

    @Mock
    private UserMapper userMapper;

    @Before
    public void init(){
        orderMapper = new OrderMapper(applianceMapper,userMapper);
    }

    @Test
    public void mapOrderEntityToOrderShouldReturnOrder() {
        OrderEntity orderEntity = OrderEntity.builder()
                .withId(ID)
                .withTitle(TITLE)
                .withPrice(PRICE)
                .withApplianceEntity(APPLIANCE_ENTITY)
                .withCustomerEntity(USER_ENTITY)
                .withState(STATE)
                .build();

        when(applianceMapper.mapApplianceEntityToAppliance(APPLIANCE_ENTITY)).thenReturn(APPLIANCE);
        when(userMapper.mapUserEntityToUser(USER_ENTITY)).thenReturn(USER);

        final Order order = orderMapper.mapOrderEntityToOrder(orderEntity);

        assertThat("mapping id is failed", order.getId(), is(ID));
        assertThat("mapping title is failed", order.getTitle(), is(TITLE));
        assertThat("mapping price is failed", order.getPrice(), is(PRICE));
        assertThat("mapping appliance is failed", order.getAppliance(), is(APPLIANCE));
        assertThat("mapping customer is failed", order.getCustomer(), is(USER));
        assertThat("mapping state is failed", order.getState(), is(STATE));
    }

    @Test
    public void mapOrderToOrderEntityShouldReturnOrderEntity() {
        Order order = Order.builder()
                .withId(ID)
                .withTitle(TITLE)
                .withPrice(PRICE)
                .withAppliance(APPLIANCE)
                .withCustomer(USER)
                .build();

        when(applianceMapper.mapApplianceToApplianceEntity(APPLIANCE)).thenReturn(APPLIANCE_ENTITY);
        when(userMapper.mapUserToUserEntity(USER)).thenReturn(USER_ENTITY);

        OrderEntity orderEntity = orderMapper.mapOrderToOrderEntity(order);

        assertThat("mapping id is failed", orderEntity.getId(), is(ID));
        assertThat("mapping title is failed", orderEntity.getTitle(), is(TITLE));
        assertThat("mapping price is failed", orderEntity.getPrice(), is(PRICE));
        assertThat("mapping appliance is failed", orderEntity.getApplianceEntity(), is(APPLIANCE_ENTITY));
        assertThat("mapping customer is failed", orderEntity.getCustomerEntity(), is(USER_ENTITY));
    }
}