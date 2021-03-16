docker rm $(docker ps -a -q)
docker ps -a -q
docker rmi serveur
docker rmi moteur
docker rmi client

mvn clean install -Dmaven.test.skip=true

docker-compose --version
docker ps -a -q
docker build serveur -t serveur
docker build moteur -t moteur
docker build client -t client
docker images

docker-compose up --scale client=3

docker rm $(docker ps -a -q)
docker ps -a -q
docker rmi serveur
docker rmi moteur
docker rmi client
