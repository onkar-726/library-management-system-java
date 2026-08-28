# 📚 Library Management System - Java

A console-based **Library Management System** developed using **Core Java** and **Object-Oriented Programming (OOP)** principles.

The application allows users to manage books and library members, issue and return books, maintain borrowing history, enforce borrowing limits, and calculate fines for late returns.

---

## 🚀 Features

### 📖 Book Management

- Add new books
- Remove books
- Find books by ID
- Display available books
- Prevent duplicate book IDs
- Prevent removal of currently issued books

### 👤 User Management

- Add students
- Add administrators
- Find users by ID
- Display all registered users
- Prevent duplicate user IDs

### 🔄 Book Borrowing

- Issue books to users
- Return books
- Check book availability before issuing
- Track books borrowed by each student
- Limit students to a maximum of **3 borrowed books**

### 📜 Borrowing History

- Store every borrowing transaction
- Track issue date
- Track due date
- Track return date
- Display `BORROWED` or `RETURNED` status
- Preserve returned transactions as historical records

### 💰 Fine Management

- Calculate fines for late returns
- Fine rate: **₹10 per late day**

### ⚠️ Exception Handling

Uses custom exceptions for invalid operations and business-rule violations.

---

## 🛠️ Technologies Used

- Java
- Core Java
- Object-Oriented Programming
- Collections Framework
- Exception Handling
- Java Time API
- IntelliJ IDEA

---

## 🧠 OOP Concepts Demonstrated

### 1. Encapsulation

Private fields with controlled access through getters, setters, and methods.

Example:

```java
private String title;
private boolean available;
```

---

### 2. Inheritance

`Student` and `Admin` extend the `User` class.

```text
             User
            /    \
       Student   Admin
```

---

### 3. Abstraction

`User` is an abstract class containing common user properties and behavior.

```java
public abstract class User
```

---

### 4. Polymorphism

The `showRole()` method is overridden by different user types.

```text
Student → showRole()
Admin   → showRole()
```

---

### 5. Method Overriding

Child classes provide their own implementation of methods inherited from the parent class.

---

### 6. Association

`BorrowRecord` maintains references to both a `User` and a `Book`.

```text
        BorrowRecord
          /       \
       User       Book
```

---

## 🏗️ Project Architecture

The project follows a simple layered structure:

```text
                    Main
                     │
                     ▼
                LibraryApp
                     │
                     ▼
              LibraryService
                     │
              ┌──────┴──────┐
              ▼             ▼
           Models       Exceptions
```

### Main

Responsible for starting the application.

### LibraryApp

Responsible for:

- Console menu
- User input
- Calling service methods
- Displaying results
- Handling user-facing errors

### LibraryService

Contains the main business logic for:

- Book management
- User management
- Issuing books
- Returning books
- Borrowing history
- Fine calculation
- Validation
- Borrowing limits

### Model

Contains the application's data classes:

- `Book`
- `User`
- `Student`
- `Admin`
- `BorrowRecord`

### Exception

Contains custom exceptions used to handle invalid operations and business-rule violations.

---

## 📂 Project Structure

```text
library-management-system-java
│
├── src
│   └── com.onkar.librarymanagement
│       │
│       ├── Main.java
│       ├── LibraryApp.java
│       │
│       ├── model
│       │   ├── User.java
│       │   ├── Student.java
│       │   ├── Admin.java
│       │   ├── Book.java
│       │   └── BorrowRecord.java
│       │
│       ├── service
│       │   └── LibraryService.java
│       │
│       └── exception
│           ├── BookNotFoundException.java
│           ├── BookNotAvailableException.java
│           ├── BookNotIssuedException.java
│           ├── BookAlreadyIssuedException.java
│           ├── DuplicateBookException.java
│           ├── DuplicateUserException.java
│           ├── StudentBorrowLimitException.java
│           └── UserNotFoundException.java
│
├── README.md
└── .gitignore
```

---

## 📋 Application Menu

```text
======================================
      LIBRARY MANAGEMENT SYSTEM
======================================
1. Display Available Books
2. Find Book
3. Add Book
4. Remove Book
5. Display Users
6. Find User
7. Issue Book
8. Return Book
9. Add Student
10. Borrowing History
11. View Fine
0. Exit
======================================
Enter your choice:
```

---

## 📌 Business Rules

| Rule | Description |
|---|---|
| Book ID | Must be unique |
| User ID | Must be unique |
| Issue Book | Only available books can be issued |
| Return Book | Only currently issued books can be returned |
| Remove Book | Issued books cannot be removed |
| Student Limit | Maximum 3 borrowed books |
| Borrowing History | Returned records are preserved |
| Fine | ₹10 per late day |

