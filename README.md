## Overview

This automation test framework is built using Playwright, a powerful tool for automating web applications, and Cucumber, a behavior-driven development (BDD) tool that allows you to write tests in a human-readable format.
This framework enables you to write automated tests for web applications in a structured and maintainable way.

### Built With

- [Playwright](https://playwright.dev)
- [Cucumber](https://cucumber.io/)
- [JUnit](https://junit.org/junit5/)
- [Maven](https://maven.apache.org/)
- [OpenJDK](https://www.openlogic.com/openjdk-downloads)

Top Features:
- **Playwright Integration**: Playwright provides a high-level API for automating browsers, enabling you to interact with web elements, perform actions, and verify page content.
- **Cucumber Integration**: Cucumber allows you to write test scenarios in Gherkin syntax, making them easy to understand by both technical and non-technical stakeholders.
- **Page Object Model (POM)**: The framework follows the Page Object Model pattern, separating page interactions from test logic for better code organization and maintainability.
- **Test Data Management**: Test data is managed centrally and can be shared across test steps using the test context, ensuring consistency and reusability.
- **Reporting**: The framework generates Spark PDF/HTML Reports, providing insights into test execution and results.
- **Screenshot**: The framework takes screenshot on the failed page, providing more visibility to troubleshoot.

## Getting Started
To set up the automation test framework locally, follow these steps:
1.  **Precondition**
    - Java 11 must be installed and configured
    - Maven must be installed and configured
2. **Clone the Repository**: Clone this repository to your local machine.
    ```sh
    https://github.com/lillianJi/westpac-calculator-playwright-demo.git
    ```
2. **Install Dependencies**: Navigate to the project directory and install dependencies using Maven.
    ```sh
    mvn clean install
    ```
3. **Run Tests**: Execute the automated tests using Maven or execute from Cucumber feature.
- From maven
    ```sh
    mvn test "-Dcucumber. options=--tags @smoke"
    ```
- From cucumber

    Right-click the feature file, and click "Run Feature:Calculator".
    Or open feature file and right-click to run single scenario
4. **View Reports**: After test execution, view the generated test reports located in the `target/HTMLReport` and `target/PdfReport` directory.
5. **Configuration**
-  For Browser Configuration, change required value in browser and headless mode in `src/test/resources/config/config.properties`.
-  For executing test cases in parallel, navigate to pom.xml and in plugin in configuration section
   provide `<skipTests>false</skipTests>`
   Now if you want to run 4 features in parallel provide `<threadCount>4</threadCount>` in maven-failsafe-plugin section
   in pom.xml and execute below command
    ```JS
    mvn verify
    ```

## Project Structure

The project follows a standard Maven project structure with the following main directories:
- **src/test/java**: Contains the Java test code, including step definitions, page objects, test runners, test context, etc.
- **src/test/resources**: Contains the Cucumber feature files and other test-related resources.
- **target**: Contains build artifacts, including test reports and compiled code.

## Report

## TODO List
- More Assertion: add more assertion, i.e "Things to know", the question displaying logic, etc.
- More scenarios
