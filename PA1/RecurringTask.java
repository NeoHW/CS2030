class RecurringTask implements MainTask {
    private final Task originalTask;
    private final int frequency;
    private final int repeatNum;
    private final ImList<Reminder> tasks;

    RecurringTask(Task task, int frequency, int repeatNum) {
        this.originalTask = task;
        this.frequency = frequency;
        this.repeatNum = repeatNum;
        this.tasks = getRepeats(task, frequency, repeatNum);
    }

    // static method to get ImList of tasks
    static ImList<Reminder> getRepeats(Task task, int frequency, int repeatNum) {
        ImList<Reminder> tasks = new ImList<Reminder>().add(task);
        for (int i = 1; i < repeatNum; i++) {
            Task toAdd = new Task(task.getDay() + (i*frequency), task.getStartTime(), task.getEndTime(), task.getDescription());
            tasks = tasks.add(toAdd);
        }
        return tasks;
    }

    public int getDay() {
        return originalTask.getDay();
    }

    public int getStartTime() {
        return originalTask.getStartTime();
    }

    public int getEndTime() {
        return originalTask.getEndTime();
    }
    
    public String getDescription() {
        return originalTask.getDescription();
    }

    public MainTask edit(int startTime, int endTime) {
        Task newTask = new Task(originalTask.getDay(), startTime, endTime, originalTask.getDescription());
        return new RecurringTask(newTask, frequency, repeatNum);
    }

    @Override
    public Reminder cancel() {
        return new CancelledTask(this);
    }

    // level 3 : to edit
    public MainTask edit(int index, int day, int startTime, int endTime) {
        Task newTask = new Task(originalTask.getDay(), startTime, endTime, originalTask.getDescription());
        return new RecurringTask(newTask, frequency, repeatNum);
    }

    public Reminder cancel(int index) {
        return new CancelledTask(this.originalTask);
    }

    @Override
    public String toString() {
        String output = "Recurring " + originalTask;
        for(int i = 1; i <= tasks.size(); i++) {
            output = output + "\n#" + i + ":" + tasks.get(i-1); 
        }
        return output;
    }

}
