# 🧪 GoRestRestAssured

This project showcases REST API test automation for the [GoRest public API](https://gorest.co.in/) using a scalable and modular framework built with **REST Assured**, **TestNG**, **Java**, and **Maven**.

> ✅ Users, Posts, Comments modules  
> ✅ Full CRUD operations  
> ✅ Data-driven testing with CSV  
> ✅ Schema validation, status code checks, performance assertions  
> ✅ Parallel + sequential test execution strategy  
> ✅ Clean structure for both positive and negative test scenarios  

---

## 🚀 Tech Stack

- Java 17
- REST Assured
- TestNG
- Maven
- Jackson (JSON mapping)
- OpenCSV (CSV parsing)
- SLF4J + Logback (logging)

---

## 📁 Project Structure

src/
├── main/java/com/gorest/
│ ├── base/ # BaseTest setup
│ ├── endpoints/ # Centralized API endpoint constants
│ ├── models/ # POJOs for Users, Posts, Comments
│ ├── utils/ # Assertions, schema validator, logger, CSV reader, TestDataManager
│ └── data/ # CSV DataProviders for test inputs
│
├── test/java/com/gorest/tests/
│ ├── users/
│ │ ├── positive/
│ │ └── negative/
│ ├── posts/
│ │ ├── positive/
│ │ └── negative/
│ ├── comments/
│ │ ├── positive/
│ │ └── negative/
│ └── delete/ # Delete by userId, postId, commentId
│
└── resources/
├── data/ # CSV test data files
└── schemas/ # JSON schema files for validation

markdown
Copy

---

## ✅ Test Coverage

### Users Module
- ✅ `POST /users` – Create user  
- ✅ `GET /users/{id}` – Get user by ID  
- ✅ `PUT /users/{id}` – Update user  
- ✅ `PATCH /users/{id}` – Partial update  
- ✅ `DELETE /users/{id}` – Delete user  

### Posts Module
- ✅ `POST /posts` – Create post  
- ✅ `GET /posts/{id}` – Get post by ID  
- ✅ `PUT /posts/{id}` – Update post  
- ✅ `PATCH /posts/{id}` – Partial update  
- ✅ `DELETE /posts/{id}` – Delete post  

### Comments Module
- ✅ `POST /comments` – Create comment  
- ✅ `GET /comments/{id}` – Get comment by ID  
- ✅ `PUT /comments/{id}` – Update comment  
- ✅ `PATCH /comments/{id}` – Partial update  
- ✅ `DELETE /comments/{id}` – Delete comment  

---

## 🧬 Data-Driven Testing

- POST, PUT, PATCH tests are fully data-driven  
- Test data is stored in CSV files under `resources/data/`  
- Uses OpenCSV to deserialize CSV into Java POJOs  
- Conditional execution based on testType field and null checks

---

## ✅ Assertions & Validations

- ✅ Status code checks (200, 201, 204, 422, 404)
- ✅ Field-level response validation
- ✅ JSON schema validation with `.json` schemas
- ✅ Regex validations (e.g., email format)
- ✅ Performance: max response time under 3000ms

---

## 🔀 Execution Strategy

- ✅ Parallel execution for independent tests like GetAllUsers/GetAllPosts  
- ✅ Sequential execution for chained operations using `dependsOnMethods`  
- ✅ Thread-safe `TestDataManager` using `ThreadLocal` when needed

---

## 🧪 How to Run Tests

### Requirements
- Java 17+
- Maven

### Run All Tests
```bash
mvn clean test
Run Specific Suite
bash
Copy
mvn test -DsuiteXmlFile=testng.xml
🔁 CI/CD: Jenkins Integration
Jenkins is configured to run testng.xml using Maven on every build

Tests run using:
mvn clean test -DsuiteXmlFile=testng.xml

Results are published via JUnit and optionally via HTML reports

In local setup:
Jenkins is manually triggered after each push

GitHub webhook integration is possible using ngrok or a cloud-hosted Jenkins instance

✅ Build Success Example:


📸 Sample Test Output
Status Code: ✅ 201 Created

Response Time: ✅ < 2000ms

Schema Validation: ✅ Passed

Logs: Available via SLF4J console output

📌 Notes
GoRest is a public API; duplicate or invalid IDs may appear if run frequently

All test data uses randomized email/ID to ensure uniqueness

DELETE calls are used to clean up created data during test runs

🧾 Version Control
Project hosted on GitHub: GoRestRestAssured

🙋‍♀️ Author
Saranya Seenivasan
💻 SDET | UI | API | Automation | Testing Portfolio
🔗 LinkedIn: www.linkedin.com/in/saranya-seenivasan




