package vk;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created with IntelliJ IDEA.
 * User: Nina
 * Date: 07.08.13
 * Time: 19:01
 */
public class ReminderThread extends Thread {
    Timer timer = new Timer();
    UserReminder userReminder = new UserReminder();
    TimerTask timerTask;
    Long period = Long.valueOf(1000 * 60 * 60 * 2);
//    Long period = Long.valueOf(1000 * 60);

    public void run() {
        timerTask = new TimerTask() {
            @Override
            public void run() {
                userReminder.remindBunchOfUsers();
                System.out.println("Still there!");
            }
        };

        timer.scheduleAtFixedRate(timerTask, 0, period);
    }
}
