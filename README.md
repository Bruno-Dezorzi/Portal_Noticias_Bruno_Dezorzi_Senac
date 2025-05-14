# 📰 Portal de Notícias - Bruno Dezorzi (Senac)

Este projeto é um sistema completo de **Portal de Notícias**, desenvolvido como parte dos estudos no curso de Análise e Desenvolvimento de Sistemas no Senac. O sistema é dividido em **Frontend (Angular)** e **Backend (Spring Boot)**, oferecendo funcionalidades para gerenciar categorias, autores e notícias.

---

## ⚙️ Tecnologias Utilizadas

### 💻 Backend - Spring Boot
- Spring Boot 3+
- Spring Data JPA
- PostgreSQL
- Maven
- Java 17+
- Hibernate
- Swagger (OpenAPI)
- Lombok (opcional)

### 🌐 Frontend - Angular
- Angular 16+
- TypeScript
- Angular Material
- RxJS
- SCSS

---

## 🧠 Objetivo do Projeto

Criar uma aplicação de notícias com:
- Cadastro de autores, categorias e notícias
- Associação entre entidades (ex: Autor herda de Pessoa)
- Integração entre frontend e backend via API REST
- Interface amigável e responsiva com Angular Material

---

## 🏛️ Estrutura de Entidades

- **Pessoa** (entidade base)
- **Autor** (herda de Pessoa)
- **Categoria**
- **Notícia** (relaciona-se com Autor e Categoria)

---

## 🔁 Relacionamentos

- `Autor` → herda atributos de `Pessoa`
- `Notícia`:
  - muitos-para-um com `Categoria`
  - muitos-para-um com `Autor` (que é uma `Pessoa`)

---

## 📦 Funcionalidades (em andamento)

### Backend:
- [x] API REST para CRUD de categorias
- [x] API REST para CRUD de autores
- [x] API REST para CRUD de notícias
- [x] Mapeamento JPA com herança
- [ ] Validações com Bean Validation
- [ ] Autenticação e autorização (JWT ou básica)

### Frontend:
- [x] Interface de listagem de notícias
- [x] Formulários reativos para cadastro
- [ ] Filtro por categoria
- [ ] Sistema de login

---

## 🚀 Como Rodar o Projeto

### 1. Backend (Spring Boot)

```bash
cd backend
./mvnw spring-boot:run
