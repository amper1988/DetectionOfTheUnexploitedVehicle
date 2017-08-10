package com.belspec.app.utils;

import android.util.Base64;

public class Encode {
    private static String BASIC_AUTH_TEMPLATE = "Basic %1$s";
    public static String getBasicAuthTemplate(String login, String pass){
        return String.format(BASIC_AUTH_TEMPLATE, Base64.encodeToString((login+":"+pass).getBytes(), Base64.NO_WRAP));
    }
}