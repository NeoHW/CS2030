interface MainTask extends Reminder {

    MainTask edit(int startTime, int endTime);

    CanclledTask cancel();
}
