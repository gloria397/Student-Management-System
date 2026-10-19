
import java.util.*;
import java.util.stream.Collectors;

public class StudentManager {

    private List<Student> students = new ArrayList<>();
    private Scanner scanner;

    public StudentManager() {
        this.scanner = new Scanner(System.in);
    }

    public boolean isIdExists(String id) {
        return students.stream()
                .anyMatch(student -> student.getId().equals(id));
    }

    public void registerStudent() {
        System.out.println("Enter student ID: ");
        String id = scanner.nextLine();

        System.out.println("Enter student name: ");
        String name = scanner.nextLine();

        System.out.println("Enter student age: ");
        int age = Integer.parseInt(scanner.nextLine());

        Student student = new Student(id, name, age);

        Map<String, Double> courses = new HashMap<>();
        System.out.println("Enter number of courses: ");
        int numCourses = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < numCourses; i++) {
            System.out.println("Enter course name: ");
            String courseName = scanner.nextLine();

            System.out.println("Enter course grade: ");
            double grade = Double.parseDouble(scanner.nextLine());

            courses.put(courseName, grade);
        }

        student.setCourses(courses);

        List<String> skills = new ArrayList<>();
        System.out.println("Enter number of skills: ");
        int numSkills = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < numSkills; i++) {
            System.out.println("Enter skill name: ");
            String skillName = scanner.nextLine();
            skills.add(skillName);
        }

        student.setSkills(skills);
        students.add(student);
        System.out.println("Student registered successfully!");

    }

    public void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No students registered.");
        } else {
            students.stream()
                    .forEach(student -> System.out.println(student));
        }
    }

    public void searchStudentById() {
        System.out.println("Enter student ID to search: ");
        String id = scanner.nextLine();
        boolean found = students.stream()
                .anyMatch(student -> student.getId().equals(id));

        if (!found) {
            System.out.println("Student with ID " + id + " not found.");
        }
    }

    public void displaypassingstudents() {
        students.stream()
                .filter(student -> student.getAverage() >= 65)
                .forEach(student -> System.out.println(student + "\n"));

        long passingCount = students.stream()
                .filter(student -> student.getAverage() >= 65)
                .count();

        System.out.println("Number of passing students: " + passingCount);
    }

    public void displayFailedStudents() {

        students.stream()
                .filter(student -> student.getAverage() < 65)
                .forEach(student -> System.out.println(student + "\n"));
    }

    public void searchStudentByName() {

        System.out.print("Enter student name: ");
        String name = scanner.nextLine().trim();

        students.stream()
                .filter(student
                        -> student.getStudentName()
                                .toLowerCase()
                                .contains(name.toLowerCase()))
                .forEach(student -> System.out.println(student + "\n"));
    }

    public void sortStudentsByName() {

        students.stream()
                .sorted(Comparator.comparing(
                        Student::getStudentName,
                        String.CASE_INSENSITIVE_ORDER))
                .forEach(student -> System.out.println(student + "\n"));
    }

    public void sortStudentsByAverage() {
        students.stream()
                .sorted(Comparator.comparingDouble(Student::getAverage)
                        .reversed())
                .forEach(student -> System.out.println(student + "\n"));
    }

    public void displayAllCourses() {

        students.stream()
                .flatMap(student -> student.getCourses()
                        .keySet()
                        .stream())
                .forEach(System.out::println);
    }

    public void displayUniqueCourses() {

        students.stream()
                .flatMap(student -> student.getCourses()
                        .keySet()
                        .stream())
                .distinct()
                .sorted()
                .forEach(System.out::println);
    }

    public void searchStudentsByCourse() {

        System.out.print("Enter course name: ");
        String courseName = scanner.nextLine().trim();

        students.stream()
                .filter(student
                        -> student.getCourses()
                                .keySet()
                                .stream()
                                .anyMatch(course
                                        -> course.equalsIgnoreCase(courseName)))
                .forEach(student -> System.out.println(student + "\n"));
    }

    public void displayUniqueSkills() {

        students.stream()
                .flatMap(student -> student.getSkills().stream())
                .distinct()
                .sorted()
                .forEach(System.out::println);
    }

    public void groupStudentsByCourse() {

        Map<String, List<Student>> courseGroups = students.stream()
                .flatMap(student -> student.getCourses()
                        .keySet()
                        .stream()
                        .map(course -> Map.entry(course, student)))
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.mapping(
                                Map.Entry::getValue,
                                Collectors.toList()
                        )
                ));

        courseGroups.forEach((course, studentList) -> {
            System.out.println("\nCourse: " + course);

            studentList.forEach(student
                    -> System.out.println(student.getStudentName()));
        });
    }

    public void generatePerformanceReport() {

        students.stream().forEach(student -> {

            DoubleSummaryStatistics stats = student.getCourses()
                    .values()
                    .stream()
                    .mapToDouble(Double::doubleValue)
                    .summaryStatistics();

            System.out.println("\nStudent: " + student.getStudentName());
            System.out.println("ID: " + student.getId());

            if (stats.getCount() == 0) {
                System.out.println("No courses registered.");
                return;
            }

            System.out.printf("Average marks: %.2f%n", stats.getAverage());
            System.out.println("Highest mark: " + stats.getMax());
            System.out.println("Lowest mark: " + stats.getMin());
            System.out.println("Total marks: " + stats.getSum());
            System.out.println("Number of courses: " + stats.getCount());
        });
    }

    public void displayTopStudent() {

        students.stream()
                .max(Comparator.comparingDouble(Student::getAverage))
                .ifPresentOrElse(
                        student -> System.out.println(
                                "Top student: " + student.getStudentName()
                                + ", Average: " + student.getAverage()),
                        () -> System.out.println("No students registered.")
                );
    }

    public void displayTopThreeStudents() {

        students.stream()
                .sorted(Comparator.comparingDouble(Student::getAverage)
                        .reversed())
                .limit(3)
                .forEach(student -> System.out.printf(
                        "%s - Average: %.2f%n",
                        student.getStudentName(),
                        student.getAverage()
                ));
    }

    public void applyBonusMarks() {

        students.stream()
                .filter(student -> !student.isBonusApplied())
                .filter(student -> student.getAverage() > 50)
                .forEach(student -> {

                    double originalAverage = student.getAverage();
                    student.setOriginalAverage(originalAverage);

                    Map<String, Double> updatedCourses
                            = student.getCourses()
                                    .entrySet()
                                    .stream()
                                    .collect(Collectors.toMap(
                                            Map.Entry::getKey,
                                            entry -> Math.min(
                                                    entry.getValue() + 10,
                                                    100.0
                                            )
                                    ));

                    student.setCourses(updatedCourses);
                    student.setBonusApplied(true);

                    System.out.println("\nStudent: "
                            + student.getStudentName());

                    System.out.printf("Original average: %.2f%n",
                            originalAverage);

                    System.out.printf("Final average: %.2f%n",
                            student.getAverage());
                });

    }

    //this method allows you to display the saved original average and the current average after bonuses.
    public void displayBonusResults() {

        students.stream()
                .filter(Student::isBonusApplied)
                .forEach(student -> {

                    System.out.println("\nStudent: "
                            + student.getStudentName());

                    System.out.printf("Original average: %.2f%n",
                            student.getOriginalAverage());

                    System.out.printf("Final average: %.2f%n",
                            student.getAverage());
                });
    }

    public void displayFinalRanking() {

        students.stream()
                .sorted(Comparator.comparingDouble(Student::getAverage)
                        .reversed())
                .forEach(student -> System.out.printf(
                        "%s - Final average: %.2f%n",
                        student.getStudentName(),
                        student.getAverage()
                ));
    }

    public double calculateTotalMarks(Student student) {

        return student.getCourses()
                .values()
                .stream()
                .reduce(0.0, Double::sum);

    }
}
