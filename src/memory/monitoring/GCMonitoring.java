package memory.monitoring;

import java.io.IOException;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * GC Monitoring & Analysis : 
 * Use Tools like JConsole, VisualVM and other
 * profilers to monitor GC activity to identify memory leaks and analyze GC
 * Performance. {

    
} */
public class GCMonitoring {

    private static final String LOG_FILE = "gc_monitoring.log";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) throws IOException {

        // 1. JMX Monitoring (Scheduled Task)
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(GCMonitoring::logGCStats, 0, 5, TimeUnit.SECONDS); // Log every 5 seconds

        // 2. Example Application Logic (Simulate some work)
        List<Object> objects = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            objects.add(new Object());
            if (i % 100000 == 0) {
                try {
                    Thread.sleep(100); // Simulate some work
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Restore interrupt status
                }
            }
        }

        // Keep the main thread alive for a while to allow monitoring.
        try {
            Thread.sleep(60000); // Sleep for 1 minute
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore interrupt status
        }
        scheduler.shutdown();


    }

    private static void logGCStats() {
        try {
            StringBuilder logMessage = new StringBuilder();
            logMessage.append(DATE_FORMAT.format(new Date())).append(" - GC Stats:\n");

            // Memory Usage
            MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
            MemoryUsage heapMemoryUsage = memoryBean.getHeapMemoryUsage();
            MemoryUsage nonHeapMemoryUsage = memoryBean.getNonHeapMemoryUsage();

            logMessage.append("Heap Memory Usage: ").append(formatMemoryUsage(heapMemoryUsage)).append("\n");
            logMessage.append("Non-Heap Memory Usage: ").append(formatMemoryUsage(nonHeapMemoryUsage)).append("\n");

            // GC Beans
            List<GarbageCollectorMXBean> gcBeans = ManagementFactory.getGarbageCollectorMXBeans();
            for (GarbageCollectorMXBean gcBean : gcBeans) {
                logMessage.append("  GC Bean Name: ").append(gcBean.getName()).append("\n");
                logMessage.append("    Collection Count: ").append(gcBean.getCollectionCount()).append("\n");
                logMessage.append("    Collection Time: ").append(gcBean.getCollectionTime()).append(" ms\n");
            }

            // Write to Log File (append)
            Files.write(Paths.get(LOG_FILE), logMessage.toString().getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);

            System.out.println("GC stats logged."); // Optional console output

        } catch (IOException e) {
            System.err.println("Error logging GC stats: " + e.getMessage());
        }
    }

    private static String formatMemoryUsage(MemoryUsage usage) {
        long used = usage.getUsed() / (1024 * 1024); // in MB
        long max = usage.getMax() / (1024 * 1024);   // in MB
        return used + " MB / " + max + " MB";
    }
}
