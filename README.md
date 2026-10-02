# BakchodBrain 🧠⚡
> **Desh Ka Sabse Unhinged & Savage Trivia Arena**
> Spring Boot 3.5 • Java 21 (Project Loom Virtual Threads) • Render & Multi-Cloud Native • Zero Mercy Roasts

---

## 🎭 Concept
**BakchodBrain** is a stand-alone multiple-choice trivia application engineered with dark Apple-grade glassmorphism aesthetics and an **Infinite Procedural Roast Matrix**. Every right answer brings condescending respect, and every wrong answer triggers brutal, situational, non-repeating insults based on:
1. **Reaction Speed**: Overthinking (>13s) vs Overconfident blunder (<1.5s).
2. **Shame Streaks**: Exponential escalation on consecutive wrong answers.
3. **Stream/Category Context**: Tech & Corporate Slavery, Desh-Duniya & GK, Bollywood & Memes, Dating & Delusion.

---

## 🌟 Key Features
- **4 Custom Arenas**:
  - 💻 *Broke Engineers & Corporate Majdoor* (Git trauma, Jira standups, CSS div nightmares)
  - 🌍 *Desh-Duniya & GK Bakchodi* (UPSC mocks, Indian Railways, global peace trivia)
  - 🎬 *Bollywood & Meme Culture* (Hera Pheri, Gangs of Wasseypur, Panchayat, Munna Bhai)
  - 💔 *Dating, Simping & Delusion* (WhatsApp seen messages, ghosting, Bumble bios)
- **Zero-Lag Synthetic Audio Engine**: Audio generated dynamically inside the browser via the Web Audio API (no external MP3/WAV network dependencies).
- **Anti-Cheating Security**: `QuestionDto` strips `correctOption` entirely from client payloads. Validation occurs strictly server-side.
- **Savage Scorecard & WhatsApp Share**: Unique badges (`CLOWN OF THE DECADE`, `TCS BENCH LEGEND`, `GOOGLE KHOLA THA NA BSDK?`) and direct one-click WhatsApp sharing.
- **Dictator Console (`/admin`)**:
  - Live metric cards (Total Attempts, Accuracy, Legends vs Roastees).
  - Real-time Top 10 Hall of Fame leaderboard.
  - Full participant audit log with SHA-256 IP hashes.
  - Interactive Question Bank Creator & One-Click Deletion with Admin Key protection (`bakchodadmin69`).

---

## 🛠️ Multi-Cloud Database Architecture
The app dynamically switches its persistence layer at runtime via [`DatabaseConfiguration.java`](src/main/java/com/akshat/bakchodbrain/config/DatabaseConfiguration.java):
1. **Render Deployment**: Auto-detects PostgreSQL via `DATABASE_URL` (`postgres://` connection string).
2. **Local MySQL**: Automatically binds to `jdbc:mysql://localhost:3306/bakchodbrain_db`.
3. **In-Memory H2 Fallback**: If neither MySQL nor PostgreSQL is reachable, it boots with zero-config in-memory H2 so the application never crashes.

---

## 🚀 Running Locally

### Prerequisites
- JDK 21+
- Optional: MySQL 8.x running on port `3306`

### Run Command
```bash
./mvnw spring-boot:run
```

Access the application:
- **Trivia Arena**: `http://localhost:8080/`
- **Dictator Admin Console**: `http://localhost:8080/admin`
- **Health Check**: `http://localhost:8080/health`

---

## 🚢 Render Deployment (One-Click)
BakchodBrain includes a production-ready `Dockerfile` and `render.yaml` blueprint.

1. Push this repository to GitHub.
2. In Render Dashboard, click **New > Blueprint** and select this repo.
3. Render will provision the container, attach PostgreSQL, set the port and start roasting!
