Feature: Test Province Cities API
  Scenario Outline:
    Given User have access to url "https://canadacities.herokuapp.com".
    When user call end point "/cities/{provinceCode}" with province code "<province_code>".
    Then status code should be <status_code>.
    And it should have content type "application/json".
    And it should have a city count of <city_count>.
    Examples:
      | province_code | status_code | city_count |
      | NB | 200 | 8 |
      | NL | 200 | 3 |
      | ON | 200 | 1 |
