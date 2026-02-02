# Blogging Platform

A feature-rich desktop blogging application built with **Java**, **JavaFX**, **PostgreSQL**  and  **MongoDB** support. The project implements a complete blogging system with user authentication (Argon2 password hashing), post management, commenting, tagging, engagement metrics, and a responsive JavaFX UI.

## Overview

The Blogging Platform is a JavaFX desktop application that allows users to create, read, and interact with blog posts. It features a modern GUI with multiple screens for authentication, content creation, and post browsing. The app uses PostgreSQL by default (with automatic database creation on first run) and can optionally use MongoDB when configured via environment variables. Configuration is loaded from a `.env` file (dotenv), the app uses HikariCP for connection pooling, and Argon2 (via argon2-jvm) for secure password hashing. In-memory caching and query/index optimizations are used to improve performance.

## Key Features

### User Management
- **User Registration**: Create new user accounts with validation
- **User Authentication**: Secure login with password hashing
- **User Profiles**: Store user information including username, full name, email, and gender
- **User Tracking**: Track user creation timestamps

### Post Management
- **Create Posts**: Write and publish blog posts with rich text content
- **Edit Posts**: Modify existing posts (draft and published)
- **Delete Posts**: Remove posts from the platform
- **Draft Support**: Save posts as drafts before publishing
- **Post History**: Track post creation dates
- **Post Preview**: View post summaries with statistics

### Content Features
- **Comments**: Users can comment on posts, fostering engagement
- **Tags**: Categorize and organize posts with multiple tags

### UI/UX
- **Screen Navigation**: Intuitive multi-screen navigation with screen history
- **Splash Screen**: Professional startup experience
- **Error Handling**: User-friendly error alerts and notifications

## Project Structure

```
blogging_platform/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── blog/
│   │   │   │   ├── App.java                          # Main application entry point
│   │   │   │   ├── GlobalUtilities.java              # Utility functions
│   │   │   │   ├── controllers/                      # JavaFX controllers for UI
│   │   │   │   │   ├── HomeController.java
│   │   │   │   │   ├── LoginController.java
│   │   │   │   │   ├── RegisterController.java
│   │   │   │   │   ├── PostController.java
│   │   │   │   │   ├── PostViewController.java
│   │   │   │   │   ├── PostPreviewController.java
│   │   │   │   │   └── CommentPaneController.java
│   │   │   │   ├── models/                           # Data models
│   │   │   │   │   ├── User.java
│   │   │   │   │   ├── Post.java
│   │   │   │   │   └── Comment.java
│   │   │   │   ├── services/                         # Business logic
│   │   │   │   │   ├── AuthenticationService.java    # Authentication logic
│   │   │   │   │   ├── PostService.java              # Post management
│   │   │   │   │   ├── CommentService.java           # Comment handling
│   │   │   │   │   └── TagService.java               # Tag management
│   │   │   │   ├── DataAccessors/                    # JDBC and MongoDB data access implementations
│   │   │   │   ├── DataSourceFactory/                # Hikari (Postgres) and Mongo data sources
│   │   │   │   ├── Utilities/                        # PasswordHasher, DummyDataInsertor, Sorter, etc.
│   │   │   │   ├── interfaces/                       # Interfaces
│   │   │   │   │   └── Widget.java                   # Screen interface
│   │   │   │   └── exceptions/
│   │   │   │       └── DatabaseInitializationFailureException.java
│   │   │   └── module-info.java                      # Java module configuration
│   │   └── resources/
│   │       └── blog/
│   │           ├── *.fxml                            # JavaFX UI layouts
│   │           └── assets/                           # Images and resources
│   ├── test/
│   │   └── java/blog/services/                       # Unit tests
│   │       ├── AuthServiceTest.java
│   │       ├── DatabaseServiceTest.java
│   │       └── PostServiceTest.java
├── documents/
│   ├── PerformanceReport.md                          # Performance optimization report
│   ├── ERD.png                                       # Entity Relationship Diagram
│   ├── Optimized/                                    # Optimized query output message images
│   ├── UnOptimized/                                  # Original unoptimized query message images
│   └── Tests/                                        # Unit Test images
├── pom.xml                                           # Maven configuration
├── Blogging Platform.sql                             # Database schema and seed data
└── README.md                                         # This file
```

