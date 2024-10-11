public class Enrollment{
    // attributes
    private String enrollmentDate;
    private final DatabaseManager dbManager; // Use the existing DatabaseManager

    // constructor
    Enrollment(DatabaseManager dbManager){
        this.dbManager = dbManager;
        this.enrollmentDate = "01-01-2024";
    }


    //  setter
    public void setEnrollmentDate(String enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    // getter

    public String getEnrollmentDate() {
        return enrollmentDate;
    }


    // method to enroll pupil
    public void enrollPupil(String classID) {

        int pupilID = (dbManager.countPupils());

        String[] columns = {"pupilID", "classID", "enrollmentDate"};
        String[] values = {String.valueOf(pupilID), classID, enrollmentDate}; 
        dbManager.insert("Enrollment", columns, values); 
        System.out.println("\n   MESSAGE : Enrollment Successful\n");

    }


}