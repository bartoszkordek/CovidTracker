package com.covid19.api.listener.exception;

public class FromDateAfterToDateException extends Exception{
    public FromDateAfterToDateException(String message){super(message);}
}
