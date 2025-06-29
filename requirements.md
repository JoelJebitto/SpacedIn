# SpacedIn – Application Requirements

## 📘 Project Overview

**SpacedIn** is a full-stack spaced repetition application built using **Spring Boot** and **React**, with integrated AI features to enhance personalized learning.

---

## 🧱 Tech Stack

- **Frontend**: React, Tailwind CSS, Axios
- **Backend**: Spring Boot (Java), Spring Security, Spring Data JPA, JWT
- **Database**: PostgreSQL
- **AI Integration**: OpenAI GPT / Local LLM (Ollama), LangChain
- **Infrastructure**: Docker, GitHub Actions, Nginx

---

## 🧩 Functional Modules and API Endpoints

### 1. 🧑 User Module

- `POST /api/auth/register`: Register new user
- `POST /api/auth/login`: Authenticate user (JWT)
- `GET /api/users/{id}`: View profile
- `PUT /api/users/{id}`: Update profile
- `POST /api/users/change-password`: Change password
- `DELETE /api/users/{id}`: Delete account

### 2. 🗂️ Deck Module

- `POST /api/decks`: Create new deck
- `GET /api/decks/user/{userId}`: List decks by user
- `PUT /api/decks/{deckId}`: Update deck
- `DELETE /api/decks/{deckId}`: Delete deck
- `GET /api/decks/share/{deckId}`: Share deck by link

### 3. 🧠 Flashcard Module

- `POST /api/cards`: Create flashcard
- `PUT /api/cards/{cardId}`: Update flashcard
- `DELETE /api/cards/{cardId}`: Delete flashcard
- `GET /api/decks/{deckId}/cards`: Get flashcards in deck
- `POST /api/cards/generate`: AI-generated flashcards

### 4. ⏱️ Spaced Repetition Logic

- `GET /api/deck-progress/{userId}`: Get deck progress
- `GET /api/card-progress/{userId}/{deckId}`: Get card progress
- `GET /api/schedule/{userId}`: Fetch review schedule
- `POST /api/progress`: Update review outcome

### 5. 🤖 AI Assistant

- `POST /api/ai/explain`: AI explanation for concepts
- `POST /api/ai/help-card/{cardId}`: AI help on card content
- `POST /api/ai/translate-card`: Translate flashcard using AI
- `POST /api/ai/summarize-deck`: Summarize deck content
- `GET /api/ai/weak-topics/{userId}`: Identify weak areas

### 6. 📊 Learning Analytics

- Visualize deck & card progress
- Track review frequency and retention
- AI-based feedback on weak concepts

### 7. 🛡️ Admin Module

- `GET /api/admin/users`: List all users
- `PATCH /api/admin/ban-user/{userId}`: Ban user
- `DELETE /api/admin/deck/{deckId}`: Delete deck

---

### 8. 📈 User Learning Progress Tracking

- Track individual card reviews and intervals
- Store timestamps and outcome of each review
- Use SM-2 algorithm to adjust next review date
- `GET /api/user-progress/{userId}`: Fetch all deck/card-level progress
- `POST /api/user-progress`: Update learning outcome
- `GET /api/user-progress/summary/{userId}`: Get visual dashboard data (retention %, accuracy, active streaks)

## 🔒 Security

- JWT Authentication
- Role-based Access Control (USER, ADMIN)
- Input validation
- Rate limiting for AI endpoints

---

## 🧪 Testing & CI/CD

- Unit tests for services
- Integration tests for endpoints
- Frontend tests (React Testing Library)
- GitHub Actions CI pipeline (Lint, Test, Build)
- Docker Compose setup

---

## 🐳 Deployment Strategy

- Docker for backend, frontend, and database
- Reverse proxy with Nginx
- Secrets/configs via environment variables
- Optional: Local LLM setup with Ollama

---

## 📁 Backend Structure

```
src/
├── config/
├── controller/
├── dto/
├── entity/
├── repository/
├── service/
├── security/
├── ai/
└── utils/
```

## 📁 Frontend Structure

```
src/
├── components/
├── pages/
├── hooks/
├── context/
├── services/
├── routes/
└── utils/
```

---

