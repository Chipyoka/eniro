
public class Admin {
    // Attributes
    private String username;
    private String passwordHash;  // For secure storage of the password

    // Constructor
    public Admin() {
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

    public void addAdmin(String newUsername, String newPassword) {
        DatabaseManager dbManager = new DatabaseManager("school_system.db");
        dbManager.connect();
        passwordHash = hashPassword(newPassword);

        String[] columns = {"adminName", "adminPassword"};
        String[] values = {newUsername, passwordHash};

        dbManager.insert("Admin", columns, values); 
        System.out.print("Admin Added Successfully");

        dbManager.disconnect();

    }

    public void queryAllStudents() {
    DatabaseManager dbManager = new DatabaseManager("school_system.db");
    dbManager.connect();
    String query = """
                    SELECT 
                        CONCAT( pupil.firstname, " ", Pupil.lastname) as Name,
                        Enrollment.classID AS Class,
                        Class.className As Stream
                    FROM Enrollment
                    JOIN
                        Pupil ON Pupil.pupilID = Enrollment.pupilID
                    JOIN 
                        class ON Class.classID = Enrollment.classID;
                    """;

    dbManager.fetch(query);
    dbManager.disconnect();
}



}
