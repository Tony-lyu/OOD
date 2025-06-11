import java.util.Random;

import solution.LikertQuestion;
import solution.MultipleChoiceQuestion;
import solution.Question;
import solution.YesNoQuestion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * An Abstract class to simplify tests for different types of questions.
 */
public class AbstractTest {

  private String longRandom = "aosdifjaso oifhas;ldihv;al skdfha;osidghv;osiadhvbasdjkhvn";

  /**
   * Tests for invalid Question.
   */
  public void testCreateValidQuestion(String s) {
    Random r = new Random(200);
    Question q;
    for (int i = 0; i < 1000; i++) {
      int start = r.nextInt(longRandom.length() - 1);
      int end = start + r.nextInt(longRandom.length() - start - 1) + 1;
      String questionText = longRandom.substring(start, end);
      if (s.equals("Likert")) {
        q = new LikertQuestion(questionText + "?");
      } else if (s.equals("MultipleChoice")) {
        q = new MultipleChoiceQuestion(questionText + "?");
      } else {
        q = new YesNoQuestion(questionText + "?");
      }

      assertEquals(questionText + "?", q.getQuestionText());
      assertEquals(s, q.getType());
    }
  }

  /**
   * Tests for invalid Question.
   */
  public void testCreateQuestionNoQuestionMark(String s) {
    if (s.equals("Likert")) {
      new LikertQuestion("Is this fun");
    } else if (s.equals("MultipleChoice")) {
      new MultipleChoiceQuestion("Is this fun");
    } else {
      new YesNoQuestion("Is this fun");
    }
  }


  /**
   * Tests for correctly answered Question.
   */
  public void testCorrectAnswer(String[] answers, String type) {
    Question q;
    for (String answer : answers) {
      if (type.equals("Likert")) {
        q = new LikertQuestion("Is this a trick question?");
      } else if (type.equals("MultipleChoice")) {
        q = new MultipleChoiceQuestion("Is this a trick question?");
      } else {
        q = new YesNoQuestion("Is this a trick question?");
      }
      assertFalse(q.hasBeenAnswered());
      q.answer(answer);
      assertEquals(answer.toLowerCase(), q.getEnteredAnswer());
      assertTrue(q.hasBeenAnswered());
    }
  }

  /**
   * Tests for incorrectly answered Question.
   */
  public void testInCorrectAnswer(String[] answers, String type) {
    Question q;
    for (String answer : answers) {
      if (type.equals("Likert")) {
        q = new LikertQuestion("Is this a trick question?");
      } else if (type.equals("Multiple")) {
        q = new MultipleChoiceQuestion("Is this a trick question?");
      } else {
        q = new YesNoQuestion("Is this a trick question?");
      }
      assertFalse(q.hasBeenAnswered());
      try {
        q.answer(answer);
        fail("Yes No question accepted an invalid answer");
      } catch (IllegalArgumentException e) {
        assertFalse(q.hasBeenAnswered());
      }
    }
  }
}



