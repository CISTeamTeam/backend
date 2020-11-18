package com.cis.Utils;

public class Constants {
    public static final int PORT = 8000;

    // Paths
    public static final String AUTH = "/authenticate";
    public static final String GET_POST = "/getPost";
    public static final String GET_POSTS = "/getPosts";
    public static final String RATE_POST = "/ratePost";
    public static final String GET_POST_POINTS = "/getPostPoints";
    public static final String GET_USER = "/getUser";
    public static final String GET_USER_POINTS = "/getUserPoints";
    public static final String SPEND_POINTS = "/spendPoints";
    public static final String GET_DISCOUNTS = "/getDiscounts";

    // Params
    public static final String ID_PARAM = "id";
    public static final String USER_ID_PARAM = "userID";
    public static final String PAGING_HASH = "hash";

    // Status
    public static final String SUCCESS = "{\"status\":\"success\"}";
    public static final String FAILURE = "{\"status\":\"failure\"}";

    // Error Messages
    public static final String JSONError = "JSON Error";
}
