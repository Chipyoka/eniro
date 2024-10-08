import java.util.HashMap;
import java.util.Map;

public class SoftSkillAssessment {
    // Attributes
    private String assessmentID;
    private Map<String, Integer> skillRatings;  // Map of soft skills and their ratings (1-5 scale)

    // Constructor
    public SoftSkillAssessment(String assessmentID) {
        this.assessmentID = assessmentID;
        this.skillRatings = new HashMap<>();
        initializeSkills();  // Initialize with common soft skills
    }
    // Constructor
    public SoftSkillAssessment() {
        this.assessmentID = "default";

        this.skillRatings = new HashMap<>();
        initializeSkills();  // Initialize with common soft skills
    }


    // Getters and Setters
    public String getAssessmentID() {
        return assessmentID;
    }

    public void setAssessmentID(String assessmentID) {
        this.assessmentID = assessmentID;
    }

    public Map<String, Integer> getSkillRatings() {
        return skillRatings;
    }

    public void setSkillRatings(Map<String, Integer> skillRatings) {
        this.skillRatings = skillRatings;
    }


     

    // Method to assess skills and recommend a stream
    public String assessSkills(int communication, int teamwork, int problemSolving, int creativity) {
        // Simple logic to determine stream based on scores
        if (communication > 7 && teamwork > 7) {
            return "Arts";
        } else if (problemSolving > 7 && creativity > 7) {
            return "Science";
        } else {
            return "General Studies";
        }
    }

    // Method to initialize common soft skills
    private void initializeSkills() {
        skillRatings.put("Communication", 0);
        skillRatings.put("Teamwork", 0);
        skillRatings.put("Problem Solving", 0);
        skillRatings.put("Adaptability", 0);
        skillRatings.put("Time Management", 0);
        skillRatings.put("Leadership", 0);
    }

    // Method to rate a soft skill
    public void rateSkill(String skill, int rating) {
        if (skillRatings.containsKey(skill) && rating >= 1 && rating <= 5) {
            skillRatings.put(skill, rating);
        } else {
            System.out.println("Invalid skill or rating. Please provide a rating between 1 and 5.");
        }
    }

    // Method to generate a summary of soft skills
    public void generateSkillSummary() {
        System.out.println("Soft Skill Assessment Summary:");
        for (Map.Entry<String, Integer> entry : skillRatings.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " out of 5");
        }
    }

    // Method to conduct a soft skill assessment for a pupil
    public void conductAssessment(Pupil pupil, Map<String, Integer> responses) {
        System.out.println("Conducting soft skill assessment for Pupil: " + pupil.getFirstName() + " " + pupil.getLastName());
        for (Map.Entry<String, Integer> entry : responses.entrySet()) {
            rateSkill(entry.getKey(), entry.getValue());
        }
        generateSkillSummary();
    }
}
