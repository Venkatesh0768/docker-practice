import React from "react";
import { UserProvider } from "./context/UserContext";
import TodoPage from "./pages/TodoPage";

const App = () => {
  return (
    <UserProvider>
      <TodoPage />
    </UserProvider>
  );
};

export default App;
