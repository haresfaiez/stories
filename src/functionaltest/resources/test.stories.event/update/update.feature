Feature:
  Emma wants to share her update with the visitors of "Big Party"

  Background:
    Given "Big Party" is an event at "20:00" in "Tunis"

  Scenario:
  Emma updates the event
    And   Emma is participant of that event
    When  Emma updates the event with "Oh! Good party!" at "21:00"
    Then  the event stream should include that update
