Feature: Search functionality

  Background: Preconditions
    Given Home page is opened

  Scenario Outline: Check auto-filling functionality of Global Search drop-down list
    When Enter "<text>" into Global search field
    Then The values of Global search drop-down list should start with "<text>"
    Examples:
      | text        |
      | apple       |
      | apple juice |

    Scenario: Check Global Search functionality
      When Enter "Aimprosoft" into Global search field
      When Click on Global Search button
      Then Search results should contain "MMMMMM"



