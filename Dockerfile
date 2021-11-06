FROM node:lts-alpine as client_app_build
WORKDIR /app
ENV PATH /app/node_modules/.bin:$PATH
COPY client_app/package.json ./
COPY client_app/package-lock.json ./
RUN npm install --silent
COPY client_app ./
RUN npm run build

FROM nginx:stable-alpine
COPY --from=client_app_build /app/build /usr/share/nginx/client_app/html/.
COPY nginx/nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
