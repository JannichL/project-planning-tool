package dtu.project.app.objects;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Objects;

public class Project {

    private String projectId;
    private String title;
    private ArrayList<Task> tasks;
    private String projectManager;
    private boolean isProjectManagerAssigned;
    private boolean isCompleted;

    public Project(String title) {
        this.title = title;
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

    //PLEASE REDO THIS FUNCTION!
    public int getWorkerAmount() {
        int workerAmount = 0;
         for(Task task : tasks) {
             workerAmount += task.getWorkerAmount();
         }
         return  workerAmount;
    }

    public int getTaskAmount() {
        return tasks.size();
    }
    public String getProjectId(){return projectId;}

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
        ObservableList<Task> tasksView = FXCollections.observableArrayList();
        tasksView.addAll(tasks);
        return tasksView;
    }

    public ObservableList<Task> getMyTasksViewable(User currentUser){
        ObservableList<Task> taskView = FXCollections.observableArrayList();
        boolean taskProcessed;
            for(Task task : tasks){
                taskProcessed = false;
                if(Objects.equals(currentUser.getInitials(), projectManager)){
                    taskView.add(task);
                    taskProcessed = true;
                }
                nextTask:
                for(User user : task.getAssignedWorkers()){
                    if(Objects.equals(user.getInitials(), currentUser.getInitials()) && !taskProcessed){
                        taskView.add(task);
                        break nextTask;
                    }
                }
            }
        return taskView;
    }
}
