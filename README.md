This project is set up for a UAT demonstration and includes a web application and Serenity BDD automation tests.

## Setup Instructions

### Prerequisites
- Java 17: Ensure you have Java Development Kit (JDK) 17 installed.
- Node.js 20+: Ensure you have Node.js version 20 or later installed.
- Python 3: For running the local web server.
- Google Chrome: For generating PDF reports.

### Installation

1.  **Clone the repository** (if not already done).
2.  **Navigate to the project root directory** in your terminal.

## Running the Application

### 1. Start the Web Application Server

Open your terminal, navigate to the project root, and run:
```bash
python3 -m http.server 8000 --directory web
```
This will start a local web server serving the static files from the `web` directory on `http://localhost:8000`.

### 2. Execute Serenity BDD Tests

Open a **new terminal** in the project root directory and run:
```bash
mvn clean verify
```
This command will:
- Clean previous build artifacts.
- Compile the Java code.
- Run the Serenity BDD tests against the web application.
- Aggregate test reports.

### 3. Generate and Convert Reports

After the tests have completed, Serenity BDD will generate HTML reports in the `target/site/serenity` directory.

To convert the aggregate HTML report to PDF, run the following command in your terminal:
```bash

# Ensure google-chrome is installed and in your PATH
google-chrome --headless --print-to-pdf=target/site/serenity/report.pdf target/site/serenity/index.html
```
This will create a `report.pdf` file in the `target/site/serenity` directory.
