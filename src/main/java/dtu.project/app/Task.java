package dtu.project.app;

import java.util.List;

public class Task {

    private String name;
    private int budgettedHours;
    private int startWeek;
    private int endWeek;

    private List<User> assignedWorkers;

    public Task(String name, int budgettedHours, int startWeek, int endWeek){
        this.name = name;
        this.budgettedHours = budgettedHours;
        this.startWeek = startWeek;
        this.endWeek = endWeek;
    }
}
