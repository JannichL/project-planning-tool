Description: A user searches for a project by looking through the created projects
Actors: User

Background: The database has stored projects
Given that the user is logged in
And projects with these names are contained in the database
| Installing windows in all buildings | 12/12 |
| New roofing for 358 | 7/10 |
| Renovate old parts of 101 | 10/10 |
| Blinds in all buildings |5/10 |
And the user logs out

Scenario: Viewing available projects
Given that the user is logged in
When the user looks at available projects
Then “New roofing for 358” and “Blinds in all buildings” are shown

Scenario: Viewing available projects whilst not logged in
Given that the user is not logged in
When the user tries to access available projects
Then the error message "User login required" is given

Scenario: No available projects
Given that there are no projects available
And the user is viewing available projects
Then no projects will be shown
