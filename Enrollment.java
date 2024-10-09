public class Enrollment{
    // attributes
    private String enrollmentDate;


    Enrollment(String enrollmentDate){
        this.enrollmentDate = enrollmentDate;
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
        int pupilID = (dbManager.countPupils() + 1);

        String[] columns = {"pupilID", "classID", "enrollmentID"};
        String[] values = {String.valueOf(pupilID), classID, this.enrollmentDate};  
        dbManager.insert("Enrollment", columns, values);  // Insert the enrollment's record into the database
    
        dbManager.disconnect();
    }


}