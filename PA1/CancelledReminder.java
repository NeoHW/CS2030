class CancelledReminder implements MainTask {
    private final Reminder task;

    CancelledReminder(Reminder task) {
        this.task = task;
    }

    public int getDay() {
        return this.task.getDay();
    }

    public int getStartTime() {
        return this.task.getStartTime();
    }

    public int getEndTime() {
        return this.task.getEndTime();
    }
    
    public String getDescription() {
        return this.task.getDescription();
    }

    public MainTask edit(int startTime, int endTime) {
        return new Task(task.getDay(), startTime, endTime, task.getDescription());
    }

    public Reminder cancel() {
        return this;
    }

    public boolean isCancelled() {
        return true;
    }

    @Override
    public String toString() {
        return task + "[cancelled]";
    }
}
