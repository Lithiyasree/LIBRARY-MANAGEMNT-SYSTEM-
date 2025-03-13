# LIBRARY-MANAGEMNT-SYSTEM-
JAVA, SWING, JDBC, MYSQL


The **Library Management System (LMS) GUI** is a **Java-based GUI DESKTOP APPLICATION** that efficiently manages library operations using **JDBC and MySQL**, providing a user-friendly **Graphical User Interface (GUI)**. 
This system is designed for both administrators (librarians) and users (students/members), offering role-based access to different functionalities.
The system allows librarians to **add, update, delete, and search books**, track **borrowed and returned books**, manage **due dates and fines**, and maintain **user records**. 
The **JDBC API** enables seamless interaction between the **Java application** and the **MySQL database**, ensuring efficient data retrieval, storage, and updates. 

## 🛠️ **Tech Stack**
- **Frontend (GUI):** Java **Swing** for an interactive and user-friendly interface.  
- **Backend (Database):** **MySQL** for storing book records, user details, and transaction history.  
- **Database Connectivity:** **JDBC (Java Database Connectivity)** for handling database operations.  
- **Development Tools:** **NetBeans** for coding and debugging.  
- **Version Control:** **Git & GitHub** for project tracking and collaboration.  
- **Security:** **Password hashing, access control**, and **exception handling** to ensure data integrity.  

## 🔑 **Features**

### 🔹 **Admin (Librarian)**
- **Login Authentication** – Secure login for librarians.
- **Manage Books** – Add, update, delete, and search books in the catalog.
- **Manage Users** – Add, update, and delete student/member accounts.
- **Issue Books** – Assign books to users and set due dates.
- **Return Books** – Process book returns and update availability.
- **Fine Management** – Track and calculate fines for overdue books.
- **View Transaction History** – Track issued and returned books.
- **Maintain Staff Details** – Manage staff information and roles.

### 🔹 **Users (Students/Members)**
- **User Registration/Login** – Secure authentication to access the system.
- **Search Books** – Look up books by title, author, or category.
- **View Book Availability** – Check if a book is available or issued.
- **View Issued Books** – Track borrowed books.
- **Check Fines** – View applicable overdue fines.
- 
### 🔹 **Email Notifications**
- **Forgot Password** – Email sent for password recovery.
- **Due Date Reminder** – Email notification if a book is not returned on time.
- **Book Borrowed** – Email confirmation when a book is borrowed.
- **Book Returned** – Email confirmation when a book is returned.
- **User Registration** – Email sent upon successful registration.
