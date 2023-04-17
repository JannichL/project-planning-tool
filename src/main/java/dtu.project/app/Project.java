package dtu.project.app;
import java.util.ArrayList;
public class Project {

    private String title;
    private int budgettedHours;
    private int startWeek;
    private int endWeek;

    private ArrayList<User> assignedWorkers;
    private ArrayList<Task> tasks;
    private String projectManager;
    private boolean isProjectManagerAssigned;
    private boolean isCompleted;

    public Project(String title, int budgettedHours, int startWeek, int endWeek) {
        this.title = title;
        this.budgettedHours = budgettedHours;
        this.startWeek = startWeek;
        this.endWeek = endWeek;
        this.assignedWorkers = new ArrayList<>();
        this.tasks = new ArrayList<>();
        this.projectManager = null;
        this.isProjectManagerAssigned = false;
        this.isCompleted = false;
    }

    public String getName() {
        return title;
    }

    public int getBudgettedHours() {
        return budgettedHours;
    }

    public int getStartWeek() {
        return startWeek;
    }

    public int getEndWeek() {
        return endWeek;
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
}
