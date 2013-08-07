package vk;

import pagebuild.AbstractComposer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created with IntelliJ IDEA.
 * User: Nina
 * Date: 07.08.13
 * Time: 19:06
 */
public class ReminderServletContextListener extends AbstractComposer implements ServletContextListener {

    private ReminderThread reminderThread = null;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        if ((reminderThread == null) || (!reminderThread.isAlive())) {
            reminderThread = new ReminderThread();
            reminderThread.start();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        try {
            reminderThread.interrupt();
            reminderThread.timer.cancel();
            reminderThread = null;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
