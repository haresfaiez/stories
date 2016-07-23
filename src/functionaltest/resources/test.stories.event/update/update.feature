Feature:
  Emma wants to share her feeling with people interested in her favourite event

  Scenario:
  Emma updates the event
    Given Emma participates to "Big Party"
    When  Emma updates the event with "Oh! Good party!"
    Then  all visitors of the event should get that update
