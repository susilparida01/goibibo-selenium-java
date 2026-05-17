# Goibibo Selenium Automation Demo

This project is a Selenium-based automation suite for demonstrating flight booking flows on Goibibo. It uses Java, TestNG, Maven, and Page Object Model (POM) design pattern. It also includes integration for Extent Reports, Jenkins CI/CD, and Docker containerization.

## Project Structure

- **src/main/java**: Contains Page Objects, Utilities (Reporting, Config), and Listeners.
- **src/test/java**: Contains Test Cases and Base Test configuration.
- **testng.xml**: Suite configuration for running tests.
- **pom.xml**: Project dependencies and build configurations.
- **Dockerfile**: Docker configuration for containerizing the automation suite.
- **Jenkinsfile**: Pipeline definition for Jenkins.

## Prerequisites

- Java JDK 21
- Maven 3.x
- Google Chrome (or other supported browsers)
- Docker (for containerized execution)
- Jenkins (for CI/CD)

---

## 1. Local Setup and Execution

### Step 1: Clone the Repository
```bash
git clone <repository-url>
cd goibibo-selenium-demo
```

### Step 2: Configure Properties
Update `src/main/resources/config/config.properties` with your environment details (though the defaults should work for the demo).

### Step 3: Execute Tests via Command Line
Run all tests using Maven:
```bash
mvn test -Dheadless=true
```
*Note: Set `headless=false` if you want to see the browser execution.*

### Step 4: View Reports
After execution, reports are generated in:
- **Extent Report**: `target/extent/ExtentReport_<timestamp>.html`
- **TestNG XML**: `target/surefire-reports/testng-results.xml`
- **Screenshots**: `target/screenshots/` (on failure)

---

## 2. Docker Containerization

The project includes a `Dockerfile` that sets up a Linux environment with Chrome and Maven to run tests.

### Build the Docker Image
```bash
docker build -t goibibo-automation .
```

### Run Tests in Docker
```bash
docker run --rm -v "%cd%:/app" goibibo-automation mvn test -Dheadless=true
```
*Note: Using `-v` (volume mount) allows you to see the generated reports on your local machine under the `target` folder after the container exits.*

---

## 3. Jenkins Freestyle Pipeline Setup

If you prefer a Freestyle project over the scripted Pipeline:

1.  **New Item**: Select "Freestyle project".
2.  **Source Code Management**: Select "Git" and provide the repository URL.
3.  **Build Environment**: (Optional) Check "Provide Configuration files" if using custom settings.
4.  **Build Steps**: 
    *   Add "Invoke top-level Maven targets".
    *   Goals: `clean test -Dheadless=true`.
5.  **Post-build Actions**:
    *   **Publish JUnit test result report**: Set Test report XMLs to `**/target/surefire-reports/*.xml`.
    *   **Archive the artifacts**: Set "Files to archive" to `target/extent/*.html, target/screenshots/*`.
    *   **(Optional)** If the HTML Publisher plugin is installed, add "Publish HTML reports" pointing to `target/extent`.

---

## 4. Jenkins Pipeline (Recommended)

The project includes a `Jenkinsfile` for a declarative pipeline.

1.  **New Item**: Select "Pipeline".
2.  **Pipeline**: Select "Pipeline script from SCM".
3.  **SCM**: Select "Git" and provide the repository URL.
4.  **Script Path**: Ensure it is set to `Jenkinsfile`.

The pipeline will automatically:
- Build the Docker image.
- Run tests inside a Docker container.
- Publish TestNG results and archive Extent reports/screenshots.

---

## Troubleshooting

- **Bot Detection**: Goibibo uses anti-bot measures. The framework uses `--disable-blink-features=AutomationControlled` to mitigate this, but execution in non-headless mode is generally more reliable.
- **Timeout Errors**: If you encounter timeouts, check the screenshots in `target/screenshots` to verify if the site structure has changed or a CAPTCHA appeared.
