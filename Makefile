.DEFAULT_GOAL := help

help: ## Display help messages
	@grep -E '^[a-zA-Z_/-]+:.*?## .*$$' $(MAKEFILE_LIST) | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-20s\033[0m %s\n", $$1, $$2}'

docker-build: ## Docker Compose build
	docker compose build
run: ## Run Docker Compose
	docker compose up -d
down: ## Down Docker Compose
	docker compose down
