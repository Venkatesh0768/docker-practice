package org.ecom.backendtodo.controller;


import lombok.RequiredArgsConstructor;
import org.ecom.backendtodo.dto.TodoRequestDto;
import org.ecom.backendtodo.dto.TodoResponseDto;
import org.ecom.backendtodo.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<TodoResponseDto> createTodo(@RequestBody  TodoRequestDto requestDto){
        TodoResponseDto responseDto = todoService.createTodo(requestDto);
        return  new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{userid}")
    public ResponseEntity<List<TodoResponseDto>> getAllTodoByUserId(@PathVariable UUID userid){
        List<TodoResponseDto> responseDtos = todoService.getAllTodos(userid);
        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
     }
}
