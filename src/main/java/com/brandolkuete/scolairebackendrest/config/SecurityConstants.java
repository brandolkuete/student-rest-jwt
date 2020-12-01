package com.brandolkuete.scolairebackendrest.config;

public class SecurityConstants {
    public static final String SECRET= "brandolkuete-key";
    public static final long EXPIRATION_TIME= 3600; // 1 jour
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING= "Authorization";
}
