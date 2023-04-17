Description: A worker wants to become a project manager when no project manager is currently assigned to a project
Actor: User

Scenario: Worker becomes project manager when no project manager is selected
Given no project manager is currently assigned to a project
And the User is logged in
And the User is currently viewing the project
And the User clicks on the "Become Project Manager" button
Then the system will assign the User as the project manager
