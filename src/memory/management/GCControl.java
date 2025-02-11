package memory.management;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * This class manipulates the Garbage Collection thread to do the following :
 * GC Tuning : Algorithm Selection, Heap Size & Other Parameters
 * 
 * NOTE :
 * 
 * 1. Calling GC execution is just a suggestion to the JVM which can be ignored which makes it unpredictable
 * 2. Finalization is unreliable and try-with-resources must be used instead for resource management
 * 
 */
 public class GCControl {
 
     public static void main(String[] args) {
 
         // 1. Initial GC Stats
         printGCStats("Initial GC Stats:");
 
         // 2. Suggesting Garbage Collection
         System.out.println("Suggesting GC...");
         System.gc();
         Runtime.getRuntime().gc();
 
         // 3. GC Stats After Suggestion
         printGCStats("GC Stats after suggestion:");
 
 
         // 4. Simulating Memory Pressure
         System.out.println("Creating objects to put pressure on memory...");
         List<Object> objects = new ArrayList<>();
         for (int i = 0; i < 3000000; i++) {
             objects.add(new Object());
             if (i % 1000000 == 0) {
                 System.gc();
                 printGCStats("GC Stats during object creation:"); // Print stats during the loop
             }
         }
 
         // 5. Illustrating Finalization
         class MyResource {
             private String name;
 
             public MyResource(String name) {
                 this.name = name;
             }
 
             @Override
             protected void finalize() throws Throwable {
                 System.out.println("Finalizing resource: " + name);
                 super.finalize();
             }
         }
 
         MyResource resource1 = new MyResource("Resource 1");
         MyResource resource2 = new MyResource("Resource 2");
 
         resource1 = null;
         resource2 = null;
 
         System.out.println("Suggesting GC and Finalization...");
         System.gc();
         System.runFinalization();
 
         // 6. GC Stats After Finalization
         printGCStats("GC Stats after finalization:");
 
 
         System.out.println("Program completed.");
 
     }
 
     private static void printGCStats(String message) {
         System.out.println("------------------------------------");
         System.out.println(message);
         List<GarbageCollectorMXBean> gcBeans = ManagementFactory.getGarbageCollectorMXBeans();
         for (GarbageCollectorMXBean gcBean : gcBeans) {
             System.out.println("GC Bean Name: " + gcBean.getName());
             System.out.println("Collection Count: " + gcBean.getCollectionCount());
             System.out.println("Collection Time: " + gcBean.getCollectionTime());
         }
         System.out.println("------------------------------------");
     }
 }