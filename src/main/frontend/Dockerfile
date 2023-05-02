FROM node:18.10.0

# add '/src/app/node_modules/.bin' to $PATH
#ENV PATH /src/app/node_modules/.bin:$PATH
WORKDIR /nodeapp
COPY ["package.json", "./"]
RUN npm install
RUN npm install --save-dev @angular-devkit/build-angular
RUN npm install @angular/cli
RUN npm install @angular/core
COPY . .

ENV NVM_DIR /usr/local/nvm
ENV NODE_VERSION 18
#install nvm with node and npm
RUN curl https://raw.githubusercontent.com/creationix/nvm/v0.30.1/install.sh | bash
RUN /bin/bash -c "source $NVM_DIR/nvm.sh && nvm install $NODE_VERSION && nvm alias default $NODE_VERSION && nvm use default"
COPY . /nodeapp
EXPOSE 80
EXPOSE 443
EXPOSE 4200
CMD node_modules/.bin/ng serve --ssl true --open --host 0.0.0.0
