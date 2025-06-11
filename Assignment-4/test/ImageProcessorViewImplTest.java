import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import view.ImageProcessorViewImpl;
import static org.junit.Assert.assertEquals;

/**
 * Test for Image Processor View Implementation.
 */
public class ImageProcessorViewImplTest {
  Appendable ap;
  ImageProcessorViewImpl view;

  /**
   * set up for tests.
   */
  @Before
  public void setup() {
    ap = new StringBuilder();
    view = new ImageProcessorViewImpl(ap);
  }

  @Test
  public void defaultConstructor() throws IOException {
    view.renderMessage("String Message");
    assertEquals(ap.toString(), "String Message");
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidConstructor() {
    view = new ImageProcessorViewImpl(null);
  }

  @Test
  public void validRenderMessage() throws IOException {
    view.renderMessage("String Message");
    assertEquals(ap.toString(), "String Message");
    view.renderMessage(" some message.");
    assertEquals(ap.toString(), "String Message some message.");
    view.renderMessage("\nStarted a new line.");
    assertEquals(ap.toString(), "String Message some message.\nStarted a new line.");
  }

  @Test(expected = IOException.class)
  public void renderMessageIOException() throws IOException {
    Appendable mockAp = new MockAppendable();
    view = new ImageProcessorViewImpl(mockAp);
    view.renderMessage("some message");
  }
}