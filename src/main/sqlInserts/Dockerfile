FROM ubuntu:latest

ADD . /temp
RUN apt-get update
RUN apt-get -y install mysql-client
CMD ["/temp/executeAll_Docker.sh"]
