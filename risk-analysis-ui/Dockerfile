FROM node:18.16.0-alpine as builder
WORKDIR /app
COPY . .
RUN npm install --save-exact
RUN npm run build

FROM nginx:1.17.10-alpine
RUN rm -rf /usr/share/nginx/html/* && rm -rf /etc/nginx/nginx.conf
COPY --from=builder /app/nginx.conf /etc/nginx/nginx.conf
COPY --from=builder /app/dist/risk-analysis-ui /usr/share/nginx/html
