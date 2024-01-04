package pt.sofco.graha.utilities;

public class Constants {

    // status code
    public static final Integer OK = 200;
    public static final Integer CREATED = 201;
    public static final Integer ACCEPTED = 202;
    public static final Integer BAD_REQUEST = 400;
    public static final Integer UNAUTHORIZED = 401;
    public static final Integer PAYMENT_REQUIRED = 402;
    public static final Integer FORBIDDEN = 403;
    public static final Integer NOT_FOUND = 404;
    public static final Integer METHOD_NOT_ALLOWED = 405;
    public static final Integer INTERNAL_SERVER_ERROR = 500;
    public static final Integer NOT_IMPLEMENTED = 501;
    public static final Integer BAD_GATEWAY = 502;

    // message
    public static final String VALIDATION_MESSAGE = "Validation errors in your request";
    public static final String BAD_REQUEST_MESSAGE = "The request was not valid";
    public static final String CREATE_MESSAGE = "The item was created successfully";
    public static final String UPDATE_MESSAGE = "The item was updated successfully";
    public static final String DELETE_MESSAGE = "The item was deleted successfully";
    public static final String ITEM_EXIST_MESSAGE = "The item exist";

}
