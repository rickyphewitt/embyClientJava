
COMPOSE_FILE= ./docker/docker-compose.yml

docker-compose-build:
	docker-compose --file $(COMPOSE_FILE) build && \
	docker-compose --file $(COMPOSE_FILE) up

docker-compose-build-ci:
	 docker-compose --file $(COMPOSE_FILE) build && \
	docker-compose --file $(COMPOSE_FILE) up  --exit-code-from build
