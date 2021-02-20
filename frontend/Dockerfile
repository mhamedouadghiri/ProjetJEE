# build env
FROM node:12.18.2-alpine3.9 as build

ARG IP=172.20.0.10:8080

WORKDIR /app

ENV PATH /app/node_modules/.bin:$PATH

COPY package.json ./
COPY package-lock.json ./

RUN npm ci

COPY . ./

RUN npm run build

RUN sed -i "s/localhost:8080/${IP}/" ./src/resources/properties.js

# prod env
FROM node:12.18.2-alpine3.9

RUN npm install -g serve

COPY --from=build /app/build ./build

EXPOSE 80

CMD ["serve", "-s", "build", "-p", "80"]

