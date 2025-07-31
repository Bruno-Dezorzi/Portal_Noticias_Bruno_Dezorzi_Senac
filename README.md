# 📰 Portal de Notícias - Bruno Dezorzi (Senac Cascavel)

Este projeto é um sistema completo de **Portal de Notícias**, desenvolvido como parte dos estudos no curso de **Análise e Desenvolvimento de Sistemas** na **Faculdade Senac Cascavel**. O sistema é dividido em **Frontend (Angular)** e **Backend (Spring Boot)**, oferecendo funcionalidades completas para gerenciar categorias, autores, notícias, comentários e publicidades.

**Disciplinas:** 
- Padrões de Projeto, Frameworks e API
- Tecnologias para Internet  
**Professor:** Guilherme Villaca  
**Turma:** 2 - 1º Bimestre 2025

---

## ⚙️ Tecnologias Utilizadas

### 💻 Backend - Spring Boot
- Spring Boot 3.x
- Spring Data JPA + Hibernate
- PostgreSQL
- Maven
- Java 17+
- Lombok
- MapStruct (mapeamento DTO ↔ entidade)
- Swagger (OpenAPI) para documentação
- Bean Validation para validações

### 🌐 Frontend - Angular
- Angular 16+
- TypeScript
- Angular Material
- RxJS
- SCSS
- Formulários reativos

---

## 🧠 Objetivo do Projeto

Desenvolver uma aplicação completa de portal de notícias aplicando:
- **Arquitetura limpa** com separação em camadas
- **Padrões de projeto** (DTO, Service Layer, Repository Pattern)
- **API RESTful** seguindo boas práticas
- **Regras de negócio** no domínio da aplicação
- **Interface responsiva** e amigável

---

## 🏛️ Estrutura de Entidades

### Entidades Principais
- **Pessoa** (entidade base)
- **Autor** (herda de Pessoa)
- **Categoria** (categorização de notícias)
- **Notícia** (conteúdo principal do portal)
- **Comentário** (interação dos usuários)
- **Publicidade** (sistema de anúncios)
- **Posicao** (posicionamento de publicidades)

### Campos Detalhados

#### Notícia
```java
- id: Long
- titulo: String (mín. 10 caracteres)
- subtitulo: String
- conteudo: String (mín. 200 caracteres)
- dataPublicacao: LocalDateTime
- status: StatusNoticia (RASCUNHO/PUBLICADA)
- destaque: boolean
- categoria: Categoria (muitos-para-um)
- autor: Autor (muitos-para-um)
```

#### Publicidade
```java
- id: Long
- titulo: String
- imagemUrl: String
- linkDestino: String
- dataInicio: LocalDate
- dataFim: LocalDate
- ativo: boolean
- visualizacoes: Long
- clicks: Long
- tipoMidia: String
- prioridade: Integer
- posicao: Posicao (muitos-para-um)
- categorias: Set<Categoria> (muitos-para-muitos)
```

#### Comentário
```java
- id: Long
- conteudo: String (mín. 10, máx. 500 caracteres)
- dataComentario: LocalDateTime
- ativo: boolean (para moderação)
- noticia: Noticia (muitos-para-um)
- usuario: String
```

---

## 🔁 Relacionamentos

- `Autor` → herda atributos de `Pessoa`
- `Notícia`:
  - muitos-para-um com `Categoria`
  - muitos-para-um com `Autor`
  - um-para-muitos com `Comentário`
- `Publicidade`:
  - muitos-para-um com `Posicao`
  - muitos-para-muitos com `Categoria`
- `Comentário` → muitos-para-um com `Notícia`

---

## 📋 Regras de Negócio

### 1. Gestão de Categorias
- Nome da categoria é **obrigatório** e deve ser **único**
- **Não é permitido excluir** categorias com notícias vinculadas

### 2. Gestão de Notícias
- Campos obrigatórios: título, subtítulo, conteúdo, categoria
- Notícias **publicadas** aparecem automaticamente na listagem pública
- **Não é possível alterar** a categoria de uma notícia já publicada
- Datas de publicação não podem estar no passado (quando status = PUBLICADA)

