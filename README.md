<h1 align="center">Java Web CRUD application in JSF framework</h1>

---

## Prerequisites
- JDK 8
- MySQL
- Glassfish server 4.0

---

## Setup
1. Clone the repository:
```bash
git clone https://github.com/Bhabishworgrg/student-crud.git
cd student-crud
```

2. Create database:
- In MySQL, create a database named `student_crud`.

3. Update database credentials:
- In `src/main/resources/META-INF/persistence.xml`, change `USER` and `PASSWORD` to your database credentials:
```xml
		<property name="javax.persistence.jdbc.user" value="USER"/>
		<property name="javax.persistence.jdbc.password" value="PASSWORD"/>
```

4. Deploy the package:
```bash
mvn clean package
asadmin deploy target/student-crud.war
```

---

## Usage
- Open the following link to run the app:
http://localhost:8080/student-crud
