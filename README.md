# Railway Ticket Booking - Selenium Test Automation

Automated testing framework for Railway Ticket Booking System using Selenium WebDriver, TestNG, and Page Object Model (POM) pattern.

## Project Information

**Application Under Test:** SafeRailway  
**Author:** Phạm Văn Nam

## Technologies

- Java 11+
- Selenium WebDriver 4.27.0
- TestNG 7.12.0
- Maven 3.x
- Log4j 2
- Jackson

## Project Structure

```
web-railway/
├── TestCases/Railway/          # Test cases
├── PageObjects/Railway/        # Page object classes
├── Helpers/Railway/            # Helper utilities
├── DataObjects/Railway/        # Data models
├── Common/                     # Common utilities and constants
├── Resources/                  # Test data
├── pom.xml
├── testng.xml
└── README.md
```

## Test Coverage

- User authentication (login, logout)
- Account management (create, reset password)
- Ticket booking and cancellation
  ✅ **Comprehensive Logging** - Detailed test execution logs via Log4j  
  ✅ **Allure Reporting** - Rich, interactive test reports with screenshots  
  ✅ **Exception Handling** - Robust handling of stale elements and timeouts  
  ✅ **Reusable Utilities** - Common helpers for setup, data generation, and assertions  
  ✅ **Type-Safe Constants** - Enums for menus, seat types, stations ensuring code reliability  
  ✅ **Test Base Class** - Centralized WebDriver initialization & teardown

---

## 📊 Test Coverage

### Test Cases (6 Classes)

| Test Class        | Test Scenarios                                     | Coverage                                   |
| ----------------- | -------------------------------------------------- | ------------------------------------------ |
| **LoginTest**     | Valid login, invalid credentials, remember me      | User authentication                        |
| **LogoutTest**    | Logout verification, session cleanup               | Session management                         |
| **CreateAccount** | User registration, email validation, duplicates    | Account management                         |
| **ResetPassword** | Forgot password flow, new password setup           | Password recovery                          |
| **BookTicket**    | Single/round-trip booking, seat selection, pricing | Ticket booking (12+ data-driven scenarios) |
| **CancelBooking** | Ticket cancellation, refund calculation            | Transaction management                     |

**Total Test Cases:** 12+ (with data-driven parameterization)

---

## 🚀 Getting Started

### Prerequisites

- **Java JDK 11** or higher
- **Maven 3.6+**
- **git**

### Installation & Setup

1. **Clone the repository**

   ```bash
   git clone <repository-url>
   cd web-railway
   ```

2. **Install dependencies**

   ```bash
   mvn clean install
   ```

3. **Verify setup**
   ```bash
   mvn --version
   java --version
   ```

---

## 🎯 Running Tests

### Run All Tests

```bash
mvn clean test
```

### Run Tests with Specific Browser

```bash
# Chrome (default)
mvn clean test -Dbrowser=chrome

# Firefox
mvn clean test -Dbrowser=firefox
```

### Run Specific Test Class

```bash
mvn clean test -Dtest=Railway.LoginTest
mvn clean test -Dtest=Railway.BookTicket
```

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
