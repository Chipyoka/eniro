import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class MainSystem {
    private static DatabaseManager dbManager;
   
    public static void clearScreen() {
        try {
            // Execute the 'cls' command for Windows
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (IOException | InterruptedException e) {
            System.out.println("Error occurred while clearing the screen.");
        }
    }

     // Public method to pause execution until the user presses Enter
     @SuppressWarnings("resource")
    public static void pauseScreen() {
        System.out.println("\n   Press Enter to continue...");
        new Scanner(System.in).nextLine();  // Waits for user input
    }

    public static void eniro(){
        System.out.println("\n+============================================================+");
        System.out.println("|                     ENIRO SYSTEM MENU                      |");
        System.out.println("+============================================================+");
    }
    



    public static void main(String[] args) {


        // Initialize the database manager with the database URL
        dbManager = new DatabaseManager("school_system.db");
        dbManager.connect();

        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;
            
            while (running) {
                // Display the main menu
                eniro();
                System.out.println("\n   1. Enroll Pupil");
                System.out.println("   2. Admin");
                System.out.println("   3. Exit");
                System.out.print("\nSelect an option: ");
                
                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline
                
                clearScreen();

                switch (choice) {
                    case 1 -> enrollPupil(scanner);
                    case 2 -> adminLogin(scanner);
                    case 3 -> {
                        running = false;
                        System.out.println("   Exiting the system...");
                        pauseScreen();
                        clearScreen();
                    }
                    default -> System.out.println("   Invalid choice. Please select a valid option.");
                    
                }
            }
            
            // Disconnect from the database before exiting
            dbManager.disconnect();
        }
    }



    private static void enrollPupil(Scanner scanner) {
        eniro();
        System.out.println("\n\n---------------------------------------------------------------");
        System.out.println("*******************      ENROLL PUPIL      ********************");
        System.out.println("---------------------------------------------------------------");
    
                // Collect basic pupil details with validation
                System.out.print("   Enter Pupil Grade (8 or 10): ");
                String gradeLevel = scanner.nextLine();
                if (!gradeLevel.equals("8") && !gradeLevel.equals("10")) {
                    System.out.println("   Invalid grade. Grade must be either 8 or 10.");
                    pauseScreen();
                    clearScreen();
                    return;  // Exit the program or loop to ask again
                }
        
                System.out.print("   Enter First Name: ");
                String firstName = scanner.nextLine();
        
                System.out.print("   Enter Last Name: ");
                String lastName = scanner.nextLine();
        
                System.out.print("   Enter Age: ");
                int age = scanner.nextInt();
                if (age < 10 || age > 25) {
                    System.out.println("   Invalid age. Age must be between 10 and 25.");
                    pauseScreen();
                    clearScreen();
                    return;  // Exit the program or loop to ask again
                }
                scanner.nextLine(); // Consume newline
        
                System.out.print("   Enter Gender (Male/Female): ");
                String gender = scanner.nextLine();
                if (!gender.equalsIgnoreCase("Male") && !gender.equalsIgnoreCase("Female")) {
                    System.out.println("   Invalid gender. Gender must be either Male or Female.");
                    pauseScreen();
                    clearScreen();
                    return;  // Exit the program or loop to ask again
                }
        
                // If all validations pass
                System.out.println("\n---------------------------------------------------------------");
                System.out.println("   Pupil details collected successfully!\n");
                System.out.println("   Name: " + firstName + " " + lastName);
                System.out.println("   Grade: " + gradeLevel);
                System.out.println("   Age: " + age);
                System.out.println("   Gender: " + gender);
        
        pauseScreen();
        clearScreen();
        // assess skills
        SoftSkillAssessment assessment = new SoftSkillAssessment();
        eniro();
        assessment.questionnaire(scanner);
        String stream = assessment.getStream();
     
        // add as new pupil
        Pupil pupil = new Pupil(firstName, lastName, age, gender, gradeLevel);
        pupil.addPupil(pupil, stream);

        // get class and enroll pupil
        String enrollDate = getCurrentDate();   
        Enrollment enrollment = new Enrollment(dbManager);
        enrollment.setEnrollmentDate(enrollDate);

        Class myClass = new Class();
        String classID = myClass.matchClass(stream, gradeLevel);
        enrollment.enrollPupil(classID);

        pauseScreen();
        clearScreen();
    

    }

    // get current date as enrollment date
    private static String getCurrentDate() {
        java.time.LocalDate currentDate = java.time.LocalDate.now();
        return String.format("%02d-%02d-%04d", currentDate.getDayOfMonth(), currentDate.getMonthValue(), currentDate.getYear());
    }
    



    // Method for admin login and showing admin management menu
    private static void adminLogin(Scanner scanner) {
        eniro();
        System.out.println("\n\n---------------------------------------------------------------");
        System.out.println("*******************      ADMIN SERVICE      *******************");
        System.out.println("---------------------------------------------------------------");
        System.out.println("+--- Admin Login ");
        System.out.print("Username: ");
        String adminName = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        if (authenticateAdmin(adminName, password)) {
            System.out.println("Login successful!");
            pauseScreen();
            clearScreen();
            adminMenu(scanner);
        } else {
            System.out.println("Invalid username or password.");
        }
        pauseScreen();
        clearScreen();
    }

    // Method to authenticate admin credentials from the database
    private static boolean authenticateAdmin(String adminName, String password) {
        // Hash the input password
        Admin admin = new Admin(dbManager); // Create an instance of Admin to use the hashPassword method
        String hashedPassword = admin.hashPassword(password); 

        // Query to check if the username and hashed password match a record in the database
        String query = "SELECT * FROM Admin WHERE adminName = '" + adminName + "' AND adminPassword = '" + hashedPassword + "'";
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
            eniro();
            System.out.println("\n\n---------------------------------------------------------------");
            System.out.println("****************      ADMIN SERVICE MENU      *****************");
            System.out.println("---------------------------------------------------------------");
            System.out.println("   1. View Enrollment");
            System.out.println("   2. New Admin");
            System.out.println("   3. Logout");

            System.out.print("Select an option: ");
            int adminChoice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            clearScreen();
            switch (adminChoice) {
                case 1 -> viewEnrollments();
                case 2 -> createAdmin(scanner);
                case 3 -> {
                    adminRunning = false;
                    System.out.println("Logging out of admin mode...");
                }
                default -> System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    private static void viewEnrollments() {
        Admin  admin = new Admin(dbManager);
        admin.queryAllStudents();
        pauseScreen();
        clearScreen();
    }

    // Create new admin
    private static void createAdmin(Scanner scanner) {

        Admin  admin = new Admin(dbManager);
        eniro();
        System.out.println("\n\n---------------------------------------------------------------");
        System.out.println("*******************      ADMIN SERVICE      *******************");
        System.out.println("---------------------------------------------------------------");
        System.out.println("+--- Create New Admin ");
        System.out.print("New Username: ");
        String newUsername = scanner.nextLine();

        System.out.print("New Password: ");
        String newPassword = scanner.nextLine();

        System.out.print("Confirm New Password: ");
        String confirmNewPassword = scanner.nextLine();

        if(confirmNewPassword.equals(newPassword)){
            admin.addAdmin(newUsername, confirmNewPassword);
        }

        else{
            System.out.println("Passwords do not match");
        }

        pauseScreen();

    }

}
