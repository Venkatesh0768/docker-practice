import React, { useState, useEffect } from "react";
import { useUser } from "../context/UserContext";
import { fetchTodosByUserId, createTodo } from "../API/api";

const TodoPage = () => {
  const { user, login } = useUser();
  const [usernameInput, setUsernameInput] = useState("");
  const [todoTitle, setTodoTitle] = useState("");
  const [todos, setTodos] = useState([]);

  const fetchTodos = React.useCallback(async () => {
    if (!user?.id) return;
    try {
      const data = await fetchTodosByUserId(user.id);
      setTodos(data);
    } catch (error) {
      console.error("Error fetching todos:", error);
    }
  }, [user]);

  useEffect(() => {
    fetchTodos();
  }, [fetchTodos]);

  const handleLogin = (e) => {
    e.preventDefault();
    if (usernameInput.trim()) {
      login(usernameInput);
    }
  };

  const handleAddTodo = async (e) => {
    e.preventDefault();
    if (!todoTitle.trim() || !user?.id) return;

    try {
      const newTodo = {
        title: todoTitle,
        completed: false,
        userId: user.id,
      };
      await createTodo(newTodo);
      setTodoTitle("");
      fetchTodos(); // Refresh list
    } catch (error) {
      console.error("Error adding todo:", error);
    }
  };

  if (!user) {
    return (
      <div className="flex flex-col items-center justify-center h-screen bg-gray-100">
        <div className="p-8 bg-white rounded shadow-md w-96">
          <h2 className="mb-4 text-2xl font-bold text-center">Login</h2>
          <form onSubmit={handleLogin} className="flex flex-col gap-4">
            <input
              type="text"
              placeholder="Enter Username"
              value={usernameInput}
              onChange={(e) => setUsernameInput(e.target.value)}
              className="p-2 border rounded"
            />
            <button
              type="submit"
              className="p-2 text-white bg-blue-500 rounded hover:bg-blue-600"
            >
              Enter
            </button>
          </form>
        </div>
      </div>
    );
  }

  return (
    <div className="min-h-screen p-8 bg-gray-50">
      <div className="max-w-2xl mx-auto">
        <div className="flex items-center justify-between mb-8">
          <h1 className="text-3xl font-bold">Todo List</h1>
          <span className="text-gray-600">User: {user.username}</span>
        </div>

        <div className="p-6 mb-8 bg-white rounded shadow">
          <h2 className="mb-4 text-xl font-semibold">Add New Todo</h2>
          <form onSubmit={handleAddTodo} className="flex gap-2">
            <input
              type="text"
              placeholder="Todo Title"
              value={todoTitle}
              onChange={(e) => setTodoTitle(e.target.value)}
              className="flex-1 p-2 border rounded"
            />
            <button
              type="submit"
              className="px-4 py-2 text-white bg-green-500 rounded hover:bg-green-600"
            >
              Add
            </button>
          </form>
        </div>

        <div className="bg-white rounded shadow">
          <h2 className="p-4 text-xl font-semibold border-b">My Todos</h2>
          <ul>
            {todos.length === 0 ? (
              <li className="p-6 text-center text-gray-500">
                No todos found. Add one above!
              </li>
            ) : (
              todos.map((todo) => (
                <li
                  key={todo.id}
                  className="flex items-center justify-between p-4 border-b last:border-b-0"
                >
                  <span
                    className={
                      todo.completed ? "line-through text-gray-400" : ""
                    }
                  >
                    {todo.title}
                  </span>
                  <span
                    className={`px-2 py-1 text-xs rounded ${todo.completed ? "bg-green-100 text-green-800" : "bg-yellow-100 text-yellow-800"}`}
                  >
                    {todo.completed ? "Completed" : "Pending"}
                  </span>
                </li>
              ))
            )}
          </ul>
        </div>
      </div>
    </div>
  );
};

export default TodoPage;
