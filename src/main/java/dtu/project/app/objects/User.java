package dtu.project.app.objects;

import java.util.ArrayList;
import java.util.List;

public class User {

    private int MAX_AMOUNT_OF_ASSIGNED_TASKS = 20;
    private String initials;
    private Boolean isAvailable;

    private List<String[]> loggedHours = new ArrayList<String[]>();

    public void addLogHours(String ProjectID, String Task, String Hours) {
        String[] logHours = {ProjectID, Task, Hours};
        loggedHours.add(logHours);
    }

    private List<Integer> sickDays = new ArrayList<>();

    private List<Integer> vacationDays = new ArrayList<>();
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

    public void addSickDays(int days) {
        sickDays.add(days);
    }

    public List<Integer> getSickDays() {
        return sickDays;
    }

    public void addVacationDays(int days) {
        vacationDays.add(days);
    }

    public List<Integer> getVacationDays() {
        return vacationDays;
    }



    public Boolean getIsAvailable(){
        return isAvailable;
    }
}