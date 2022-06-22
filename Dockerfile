FROM node:16-slim as railway_client_build
WORKDIR /app
RUN mkdir src
RUN mkdir public
ENV PATH /app/node_modules/.bin:$PATH
COPY railway-client/package.json ./
COPY railway-client/src ./src
COPY railway-client/public ./public
COPY railway-client/.env ./
COPY railway-client/jsconfig.json ./
RUN apt-get update
RUN apt-get install -y build-essential
RUN apt-get install -y make
RUN apt-get install -y python
RUN npm install -g node-gyp
RUN npm install
RUN npm run build

FROM nginx:stable-alpine
COPY --from=railway_client_build /app/build /usr/share/nginx/html
COPY nginx/nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]