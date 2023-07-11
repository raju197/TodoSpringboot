package com.example.demo.Service;

import com.example.demo.exception.TodoCollectionException;
import com.example.demo.model.TodoDTO;

import javax.validation.ConstraintViolationException;

public interface TodoService {
    public  void createTodo(TodoDTO todo)throws ConstraintViolationException, TodoCollectionException;
}