## Database Schema

The application uses PostgreSQL with the following Entity Relationship Diagram (ERD):

![Entity Relationship Diagram](documents/ERD.png)

### Tables Overview

The application uses PostgreSQL with the following tables:

### Users
- Stores user account information
- Fields: ID, Username, Password, FullName, Email, Gender, CreatedAt

### Posts
- Contains blog post content
- Fields: ID, UserID, Title, Body, Draft, CreatedAt
- Supports draft status for unpublished posts

### Comments
- Enables user interactions on posts
- Fields: ID, UserID, PostID, Comment, CreatedAt
- Cascading delete when post is removed

### Tags
- Categorizes posts with flexible tagging
- Fields: ID, TagName

## Technologies Used

### Core
- **Java 21**: Programming language
- **JavaFX 24+**: Desktop GUI framework (project currently uses JavaFX 24.x for controls)
- **PostgreSQL**: Default relational database (Postgres JDBC driver 42.7.x used)
- **MongoDB**: Optional document database backend (via `mongodb-driver-sync`)
- **Maven**: Build automation and dependency management

### Libraries & Tooling
- **HikariCP**: Connection pooling for JDBC
- **argon2-jvm** (Argon2): Secure password hashing
- **dotenv-java**: Environment configuration from `.env`

### Testing
- **JUnit 5 (5.10.x)**: Unit testing framework (used in project)

## Dependencies

Key dependencies (see `pom.xml` for full list):

```xml
<!-- JavaFX -->
<dependency>
  <groupId>org.openjfx</groupId>
  <artifactId>javafx-controls</artifactId>
  <version>24.0.2</version>
</dependency>

<!-- PostgreSQL JDBC -->
<dependency>
  <groupId>org.postgresql</groupId>
  <artifactId>postgresql</artifactId>
  <version>42.7.7</version>
</dependency>

<!-- MongoDB (optional) -->
<dependency>
  <groupId>org.mongodb</groupId>
  <artifactId>mongodb-driver-sync</artifactId>
  <version>5.6.2</version>
</dependency>

<!-- Connection Pooling & Configuration -->
<dependency>
  <groupId>com.zaxxer</groupId>
  <artifactId>HikariCP</artifactId>
  <version>7.0.2</version>
</dependency>
<dependency>
  <groupId>io.github.cdimascio</groupId>
  <artifactId>dotenv-java</artifactId>
  <version>3.0.0</version>
</dependency>

<!-- Password Hashing (Argon2) -->
<dependency>
  <groupId>de.mkammerer</groupId>
  <artifactId>argon2-jvm</artifactId>
  <version>2.11</version>
</dependency>

<!-- JUnit for tests -->
<dependency>
  <groupId>org.junit.jupiter</groupId>
  <artifactId>junit-jupiter</artifactId>
  <version>5.10.0</version>
  <scope>test</scope>
</dependency>
```

## Setup Instructions

### Prerequisites
- **Java 21 JDK** installed and configured
- **Apache Maven** installed
- **PostgreSQL** database server running
- **PostgreSQL JDBC Driver** (included via Maven)

### Installation Steps

1. **Clone/Download the Project**
   ```bash
   cd blogging_platform
   ```

2. **Configure Database Connection (via .env)**
   - Create a `.env` file in the project root with your credentials and optional configuration. Example:
     ```env
     POSTGRESQL_USERNAME=postgres
     POSTGRESQL_PASSWORD=your_password
     # Optional: enable automatic sample data insertion on startup
     DUMMY_DATA=YES
     # Optional: provide a MongoDB URL to use the Mongo backend
     MONGODB_URL=mongodb://user:pass@localhost:27017/blog
     ```
   - By default the app connects to `jdbc:postgresql://localhost:5432/blog` (the `JDBCDatabaseInitializer` will create the database if it does not exist). Hikari pool settings are configured in `HikariDataSourceFactory`.

3. **Build the Project**
   ```bash
   mvn clean package
   ```

