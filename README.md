📱 Mobile Automation Framework (Appium & Java)
🚀 Overview
This repository showcases a professional-grade mobile automation framework for Android. It is built using the Page Object Model (POM) to ensure clean code separation, scalability, and ease of maintenance. The framework is designed to handle both Native and Hybrid application flows.

✨ Key Features
Design Pattern: Full implementation of Page Object Model (POM) and Page Factory.

Hybrid App Support: Capability to automate WebView and Native contexts (see eCommerce_TC4_Hybrid.java).

Custom Action Library: Centralized Android gestures (Swipe, Scroll, Long Press) in AndroidActions.java using the W3C Actions API.

Automated Reporting: Integrated Extent Reports that automatically capture and attach screenshots on test failure via TestNG Listeners.

📁 Project Structure
AppiumFramework
├── src/main/java
│   └── org.rishabhchoure.pageobjects.utils
│       ├── AndroidActions.java      # Android-specific gestures
│       └── AppiumUtils.java         # Common framework utilities
├── src/test/java
│   ├── org.rishabhchoure.AppiumFramework
│   │   ├── eCommerce_TC1.java       # Functional test cases
│   │   └── eCommerce_TC4_Hybrid.java # Hybrid app test cases
│   ├── org.rishabhchoure.pageobjects.android
│   │   ├── FormPage.java            # Page Objects for Form
│   │   └── CartPage.java            # Page Objects for Checkout
│   └── org.rishabhchoure.Utils
│       ├── ExtentReporterNG.java    # Reporting configuration
│       └── Listeners.java           # TestNG Listeners
├── reports/                         # HTML reports and failure screenshots
└── testng.xml                       # Test suite configuration

📊 Sample Report
Note: Screenshots are automatically attached to the report on failure (e.g., FillFormErrorValidation.png).
