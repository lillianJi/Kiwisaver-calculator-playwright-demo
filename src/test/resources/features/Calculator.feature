@smoke
Feature: Verify projection for growth after answer all questions

  Scenario Outline: The user who is under 65 and Employed wants to use KiwiSaver for first home
    Given user navigates to "calculatorUrl"
    Then user opens the calculator
    When user enters their "<age>"
    And user uses KiwiSaver savings for "First Home"
    And user expects to purchase first home in "<numberOfYear>" years
    And user is "Employed"
    And user enter "<income>" with "<frequency>"
    And user enters current KiwiSaver "<balance>"
    And user choose contribution rate "<contributionRate>"
    And user is in "<typeOfFund>" currently
    Then It should show user the projection for growth correctly

    Examples:
      | age | numberOfYear       | income | frequency | balance | contributionRate | typeOfFund   |
      | 22  | In 2 years         | 10000  | per month | 10000   | 8%               | Growth       |
      | 14  | In 5 years or more | 20000  | per year  | 0       | 3%               | Conservative |

  Scenario Outline: The user who is under 65 and Employed wants to use KiwiSaver for retirement
    Given user navigates to "calculatorUrl"
    Then user opens the calculator
    When user enters their "<age>"
    And user uses KiwiSaver savings for "Retirement"
    And user is "Employed"
    And user enter "<income>" with "<frequency>"
    And user enters current KiwiSaver "<balance>"
    And user choose contribution rate "<contributionRate>"
    And user is in "<typeOfFund>" currently
    Then It should show user the projection for growth correctly

    Examples:
      | age | income | frequency | balance | contributionRate | typeOfFund |
      | 22  | 10000  | per month | 10000   | 3%               | Moderate   |

  Scenario: The user who is above 65
    Given user navigates to "calculatorUrl"
    Then user opens the calculator
    When user enters their "66"
    Then It should pop up we are here to help message