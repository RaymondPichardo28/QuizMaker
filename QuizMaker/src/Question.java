
public class Question {
	private String question; // Holds the question
	private String[] answers; // Holds the answer choices
	private int correctAnswer; // Holds the correct answer
	private int answerIndex = 0; // Holds current index of answer being added
	
	public Question() {
		
	}
	
	public void setQuestion(String userQuestion) {
		question = userQuestion;
	}
	
	public String getQuestion() {
		return question;
	}
	
	public void setNumAnswers(int numAnswers) {
		answers = new String [numAnswers];
	}
	
	public int getNumAnswers() {
		return answers.length;
	}
	
	public String getAnswers() {
		String allAnswers = ""; // Declares allAnswers variable to display answers
		for (int i = 0; i < answers.length; ++i) {
			allAnswers += (i+1) + ". " + answers[i] + "\n"; // Stores answers in a numbered list format in allAnswers
		}
		return allAnswers; // Returns allAnswers
	}
	
	public void addAnswer(String answerChoice) {
		answers[answerIndex] = answerChoice; // assigns answer choice to respective index in answers array
		answerIndex++; // Increments answer index for next input
	}
	
	public void setCorrectAnswer(int correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	
	public int getCorrectAnswer() {
		return correctAnswer;
	}
	
	public String getCorrectAnswerString() {
		return answers[correctAnswer-1];
	}
	
	public String getAnswerString(int userAnswer) {
		return answers[userAnswer-1];
	}
	
	// need method to set correct answer
}
