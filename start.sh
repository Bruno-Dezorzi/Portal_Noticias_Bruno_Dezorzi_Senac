#!/bin/bash

echo "Subindo a aplicação com Docker Compose..."
docker compose up -d --build

echo "Aguardando o servidor subir..."
sleep 10

echo "Abrindo navegador no front-end (http://localhost:4200)..."
xdg-open http://localhost:4200 || start http://localhost:4200 || open http://localhost:4200