### 3. Sistema de Comentários
- Usuários podem comentar após login simulado
- Administradores podem **aprovar/ocultar** comentários
- Comentários inativos não aparecem na visualização pública

### 4. Sistema de Publicidades
- Exibição apenas de anúncios **ativos e vigentes**
- Segmentação por categoria de notícias
- Controle de posicionamento (topo, lateral, entre notícias)
- Contador de visualizações e clicks

### 5. Autenticação e Autorização
- Login simulado para usuários e administradores
- Apenas administradores podem gerenciar conteúdo
- Usuários logados podem comentar

---

## 🛠️ Arquitetura do Backend

### Estrutura em Camadas
```
├── controller/     # Controladores REST
├── service/        # Regras de negócio
├── domain/model/   # Entidades JPA
├── repository/     # Acesso a dados
├── dto/           # Data Transfer Objects
└── config/        # Configurações
```

### Padrões de Projeto Implementados
- **DTO Pattern** para entrada e saída de dados
- **Service Layer** para regras de negócio
- **Repository Pattern** via Spring Data JPA
- **Strategy/Template Method** para filtros e validações

---

## 🌐 API REST - Endpoints Principais

### Endpoints Públicos
```
GET /noticias                    # Lista notícias públicas
GET /noticias/{id}              # Detalha notícia com comentários
GET /noticias/destaque          # Notícias em destaque
POST /comentarios               # Adiciona comentário (usuário logado)
GET /publicidades/posicao/{id}  # Publicidades por posição
```

### Endpoints Administrativos
```
POST   /admin/noticias                 # Cadastra notícia
PUT    /admin/noticias/{id}           # Atualiza notícia
DELETE /admin/noticias/{id}           # Remove notícia
POST   /admin/categorias              # Cadastra categoria
GET    /admin/comentarios/noticia/{id} # Lista comentários
PUT    /admin/comentarios/{id}/status  # Aprova/oculta comentário
POST   /admin/publicidades            # Cadastra publicidade
```

### Métodos de Repository Customizados
```java
// Query derivadas
List<Noticia> findByDestaqueTrue();
List<Noticia> findByDestaque(Boolean destaque);

// JPQL customizada
@Query("SELECT n FROM Noticia n WHERE n.destaque = true")
List<Noticia> buscarNoticiasEmDestaque();

// SQL nativo
@Query(value = "SELECT * FROM noticia WHERE destaque = true", nativeQuery = true)
List<Noticia> buscarNoticiasEmDestaqueSQL();
```

---

## 📦 Funcionalidades Implementadas

### Backend ✅
- [x] API REST completa para CRUD de todas as entidades
- [x] Mapeamento JPA com herança (Pessoa → Autor)
- [x] Relacionamentos complexos (1:N, N:N)
- [x] Validações com Bean Validation
- [x] Query derivadas e JPQL customizada
- [x] Sistema de comentários com moderação
- [x] Módulo de publicidades com segmentação
- [x] Controle de status e datas de vigência

### Frontend ✅
- [x] Interface de listagem de notícias
- [x] Sistema de destaque e carrossel
- [x] Formulários reativos para cadastro
- [x] Área administrativa completa
- [x] Sistema de login simulado
- [x] Gestão de comentários
- [x] Componente de publicidades
- [x] Filtros por categoria

### Funcionalidades Adicionais 🎯
- [x] Versionamento de API (/v1/)
- [x] Documentação Swagger/OpenAPI
- [x] Contador de visualizações para publicidades
- [x] Sistema de prioridades para anúncios
- [x] Suporte a diferentes tipos de mídia

---

## 🚀 Como Rodar o Projeto

### 🐳 Opção 1: Docker (Recomendado)

#### Pré-requisitos
- Docker
- Docker Compose

#### Executando com Docker
```bash
# Clone o repositório
git clone https://github.com/seu-usuario/portal-noticias.git
cd portal-noticias

# Execute todos os serviços
docker-compose up -d

# Para parar os serviços
docker-compose down
```

