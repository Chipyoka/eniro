import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Enrollment {
    // Attributes
    private String enrollmentID;
    private Map<Pupil, Class> enrolledPupils;  // Mapping Pupil to Class
    private Map<Pupil, String> enrollmentStatus; // Status can be "Enrolled", "Completed", "Withdrawn"

    // Constructor
    public Enrollment(String enrollmentID) {
        this.enrollmentID = enrollmentID;
        this.enrolledPupils = new HashMap<>();
        this.enrollmentStatus = new HashMap<>();
    }

    // Getters and Setters
    public String getEnrollmentID() {
        return enrollmentID;
    }

    public void setEnrollmentID(String enrollmentID) {
        this.enrollmentID = enrollmentID;
    }

    public Map<Pupil, Class> getEnrolledPupils() {
        return enrolledPupils;
    }

    public Map<Pupil, String> getEnrollmentStatus() {
        return enrollmentStatus;
    }

    // Method to enroll a pupil in a class
    public void enrollStudent(Pupil pupil, Class pupilClass) {
        if (!enrolledPupils.containsKey(pupil)) {
            enrolledPupils.put(pupil, pupilClass);
            enrollmentStatus.put(pupil, "Enrolled");
            System.out.println(pupil.getFirstName() + " " + pupil.getLastName() + " has been successfully enrolled in class " + pupilClass.getClassName());
        } else {
            System.out.println(pupil.getFirstName() + " " + pupil.getLastName() + " is already enrolled in a class.");
        }
    }

    // Method to withdraw a pupil from a class
    public void withdrawStudent(Pupil pupil) {
        if (enrolledPupils.containsKey(pupil)) {
            enrolledPupils.remove(pupil);
            enrollmentStatus.put(pupil, "Withdrawn");
            System.out.println(pupil.getFirstName() + " " + pupil.getLastName() + " has been withdrawn from the class.");
        } else {
            System.out.println(pupil.getFirstName() + " " + pupil.getLastName() + " is not enrolled in any class.");
        }
    }

    // Method to mark a pupil's class as completed
    public void completeClass(Pupil pupil) {
        if (enrolledPupils.containsKey(pupil)) {
            enrollmentStatus.put(pupil, "Completed");
            System.out.println(pupil.getFirstName() + " " + pupil.getLastName() + " has completed the class.");
        } else {
            System.out.println(pupil.getFirstName() + " " + pupil.getLastName() + " is not enrolled in any class.");
        }
    }

    // Method to get a list of all pupils in a class
    public List<Pupil> getPupilsInClass(Class pupilClass) {
        return enrolledPupils.entrySet().stream()
                .filter(entry -> entry.getValue().equals(pupilClass))
                .map(Map.Entry::getKey)
                .toList();
    }

    // Method to get the enrollment status of a specific pupil
    public String getPupilEnrollmentStatus(Pupil pupil) {
        return enrollmentStatus.getOrDefault(pupil, "Not Enrolled");
    }

    // Method to print the enrollment details of all pupils
    public void printEnrollmentDetails() {
        System.out.println("Enrollment ID: " + enrollmentID);
        for (Map.Entry<Pupil, Class> entry : enrolledPupils.entrySet()) {
            Pupil pupil = entry.getKey();
            Class pupilClass = entry.getValue();
            String status = enrollmentStatus.get(pupil);
            System.out.println("Pupil: " + pupil.getFirstName() + " " + pupil.getLastName() + " | Class: " + pupilClass.getClassName() + " | Status: " + status);
        }
    }

    @Override
    public String toString() {
        return "Enrollment{" +
                "enrollmentID='" + enrollmentID + '\'' +
                ", enrolledPupils=" + enrolledPupils.size() +
                ", enrollmentStatus=" + enrollmentStatus.size() +
                '}';
    }
}
