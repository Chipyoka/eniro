
public class Class {
    // Attributes
    private String classID;

    // default constructor
    public Class() {
        classID = "8D";   
    }

   //  setter
    public void setClassID(String classID) {
        this.classID = classID;
    }

   //  getter
    public String getClassID() {
         return classID;
    }


    // determine class depending on the stream and grade level

    public String matchClass(String stream,  String gradeLevel) {

        if(stream.equals("business")  && gradeLevel.equals("8")){
            return "8A";
         }else if (stream.equals("social sciences")  && gradeLevel.equals("8")){
            return "8B";
         }
         else if (stream.equals("stem")  && gradeLevel.equals("8")){
            return "8C";
         
         }else if (stream.equals("general studies")  && gradeLevel.equals("8")){
            return "8D";
         
         }else if (stream.equals("business")  && gradeLevel.equals("10")){
            return "10A";
         
         }else if (stream.equals("social sciences")  && gradeLevel.equals("10")){
            return "10B";
         
         }else if (stream.equals("stem")  && gradeLevel.equals("10")){
            return "10C";
         }

        else{
            return "10D";
        }

    }
}
