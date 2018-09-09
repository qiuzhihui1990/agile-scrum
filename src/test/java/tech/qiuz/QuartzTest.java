package tech.qiuz;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import tech.qiuz.job.MyJob;

public class QuartzTest {
  public static void main(String[] args) throws SchedulerException {
    Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

    // define the job and tie it to our MyJob class
    JobDetail job = newJob(MyJob.class)
        .withIdentity("job1", "group1")
        .build();

    // Trigger the job to run now, and then repeat every 40 seconds
    Trigger trigger = newTrigger()
        .withIdentity("trigger1", "group1")
        .startNow()
        .withSchedule(simpleSchedule()
            .withIntervalInSeconds(40)
            .repeatForever())
        .build();

    // Tell quartz to schedule the job using our trigger
    scheduler.scheduleJob(job, trigger);
    scheduler.start();

  }
}
