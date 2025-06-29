# SpacedIn

SpacedIn is a spaced repetition web application powered by Spring Boot and React.
It provides flashcards, deck management and AI-assisted learning.

## Requirements
- Java 21
- Node 18+
- PostgreSQL

## Running with Docker Compose

```bash
docker-compose up --build
```

Backend will be available on `http://localhost:8080` and the frontend on `http://localhost:3000`.

## Development

### Backend

```bash
cd Server
./mvnw spring-boot:run
```

### Frontend

```bash
cd SpacedIn
pnpm install
pnpm dev
```

The frontend dev server runs at `http://localhost:5173`.
The frontend now includes a dashboard for managing decks and pages to view and add flashcards.
