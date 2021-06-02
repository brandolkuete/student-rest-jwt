package com.brandolkuete.scolairebackendrest.config;

import java.util.Date;

public class SecurityConstants {
    public static final String SECRET= "brandolkuete-key";
    public static final long EXPIRATION_TIME=  360000000; // 1 jour
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING= "Authorization";
}
