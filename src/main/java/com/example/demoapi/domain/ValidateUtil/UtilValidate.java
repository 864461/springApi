package com.example.demoapi.domain.ValidateUtil;
public class UtilValidate {


    public static boolean isEmpty(Object object){
        if(object == null){
            return true;
        }
        return false;
    }

    public static boolean isEmpty(String value){
        if(value == null){
            return true;
        }
        return false;
    }



}
