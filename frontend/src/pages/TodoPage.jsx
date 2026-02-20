import React, { useState, useEffect } from "react";
import { fetchTodos, createTodo } from "../API/api";

const TodoPage = () => {
  


  const [todoTitle, setTodoTitle] = useState("");
  const [todos, setTodos] = useState([]);
  const [loading, setLoading] = useState(false);

  const loadTodos = async () => {
    try {
      setLoading(true);
      const data = await fetchTodos();
      setTodos(data);
    } catch (error) {
      console.error("Error fetching todos:", error);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    loadTodos();
  }, []);



  const handleAddTodo = async (e) => {
    e.preventDefault();
    if (!todoTitle.trim()) return;

    try {
      await createTodo({ title: todoTitle });
      setTodoTitle("");
      loadTodos();
    } catch (error) {
      console.error("Error adding todo:", error);
    }
  };






  
  // TODO PAGE UI
  return (
    <div className="min-h-screen bg-gray-100">

      <div className="max-w-4xl p-6 mx-auto">
        {/* CREATE TODO CARD */}
        <div className="p-6 mb-8 bg-white shadow-lg rounded-2xl">
          <h2 className="mb-4 text-xl font-semibold text-gray-800">
            Auto Instance is working
          </h2>

          <form onSubmit={handleAddTodo} className="flex gap-3">
            <input
              type="text"
              placeholder="Enter your task..."
              value={todoTitle}
              onChange={(e) => setTodoTitle(e.target.value)}
              className="flex-1 p-3 border rounded-xl focus:outline-none focus:ring-2 focus:ring-indigo-400"
            />

            <button
              type="submit"
              className="px-6 py-3 font-semibold text-white transition bg-indigo-600 rounded-xl hover:bg-indigo-700"
            >
              Add
            </button>
          </form>
        </div>

        {/* TODO LIST */}
        <div className="p-6 bg-white shadow-lg rounded-2xl">
          <h2 className="mb-4 text-xl font-semibold text-gray-800">
            Todo List
          </h2>

          {loading ? (
            <p className="text-center text-gray-500">Loading todos...</p>
          ) : todos.length === 0 ? (
            <p className="text-center text-gray-500">
              No todos found. Add one above!
            </p>
          ) : (
            <ul className="space-y-3">
              {todos.map((todo) => (
                <li
                  key={todo.id}
                  className="flex items-center justify-between p-4 transition border rounded-xl hover:shadow-md"
                >
                  <span className="font-medium text-gray-700">
                    {todo.title}
                  </span>

                  <span className="px-3 py-1 text-sm font-semibold text-indigo-700 bg-indigo-100 rounded-full">
                    Task
                  </span>
                </li>
              ))}
            </ul>
          )}
        </div>
      </div>
    </div>
  );
};

export default TodoPage;
