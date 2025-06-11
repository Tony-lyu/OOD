package solution;

/**
 * Abstract Question class to simplify repeated code for different types of questions.
 */
public abstract class AbstractQuestion implements Question {
  String questionText;
  String enteredAnswer;
  String type;

  /**
   * General question constructor.
   *
   * @param text a String to represent the body of the question
   * @param type a String to represent the type of question
   * @throws IllegalArgumentException throws error when there is no text or no question mark
   *                                  in the question body.
   */
  public AbstractQuestion(String text, String type) throws IllegalArgumentException {
    if ((text.length() == 0 || (text.charAt(text.length() - 1) != '?'))) {
      throw new IllegalArgumentException("Invalid question text");
    }
    this.questionText = text;
    enteredAnswer = "";
    this.type = type;
  }

  /**
   * Getter method to access text of a question.
   *
   * @return the text of the question
   */
  public String getQuestionText() {
    return questionText;
  }

  /**
   * Getter method to access the type of a question.
   *
   * @return the type of the question
   */
  public String getType() {
    return this.type;
  }


  /**
   * A method that checks if the answered of a question is valid.
   *
   * @param enteredAnswer the answer entered for this question
   */
  public void answer(String enteredAnswer) {
    this.enteredAnswer = enteredAnswer;
    if (this.hasBeenAnswered()) {
      this.enteredAnswer = enteredAnswer.toLowerCase();
    } else {
      throw new IllegalArgumentException("Invalid answer: " + enteredAnswer);
    }
  }

  /**
   * A method that returns the answer of a question if it's answered.
   *
   * @return A string represents the answer of the question
   */
  public String getEnteredAnswer() {
    if (!hasBeenAnswered()) {
      throw new IllegalStateException("solution.solution.Question not attempted yet!");
    } else {
      return enteredAnswer;
    }
  }
}
