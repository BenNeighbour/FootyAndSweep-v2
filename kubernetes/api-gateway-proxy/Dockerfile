FROM nginx:latest

COPY nginx.conf /etc/nginx/nginx.conf
COPY ./config /etc/nginx/config

CMD nginx -g 'daemon off;'
