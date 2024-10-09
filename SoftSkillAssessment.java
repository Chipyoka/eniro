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
  

    public void setScore(int Score) {
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

        

        // Simple logic to determine stream based on scores
        if (communication > 7 && teamwork > 7) {
            return "Arts";
        } else if (problemSolving > 7 && creativity > 7) {
            return "stem";
        } else if (problemSolving > 7 && creativity > 7) {
            return "stem";
        } else {
            return "General";
        }
    }

      // Method to ask a yes/no question and return a score based on the response
      public static int askQuestion(Scanner scanner, String question) {
        System.out.println(question + " (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();
    
        // If "yes", give 10 points; if "no", give 5 points
        if (response.equals("yes")) {
            return 10;  // High score for positive answer
        } else if (response.equals("no")) {
            return 5;  // Lower score for negative answer
        } else {
            System.out.println("Invalid response. Defaulting to 5 points.");
            return 5;  // Default to 5 for invalid input
        }
    }

    public void questionnaire ( Scanner scanner){

        System.out.println("\n--- Soft Skill Assessment ---");

        // Ask communication-related questions
        int communicationScore = askQuestion(scanner, "Do you enjoy speaking in front of groups or leading discussions?");
        
        // Ask teamwork-related questions
        int teamworkScore = askQuestion(scanner, "Do you prefer working with others in a team rather than alone?");
        
        // Ask problem-solving-related questions
        int problemSolvingScore = askQuestion(scanner, "Do you enjoy solving puzzles or figuring out complex problems?");
        
        // Ask creativity-related questions
        int creativityScore = askQuestion(scanner, "Do you often come up with creative ideas or new ways of doing things?");

       this.stream = assessSkills(communicationScore, teamworkScore, problemSolvingScore, creativityScore);  // Assess and determine stream
    
       System.out.println("\nBased on the assessment, the recommended stream for the pupil is: " + stream);
   
    }

  
  

}
