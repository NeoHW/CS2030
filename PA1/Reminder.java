interface Reminder {

    public int getDay();

    public int getStartTime();

    public int getEndTime();
    
    public String getDescription();

    public boolean isCancelled();

    public ImList<Reminder> getAllActiveTasks();

}
