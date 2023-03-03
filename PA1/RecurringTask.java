class RecurringTask implements MainTask {
    private final Task originalTask;
    private final int frequency;
    private final int repeatNum;
    private final ImList<MainTask> tasks;

    RecurringTask(Task task, int frequency, int repeatNum) {
        this.originalTask = task;
        this.frequency = frequency;
        this.repeatNum = repeatNum;
        this.tasks = getRepeats(task, frequency, repeatNum);
    }

    // static method to get ImList of tasks
    static ImList<MainTask> getRepeats(Task task, int frequency, int repeatNum) {
        ImList<MainTask> tasks = new ImList<MainTask>().add(task);
        for (int i = 1; i < repeatNum; i++) {
            Task toAdd = new Task(task.getDay() + (i*frequency), task.getStartTime(), task.getEndTime(), task.getDescription());
            tasks = tasks.add(toAdd);
        }
        return tasks;
    }

    @Override
    public MainTask edit(int startTime, int endTime) {
        Task newTask = new Task(originalTask.getDay(), startTime, endTime, originalTask.getDescription());
        return new RecurringTask(newTask, frequency, repeatNum);
    }

    @Override
    public CancelledTask cancel() {
        return new CancelledTask(this.originalTask);
    }

    // level 3 : to edit
    public MainTask edit(int index, int day, int startTime, int endTime) {
        Task newTask = new Task(originalTask.getDay(), startTime, endTime, originalTask.getDescription());
        return new RecurringTask(newTask, frequency, repeatNum);
    }

    public CancelledTask cancel(int index) {
        return new CancelledTask(this.originalTask);
    }

    @Override
    public String toString() {
        String output = "";
        for(int i = 1; i <= tasks.size(); i++) {
            output = output + "#" + i + ":" + tasks.get(i); 
        }
        return originalTask + output;
    }

}
