package com.paperized.productstore.security;

public class AuthRole {
    public static final String USER = "ROLE_USER";
    public static final String ADMIN = "ROLE_ADMIN";

    public static final String AUTH_ROLE_USER = "hasRole('USER')";
    public static final String AUTH_ROLE_ADMIN = "hasRole('ADMIN')";
}
