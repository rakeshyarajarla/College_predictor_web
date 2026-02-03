# College Predictor Web App

A powerful **Full Stack Web Application** that predicts college admissions based on user rank, gender, category, and preferred branch. 

## 🚀 Project Overview
This project is built completely from scratch, demonstrating a deep understanding of core web technologies without relying on heavy frameworks. It connects a modern, responsive frontend to a robust Java backend and a MySQL database.

## 🏗️ Full Stack Architecture

### 1. Frontend (The View)
*   **HTML5**: Semantic structure for the web interface.
*   **CSS3**: Custom, premium styling with glassmorphism effects, gradients, and responsive design (no CSS libraries used).
*   **JavaScript (ES6+)**: Handles user interactions, form validation, and asynchronous API calls (AJAX/Fetch) to the Java backend.

### 2. Backend (The Server)
*   **Java (JDK 11+)**: Core logic implementation.
*   **Custom HTTP Server**: Built using Java's `com.sun.net.httpserver` to handle HTTP requests and serve static files.
*   **REST API**: Implements a `/api/predict` endpoint that processes JSON data.
*   **JDBC**: Manages secure and efficient connectivity with the MySQL database.

### 3. Database (The Data)
*   **MySQL**: Relational database storing college data (Institutes, Branches, Fees, Cutoff Ranks).
*   **Data Models**: Structured tables for Boys (`college_details_boys`) and Girls (`college_details_girls`).

## ✨ Key Features
*   **Real-time Prediction**: Instant results based on EAMCET ranks.
*   **Categorized Filtering**: Filters by Gender (Male/Female), Category (OC, SC, ST, BC-A/B/C/D/E), and Branch (CSE, ECE, etc.).
*   **Premium UI/UX**: A visually stunning interface with smooth animations and a clean layout.
*   **Portable**: Project is self-contained with bundled dependencies for easy sharing.
*   **Deployment Ready**: strict separation of concerns allows for cloud deployment (Docker support included).

## 🛠️ Technologies Used
*   **Languages**: Java, JavaScript, HTML, CSS, SQL.
*   **Database**: MySQL Server.
*   **Tools**: Git, Docker (optional).

---

## 📥 How to Run (Local System)
1.  **Prerequisites**: Ensure Java (JDK 11+) and MySQL are installed.
2.  **Database Setup**:
    *   Import your `college_program` database schema.
    *   Ensure username is `root` and password is `mysql` (or update `DatabaseConnection.java` / Environment Variables).
3.  **Start the App**:
    *   Open the project folder.
    *   Double-click **`run.bat`**.
4.  **Access**: Open `http://localhost:8000` in your browser.

## 🤝 How to Run on Another System (Friend's PC)
1.  **Export Data**: Export your `college_program` database to a `.sql` file.
2.  **Transfer**: Copy this entire folder to the new system.
3.  **Import Data**: Import the `.sql` file into the friend's MySQL server.
4.  **Run**: Double-click `run.bat`.
    *   *Note: This project includes the necessary MySQL Java Connector in the `lib/` folder, so no complex classpath setup is needed.*

## ☁️ Deployment
See the **`deployment_guide.md`** file for instructions on how to deploy this project to the cloud (Render, Railway, etc.) using Docker.

This the link:
https://collegepredictorweb-production.up.railway.app/
