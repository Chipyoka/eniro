import java.util.List;

public class Admin {
    // Attributes
    private String adminID;
    private String username;
    private String passwordHash;  // For secure storage of the password

    // Constructor
    public Admin(String adminID, String username, String passwordHash) {
        this.adminID = adminID;
        this.username = username;
        this.passwordHash = passwordHash;
    }

    // Getters and Setters
    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }

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

    // Method to add a pupil to the system
    public void addPupil(List<Pupil> pupilList, Pupil newPupil) {
        pupilList.add(newPupil);
        System.out.println("Pupil " + newPupil.getFirstName() + " " + newPupil.getLastName() + " has been added.");
    }

    // Method to edit a pupil's details
    public void editPupil(Pupil pupil, String newFirstName, String newLastName, int newAge, String newGender, int newGradeLevel, List<String> newInterests) {
        pupil.setFirstName(newFirstName);
        pupil.setLastName(newLastName);
        pupil.setAge(newAge);
        pupil.setGender(newGender);
        pupil.setGradeLevel(newGradeLevel);
        pupil.setInterests(newInterests);
        System.out.println("Pupil " + pupil.getPupilID() + "'s details have been updated.");
    }

    // Method to delete a pupil from the system
    public void deletePupil(List<Pupil> pupilList, Pupil pupilToRemove) {
        if (pupilList.contains(pupilToRemove)) {
            pupilList.remove(pupilToRemove);
            System.out.println("Pupil " + pupilToRemove.getFirstName() + " " + pupilToRemove.getLastName() + " has been removed.");
        } else {
            System.out.println("Pupil not found.");
        }
    }

    // Method to assign a pupil to a class
    public void assignClass(Enrollment enrollment, Pupil pupil, Class pupilClass) {
        enrollment.enrollStudent(pupil, pupilClass);
        System.out.println("Pupil " + pupil.getFirstName() + " " + pupil.getLastName() + " has been assigned to class " + pupilClass.getClassName());
    }

    // Method to view the list of all pupils
    public void viewAllPupils(List<Pupil> pupilList) {
        if (pupilList.isEmpty()) {
            System.out.println("No pupils available.");
        } else {
            System.out.println("List of all pupils:");
            for (Pupil pupil : pupilList) {
                System.out.println("Pupil ID: " + pupil.getPupilID() + ", Name: " + pupil.getFirstName() + " " + pupil.getLastName());
            }
        }
    }

    // Method to assign grades to pupils
    public void assignGrade(Enrollment enrollment, Pupil pupil, String grade) {
        if (enrollment.getEnrolledPupils().containsKey(pupil)) {
            pupil.setGradeLevel(Integer.parseInt(grade));
            System.out.println("Assigned grade " + grade + " to pupil " + pupil.getFirstName() + " " + pupil.getLastName());
        } else {
            System.out.println("Pupil " + pupil.getFirstName() + " " + pupil.getLastName() + " is not enrolled in any class.");
        }
    }

    // Method to print admin details
    public void printAdminDetails() {
        System.out.println("Admin ID: " + adminID + ", Username: " + username);
    }
}
