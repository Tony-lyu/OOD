package solution;

/**
 * Represents a Yes or No choice question.
 */
public class YesNoQuestion extends AbstractQuestion {
  private final String[] options = {"Yes", "No"};

  /**
   * Yes No question constructor.
   * @param text inherited string from abstract class representing text of the question
   * @throws IllegalArgumentException throws error when the question is invalid
   */
  public YesNoQuestion(String text) throws IllegalArgumentException {
    super(text, "YesNo");
  }

  /**
   * To check if this question is answered.
   *
   * @return true if this question is answered and false otherwise
   */
  public boolean hasBeenAnswered() {
    return enteredAnswer.toLowerCase().equals("yes") || enteredAnswer.toLowerCase().equals("no");
  }

}
