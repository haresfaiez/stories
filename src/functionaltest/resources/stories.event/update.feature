Feature:
  Emma wants to share her update with the visitors of "Big Party"

  Background:
    Given the event
      | Title     | Time             | Location |
      | Big Party | 2016-01-01T20:00 | Tunis    |

  Scenario:
  Emma updates the event
    And   Emma is participant of that event
    When  Emma updates the event with
      | Message         | Time             |
      | Oh! Good party! | 2016-01-01T21:00 |
    Then  the event stream should include that update
