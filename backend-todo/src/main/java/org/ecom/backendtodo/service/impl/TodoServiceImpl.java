package org.ecom.backendtodo.service.impl;

import org.ecom.backendtodo.dto.TodoRequestDto;
import org.ecom.backendtodo.dto.TodoResponseDto;
import org.ecom.backendtodo.exceptions.TodoNotFoundException;
import org.ecom.backendtodo.exceptions.UserNotFoundException;
import org.ecom.backendtodo.model.Todo;
import org.ecom.backendtodo.model.User;
import org.ecom.backendtodo.repository.TodoRepository;
import org.ecom.backendtodo.repository.UserRepository;
import org.ecom.backendtodo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final UserRepository userRepository;
    private final TodoRepository todoRepository;

    @Override
    public TodoResponseDto createTodo(TodoRequestDto requestDto) {
        if (requestDto.getUserId() == null) {
            throw new UserNotFoundException("User id is required to create a todo");
        }

        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User Not Found"));

        Todo todo = Todo.builder()
                .title(requestDto.getTitle())
                .completed(requestDto.isCompleted())
                .user(user)
                .build();


        Todo saveTodo = todoRepository.save(todo);

        return toDtos(saveTodo);
    }

    @Override
    public List<TodoResponseDto> getAllTodos(UUID userId) {
        if(!userRepository.existsById(userId)){
            throw  new UserNotFoundException("User Not Found");
        }

        List<Todo> allTodos = todoRepository.findAllByUser_Id(userId);
        return allTodos.stream().map(this::toDtos).toList();
    }

    @Override
    public TodoResponseDto getTodoById(UUID id) {
        return null;
    }

    @Override
    public TodoResponseDto updateTodo(UUID todoId, TodoRequestDto requestDto) {
        return null;
    }

    @Override
    public void deleteTodo(UUID todoId) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(
                () -> new TodoNotFoundException("The Todo not found")
        );

        todoRepository.deleteById(todoId);
    }

    public TodoResponseDto toDtos(Todo todo) {
        User user = todo.getUser();
        return TodoResponseDto.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .completed(todo.isCompleted())
                .userId(user != null ? user.getId() : null)
                .username(user != null ? user.getUsername() : null)
                .build();
    }
}
