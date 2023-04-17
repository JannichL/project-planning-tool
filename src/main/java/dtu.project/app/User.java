package dtu.project.app;

import java.util.ArrayList;
import java.util.List;

public class User {

    private int MAX_AMOUNT_OF_ASSIGNED_TASKS = 20;
    private String initials;
    private Boolean isAvailable;
    private List<Task> assignedTasks;
    public User(String initials) {
        this.initials = initials;
        this.assignedTasks = new ArrayList<Task>();
        this.isAvailable = true;
    }

    public String getInitials() {
        return initials;
    }

    public void assignToTask(Task task){
        assignedTasks.add(task);

        if(assignedTasks.size() >= MAX_AMOUNT_OF_ASSIGNED_TASKS){
            this.isAvailable = false;
        }
    }

    public void removeFromTask(Task task){
        assignedTasks.remove(task);

        if(assignedTasks.size() < MAX_AMOUNT_OF_ASSIGNED_TASKS){
            this.isAvailable = true;
        }
    }

    public Boolean getIsAvailable(){
        return isAvailable;
    }
}