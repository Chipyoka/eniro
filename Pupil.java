import java.util.ArrayList;
import java.util.List;
// import java.util.Map;

public class Pupil {
    // Attributes
    private String pupilID;
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private int gradeLevel;
    private List<String> interests;
    private List<SoftSkillAssessment> softSkills;

    public Pupil(String pupilID, String firstName, String lastName, int age, String gender, int gradeLevel, String stream) {
        this.pupilID = pupilID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.gradeLevel = gradeLevel;
        this.interests = new ArrayList<>();  // Assuming you have an interests field
        this.softSkills = new ArrayList<>();  // Assuming you have a softSkills field
    }
    

    // Getters and Setters
    public String getPupilID() {
        return pupilID;
    }

    public void setPupilID(String pupilID) {
        this.pupilID = pupilID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(int gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public List<String> getInterests() {
        return interests;
    }

    public void setInterests(List<String> interests) {
        this.interests = interests;
    }

    public List<SoftSkillAssessment> getSoftSkills() {
        return softSkills;
    }

    public void setSoftSkills(List<SoftSkillAssessment> softSkills) {
        this.softSkills = softSkills;
    }

    // Method to get full details of the Pupil
    public void getDetails() {
        System.out.println("Pupil ID: " + pupilID);
        System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("Age: " + age);
        System.out.println("Gender: " + gender);
        System.out.println("Grade Level: " + gradeLevel);
        System.out.println("Interests: " + String.join(", ", interests));
        if (softSkills != null && !softSkills.isEmpty()) {
            System.out.println("Soft Skills: ");
            for (SoftSkillAssessment skill : softSkills) {
                skill.generateSkillSummary();
            }
        } else {
            System.out.println("Soft Skills have not been assessed yet.");
        }
    }

    // Method to update the pupil's profile (basic details)
    public void updateProfile(String firstName, String lastName, int age, String gender, int gradeLevel, List<String> interests) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.gradeLevel = gradeLevel;
        this.interests = interests;
        System.out.println("Profile updated successfully.");
    }

    // Method to enroll the pupil in a class
    public void enrollInClass(Class pupilClass, Enrollment enrollment) {
        enrollment.enrollStudent(this, pupilClass);
        System.out.println(firstName + " " + lastName + " has been enrolled in class " + pupilClass.getClassName());
    }


    // Method to assess soft skills
    public void assessSoftSkills(SoftSkillAssessment assessment) {
        this.softSkills.add(assessment);  // Add assessment to the list
        System.out.println("Soft skills assessment has been added.");
    }

    @Override
    public String toString() {
        return "Pupil{" +
                "pupilID='" + pupilID + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", gradeLevel=" + gradeLevel +
                ", interests=" + interests +
                ", softSkills=" + softSkills +
                '}';
    }
}
