import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CareerGuidance {
    // Attributes
    private String guidanceID;                   // Unique identifier for guidance sessions
    private List<String> questionnaire;           // List of questions used for assessment
    private List<String> careerSuggestions;       // List of suggested careers based on assessment

    // Constructor with guidance ID
    public CareerGuidance(String guidanceID) {
        this.guidanceID = guidanceID;
        this.questionnaire = new ArrayList<>();
        this.careerSuggestions = new ArrayList<>();
        initializeQuestions();  // Initialize some sample questions
    }

    // Default constructor
    public CareerGuidance() {
        this.guidanceID = "default";
        this.questionnaire = new ArrayList<>();
        this.careerSuggestions = new ArrayList<>();
        initializeQuestions();  // Initialize some sample questions
    }

    // Getters and Setters
    public String getGuidanceID() {
        return guidanceID;
    }

    public void setGuidanceID(String guidanceID) {
        this.guidanceID = guidanceID;
    }

    public List<String> getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(List<String> questionnaire) {
        this.questionnaire = questionnaire;
    }

    public List<String> getCareerSuggestions() {
        return careerSuggestions;
    }

    public void setCareerSuggestions(List<String> careerSuggestions) {
        this.careerSuggestions = careerSuggestions;
    }

    // Method to initialize the questionnaire with sample questions
    private void initializeQuestions() {
        questionnaire.add("Do you enjoy working with numbers or solving complex problems?");
        questionnaire.add("Do you have a strong interest in science and technology?");
        questionnaire.add("Are you more comfortable with creative tasks like drawing or writing?");
        questionnaire.add("Do you enjoy helping people or working in a team?");
        questionnaire.add("Do you prefer working with your hands, such as building or fixing things?");
    }

    // Method to conduct assessment and return answers
    public List<String> conductAssessment(Map<String, String> pupilResponses) {
        List<String> responses = new ArrayList<>();
        for (String question : questionnaire) {
            // Check if pupilResponses contains a response for the question
            responses.add(pupilResponses.getOrDefault(question, "No response"));
        }
        return responses;
    }

    // Method to generate career suggestions based on assessment responses
    public List<String> generateSuggestion(List<String> responses) {
        careerSuggestions.clear();  // Clear previous suggestions

        for (String response : responses) {
            if (response.toLowerCase().contains("numbers") || response.toLowerCase().contains("complex")) {
                careerSuggestions.add("Finance, Accounting, Data Analysis");
            }
            if (response.toLowerCase().contains("science") || response.toLowerCase().contains("technology")) {
                careerSuggestions.add("Engineering, Computer Science, Medical Research");
            }
            if (response.toLowerCase().contains("creative")) {
                careerSuggestions.add("Graphic Design, Writing, Arts");
            }
            if (response.toLowerCase().contains("helping") || response.toLowerCase().contains("team")) {
                careerSuggestions.add("Social Work, Teaching, Human Resources");
            }
            if (response.toLowerCase().contains("hands") || response.toLowerCase().contains("fixing")) {
                careerSuggestions.add("Mechanics, Construction, Carpentry");
            }
        }
        // Default suggestion if no matches were found
        if (careerSuggestions.isEmpty()) {
            careerSuggestions.add("General Studies - Further Exploration Needed");
        }
        return careerSuggestions;
    }

    // Method to print out career suggestions
    public void printCareerSuggestions() {
        System.out.println("Career Suggestions based on the assessment:");
        for (String suggestion : careerSuggestions) {
            System.out.println("- " + suggestion);
        }
    }

    // Method to conduct a full guidance session
    public void conductGuidanceSession(Pupil pupil, Map<String, String> responses) {
        System.out.println("Conducting career guidance session for Pupil: " + pupil.getFirstName() + " " + pupil.getLastName());
        List<String> assessmentResponses = conductAssessment(responses);  // Get pupil responses
        generateSuggestion(assessmentResponses);  // Generate suggestions based on responses
        printCareerSuggestions();  // Print the suggestions
    }
}
