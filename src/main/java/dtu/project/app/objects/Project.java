package dtu.project.app.objects;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Objects;

public class Project {

    private String projectId;
    private String title;

    private ArrayList<User> assignedWorkers;
    private ArrayList<Task> tasks;
    private ObservableList<Task> tasksView = FXCollections.observableArrayList();
    private String projectManager;
    private boolean isProjectManagerAssigned;
    private boolean isCompleted;

    public Project(String title) {
        this.title = title;
        this.assignedWorkers = new ArrayList<>();
        this.tasks = new ArrayList<>();
        this.projectManager = null;
        this.isProjectManagerAssigned = false;
        this.isCompleted = false;
    }

    public String getName() {
        return title;
    }

    public void setProjectId(String ID){
        this.projectId = ID;
    }

    public ArrayList<User> getAssignedWorkers() {
        return assignedWorkers;
    }

    public void addAssignedWorker(User worker) {
        assignedWorkers.add(worker);
    }

    public void removeAssignedWorker(User worker) {
        assignedWorkers.remove(worker);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public String getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(String projectManager) {
        this.projectManager = projectManager;
        this.isProjectManagerAssigned = true;
    }

    public void removeProjectManager(String projectManager) {
        this.projectManager = "";
        this.isProjectManagerAssigned = false;
    }

    public Task getTask(String taskName){
        for(Task task : tasks){
            if(Objects.equals(task.getTitle(), taskName)){
                return task;
            }
        }
        return null;
    }
    public boolean getIsProjectManagerAssigned() {
        return isProjectManagerAssigned;
    }

    public boolean getIsCompleted() {
        return isCompleted;
    }

    public void markAsCompleted() {
        this.isCompleted = true;
    }

    public int getWorkerAmount() {
            return assignedWorkers.size();
    }

    public int getTaskAmount() {
        return tasks.size();
    }

    public int getTasksCompleted() {
        int completed = 0;
        for(Task task : tasks){
            if(task.getIsCompleted()){
                completed++;
            }
        }
        return completed;
    }

    public ObservableList<Task> getAllTasksViewable(){
        tasksView.addAll(tasks);
        return tasksView;
    }
}
