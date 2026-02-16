package org.ecom.backendtodo.service.impl;

import lombok.RequiredArgsConstructor;
import org.ecom.backendtodo.model.Todo;
import org.ecom.backendtodo.repository.TodoRepository;
import org.ecom.backendtodo.service.TodoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {


    private final TodoRepository todoRepository;

    @Override
    public Todo createTodo(String request) {
        Todo todo = Todo.builder()
                .title(request)
                .build();
        Todo savedTodo = todoRepository.save(todo);
        return savedTodo;
    }

    @Override
    public List<Todo> getAllTodo() {
        return todoRepository.findAll();
    }
}
