FROM nginx:latest

COPY nginx.conf /etc/nginx/nginx.conf
COPY ssl/server.crt /etc/nginx/server.crt
COPY ssl/server.key /etc/nginx/server.key

CMD nginx -g 'daemon off;'
