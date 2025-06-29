CREATE TABLE users (
  id SERIAL PRIMARY KEY,
  username VARCHAR(50) UNIQUE NOT NULL,
  password VARCHAR(200) NOT NULL,
  email VARCHAR(100) NOT NULL,
  role VARCHAR(20) DEFAULT 'USER'
);

CREATE TABLE deck (
  id SERIAL PRIMARY KEY,
  title VARCHAR(100) NOT NULL,
  owner_id INTEGER REFERENCES users(id)
);

CREATE TABLE flashcard (
  id SERIAL PRIMARY KEY,
  question TEXT NOT NULL,
  answer TEXT NOT NULL,
  deck_id INTEGER REFERENCES deck(id)
);

CREATE TABLE card_progress (
  id SERIAL PRIMARY KEY,
  user_id INTEGER REFERENCES users(id),
  card_id INTEGER REFERENCES flashcard(id),
  repetitions INTEGER DEFAULT 0,
  interval INTEGER DEFAULT 1,
  easiness DOUBLE PRECISION DEFAULT 2.5,
  next_review TIMESTAMP,
  last_review TIMESTAMP
);
