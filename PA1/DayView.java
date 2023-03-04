class DayView implements View {
    private final int day;

    DayView(int day) {
        this.day = day;
    }

    public void view(ImList<Reminder> tasks) {
        tasks = tasks.sort(new TaskComp());
        for (Reminder task : tasks) {
            if (task.getDay() == day) {
                System.out.println(task);
            }
        }
    }

}
