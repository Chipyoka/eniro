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
        int age = scanner.nextInt(); // Changed to int
    
        System.out.print("Enter Gender (Male/Female): ");
        String gender = scanner.next(); // Changed to next() to avoid consuming the newline character
    
        // Step 1: Conduct Soft Skill Assessment
        System.out.println("\n--- Soft Skill Assessment ---");
        System.out.print("Enter Communication Skills score (1-10): ");
        int communication = scanner.nextInt();
    
        System.out.print("Enter Teamwork Skills score (1-10): ");
        int teamwork = scanner.nextInt();
    
        System.out.print("Enter Problem Solving Skills score (1-10): ");
        int problemSolving = scanner.nextInt();
    
        System.out.print("Enter Creativity Skills score (1-10): ");
        int creativity = scanner.nextInt();
    
        // Assess soft skills and get the stream recommendation
        String stream = softSkillAssess.assessSkills(communication, teamwork, problemSolving, creativity);
        System.out.println("Based on the soft skill assessment, the recommended stream is: " + stream);
    
        // Step 2: Provide Career Guidance based on soft skill assessment
        System.out.println("\n--- Career Guidance ---");
    
        // Create a map to hold pupil responses
        Map<String, String> responses = new HashMap<>();
        responses.put("Do you enjoy working with numbers or solving complex problems?", "Yes, I love numbers."); // Example response
        responses.put("Do you have a strong interest in science and technology?", "Yes, I enjoy science."); // Add other responses as needed
        responses.put("Are you more comfortable with creative tasks like drawing or writing?", "I prefer drawing."); // Example response
        responses.put("Do you enjoy helping people or working in a team?", "Yes, I love teamwork."); // Example response
        responses.put("Do you prefer working with your hands, such as building or fixing things?", "No, I prefer mental challenges."); // Example response
    
        // Create a Pupil object with the provided details
        Pupil pupil = new Pupil(pupilID, firstName, lastName, age, gender, 8, stream);  // Assuming Grade 8 is represented as 8
        careerGuidance.conductGuidanceSession(pupil, responses);
    
        // Step 3: Enroll pupil into the recommended stream
        System.out.println("\nPupil will be enrolled into the " + stream + " stream.");
    
        // Insert pupil record into the database, including stream
        String[] columns = {"pupilID", "firstName", "lastName", "age", "gender", "gradeLevel", "stream"};
        String[] values = {pupilID, firstName, lastName, String.valueOf(age), gender, "8", stream};  // Assuming enrolling into Grade 8 for simplicity
        // Handle potential database errors
        dbManager.insert("Pupil", columns, values);
        System.out.println("Pupil enrolled successfully!");
    }


    // Method for admin login and showing admin management menu
    private static void adminLogin(Scanner scanner) {
        System.out.println("\n--- Admin Login ---");

        System.out.print("Enter Username: ");
        String username = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        if (authenticateAdmin(username, password)) {
            System.out.println("Login successful!");
            adminMenu(scanner);
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    // Method to authenticate admin credentials from the database
    private static boolean authenticateAdmin(String username, String password) {
        String query = "SELECT * FROM Admin WHERE username = '" + username + "' AND password = '" + password + "'";
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
