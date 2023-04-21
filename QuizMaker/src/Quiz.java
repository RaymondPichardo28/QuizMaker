
import java.util.Scanner;

public class Quiz {
	
	// This method takes user input and makes sure it was a number
	public static int getAnswer(Scanner scnr, Question[] questions, int currQ) {
			int answer = 0; // int variable meant to hold the users inputted answer
			String userInput;
			//While loop continues until user inputs a number between 1 and the number of answers for the current question
	        	while (answer <= 0 || answer > questions[currQ].getNumAnswers()) {
	        		userInput = scnr.nextLine(); // Gets user input and stores it in a string variable.
					try 
		    		{
		    			answer = Integer.parseInt(userInput); // Takes user input and stores it in the variable "answer".
		    		}  
		    		catch (NumberFormatException e)  // If user inputs something that isn't a number.
		    		{ 
		    			System.out.println("That was not a number, try again");
		    		}
					// In case user enters an invalid number
					if (answer <= 0 || answer > questions[currQ].getNumAnswers()) {
						System.out.println("Please enter a number 1 - " + questions[currQ].getNumAnswers() + ", try again");
					}
	        	}
	        	return answer;
	         }
	
	// Takes string input and makes sure it is an integer
	public static int stringToInteger(Scanner scnr) {
		System.out.println("Enter a number");
		int answer = 0; // int variable meant to hold the users inputted answer
		String userInput;
		//While loop continues until user inputs a number
	    	while (answer == 0) {
	    		userInput = scnr.nextLine(); // Gets user input and stores it in a string variable.
				try 
	    		{
	    			answer = Integer.parseInt(userInput); // Takes user input and stores it in the variable "answer".
	    		}  
	    		catch (NumberFormatException e)  // If user inputs something that isn't a number.
	    		{ 
	    			System.out.println("That was not a number, try again");
	    		}
	    	}
	    	return answer;
	     }
	
	// Will allow user to enter the answers
	public static void addAnswers(Scanner scnr, int numAnswers, int currQ, Question[] questions) {
		String answerChoice; // Declares String variable answer choice to take user input
		
		for (int i = 0; i < numAnswers; i++) {
			answerChoice = scnr.nextLine(); // Takes user input and assigns it to answerChoice
			questions[currQ].addAnswer(answerChoice); // Adds answer choice to array of answers
		}
	}
	
	public static int calculateScore(int[] userAnswers, Question[] questions) {
		int score = 0;
		for (int i = 0; i < userAnswers.length; ++i) {
			if (userAnswers[i] == questions[i].getCorrectAnswer()) {
				score++;
				// Displays Correct Answer and Your answer for each question answered correctly
				System.out.println("Question " + (i+1) + ": \"" + questions[i].getQuestion() + "\" Correct \nCorrect Answer: " + questions[i].getCorrectAnswer() + ". " + questions[i].getCorrectAnswerString() + 
						"\nYour Answer: " + userAnswers[i] + ". " + questions[i].getAnswerString(userAnswers[i]) + "\n");
			}
			else {
				// Displays Correct Answer and Your answer for each question answered incorrectly
				System.out.println("Question " + (i+1) + ": \"" + questions[i].getQuestion() + "\" Inorrect \nCorrect Answer: " + questions[i].getCorrectAnswer() + ". " + questions[i].getCorrectAnswerString() + 
						"\nYour Answer: " + userAnswers[i] + ". " + questions[i].getAnswerString(userAnswers[i]) + "\n");
			}
		}
		return score;
	}

	public static void main(String[] args) {
		
		int currQ = 0; // Keeps track of what question the user is on
		boolean done = false; // Used to end while loop upon completion of the quiz
		Scanner scnr = new Scanner(System.in);
		int userAnswer;
		String guess;
		String userQuestion;
		int numQuestions;
		int numAnswers = 0;
		int correctAnswer;
		
		// Prompts user to enter the number of questions the quiz is intended to have to initialize the size of the questions array
		System.out.println("How many questions will your quiz have?");
		
		// User initializes the size of the questions array, using getAnswer method to maintain scnr.nextLine
		numQuestions = stringToInteger(scnr);
		
		// Array of Question objects initialized to the number of questions
		Question[] questions = new Question [numQuestions];
		// Array of integers initialized to the number of questions to store user answers for end of quiz scoring
		int[] userAnswers = new int [numQuestions];
		
		while(!done) {
			
			questions[currQ] = new Question(); // Question object is created in questions array at index currQ
			
			System.out.println("Enter a question"); // Prompts user to enter a question
			userQuestion = scnr.nextLine(); // Allows user to input a question
			questions[currQ].setQuestion(userQuestion); // Sets the question
			
			System.out.println("How many answer choices will you provide?"); // Prompts user to set the number of answer choices for this question
			numAnswers = stringToInteger(scnr); // Allows user to set number of answer choices
			questions[currQ].setNumAnswers(numAnswers); // Initializes the size of the answer array
			
			System.out.println("You have created the following question, \n" + questions[currQ].getQuestion() + ", with " + questions[currQ].getNumAnswers() + " answer choices.");
			System.out.println("Enter answer choices one at a time");
			
			// User then enters answer choices and they are saved in the relevant question object
			addAnswers(scnr, numAnswers, currQ, questions);
			
			System.out.println("Which submission was correct? (First, second, third...)" + "\n" + questions[currQ].getAnswers());
			correctAnswer = stringToInteger(scnr);
			questions[currQ].setCorrectAnswer(correctAnswer);
			
			System.out.println("You have created the following question, \n" + questions[currQ].getQuestion() + ", with " + questions[currQ].getNumAnswers() + 
					" answer choices. \n" + questions[currQ].getAnswers());
			
			// Checks if this was the last question, if so, breaks while loop
			if ((currQ + 1) == questions.length) {
				done = true;
			}
			++currQ; // Increases currQ to move onto next question
			
		} // While loop
		
		currQ = 0; // Resets currQ to zero to reuse, reduce, and recycle
		done = false; // Resets done to false to reuse, reduce, and recycle
		
		// Starts a while loop in which questions are asked one after another
		while(!done) {
			System.out.println(questions[currQ].getQuestion() + "\n" + questions[currQ].getAnswers()); // Prints the current question
		
			userAnswer = getAnswer(scnr, questions, currQ); // Calls get answer method with scanner argument and assigns it to variable userAnswer
			userAnswers[currQ] = userAnswer; // Saves userAnswer to array of userAnswers for end of quiz scoring

			// Checks if this was the last question, if so, breaks while loop
			if ((currQ + 1) == questions.length) {
				done = true;
			}
			++currQ; // Increases currQ to move onto next question
			
		} // While loop
		
		System.out.println("You have reached the end of the quiz.");
		System.out.println("You scored " + calculateScore(userAnswers, questions) + "/" + numQuestions);
		
	} // Main

} // Class
