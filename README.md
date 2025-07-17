# GoRestRestAssured
API Automation Testing with RestAssured testNG.  This repository contains automated API tests for the GoRest API using TestNG. It covers various CRUD operations to validate API endpoints. Integrated with GitHub Actions for CI/CD, the tests run automatically on every push or pull request.
<<<<<<< HEAD
# 🧪 GoRest API Automation Project (REST Assured + TestNG)

This project showcases REST API test automation for the [GoRest public API](https://gorest.co.in/) using a scalable and modular framework built with **REST Assured**, **TestNG**, **Java**, and **Maven**.

> ✅ Users, Posts, Comments modules  
> ✅ Full CRUD operations  
> ✅ Data-driven testing with CSV  
> ✅ Schema validation, status code checks, performance assertions  
> ✅ Parallel + sequential test execution strategy  
> ✅ Clean structure for both positive and negative test scenarios  

---

## 🚀 Tech Stack

- **Java 17**
- **REST Assured**
- **TestNG**
- **Maven**
- **Jackson** (JSON mapping)
- **OpenCSV** (CSV parsing)
- **SLF4J + Logback** (logging)

---

## 📁 Project Structure

src/
├── main/
│ └── java/com/gorest/
│ ├── base/ # BaseTest setup
│ ├── endpoints/ # Centralized API endpoint constants
│ ├── models/ # POJOs for Users, Posts, Comments
│ ├── utils/ # Assertions, schema validator, logger, CSV reader, TestDataManager
│ └── data/ # CSV DataProviders for test inputs
│
├── test/
│ └── java/com/gorest/tests/
│ ├── users/
│ │ ├── positive/ # Create, GetById, Update, Patch
│ │ └── negative/ # Invalid JSON, empty fields, bad email, etc.
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
Edit

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

- All POST, PUT, PATCH tests are data-driven  
- Test data comes from CSV files in `resources/data/`  
- Uses OpenCSV for reading data into POJOs  
- Flexible logic for skipping requests by `testType` or missing fields

---

## ✅ Assertions & Validations

- ✅ Status codes (200, 201, 204, 422, 404)
- ✅ Field-level response validations
- ✅ Schema validation via `.json` files
- ✅ Regex checks (e.g., email format)
- ✅ Performance validation: response time < 3000ms

---

## 🔀 Execution Strategy

- ✅ Parallel execution for independent tests (`GetAllUsers`, `GetAllPosts`, etc.)
- ✅ Sequential execution using `dependsOnMethods` for chained tests (Create → Update → Patch)
- ✅ Thread-safe `TestDataManager` using `ThreadLocal` where applicable

---

## 🧪 How to Run Tests

### Requirements
- Java 17+
- Maven

### Run All Tests
```bash
=======
GoRestRestAssured

API Automation Testing with RestAssured testNG. This repository contains automated API tests for the GoRest API using TestNG. It covers various CRUD operations to validate API endpoints. Integrated with GitHub Actions for CI/CD, the tests run automatically on every push or pull request.

🧪 GoRest API Automation Project (REST Assured + TestNG)

This project showcases REST API test automation for the GoRest public API using a scalable and modular framework built with REST Assured, TestNG, Java, and Maven.


✅ Users, Posts, Comments modules
✅ Full CRUD operations
✅ Data-driven testing with CSV
✅ Schema validation, status code checks, performance assertions
✅ Parallel + sequential test execution strategy
✅ Clean structure for both positive and negative test scenarios



🚀 Tech Stack
•Java 17
•REST Assured
•TestNG
•Maven
•Jackson (JSON mapping)
•OpenCSV (CSV parsing)
•SLF4J + Logback (logging)


📁 Project Structure

src/ ├── main/ │ └── java/com/gorest/ │ ├── base/ # BaseTest setup │ ├── endpoints/ # Centralized API endpoint constants │ ├── models/ # POJOs for Users, Posts, Comments │ ├── utils/ # Assertions, schema validator, logger, CSV reader, TestDataManager │ └── data/ # CSV DataProviders for test inputs │ ├── test/ │ └── java/com/gorest/tests/ │ ├── users/ │ │ ├── positive/ # Create, GetById, Update, Patch │ │ └── negative/ # Invalid JSON, empty fields, bad email, etc. │ ├── posts/ │ │ ├── positive/ │ │ └── negative/ │ ├── comments/ │ │ ├── positive/ │ │ └── negative/ │ └── delete/ # Delete by userId, postId, commentId │ └── resources/ ├── data/ # CSV test data files └── schemas/ # JSON schema files for validation

markdown Copy Edit


✅ Test Coverage

Users Module
•✅ POST /users – Create user

•✅ GET /users/{id} – Get user by ID

•✅ PUT /users/{id} – Update user

•✅ PATCH /users/{id} – Partial update

•✅ DELETE /users/{id} – Delete user


Posts Module
•✅ POST /posts – Create post

•✅ GET /posts/{id} – Get post by ID

•✅ PUT /posts/{id} – Update post

•✅ PATCH /posts/{id} – Partial update

•✅ DELETE /posts/{id} – Delete post


Comments Module
•✅ POST /comments – Create comment

•✅ GET /comments/{id} – Get comment by ID

•✅ PUT /comments/{id} – Update comment

•✅ PATCH /comments/{id} – Partial update

•✅ DELETE /comments/{id} – Delete comment



🧬 Data-Driven Testing
•All POST, PUT, PATCH tests are data-driven

•Test data comes from CSV files in resources/data/

•Uses OpenCSV for reading data into POJOs

•Flexible logic for skipping requests by testType or missing fields


✅ Assertions & Validations
•✅ Status codes (200, 201, 204, 422, 404)
•✅ Field-level response validations
•✅ Schema validation via .json files
•✅ Regex checks (e.g., email format)
•✅ Performance validation: response time < 3000ms


🔀 Execution Strategy
•✅ Parallel execution for independent tests (GetAllUsers, GetAllPosts, etc.)
•✅ Sequential execution using dependsOnMethods for chained tests (Create → Update → Patch)
•✅ Thread-safe TestDataManager using ThreadLocal where applicable


🧪 How to Run Tests

Requirements
•Java 17+
•Maven

Run All Tests
>>>>>>> e3e26b8a9c55a1136ba7cfc1f07da47d38934a84
mvn clean test
Run Specific Suite
bash
Copy
Edit
mvn test -DsuiteXmlFile=testng.xml
📸 Sample Test Output
Status code: ✅ 201 Created

Response time: ✅ < 2000 ms

Schema: ✅ Passed

Logs: Available in console with SLF4J

📌 Notes
GoRest is a free public API. IDs may conflict if run multiple times.

Each user/post/comment created is unique using randomized values.

Proper cleanup is ensured by DELETE requests at the end.

📂 Version Control
Project hosted on GitHub
📌 GitHub Repo
<<<<<<< HEAD

🙋‍♀️ Author
Saranya Seenivasan
🔗 LinkedIn
💻 SDET | API | UI | Automation | Testing Portfolio

python
Copy
Edit
=======
- Jenkins is configured to run `testng.xml` using Maven on every build
- Tests are executed using the `mvn clean test -DsuiteXmlFile=testng.xml` command
- Results are published via JUnit (and optional HTML reports)

In local setup:
- Jenkins is manually triggered after each push
- GitHub webhook integration is possible using ngrok or cloud-hosted Jenkins

✅ Build Success Example:
![Build Passed]![Jenkins Dashboard](C:\Users\sridh\eclipse-workspace\LMSUI\gorest-api-automation\images)

🙋‍♀️ Author
Saranya Seenivasan

💻 SDET |UI, POSTMAN, REST ASSURED
>>>>>>> e3e26b8a9c55a1136ba7cfc1f07da47d38934a84
