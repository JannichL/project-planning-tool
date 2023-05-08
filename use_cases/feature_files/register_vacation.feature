Feature: Registering vacation time
  Description: A User registers vacation time
  Actors: User

  Scenario: user registers vacation time
    Given the user selects the vacation time option
    When the user enters the vacation time details
    Then vacation is registered in the database
