package org.ecom.backendtodo.service;

import org.ecom.backendtodo.dto.TodoRequestDto;
import org.ecom.backendtodo.dto.TodoResponseDto;

import java.util.List;
import java.util.UUID;

public interface TodoService {
    TodoResponseDto createTodo(TodoRequestDto requestDto);
    List<TodoResponseDto> getAllTodos(UUID userId);
    TodoResponseDto getTodoById(UUID id);
    TodoResponseDto updateTodo(UUID todoId , TodoRequestDto requestDto);
    void deleteTodo(UUID todoId);
}
