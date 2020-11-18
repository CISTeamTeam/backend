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
    public static final String CREATE_USER = "/createUser";
    public static final String UPDATE_USER = "/updateUser";
    public static final String UPDATE_USER_ATTRIBUTE = "/updateUserAttribute";
    public static final String GET_COMMENT = "/getComment";

    // Params
    public static final String ID_PARAM = "id";
    public static final String BIO_PARAM = "bio";
    public static final String PFP_URL_PARAM = "profilePictureURL";
    public static final String USERNAME_PARAM = "username";
    public static final String POSTS_PARAM = "posts";
    public static final String NAME_PARAM =  "name";
    public static final String ATTRIBUTE_PARAM = "attribute";
    public static final String NEW_PARAM = "new";
    public static final String POINTS_PARAM = "points";


    // Status
    public static final String SUCCESS = "{\"status\":\"success\"}";
    public static final String FAILURE = "{\"status\":\"failure\"}";

    // Error Messages
    public static final String JSONError = "JSON Error";
}
