# 🍽️ Restaurant Booking API (Mu Server)

Simple REST API for managing restaurant reservations, built using **Mu Server** and Java.

---

## 🚀 Overview

This project implements a minimal booking system where:

* Customers can create reservations
* Restaurant owners can view bookings by date

The goal is to demonstrate:

* REST API design
* Input validation
* Error handling
* Lightweight server implementation

---

## 🛠️ Tech Stack

* Java 17+
* Mu Server
* Maven
* Jackson (JSON processing)

---

## 📦 Project Structure

```
src/main/java/com/example/
 ├── Main.java              # Application entry point
 ├── Booking.java           # Data model (record)
 ├── BookingService.java    # Business logic
 └── BookingHandler.java    # REST endpoints
```

---

## ▶️ How to Run

1. Clone the repository:

```bash
git clone https://github.com/YOUR_USER/YOUR_REPO.git
cd YOUR_REPO
```

2. Build the project:

```bash
mvn clean install
```

3. Run the application:

```bash
mvn exec:java -Dexec.mainClass="com.example.Main"
```

OR run `Main.java` directly from your IDE.

---

## 🌐 API Endpoints

### 1. Create Booking

**POST** `/bookings`

#### Request:

```json
{
  "customerName": "Jorge",
  "tableSize": 4,
  "date": "2026-04-12",
  "time": "18:00"
}
```

#### Response:

* `201 Created` → Booking created
* `400 Bad Request` → Validation error

---

### 2. Get Bookings by Date

**GET** `/bookings?date=YYYY-MM-DD`

#### Example:

```bash
curl "http://localhost:8080/bookings?date=2026-04-12"
```

#### Response:

```json
[
  {
    "customerName": "Jorge",
    "tableSize": 4,
    "date": "2026-04-12",
    "time": "18:00"
  }
]
```

---

## ✅ Validations

The API enforces the following rules:

* `customerName` must not be empty
* `tableSize` must be greater than 0
* `date` cannot be in the past
* `time` must follow **2-hour intervals** (e.g., 18:00, 20:00)
* Duplicate bookings for the same date/time are not allowed

---

## ⚠️ Assumptions

* Data is stored **in memory** (no database)
* No table capacity or availability logic is implemented
* Time slots are fixed at 2-hour intervals
* Single instance application (no persistence)

---

## 🧪 Testing

You can test the API using:

### curl

```bash
curl -X POST http://localhost:8080/bookings \
-H "Content-Type: application/json" \
-d '{
  "customerName": "Jorge",
  "tableSize": 4,
  "date": "2026-04-12",
  "time": "18:00"
}'
```

### Postman

* Import endpoint
* Send JSON body

---

## 🧠 Design Decisions

* Used `record` for immutable data model
* Separated concerns:

    * Handler → HTTP layer
    * Service → business logic
* In-memory storage for simplicity
* Explicit validation for business rules

---

## 📌 Future Improvements

* Add persistent storage (e.g., database)
* Implement table availability logic
* Add unit and integration tests
* Improve error responses (standard JSON format)
* Add logging and monitoring

---

## 👨‍💻 Author

Developed as a technical exercise to demonstrate backend API skills using Java and lightweight frameworks.
