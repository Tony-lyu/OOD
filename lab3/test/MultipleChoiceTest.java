import org.junit.Test;

/**
 * Tests for MultipleChoiceQuestion.
 */
public class MultipleChoiceTest extends AbstractTest {


  /**
   * MultipleChoiceQuestion test constructor.
   */
  public MultipleChoiceTest() {
    String longRandom;
    longRandom = "aosdifjaso oifhas;ldihv;al skdfha;osidghv;osiadhvbasdjkhvn";
  }

  /**
   * Tests for creating MultipleChoiceQuestion.
   */
  @Test(expected = Test.None.class)
  public void testCreateValidMultipleChoiceQuestion() {
    testCreateValidQuestion("MultipleChoice");
  }

  /**
   * Tests for creating invalid MultipleChoiceQuestion.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCreateMultipleChoiceQuestionNoText() {
    testCreateQuestionNoQuestionMark("");
  }

  /**
   * Tests for invalid MultipleChoiceQuestion.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCreateMultipleChoiceQuestionNoQuestionMark() {
    testCreateQuestionNoQuestionMark("MultipleChoice");
  }

  /**
   * Tests for correctly answered LikertQuestion.
   */
  @Test(expected = Test.None.class)
  public void testAnswerCorrectly() {
    String[] answers = {"3", "1", "3", "4"};
    testCorrectAnswer(answers, "MultipleChoice");
  }

  /**
   * Tests for incorrectly answered LikertQuestion.
   */
  @Test(expected = Test.None.class)
  public void testAnswerInCorrectly() {
    String[] answers = {"4", "9"};
    testInCorrectAnswer(answers, "Likert");
  }

}
