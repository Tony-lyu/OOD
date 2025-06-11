import org.junit.Test;

/**
 * Tests for LikertQuestion.
 */
public class LikertQuestionTest extends AbstractTest {

  /**
   * Constructor for LikertQuestion test.
   */
  public LikertQuestionTest() {
    String longRandom;
    longRandom = "aosdifjaso oifhas;ldihv;al skdfha;osidghv;osiadhvbasdjkhvn";
  }

  /**
   * Tests for creating LikertQuestion.
   */
  @Test(expected = Test.None.class)
  public void testCreateValidLikertQuestion() {
    testCreateValidQuestion("Likert");
  }

  /**
   * Tests for invalid LikertQuestion.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCreateLikertQuestionNoText() {
    testCreateQuestionNoQuestionMark("");
  }

  /**
   * Tests for creating invalid LikertQuestion.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCreateYesNoQuestionNoQuestionMark() {
    testCreateQuestionNoQuestionMark("Likert");
  }

  /**
   * Tests for correctly answered LikertQuestion.
   */
  @Test(expected = Test.None.class)
  public void testAnswerCorrectly() {
    String[] answers = {"strongly agree",
                        "agree",
                        "neither agree nor disagree",
                        "disagree",
                        "strongly disagree"};
    testCorrectAnswer(answers, "Likert");
  }

  /**
   * Tests for incorrectly answered LikertQuestion.
   */
  @Test(expected = Test.None.class)
  public void testAnswerInCorrectly() {
    String[] answers = {"weakly disagree", ""};
    testInCorrectAnswer(answers, "Likert");
  }


}