package ua.kiev.repairagency.context;

import ua.kiev.repairagency.controller.command.Command;
import ua.kiev.repairagency.controller.command.authentification.LoginCommand;
import ua.kiev.repairagency.controller.command.authentification.LogoutCommand;
import ua.kiev.repairagency.controller.command.authentification.RegisterCommand;
import ua.kiev.repairagency.controller.command.authentification.ShowResponsesCommand;
import ua.kiev.repairagency.controller.command.manager.OrderListCommand;
import ua.kiev.repairagency.controller.command.manager.RegisterMasterCommand;
import ua.kiev.repairagency.controller.command.manager.UpdateOrder;
import ua.kiev.repairagency.controller.command.manager.UserListCommand;
import ua.kiev.repairagency.controller.command.master.AcceptOrderCommand;
import ua.kiev.repairagency.controller.command.master.ChangePasswordCommand;
import ua.kiev.repairagency.controller.command.master.CompleteOrderCommand;
import ua.kiev.repairagency.controller.command.master.MasterAcceptedOrdersCommand;
import ua.kiev.repairagency.controller.command.master.MasterSelectOrdersCommand;
import ua.kiev.repairagency.controller.command.customer.LeaveFeedbackCommand;
import ua.kiev.repairagency.controller.command.customer.MakeOrderCommand;
import ua.kiev.repairagency.controller.command.customer.OrdersOfUserCommand;
import ua.kiev.repairagency.dao.ApplianceDao;
import ua.kiev.repairagency.dao.DataBaseConnector;
import ua.kiev.repairagency.dao.OrderDao;
import ua.kiev.repairagency.dao.UserDao;
import ua.kiev.repairagency.dao.impl.ApplianceDaoImpl;
import ua.kiev.repairagency.dao.impl.OrderDaoImpl;
import ua.kiev.repairagency.dao.impl.ResponseDaoImpl;
import ua.kiev.repairagency.dao.impl.UserDaoImpl;
import ua.kiev.repairagency.service.CustomerService;
import ua.kiev.repairagency.service.ManagerService;
import ua.kiev.repairagency.service.MasterService;
import ua.kiev.repairagency.service.OrderService;
import ua.kiev.repairagency.service.UserGenericService;
import ua.kiev.repairagency.service.encoder.PasswordEncoder;
import ua.kiev.repairagency.service.impl.CustomerServiceImpl;
import ua.kiev.repairagency.service.impl.ManagerServiceImpl;
import ua.kiev.repairagency.service.impl.MasterServiceImpl;
import ua.kiev.repairagency.service.impl.OrderServiceImpl;
import ua.kiev.repairagency.service.impl.UserGenericServiceImpl;
import ua.kiev.repairagency.service.mapper.ApplianceMapper;
import ua.kiev.repairagency.service.mapper.OrderMapper;
import ua.kiev.repairagency.service.mapper.ResponseMapper;
import ua.kiev.repairagency.service.mapper.UserMapper;
import ua.kiev.repairagency.service.validator.UserValidator;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class ApplicationContextInjector {
    private static final DataBaseConnector CONNECTOR = new DataBaseConnector("database");

    private static final PasswordEncoder PASSWORD_ENCODER = new PasswordEncoder();
    private static final UserValidator USER_VALIDATOR = new UserValidator();

    private static final UserMapper USER_MAPPER = new UserMapper();
    private static final ApplianceMapper APPLIANCE_MAPPER = new ApplianceMapper(USER_MAPPER);
    private static final OrderMapper ORDER_MAPPER = new OrderMapper(APPLIANCE_MAPPER, USER_MAPPER);
    private static final ResponseMapper RESPONSE_MAPPER = new ResponseMapper(USER_MAPPER);

    private static final UserDao USER_DAO = new UserDaoImpl(CONNECTOR);
    private static final ApplianceDao APPLIANCE_DAO = new ApplianceDaoImpl(CONNECTOR, USER_DAO);
    private static final OrderDao ORDER_DAO = new OrderDaoImpl(USER_DAO, APPLIANCE_DAO, CONNECTOR);
    private static final ResponseDaoImpl RESPONSE_DAO = new ResponseDaoImpl(USER_DAO, CONNECTOR);

    private static final UserGenericService USER_GENERIC_SERVICE =
            new UserGenericServiceImpl(PASSWORD_ENCODER, USER_DAO, USER_VALIDATOR, USER_MAPPER);
    private static final ManagerService MANAGER_SERVICE = new ManagerServiceImpl(USER_DAO,
            ORDER_DAO, PASSWORD_ENCODER, USER_VALIDATOR, USER_MAPPER, ORDER_MAPPER);
    private static final CustomerService CUSTOMER_SERVICE = new CustomerServiceImpl(USER_DAO, ORDER_DAO,
            PASSWORD_ENCODER, USER_VALIDATOR, USER_MAPPER, ORDER_MAPPER, RESPONSE_DAO, RESPONSE_MAPPER);
    private static final MasterService MASTER_SERVICE = new MasterServiceImpl(PASSWORD_ENCODER, USER_DAO,
            USER_VALIDATOR, USER_MAPPER, ORDER_MAPPER, ORDER_DAO);
    private static final OrderService ORDER_SERVICE =
            new OrderServiceImpl(ORDER_DAO, ORDER_MAPPER, RESPONSE_DAO, RESPONSE_MAPPER, USER_MAPPER);

    private static final Command LOGIN_COMMAND = new LoginCommand(USER_GENERIC_SERVICE);
    private static final Command LOGOUT_COMMAND = new LogoutCommand();
    private static final Command REGISTER_COMMAND = new RegisterCommand(USER_GENERIC_SERVICE, MANAGER_SERVICE);
    private static final Command MAKE_ORDER = new MakeOrderCommand(CUSTOMER_SERVICE);
    private static final Command LEAVE_FEEDBACK = new LeaveFeedbackCommand(CUSTOMER_SERVICE);
    private static final Command REGISTER_MASTER = new RegisterMasterCommand(USER_GENERIC_SERVICE);
    private static final Command UPDATE_ORDER = new UpdateOrder(MANAGER_SERVICE);
    private static final Command CHANGE_PASSWORD = new ChangePasswordCommand(MASTER_SERVICE);
    private static final Command ACCEPT_ORDER = new AcceptOrderCommand(MASTER_SERVICE, MANAGER_SERVICE);
    private static final Command ORDERS_LIST = new OrderListCommand(ORDER_SERVICE);
    private static final Command USERS_LIST = new UserListCommand(USER_GENERIC_SERVICE);
    private static final Command ORDERS_OF_USER = new OrdersOfUserCommand(CUSTOMER_SERVICE, ORDER_SERVICE);
    private static final Command MASTER_SELECT_ORDERS = new MasterSelectOrdersCommand(ORDER_SERVICE);
    private static final Command MASTER_ACCEPTED_ORDERS = new MasterAcceptedOrdersCommand(ORDER_SERVICE, MASTER_SERVICE);
    private static final Command COMPLETE_ORDER = new CompleteOrderCommand(MASTER_SERVICE, MANAGER_SERVICE);
    private static final Command SHOW_RESPONSES = new ShowResponsesCommand(ORDER_SERVICE);

    private static final Map<String, Command> CUSTOMER_COMMAND_NAME_TO_COMMAND = initCustomerCommand();
    private static final Map<String, Command> MANAGER_COMMAND_NAME_TO_COMMAND = initManagerCommand();
    private static final Map<String, Command> MASTER_COMMAND_NAME_TO_COMMAND = initMasterCommand();
    private static final Map<String, Command> AUTHENTICATION_COMMAND_NAME_TO_COMMAND = initAuthorisationCommand();

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

    public Map<String, Command> getCustomerCommands() {
        return CUSTOMER_COMMAND_NAME_TO_COMMAND;
    }

    public Map<String, Command> getManagerCommands() {
        return MANAGER_COMMAND_NAME_TO_COMMAND;
    }

    public Map<String, Command> getMasterCommands() {
        return MASTER_COMMAND_NAME_TO_COMMAND;
    }

    public Map<String, Command> getAuthenticationCommands() {
        return AUTHENTICATION_COMMAND_NAME_TO_COMMAND;
    }


    private static Map<String, Command> initCustomerCommand() {
        Map<String, Command> commandNameToCommand = new HashMap<>();
        commandNameToCommand.put("makeOrder", MAKE_ORDER);
        commandNameToCommand.put("feedback", LEAVE_FEEDBACK);
        commandNameToCommand.put("userOrderList", ORDERS_OF_USER);

        return Collections.unmodifiableMap(commandNameToCommand);
    }

    private static Map<String, Command> initManagerCommand() {
        Map<String, Command> commandNameToCommand = new HashMap<>();
        commandNameToCommand.put("registerMaster", REGISTER_MASTER);
        commandNameToCommand.put("updateOrder", UPDATE_ORDER);
        commandNameToCommand.put("orderList", ORDERS_LIST);
        commandNameToCommand.put("userList", USERS_LIST);

        return Collections.unmodifiableMap(commandNameToCommand);
    }

    private static Map<String, Command> initMasterCommand() {
        Map<String, Command> commandNameToCommand = new HashMap<>();
        commandNameToCommand.put("changePassword", CHANGE_PASSWORD);
        commandNameToCommand.put("masterAllOrders", MASTER_SELECT_ORDERS);
        commandNameToCommand.put("acceptOrder", ACCEPT_ORDER);
        commandNameToCommand.put("masterOrders", MASTER_ACCEPTED_ORDERS);
        commandNameToCommand.put("completeOrder", COMPLETE_ORDER);

        return Collections.unmodifiableMap(commandNameToCommand);
    }

    private static Map<String, Command> initAuthorisationCommand() {
        Map<String, Command> commandNameToCommand = new HashMap<>();
        commandNameToCommand.put("login", LOGIN_COMMAND);
        commandNameToCommand.put("logout", LOGOUT_COMMAND);
        commandNameToCommand.put("register", REGISTER_COMMAND);
        commandNameToCommand.put("responses", SHOW_RESPONSES);

        return Collections.unmodifiableMap(commandNameToCommand);
    }
}
