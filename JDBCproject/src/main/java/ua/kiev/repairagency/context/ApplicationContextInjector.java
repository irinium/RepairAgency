package ua.kiev.repairagency.context;

import ua.kiev.repairagency.controller.command.Command;
import ua.kiev.repairagency.controller.command.manager.GetAllCustomers;
import ua.kiev.repairagency.controller.command.manager.RegisterMasterCommand;
import ua.kiev.repairagency.controller.command.user.*;
import ua.kiev.repairagency.dao.ApplianceDao;
import ua.kiev.repairagency.dao.DataBaseConnector;
import ua.kiev.repairagency.dao.OrderDao;
import ua.kiev.repairagency.dao.UserDao;
import ua.kiev.repairagency.dao.impl.ApplianceDaoImpl;
import ua.kiev.repairagency.dao.impl.OrderDaoImpl;
import ua.kiev.repairagency.dao.impl.ResponseDaoImpl;
import ua.kiev.repairagency.dao.impl.UserDaoImpl;
import ua.kiev.repairagency.service.*;
import ua.kiev.repairagency.service.impl.*;
import ua.kiev.repairagency.service.mapper.ApplianceMapper;
import ua.kiev.repairagency.service.mapper.OrderMapper;
import ua.kiev.repairagency.service.mapper.ResponseMapper;
import ua.kiev.repairagency.service.mapper.UserMapper;
import ua.kiev.repairagency.service.validator.Validator;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class ApplicationContextInjector {
    private static final DataBaseConnector CONNECTOR = new DataBaseConnector("database");

    private static final PasswordEncoder PASSWORD_ENCODER = new PasswordEncoder();
    private static final Validator VALIDATOR = new Validator();

    private static final UserMapper USER_MAPPER = new UserMapper();
    private static final ApplianceMapper APPLIANCE_MAPPER = new ApplianceMapper(USER_MAPPER);
    private static final OrderMapper ORDER_MAPPER = new OrderMapper(APPLIANCE_MAPPER, USER_MAPPER);
    private static final ResponseMapper RESPONSE_MAPPER = new ResponseMapper(USER_MAPPER);

    private static final UserDao USER_DAO = new UserDaoImpl(CONNECTOR);
    private static final ApplianceDao APPLIANCE_DAO = new ApplianceDaoImpl(CONNECTOR,USER_DAO);
    private static final OrderDao ORDER_DAO = new OrderDaoImpl(USER_DAO, APPLIANCE_DAO, CONNECTOR);
    private static final ResponseDaoImpl RESPONSE_DAO = new ResponseDaoImpl(USER_DAO, CONNECTOR);

    private static final UserGenericService USER_GENERIC_SERVICE =
            new UserGenericService(PASSWORD_ENCODER, USER_DAO, VALIDATOR, USER_MAPPER);
    private static final ManagerService MANAGER_SERVICE = new ManagerServiceImpl(USER_DAO, APPLIANCE_DAO,
            ORDER_DAO, PASSWORD_ENCODER, VALIDATOR, USER_MAPPER, ORDER_MAPPER);
    private static final CustomerService CUSTOMER_SERVICE = new CustomerServiceImpl(USER_DAO, ORDER_DAO,
            APPLIANCE_DAO, PASSWORD_ENCODER, VALIDATOR, USER_MAPPER, ORDER_MAPPER, RESPONSE_DAO, RESPONSE_MAPPER);
    private static final MasterService MASTER_SERVICE = new MasterServiceImpl(PASSWORD_ENCODER, USER_DAO,
            VALIDATOR, USER_MAPPER, ORDER_MAPPER, ORDER_DAO);
    private static final OrderService ORDER_SERVICE = new OrderServiceImpl(ORDER_DAO, ORDER_MAPPER);

    private static final Command LOGIN_COMMAND = new LoginCommand(USER_GENERIC_SERVICE);
    private static final Command LOGOUT_COMMAND = new LogoutCommand();
    private static final Command REGISTER_COMMAND = new RegisterCommand(USER_GENERIC_SERVICE, MANAGER_SERVICE);
    private static final Command GET_ALL_CUSTOMERS = new GetAllCustomers(USER_GENERIC_SERVICE);
    private static final Command MAKE_ORDER = new MakeOrderCommand(CUSTOMER_SERVICE);
    private static final Command ORDER_LIST = new OrderListCommand(ORDER_SERVICE);
    private static final Command LEAVE_FEEDBACK = new LeaveFeedback(CUSTOMER_SERVICE);
    private static final Command REGISTER_MASTER = new RegisterMasterCommand(USER_GENERIC_SERVICE);
    private static final Map<String, Command> USER_COMMAND_NAME_TO_COMMAND = initUserCommand();

    private static Map<String, Command> initUserCommand() {
        Map<String, Command> userCommandNameToCommand = new HashMap<>();
        userCommandNameToCommand.put("login", LOGIN_COMMAND);
        userCommandNameToCommand.put("logout", LOGOUT_COMMAND);
        userCommandNameToCommand.put("register", REGISTER_COMMAND);
        userCommandNameToCommand.put("listUsers", GET_ALL_CUSTOMERS);
        userCommandNameToCommand.put("makeOrder", MAKE_ORDER);
        userCommandNameToCommand.put("orderList", ORDER_LIST);
        userCommandNameToCommand.put("feedback", LEAVE_FEEDBACK);
        userCommandNameToCommand.put("registerMaster", REGISTER_MASTER);

        return Collections.unmodifiableMap(userCommandNameToCommand);
    }

    private static ApplicationContextInjector applicationContextInjector;

    private ApplicationContextInjector() {

    }

    public static ApplicationContextInjector getInstance() {
        if (applicationContextInjector == null) {
            synchronized (ApplicationContextInjector.class) {
                if (applicationContextInjector == null) {
                    applicationContextInjector = new ApplicationContextInjector();
                }
            }
        }
        return applicationContextInjector;
    }

    public Map<String, Command> getUserCommands() {
        return USER_COMMAND_NAME_TO_COMMAND;
    }

    public static UserGenericService getUserGenericService() {
        return USER_GENERIC_SERVICE;
    }

    public static OrderService getOrderService() {
        return ORDER_SERVICE;
    }

    public static CustomerService getCustomerService() {
        return CUSTOMER_SERVICE;
    }
}
