#!/bin/bash

echo "Subindo a aplicação com Docker Compose..."
docker compose up -d --build

echo "Aguardando o servidor subir..."
sleep 10

echo "Abrindo navegador no back-end (http://localhost:8080/swagger-ui/index.html)..."
xdg-open http://localhost:8080/swagger-ui/index.html || start http://localhost:8080/swagger-ui/index.html || open http://localhost:8080/swagger-ui/index.html

echo "Abrindo navegador no front-end (http://localhost:4200)..."
xdg-open http://localhost:4200 || start http://localhost:4200 || open http://localhost:4200

# Espera o usuário decidir quando derrubar o compose
read -p "Pressione ENTER para derrubar o Docker Compose e fechar o terminal..."

echo "Derrubando a aplicação..."
docker compose down

echo "Aplicação derrubada. Até a próxima!"
