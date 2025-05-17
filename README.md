# Real-time Chat Application

A real-time chat application built with Spring Boot, WebSocket, and H2 Database.

## Features

- Real-time messaging using WebSocket
- Message persistence with H2 Database
- Message status tracking (sent, delivered, read)
- Media file support (images, videos)
- User presence notifications
- Clean and responsive UI

## Technologies Used

- Java 17
- Spring Boot 3.2.3
- Spring WebSocket
- Spring Data JPA
- H2 Database
- HTML/CSS/JavaScript
- SockJS
- STOMP.js

## Prerequisites

- Java 17 or higher
- Maven
- Git

## Getting Started

1. Clone the repository:
```bash
git clone https://github.com/yourusername/chat-application.git
```

2. Navigate to the project directory:
```bash
cd chat-application
```

3. Build the project:
```bash
mvn clean install
```

4. Run the application:
```bash
mvn spring-boot:run
```

5. Access the application:
   - Open your browser and go to `http://localhost:8080`
   - H2 Console: `http://localhost:8080/h2-console`
     - JDBC URL: `jdbc:h2:mem:chatdb`
     - Username: `sa`
     - Password: `password`

## Project Structure

```
src/main/java/com/example/chatapplication/
├── ChatApplication.java
├── config/
│   └── WebSocketConfig.java
├── controller/
│   └── ChatController.java
├── model/
│   ├── ChatMessage.java
│   └── Message.java
└── repository/
    └── MessageRepository.java
```

## Usage

1. Open the application in your browser
2. Enter your name when prompted
3. Start chatting!
   - Send text messages
   - Upload media files
   - See message status
   - Get notifications when users join/leave

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details. 
