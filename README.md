# TODO PROJECT and Running This Project Using Docker

## Clone the Repository

```bash
git clone https://github.com/Venkatesh0768/docker-practice.git
cd docker-practice
```

## Create a `.env` File in the Root Directory

Create a `.env` file in the root folder and add:

```env
BACKEND_IMAGE=venkatesh0768/backend-docker
BACKEND_PORT=8081

FRONTEND_IMAGE=venkatesh0768/frontend-docker
FRONTEND_PORT=3000

MYSQL_ROOT_PASSWORD=your_password
MYSQL_DATABASE=todo
MYSQL_PORT=3306

SPRING_DATASOURCE_URL=jdbc:mysql://todo-db:3306/todo
SPRING_DATASOURCE_USERNAME=root
SPRING_DATASOURCE_PASSWORD=your_password
```

## Run the Project

```bash
docker compose up -d
```

## Stop the Project

```bash
docker compose down
```

## Access the Application

Frontend: http://localhost:3000  
Backend: http://localhost:8081
