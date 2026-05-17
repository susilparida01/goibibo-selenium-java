# Goibibo Selenium Demo

This project is a Selenium WebDriver demonstration for the Goibibo website, built with Java, TestNG, and Maven.

## Prerequisites

- Java 21 or higher
- Maven
- Chrome Browser (or other supported browsers)

## Setup

1. Clone the repository.
2. Update `src/main/resources/config/config.properties` with your specific configurations if needed.

## Running Tests

To run the tests, use the following Maven command:

```bash
mvn test
```

## Test Cases

- `Test_GoibiboQuickLaunch`: Validates that the Goibibo homepage is successfully launched and the title is correct.
- `Test_GoibiboFlightBooking`: An end-to-end test case covering:
    - Searching for flights with source, destination, and travel details.
    - Selecting a flight from the results.
    - Entering passenger details.
    - Navigating through a dummy payment process.
    - Verifying the booking flow.

## Project Structure

- `src/main/java`: Contains the Page Object Model (POM) classes and utility classes.
- `src/test/java`: Contains the test cases.
- `src/main/resources/config`: Configuration files.
- `test-output`: (Ignored) Contains test execution reports.
