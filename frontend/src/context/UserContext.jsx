import React, { createContext, useState, useContext } from "react";
import { createUser } from "../API/api";

const UserContext = createContext();

export const useUser = () => useContext(UserContext);

export const UserProvider = ({ children }) => {
  const [user, setUser] = useState(null);

  const login = async (username) => {
    try {
      const data = await createUser(username);
      setUser(data);
    } catch (error) {
      console.error("Error creating user:", error);
      // Handle error appropriately (e.g., show a message)
    }
  };

  return (
    <UserContext.Provider value={{ user, login }}>
      {children}
    </UserContext.Provider>
  );
};
