package com.example.demo.controllers;


import com.example.demo.Service.TodoService;
import com.example.demo.exception.TodoCollectionException;
import com.example.demo.model.TodoDTO;
import com.example.demo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class TodoController {
    @Autowired
    private TodoRepository todoRepo;
    @Autowired
    private TodoService todoService;
    @GetMapping("/todos")
    private ResponseEntity<?>getAllTodos(){
        List<TodoDTO> todos = todoRepo.findAll();
        if(todos.size() > 0){
            return new ResponseEntity<List<TodoDTO>>(todos,HttpStatus.OK);
        }else {
            return  new ResponseEntity<>("no todos available",HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping("getaApplicationStatus")
    public String getApplicationStatus(){
        return "application is running properl!!!";

    }

    @PostMapping("/todos")
    public ResponseEntity<?>createTodos(@RequestBody TodoDTO todo){
        try{
            todoService.createTodo(todo);

            return new ResponseEntity<TodoDTO>(todo, HttpStatus.OK);

        }catch (ConstraintViolationException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);

        }catch (TodoCollectionException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/todo/{id}")
    public ResponseEntity<?>getSingleTodo(@PathVariable("id") String id){
        Optional<TodoDTO>todoOptional = todoRepo.findById(id);
        if(todoOptional.isPresent()){
            return new ResponseEntity<>(todoOptional.get(),HttpStatus.OK);
        }else {
           return new ResponseEntity<>("Todo not found with id"+id,HttpStatus.NOT_FOUND);
        }


    }
    @PutMapping("/todo/{id}")
    public ResponseEntity<?>updateById(@PathVariable("id") String id,@RequestBody TodoDTO todo){
        Optional<TodoDTO>todoOptional = todoRepo.findById(id);
        if(todoOptional.isPresent()){
           TodoDTO todoSave =  todoOptional.get();
           todoSave.setCompleted(todo.getCompleted()!=null?todo.getCompleted():todoSave.getCompleted());
           todoSave.setTodo(todo.getTodo() != null ? todo.getTodo(): todoSave.getTodo());
           todoSave.setDescription(todo.getTodo() != null ? todo.getDescription(): todoSave.getDescription());
           todoSave.setUpdatedAt(new Date(System.currentTimeMillis()));
           todoRepo.save(todoSave);
           return new ResponseEntity<>(todoSave,HttpStatus.OK);

        }else {
            return new ResponseEntity<>("Todo not found with id"+id,HttpStatus.NOT_FOUND);
        }


    }
    @DeleteMapping("/todo/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable("id") String id){
        try {
            todoRepo.deleteById(id);
            return new ResponseEntity<>("successfully deleted with id:"+id,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/countTodo")
    public  String getTodoCollectionCount(){
        long result = todoRepo.count();
        return "Record found: "+result;
    }
    @GetMapping("/findAllTodos")
    public List<TodoDTO>findAllTodos(){
        return todoRepo.findAll();
    }
}
