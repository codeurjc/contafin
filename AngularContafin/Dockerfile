#for dev
FROM node:16.20.2-alpine

WORKDIR /app
COPY package*.json ./
RUN npm install -g npm@8.19.4

COPY . .
RUN npm run build
CMD ["npm", "start"]

EXPOSE 4200

#for prod
#FROM node:13-alpine AS build
#WORKDIR /app

#COPY . .
#RUN npm install
#RUN npm run build
# Serve Application using Nginx Server
#FROM nginx:alpine
#COPY --from=build /app/dist/project-name/ /usr/share/nginx/html
#EXPOSE 80