Feature: View Available Worker
Description: The worker is logged in to the project planning tool and wants to Views available workers
Actor: User

Scenario: Worker trying to view the available workers
Given that the worker is logged in
When the worker navigates to the "View Available Workers"
Then the system displays a list of available workers
And the list includes the workers' names, job titles, and availability status
And the list is sorted by the worker's availability status (available first)
And the worker can filter the list by job title or availability status
And the worker can select a worker from the list to assign tasks or projects to them.
And the worker can return to the main dashboard of the project planning tool by clicking on the "Home" button.
