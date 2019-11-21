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
import ua.kiev.repairagency.domain.appliance.Manufacturer;
import ua.kiev.repairagency.domain.appliance.Type;
import ua.kiev.repairagency.domain.user.User;
import ua.kiev.repairagency.entity.appliance.ApplianceEntity;
import ua.kiev.repairagency.entity.appliance.ManufacturerEntity;
import ua.kiev.repairagency.entity.appliance.TypeEntity;
import ua.kiev.repairagency.entity.user.UserEntity;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ApplianceMapperTest {
    private static final Long ID = 1L;
    private static final String NAME = "Name";
    private static final String MODEL = "Model";
    private static final String DISREPAIR = "Disrepair";
    private static final User USER = User.builder().build();
    private static final UserEntity USER_ENTITY = UserEntity.builder().build();

    @InjectMocks
    private ApplianceMapper applianceMapper;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Mock
    private UserMapper userMapper;

    @Before
    public void init(){
        applianceMapper = new ApplianceMapper(userMapper);
    }

    @Test
    public void mapApplianceEntityToApplianceShouldReturnAppliance() {
        final ApplianceEntity applianceEntity = ApplianceEntity.builder()
                .withId(ID)
                .withName(NAME)
                .withManufacturer(ManufacturerEntity.BOSCH)
                .withType(TypeEntity.CLIMATIC_APPLIANCE)
                .withModel(MODEL)
                .withUser(USER_ENTITY)
                .withDisrepair(DISREPAIR)
                .build();

        when(userMapper.mapUserEntityToUser(USER_ENTITY)).thenReturn(USER);

        final Appliance appliance = applianceMapper.mapApplianceEntityToAppliance(applianceEntity);
        assertThat("mapping id is failed", appliance.getId(), is(ID));
        assertThat("mapping name is failed", appliance.getName(), is(NAME));
        assertThat("mapping manufacturer is failed", appliance.getManufacturer(), is(Manufacturer.BOSCH));
        assertThat("mapping type is failed", appliance.getType(), is(Type.CLIMATIC_APPLIANCE));
        assertThat("mapping model is failed", appliance.getModel(), is(MODEL));
        assertThat("mapping user is failed", appliance.getUser(), is(USER));
        assertThat("mapping disrepair is failed", appliance.getDisrepair(), is(DISREPAIR));
    }

    @Test
    public void mapApplianceToApplianceEntityShouldReturnApplianceEntity() {
        final Appliance appliance = Appliance.builder()
                .withId(ID)
                .withName(NAME)
                .withManufacturer(Manufacturer.BOSCH)
                .withType(Type.CLIMATIC_APPLIANCE)
                .withModel(MODEL)
                .withUser(USER)
                .withDisrepair(DISREPAIR)
                .build();

        when(userMapper.mapUserToUserEntity(USER)).thenReturn(USER_ENTITY);

        final ApplianceEntity applianceEntity = applianceMapper.mapApplianceToApplianceEntity(appliance);
        assertThat("mapping id is failed", applianceEntity.getId(), is(ID));
        assertThat("mapping name is failed", applianceEntity.getName(), is(NAME));
        assertThat("mapping manufacturer is failed", applianceEntity.getManufacturerEntity(), is(ManufacturerEntity.BOSCH));
        assertThat("mapping type is failed", applianceEntity.getTypeEntity(), is(TypeEntity.CLIMATIC_APPLIANCE));
        assertThat("mapping model is failed", applianceEntity.getModel(), is(MODEL));
        assertThat("mapping user is failed", applianceEntity.getUserEntity(), is(USER_ENTITY));
        assertThat("mapping disrepair is failed", applianceEntity.getDisrepair(), is(DISREPAIR));
    }
}