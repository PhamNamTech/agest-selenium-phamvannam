# Railway Ticket Booking - Selenium Test Automation

Automated testing framework for Railway Ticket Booking System using Selenium WebDriver, TestNG, and Page Object Model (POM) pattern.

**Author:** Phạm Văn Nam

---

## Overview

| Item | Details |
|------|---------|
| **AUT** | SafeRailway (http://saferailway.somee.com) |
| **Framework** | TestNG + Selenium 4 |
| **Language** | Java 11+ |
| **Design Pattern** | Page Object Model (POM) |
| **Build Tool** | Maven 3.6+ |

---

## Technology Stack

- **Selenium WebDriver 4.27.0** - Browser automation
- **TestNG 7.12.0** - Test execution framework
- **Log4j 2.24.3** - Logging
- **Jackson 2.21.0** - JSON data processing
- **WebDriverManager 5.9.2** - Browser driver management
- **Allure 2.33.0** - Test reporting

---

## Architecture

```
┌─────────────────────────────────────────────────────────┐
│                   TestCases Layer                        │
│  (LoginTest, BookTicket, CancelBooking, etc.)            │
└────────────────┬────────────────────────────────────────┘
                 │
┌────────────────▼────────────────────────────────────────┐
│                PageObjects Layer (POM)                   │
│  (LoginPage, BookTicketPage, HomePage, etc.)             │
└────────────────┬────────────────────────────────────────┘
                 │
┌────────────────▼────────────────────────────────────────┐
│                Utilities & Helpers                       │
│  (FluentWait, WebDriver Setup, Data Utils, Logger)       │
└────────────────┬────────────────────────────────────────┘
                 │
┌────────────────▼────────────────────────────────────────┐
│              Selenium WebDriver                          │
│         (Chrome, Firefox via WebDriverManager)           │
└─────────────────────────────────────────────────────────┘
```

---

## Project Structure

```
web-railway/
├── TestCases/Railway/
│   ├── LoginTest.java              # Login functionality
│   ├── LogoutTest.java             # Logout scenarios
│   ├── CreateAccount.java          # Registration tests
│   ├── ResetPassword.java          # Password recovery
│   ├── BookTicket.java             # Ticket booking (data-driven)
│   ├── CancelBooking.java          # Cancellation flows
│   └── TestBase.java               # Base test configuration
│
├── PageObjects/Railway/
│   ├── GeneralPage.java            # Base page class
│   ├── LoginPage.java              # Login & reset password
│   ├── RegisterPage.java           # Registration form
│   ├── HomePage.java               # Main navigation
│   ├── BookTicketPage.java         # Booking interface
│   ├── MyTicketPage.java           # Ticket management
│   ├── TimetablePage.java          # Schedule lookup
│   ├── TicketPricePage.java        # Pricing info
│   └── FAQPage.java                # FAQ page
│
├── Helpers/Railway/
│   ├── PreconditionHelper.java     # Pre-test setup
│   └── TableHelper.java            # Table parsing
│
├── DataObjects/Railway/
│   ├── RegisterAccount.java        # User account model
│   ├── BookTicketData.java         # Booking data model
│   └── DataObjectBase.java         # Base POJO
│
├── Common/
│   ├── Common/
│   │   ├── Utilities.java          # FluentWait, WebDriver setup
│   │   ├── Log4j.java              # Logging wrapper
│   │   ├── DataProviders.java      # TestNG data providers
│   │   └── JsonDataReader.java     # JSON test data loader
│   ├── Constant/
│   │   ├── Constant.java           # App constants
│   │   ├── PageMenu.java           # Menu enum
│   │   ├── FieldsLogin.java        # Login field mapping
│   │   ├── SeatType.java           # Seat types
│   │   ├── StationCity.java        # Stations
│   │   └── TicketTableCol.java     # Table columns
│
├── Resources/Railway/
│   ├── BookTicket.json             # Test data
│   └── log4j2.xml                  # Log configuration
│
├── pom.xml                         # Maven config
├── testng.xml                      # Test suite definition
└── README.md
```

---

## Test Coverage

| Test Class | Scenarios | Count |
|-----------|-----------|-------|
| LoginTest | Valid/invalid login, remember me | 3 |
| LogoutTest | Logout & session cleanup | 1 |
| CreateAccount | Registration, validation, duplicates | 3 |
| ResetPassword | Forgot password, recovery | 2 |
| BookTicket | Single/round-trip, seat selection, pricing | 8 |
| CancelBooking | Cancellation, refund | 2 |
| **Total** | | **19 test cases** |

---

## Getting Started

### Prerequisites

- Java JDK 11+
- Maven 3.6+
- Git

### Setup

```bash
# Clone repository
git clone <repository-url>
cd web-railway

# Install dependencies
mvn clean install

# Verify setup
mvn --version
java --version
```

---

## Running Tests

### All Tests
```bash
mvn clean test
```

### Specific Browser
```bash
# Chrome (default)
mvn clean test -Dbrowser=chrome

# Firefox
mvn clean test -Dbrowser=firefox
```

### Specific Test Class
```bash
mvn clean test -Dtest=Railway.LoginTest
mvn clean test -Dtest=Railway.BookTicket
mvn clean test -Dtest=Railway.CancelBooking
```

### Single Test Method
```bash
mvn clean test -Dtest=Railway.BookTicket#testBookTicketSingleTrip
```

---

## Reports

### Allure Report

```bash
# Generate Allure report
mvn allure:report

# Open report
mvn allure:serve
```

### TestNG Report

Generated in `test-output/` directory automatically.

### Logs

View execution logs in `logs/` directory using Log4j configuration.

---

## Key Features

- **POM Design Pattern** - Maintainable and scalable page objects
- **Explicit Waits (FluentWait)** - Reliable element synchronization
- **Data-Driven Testing** - JSON-based test data parameterization
- **Logging** - Detailed execution traces via Log4j
- **Exception Handling** - Stale element and timeout recovery
- **Browser Management** - Automatic driver handling (Chrome/Firefox)
- **Type-Safe Constants** - Enums for menus, seats, stations

---

## Dependencies

See `pom.xml` for complete dependency list.

---

## Notes

- All test data is externalized in `Resources/Railway/BookTicket.json`
- Screenshots captured on test failure (configured in Log4j)
- Cross-browser support: Chrome and Firefox

### Run Specific Test Method

```bash
mvn clean test -Dtest=Railway.LoginTest#testLoginWithValidCredentials
```

### Run with Maven Surefire

```bash
mvn clean test -DsuiteXmlFile=testng.xml
```

---

## 📈 Test Reports

### Generate Allure Report

```bash
mvn allure:report
```

### View Allure Report

```bash
mvn allure:serve
```

Report opens automatically in browser at `http://localhost:4444`

### TestNG Report

- Located in: `test-output/index.html`

### Logs

- Located in: `logs/TestExecution.log`

---

## ⚙️ Configuration

### Test Configuration (testng.xml)

```xml
<suite name="Railway Test Suite">
    <parameter name="browser" value="chrome"/>

    <test name="Railway Test">
        <classes>
            <class name="Railway.LoginTest"/>
            <class name="Railway.LogoutTest"/>
            <class name="Railway.CreateAccount"/>
            <class name="Railway.ResetPassword"/>
            <class name="Railway.BookTicket"/>
            <class name="Railway.CancelBooking"/>
        </classes>
    </test>
</suite>
```

### Application Settings (Constant.java)

```properties
Base URL: http://saferailway.somee.com
Test User: phamnam@sharklasers.com
Test Password: 11111111
Implicit Wait: 12 seconds
Browser Window: Maximized
```

### Logging Configuration (Log4j)

- Level: INFO (can be adjusted to DEBUG)
- Output: Console + File (logs/TestExecution.log)
- Format: Timestamp | Level | Message

---

## 💾 Test Data Management

### Data-Driven Testing

- Test data stored in JSON format: `Resources/Railway/BookTicket.json`
- Loaded via `JsonDataReader` utility
- Used with TestNG `@DataProvider` for parameterized execution

### Example Test Data

```json
{
  "ticketBookings": [
    {
      "departStation": "SGN",
      "arriveStation": "HAN",
      "tripDate": "21/12/2024",
      "seatType": "SeatType.HARD_SEAT",
      "quantity": 1
    }
  ]
}
```

---

## 🔧 Key Classes & Responsibilities

### Test Layer (TestCases/)

- Contains test methods with assertions
- Uses Page Objects for UI interaction
- Inherits from TestBase for WebDriver management

### Page Object Layer (PageObjects/)

- Encapsulates UI element locators
- Provides page-specific action methods
- Returns page objects for method chaining (fluent API)

### Utility Layer (Common/)

- **Utilities.java** - Common operations (scroll, click, wait, file I/O)
- **Log4j.java** - Structured logging with different severity levels
- **DataProviders.java** - TestNG data providers for parameterized tests

### Helper Layer (Helpers/)

- **PreconditionHelper** - Pre-test setup (create test accounts, activate users)
- **TableHelper** - Parse and verify table data

### Data Layer (DataObjects/)

- POJOs representing business entities
- Used for test data serialization/deserialization
- Mapped from JSON files via Jackson

---

## 📱 Best Practices Implemented

1. **Separation of Concerns** - Clear separation between test logic, page interactions, and utilities
2. **DRY Principle** - Reusable methods in base classes and utilities
3. **Single Responsibility** - Each class has one clear purpose
4. **Maintainability** - Constants centralized, easy to update application URLs/credentials
5. **Scalability** - Page Object pattern makes adding new tests straightforward
6. **Readability** - Descriptive method names, self-documenting code
7. **Error Handling** - Graceful exception handling with informative logs
8. **Reporting** - Allure integration for stakeholder-friendly reports

---

## 🐛 Troubleshooting

### Issue: Tests fail due to StaleElementReferenceException

**Solution:** Utilities class includes retry logic with explicit waits. Verify explicit wait timeouts in Constant.java.

### Issue: WebDriver not found

**Solution:** WebDriverManager automatically downloads drivers. Check internet connectivity and Maven setup.

### Issue: Allure report not generating

**Solution:** Run `mvn clean test` first, then `mvn allure:report`. Ensure AspectJ weaver is properly configured in pom.xml.

### Issue: Tests pass locally but fail in CI/CD

**Solution:** Verify browser version compatibility, check headless mode compatibility, verify test data paths.

---

## 📚 Documentation References

- [Selenium Documentation](https://www.selenium.dev/documentation/)
- [TestNG Documentation](https://testng.org/doc/)
- [Allure Framework](https://docs.qameta.io/allure/)
- [Maven Documentation](https://maven.apache.org/guides/)

---

## 🎓 Learning Outcomes Demonstrated

- ✅ Advanced Selenium WebDriver automation
- ✅ Page Object Model design pattern
- ✅ TestNG framework expertise
- ✅ Data-driven testing approach
- ✅ Maven build automation
- ✅ Allure reporting
- ✅ Exception handling & robust test design
- ✅ Clean code principles & architecture
- ✅ Log4j logging framework
- ✅ JSON data processing

---
