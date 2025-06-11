
import org.junit.Test;

import solution.YesNoQuestion;

/**
 * Tests for YesNoQuestion Class.
 */
public class YesNoQuestionTest extends AbstractTest {

  /**
   * Constructor for test.
   */
  public YesNoQuestionTest() {
    String longRandom;
    longRandom = "aosdifjaso oifhas;ldihv;al skdfha;osidghv;osiadhvbasdjkhvn";
  }


  /**
   * Tests for creating YesNoQuestion.
   */
  @Test(expected = Test.None.class)
  public void testCreateValidYesNoQuestion() {
    testCreateValidQuestion("YesNo");
  }

  /**
   * Tests for creating invalid YesNoQuestion.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCreateYesNoQuestionNoText() {
    new YesNoQuestion("");
  }

  /**
   * Tests for correctly answered YesNoQuestion.
   */
  @Test(expected = Test.None.class)
  public void testAnswerCorrectly() {
    String[] answers = {"yes", "Yes", "YEs", "YeS", "YES", "yEs", "yES", "yeS",
                        "no", "No", "nO", "NO"};
    testCorrectAnswer(answers, "YesNo");
  }

  /**
   * Tests for incorrectly answered YesNoQuestion.
   */
  @Test(expected = Test.None.class)
  public void testAnswerInCorrectly() {
    String[] answers = {"yess", ""};
    testInCorrectAnswer(answers, "YesNo");
  }


}