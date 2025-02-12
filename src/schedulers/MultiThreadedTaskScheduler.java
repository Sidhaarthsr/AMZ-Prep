package schedulers;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class MultiThreadedTaskScheduler {
    public static void main(String[] args) {
        final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);

        final long delay = 5;
        final long interval = 10;

        // Running the task with delay
        final Runnable firstTask = () -> {
            System.out.println("Task 1 running...");
        };
        executorService.schedule(firstTask, delay, TimeUnit.SECONDS);

        // Running the task with delay & interval
        final Runnable secondTask = () -> System.out.println("Task 2 running...");

        executorService.scheduleAtFixedRate(secondTask, delay, interval, TimeUnit.SECONDS);

        // Instruct scheduler to wait for Task 2 to finish as well. Works with both
        // shutdown & shutdownNow
        try {
            executorService.awaitTermination(25, TimeUnit.SECONDS);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }

        // shutdown only allows submitted tasks to complete, so giving some time for
        // task 2 to submit
        try {
            Thread.sleep(5500);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }

        // scheduler shuts down after gracefully completing previously submitted tasks
        executorService.shutdown();

        // ShutdownNow shuts down all tasks immediately including even the tasks that
        executorService.shutdownNow();
    }
}
