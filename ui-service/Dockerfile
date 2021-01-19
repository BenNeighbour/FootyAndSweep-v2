ENV DEBUG=*

FROM node:alpine
RUN mkdir /app
COPY . /app
WORKDIR /app
COPY package*.json ./
RUN npm install
CMD ["npm", "start"]