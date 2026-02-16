package org.ecom.backendtodo.service;

import org.ecom.backendtodo.model.Todo;

import java.util.List;
import java.util.UUID;

public interface TodoService {
    Todo createTodo(String title);
    List<Todo> getAllTodo();
}
