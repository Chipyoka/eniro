import java.util.ArrayList;
import java.util.List;
// import java.util.Map;

public class Pupil {
    // Attributes
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private String gradeLevel;

    public Pupil(String firstName, String lastName, int age, String gender, String gradeLevel) { 
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.gradeLevel = gradeLevel;

    }
    

    // Getters and Setters
 

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

    public String getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(String gradeLevel) {
        this.gradeLevel = gradeLevel;
    }



    // Adding the student to the database
     public void addPupil(Pupil newPupil, String stream){
        DatabaseManager dbManager = new DatabaseManager("school_system.db");
        dbManager.connect();
        int pupilID = (dbManager.countPupils() + 1);
        String x = String.valueOf(pupilID);
      
        System.out.println(x);
        String[] columns = {"gradeLevel", "firstName", "lastName", "age", "gender", "gradeLevel", "stream"};
        String[] values = {x, newPupil.getFirstName(), newPupil.getLastName(), String.valueOf(newPupil.getAge()), newPupil.getGender(),newPupil.getGradeLevel(), stream}; 
        dbManager.insert("Pupil", columns, values);

        System.out.println("\nEnrolling pupil into the " + stream + " stream.");

        dbManager.disconnect();
     }



    @Override
    public String toString() {
        return "Pupil{" +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", gradeLevel=" + gradeLevel  +
                '}';
    }
}
