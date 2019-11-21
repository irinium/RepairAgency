package ua.kiev.repairagency.service.mapper;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.kiev.repairagency.domain.order.Response;
import ua.kiev.repairagency.domain.user.User;
import ua.kiev.repairagency.entity.order.ResponseEntity;
import ua.kiev.repairagency.entity.user.UserEntity;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ResponseMapperTest {
    private static final Long ID = 1L;
    private static final String TEXT = "Text";
    private static final User USER = User.builder().build();
    private static final UserEntity USER_ENTITY = UserEntity.builder().build();


    @InjectMocks
    private ResponseMapper responseMapper;

    @Mock
    private UserMapper userMapper;

    @Before
    public void init(){
        responseMapper = new ResponseMapper(userMapper);
    }

    @Test
    public void mapResponseEntityToResponseShouldReturnResponse() {
        when(userMapper.mapUserEntityToUser(USER_ENTITY)).thenReturn(USER);

        ResponseEntity responseEntity = new ResponseEntity(TEXT,USER_ENTITY);
        responseEntity.setId(ID);

        Response response = responseMapper.mapResponseEntityToResponse(responseEntity);

        assertThat("mapping id is failed", response.getId(), CoreMatchers.is(ID));
        assertThat("mapping text is failed", response.getText(), is(TEXT));
        assertThat("mapping user is failed", response.getUser(), is(USER));
    }

    @Test
    public void mapResponseToResponseEntityShouldReturnResponseEntity() {
        when(userMapper.mapUserToUserEntity(USER)).thenReturn(USER_ENTITY);

        Response response = new Response(TEXT,USER);

        ResponseEntity responseEntity = responseMapper.mapResponseToResponseEntity(response);

        assertThat("mapping text is failed", responseEntity.getText(), is(TEXT));
        assertThat("mapping user is failed", responseEntity.getUser(), is(USER_ENTITY));
    }
}