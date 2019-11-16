package ua.kiev.repairagency.service.mapper;

import ua.kiev.repairagency.domain.order.Response;
import ua.kiev.repairagency.entity.order.ResponseEntity;

public class ResponseMapper {
    private final UserMapper userMapper;

    public ResponseMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public Response mapResponseEntityToResponse(ResponseEntity responseEntity) {
        return new Response(
                responseEntity.getText(),
                userMapper.mapUserEntityToUser(responseEntity.getUser()));
    }

    public ResponseEntity mapResponseToResponseEntity(Response response) {
        return new ResponseEntity(
                response.getText(),
                userMapper.mapUserToUserEntity(response.getUser()));
    }
}
