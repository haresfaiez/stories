Feature:
  Emma wants to share her feeling with people interested in her favourite event

  Scenario:
  Emma updates the event
    Given Emma is participant of "Big Party"
    When  Emma updates the event with "Oh! Good party!"
    Then  the event stream should include that update
