# TODO Project - Running This Project Using Docker

This is a full-stack TODO application built using:

- Spring Boot (Backend)
- React (Frontend)
- MySQL (Database)
- Docker & Docker Compose (Containerization)

---

## Prerequisites

Make sure the following are installed on your system:

- Docker
- Docker Compose

Check installation:

```bash
docker --version
docker compose version
```

---

## Step 1: Clone the Repository

```bash
git clone https://github.com/Venkatesh0768/docker-practice.git
cd docker-practice
```

---

## Step 2: Create a `.env` File

Create a `.env` file in the root directory of the project.

```bash
touch .env
```

Add the following configuration inside the `.env` file:

```env
# -----------------------------
# Backend Configuration
# -----------------------------
BACKEND_IMAGE=venkatesh0768/backend-docker
BACKEND_PORT=8081

# -----------------------------
# Frontend Configuration
# -----------------------------
FRONTEND_IMAGE=venkatesh0768/frontend-docker
FRONTEND_PORT=3000

# -----------------------------
# MySQL Configuration
# -----------------------------
MYSQL_ROOT_PASSWORD=your_password
MYSQL_DATABASE=todo
MYSQL_PORT=3306

# -----------------------------
# Spring Boot Database Config
# -----------------------------
SPRING_DATASOURCE_URL=jdbc:mysql://todo-db:3306/todo
SPRING_DATASOURCE_USERNAME=root
SPRING_DATASOURCE_PASSWORD=your_password
```

Replace `your_password` with a secure MySQL password.

⚠️ Do NOT commit your `.env` file to GitHub.

---

## Step 3: Start the Application

Run the following command from the project root:

```bash
docker compose up -d
```

This will:

- Pull required Docker images
- Create backend container
- Create frontend container
- Create MySQL container
- Set up internal Docker network
- Start all services

---

## Step 4: Verify Running Containers

```bash
docker ps
```

You should see:

- Backend container
- Frontend container
- MySQL container

---

## Step 5: Access the Application

Open in your browser:

Frontend:
```
http://localhost:3000
```

Backend:
```
http://localhost:8081
```

MySQL:
```
localhost:3306
```

---

## Viewing Logs

To see logs of all services:

```bash
docker compose logs -f
```

To see logs of a specific service:

```bash
docker compose logs backend
docker compose logs frontend
docker compose logs todo-db
```

---

## Rebuild Containers (If Code Changes)

```bash
docker compose up --build -d
```

---

## Stop the Application

```bash
docker compose down
```

---

## Remove Containers and Volumes (Clean Reset)

```bash
docker compose down -v
```

---

## Project Structure

```
docker-practice/
│
├── backend-todo/        # Spring Boot application
├── frontend/            # React application
├── docker-compose.yml   # Docker multi-service configuration
└── .env                 # Environment variables (not committed)
```

---

## Notes

- Backend connects to MySQL using service name `todo-db`
- Environment variables are injected using Docker Compose
- All services communicate over a Docker bridge network
- Ensure ports 3000, 8081, and 3306 are not already in use

---

## Author

Venkatesh Rapolu
