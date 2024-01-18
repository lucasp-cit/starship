FROM node:18-alpine

COPY ./starship /src

WORKDIR /src

RUN npm install

CMD npx nx run search:serve --stage=dev --host=0.0.0.0
