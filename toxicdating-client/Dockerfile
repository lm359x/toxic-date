FROM node:16-alpine

WORKDIR /app

COPY . .
RUN npm ci
RUN npm install -g serve

ARG BACKEND_BASE_URI
ENV REACT_APP_BACKEND_BASE_URI $BACKEND_BASE_URI

RUN npm run build
EXPOSE 3000
CMD [ "npx", "serve", "build" ]