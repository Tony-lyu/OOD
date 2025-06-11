import java.io.IOException;

/**
 * This is a mock appendable class for testing IOException.
 */
public class MockAppendable implements Appendable {
  @Override
  public Appendable append(CharSequence csq) throws IOException {
    throw new IOException("testing");
  }

  @Override
  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    throw new IOException("testing");
  }

  @Override
  public Appendable append(char c) throws IOException {
    throw new IOException("testing");
  }
}

