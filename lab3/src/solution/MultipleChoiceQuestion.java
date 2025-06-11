package solution;


/**
 * Represents a multiple choice question.
 */
public class MultipleChoiceQuestion extends AbstractQuestion {
  private String[] options = {"1", "2", "3", "4"};


  /**
   * Multiple Choice constructor.
   * @param text inherited string from abstract class representing text of the question
   * @throws IllegalArgumentException throws error when the question is invalid
   */
  public MultipleChoiceQuestion(String text) throws IllegalArgumentException {
    super(text, "MultipleChoice");
  }

  /**
   * To check if this question is answered.
   * @return true if this question is answered and false otherwise
   */
  public boolean hasBeenAnswered() {
    for (String option : options) {
      if (enteredAnswer.toLowerCase().equals(option)) {
        return true;
      }
    }
    return false;
  }
}

