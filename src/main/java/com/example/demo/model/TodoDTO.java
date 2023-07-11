package com.example.demo.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "todos")
public class TodoDTO {
    @Id
    private String id;
    @NotNull(message = "todo can not be null")
    private String todo;
    @NotNull(message = "description can not be null")
    private String description;
    @NotNull(message = "completed can not be null")
    private Boolean completed;
    private Date createdAt;
    private Date updatedAt;
}
