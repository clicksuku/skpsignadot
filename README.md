# Calculator Service

A simple calculator application with React frontend and Spring Boot backend.

## Project Structure

```
calculator-service/
├── frontend/          # React + TypeScript + Vite
└── backend/           # Spring Boot Java
```

## Quick Start

### Backend (Spring Boot)

```bash
cd backend
./mvnw spring-boot:run
```

The backend will start on `http://localhost:8080`

### Frontend (React)

```bash
cd frontend
npm install
npm run dev
```

The frontend will start on `http://localhost:3000`

## API Endpoints

- `POST /api/calculate` - Perform calculation
- `GET /api/health` - Health check

## Supported Operations

- ADD
- SUBTRACT
- MULTIPLY
- DIVIDE
