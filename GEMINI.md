# Project Context: Goibibo Selenium Demo

## Architecture
- **Language**: Java 21
- **Framework**: TestNG
- **Build Tool**: Maven
- **Design Pattern**: Page Object Model (POM)
- **Reporting**: ExtentReports
- **Utilities**: ConfigReader, Log, ReportManager, DriverFactory

## Conventions
- Use `ConfigReader` for property management.
- Log steps using `Log.step()`.
- Page objects are located in `com.simplilearn.goibibo_selenium_demo.pages`.
- Tests are located in `com.simplilearn.goibibo_selenium_demo.testcases`.

## Workflow
- Run tests via `mvn test`.
- Configuration is managed in `src/main/resources/config/config.properties`.
- Build artifacts and IDE files are ignored via `.gitignore`.
