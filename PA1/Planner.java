class Planner {
    private final ImList<Reminder> tasks;

    Planner() {
        this.tasks = new ImList<Reminder>();
    }

    Planner(ImList<Reminder> tasks) {
        this.tasks = tasks;
    }

    public Planner add(Reminder task) {
        return new Planner(tasks.add(task));
    }

    public void view(View view) {
        ImList<Reminder> list = new ImList<Reminder>();
        for(Reminder task : tasks) {
            list = list.addAll(task.getAllActiveTasks());
        }
        view.view(list);
    }

    @Override
    public String toString() {
        String output = "";
        for(int i = 1; i <= tasks.size(); i++) {
            output = output + "\n" + tasks.get(i-1); 
        }
        return output;
    }
}