#### Estrutura Docker
```yaml
# docker-compose.yml
version: '3.8'
services:
  postgres:
    image: postgres:15
    environment:
      POSTGRES_DB: portal_noticias
      POSTGRES_USER: admin
      POSTGRES_PASSWORD: admin123
    ports:
      - "5432:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data

  backend:
    build: ./backend
    ports:
      - "8080:8080"
    depends_on:
      - postgres
    environment:
      - SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/portal_noticias
      - SPRING_DATASOURCE_USERNAME=admin
      - SPRING_DATASOURCE_PASSWORD=admin123

  frontend:
    build: ./frontend
    ports:
      - "4200:80"
    depends_on:
      - backend

volumes:
  postgres_data:
```

#### Dockerfiles

**Backend Dockerfile:**
```dockerfile
FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```

**Frontend Dockerfile:**
```dockerfile
FROM node:18-alpine AS build
WORKDIR /app
COPY package*.json ./
RUN npm ci
COPY . .
RUN npm run build

FROM nginx:alpine
COPY --from=build /app/dist/portal-noticias /usr/share/nginx/html
COPY nginx.conf /etc/nginx/nginx.conf
EXPOSE 80
```

### 🔧 Opção 2: Execução Manual

#### Pré-requisitos
- Java 17+
- Node.js 16+
- PostgreSQL
- Maven

#### 1. Backend (Spring Boot)
```bash
cd backend
# Configurar banco PostgreSQL em application.properties
./mvnw clean package
./mvnw spring-boot:run
```

#### 2. Frontend (Angular)
```bash
cd frontend
npm install
ng serve
```

#### 3. Banco de Dados
```sql
-- Criar banco PostgreSQL
CREATE DATABASE portal_noticias;
-- As tabelas são criadas automaticamente via JPA/Hibernate
```

### 🌐 Acessos
- **Frontend:** http://localhost:4200
- **Backend API:** http://localhost:8080
- **Swagger UI:** http://localhost:8080/swagger-ui.html
- **Banco PostgreSQL:** localhost:5432

---

## 📅 Cronograma de Entregas

- **04/06/2025** - Entrega parcial: CRUD básico funcional
- **09/06/2025** - Implementação de destaque e publicidades
- **25/06/2025** - **Entrega final**: Sistema completo com todas as funcionalidades

---

## 🎨 Funcionalidades do Frontend

### Para Usuários (Leitores)
- Visualização de notícias mais recentes
- Sistema de destaque/carrossel
- Detalhamento completo das notícias
- Login simulado para comentários
- Visualização de publicidades segmentadas

### Para Administradores
- Área administrativa protegida
- Gestão completa de categorias e notícias
- Moderação de comentários
- Gestão de publicidades
- Relatórios de visualizações

---

## 🔧 Validações Implementadas

### Bean Validation
```java
@NotBlank
@Size(min = 10, message = "Título deve ter no mínimo 10 caracteres")
private String titulo;

@NotBlank
@Size(min = 200, message = "Conteúdo deve ter no mínimo 200 caracteres") 
private String conteudo;

@Size(min = 10, max = 500, message = "Comentário deve ter entre 10 e 500 caracteres")
private String comentario;
```

---

## 📊 Estrutura de Dados

### Relacionamentos Principais
```
Pessoa (1) ←← Autor (N) → Noticia (N) → Categoria (1)
                ↓
            Comentario (N)
            
Publicidade (N) → Posicao (1)
      ↓
  Categoria (N:N)
```

---

## 🚀 Tecnologias Avançadas

### Opcionais Implementadas
- **MapStruct** para mapeamento automático DTO ↔ Entidade
- **Lombok** para redução de boilerplate
- **Swagger UI** para documentação interativa da API
- **Paginação** e **ordenação** nas listagens
- **Upload simulado** de imagens para notícias
- **RSS integration** para sugestões de notícias

---

## 📁 Estrutura do Repositório

```
portal-noticias/
├── backend/                    # API Spring Boot
│   ├── src/main/java/
│   ├── src/main/resources/
│   ├── Dockerfile             # Container do backend
│   └── pom.xml
├── frontend/                  # App Angular  
│   ├── src/app/
│   ├── src/assets/
│   ├── Dockerfile            # Container do frontend
│   ├── nginx.conf            # Configuração Nginx
│   └── package.json
├── docker-compose.yml        # Orquestração dos containers
├── docs/                     # Documentação adicional
└── README.md                # Este arquivo
```

