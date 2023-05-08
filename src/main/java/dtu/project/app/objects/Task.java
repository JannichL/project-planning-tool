package dtu.project.app.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Task {


    private String id; // Unique identifier for the task
    private String title;
    private int budgetedHours;
    private int startWeek;
    private int endWeek;
    private List<User> assignedWorkers;
    private boolean isCompleted;
    private Project project; // Project that the task belongs to

    public Task(String title, int budgetedHours, int startWeek, int endWeek){

        this.id = id;
        this.title = title;
        this.budgetedHours = budgetedHours;
        this.startWeek = startWeek;
        this.endWeek = endWeek;

        // Initialize the list of assigned workers to an empty list
        this.assignedWorkers = new ArrayList<>();
        // Initialize the flag indicating that the task is not completed
        this.isCompleted = false;
        // Store the project that the task belongs to
        this.project = project;
    }
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getBudgetedHours() {
        return budgetedHours;
    }

    public int getStartWeek() {
        return startWeek;
    }

    public int getEndWeek() {
        return endWeek;
    }

    public List<User> getAssignedWorkers() {
        return assignedWorkers;
    }
    public boolean isWorkerAssigned(String name){
        for(User user : assignedWorkers){
            if(Objects.equals(name, user.getInitials())){
                return true;
            }
        }
        return false;
    }

    public int getWorkerAmount(){ return assignedWorkers.size();}

    public boolean getIsCompleted() {
        return isCompleted;
    }

    public void assignWorker(User worker) {
        assignedWorkers.add(worker);
    }

    public void complete() {
        isCompleted = true;
    }

    public void incomplete() {
        isCompleted = false;
    }

    public Project getProject() {
        return project;
    }

}
