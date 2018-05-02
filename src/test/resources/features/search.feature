Feature: Search functionality

  Background: Preconditions
    Given Home page is opened

  Scenario Outline: Check search results in Global Search drop-down list
    When Enter "<text>" into Global search field
    Then The values of Global search drop-down list should start with "<text>"
    Examples:
      | text        |
      | apple       |
      | apple juice |

