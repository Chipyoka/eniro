import java.util.ArrayList;
import java.util.List;

public class Class {
    // Attributes
    private String classID;
    private String className;
    private String streamType;  // E.g., STEM, Business, Arts, etc.
    private List<Pupil> pupils;

    // Constructor
    public Class(String classID, String className, String streamType) {
        this.classID = classID;
        this.className = className;
        this.streamType = streamType;
        this.pupils = new ArrayList<>();
    }

    // Getters and Setters
    public String getClassID() {
        return classID;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getStreamType() {
        return streamType;
    }

    public void setStreamType(String streamType) {
        this.streamType = streamType;
    }

    public List<Pupil> getPupils() {
        return pupils;
    }

    public void setPupils(List<Pupil> pupils) {
        this.pupils = pupils;
    }

    // Method to add a pupil to the class
    public void addStudentToClass(Pupil pupil) {
        if (!pupils.contains(pupil)) {
            pupils.add(pupil);
            System.out.println("Pupil " + pupil.getFirstName() + " " + pupil.getLastName() + " has been added to class " + className);
        } else {
            System.out.println("Pupil " + pupil.getFirstName() + " " + pupil.getLastName() + " is already in the class.");
        }
    }

    // Method to remove a pupil from the class
    public void removeStudent(Pupil pupil) {
        if (pupils.contains(pupil)) {
            pupils.remove(pupil);
            System.out.println("Pupil " + pupil.getFirstName() + " " + pupil.getLastName() + " has been removed from class " + className);
        } else {
            System.out.println("Pupil not found in class " + className);
        }
    }

    // Method to get the list of all pupils in the class
    public void getClassList() {
        if (pupils.isEmpty()) {
            System.out.println("No pupils enrolled in class " + className);
        } else {
            System.out.println("List of pupils in class " + className + ":");
            for (Pupil pupil : pupils) {
                System.out.println("- Pupil ID: " + pupil.getPupilID() + ", Name: " + pupil.getFirstName() + " " + pupil.getLastName());
            }
        }
    }

    // Method to print class details
    public void printClassDetails() {
        System.out.println("Class ID: " + classID + ", Class Name: " + className + ", Stream Type: " + streamType);
    }
}
