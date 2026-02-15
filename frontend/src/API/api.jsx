import axios from "axios";

// Function to get API URL from runtime config or fallback
const getApiUrl = () => {
  // Check if window.ENV exists (loaded from env-config.js)
  if (window.ENV && window.ENV.VITE_API_URL) {
    console.log('✅ Using runtime API URL:', window.ENV.VITE_API_URL);
    return window.ENV.VITE_API_URL;
  }
  
  // Fallback to build-time env variable
  if (import.meta.env.VITE_API_URL) {
    console.log('⚠️ Using build-time API URL:', import.meta.env.VITE_API_URL);
    return import.meta.env.VITE_API_URL;
  }
  
  // Final fallback
  console.warn('❌ No API URL configured, using default');
  return 'http://localhost:8081';
};

const BASE_URL = `${getApiUrl()}/api/v1`;

console.log('🚀 API Base URL:', BASE_URL);

const api = axios.create({
  baseURL: BASE_URL,
  timeout: 10000, // Increased timeout to 10 seconds
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