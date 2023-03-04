class CancelledTask implements Reminder {
    private final Reminder task;

    CancelledTask(Reminder task) {
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

    public boolean isCancelled() {
        return true;
    }

    public ImList<Reminder> getAllActiveTasks() {
        return new ImList<Reminder>();
    }

    @Override
    public String toString() {
        String[] arr = task.toString().split("\n");
        return arr[0] + "[cancelled]";
    }
}
