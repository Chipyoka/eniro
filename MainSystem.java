import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class MainSystem {
    private static DatabaseManager dbManager;
   

    public static void main(String[] args) {
        // Initialize the database manager with the database URL
        dbManager = new DatabaseManager("school_system.db");
        dbManager.connect();

        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;
            
            while (running) {
                // Display the main menu
                System.out.println("\n--- School System ---");
                System.out.println("1. Enroll Pupil");
                System.out.println("2. Admin");
                System.out.println("3. Exit");
                System.out.print("Select an option: ");
                
                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline
                
                switch (choice) {
                    case 1 -> enrollPupil(scanner);
                    case 2 -> adminLogin(scanner);
                    case 3 -> {
                        running = false;
                        System.out.println("Exiting the system...");
                    }
                    default -> System.out.println("Invalid choice. Please select a valid option.");
                }
            }
            
            // Disconnect from the database before exiting
            dbManager.disconnect();
        }
    }



    private static void enrollPupil(Scanner scanner) {
        System.out.println("\n--- Enroll New Pupil ---");
    
        // Collect basic pupil details
        System.out.print("Enter Pupil ID: ");
        String gradeLevel = scanner.nextLine();
    
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();
    
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();
    
        System.out.print("Enter Age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline
    
        System.out.print("Enter Gender (Male/Female): ");
        String gender = scanner.nextLine();
        
        SoftSkillAssessment assessment = new SoftSkillAssessment();

        assessment.questionnaire(scanner);

        String stream = assessment.getStream();
     
        Pupil pupil = new Pupil(firstName, lastName, age, gender, gradeLevel);
        pupil.addPupil(pupil, stream);

        String enrollDate = getCurrentDate();   
    
        // handle class ID and enrollment
        Enrollment enrollment = new Enrollment();
        enrollment.setEnrollmentDate(enrollDate);

        Class myClass = new Class();

        String classID = myClass.matchClass(stream, gradeLevel);

        enrollment.enrollPupil(classID);
        
    

    }

    private static String getCurrentDate() {
        java.time.LocalDate currentDate = java.time.LocalDate.now();
        return String.format("%02d-%02d-%04d", currentDate.getDayOfMonth(), currentDate.getMonthValue(), currentDate.getYear());
    }
    



    // Method for admin login and showing admin management menu
    private static void adminLogin(Scanner scanner) {
        System.out.println("\n--- Admin Login ---");

        System.out.print("Enter adminName: ");
        String adminName = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        if (authenticateAdmin(adminName, password)) {
            System.out.println("Login successful!");
            adminMenu(scanner);
        } else {
            System.out.println("Invalid adminName or password.");
        }
    }

    // Method to authenticate admin credentials from the database
    private static boolean authenticateAdmin(String adminName, String password) {
        String query = "SELECT * FROM Admin WHERE adminName = '" + adminName + "' AND adminPassword = '" + password + "'";
        ResultSet rs = dbManager.fetch(query);
        try {
            return rs.next();  // If a record is found, authentication is successful
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    // Method to display the admin management menu
    private static void adminMenu(Scanner scanner) {
        boolean adminRunning = true;

        while (adminRunning) {
            System.out.println("\n--- Admin Management ---");
            System.out.println("1. Manage Pupil Records");
            System.out.println("2. Manage Enrollments");
            System.out.println("3. Manage Classes");
            System.out.println("4. Logout");

            System.out.print("Select an option: ");
            int adminChoice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (adminChoice) {
                case 1 -> managePupilRecords(scanner);
                case 2 -> manageEnrollments(scanner);
                case 3 -> manageClasses(scanner);
                case 4 -> {
                    adminRunning = false;
                    System.out.println("Logging out of admin mode...");
                }
                default -> System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    // Placeholder methods for managing pupil records, enrollments, and classes
    private static void managePupilRecords(Scanner scanner) {
       System.out.print("We testing" + scanner);
    }

    private static void manageEnrollments(Scanner scanner) {
        // Implementation for managing enrollments
        System.out.print("We testing" + scanner);
    }

    private static void manageClasses(Scanner scanner) {
        // Implementation for managing classes
        System.out.print("We testing" + scanner);
    }
}
