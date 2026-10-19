
public class Main {

    public static void main(String[] args) {

        StudentManager manager = new StudentManager();

        int choice;

        do {
            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");

            System.out.println("\n--- Task 1: Student Registration ---");
            System.out.println("1. Register student");
            System.out.println("2. Display all students");
            System.out.println("3. Search student by ID");

            System.out.println("\n--- Task 2: Search, Filter and Sort ---");
            System.out.println("4. Display students with average >= 65");
            System.out.println("5. Display students with average < 65");
            System.out.println("6. Search students by name");
            System.out.println("7. Sort students by name");

            System.out.println("\n--- Task 3: Courses and Skills ---");
            System.out.println("8. Display all courses");
            System.out.println("9. Display unique courses");
            System.out.println("10. Search students by course");
            System.out.println("11. Display unique skills");
            System.out.println("12. Group students by course");

            System.out.println("\n--- Task 4: Performance and Statistics ---");
            System.out.println("13. Generate performance report");
            System.out.println("14. Display top student");
            System.out.println("15. Display top three students");

            System.out.println("\n--- Task 5: Bonus and Final Results ---");
            System.out.println("16. Apply bonus marks");
            System.out.println("17. Display bonus results");
            System.out.println("18. Display final ranking");
            System.out.println("19. Calculate total marks for a student");

            System.out.println("0. Exit");

            System.out.print("\nEnter your choice: ");
            choice = Integer.parseInt(
                    new java.util.Scanner(System.in).nextLine()
            );

            switch (choice) {

                case 1:
                    manager.registerStudent();
                    break;

                case 2:
                    manager.displayStudents();
                    break;

                case 3:
                    manager.searchStudentById();
                    break;

                case 4:
                    manager.displaypassingstudents();
                    break;

                case 5:
                    manager.displayFailedStudents();
                    break;

                case 6:
                    manager.searchStudentByName();
                    break;

                case 7:
                    manager.sortStudentsByName();
                    break;

                case 8:
                    manager.displayAllCourses();
                    break;

                case 9:
                    manager.displayUniqueCourses();
                    break;

                case 10:
                    manager.searchStudentsByCourse();
                    break;

                case 11:
                    manager.displayUniqueSkills();
                    break;

                case 12:
                    manager.groupStudentsByCourse();
                    break;

                case 13:
                    manager.generatePerformanceReport();
                    break;

                case 14:
                    manager.displayTopStudent();
                    break;

                case 15:
                    manager.displayTopThreeStudents();
                    break;

                case 16:
                    manager.applyBonusMarks();
                    break;

                case 17:
                    manager.displayBonusResults();
                    break;

                case 18:
                    manager.displayFinalRanking();
                    break;

                case 19:
                    System.out.println(
                            "To calculate total marks, call "
                            + "calculateTotalMarks(student) "
                            + "from your StudentManager methods."
                    );
                    break;

                case 0:
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
                }

        } while (choice != 0);

    }
    

}```