4. **Run the Application**
   ```bash
   mvn clean javafx:run
   ```
   
   Or run directly (Preferred):
   ```bash
   java -module-path $JAVAFX_PATH --add-modules javafx.controls,javafx.fxml -cp target/blogging_plaform-1.0.0.jar blog.App
   ```

### Database Setup
The application automatically creates the PostgreSQL database on first run if it does not exist (see `JDBCDatabaseInitializer`). If you provide a `MONGODB_URL` environment variable the project will use MongoDB and initialize collections via `MongoDBDatabaseInitializer`.

The `Blogging Platform.sql` file contains the SQL schema and sample data (7 sample users, sample posts and comments). To seed the app on startup set `DUMMY_DATA=YES` in your `.env` file — the `DummyDataInsertor` will populate demo records.

## Application Screens

### 1. Splash Screen
- Displays on application startup
- Initializes database connection
- Transition to login screen

### 2. Login Screen
- User authentication
- Username and password validation
- Registration link for new users

### 3. Registration Screen
- New user account creation
- Form validation (username uniqueness, password strength)
- Email and profile information input

### 4. Home Screen
- Browse all published blog posts
- View post previews with engagement metrics
- Search and filter functionality
- Access to post details and comments

### 5. Post View Screen
- Display full post content
- Show post author and creation date
- View and add comments
- Display like/dislike engagement metrics

### 6. Create/Edit Post Screen
- Write new blog posts
- Edit existing posts
- Save as draft or publish
- Add tags to posts

### 7. Comment Pane
- Display comments for a post
- Add new comments
- Show comment author and timestamp

## User Workflows

### Creating a Post
1. Navigate to home screen
2. Click "Create New Post"
3. Enter post title and content
4. Add tags (optional)
5. Save as draft or publish immediately
6. View published post on home feed

### Engaging with Content
1. Browse posts on home screen
2. Click on post to view details
3. Read comments from other users
4. Add your own comment
5. Like or dislike the post

### User Authentication
1. Launch application → Splash screen appears
2. Auto-transition to Login screen
3. Enter credentials or navigate to Register
4. Upon successful login, view home feed

## Running Tests

The project includes unit tests for core services:

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=AuthServiceTest
mvn test -Dtest=PostServiceTest
mvn test -Dtest=DatabaseServiceTest
```

### Test Coverage
- **AuthServiceTest**: User login and registration functionality
- **PostServiceTest**: Post creation, retrieval, and management
- **DatabaseServiceTest**: Database connection and query operations

## Performance Optimization

The project includes performance optimization documentation. [PerformanceReport.md](documents/PerformanceReport.md) has Detailed analysis of optimizations

### Key Optimizations
- **In-Memory Caching**: Cache posts, comments, and tags
- **Database Indexing**: Strategic indexes on frequently queried columns
- **Query Optimization**: Efficient SQL queries with JOINs and aggregations
- **Connection Pooling**: Reuse database connections

## Security Features

- **Password Hashing (Argon2)**: Passwords are hashed using Argon2 (via `argon2-jvm`) in `PasswordHasher` for secure storage and verification
- **SQL Prepared Statements**: Protection against SQL injection for JDBC accessors
- **Unique Constraints**: Email and username uniqueness enforced at the database level
- **Connection Pooling**: HikariCP reduces connection overhead and helps with resilience
- **Session Management**: Current user tracked during application session

## Known Issues & Improvements

### Current Limitations
- No password recovery mechanism
- Limited input validation on UI forms
- Basic error handling and logging

## Code Standards

### Naming Conventions
- Classes: PascalCase (e.g., `DatabaseService`)
- Methods: camelCase (e.g., `getCurrentUser()`)
- Constants: UPPER_SNAKE_CASE (e.g., `DB_URL`)
- Variables: camelCase (e.g., `currentUser`)

### Architecture Patterns
- **MVC**: Separation of business logic (models, services) from UI (controllers, views)
- **Service Pattern**: Encapsulation of business logic
- **DAO Pattern**: Data access abstraction through DatabaseService

## Sample Data

The database is pre-populated with:
- **7 Sample Users**: Diverse profiles for testing
- **9 Sample Posts**: Various blog content examples
- **Multiple Comments**: Engagement examples
- **Tags**: Post categorization

Users can log in with any of the sample accounts to explore functionality.