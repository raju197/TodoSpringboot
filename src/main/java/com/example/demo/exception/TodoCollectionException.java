package com.example.demo.exception;

public class TodoCollectionException extends Exception{
    public TodoCollectionException(String message){
        super(message);
    }
    public static String StringNotFoundException(String id){
        return  "Todo with "+id+"not found!!";
    }
    public static String TodoAlreadyExists(){
        return "Todo with given name already exits";
    }
}
