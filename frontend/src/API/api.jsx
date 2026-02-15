import axios from "axios";

const BASE_URL = `${import.meta.env.VITE_API_URL}/api/v1`;


const api = axios.create({
  baseURL: BASE_URL,
  timeout: 1000,
});

export const fetchTodosByUserId = async (userId) => {
  const res = await api.get(`/todos/${userId}`);
  return res.data;
};

export const createUser = async (username) => {
  const res = await api.post("/users", { username });
  return res.data;
};

export const createTodo = async (todo) => {
  const res = await api.post("/todos", todo);
  return res.data;
};
