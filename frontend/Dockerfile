#build the os 

FROM node:22-alpine

#Work directory of the project

WORKDIR /app

#copy the package json file 

COPY package.json package-lock.json ./

#Installing the dependience 

RUN npm install

#Copying the all file to the workdir

COPY . .

#Exposing the port

EXPOSE 5173

#Runing command of the project 

CMD ["npm" , "run" , "dev" , "--", "--host"]
