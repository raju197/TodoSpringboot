package com.example.demo.Service;

import com.example.demo.exception.TodoCollectionException;
import com.example.demo.model.TodoDTO;
import com.example.demo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.Optional;
@Service
public class TodoServiceImpl implements TodoService {
    @Autowired
    private TodoRepository todoRepo;
    @Override
    public void createTodo(TodoDTO todo) throws ConstraintViolationException ,TodoCollectionException  {
       Optional<TodoDTO> todoOptional= todoRepo.findByTodo(todo.getTodo());
       if(todoOptional.isPresent()){
           throw  new TodoCollectionException(TodoCollectionException.TodoAlreadyExists());
       }else{
           todo.setCreatedAt(new Date(System.currentTimeMillis()));
           todoRepo.save(todo);
       }

    }
}
