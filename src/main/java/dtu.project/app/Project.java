package dtu.project.app;

public class Project {

    private String name;
    private int budgettedHours;
    private int startWeek;
    private int endWeek;

    public Project(String name, int budgettedHours, int startWeek, int endWeek) {
        this.name = name;
        this.budgettedHours = budgettedHours;
        this.startWeek = startWeek;
        this.endWeek = endWeek;
    }

    public String getName() {
        return name;
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
}
