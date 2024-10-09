import java.sql.ResultSet;

public class Admin {
    private String username;
    private String passwordHash;
    private final DatabaseManager dbManager; // Use the existing DatabaseManager

    // Constructor takes the dbManager as a parameter
    public Admin(DatabaseManager dbManager) {
        this.dbManager = dbManager;
        this.username = "admin";
        this.passwordHash = "password";
    }

    // Getters and Setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    // hash password
    public String hashPassword(String password) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    // Add Admin using the existing connection
    public void addAdmin(String newUsername, String newPassword) {
        passwordHash = hashPassword(newPassword);

        String[] columns = {"adminName", "adminPassword"};
        String[] values = {newUsername, passwordHash};

        dbManager.insert("Admin", columns, values); 
        System.out.print("Admin Added Successfully");
    }

    // Query all students using the existing connection
    public void queryAllStudents() {
        String query = """
            SELECT 
                CONCAT(Pupil.firstname, ' ', Pupil.lastname) AS Name,
                Enrollment.classID AS Class,
                Class.className AS Stream
            FROM Enrollment
            JOIN Pupil ON Pupil.pupilID = Enrollment.pupilID
            JOIN Class ON Class.classID = Enrollment.classID;
        """;

        dbManager.fetch(query);
        ResultSet resultSet = dbManager.fetch(query);
    }


}
