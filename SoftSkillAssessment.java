import java.util.Scanner;

public class SoftSkillAssessment {
    // Attributes
    private int score;
    private String stream;

  
    // Constructor
    public SoftSkillAssessment() {
        this.score = 2;
        this.stream = " ";
    }


    // Getters and Setters
  

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public String getStream(){
        return stream;
    }

    // Method to assess skills and recommend a stream
    public String assessSkills(int communication, int teamwork, int problemSolving, int creativity) {

        // Define thresholds
        int low = 5;   
        int high = 10; 

        // Simple logic to determine stream based on skill scores
        if (communication >= high && teamwork >= high && creativity >= high) {
            return "business"; 
        } else if (communication >= high && teamwork >= high && creativity <= low) {
            return "social sciences"; 
        } else if (problemSolving >= high && creativity >= high && teamwork <= low) {
            return "stem";  
        } else if (problemSolving >= high && creativity >= high && teamwork >= high) {
            return "stem";  
        } else if (communication <= low && teamwork <= low && creativity <= low && problemSolving <= low) {
            return "general studies"; 
        } else if (problemSolving >= high && teamwork >= high && communication <= low && creativity <= low) {
            return "stem"; 
        } else if (communication >= high && problemSolving <= low && creativity >= high) {
            return "social sciences";  
        } else if (problemSolving <= low && creativity >= high && teamwork >= high) {
            return "business"; 
        } else {
            return "General Studies";  
        }
    }


      // Method to ask a yes/no question and return a score based on the response
      public static int askQuestion(Scanner scanner, String question) {
        System.out.print(question + " (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();
    
        // If "yes", give 10 points; if "no", give 5 points
        switch (response) {
            case "yes" -> {
                return 10;  // High score for positive answer
            }
            case "no" -> {
                return 5;  // Lower score for negative answer
            }
            default -> {
                System.out.println("Invalid response. Defaulting to 5 points.");
                return 5;  // Default to 5 for invalid input
            }
        }
    }

    public String[] suggestCareers(String stream) {
        String[] careers = new String[3];
    
        switch (stream) {
            case "business" -> {
                careers[0] = "Manager";
                careers[1] = "Accountant";
                careers[2] = "Financial Analyst";
                }
            case "social sciences" -> {
                careers[0] = "Social Worker";
                careers[1] = "Counselor";
                careers[2] = "Policy Analyst";
                }
            case "stem" -> {
                careers[0] = "Engineer";
                careers[1] = "Scientist";
                careers[2] = "Doctor";
                }
            case "general studies" -> {
                careers[0] = "Teacher";
                careers[1] = "Writer";
                careers[2] = "Librarian";
                }
            default -> {
                careers[0] = "Career Counselor";
                careers[1] = "Academic Advisor";
                careers[2] = "Career Coach";
                }
        }
    
        return careers;
    }
      

    public void questionnaire ( Scanner scanner){

        System.out.println("\n\n---------------------------------------------------------------");
        System.out.println("****************      ASSESS SOFT SKILLS      *****************");
        System.out.println("---------------------------------------------------------------");

        // Ask communication-related questions
        int communicationScore = askQuestion(scanner, "   Do you enjoy leading discussions?");
        
        // Ask teamwork-related questions
        int teamworkScore = askQuestion(scanner, "   Do you prefer working with others in a team?");
        
        // Ask problem-solving-related questions
        int problemSolvingScore = askQuestion(scanner, "   Do you enjoy solving puzzles?");
        
        // Ask creativity-related questions
        int creativityScore = askQuestion(scanner, "   Do you often come up with new ways of doing things?");

       this.stream = assessSkills(communicationScore, teamworkScore, problemSolvingScore, creativityScore);  
    
       System.out.println("\n   Recommended stream for the pupil is: " + stream.toUpperCase());

    //    print career suggestions

       String[] careerList = suggestCareers(stream);
       int i = 0;
       System.out.println("\n---------------------------------------------------------------");

       while (i < careerList.length) {
            System.out.println("   Career Suggestion " + (i + 1) + ": " + careerList[i]);
            i++;
        }
    }

 

}
