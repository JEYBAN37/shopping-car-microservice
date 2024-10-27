package com.example.car.domain.model.constant;
public class CarConstant {
    private CarConstant() {
        throw new IllegalStateException("Utility class");
    }
    public static final String TASK_NOT_FOUND_MESSAGE_ERROR = "No found Car with id %s";
    public static final String MESSAGE = "message";
    public static final String TIMESTAMP = "timestamp";
    public static final String DETAILS = "details";
    public static final int ZERO_CONSTANT = 0;
    public static final String ARTICLE_MANDATORY = "id article is mandatory";
    public static final String ARTICLE_MESSAGE_MIN_ERROR = "id article must  be greater than zero";
    public static final String QUANTITY_MANDATORY = "quantity is mandatory";
    public static final String USER_MANDATORY = "user is mandatory";
    public static final String QUANTITY_MESSAGE_MIN_ERROR = "Quantity must be greater than zero";
    public static final String USER_MESSAGE_MIN_ERROR = "User must be greater than zero";
    public static final int MAXIMUM_ALLOW_LETTERS = 50;
    public static final String MAX_DATE_CREATE = "The update date cannot be earlier than the creation date";
    public static final String MESSAGE_DATE_NOT_NULL = "date don't be null";
    public static final String MESSAGE_ERROR_ADD = "Car Exist";
    public static final String MESSAGE_ERROR_REMOVE = "Error To Delete";
    public static final String USER_EXIST = "Car of User exists";
    public static final String PRICE_MIN_ZERO = "price article must  be greater than zero";
    public static final String INVALID_ID_MESSAGE = "Invalid article ID";
    public static final String ARTICLE_ID = " for article ID: ";
    public static final String ARTICLES_NOT_EMPTY = "The articles map cannot be null or empty";
    public static final String  CAR_NO_EXIST = "Car of User Not Exist";
    public static final String ARTICLE_NO_EXIST = "Article no exists";
    public static final String ARTICLE_NO_STOCK = " no quantity in Stock";
    public static final String ARTICLE_THREE_CATEGORIES = "The articles must have three categories";
    public static final String MESSAGE_PAGE_VALID = "Page index must be non-negative and size must be greater than zero.";
    public static final String NAME = "name";
    public static final String ID = "id";
    public static final String CATEGORY = "categories";
    public static final String BRAND = "brand";
}
