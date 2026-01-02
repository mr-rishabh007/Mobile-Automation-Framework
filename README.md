📱 Mobile Automation Framework (Appium & Java)
🚀 Overview
This repository showcases a professional-grade mobile automation framework for Android. It is built using the Page Object Model (POM) to ensure clean code separation, scalability, and ease of maintenance. The framework handles both Native and Hybrid application flows.

✨ Key Features
Design Pattern: Full implementation of Page Object Model (POM) to separate UI locators from test logic.

Hybrid App Support: Capability to automate WebView and Native contexts (see eCommerce_TC4_Hybrid.java).

Custom Action Library: Centralized Android gestures (Swipe, Scroll, Long Press) in AndroidActions.java using the W3C Actions API.

Automated Reporting: Integrated Extent Reports that automatically capture and attach screenshots on test failure via TestNG Listeners.

Data-Driven Capabilities: Structured to handle external configurations and test data.

🛠 Tech Stack
Language: Java (JDK 17)

Automation Engine: Appium (UIAutomator2)

Test Runner: TestNG

Build Tool: Maven

Reporting: ExtentReports

Inspector: Appium Inspector / UIAutomatorViewer

📁 Project Structure
Plaintext

AppiumFramework
├── src/main/java
│   └── org.rishabhchoure.pageobjects.utils
│       ├── AndroidActions.java      # Android-specific gestures
│       └── AppiumUtils.java         # Common framework utilities
├── src/test/java
│   ├── org.rishabhchoure.AppiumFramework
│   │   ├── AndroidBaseTest.java     # Setup/Teardown logic
│   │   ├── eCommerce_TC1.java       # Functional test cases
│   │   └── eCommerce_TC4_Hybrid.java # Hybrid app test cases
│   ├── org.rishabhchoure.pageobjects.android
│   │   ├── FormPage.java            # Page Factory for Login/Form
│   │   ├── ProductsCatalogue.java   # Page Factory for Product List
│   │   └── CartPage.java            # Page Factory for Checkout
│   └── org.rishabhchoure.Utils
│       ├── ExtentReporterNG.java    # Reporting configuration
│       └── Listeners.java           # TestNG Listeners for failure capture
├── reports/                         # HTML reports and failure screenshots
├── pom.xml                          # Maven dependencies
└── testng.xml                       # Test suite execution configuration
⚙️ Setup & Execution
Prerequisites: Install Node.js, Appium Server, and Android Studio (SDK).

Clone the Repository:

Bash

git clone https://github.com/yourusername/AppiumFramework.git
Run Tests via Maven:

Bash

mvn clean test
View Reports: Open reports/index.html in any browser to see the latest execution results.

📊 Challenges & Solutions
Handling Flaky Elements: Implemented Explicit Waits within the Page Object classes to manage synchronization issues on slow-loading mobile screens.

Context Switching: Solved the challenge of automating Hybrid apps by implementing logic to identify and switch between NATIVE_APP and WEBVIEW contexts.

Next Step for You:
Create a file named .gitignore in your project root and add these two lines so you don't upload "junk" files to GitHub:

Plaintext

target/
test-output/
