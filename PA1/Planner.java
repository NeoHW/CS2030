class Planner {
    private final ImList<MainTask> tasks;

    Planner() {
        this.tasks = new ImList<MainTask>();
    }

    public Planner add(MainTask task) {

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
