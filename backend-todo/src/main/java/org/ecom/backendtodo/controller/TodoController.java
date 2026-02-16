package org.ecom.backendtodo.controller;


import lombok.RequiredArgsConstructor;
import org.ecom.backendtodo.dto.TodoRequest;
import org.ecom.backendtodo.model.Todo;
import org.ecom.backendtodo.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<Todo> createTodo(@RequestBody TodoRequest request) {
        Todo response = todoService.createTodo(request.getTitle());
        return ResponseEntity.ok(response);
    }


    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodo() {
        List<Todo> response = todoService.getAllTodo();
        return ResponseEntity.ok(response);
    }


}
