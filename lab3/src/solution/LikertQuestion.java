package solution;

/**
 * Represents a 5 scaled Like rate question.
 */
public class LikertQuestion extends AbstractQuestion {
  private String[] options = {"strongly agree", "agree", "neither agree nor disagree", "disagree",
                              "strongly disagree"};

  /**
   * Yes No question constructor.
   *
   * @param text inherited string from abstract class representing text of the question
   * @throws IllegalArgumentException throws error when the question is invalid
   */
  public LikertQuestion(String text) throws IllegalArgumentException {
    super(text, "Likert");
  }

  /**
   * To check if this question is answered.
   *
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
