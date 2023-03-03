class Task implements MainTask {
    private final int day;
    private final int startTime;
    private final int endTime;
    private final String description;

    Task(int day, int startTime, int endTime, String description) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
    }

    @Override
    public MainTask edit(int startTime, int endTime) {
        return new Task(this.day, startTime, endTime, this.description);
    }

    @Override
    public CancelledTask cancel() {
        return new CancelledTask(this);
    }

    public int getDay() {
        return this.day;
    }

    public int getStartTime() {
        return this.startTime;
    }

    public int getEndTime() {
        return this.endTime;
    }
    
    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return String.format("Task: %d,%d,%d,%s", day, startTime, endTime, description);
    }

}