---

## 💾 Data Storage

Currently, the application uses Java's in-memory collections:

```java
List<Book>
List<User>
List<BorrowRecord>
```

Data is stored only during program execution and is **not persisted** after the application is closed.

---

## ▶️ How to Run

### Prerequisites

- Java JDK installed
- IntelliJ IDEA or another Java IDE

### Steps

1. Clone or download the repository.
2. Open the project in IntelliJ IDEA.
3. Configure the project with a Java JDK.
4. Open `Main.java`.
5. Run the `main()` method.
6. Use the console menu to interact with the application.

---

## 🧪 Example Workflow

### Issue a Book

```text
Enter your choice: 7

Enter Book ID: 1
Enter User ID: 1

Book issued successfully.
```

The issued book will no longer appear in the **Available Books** list.

---

### Return a Book

```text
Enter your choice: 8

Enter Book ID to return: 1

Book returned successfully.
```

The returned book becomes available again.

---

### Borrowing History

Borrowing records remain in the history even after a book is returned.

Example:

```text
BorrowRecord{
    recordId=1,
    user=Onkar,
    book=Clean Code,
    issueDate=2026-08-28,
    dueDate=2026-09-11,
    returnDate=2026-08-28,
    status=RETURNED,
    fine=₹0
}
```

This preserves previous borrowing activity for historical tracking.

---

## ⚠️ Custom Exceptions

The project uses custom exceptions to handle invalid operations:

```text
BookNotFoundException
BookNotAvailableException
BookNotIssuedException
BookAlreadyIssuedException
DuplicateBookException
DuplicateUserException
StudentBorrowLimitException
UserNotFoundException
```

This makes business-rule violations easier to understand and maintain.

---

## 🔍 Example Business Validation

### Duplicate Book

```text
Enter Book ID: 1

Error: Book with ID 1 already exists
```

### Book Already Issued

```text
Enter Book ID: 1

Error: Book with ID 1 is already issued
```

### Student Borrowing Limit

```text
Error: Student Onkar has already borrowed 3 books
```

### Book Not Found

```text
Enter Book ID: 99

Error: Book with ID 99 not found
```

---

## 📈 Current Project Status

```text
Core Java                    ✅
OOP                          ✅
Encapsulation                ✅
Inheritance                  ✅
Abstraction                  ✅
Polymorphism                 ✅
Collections                  ✅
Exception Handling           ✅
Book Management              ✅
User Management              ✅
Book Issue/Return            ✅
Borrowing History            ✅
Fine Calculation             ✅
Console Application          ✅
Database                     ⏳
Spring Boot                  ⏳
REST API                     ⏳
```

---

## 🔮 Future Improvements

The current version uses in-memory storage. Future versions can include:

### Database

- MySQL database integration
- SQL database design
- JDBC
- Persistent book and user data
- Database relationships

### Backend

- Spring Boot
- Spring Data JPA
- REST APIs
- DTOs
- Request validation
- Global exception handling

### Security

- User authentication
- Role-based authorization
- Admin login
- Student login

### Frontend

- Web-based user interface
- Book search and filtering
- Student dashboard
- Admin dashboard
- Borrowing dashboard

### Deployment

- Docker
- Cloud deployment
- Production database

---

## 🗺️ Project Roadmap

```text
Phase 1
Core Java + OOP
        │
        ▼
Phase 2
Library Management System
        │
        ▼
Phase 3
SQL + MySQL
        │
        ▼
Phase 4
JDBC
        │
        ▼
Phase 5
Spring Boot
        │
        ▼
Phase 6
Spring Data JPA
        │
        ▼
Phase 7
REST API
        │
        ▼
Phase 8
Authentication + Frontend
```

---

## 🎯 Learning Outcomes

Through this project, the following concepts are practiced:

- Core Java programming
- Object-Oriented Programming
- Class design
- Encapsulation
- Inheritance
- Abstraction
- Polymorphism
- Method overriding
- Collections Framework
- Exception handling
- Custom exceptions
- Java date and time API
- Business logic implementation
- Layered application structure
- Basic software design principles

---

## 👨‍💻 Author

**Onkar Londhe**

---

## ⭐ Project Goal

This project is being developed as a practical Java application to strengthen **Core Java, OOP, problem-solving, and backend development fundamentals**, with plans to evolve it into a database-driven **Spring Boot application**.