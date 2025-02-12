package memory.monitoring;

import java.util.List;

import java.util.ArrayList;

/**
 * 
 */
public class GCVisualMonitoring {

    public static void main(String[] args) {
        System.out.println("Start");
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            Student student = new Student(Long.valueOf(i+""), "Student"+i, new ArrayList<>());
            students.add(student);
        }
        //Make the objects eligible for GC and then try manual GC from JVisualVM
        students = null;
        System.out.println("End");
    }
    
}

class Student {
    private Long id;
    private String name;
    private List<String> resources;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<String> getResources() {
        return resources;
    }
    public void setResources(List<String> resources) {
        this.resources = resources;
    }

    public Student(Long id, String name, List<String> resources) {
        this.id = id;
        this.name = name;
        this.resources = resources;
    }
    
}
