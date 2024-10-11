

public class Pupil {
    // Attributes
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private String gradeLevel;
    private final DatabaseManager dbManager;


    // constructor
    public Pupil(DatabaseManager dbManager, String firstName, String lastName, int age, String gender, String gradeLevel) {
        this.dbManager = dbManager;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.gradeLevel = gradeLevel;

    }
    
    // setters

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGradeLevel(String gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    // getters

    public int getAge() {
        return age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getGender() {
        return gender;
    }
  
    public String getGradeLevel() {
        return gradeLevel;
    }

    public String getLastName() {
        return lastName;
    }

   



    // Adding the student to the database
     public void addPupil(Pupil newPupil){
        int pupilID = (dbManager.countPupils() + 1);
   
        String[] columns = {"pupilID", "firstName", "lastName", "age", "gender", "gradeLevel"};
        String[] values = {String.valueOf(pupilID), newPupil.getFirstName(), newPupil.getLastName(), String.valueOf(newPupil.getAge()), newPupil.getGender(),newPupil.getGradeLevel()}; 
        dbManager.insert("Pupil", columns, values);

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
