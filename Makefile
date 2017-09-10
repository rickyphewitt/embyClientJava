
COMPOSE_FILE= ./docker/docker-compose.yml

build:
	mvn clean install

docker-build:
	docker build -t embyapiclient:latest . && \
	docker run embyapiclient:latest

build-ci:
	mvn clean verify
