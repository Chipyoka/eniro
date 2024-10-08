import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainSystem {
    private static DatabaseManager dbManager;
    private static SoftSkillAssessment softSkillAssess = new SoftSkillAssessment();
    private static CareerGuidance careerGuidance = new CareerGuidance();

    public static void main(String[] args) {
        // Initialize the database manager with the database URL
        dbManager = new DatabaseManager("school_system.db");
        dbManager.connect();

        Scanner scanner = new Scanner(System.in);
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
                case 1:
                    enrollPupil(scanner);
                    break;
                case 2:
                    adminLogin(scanner);
                    break;
                case 3:
                    running = false;
                    System.out.println("Exiting the system...");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }

        // Disconnect from the database before exiting
        dbManager.disconnect();
        scanner.close();
    }



    private static void enrollPupil(Scanner scanner) {
        System.out.println("\n--- Enroll New Pupil ---");
    
        // Collect basic pupil details
        System.out.print("Enter Pupil ID: ");
        String pupilID = scanner.nextLine();
    
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();
    
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();
    
        System.out.print("Enter Age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline
    
        System.out.print("Enter Gender (Male/Female): ");
        String gender = scanner.nextLine();
    
        // Step 1: Conduct Soft Skill Assessment with Questions
        System.out.println("\n--- Soft Skill Assessment ---");
    
        // Ask communication-related questions
        int communicationScore = askQuestion(scanner, "Do you enjoy speaking in front of groups or leading discussions?");
        
        // Ask teamwork-related questions
        int teamworkScore = askQuestion(scanner, "Do you prefer working with others in a team rather than alone?");
        
        // Ask problem-solving-related questions
        int problemSolvingScore = askQuestion(scanner, "Do you enjoy solving puzzles or figuring out complex problems?");
        
        // Ask creativity-related questions
        int creativityScore = askQuestion(scanner, "Do you often come up with creative ideas or new ways of doing things?");
        
        // Step 2: Use the SoftSkillAssessment class to determine stream based on the derived scores
        SoftSkillAssessment softSkillAssess = new SoftSkillAssessment();  // Initialize assessment object
        String stream = softSkillAssess.assessSkills(communicationScore, teamworkScore, problemSolvingScore, creativityScore);  // Assess and determine stream
    
        System.out.println("\nBased on the assessment, the recommended stream for the pupil is: " + stream);
    
        // Step 3: Enroll pupil into the system with the determined stream
        Pupil pupil = new Pupil(pupilID, firstName, lastName, age, gender, 8, stream);  // Assuming default grade level is 8
    
        System.out.println("\nEnrolling pupil into the " + stream + " stream.");
    
        String[] columns = {"pupilID", "firstName", "lastName", "age", "gender", "gradeLevel", "stream"};
        String[] values = {pupilID, firstName, lastName, String.valueOf(age), gender, "8", stream};  // Assuming grade level is 8
        dbManager.insert("Pupil", columns, values);  // Insert the pupil's record into the database
    
        System.out.println("Pupil enrolled successfully!");
    }
    
    // Method to ask a yes/no question and return a score based on the response
    private static int askQuestion(Scanner scanner, String question) {
        System.out.println(question + " (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();
    
        // If "yes", give 10 points; if "no", give 5 points
        if (response.equals("yes")) {
            return 10;  // High score for positive answer
        } else if (response.equals("no")) {
            return 5;  // Lower score for negative answer
        } else {
            System.out.println("Invalid response. Defaulting to 5 points.");
            return 5;  // Default to 5 for invalid input
        }
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
            e.printStackTrace();
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
                case 1:
                    managePupilRecords(scanner);
                    break;
                case 2:
                    manageEnrollments(scanner);
                    break;
                case 3:
                    manageClasses(scanner);
                    break;
                case 4:
                    adminRunning = false;
                    System.out.println("Logging out of admin mode...");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
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
