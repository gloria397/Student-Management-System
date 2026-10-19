import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student {
    private String id;
    private String studentName;
    private int age;
    private Map<String,Double> courses = new HashMap<>();
    private List<String> skills = new ArrayList<>();
    private boolean BonusApplied;
    private double OriginalAverage;

    public Student(String id, String studentName, int age) {
        this.id = id;
        this.studentName = studentName;
        this.age = age;
        this.courses = new HashMap<>();
        this.skills = new ArrayList<>();    
    }

    public String getId() {
        return id;
    }

    public String getStudentName() {
        return studentName;
    }
    public int getAge() {
        return age;
    }
    public Map<String, Double> getCourses() {
        return courses;
    }
    public List<String> getSkills() {
        return skills;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setStudentName(String studentName){
        this.studentName = studentName;
    }
    public Map<String, Double> setCourses(Map<String, Double> courses) {
        this.courses = courses;
        return courses;
    }
    public void setSkills(List<String> skills) {
        this.skills = skills;
    }
    public double getAverage(){
        return courses.values().stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);
    }
    public boolean isBonusApplied(){
        return BonusApplied;
    }
    public double getOriginalAverage(){
        return OriginalAverage;
    }
    public void setBonusApplied(boolean bonusApplied) {
        BonusApplied = bonusApplied;
    }
    public void setOriginalAverage(double originalAverage) {
        this.OriginalAverage = originalAverage;
    }



    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", studentName='" + studentName + '\'' +
                ", age=" + age +
                ", courses=" + courses +
                ", skills=" + skills +
                ", BonusApplied=" + BonusApplied +
                ", OriginalAverage=" + OriginalAverage +
                '}';
    }
}
