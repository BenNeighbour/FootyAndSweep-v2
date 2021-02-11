FROM node:14.15.4

RUN mkdir /app
WORKDIR /app

COPY package.json .
RUN npm install

COPY . .

CMD ["npm", "start"]