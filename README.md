# Recording Studio Management System

A Java-based desktop application for managing songs, artists, albums, users, and playlists for a recording studio.

## Overview

The Recording Studio Management System is a CRUD-based Java application connected to a MySQL database. It was developed to practice object-oriented programming, database integration, layered application architecture, input validation, and Git/GitHub version control.

The system provides separate dashboards for administrators and users, with login and registration functionality.

## Features

### Authentication

- User Login
- User Registration
- Role-based dashboards

### Admin Features

- Manage Users
- Manage Artists
- Manage Albums
- Manage Songs
- Add, View, Update, and Delete records
- Archive and Restore records
- Search records

### User Features

- Browse Songs
- Browse Albums
- Browse Artists
- Search Songs
- Manage Personal Playlists

### Playlist Management

- Create Playlists
- View Playlists
- Update Playlists
- Delete Playlists
- Archive Playlists
- Restore Playlists
- Search Playlists

## Technologies Used

- Java
- MySQL
- JDBC
- Maven
- IntelliJ IDEA
- Git
- GitHub
- XAMPP
- phpMyAdmin

## Architecture

The application follows a layered architecture:

```text
Model
   ↓
Repository
   ↓
Service
   ↓
Controller
   ↓
View
```

### Project Layers

- **Model** – Represents the data and entities used by the application.
- **Repository** – Handles database operations and SQL queries.
- **Service** – Handles business logic and input validation.
- **Controller** – Connects the services with the application views.
- **View** – Handles menus and user interaction.
- **Config** – Handles the database connection.

## Database

The application uses **MySQL** as its database.

Main entities include:

- Users
- Artists
- Albums
- Songs
- Playlists

## Requirements

Before running the application, make sure you have:

- Java JDK 21
- MySQL
- XAMPP (optional)
- IntelliJ IDEA
- Maven

## Database Setup

1. Start MySQL using XAMPP or another MySQL server.
2. Create the required project database.
3. Import the SQL database files included in the project.
4. Check the database connection settings.
5. Make sure the MySQL server is running.

## How to Run

1. Clone the repository:

```bash
git clone https://github.com/sj2jamatubis26-coder/b226-csp2-matubis.git
```

2. Open the project in IntelliJ IDEA.

3. Allow Maven to download the required dependencies.

4. Make sure MySQL is running.

5. Check the database connection configuration.

6. Run the application from `Application.java`.

7. The application will display the main menu:

```text
==================================
 Recording Studio Management
==================================
1. Login
2. Register
0. Exit
```

## Learning Objectives

This project demonstrates practical experience with:

- Object-Oriented Programming
- Java
- SQL and MySQL
- JDBC database connectivity
- CRUD operations
- Input validation
- Layered application architecture
- Git and GitHub
- Authentication and role-based access
- Database-driven application development

## Author

**Jerry Antonio Matubis Jr.**

Computer Science Graduate

GitHub:  
https://github.com/sj2jamatubis26-coder
