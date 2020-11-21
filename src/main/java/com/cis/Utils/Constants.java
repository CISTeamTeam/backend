package com.cis.Utils;

public class Constants {
    public static final int PORT = 8000;
    public static final String SAVE_FILE = "save.txt";

    // Paths
    public static final String CLEAR_DATA = "/clearData";
    public static final String GET_POST = "/getPost";
    public static final String GET_POSTS = "/getPosts";
    public static final String CREATE_POST = "/createPost";
    public static final String CAN_RATE_POST = "/canRatePost";
    public static final String RATE_POST = "/ratePost";
    public static final String GET_POST_POINTS = "/getPostPoints";
    public static final String AUTH = "/authenticate";
    public static final String GET_USER = "/getUser";
    public static final String CREATE_USER = "/createUser";
    public static final String UPDATE_USER = "/updateUser";
    public static final String GET_USER_POINTS = "/getUserPoints";
    public static final String SPEND_POINTS = "/spendPoints";
    public static final String GET_COMMENT = "/getComment";
    public static final String POST_COMMENT = "/postComment";
    public static final String GET_DISCOUNT = "/getDiscount";
    public static final String GET_DISCOUNTS = "/getDiscounts";
    public static final String GET_CHALLENGE = "/getChallenge";
    public static final String GET_CHALLENGES = "/getChallenges";

    // Params
    public static final String ID_PARAM = "id";
    public static final String USER_ID_PARAM = "userID";
    public static final String POST_ID_PARAM = "postID";
    public static final String PAGING_HASH = "hash";
    public static final String BIO_PARAM = "bio";
    public static final String URL_PARAM = "url";
    public static final String PFP_URL_PARAM = "profilePictureURL";
    public static final String USERNAME_PARAM = "username";
    public static final String NAME_PARAM =  "name";
    public static final String POINTS_PARAM = "points";
    public static final String TEXT_PARAM = "text";
    public static final String CREATION_DATE = "creationDate";
    public static final String DESCRIPTION_PARAM = "description";
    public static final String RATING_PARAM = "rating";

    // Status
    public static final String SUCCESS = "{\"status\":\"success\"}";
    public static final String FAILURE = "{\"status\":\"failure\"}";

    // Error Messages
    public static final String JSONError = "JSON Error";
}