### 🐳 Arquivos Docker

#### docker-compose.yml
```yaml
version: '3.8'
services:
  postgres:
    image: postgres:15
    container_name: portal_postgres
    environment:
      POSTGRES_DB: portal_noticias
      POSTGRES_USER: admin
      POSTGRES_PASSWORD: admin123
    ports:
      - "5432:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data
    networks:
      - portal-network

  backend:
    build: 
      context: ./backend
      dockerfile: Dockerfile
    container_name: portal_backend
    ports:
      - "8080:8080"
    depends_on:
      - postgres
    environment:
      - SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/portal_noticias
      - SPRING_DATASOURCE_USERNAME=admin
      - SPRING_DATASOURCE_PASSWORD=admin123
      - SPRING_JPA_HIBERNATE_DDL_AUTO=update
    networks:
      - portal-network
    restart: unless-stopped

  frontend:
    build:
      context: ./frontend
      dockerfile: Dockerfile
    container_name: portal_frontend
    ports:
      - "4200:80"
    depends_on:
      - backend
    networks:
      - portal-network
    restart: unless-stopped

volumes:
  postgres_data:

networks:
  portal-network:
    driver: bridge
```

#### Backend Dockerfile
```dockerfile
# Estágio de build
FROM maven:3.8.6-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Estágio de execução
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

#### Frontend Dockerfile
```dockerfile
# Estágio de build
FROM node:18-alpine AS build
WORKDIR /app
COPY package*.json ./
RUN npm ci --silent
COPY . .
RUN npm run build --prod

# Estágio de produção
FROM nginx:alpine
COPY --from=build /app/dist/portal-noticias /usr/share/nginx/html
COPY nginx.conf /etc/nginx/nginx.conf
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
```

#### nginx.conf
```nginx
events {
    worker_connections 1024;
}

http {
    include       /etc/nginx/mime.types;
    default_type  application/octet-stream;

    server {
        listen 80;
        server_name localhost;
        root /usr/share/nginx/html;
        index index.html;

        # Angular routes
        location / {
            try_files $uri $uri/ /index.html;
        }

        # Proxy para API do backend
        location /api/ {
            proxy_pass http://backend:8080/;
            proxy_set_header Host $host;
            proxy_set_header X-Real-IP $remote_addr;
        }
    }
}
```

## 🐳 Comandos Docker Úteis

### Gerenciamento dos Containers
```bash
# Iniciar todos os serviços
docker-compose up -d

# Ver logs de todos os serviços
docker-compose logs -f

# Ver logs de um serviço específico
docker-compose logs -f backend
docker-compose logs -f frontend
docker-compose logs -f postgres

# Parar todos os serviços
docker-compose down

# Parar e remover volumes (CUIDADO: remove dados do banco)
docker-compose down -v

# Reconstruir containers após mudanças
docker-compose up --build

# Acessar container do backend
docker exec -it portal_backend bash

# Acessar container do banco
docker exec -it portal_postgres psql -U admin -d portal_noticias
```

### Troubleshooting
```bash
# Verificar status dos containers
docker-compose ps

# Limpar containers não utilizados
docker system prune

# Reconstruir apenas um serviço
docker-compose up --build backend

# Verificar redes Docker
docker network ls
```

### Backup do Banco de Dados
```bash
# Fazer backup
docker exec portal_postgres pg_dump -U admin portal_noticias > backup.sql

# Restaurar backup
docker exec -i portal_postgres psql -U admin portal_noticias < backup.sql
```

**Autor:** Bruno Dezorzi  
**Instituição:** Faculdade Senac Cascavel  
**Curso:** Análise e Desenvolvimento de Sistemas  
**Professor Orientador:** Guilherme Villaca

---

## 📞 Suporte

Para dúvidas sobre o projeto ou implementação, consulte:
- Documentação da API: `/swagger-ui.html`
- Código fonte: Repositório GitHub
- Relatórios semanais de progresso com o professor