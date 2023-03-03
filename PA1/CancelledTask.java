class CancelledTask implements Reminder {
    private final Task task;

    CancelledTask(Task task) {
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

    @Override
    public String toString() {
        return task + "[cancelled]";
    }
}
