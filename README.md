# ECS
[![Docker CI](https://github.com/mhamedouadghiri/ProjetJEE/actions/workflows/docker.yml/badge.svg?branch=master)](https://github.com/mhamedouadghiri/ProjetJEE/actions/workflows/docker.yml)

The ECS project includes two parts:
* **Backend**: consists of an API based on the JAX-RS API specification and runs on Glassfish.
* **Frontend**: based on React and consumes the aforementioned API.

### DevOps
The are two Dockerfiles, one for the backend in ``./`` context, and a second one for the frontend in ``./frontend/`` context.

A ``docker-compose.yml`` file encompassing all the required settings (networking, data volumes and images) is provided.

Just run ``docker-compose up -d`` to fire it up!
