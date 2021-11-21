FROM node:lts-alpine as client_app_build
WORKDIR /app
ENV PATH /app/node_modules/.bin:$PATH
COPY railway-client/package.json ./
COPY railway-client/package-lock.json ./
RUN npm install --silent
COPY railway-client ./
RUN npm run build

FROM nginx:stable-alpine
COPY --from=railway-client_build /app/build /usr/share/nginx/railway-client/html/.
COPY nginx/nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
