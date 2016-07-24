Feature:
  Emma wants to share her feeling with people interested in her favourite event

  Scenario:
  Emma updates the event
    Given "Big Party" is an event at "20:00" in "Tunis"
    And   Emma is participant of that event
    When  Emma updates the event with "Oh! Good party!" at "21:00"
    Then  the event stream should include that update
