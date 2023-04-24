Feature: Become Project Manager
Description: A worker wants to become a project manager when no project manager is currently assigned to a project
Actor: User

Scenario: Worker becomes project manager when no project manager is selected

  Given A project is called Project1
  And A User is called AB
  And no project manager is currently assigned to a project
  Then the system will assign the User as the project manager
