Feature:
  Emma wants to get informed about her favourite events

  Scenario:
  Emma wants to get real time updates of a missed concert
    Given  there is a concert tonight
    And    Emma wants to attend it
    But    she is unable to do that
    When   the concert begins
    Then   she should receive all the updates of attendees about it
