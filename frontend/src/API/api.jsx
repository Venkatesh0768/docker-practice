import axios from "axios";

const BASE_URL = `${import.meta.env.VITE_API_URL}/api/v1`;

const api = axios.create({
  baseURL: BASE_URL,
  timeout: 5000,
});

// GET ALL TODOS
export const fetchTodos = async () => {
  const res = await api.get("/todos");
  return res.data;
};

// CREATE TODO
export const createTodo = async (todo) => {
  const res = await api.post("/todos", todo);
  return res.data;
};
