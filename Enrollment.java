public class Enrollment{
    // attributes
    private String enrollmentDate;


    Enrollment(){
        this.enrollmentDate = "01-01-2024";
    }


    public String getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(String enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    // method to enroll pupil
    public void enrollPupil(String classID) {
        DatabaseManager dbManager = new DatabaseManager("school_system.db");
        dbManager.connect();
        int pupilID = (dbManager.countPupils());

        String[] columns = {"pupilID", "classID", "enrollmentDate"};
        String[] values = {String.valueOf(pupilID), classID, enrollmentDate}; 
        dbManager.insert("Enrollment", columns, values);  // Insert the enrollment's record into the database
    
        dbManager.disconnect();
    }


}